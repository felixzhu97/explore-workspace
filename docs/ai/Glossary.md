# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 对话 | Chat | 参与者之间的会话式交流。 | 私聊「你好」；群聊「项目组」 |
| 工作流画布 | Pipeline | 由节点与边组成的处理流程。 | 「检索→生成→审校」DAG |
| Agent 门面 | Agent | 能感知环境并采取行动以达成目标的软件实体。 | Research Agent 调搜索工具 |
| Agent2Agent | A2A | 智能体之间发现与协作的协议。 | Agent Card；message/send |
| 定时自动化 | Automation | 按计划自动执行的任务。 | 每天 09:00 Cron 触发 |
| 知识问答 | RAG | 先检索外部知识再据此生成回答的范式。 | 先检索手册再答「年假几天」 |
| 结构化分析 | Text Analysis | 对文本进行结构化分析。 | 抽取合同甲方/金额/日期 |
| 对话质量评估 | Eval | 对模型或系统输出质量的评估。 | Golden 集 + LLM-as-a-Judge |
| 语音 | Audio | 声音信号或其处理能力。 | 语音转写；TTS 播报 |
| MCP | MCP | 模型与外部工具/上下文互操作的开放协议。 | tools/list；tools/call |
| Workflow Lab 原语 | Workflow | 按步骤编排的工作过程。 | 「上传→分块→索引」步骤流 |
| 生成 | Generation | 由模型生成内容的能力类别。 | 文生图；补全下一句 |
| 账号 | Account | 代表个人或主体的账号。 | alice@example.com 账号 |
| 用户同步 | Provisioning | 供给与生命周期管理身份资源。 | IdP 经 SCIM 开停用户 |
| 横切 | Common | 跨功能共享的横切能力。 | 跨域共享内核（异常、基类） |

## Pipeline | 工作流（画布 Agent）

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 技能 | Skill | 可注入对话的指令与能力包。 | /skills 指令包 summarize |
| Agent 类型 | Agent Type | 智能体的类型分类。 | research Agent 配置 |
| Agent 定义 | Agent Definition | 智能体的定义描述。 | research Agent 配置 |
| Agent 注册表 | Agent Registry | 已注册智能体的登记处。 | research Agent 配置 |
| Agent 模版 | Agent Template | 可实例化的智能体模板。 | research Agent 配置 |
| 工作流流水线 | Agent Pipeline | 由多个智能体步骤组成的流水线。 | 由多个智能体步骤组成的流水线 |
| 流水线节点 | Pipeline Node | 流水线中的一个处理节点。 | DAG 上一步骤 |
| 流水线边 | Pipeline Edge | 流水线节点之间的连接。 | 节点 A → 节点 B |
| 工作流模版 | Pipeline Template | 可复用的流水线模板。 | 预置画布模板 |
| 自定义 Agent | Custom Agent | 用户自定义的智能体。 | research Agent 配置 |
| Pipeline 服务 | Pipeline Service | 提供流水线能力的应用服务。 | 编排执行画布 DAG |
| 自动化日程 | Automation Schedule | 自动化任务的调度安排。 | 工作日 09:00 触发 |
| 自动化运行记录 | Automation Run | 自动化任务的一次执行。 | run_01 执行记录 |
| 日程时间规则 | Schedule Timing | 调度的时间安排。 | TZ=Asia/Shanghai + Cron |
| Cron 计算 | Cron Schedule | 基于 cron 的调度表达式。 | 0 9 * * MON-FRI |
| 邮件网关 | Email Gateway | 发送电子邮件的出站网关。 | Resend 发通知邮件 |
| 编排服务 | Pipeline Orchestration | 调度流水线节点执行的过程。 | 编排执行画布 DAG |
| Supervisor 路由 | Supervisor Router | 监督并路由子任务的组件。 | 监督者分派子任务 |
| Worker 调用器 | Worker Agent Invoker | 调用工作者智能体的组件。 | 执行子任务的 Worker |
| Agent 门面服务 | Agent Service | 提供智能体能力的应用服务。 | Agent 门面/提示词 |
| 默认 Agent 类型 | Default Agent Type | 默认选用的智能体类型。 | research Agent 配置 |
| 工具标记过滤器 | Tool Call Markup Filter | 过滤工具调用标记的过滤器。 | 过滤输出中的工具标记 |
| 工具循环守卫 | Tool Call Loop Guard | 限制工具调用循环次数的保护。 | 阻断工具死循环 |
| 路由计划 | Routing Plan | 任务路由的计划。 | 意图 → 子 Agent 计划 |
| Agent 交接 | Agent Handoff | 将任务移交给另一智能体。 | 销售 → 支持 Agent |
| Agent 提示词目录 | Agent Prompt Catalog | 智能体提示模板目录。 | 提示词模板目录 |
| 内置技能 | Bundled Skill | 预置打包的技能。 | 内置 summarize 技能 |
| 技能仓储 | Skill Registry | 技能的登记与查找。 | 名称 → Skill 实现 |
| Agent 技能运行时 | Agent Skills Runtime | 执行智能体技能的运行时。 | 加载并执行技能 |
| 提示词目录 | Prompt Catalog | 提示模板目录。 | 提示词模板目录 |
| 提示词组合服务 | Prompt Templates | 可复用的提示模板集合。 | 提示词模板目录 |
| 本地化 RAG 提示词 | Localized RAG Prompt | 按语言本地化的 RAG 提示。 | 中/英 RAG 提示词 |

## Tool Calling | 工具调用

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 工具调用 | Tool Calling | 模型按约定模式调用外部工具。 | 模型发 function call |
| 工具对话 | Tool Chat | 具备工具调用能力的对话。 | 带 tools 的多轮对话 |
| 工具结果 | Tool Result | 工具执行返回的结果。 | function 返回 JSON |
| 工具回调注册表 | Tool Callback Registry | 工具名到回调实现的注册表。 | 名称 → ToolCallback |
| 文档搜索工具 | Document Search Tool | 用于文档检索的工具。 | 检索「年假」相关块 |
| RAG 搜索工具 | RAG Search Tool | 在 RAG 知识库中检索的工具。 | 检索「年假」相关块 |
| 文档检索 | Document Search | 在文档集合中检索相关内容。 | 检索「年假」相关块 |
| 天气工具 | Weather Tool | 查询天气信息的工具。 | 「北京今天天气」 |
| 时间工具 | DateTime Tool | 提供权威日期时间的工具。 | 「现在几点」 |
| 网页搜索工具 | Web Search Tool | 用于检索公开网页的工具。 | 「最新汇率」 |

## Image Generation | 图像生成

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 图像生成 | Image Generation | 由模型根据提示生成图像。 | 「赛博朋克城市」→ PNG |
| 生成请求 | Image Generation Request | 请求生成图像的输入。 | 文生图请求/响应 |
| 生成响应 | Image Generation Response | 图像生成的输出结果。 | 文生图请求/响应 |
| 提示词 | Prompt | 引导模型生成的输入文本。 | system：「你是助手」 |
| 生成图像 | Generated Image | 模型生成的图像。 | 文生图请求/响应 |

## Image Analysis | 图像分析

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 图像分析 | Image Analysis | 对图像内容的分析。 | 图 → 描述/检测/OCR |
| 图像描述 | Caption | 对图像或视频内容的文字描述。 | 「橘猫趴在窗台」 |
| 目标检测 | Object Detection | 在图像中定位并分类目标。 | 框选「猫」0.92 |
| 文字识别 | OCR | 从图像中识别文字。 | 发票图 →「金额 128.00」 |
| 描述引擎 | Caption Engine | 生成图像描述的引擎。 | 视觉引擎实现 |
| 检测引擎 | Detect Engine | 执行目标检测的引擎。 | 视觉引擎实现 |
| 识别引擎 | OCR Engine | 执行文字识别的引擎。 | 视觉引擎实现 |

## Metrics | AI 指标看板

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| AI 指标看板 | Metrics | 可观测的定量指标。 | /metrics 看板 p95 |
| AI 调用事件 | AI Invocation Event | 一次 AI 调用的事实记录。 | 一次 chat/rag 调用 |
| AI 能力 | AI Capability | 可度量的一类 AI 能力领域。 | chat；rag；tools |
| 调用结果 | Invocation Outcome | 调用成功或失败的结果。 | success / failure |
| 指标概览 | Metrics Overview | 指标总览。 | 看板 KPI / 时序点 |
| 能力指标快照 | Metrics Capability Snapshot | 某能力在窗口内的指标快照。 | 看板 KPI / 时序点 |
| 指标时序 | Metrics Series | 指标随时间变化的序列。 | 看板 KPI / 时序点 |
| 指标下钻 | Metrics Drill-down | 指标的下钻明细。 | 看板 KPI / 时序点 |
| AI 调用记录器 | AI Invocation Recorder | 记录 AI 调用事实的组件。 | 一次 chat/rag 调用 |
| AI 调用完成事件 | AI Invocation Completed Event | AI 调用成功完成的事实。 | 一次调用成功落库 |
| AI 调用失败事件 | AI Invocation Failed Event | AI 调用失败的事实。 | 一次调用失败落库 |
| 耗时 | Latency | 从请求到响应的时间延迟。 | p95=120ms |
| 错误摘要 | Error Summary | 错误的汇总说明。 | timeout: upstream 5xx |
| 调用统计 | Invocation Stats | 调用次数与结果的统计。 | 看板 KPI / 时序点 |
| 指标时间窗 | Metrics Window | 指标统计的时间窗口。 | 看板 KPI / 时序点 |
| 耗时统计 | Latency Stats | 延迟分布的统计。 | 看板 KPI / 时序点 |

## Domain Kernel | 领域内核

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 实体标识 | Entity ID | 实体的唯一标识。 | UUID 主键 |
| 值对象基类 | Abstract Embeddable | 可嵌入值对象的基类型。 | @Embeddable Money |
| 不可变实体基类 | Abstract Immutable | 创建后不可变实体的基类型。 | 创建后不可变基类 |
| 可变实体基类 | Abstract Entity | 可变实体的持久化基类型。 | 带 @Version 的可变基类 |
| 归属实体基类 | Owner-Aware Entity | 带数据归属键的实体。 | c:{clientId}；u:{accountId} |
| 可启用实体 | Enableable Entity | 可启用或停用的实体。 | enabled=true/false |
| 归属仓储契约 | Owner-Scoped Repository | 按归属键限定访问的仓储。 | c:{clientId}；u:{accountId} |
| 领域事件 | Domain Event | 领域中已发生事实的记录。 | OrderPaid；UserProvisioned |
| 事件处理器 | Event Handler | 响应领域或应用事件的处理器。 | 消费 OrderPaid 发邮件 |
| 域字符串校验 | Domain Strings | 领域字符串的规范化与校验。 | 非空、邮箱格式校验 |

## Chat | 对话

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 会话 | Chat Session | 用户与系统之间的多轮对话容器。 | 多轮 New Chat 线程 |
| 会话标题 | Session Title | 会话的标题名称。 | 「关于年假政策」 |
| 会话活跃 | Session Activity | 会话最近活跃的时间点。 | lastActiveAt=刚刚 |
| 客户端身份 | Anonymous id | 未登录访问者的匿名标识。 | 访客 Cookie id |
| 数据归属键 | Owner Key | 划分数据归属的分区键。 | c:{clientId}；u:{accountId} |
| 无主数据 | Unowned | 尚无归属主体的数据分区。 | 尚无归属主体的数据分区 |
| 隐私清除 | Privacy Erasure | 按请求清除个人数据。 | 按请求清除个人数据 |
| 归属数据清除事件 | Owner Erasure Requested Event | 请求清除某归属数据的事实。 | 按 GDPR 清归属数据 |
| 访客并入账号事件 | Guest Linked To Account Event | 访客数据并入账号的事实。 | 访客会话并入正式账号 |
| 会话清除事件 | Chat Sessions Erased Event | 对话会话已被清除的事实。 | 清空该用户全部会话 |
| 隐私同意 | Privacy Consent | 用户对数据处理的同意表示。 | 用户对数据处理的同意表示 |
| 数据留存 | Data Retention | 按规定期限保留或清理数据。 | 按规定期限保留或清理数据 |
| 套餐配额 | Plan Quota | 套餐下的用量限额。 | 套餐下的用量限额 |
| 套餐 | Plan | 计费或能力套餐档位。 | 计费或能力套餐档位 |
| 配额策略 | Quota Policy | 用量限额的规则。 | 用量限额的规则 |
| 当日用量 | Daily Usage | 自然日内的累计用量。 | 自然日内的累计用量 |
| 配额对象 | Quota Subject | 用量计数所归属的对象。 | 用量计数所归属的对象 |
| 配额判定 | Quota Decision | 一次用量检查的判定结果。 | 一次用量检查的判定结果 |
| Metrics 管理密钥 | Metrics Admin Key | 访问指标管理接口的密钥。 | 访问指标管理接口的密钥 |
| 当前账号 | Account Me | 当前查看者的账号视图。 | 当前查看者的账号视图 |
| 外部身份 | External Identity | 外部身份提供者中的主体标识。 | 外部身份提供者中的主体标识 |
| 联系邮箱 | Contact Email | 用于联系的电子邮箱地址。 | 用于联系的电子邮箱地址 |
| 客户端 ID | Client ID | OAuth 或客户端的标识符。 | client_id=spa-web |
| OAuth 登录 | OAuth Login | 基于 OAuth 2.0 / OIDC 的登录流程。 | 用 Google 账号授权码登录 |
| IAM 访问令牌 | IAM Bearer | 以 Bearer 方式携带的 IAM 访问令牌。 | 以 Bearer 方式携带的 IAM 访问令牌 |
| IAM 模块范围 | IAM Module Scope | 访问令牌上约束模块能力的范围声明。 | 访问令牌上约束模块能力的范围声明 |
| 账号用户名 | Account User Name | 账号的登录名。 | 登录名 alice |
| 账号状态 | Account Status | 账号是否可用的状态。 | 账号是否可用的状态 |
| 预置账号 | Provisioned Account | 经供给预先创建的账号。 | 经供给预先创建的账号 |
| SCIM 用户同步 | SCIM Provisioning | 通过 SCIM 创建、更新与停用身份资源。 | 通过 SCIM 创建、更新与停用身份资源 |
| SCIM 范围 | SCIM Scope | 调用 SCIM 接口所需的范围。 | 调用 SCIM 接口所需的范围 |
| 法律文档 | Legal Documents | 条款、隐私等法律文本。 | 上传劳动合同 PDF |
| 消息 | Chat Message | 对话中的一条消息。 | 用户：「年假几天？」 |
| 用户消息 | User Message | 由用户发出的消息。 | 角色=user 的一条消息 |
| 助手消息 | Assistant Message | 由助手模型发出的消息。 | 由助手模型发出的消息 |
| 会话标识 | Chat Session ID | 对话会话的唯一标识。 | sess_01HXYZ… |
| 消息标识 | Message ID | 消息的唯一标识。 | msg_01H… |
| 结构图 | Structure Diagram | 用图形表示结构关系的图。 | 用图形表示结构关系的图 |
| 检测语言 | Detected Language | 检测到的文本语言。 | 检测到的文本语言 |
| 活跃 | ACTIVE | 处于可用或进行中状态。 | 处于可用或进行中状态 |
| 已关闭 | CLOSED | 已关闭、不再接受新活动。 | 已关闭、不再接受新活动 |

## Workflow | Effective Agents 工作流

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 链式工作流 | Chain Workflow | 顺序串联步骤的工作流。 | 顺序串联步骤的工作流 |
| 并行化工作流 | Parallelization Workflow | 并行执行子任务的工作流。 | 并行执行子任务的工作流 |
| 路由工作流 | Routing Workflow | 按条件路由到不同路径的工作流。 | 按条件路由到不同路径的工作流 |
| 编排-工人工作流 | Orchestrator-Workers Workflow | 编排者—工作者模式的工作流。 | 编排者—工作者模式的工作流 |
| 评估-优化工作流 | Evaluator-Optimizer Workflow | 评估与优化循环的工作流模式。 | 评估与优化循环的工作流模式 |

## RAG | 知识问答

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 文档 | Rag Document | 纳入 RAG 知识库的文档。 | 知识库中的一篇 PDF |
| 文档标识 | Document ID | Unique identifier of a document。 | doc_01H… |
| 文档状态 | Document Status | 文档处理所处的状态。 | pending / indexed |
| 文档分块 | Document Chunk | 文档切分后的可检索片段。 | 512 token 文档块 |
| 文档导入 | Document Ingestion | 将文档纳入知识库的过程。 | PDF → 分块 → 向量化 |
| 评分分块 | Scored Chunk | 带相关性分数的检索片段。 | 512 token 文档块 |
| 提取文档 | Extracted Document | 从文件中抽取得到的文本内容。 | OCR/解析后的正文 |
| 分块 | Chunking | 将长文档切分为可检索片段。 | 512 token 文档块 |
| 嵌入向量 | Embedding | 将文本或数据映射到向量空间的表示。 | 1536 维浮点向量 |
| 检索 | Retrieval | 从知识库中找出与查询相关的内容。 | 向量库 top-5 相关块 |
| Spring AI 向量库 | Spring AI Vector Store | Spring AI 中的向量存储抽象。 | pgvector SimilaritySearch |
| 来源引用 | Source Citation | 生成内容所依据的来源引用。 | 生成内容所依据的来源引用 |
| 上下文 | Context | 解释或约束当前操作的上下文信息。 | 解释或约束当前操作的上下文信息 |
| RAG 对话 | RAG Chat | 结合检索增强的对话。 | 结合检索增强的对话 |
| 视觉问答 | Vision Chat | 结合视觉输入的对话。 | 结合视觉输入的对话 |
| 文档上传 | Document Upload | 上传文档文件。 | multipart 上传 PDF |
| 向量相似度 | Vector Similarity | 向量空间中相近程度的度量。 | 向量空间中相近程度的度量 |
| 分块大小 | Chunk Size | 单个片段的目标大小。 | 512 token 文档块 |
| 检索数量 | Top K | 仅保留得分最高的前 k 条。 | 仅保留得分最高的前 k 条 |
| 分数阈值 | Score Threshold | 接受结果的最低分数门槛。 | 接受结果的最低分数门槛 |
| 文档读取器 | Document Reader | 读取文档内容的组件。 | PDFBox 读页文本 |
| 文档转换器 | Document Transformer | 转换文档内容形态的组件。 | 清洗/分块转换 |
| 文档写入器 | Document Writer | 写出文档或片段的组件。 | 写入向量库 |
| RAG 数据管道 | RAG ETL Pipeline | 文档抽取、转换与装载的流水线。 | 文档抽取、转换与装载的流水线 |
| 处理中 | PROCESSING | 正在处理中。 | 正在处理中 |
| 就绪 | READY | 已就绪可执行或可使用。 | 已就绪可执行或可使用 |
| 失败 | FAILED | 执行失败。 | 执行失败 |

## Audio | 语音

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 语音合成 | Text-to-Speech (TTS) | 将文本转换为语音。 | 将文本转换为语音 |
| 音色 | Voice | 可辨识的说话声音特征。 | 可辨识的说话声音特征 |
| 语音文本 | Speech Text | 语音对应的文本。 | 语音对应的文本 |
| 合成音频 | Synthesized Audio | 由合成得到的音频。 | 由合成得到的音频 |
| 合成 | Synthesize | 将文本合成为语音。 | 将文本合成为语音 |
| 自动语音识别 | Automatic Speech Recognition (ASR) | 将语音转换为文本。 | 将语音转换为文本 |
| 流式转写 | Streaming Transcription | 流式输出的语音转写。 | 流式输出的语音转写 |
| 转写 | Transcription | 语音内容的文本转写结果。 | 语音内容的文本转写结果 |
| 语音对话 | Voice Conversation | 以语音进行的对话。 | 以语音进行的对话 |

## MCP | MCP

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| MCP 服务端 | MCP Server | 提供 MCP 能力的服务端。 | 提供 MCP 能力的服务端 |
| MCP 客户端 | MCP Client | 连接 MCP 服务端的客户端。 | 连接 MCP 服务端的客户端 |
| MCP 工具 | MCP Tool | 通过 MCP 暴露的工具。 | 通过 MCP 暴露的工具 |
| MCP 工具定义 | MCP Tool Definition | MCP 工具的名称与参数定义。 | MCP 工具的名称与参数定义 |
| MCP 会话 | MCP Session | MCP 交互的会话。 | 一次 MCP 工具会话 |
| MCP 服务端连接 | MCP Server Connection | 到 MCP 服务端的连接。 | 到 MCP 服务端的连接 |
| MCP 对话 | MCP Chat | 通过 MCP 使用工具的对话。 | 通过 MCP 使用工具的对话 |

## A2A | Agent2Agent

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| A2A 服务端 | A2A Server | 实现 A2A 协议的服务端。 | 实现 A2A 协议的服务端 |
| Agent 名片 | Agent Card | 描述智能体能力与端点的名片。 | 描述智能体能力与端点的名片 |
| A2A 技能 | A2A Skill | A2A 智能体声明的技能。 | A2A 智能体声明的技能 |
| A2A 任务 | A2A Task | A2A 协议中的任务单元。 | A2A 协议中的任务单元 |
| A2A 消息 | A2A Message | A2A 协议中的消息。 | A2A 协议中的消息 |
| 发送消息 | Send Message | 向会话发送一条消息。 | 向会话发送一条消息 |
| 流式发送消息 | Stream Message | 以流式方式输出消息内容。 | SSE 流式吐字 |
| Agent 目录 | Agent Gallery | 可浏览选用的智能体陈列。 | 可浏览选用的智能体陈列 |
| Agent 工作室 | Agent Studio | 创建与编辑智能体的工作室。 | 创建与编辑智能体的工作室 |
| A2A 客户端 | A2A Client | 调用 A2A 服务的客户端。 | 调用 A2A 服务的客户端 |

## Text Analysis | 结构化分析

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 结构化输出 | Structured Output | 符合约定结构的模型输出。 | 符合约定结构的模型输出 |
| 分析结果 | Text Analysis Result | 文本分析得到的结果。 | 文本分析得到的结果 |
| 情感 | Sentiment | 文本所表达的情感倾向。 | 文本所表达的情感倾向 |
| 关键点 | Key Points | 内容中的要点摘录。 | 内容中的要点摘录 |
| 命名实体 | Named Entities | 文本中识别出的命名实体。 | 文本中识别出的命名实体 |

## Eval | 对话质量评估

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 对话评估 | Chat Evaluation | 对对话质量的评估。 | 对对话质量的评估 |
| 评估结果 | Chat Evaluation Result | 对话评估的结果。 | 对话评估的结果 |
| 相关性评估器 | Relevancy Evaluator | 评估回答相关性的组件。 | 评估回答相关性的组件 |
| 事实性评估器 | Fact-Checking Evaluator | 评估事实准确性的组件。 | 评估事实准确性的组件 |
| 连贯性分数 | Coherence Score | 文本连贯程度的分数。 | 文本连贯程度的分数 |
| 相关性分数 | Relevance Score | 结果与查询相关程度的分数。 | 结果与查询相关程度的分数 |
| 有用性分数 | Helpfulness Score | 回答对用户有帮助程度的分数。 | 回答对用户有帮助程度的分数 |
| 事实性分数 | Factuality Score | 陈述符合事实程度的分数。 | 陈述符合事实程度的分数 |
| 综合分数 | Overall Score | 综合各项得到的总分。 | 综合各项得到的总分 |
| 安全标记 | Safety Flag | 标识潜在安全风险的标记。 | 标识潜在安全风险的标记 |
| 评估对话客户端 | Evaluation ChatClient | 用于评估的对话客户端。 | Spring AI ChatClient |
| 黄金评估套件 | Golden Suite | 标准评估用例的集合。 | 标准评估用例的集合 |
| 黄金评估用例 | Golden Eval Case | 用于回归评估的标准用例。 | 用于回归评估的标准用例 |
| 官方门禁结果 | Official Gate Result | 门槛门禁检查结果。 | 门槛门禁检查结果 |

## Appendix C. Business Intelligence Terms | 商业智能通用术语

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 令牌用量 | Token Usage | 一次调用消耗的记号数量。 | prompt=120 completion=80 |
| 商业智能 | Business Intelligence (BI) | 将数据转化为经营洞察的方法与工具。 | 销售漏斗看板 |
| 仪表盘 | Dashboard | 汇集指标与图表的概览视图。 | 汇集指标与图表的概览视图 |
| 关键绩效指标 | Key Performance Indicator (KPI) | 衡量目标达成程度的关键绩效指标。 | 月活；转化率 |
| 指标 | Metric | Named quantitative measure of a process (count, rate, latency, tokens)。 | 错误率；QPS |
| 度量 | Measure | 分析中可聚合的度量值。 | 分析中可聚合的度量值 |
| 维度 | Dimension | 分析中用于切片的分类属性。 | 分析中用于切片的分类属性 |
| 事实事件 | Fact Event | 已发生业务事实的事件记录。 | 不可变领域事实事件 |
| 粒度 | Grain | 数据分析的粒度。 | 数据分析的粒度 |
| 聚合 | Aggregation | 将多条观测合并为汇总结果。 | 将多条观测合并为汇总结果 |
| 时间范围 | Time Range | 起止时间所界定的区间。 | 起止时间所界定的区间 |
| 时间分桶 | Time Bucket | 时间轴上的一个统计桶。 | 时间轴上的一个统计桶 |
| 时序 | Time Series | 按时间顺序排列的观测序列。 | 按时间顺序排列的观测序列 |
| 分类序列 | Categorical Series | 分类轴上的数据序列。 | 分类轴上的数据序列 |
| 分布 | Distribution | 取值的分布情况。 | 取值的分布情况 |
| 趋势 | Trend | 指标随时间的变化趋势。 | 指标随时间的变化趋势 |
| 筛选 | Filter | 拦截并处理请求或响应的组件。 | 请求过滤链一环 |
| 下钻 | Drill-down | 从汇总下钻到更细粒度。 | 从汇总下钻到更细粒度 |
| 上卷 | Roll-up | 从明细汇总到更高粒度。 | 从明细汇总到更高粒度 |
| 切片 | Slice | 按维度切出的数据切片。 | 按维度切出的数据切片 |
| 成功率 | Success Rate | 成功请求所占比例。 | 成功请求所占比例 |
| 错误率 | Error Rate | 失败请求所占比例。 | 失败请求所占比例 |
| 请求量 | Request Count | 请求次数。 | 请求次数 |
| 百分位 | Percentile | 将分布划分为百分位的统计量。 | 将分布划分为百分位的统计量 |
| 清单指标 | Inventory | 可售商品的库存数量。 | 蓝 T 恤可售 42 件 |
| 空态 | Empty State | 无内容时的占位呈现。 | 无内容时的占位呈现 |
| 刷新 | Refresh | Reload metrics for the current filters and time range。 | 点击刷新重载看板 |

## Common | 横切

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 模块开关 | Module Flag | 控制某功能模块启停的开关。 | 控制某功能模块启停的开关 |
| 功能开关服务 | Feature Flag Service | 控制功能开关的服务。 | flags.rag_v2=true |
| 模块访问过滤 | Module Access Filter | 按模块权限过滤请求的过滤器。 | 请求过滤链一环 |

## Appendix A. Cross-Cutting Architecture Terms | 通用架构术语

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 聚合根 | Aggregate Root | 聚合的入口实体，对外维护一致性边界。 | 聚合的入口实体，对外维护一致性边界 |
| 实体 | Entity | 具有连续身份的领域对象。 | 具有连续身份的领域对象 |
| 值对象 | Value Object | 由属性值定义、无独立身份的对象。 | 由属性值定义、无独立身份的对象 |
| 应用服务 | Application Service | 编排用例、不承载业务规则的应用层服务。 | ChatService.sendMessage |
| 仓储 | Repository | 封装聚合持久化与重建的机制。 | 按 id 查找 / 保存 |
| 网关 | Gateway | 对外出入流量的入口组件。 | 出站适配外部系统 |
| 流式响应 | Streaming (SSE) | 服务器通过 HTTP 向客户端单向推送事件流。 | 服务器通过 HTTP 向客户端单向推送事件流 |
| 提供商 | Provider | 提供某类能力的外部或内部供应方。 | 提供某类能力的外部或内部供应方 |
| 领域异常 | Domain Exception | 表示领域规则被违反的异常。 | 表示领域规则被违反的异常 |
| 跨域身份管理 | SCIM 2.0 | 跨域身份资源供给与管理的 HTTP/JSON 标准（RFC 7643/7644）。 | POST /Users（RFC 7644） |

## Appendix B. AI Engineering Terms | AI 工程通用术语

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 大语言模型 | Large Language Model (LLM) | 在大规模文本上预训练的通用语言模型。 | 在大规模文本上预训练的通用语言模型 |
| 对话客户端 | ChatClient | 调用对话模型的客户端。 | Spring AI ChatClient |
| 对话模型 | ChatModel | 对话补全模型抽象。 | 对话补全模型抽象 |
| 系统提示词 | System Prompt | 设定模型角色与约束的系统级提示。 | 设定模型角色与约束的系统级提示 |
| 提示词模板 | Prompt Template | 带占位符的可复用提示模板。 | 可复用预置结构 |
| 共享风格提示词 | Shared Style Prompt | 统一文风的共享提示。 | 统一文风的共享提示 |
| 上下文窗口 | Context Window | 模型单次可处理的最大上下文长度。 | 模型单次可处理的最大上下文长度 |
| 令牌 | Token | 模型处理的基本文本或符号单元。 | 模型词元 / API 令牌 |
| 温度 | Temperature | 控制生成随机性的采样参数。 | 控制生成随机性的采样参数 |
| 检索增强生成 | Retrieval-Augmented Generation (RAG) | 先检索外部知识再据此生成回答的范式。 | 先检索外部知识再据此生成回答的范式 |
| 增强生成 | Augmented Generation | 在检索上下文增强下的生成。 | 在检索上下文增强下的生成 |
| 向量存储 | Vector Store | 存储并检索嵌入向量的存储系统。 | pgvector SimilaritySearch |
| 工具回调 | Tool Callback | 工具被调用时的回调处理。 | 工具被调用时的回调处理 |
| 顾问 | Advisor | 投资顾问角色或服务。 | 投资顾问角色或服务 |
| 多模态 | Multimodal | 同时处理文本、图像或语音等多种模态。 | 同时处理文本、图像或语音等多种模态 |
| 模型上下文协议 | Model Context Protocol (MCP) | 模型与外部工具/上下文互操作的开放协议。 | 模型与外部工具/上下文互操作的开放协议 |
| Agent 互操作协议 | Agent2Agent Protocol (A2A) | 智能体之间发现与协作的协议。 | 智能体之间发现与协作的协议 |
| 编排器 | Orchestrator | 协调多个工作者完成任务的组件。 | 协调多个工作者完成任务的组件 |
| 子智能体 | Subagent | 在编排下执行子任务的智能体。 | 在编排下执行子任务的智能体 |
| 事实锚定 | Grounding | 使生成内容锚定于可核查的外部依据。 | 使生成内容锚定于可核查的外部依据 |
| 提示工程 | Prompt Engineering | 设计与优化提示以改善模型输出的做法。 | 设计与优化提示以改善模型输出的做法 |

## D.1 Foundations | 底层原理

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| Transformer | Transformer | 以自注意力为核心的序列建模神经网络架构。 | 以自注意力为核心的序列建模神经网络架构 |
| 自注意力 | Self-Attention | 序列内各位置相互计算相关权重的机制。 | 序列内各位置相互计算相关权重的机制 |
| 多头注意力 | Multi-Head Attention | 并行多组注意力以捕获不同关系的机制。 | 并行多组注意力以捕获不同关系的机制 |
| 前馈网络 | Feed-Forward Network (FFN) | 注意力层之间的逐位置前馈子网络。 | 注意力层之间的逐位置前馈子网络 |
| 混合专家 | Mixture of Experts (MoE) | 按输入路由到若干专家子网络的架构。 | 按输入路由到若干专家子网络的架构 |
| 旋转位置编码 | Rotary Position Embedding (RoPE) | 以旋转方式编码相对位置的位置嵌入。 | 1536 维浮点向量 |
| 分词器 | Tokenizer | 将文本切分为模型可处理记号的组件。 | 「你好」→ [101, 792, …] |
| 词元嵌入 | Token Embedding | 记号的向量嵌入。 | 词元 → 向量 |
| 隐藏状态 | Hidden State | 网络中间层的激活表示。 | 网络中间层的激活表示 |
| 对数几率 | Logits | 模型输出的未归一化分数。 | 模型输出的未归一化分数 |
| Softmax | Softmax | 将向量归一为概率分布的函数。 | 将向量归一为概率分布的函数 |
| 推理 | Inference | 用已训练模型进行预测或生成。 | 用已训练模型进行预测或生成 |
| 自回归解码 | Autoregressive Decoding | 逐记号依条件概率生成序列。 | 逐记号依条件概率生成序列 |
| 下一词预测 | Next-Token Prediction | 预测序列下一记号的训练/推理目标。 | 预测下一个 token |
| KV 缓存 | KV Cache | 推理时缓存键值以加速自回归解码。 | 推理时缓存键值以加速自回归解码 |
| 采样 | Sampling | 按概率分布抽取下一记号的过程。 | 按概率分布抽取下一记号的过程 |
| Top-p 采样 | Top-p (Nucleus Sampling) | 从累计概率达到 p 的最小记号集中采样。 | 从累计概率达到 p 的最小记号集中采样 |
| Top-k 采样 | Top-k Sampling | 仅从概率最高的 k 个记号中采样。 | 仅从概率最高的 k 个记号中采样 |
| 参数（权重） | Parameters (Weights) | 模型可学习的数值参数。 | Transformer 数十亿权重 |
| 视觉编码器 | Vision Encoder | 将图像编码为模型可用表示的组件。 | 将图像编码为模型可用表示的组件 |
| 多模态投影 | Multimodal Projector | 将一种模态表示投影到另一空间的组件。 | 将一种模态表示投影到另一空间的组件 |

## D.2 Engineering | 工程运行

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 模型产物 | Model Artifact | 可分发的模型制品。 | 可分发的模型制品 |
| 量化 | Quantization | 降低数值精度以压缩模型与加速推理。 | 降低数值精度以压缩模型与加速推理 |
| GGUF | GGUF | 用于本地大模型权重的文件格式。 | 用于本地大模型权重的文件格式 |
| 模型清单 | Model Manifest | 描述模型制品的清单。 | 描述模型制品的清单 |
| 模型架构标识 | Model Architecture | 模型的网络结构。 | 模型的网络结构 |
| 模型能力 | Model Capability | 模型所支持的能力。 | 模型所支持的能力 |
| 模型加载 | Model Load | 将模型装入运行时。 | 将模型装入运行时 |
| 嵌入维度 | Embedding Dimension | 嵌入向量的维数。 | 1536 维浮点向量 |
| 上下文长度 | Context Length | 模型单次可处理的最大上下文长度。 | 模型单次可处理的最大上下文长度 |
| 提示令牌数 | Prompt Tokens | 提示所占用的记号数。 | 提示所占用的记号数 |
| 补全令牌数 | Completion Tokens | 生成内容所占用的记号数。 | completion_tokens=80 |
| ONNX | ONNX | 跨框架表示机器学习模型的开放格式。 | 跨框架表示机器学习模型的开放格式 |
| ONNX 运行时 | ONNX Runtime | 执行 ONNX 模型的推理引擎。 | 执行 ONNX 模型的推理引擎 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 消息角色 | Message Role | 区分用户、助手等消息发送角色。 | 区分用户、助手等消息发送角色 |
| 智能体运行 | Agent Run | 智能体或流水线的一次执行实例。 | 智能体或流水线的一次执行实例 |
| 运行状态 | Run Status | 运行从排队、执行到结束所处的状态。 | 运行从排队、执行到结束所处的状态 |
| 网页来源 | Web Source | 对话或回答所引用的网页出处。 | 对话或回答所引用的网页出处 |
| 天气模拟器 | Weather Simulator | 提供模拟天气查询结果的工具实现。 | 「北京今天天气」 |
| 类型化标识 | Typed Id | 带类型约束的实体标识值对象。 | 带类型约束的实体标识值对象 |
| 可开关命名实体 | Enableable Named Entity | 带名称且可启用或停用的归属实体。 | 带名称且可启用或停用的归属实体 |
| 可开关描述实体 | Enableable Described Entity | 带名称与描述且可启用或停用的归属实体。 | 带名称与描述且可启用或停用的归属实体 |
| 归属不可变实体 | Owner-Aware Immutable | 按归属键分区且创建后不可变的实体。 | c:{clientId}；u:{accountId} |
| 不可变实体基类 | Abstract Immutable Entity | 带标识与创建时间的不可变实体基类型。 | 创建后不可变基类 |
