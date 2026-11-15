package com.iam.policy.domain.converter;

import com.iam.common.domain.model.Effect;
import com.iam.common.domain.model.Permission;
import com.iam.common.domain.model.Resource;
import com.iam.policy.domain.model.Rule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

/** Persists policy rules as JSON in a single column. */
@Converter(autoApply = false)
public class RulesJsonConverter implements AttributeConverter<List<Rule>, String> {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<Rule> attribute) {
    if (attribute == null) {
      return null;
    }
    try {
      List<Map<String, Object>> payload = new ArrayList<>();
      for (Rule rule : attribute) {
        payload.add(
            Map.of(
                "effect", rule.effect().name(),
                "actions", rule.actions().stream().map(Permission::value).toList(),
                "resources", rule.resources().stream().map(Resource::value).toList()));
      }
      return OBJECT_MAPPER.writeValueAsString(payload);
    } catch (JacksonException ex) {
      throw new IllegalStateException("Failed to serialize policy rules", ex);
    }
  }

  @Override
  public List<Rule> convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return List.of();
    }
    try {
      List<Map<String, Object>> payload =
          OBJECT_MAPPER.readValue(dbData, new TypeReference<List<Map<String, Object>>>() {});
      List<Rule> rules = new ArrayList<>();
      for (Map<String, Object> row : payload) {
        Effect effect = parseStoredEffect((String) row.get("effect"));
        @SuppressWarnings("unchecked")
        List<String> actions = (List<String>) row.get("actions");
        @SuppressWarnings("unchecked")
        List<String> resources = (List<String>) row.get("resources");
        rules.add(
            Rule.createRule(
                effect,
                actions.stream().map(Permission::new).collect(Collectors.toSet()),
                resources.stream().map(Resource::new).collect(Collectors.toSet())));
      }
      return List.copyOf(rules);
    } catch (JacksonException ex) {
      throw new IllegalStateException("Failed to deserialize policy rules", ex);
    }
  }

  private static Effect parseStoredEffect(String raw) {
    if (raw == null || raw.isBlank()) {
      throw new IllegalArgumentException("effect must not be blank");
    }
    String normalized = raw.trim().toUpperCase();
    if ("ALLOW".equals(normalized)) {
      return Effect.PERMIT;
    }
    return Effect.valueOf(normalized);
  }
}