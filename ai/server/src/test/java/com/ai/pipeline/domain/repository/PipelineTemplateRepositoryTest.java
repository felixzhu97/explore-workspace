package com.ai.pipeline.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ai.common.domain.model.OwnerId;
import com.ai.pipeline.domain.model.PipelineTemplate;
import com.ai.testsupport.AbstractDataJpaTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PipelineTemplateRepositoryTest extends AbstractDataJpaTest {

  private static final OwnerId OWNER = OwnerId.parseKey("c:66666666-6666-6666-6666-666666666666");
  private static final OwnerId OTHER = OwnerId.parseKey("c:77777777-7777-7777-7777-777777777777");

  @Autowired private PipelineTemplateRepository repository;

  @Test
  @DisplayName("should reload the agent order and brief only for the owner")
  void shouldReloadTheAgentOrderAndBriefOnlyForTheOwner() {
    PipelineTemplate template =
        repository.save(
            PipelineTemplate.createTemplate(
                OWNER.getValue(),
                "Research flow",
                "Two-step workflow",
                List.of("researcher", "writer"),
                "AI trends",
                "Summarize recent AI news",
                null));
    flushAndClear();

    PipelineTemplate reloaded =
        repository.findByIdAndOwnerId(template.getId(), OWNER).orElseThrow();

    assertThat(reloaded.getName()).isEqualTo("Research flow");
    assertThat(reloaded.getAgentTypes()).containsExactly("researcher", "writer");
    assertThat(reloaded.getBrief()).isEqualTo("Summarize recent AI news");
    assertThat(repository.findByIdAndOwnerId(template.getId(), OTHER)).isEmpty();
  }
}
