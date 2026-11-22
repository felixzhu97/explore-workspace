package com.ai.skill.domain.repository;

import com.ai.common.domain.model.OwnerId;
import com.ai.common.domain.repository.OwnerScopedRepository;
import com.ai.skill.domain.model.Skill;
import com.ai.skill.domain.model.SkillId;
import java.util.Collection;
import java.util.List;

/** Owner-scoped repository for skills, including lookup of enabled skills by id. */
public interface SkillRepository extends OwnerScopedRepository<Skill, SkillId> {

  /** Lists the owner's skills that are on and match the ids. */
  List<Skill> findAllByOwnerIdAndEnabledTrueAndIdIn(OwnerId ownerId, Collection<SkillId> ids);

  /** Lists the owner's skills that are on and match the ids; no ids means no skills. */
  default List<Skill> findEnabledByOwnerIdAndIds(OwnerId ownerId, List<SkillId> ids) {
    return ids == null || ids.isEmpty()
        ? List.of()
        : findAllByOwnerIdAndEnabledTrueAndIdIn(ownerId, ids);
  }
}
