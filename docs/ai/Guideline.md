# Guideline

`explore-ai` 提供对话、知识检索、工具调用、Agent 编排、质量评估与本地模型能力。仅在产出明确价值时启用生成式能力。

## Introduction

本指南规定 Chat、RAG、Tools、MCP、Agents、Eval、Metrics 与本地模型的设计与集成边界。变更 AI 行为时引用官方文档与一手研究。产品词汇见 [Glossary](Glossary.md)；架构边界见 [C4 model](c4-model/)。

## Best practices

**负责任地设计体验。**

负责的 AI 设计必须覆盖对人、系统与社会的直接与间接影响。原型易做，稳健体验难做。同一输入可产生不同输出。设计目标：包容、可审慎操作、隐私可保护。

**把控制权留给人。**

尊重用户能动性。范围内且预期清晰的请求必须响应；敏感内容必须谨慎处理；用户必须能关闭、重试或撤销不需要的结果。产品必须标明 AI 使用位置。Chat 与 Pipeline 优先暴露可见动作——发送、停止、重试、应用 Skill——禁止静默自动化。

**仅在价值明确时提供生成能力。**

生成式 AI 不是默认答案。Chat、RAG、工具与 Agent 必须交付省时、有据回答或创作杠杆。禁止因「新颖」或「预期」而堆砌 AI。

**生成不可用时仍保持可用。**

用户缺少 API Key、拒绝提供商或未运行本地模型时，无 AI 路径必须仍可用：文档浏览、空态与明确配置指引。禁止因生成失败而呈现产品损坏态。

**用评估器衡量质量，禁止用轶事。**

Golden Eval 使用 Spring AI 评估器与版本化用例。提示词与模型变更后质量必须可度量。参见 [Judging LLM-as-a-Judge](https://arxiv.org/abs/2306.05685) 与 [Spring AI evaluation testing](https://docs.spring.io/spring-ai/reference/api/testing.html)。

## Transparency

**标明产品何处使用 AI。**

用户必须知道 AI 参与处，方可知情选择。禁止将模型输出冒充人类撰写且不做披露。

**设定能力与边界预期。**

必须说明能力与限制。例如：RAG 回答依赖已上传文档；工具仅在已注册且获准时执行。

**有据回答必须展示来源。**

RAG 检索到上下文时，必须展示 Source Document 命中或等价引用。禁止无据「知识库」断言。

## Privacy

**每个 Chat Session 用 Owner Key 隔离。**

会话与消息仅存在于浏览器或账号边界内。跨归属访问返回 not found；界面回到新对话。术语见 [Glossary](Glossary.md)。

**只收集功能所需数据。**

提供商密钥、文档与可选账号关联必须目的绑定。产品支持隐私控制处必须提供撤销访问或擦除数据的路径。

**披露模型与提供商如何使用共享信息。**

用户提交敏感内容前，必须知悉提示词与文档去向——本地 Ollama 或远程提供商。

## Protocols and platform APIs

**以 Spring AI 作为 chat、tools、RAG、MCP 与评估器的集成面。**

使用已文档化 API；禁止自建提供商 HTTP 客户端。见 [Spring AI reference](https://docs.spring.io/spring-ai/reference/)。

**跨进程工具遵循 Model Context Protocol。**

服务端与客户端工具契约遵循 MCP。见 [MCP](https://modelcontextprotocol.io/) 与 [specification](https://spec.modelcontextprotocol.io/)。

**助手输出用 Server-Sent Events 流式传输。**

**通过已文档化 API 配置提供商。**

默认对话与评估使用 [DeepSeek](https://api-docs.deepseek.com/)。启用 OpenAI 兼容与 Anthropic 路径时遵循其官方文档。本地运行时使用 [Ollama](https://github.com/ollama/ollama)。

## Chat and sessions

**仅持久化非空会话。**

仅在存在实质用户或助手内容后将会话加入 Recents。空消息体不渲染为气泡。

**流式与记忆分离。**

流式是呈现层。会话记忆经 ChatMemory 与仓储边界持久化——界面不得拼装权威历史。

**便于精炼或重试生成回复。**

停止、重新生成与编辑流程必须靠近助手内容，用户保持对对话的控制。

## Retrieval-augmented generation

**检索与生成分离。**

先检索文档分块，再以有据上下文生成。经典框架：[Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401)。

**仅在分块与嵌入完成后将文档标为就绪。**

Document 状态机（uploading → processing → ready / failed）必须可见。失败可重试；禁止提前假装可问答。

**禁止混用嵌入空间。**

更换嵌入模型后必须重新导入文档。向量维度与模型身份必须匹配存储 schema。默认嵌入：[Qwen3 Embedding](https://qwenlm.github.io/blog/qwen3-embedding/)。

## Tools, MCP, and agents

**围绕明确的 reason–act 循环设计工具型 Agent。**

工具契约必须显式；步骤必须可观测。读物：[ReAct](https://arxiv.org/abs/2210.03629)、[Toolformer](https://arxiv.org/abs/2302.04761)。

**内置与 MCP 工具共享可调用约定。**

天气、搜索、日期时间与 MCP 注册工具共享命名、描述与错误形状，Chat 与 Pipeline 可复用。

**Pipeline 画布视为有向执行图。**

节点是可编辑的 Agent 副本（提示词与工具）；边表达依赖顺序。模版与运行结果分离。

**将 Skills 作为可复用指令包应用。**

Skills 在 Chat 中显式出现。编辑不得静默改写历史；作用域对用户可见。

**不可逆或高影响工具动作前必须确认。**

工具可发邮件、删除数据或触发自动化时，必须明确确认，禁止静默副作用。

## Evaluation and metrics

**优先使用版本化输入与期望输出。**

以结构化用例扩展套件（例如 JSONL：`id`、`input`、`ideal`、`metadata`）。禁止仅依赖截图。用例格式：[OpenAI Evals](https://github.com/openai/evals)。

**在 Metrics 中记录可聚合的调用事实。**

观测领域、结果、延迟与 token。看板与下钻使用 Glossary 指标用语。

**以评估与指标驱动持续改进。**

Eval 回归与 Metrics 趋势指导提示词、模型与工具变更——作为持续改进信号，禁止当作一次性演示。

## Speech and imagery

**各模态对齐成熟方法。**

ASR 谱系：[Whisper](https://arxiv.org/abs/2212.04356)。图像生成谱系：[Latent Diffusion Models](https://arxiv.org/abs/2112.10752)。禁止混用不兼容的模型契约。

**TTS、ASR、图像生成与图像分析使用独立路由。**

Generation 壳组织图像与 TTS。Vision 与 ASR 保留独立入口——禁止单一全能多模态页。

**将处理时间纳入体验。**

生成工作可长于普通 UI 操作。优先具体状态（例如 summarizing、embedding），禁止模糊的「Processing…」；流式支持处必须允许取消。

## Models and local runtimes

**以 Transformer 解码作为对话与本地模型基础。**

共享词汇（tokens、sampling、context length）：[Attention Is All You Need](https://arxiv.org/abs/1706.03762) 与 Glossary Appendix D。

**优先已文档化的开放模型与稳定打包。**

对话默认遵循 DeepSeek（[DeepSeek-V3](https://arxiv.org/abs/2412.19437)、[deepseek-ai](https://github.com/deepseek-ai)）。嵌入与多模态族遵循 [Qwen](https://qwen.ai/)。在 [Hugging Face](https://huggingface.co/) 发现模型；在 [arXiv cs.AI](https://arxiv.org/list/cs.AI/recent) 跟踪新工作。

**本地 LLM 权重用 GGUF；本地视觉用 ONNX。**

见 [GGUF](https://huggingface.co/docs/hub/en/gguf) 与 [ONNX Runtime](https://onnxruntime.ai/)。

**冷启动与上下文长度必须可见。**

本地模型首次加载可慢。请求不得超过运行时声明的上下文长度。

## Product language

**产品 UI 使用 Preferred Terms：Chat、RAG、Tool Calling、MCP、Eval、Metrics。**

存在 Preferred Term 时，禁止在 UI 使用厂商品牌名（Glossary *Terms to Avoid*）。

**框架与提供商名称留在开发者文档。**

Spring AI、Ollama 与提供商标签不得出现在终端用户 chrome，除非用户正在选择提供商。

## Related

[Apple HIG — Generative AI](https://developer.apple.com/design/human-interface-guidelines/generative-ai)

[Attention Is All You Need](https://arxiv.org/abs/1706.03762)

[Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401)

[ReAct](https://arxiv.org/abs/2210.03629)

[Toolformer](https://arxiv.org/abs/2302.04761)

[Judging LLM-as-a-Judge](https://arxiv.org/abs/2306.05685)

[Whisper](https://arxiv.org/abs/2212.04356)

[Latent Diffusion Models](https://arxiv.org/abs/2112.10752)

[DeepSeek-V3](https://arxiv.org/abs/2412.19437)

[Model Context Protocol](https://modelcontextprotocol.io/)

[MCP specification](https://spec.modelcontextprotocol.io/)

[Hugging Face](https://huggingface.co/)

[arXiv cs.AI](https://arxiv.org/list/cs.AI/recent)

[Qwen3 Embedding](https://qwenlm.github.io/blog/qwen3-embedding/)

[OpenAI Evals](https://github.com/openai/evals)

## Developer documentation

[Spring AI reference](https://docs.spring.io/spring-ai/reference/)

[Spring AI ChatClient](https://docs.spring.io/spring-ai/reference/api/chatclient.html)

[Spring AI Advisors](https://docs.spring.io/spring-ai/reference/api/advisors.html)

[Spring AI Retrieval Augmented Generation](https://docs.spring.io/spring-ai/reference/api/retrieval-augmented-generation.html)

[Spring AI Tool Calling](https://docs.spring.io/spring-ai/reference/api/tools.html)

[Spring AI Chat Memory](https://docs.spring.io/spring-ai/reference/api/chat-memory.html)

[Spring AI MCP](https://docs.spring.io/spring-ai/reference/guides/getting-started-mcp.html)

[Spring AI MCP Client](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-client-boot-starter-docs.html)

[Spring AI evaluation testing](https://docs.spring.io/spring-ai/reference/api/testing.html)

[DeepSeek API](https://api-docs.deepseek.com/)

[Ollama](https://github.com/ollama/ollama)

[OpenAI platform docs](https://platform.openai.com/docs)

[Anthropic docs](https://docs.anthropic.com/)

[Glossary](Glossary.md)

