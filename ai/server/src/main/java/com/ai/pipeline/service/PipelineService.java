package com.ai.pipeline.service;

import com.ai.pipeline.domain.model.AgentDefinition;
import com.ai.pipeline.domain.model.AgentPipeline;
import com.ai.pipeline.domain.model.AgentType;
import com.ai.pipeline.domain.repository.AgentRegistry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/** Entry point for agent listing and supervisor, single-agent and pipeline invocations. */
@Service
@RequiredArgsConstructor
public class PipelineService {

  private final AgentRegistry registry;
  private final PipelineOrchestrationService orchestrator;

  /** Lists the agents. */
  public List<AgentDefinition> listAgents(String ownerId, String language) {
    return orchestrator.listAgents(ownerId, language);
  }

  /** Counts the built-in agents. */
  public int countBuiltins() {
    return registry.listBuiltins("en").size();
  }

  /** Returns an agent with its health. */
  public AgentDefinition getHealth(String agentType, String ownerId, String language) {
    return orchestrator.getHealth(AgentType.createType(agentType), ownerId, language);
  }

  /** Streams a supervisor run. */
  public Flux<ServerSentEvent<String>> invokeSupervisor(
      String message, String ownerId, String language) {
    return orchestrator.invokeSupervisor(message, ownerId, language);
  }

  /** Streams a pipeline run. */
  public Flux<ServerSentEvent<String>> invokePipeline(
      String message, AgentPipeline pipeline, String ownerId, String language) {
    return orchestrator.invokePipeline(message, pipeline, ownerId, language);
  }

  /** Streams a single agent run. */
  public Flux<ServerSentEvent<String>> invokeAgent(
      String agentType, String message, String ownerId, String language) {
    return orchestrator.invokeAgent(AgentType.createType(agentType), message, ownerId, language);
  }

  /** Runs a single agent and returns the final answer. */
  public String invokeAgentSync(String agentType, String message, String ownerId, String language) {
    return orchestrator.invokeAgentSync(
        AgentType.createType(agentType), message, ownerId, language);
  }

  /** Runs a pipeline and returns the final answer. */
  public String invokePipelineSync(
      String message, AgentPipeline pipeline, String ownerId, String language) {
    return orchestrator.invokePipelineSync(message, pipeline, ownerId, language);
  }
}
