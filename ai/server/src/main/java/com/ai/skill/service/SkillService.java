package com.ai.skill.service;

import com.ai.account.domain.event.GuestLinkedToAccountEvent;
import com.ai.account.domain.event.OwnerErasureRequestedEvent;
import com.ai.common.domain.model.DomainStrings;
import com.ai.common.domain.model.OwnerId;
import com.ai.skill.domain.model.Skill;
import com.ai.skill.domain.model.SkillId;
import com.ai.skill.domain.repository.SkillRepository;
import com.explore.errors.DomainException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Skill use case that enforces per-owner unique names and derives names from templates. */
@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {

  private final SkillRepository skillRepository;

  /** Lists the owner's skills. */
  public List<Skill> listSkills(String ownerId) {
    return skillRepository.findAllByOwnerIdOrderByNameAsc(OwnerId.parseKey(ownerId));
  }

  /**
   * Builds the "Active Skills" system prompt from the owner's enabled skills among {@code
   * skillIds}; unknown, invalid or disabled ids are ignored.
   */
  public Optional<String> buildSkillsPrompt(String ownerId, List<String> skillIds) {
    List<SkillId> ids = parseSkillIds(skillIds);
    if (ids.isEmpty()) {
      return Optional.empty();
    }
    List<Skill> skills =
        skillRepository.findEnabledByOwnerIdAndIds(OwnerId.parseKey(ownerId), ids);
    return Optional.ofNullable(SkillSystemPromptBuilder.buildPrompt(skills));
  }

  /** Lists the built-in skill templates. */
  public List<SkillTemplate> listTemplates(String language) {
    return SkillTemplateCatalog.listAll(language);
  }

  /** Returns the owner's skill. */
  public Skill getSkill(String ownerId, String id) {
    return findOwnedSkill(ownerId, id);
  }

  /** Creates a skill, rejecting a taken name. */
  public Skill createSkill(
      String ownerId,
      String name,
      String description,
      String instructions,
      List<String> allowedTools) {
    assertNameAvailable(ownerId, name, null);
    Skill skill = Skill.createSkill(ownerId, name, description, instructions, allowedTools);
    return skillRepository.save(skill);
  }

  /** Copies a built-in skill template into the owner's skills. */
  public Skill createFromTemplate(String ownerId, String templateId, String language) {
    SkillTemplate template =
        SkillTemplateCatalog.findById(templateId, language)
            .orElseThrow(
                () -> new IllegalArgumentException("Unknown skill template: " + templateId));
    return createSkill(
        ownerId,
        findNextAvailableName(ownerId, template.name()),
        template.description(),
        template.instructions(),
        template.allowedTools());
  }

  /** Replaces the owner's skill, keeping names unique per owner. */
  public Skill updateSkill(
      String ownerId,
      String id,
      String name,
      String description,
      String instructions,
      List<String> allowedTools) {
    Skill skill = findOwnedSkill(ownerId, id);
    assertNameAvailable(ownerId, name, skill.getId());
    skill.update(name, description, instructions, allowedTools);
    return skillRepository.save(skill);
  }

  /** Enables or disables the owner's skill. */
  public Skill setEnabled(String ownerId, String id, boolean enabled) {
    Skill skill = findOwnedSkill(ownerId, id);
    skill.updateEnabledState(enabled);
    return skillRepository.save(skill);
  }

  /** Deletes the owner's skill. */
  public void deleteSkill(String ownerId, String id) {
    findOwnedSkill(ownerId, id);
    skillRepository.deleteByIdAndOwnerId(SkillId.parseId(id), OwnerId.parseKey(ownerId));
  }

  private Skill findOwnedSkill(String ownerId, String id) {
    return skillRepository
        .findByIdAndOwnerId(SkillId.parseId(id), OwnerId.parseKey(ownerId))
        .orElseThrow(
            () -> DomainException.createNotFoundError("SKILL_NOT_FOUND", "Skill not found: " + id));
  }

  private void assertNameAvailable(String ownerId, String name, SkillId excludeId) {
    String normalized = DomainStrings.normalizeName(name);
    if (skillRepository.existsByOwnerIdAndNameIgnoringId(
        OwnerId.parseKey(ownerId), normalized, excludeId)) {
      throw DomainException.createConflictError(
          "SKILL_NAME_CONFLICT", "Skill name already exists: " + normalized);
    }
  }

  private String findNextAvailableName(String ownerId, String baseName) {
    OwnerId owner = OwnerId.parseKey(ownerId);
    return DomainStrings.copyNameCandidates(baseName, DomainStrings.DEFAULT_NAME_MAX)
        .filter(name -> !skillRepository.existsByOwnerIdAndNameIgnoringId(owner, name, null))
        .findFirst()
        .orElseGet(
            () ->
                DomainStrings.copyName(
                    baseName,
                    SkillId.generateId().toString().substring(0, 8),
                    DomainStrings.DEFAULT_NAME_MAX));
  }

  private static List<SkillId> parseSkillIds(List<String> skillIds) {
    List<SkillId> parsed = new ArrayList<>();
    if (skillIds == null) {
      return parsed;
    }
    for (String skillId : skillIds) {
      if (skillId == null || skillId.isBlank()) {
        continue;
      }
      try {
        parsed.add(SkillId.parseId(skillId.trim()));
      } catch (IllegalArgumentException expected) {
      }
    }
    return parsed;
  }

  @EventListener
  void handleOwnerErasureRequestedEvent(OwnerErasureRequestedEvent event) {
    skillRepository.deleteAllByOwnerId(event.ownerId());
  }

  @EventListener
  void handleGuestLinkedToAccountEvent(GuestLinkedToAccountEvent event) {
    skillRepository.reassignOwnerId(event.fromOwnerId(), event.toOwnerId());
  }
}
