package com.ai.automation.infra.pipeline;

import com.ai.automation.domain.repository.PipelineGateway;
import com.ai.common.domain.model.OwnerId;
import com.ai.pipeline.domain.model.PipelineTemplate;
import com.ai.pipeline.domain.model.PipelineTemplateId;
import com.ai.pipeline.domain.repository.PipelineTemplateRepository;
import com.ai.pipeline.service.PipelineService;
import com.explore.errors.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Runs an owner's saved pipeline template in process as a linear agent pipeline and returns its
 * output.
 */
@Component
@RequiredArgsConstructor
public class InProcessPipelineGateway implements PipelineGateway {

  private final PipelineTemplateRepository pipelineTemplateRepository;
  private final PipelineService pipelineService;

  @Override
  public String runSavedTemplate(
      String ownerId, String pipelineTemplateId, String brief, String language) {
    PipelineTemplate template =
        pipelineTemplateRepository
            .findByIdAndOwnerId(
                PipelineTemplateId.parseId(pipelineTemplateId), OwnerId.parseKey(ownerId))
            .filter(PipelineTemplate::isRunnable)
            .orElseThrow(
                () ->
                    DomainException.createNotFoundError(
                        "PIPELINE_TEMPLATE_NOT_FOUND",
                        "Pipeline template not found: " + pipelineTemplateId));
    return pipelineService.invokePipelineSync(
        template.composeFirstMessage(brief), template.buildLinearPipeline(), ownerId, language);
  }
}
