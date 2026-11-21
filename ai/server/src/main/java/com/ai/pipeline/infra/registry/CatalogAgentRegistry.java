package com.ai.pipeline.infra.registry;

import com.ai.common.domain.model.OwnerId;
import com.ai.common.infra.prompt.ClasspathPromptLoader;
import com.ai.common.infra.prompt.PromptTemplates;
import com.ai.pipeline.domain.model.AgentDefinition;
import com.ai.pipeline.domain.model.AgentType;
import com.ai.pipeline.domain.model.CustomAgent;
import com.ai.pipeline.domain.repository.AgentRegistry;
import com.ai.pipeline.domain.repository.CustomAgentRepository;
import com.ai.pipeline.service.AgentTemplate;
import com.ai.pipeline.service.AgentTemplateCatalog;
import com.explore.errors.DomainException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Builtin agent definitions from multilingual classpath templates, merged with client-owned library
 * definitions (same agentType overrides builtin).
 */
@Component
@RequiredArgsConstructor
public class CatalogAgentRegistry implements AgentRegistry {

  private final PromptTemplates promptTemplates;
  private final CustomAgentRepository customAgentRepository;

  /** Test helper: fixed in-memory catalog (not a Spring bean). */
  public static AgentRegistry createFixedRegistry(List<AgentDefinition> definitions) {
    Map<String, AgentDefinition> map = new LinkedHashMap<>();
    for (AgentDefinition definition : definitions) {
      map.put(definition.getType().getValue(), definition);
    }
    Map<String, AgentDefinition> fixed = Map.copyOf(map);
    return new AgentRegistry() {
      @Override
      public List<AgentDefinition> listBuiltins(String language) {
        return List.copyOf(fixed.values());
      }

      @Override
      public List<AgentDefinition> listAll(String ownerId, String language) {
        return listBuiltins(language);
      }

      @Override
      public List<AgentDefinition> listWorkers(String ownerId, String language) {
        List<AgentDefinition> workers = new ArrayList<>();
        for (AgentDefinition agent : listAll(ownerId, language)) {
          if (agent.isWorker()) {
            workers.add(agent);
          }
        }
        return List.copyOf(workers);
      }

      @Override
      public Optional<AgentDefinition> findByType(AgentType type, String ownerId, String language) {
        return Optional.ofNullable(fixed.get(type.getValue()));
      }

      @Override
      public AgentDefinition requireAgent(AgentType type, String ownerId, String language) {
        return findByType(type, ownerId, language)
            .orElseThrow(
                () ->
                    DomainException.createNotFoundError(
                        "AGENT_NOT_FOUND", "Unknown agent type: " + type.getValue()));
      }
    };
  }

  @Override
  public List<AgentDefinition> listBuiltins(String language) {
    return AgentTemplateCatalog.listAll(language).stream().map(this::toDefinition).toList();
  }

  @Override
  public List<AgentDefinition> listAll(String ownerId, String language) {
    Map<String, AgentDefinition> byType = new LinkedHashMap<>();
    for (AgentDefinition builtin : listBuiltins(language)) {
      byType.put(builtin.getType().getValue(), builtin);
    }
    for (CustomAgent saved : listLibraryAgents(ownerId)) {
      byType.put(saved.getAgentType(), saved.buildAgentDefinition());
    }
    return List.copyOf(byType.values());
  }

  @Override
  public List<AgentDefinition> listWorkers(String ownerId, String language) {
    List<AgentDefinition> workers = new ArrayList<>();
    for (AgentDefinition agent : listAll(ownerId, language)) {
      if (agent.isWorker()) {
        workers.add(agent);
      }
    }
    return List.copyOf(workers);
  }

  @Override
  public Optional<AgentDefinition> findByType(AgentType type, String ownerId, String language) {
    return listLibraryAgents(ownerId).stream()
        .filter(saved -> saved.hasAgentType(type))
        .findFirst()
        .map(CustomAgent::buildAgentDefinition)
        .or(
            () ->
                AgentTemplateCatalog.findByAgentType(type.getValue(), language)
                    .map(this::toDefinition));
  }

  @Override
  public AgentDefinition requireAgent(AgentType type, String ownerId, String language) {
    return findByType(type, ownerId, language)
        .orElseThrow(
            () ->
                DomainException.createNotFoundError(
                    "AGENT_NOT_FOUND", "Unknown agent type: " + type.getValue()));
  }

  private List<CustomAgent> listLibraryAgents(String ownerId) {
    if (ownerId == null || ownerId.isBlank()) {
      return List.of();
    }
    return customAgentRepository.findAllByOwnerIdAndEnabledTrueOrderByNameAsc(
        OwnerId.parseKey(ownerId));
  }

  private AgentDefinition toDefinition(AgentTemplate template) {
    String prompt =
        ClasspathPromptLoader.joinSections(
            template.systemPrompt(), promptTemplates.getSharedStyleInstructions());
    return AgentDefinition.createDefinition(
        AgentType.createType(template.agentType()),
        template.name(),
        template.description(),
        prompt,
        template.tools() == null ? List.of() : template.tools(),
        AgentDefinition.RUNTIME_SINGLE);
  }
}
