# Glossary | Explore AI

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 对话 / Chat | 参与者之间的会话式交流（如：私聊「你好」；群聊「项目组」）。<br>Conversational interaction among participants. (e.g. DM "Hello"; group "Project team"). |
| 工作流画布 / Pipeline | 由节点与边组成的处理流程（如：「检索→生成→审校」DAG）。<br>A process flow consisting of nodes and edges. (e.g. "retrieve→generate→review" DAG). |
| Agent 门面 / Agent | 能感知环境并采取行动以达成目标的软件实体（如：Research Agent 调搜索工具）。<br>A software entity that can perceive its environment and take action to achieve its goals. (e.g. Research Agent calls search tool). |
| Agent2Agent / A2A | 智能体之间发现与协作的协议（如：Agent Card；message/send）。<br>Protocols for discovery and collaboration between agents. (e.g. Agent Card；message/send). |
| 定时自动化 / Automation | 按计划自动执行的任务（如：每天 09:00 Cron 触发）。<br>Tasks that are automatically executed according to schedule. (e.g. Cron daily at 09:00). |
| 知识问答 / RAG | 先检索外部知识再据此生成回答的范式（如：先检索手册再答「年假几天」）。<br>A paradigm that first retrieves external knowledge and then generates answers based on it. (e.g. retrieve handbook then answer "how many leave days"). |
| 结构化分析 / Text Analysis | 对文本进行结构化分析（如：抽取合同甲方/金额/日期）。<br>Perform a structured analysis of the text. (e.g. extract contract party/amount/date). |
| 对话质量评估 / Eval | 对模型或系统输出质量的评估（如：Golden 集 + LLM-as-a-Judge）。<br>Evaluation of the quality of the model or system output. (e.g. Golden set + LLM-as-a-Judge). |
| 语音 / Audio | 声音信号或其处理能力（如：语音转写；TTS 播报）。<br>Sound signals or their processing capabilities. (e.g. speech-to-text; TTS playback). |
| MCP / MCP | 模型与外部工具/上下文互操作的开放协议（如：tools/list；tools/call）。<br>Open protocol for interoperability between models and external tools/contexts. (e.g. tools/list；tools/call). |
| Workflow Lab 原语 / Workflow | 按步骤编排的工作过程（如：「上传→分块→索引」步骤流）。<br>A work process organized by steps. (e.g. "upload→chunk→index" step flow). |
| 生成 / Generation | 由模型生成内容的能力类别（如：文生图；补全下一句）。<br>The category of the model's ability to generate content. (e.g. text-to-image; complete next sentence). |
| 账号 / Account | 代表个人或主体的账号（如：alice@example.com 账号）。<br>An account representing an individual or entity. (e.g. alice@example.com account). |
| 用户同步 / Provisioning | 供给与生命周期管理身份资源（如：IdP 经 SCIM 开停用户）。<br>Provision and manage identity resources throughout their lifecycle. (e.g. IdP provisions users via SCIM). |
| 横切 / Common | 跨功能共享的横切能力（如：跨域共享内核（异常、基类））。<br>Cross-functional, shared, cross-cutting capabilities. (e.g. shared cross-cutting kernel (exceptions, bases)). |

## Pipeline | 工作流（画布 Agent）

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 技能 / Skill | 可注入对话的指令与能力包（如：/skills 指令包 summarize）。<br>Commands and capability packages that can be injected into conversations. (e.g. /skills instruction pack summarize). |
| Agent 类型 / Agent Type | 智能体的类型分类（如：research Agent 配置）。<br>Classification of agent types. (e.g. research Agent config). |
| Agent 定义 / Agent Definition | 智能体的定义描述（如：research Agent 配置）。<br>Definition and description of the agent. (e.g. research Agent config). |
| Agent 注册表 / Agent Registry | 已注册智能体的登记处（如：research Agent 配置）。<br>Registry of registered agents. (e.g. research Agent config). |
| Agent 模版 / Agent Template | 可实例化的智能体模板（如：research Agent 配置）。<br>Instantiable agent template. (e.g. research Agent config). |
| 工作流流水线 / Agent Pipeline | 由多个智能体步骤组成的流水线。<br>A pipeline consisting of multiple agent steps. |
| 流水线节点 / Pipeline Node | 流水线中的一个处理节点（如：DAG 上一步骤）。<br>A processing node in the pipeline. (e.g. one step on the DAG). |
| 流水线边 / Pipeline Edge | 流水线节点之间的连接（如：节点 A → 节点 B）。<br>Connections between pipeline nodes. (e.g. node A → node B). |
| 工作流模版 / Pipeline Template | 可复用的流水线模板（如：预置画布模板）。<br>Reusable pipeline templates. (e.g. preset canvas template). |
| 自定义 Agent / Custom Agent | 用户自定义的智能体（如：research Agent 配置）。<br>User-defined agents. (e.g. research Agent config). |
| Pipeline 服务 / Pipeline Service | 提供流水线能力的应用服务（如：编排执行画布 DAG）。<br>Application services that provide pipeline capabilities. (e.g. orchestrate canvas DAG execution). |
| 自动化日程 / Automation Schedule | 自动化任务的调度安排（如：工作日 09:00 触发）。<br>Scheduling of automated tasks. (e.g. trigger weekdays at 09:00). |
| 自动化运行记录 / Automation Run | 自动化任务的一次执行（如：run_01 执行记录）。<br>A single execution of an automated task. (e.g. run_01 execution record). |
| 日程时间规则 / Schedule Timing | 调度的时间安排（如：TZ=Asia/Shanghai + Cron）。<br>Scheduling. (e.g. TZ=Asia/Shanghai + Cron). |
| Cron 计算 / Cron Schedule | 基于 cron 的调度表达式（如：0 9 * * MON-FRI）。<br>Cron-based scheduling expression. (e.g. 0 9 * * MON-FRI). |
| 邮件网关 / Email Gateway | 发送电子邮件的出站网关（如：Resend 发通知邮件）。<br>Outbound gateway for sending emails. (e.g. Resend sends notification email). |
| 编排服务 / Pipeline Orchestration | 调度流水线节点执行的过程（如：编排执行画布 DAG）。<br>The process of scheduling the execution of pipeline nodes. (e.g. orchestrate canvas DAG execution). |
| Supervisor 路由 / Supervisor Router | 监督并路由子任务的组件（如：监督者分派子任务）。<br>Components that supervise and route subtasks. (e.g. supervisor assigns subtasks). |
| Worker 调用器 / Worker Agent Invoker | 调用工作者智能体的组件（如：执行子任务的 Worker）。<br>Components for invoking worker agents. (e.g. Worker that runs subtasks). |
| Agent 门面服务 / Agent Service | 提供智能体能力的应用服务（如：Agent 门面/提示词）。<br>An application service that exposes agent capabilities. (e.g. Agent facade/prompts). |
| 默认 Agent 类型 / Default Agent Type | 默认选用的智能体类型（如：research Agent 配置）。<br>The default agent type. (e.g. research Agent config). |
| 工具标记过滤器 / Tool Call Markup Filter | 过滤工具调用标记的过滤器（如：过滤输出中的工具标记）。<br>Filter for tool call tags. (e.g. filter tool markup from output). |
| 工具循环守卫 / Tool Call Loop Guard | 限制工具调用循环次数的保护（如：阻断工具死循环）。<br>Protection that limits the number of tool call cycles. (e.g. block tool call loops). |
| 路由计划 / Routing Plan | 任务路由的计划（如：意图 → 子 Agent 计划）。<br>Planning for task routing. (e.g. intent → sub-agent plan). |
| Agent 交接 / Agent Handoff | 将任务移交给另一智能体（如：销售 → 支持 Agent）。<br>Transfer the task to another agent. (e.g. sales → support Agent). |
| Agent 提示词目录 / Agent Prompt Catalog | 智能体提示模板目录（如：提示词模板目录）。<br>A catalog of agent prompt templates. (e.g. prompt template catalog). |
| 内置技能 / Bundled Skill | 预置打包的技能（如：内置 summarize 技能）。<br>Pre-packaged skills. (e.g. built-in summarize skill). |
| 技能仓储 / Skill Registry | 技能的登记与查找（如：名称 → Skill 实现）。<br>Skill registration and search. (e.g. name → Skill implementation). |
| Agent 技能运行时 / Agent Skills Runtime | 执行智能体技能的运行时（如：加载并执行技能）。<br>Runtime for executing agent skills. (e.g. load and run a skill). |
| 提示词目录 / Prompt Catalog | 提示模板目录（如：提示词模板目录）。<br>Prompt template directory. (e.g. prompt template catalog). |
| 提示词组合服务 / Prompt Templates | 可复用的提示模板集合（如：提示词模板目录）。<br>A collection of reusable prompt templates. (e.g. prompt template catalog). |
| 本地化 RAG 提示词 / Localized RAG Prompt | 按语言本地化的 RAG 提示（如：中/英 RAG 提示词）。<br>RAG prompts localized by language. (e.g. zh/en RAG prompts). |

## Tool Calling | 工具调用

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 工具调用 / Tool Calling | 模型按约定模式调用外部工具（如：模型发 function call）。<br>The model invokes external tools according to the agreed-upon mode. (e.g. model emits function call). |
| 工具对话 / Tool Chat | 具备工具调用能力的对话（如：带 tools 的多轮对话）。<br>Conversations with tool-calling capabilities. (e.g. multi-turn chat with tools). |
| 工具结果 / Tool Result | 工具执行返回的结果（如：function 返回 JSON）。<br>The result returned by the tool execution. (e.g. function returns JSON). |
| 工具回调注册表 / Tool Callback Registry | 工具名到回调实现的注册表（如：名称 → ToolCallback）。<br>Registry of tool names to callback implementations. (e.g. name → ToolCallback). |
| 文档搜索工具 / Document Search Tool | 用于文档检索的工具（如：检索「年假」相关块）。<br>Tools for document retrieval. (e.g. retrieve chunks about "annual leave"). |
| RAG 搜索工具 / RAG Search Tool | 在 RAG 知识库中检索的工具（如：检索「年假」相关块）。<br>Tools for retrieving information from the RAG knowledge base. (e.g. retrieve chunks about "annual leave"). |
| 文档检索 / Document Search | 在文档集合中检索相关内容（如：检索「年假」相关块）。<br>Retrieve relevant content from a collection of documents. (e.g. retrieve chunks about "annual leave"). |
| 天气工具 / Weather Tool | 查询天气信息的工具（如：「北京今天天气」）。<br>A tool for checking weather information. (e.g. "Beijing weather today"). |
| 时间工具 / DateTime Tool | 提供权威日期时间的工具（如：「现在几点」）。<br>A tool that provides authoritative date and time. (e.g. "What time is it now"). |
| 网页搜索工具 / Web Search Tool | 用于检索公开网页的工具（如：「最新汇率」）。<br>A tool for retrieving publicly available web pages. (e.g. "latest exchange rate"). |

## Image Generation | 图像生成

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 图像生成 / Image Generation | 由模型根据提示生成图像（如：「赛博朋克城市」→ PNG）。<br>Images are generated by the model based on the prompt. (e.g. "cyberpunk city" → PNG). |
| 生成请求 / Image Generation Request | 请求生成图像的输入（如：文生图请求/响应）。<br>Request input to generate an image. (e.g. image-generation request/response). |
| 生成响应 / Image Generation Response | 图像生成的输出结果（如：文生图请求/响应）。<br>Output results of image generation. (e.g. image-generation request/response). |
| 提示词 / Prompt | 引导模型生成的输入文本（如：system：「你是助手」）。<br>Input text that guides the generation of the model. (e.g. system: "You are an assistant"). |
| 生成图像 / Generated Image | 模型生成的图像（如：文生图请求/响应）。<br>Images generated by the model. (e.g. image-generation request/response). |

## Image Analysis | 图像分析

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 图像分析 / Image Analysis | 对图像内容的分析（如：图 → 描述/检测/OCR）。<br>Analysis of image content. (e.g. image → caption/detect/OCR). |
| 图像描述 / Caption | 对图像或视频内容的文字描述（如：「橘猫趴在窗台」）。<br>Textual description of image or video content. (e.g. "An orange cat on the windowsill"). |
| 目标检测 / Object Detection | 在图像中定位并分类目标（如：框选「猫」0.92）。<br>Locate and classify objects in images. (e.g. box "cat" at 0.92). |
| 文字识别 / OCR | 从图像中识别文字（如：发票图 →「金额 128.00」）。<br>Recognize text from images. (e.g. invoice image → "amount 128.00"). |
| 描述引擎 / Caption Engine | 生成图像描述的引擎（如：视觉引擎实现）。<br>The engine that generates image descriptions. (e.g. vision engine implementation). |
| 检测引擎 / Detect Engine | 执行目标检测的引擎（如：视觉引擎实现）。<br>The engine that performs object detection. (e.g. vision engine implementation). |
| 识别引擎 / OCR Engine | 执行文字识别的引擎（如：视觉引擎实现）。<br>The engine that performs text recognition. (e.g. vision engine implementation). |

## Metrics | AI 指标看板

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| AI 指标看板 / Metrics | 可观测的定量指标（如：/metrics 看板 p95）。<br>Observable quantitative indicators. (e.g. /metrics dashboard p95). |
| AI 调用事件 / AI Invocation Event | 一次 AI 调用的事实记录（如：一次 chat/rag 调用）。<br>A factual record of an AI call. (e.g. one chat/rag invocation). |
| AI 能力 / AI Capability | 可度量的一类 AI 能力领域（如：chat；rag；tools）。<br>A measurable category of AI capability domains. (e.g. chat；rag；tools). |
| 调用结果 / Invocation Outcome | 调用成功或失败的结果（如：success / failure）。<br>The result of a successful or failed call. (e.g. success / failure). |
| 指标概览 / Metrics Overview | 指标总览（如：看板 KPI / 时序点）。<br>Indicator Overview. (e.g. dashboard KPI / time-series points). |
| 能力指标快照 / Metrics Capability Snapshot | 某能力在窗口内的指标快照（如：看板 KPI / 时序点）。<br>Snapshot of indicators for a certain capability within the window. (e.g. dashboard KPI / time-series points). |
| 指标时序 / Metrics Series | 指标随时间变化的序列（如：看板 KPI / 时序点）。<br>A sequence of indicators that change over time. (e.g. dashboard KPI / time-series points). |
| 指标下钻 / Metrics Drill-down | 指标的下钻明细（如：看板 KPI / 时序点）。<br>Drill-down details of the indicators. (e.g. dashboard KPI / time-series points). |
| AI 调用记录器 / AI Invocation Recorder | 记录 AI 调用事实的组件（如：一次 chat/rag 调用）。<br>Component that records the fact of AI calls. (e.g. one chat/rag invocation). |
| AI 调用完成事件 / AI Invocation Completed Event | AI 调用成功完成的事实（如：一次调用成功落库）。<br>The fact that the AI call was successfully completed. (e.g. one successful invocation persisted). |
| AI 调用失败事件 / AI Invocation Failed Event | AI 调用失败的事实（如：一次调用失败落库）。<br>The fact that the AI call failed. (e.g. one failed invocation persisted). |
| 耗时 / Latency | 从请求到响应的时间延迟（如：p95=120ms）。<br>The time delay from request to response. (e.g. p95=120ms). |
| 错误摘要 / Error Summary | 错误的汇总说明（如：timeout: upstream 5xx）。<br>Incorrect summary description. (e.g. timeout: upstream 5xx). |
| 调用统计 / Invocation Stats | 调用次数与结果的统计（如：看板 KPI / 时序点）。<br>Statistics on the number of calls and results. (e.g. dashboard KPI / time-series points). |
| 指标时间窗 / Metrics Window | 指标统计的时间窗口（如：看板 KPI / 时序点）。<br>Time window for metric statistics. (e.g. dashboard KPI / time-series points). |
| 耗时统计 / Latency Stats | 延迟分布的统计（如：看板 KPI / 时序点）。<br>Statistics on delay distribution. (e.g. dashboard KPI / time-series points). |

## Domain Kernel | 领域内核

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 实体标识 / Entity ID | 实体的唯一标识（如：UUID 主键）。<br>Unique identifier of the entity. (e.g. UUID primary key). |
| 值对象基类 / Abstract Embeddable | 可嵌入值对象的基类型（如：@Embeddable Money）。<br>The base type of the embeddable value object. (e.g. @Embeddable Money). |
| 不可变实体基类 / Abstract Immutable | 创建后不可变实体的基类型（如：创建后不可变基类）。<br>Base type of an immutable entity after creation. (e.g. immutable-after-create base class). |
| 可变实体基类 / Abstract Entity | 可变实体的持久化基类型（如：带 @Version 的可变基类）。<br>The persistence base type of the mutable entity. (e.g. mutable base class with @Version). |
| 归属实体基类 / Owner-Aware Entity | 带数据归属键的实体（如：c:{clientId}；u:{accountId}）。<br>Entity with data attribution key. (e.g. c:{clientId}；u:{accountId}). |
| 可启用实体 / Enableable Entity | 可启用或停用的实体（如：enabled=true/false）。<br>Entities that can be enabled or disabled. (e.g. enabled=true/false). |
| 归属仓储契约 / Owner-Scoped Repository | 按归属键限定访问的仓储（如：c:{clientId}；u:{accountId}）。<br>Warehouse with access restricted by ownership key. (e.g. c:{clientId}；u:{accountId}). |
| 领域事件 / Domain Event | 领域中已发生事实的记录（如：OrderPaid；UserProvisioned）。<br>Records of facts that have occurred in the field. (e.g. OrderPaid；UserProvisioned). |
| 事件处理器 / Event Handler | 响应领域或应用事件的处理器（如：消费 OrderPaid 发邮件）。<br>Handler that responds to domain or application events. (e.g. consume OrderPaid and send email). |
| 域字符串校验 / Domain Strings | 领域字符串的规范化与校验（如：非空、邮箱格式校验）。<br>Normalization and validation of domain strings. (e.g. non-empty and email-format checks). |

## Chat | 对话

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 会话 / Chat Session | 用户与系统之间的多轮对话容器（如：多轮 New Chat 线程）。<br>A container for multi-turn dialogues between the user and the system. (e.g. multi-turn New Chat thread). |
| 会话标题 / Session Title | 会话的标题名称（如：「关于年假政策」）。<br>Title of the session. (e.g. "About annual leave policy"). |
| 会话活跃 / Session Activity | 会话最近活跃的时间点（如：lastActiveAt=刚刚）。<br>The most recent time the conversation was active. (e.g. lastActiveAt=just now). |
| 客户端身份 / Anonymous id | 未登录访问者的匿名标识（如：访客 Cookie id）。<br>Anonymous identification of unregistered visitors. (e.g. guest Cookie id). |
| 数据归属键 / Owner Key | 划分数据归属的分区键（如：c:{clientId}；u:{accountId}）。<br>Partition key that divides data ownership. (e.g. c:{clientId}；u:{accountId}). |
| 无主数据 / Unowned | 尚无归属主体的数据分区。<br>There are no data partitions with an assigned owner. |
| 隐私清除 / Privacy Erasure | 按请求清除个人数据。<br>Erase personal data upon request. |
| 归属数据清除事件 / Owner Erasure Requested Event | 请求清除某归属数据的事实（如：按 GDPR 清归属数据）。<br>The fact that a request was made to erase certain attribution data. (e.g. erase owner data per GDPR). |
| 访客并入账号事件 / Guest Linked To Account Event | 访客数据并入账号的事实（如：访客会话并入正式账号）。<br>The fact that visitor data is incorporated into the account. (e.g. guest session linked to account). |
| 会话清除事件 / Chat Sessions Erased Event | 对话会话已被清除的事实（如：清空该用户全部会话）。<br>The fact that the chat session has been cleared. (e.g. erase all sessions for the user). |
| 隐私同意 / Privacy Consent | 用户对数据处理的同意表示。<br>A user’s consent for data processing. |
| 数据留存 / Data Retention | 按规定期限保留或清理数据。<br>Retain or dispose of data within the prescribed time limits. |
| 套餐配额 / Plan Quota | 套餐下的用量限额。<br>Usage limits under the plan. |
| 套餐 / Plan | 计费或能力套餐档位。<br>Billing or capacity package level. |
| 配额策略 / Quota Policy | 用量限额的规则。<br>Rules for usage limits. |
| 当日用量 / Daily Usage | 自然日内的累计用量。<br>Cumulative usage within a calendar day. |
| 配额对象 / Quota Subject | 用量计数所归属的对象。<br>The object to which the usage count belongs. |
| 配额判定 / Quota Decision | 一次用量检查的判定结果。<br>Determination result of a single-dose inspection. |
| Metrics 管理密钥 / Metrics Admin Key | 访问指标管理接口的密钥。<br>The key to access the metrics management interface. |
| 当前账号 / Account Me | 当前查看者的账号视图。<br>Account view of the current viewer. |
| 外部身份 / External Identity | 外部身份提供者中的主体标识。<br>Subject identifier in the external identity provider. |
| 联系邮箱 / Contact Email | 用于联系的电子邮箱地址。<br>Email address for contact. |
| 客户端 ID / Client ID | OAuth 或客户端的标识符（如：client_id=spa-web）。<br>Identifier for OAuth or client. (e.g. client_id=spa-web). |
| OAuth 登录 / OAuth Login | 基于 OAuth 2.0 / OIDC 的登录流程（如：用 Google 账号授权码登录）。<br>OAuth 2.0 / OIDC-based login process. (e.g. sign in with Google auth code). |
| IAM 访问令牌 / IAM Bearer | 以 Bearer 方式携带的 IAM 访问令牌。<br>IAM access token carried as a Bearer token. |
| IAM 模块范围 / IAM Module Scope | 访问令牌上约束模块能力的范围声明。<br>Scope declaration that restricts module capabilities on the access token. |
| 账号用户名 / Account User Name | 账号的登录名（如：登录名 alice）。<br>Login name for the account. (e.g. username alice). |
| 账号状态 / Account Status | 账号是否可用的状态。<br>Status indicating whether the account is available. |
| 预置账号 / Provisioned Account | 经供给预先创建的账号。<br>Pre-created accounts provided. |
| SCIM 用户同步 / SCIM Provisioning | 通过 SCIM 创建、更新与停用身份资源。<br>Create, update, and deactivate identity resources through SCIM. |
| SCIM 范围 / SCIM Scope | 调用 SCIM 接口所需的范围。<br>The scope required to call the SCIM interface. |
| 法律文档 / Legal Documents | 条款、隐私等法律文本（如：上传劳动合同 PDF）。<br>Legal texts such as terms and privacy policies. (e.g. upload employment-contract PDF). |
| 消息 / Chat Message | 对话中的一条消息（如：用户：「年假几天？」）。<br>A message in the conversation. (e.g. user: "How many leave days?"). |
| 用户消息 / User Message | 由用户发出的消息（如：角色=user 的一条消息）。<br>Messages sent by the user. (e.g. a message with role=user). |
| 助手消息 / Assistant Message | 由助手模型发出的消息。<br>Messages sent by the assistant model. |
| 会话标识 / Chat Session ID | 对话会话的唯一标识（如：sess_01HXYZ…）。<br>Unique identifier for the conversation session. (e.g. sess_01HXYZ…). |
| 消息标识 / Message ID | 消息的唯一标识（如：msg_01H…）。<br>Unique identifier of the message. (e.g. msg_01H…). |
| 结构图 / Structure Diagram | 用图形表示结构关系的图。<br>A diagram that graphically represents structural relationships. |
| 检测语言 / Detected Language | 检测到的文本语言。<br>Detected text language. |
| 活跃 / ACTIVE | 处于可用或进行中状态。<br>In Available or In Progress status. |
| 已关闭 / CLOSED | 已关闭、不再接受新活动。<br>Closed. No longer accepting new activities. |

## Workflow | Effective Agents 工作流

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 链式工作流 / Chain Workflow | 顺序串联步骤的工作流。<br>Workflow with sequentially linked steps. |
| 并行化工作流 / Parallelization Workflow | 并行执行子任务的工作流。<br>Workflow for executing subtasks in parallel. |
| 路由工作流 / Routing Workflow | 按条件路由到不同路径的工作流。<br>Workflow that routes to different paths based on conditions. |
| 编排-工人工作流 / Orchestrator-Workers Workflow | 编排者—工作者模式的工作流。<br>Workflow of the Orchestrator-Worker model. |
| 评估-优化工作流 / Evaluator-Optimizer Workflow | 评估与优化循环的工作流模式。<br>Evaluate and optimize the workflow model for cycles. |

## RAG | 知识问答

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 文档 / Rag Document | 纳入 RAG 知识库的文档（如：知识库中的一篇 PDF）。<br>Documents included in the RAG knowledge base. (e.g. a PDF in the knowledge base). |
| 文档标识 / Document ID | Unique identifier of a document（如：doc_01H…）。<br>Unique identifier of a document。. (e.g. doc_01H…). |
| 文档状态 / Document Status | 文档处理所处的状态（如：pending / indexed）。<br>The status of document processing. (e.g. pending / indexed). |
| 文档分块 / Document Chunk | 文档切分后的可检索片段（如：512 token 文档块）。<br>Retrievable segments after document segmentation. (e.g. 512-token document chunk). |
| 文档导入 / Document Ingestion | 将文档纳入知识库的过程（如：PDF → 分块 → 向量化）。<br>The process of incorporating documents into the knowledge base. (e.g. PDF → chunk → embed). |
| 评分分块 / Scored Chunk | 带相关性分数的检索片段（如：512 token 文档块）。<br>Retrieved snippets with relevance scores. (e.g. 512-token document chunk). |
| 提取文档 / Extracted Document | 从文件中抽取得到的文本内容（如：OCR/解析后的正文）。<br>Text content extracted from the file. (e.g. OCR/parsed plain text). |
| 分块 / Chunking | 将长文档切分为可检索片段（如：512 token 文档块）。<br>Divide long documents into searchable segments. (e.g. 512-token document chunk). |
| 嵌入向量 / Embedding | 将文本或数据映射到向量空间的表示（如：1536 维浮点向量）。<br>A representation that maps text or data to a vector space. (e.g. 1536-dim float embedding). |
| 检索 / Retrieval | 从知识库中找出与查询相关的内容（如：向量库 top-5 相关块）。<br>Find content related to the query in the knowledge base. (e.g. vector store top-5 related chunks). |
| Spring AI 向量库 / Spring AI Vector Store | Spring AI 中的向量存储抽象（如：pgvector SimilaritySearch）。<br>Vector store abstraction in Spring AI. (e.g. pgvector SimilaritySearch). |
| 来源引用 / Source Citation | 生成内容所依据的来源引用。<br>Source citations on which the generated content is based. |
| 上下文 / Context | 解释或约束当前操作的上下文信息。<br>Contextual information that explains or constrains the current operation. |
| RAG 对话 / RAG Chat | 结合检索增强的对话。<br>Combining retrieval-augmented dialogue. |
| 视觉问答 / Vision Chat | 结合视觉输入的对话。<br>Conversations that incorporate visual input. |
| 文档上传 / Document Upload | 上传文档文件（如：multipart 上传 PDF）。<br>Upload document file. (e.g. multipart upload PDF). |
| 向量相似度 / Vector Similarity | 向量空间中相近程度的度量。<br>A measure of similarity in a vector space. |
| 分块大小 / Chunk Size | 单个片段的目标大小（如：512 token 文档块）。<br>Target size of a single segment. (e.g. 512-token document chunk). |
| 检索数量 / Top K | 仅保留得分最高的前 k 条。<br>Only keep the top k items with the highest scores. |
| 分数阈值 / Score Threshold | 接受结果的最低分数门槛。<br>The minimum score threshold for accepting results. |
| 文档读取器 / Document Reader | 读取文档内容的组件（如：PDFBox 读页文本）。<br>Component for reading document content. (e.g. PDFBox reads page text). |
| 文档转换器 / Document Transformer | 转换文档内容形态的组件（如：清洗/分块转换）。<br>Components that transform the format of document content. (e.g. clean/chunk transform). |
| 文档写入器 / Document Writer | 写出文档或片段的组件（如：写入向量库）。<br>Write the components of a document or passage. (e.g. write to vector store). |
| RAG 数据管道 / RAG ETL Pipeline | 文档抽取、转换与装载的流水线。<br>Pipeline for document extraction, transformation, and loading. |
| 处理中 / PROCESSING | 正在处理中。<br>is in process. |
| 就绪 / READY | 已就绪可执行或可使用。<br>Ready to be executed or used. |
| 失败 / FAILED | 执行失败。<br>Execute Failed. |

## Audio | 语音

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 语音合成 / Text-to-Speech (TTS) | 将文本转换为语音。<br>Convert text to speech. |
| 音色 / Voice | 可辨识的说话声音特征。<br>Identifiable speech characteristics. |
| 语音文本 / Speech Text | 语音对应的文本。<br>Text corresponding to the speech. |
| 合成音频 / Synthesized Audio | 由合成得到的音频。<br>Audio obtained by synthesis. |
| 合成 / Synthesize | 将文本合成为语音。<br>Convert text to speech. |
| 自动语音识别 / Automatic Speech Recognition (ASR) | 将语音转换为文本。<br>Convert speech to text. |
| 流式转写 / Streaming Transcription | 流式输出的语音转写。<br>Speech transcription with streaming output. |
| 转写 / Transcription | 语音内容的文本转写结果。<br>Text transcription of audio content. |
| 语音对话 / Voice Conversation | 以语音进行的对话。<br>Conversations conducted by voice. |

## MCP | MCP

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| MCP 服务端 / MCP Server | 提供 MCP 能力的服务端。<br>The server-side that provides MCP capabilities. |
| MCP 客户端 / MCP Client | 连接 MCP 服务端的客户端。<br>Client connecting to the MCP server. |
| MCP 工具 / MCP Tool | 通过 MCP 暴露的工具。<br>Tools exposed through MCP. |
| MCP 工具定义 / MCP Tool Definition | MCP 工具的名称与参数定义。<br>Names and parameter definitions of MCP tools. |
| MCP 会话 / MCP Session | MCP 交互的会话（如：一次 MCP 工具会话）。<br>Session for MCP interaction. (e.g. one MCP tool session). |
| MCP 服务端连接 / MCP Server Connection | 到 MCP 服务端的连接。<br>Connection to the MCP server. |
| MCP 对话 / MCP Chat | 通过 MCP 使用工具的对话。<br>Conversation about using tools through MCP. |

## A2A | Agent2Agent

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| A2A 服务端 / A2A Server | 实现 A2A 协议的服务端。<br>Implements the server side of the A2A protocol. |
| Agent 名片 / Agent Card | 描述智能体能力与端点的名片。<br>A business card describing the agent's capabilities and endpoints. |
| A2A 技能 / A2A Skill | A2A 智能体声明的技能。<br>Skills declared by the A2A agent. |
| A2A 任务 / A2A Task | A2A 协议中的任务单元。<br>Task unit in the A2A agreement. |
| A2A 消息 / A2A Message | A2A 协议中的消息。<br>Messages in the A2A agreement. |
| 发送消息 / Send Message | 向会话发送一条消息。<br>Send a message to the conversation. |
| 流式发送消息 / Stream Message | 以流式方式输出消息内容（如：SSE 流式吐字）。<br>Output the message content in a streaming manner. (e.g. SSE token streaming). |
| Agent 目录 / Agent Gallery | 可浏览选用的智能体陈列。<br>Browse the selection of available agents. |
| Agent 工作室 / Agent Studio | 创建与编辑智能体的工作室。<br>A studio for creating and editing agents. |
| A2A 客户端 / A2A Client | 调用 A2A 服务的客户端。<br>Client that calls the A2A service. |

## Text Analysis | 结构化分析

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 结构化输出 / Structured Output | 符合约定结构的模型输出。<br>Model output that conforms to the agreed-upon structure. |
| 分析结果 / Text Analysis Result | 文本分析得到的结果。<br>Results obtained from text analysis. |
| 情感 / Sentiment | 文本所表达的情感倾向。<br>The sentiment expressed in the text. |
| 关键点 / Key Points | 内容中的要点摘录。<br>Extracts of key points from the content. |
| 命名实体 / Named Entities | 文本中识别出的命名实体。<br>Named entities identified in the text. |

## Eval | 对话质量评估

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 对话评估 / Chat Evaluation | 对对话质量的评估。<br>Assessment of conversation quality. |
| 评估结果 / Chat Evaluation Result | 对话评估的结果。<br>Results of the dialogue assessment. |
| 相关性评估器 / Relevancy Evaluator | 评估回答相关性的组件。<br>Components for assessing the relevance of answers. |
| 事实性评估器 / Fact-Checking Evaluator | 评估事实准确性的组件。<br>Components for assessing the accuracy of facts. |
| 连贯性分数 / Coherence Score | 文本连贯程度的分数。<br>Score for the level of textual coherence. |
| 相关性分数 / Relevance Score | 结果与查询相关程度的分数。<br>Score for the relevance of the result to the query. |
| 有用性分数 / Helpfulness Score | 回答对用户有帮助程度的分数。<br>Score for how helpful the answer is to the user. |
| 事实性分数 / Factuality Score | 陈述符合事实程度的分数。<br>Score for the degree to which the statement is factually accurate. |
| 综合分数 / Overall Score | 综合各项得到的总分。<br>The total score obtained by combining all the scores. |
| 安全标记 / Safety Flag | 标识潜在安全风险的标记。<br>Markers that identify potential safety risks. |
| 评估对话客户端 / Evaluation ChatClient | 用于评估的对话客户端（如：Spring AI ChatClient）。<br>Conversation client used for evaluation. (e.g. Spring AI ChatClient). |
| 黄金评估套件 / Golden Suite | 标准评估用例的集合。<br>A collection of standard assessment use cases. |
| 黄金评估用例 / Golden Eval Case | 用于回归评估的标准用例。<br>Standard use case for regression assessment. |
| 官方门禁结果 / Official Gate Result | 门槛门禁检查结果。<br>Threshold access control check results. |

## Appendix C. Business Intelligence Terms | 商业智能通用术语

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 令牌用量 / Token Usage | 一次调用消耗的记号数量（如：prompt=120 completion=80）。<br>The number of tokens consumed per call. (e.g. prompt=120 completion=80). |
| 商业智能 / Business Intelligence (BI) | 将数据转化为经营洞察的方法与工具（如：销售漏斗看板）。<br>Methods and tools for transforming data into business insights. (e.g. sales-funnel dashboard). |
| 仪表盘 / Dashboard | 汇集指标与图表的概览视图。<br>An overview view that brings together metrics and charts. |
| 关键绩效指标 / Key Performance Indicator (KPI) | 衡量目标达成程度的关键绩效指标（如：月活；转化率）。<br>Key performance indicators for measuring the degree to which goals are achieved. (e.g. MAU; conversion rate). |
| 指标 / Metric | Named quantitative measure of a process (count, rate, latency, tokens)（如：错误率；QPS）。<br>Named quantitative measure of a process (count, rate, latency, tokens)。. (e.g. error rate; QPS). |
| 度量 / Measure | 分析中可聚合的度量值。<br>Aggregatable measures in the analysis. |
| 维度 / Dimension | 分析中用于切片的分类属性。<br>Classification attributes used for slicing in the analysis. |
| 事实事件 / Fact Event | 已发生业务事实的事件记录（如：不可变领域事实事件）。<br>Record of events for which business facts have occurred. (e.g. immutable domain fact event). |
| 粒度 / Grain | 数据分析的粒度。<br>Granularity of data analysis. |
| 聚合 / Aggregation | 将多条观测合并为汇总结果。<br>Combine multiple observations into an aggregated result. |
| 时间范围 / Time Range | 起止时间所界定的区间。<br>The interval defined by the start and end times. |
| 时间分桶 / Time Bucket | 时间轴上的一个统计桶。<br>A statistical bucket on the timeline. |
| 时序 / Time Series | 按时间顺序排列的观测序列。<br>A sequence of observations arranged in chronological order. |
| 分类序列 / Categorical Series | 分类轴上的数据序列。<br>Data series on the category axis. |
| 分布 / Distribution | 取值的分布情况。<br>Distribution of values. |
| 趋势 / Trend | 指标随时间的变化趋势。<br>Trends in indicators over time. |
| 筛选 / Filter | 拦截并处理请求或响应的组件（如：请求过滤链一环）。<br>A component that intercepts and processes requests or responses. (e.g. one hop in the request filter chain). |
| 下钻 / Drill-down | 从汇总下钻到更细粒度。<br>Drill down from the summary to a finer granularity. |
| 上卷 / Roll-up | 从明细汇总到更高粒度。<br>Aggregate from details to a higher level of granularity. |
| 切片 / Slice | 按维度切出的数据切片。<br>Data slices by dimension. |
| 成功率 / Success Rate | 成功请求所占比例。<br>Proportion of successful requests. |
| 错误率 / Error Rate | 失败请求所占比例。<br>Percentage of failed requests. |
| 请求量 / Request Count | 请求次数。<br>Number of cases. |
| 百分位 / Percentile | 将分布划分为百分位的统计量。<br>A statistic that divides a distribution into percentiles. |
| 清单指标 / Inventory | 可售商品的库存数量（如：蓝 T 恤可售 42 件）。<br>Inventory quantity of items available for sale. (e.g. blue T-shirt stock 42). |
| 空态 / Empty State | 无内容时的占位呈现。<br>Placeholder display when there is no content. |
| 刷新 / Refresh | Reload metrics for the current filters and time range（如：点击刷新重载看板）。<br>Reload metrics for the current filters and time range。. (e.g. click refresh to reload dashboard). |

## Common | 横切

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 模块开关 / Module Flag | 控制某功能模块启停的开关。<br>Switch to control the start and stop of a function module. |
| 功能开关服务 / Feature Flag Service | 控制功能开关的服务（如：flags.rag_v2=true）。<br>Service for controlling feature switches. (e.g. flags.rag_v2=true). |
| 模块访问过滤 / Module Access Filter | 按模块权限过滤请求的过滤器（如：请求过滤链一环）。<br>Filter for filtering requests by module permissions. (e.g. one hop in the request filter chain). |

## Appendix A. Cross-Cutting Architecture Terms | 通用架构术语

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 聚合根 / Aggregate Root | 聚合的入口实体，对外维护一致性边界。<br>The aggregated entry point entity that maintains consistency boundaries externally. |
| 实体 / Entity | 具有连续身份的领域对象。<br>Domain object with continuous identity. |
| 值对象 / Value Object | 由属性值定义、无独立身份的对象。<br>Objects defined by attribute values, without independent identities. |
| 应用服务 / Application Service | 编排用例、不承载业务规则的应用层服务（如：ChatService.sendMessage）。<br>Application layer services for orchestrating use cases, not hosting business rules. (e.g. ChatService.sendMessage). |
| 仓储 / Repository | 封装聚合持久化与重建的机制（如：按 id 查找 / 保存）。<br>Encapsulate the mechanism for aggregate persistence and reconstruction. (e.g. find/save by id). |
| 网关 / Gateway | 对外出入流量的入口组件（如：出站适配外部系统）。<br>Ingress component for outbound traffic. (e.g. outbound adapter to external system). |
| 流式响应 / Streaming (SSE) | 服务器通过 HTTP 向客户端单向推送事件流。<br>The server pushes event streams unidirectionally to the client via HTTP. |
| 提供商 / Provider | 提供某类能力的外部或内部供应方。<br>An external or internal supplier that provides a certain type of capability. |
| 领域异常 / Domain Exception | 表示领域规则被违反的异常。<br>An exception indicating that a domain rule has been violated. |
| 跨域身份管理 / SCIM 2.0 | 跨域身份资源供给与管理的 HTTP/JSON 标准（RFC 7643/7644）（如：POST /Users（RFC 7644））。<br>HTTP/JSON standard for cross-domain identity resource provisioning and management (RFC 7643/7644). (e.g. POST /Users（RFC 7644）). |

## Appendix B. AI Engineering Terms | AI 工程通用术语

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 大语言模型 / Large Language Model (LLM) | 在大规模文本上预训练的通用语言模型。<br>A general-purpose language model pre-trained on large-scale text. |
| 对话客户端 / ChatClient | 调用对话模型的客户端（如：Spring AI ChatClient）。<br>Client that calls the dialogue model. (e.g. Spring AI ChatClient). |
| 对话模型 / ChatModel | 对话补全模型抽象。<br>Abstraction of the dialogue completion model. |
| 系统提示词 / System Prompt | 设定模型角色与约束的系统级提示。<br>System-level prompts for setting model roles and constraints. |
| 提示词模板 / Prompt Template | 带占位符的可复用提示模板（如：可复用预置结构）。<br>Reusable prompt template with placeholders. (e.g. reusable preset structure). |
| 共享风格提示词 / Shared Style Prompt | 统一文风的共享提示。<br>A shared prompt that unifies writing style. |
| 上下文窗口 / Context Window | 模型单次可处理的最大上下文长度。<br>The maximum context length that the model can process in a single pass. |
| 令牌 / Token | 模型处理的基本文本或符号单元（如：模型词元 / API 令牌）。<br>The basic text or symbol unit processed by the model. (e.g. model token / API token). |
| 温度 / Temperature | 控制生成随机性的采样参数。<br>Control the sampling parameters that generate randomness. |
| 检索增强生成 / Retrieval-Augmented Generation (RAG) | 先检索外部知识再据此生成回答的范式。<br>A paradigm that first retrieves external knowledge and then generates answers based on it. |
| 增强生成 / Augmented Generation | 在检索上下文增强下的生成。<br>Generated with retrieval-augmented context. |
| 向量存储 / Vector Store | 存储并检索嵌入向量的存储系统（如：pgvector SimilaritySearch）。<br>A storage system that stores and retrieves embedded vectors. (e.g. pgvector SimilaritySearch). |
| 工具回调 / Tool Callback | 工具被调用时的回调处理。<br>Callback handling when the tool is invoked. |
| 顾问 / Advisor | 投资顾问角色或服务。<br>Investment advisor roles or services. |
| 多模态 / Multimodal | 同时处理文本、图像或语音等多种模态。<br>Simultaneously process multiple modalities such as text, images, or speech. |
| 模型上下文协议 / Model Context Protocol (MCP) | 模型与外部工具/上下文互操作的开放协议。<br>Open protocol for interoperability between models and external tools/contexts. |
| Agent 互操作协议 / Agent2Agent Protocol (A2A) | 智能体之间发现与协作的协议。<br>Protocols for discovery and collaboration between agents. |
| 编排器 / Orchestrator | 协调多个工作者完成任务的组件。<br>Components that coordinate multiple workers to complete tasks. |
| 子智能体 / Subagent | 在编排下执行子任务的智能体。<br>An agent that performs subtasks under orchestration. |
| 事实锚定 / Grounding | 使生成内容锚定于可核查的外部依据。<br>Anchor generated content to verifiable external sources. |
| 提示工程 / Prompt Engineering | 设计与优化提示以改善模型输出的做法。<br>The practice of designing and optimizing prompts to improve model outputs. |

## D.1 Foundations | 底层原理

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| Transformer / Transformer | 以自注意力为核心的序列建模神经网络架构。<br>A neural network architecture for sequence modeling centered on self-attention. |
| 自注意力 / Self-Attention | 序列内各位置相互计算相关权重的机制。<br>A mechanism for calculating the relative weights of each position within a sequence. |
| 多头注意力 / Multi-Head Attention | 并行多组注意力以捕获不同关系的机制。<br>Parallel multi-set attention to capture mechanisms of different relationships. |
| 前馈网络 / Feed-Forward Network (FFN) | 注意力层之间的逐位置前馈子网络。<br>Position-wise feedforward subnetwork between attention layers. |
| 混合专家 / Mixture of Experts (MoE) | 按输入路由到若干专家子网络的架构。<br>An architecture that routes inputs to several expert sub-networks. |
| 旋转位置编码 / Rotary Position Embedding (RoPE) | 以旋转方式编码相对位置的位置嵌入（如：1536 维浮点向量）。<br>Positional embedding that encodes relative positions in a rotational manner. (e.g. 1536-dim float embedding). |
| 分词器 / Tokenizer | 将文本切分为模型可处理记号的组件（如：「你好」→ [101, 792, …]）。<br>Segment the text into components that the model can process as tokens. (e.g. "Hello" → [101, 792, …]). |
| 词元嵌入 / Token Embedding | 记号的向量嵌入（如：词元 → 向量）。<br>Vector embedding of tokens. (e.g. token → embedding). |
| 隐藏状态 / Hidden State | 网络中间层的激活表示。<br>Activation representation of the network's intermediate layers. |
| 对数几率 / Logits | 模型输出的未归一化分数。<br>Unnormalized scores output by the model. |
| Softmax / Softmax | 将向量归一为概率分布的函数。<br>A function that normalizes a vector to a probability distribution. |
| 推理 / Inference | 用已训练模型进行预测或生成。<br>Make predictions or generate content using a trained model. |
| 自回归解码 / Autoregressive Decoding | 逐记号依条件概率生成序列。<br>Generate sequences based on conditional probability, symbol by symbol. |
| 下一词预测 / Next-Token Prediction | 预测序列下一记号的训练/推理目标（如：预测下一个 token）。<br>The training/inference objective is to predict the next token in the sequence. (e.g. predict next token). |
| KV 缓存 / KV Cache | 推理时缓存键值以加速自回归解码。<br>Cache key-values during inference to speed up autoregressive decoding. |
| 采样 / Sampling | 按概率分布抽取下一记号的过程。<br>The process of sampling the next token according to a probability distribution. |
| Top-p 采样 / Top-p (Nucleus Sampling) | 从累计概率达到 p 的最小记号集中采样。<br>Sample from the smallest set of tokens whose cumulative probability reaches p. |
| Top-k 采样 / Top-k Sampling | 仅从概率最高的 k 个记号中采样。<br>Sample only from the k tokens with the highest probabilities. |
| 参数（权重） / Parameters (Weights) | 模型可学习的数值参数（如：Transformer 数十亿权重）。<br>Numerical parameters that the model can learn. (e.g. Transformer with billions of weights). |
| 视觉编码器 / Vision Encoder | 将图像编码为模型可用表示的组件。<br>A component that encodes images into model-usable representations. |
| 多模态投影 / Multimodal Projector | 将一种模态表示投影到另一空间的组件。<br>A component that projects a modal representation into another space. |

## D.2 Engineering | 工程运行

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 模型产物 / Model Artifact | 可分发的模型制品。<br>Distributable model products. |
| 量化 / Quantization | 降低数值精度以压缩模型与加速推理。<br>Reduce numerical precision to compress the model and accelerate inference. |
| GGUF / GGUF | 用于本地大模型权重的文件格式。<br>File format used for local large model weights. |
| 模型清单 / Model Manifest | 描述模型制品的清单。<br>List describing the model products. |
| 模型架构标识 / Model Architecture | 模型的网络结构。<br>Network structure of the model. |
| 模型能力 / Model Capability | 模型所支持的能力。<br>Capabilities supported by the model. |
| 模型加载 / Model Load | 将模型装入运行时。<br>Load the model at runtime. |
| 嵌入维度 / Embedding Dimension | 嵌入向量的维数（如：1536 维浮点向量）。<br>The dimensionality of the embedding vector. (e.g. 1536-dim float embedding). |
| 上下文长度 / Context Length | 模型单次可处理的最大上下文长度。<br>The maximum context length that the model can process in a single pass. |
| 提示令牌数 / Prompt Tokens | 提示所占用的记号数。<br>Indicates the number of tokens used. |
| 补全令牌数 / Completion Tokens | 生成内容所占用的记号数（如：completion_tokens=80）。<br>The number of tokens used to generate the content. (e.g. completion_tokens=80). |
| ONNX / ONNX | 跨框架表示机器学习模型的开放格式。<br>A cross-framework, open format for representing machine learning models. |
| ONNX 运行时 / ONNX Runtime | 执行 ONNX 模型的推理引擎。<br>Inference engine for executing ONNX models. |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 消息角色 / Message Role | 区分用户、助手等消息发送角色。<br>Distinguish between message-sending roles such as users and assistants. |
| 智能体运行 / Agent Run | 智能体或流水线的一次执行实例。<br>A single execution instance of an agent or pipeline. |
| 运行状态 / Run Status | 运行从排队、执行到结束所处的状态。<br>The status of the run from queuing to execution to completion. |
| 网页来源 / Web Source | 对话或回答所引用的网页出处。<br>The source of the webpage referenced in the conversation or answer. |
| 天气模拟器 / Weather Simulator | 提供模拟天气查询结果的工具实现（如：「北京今天天气」）。<br>Implementation of a tool that provides simulated weather query results. (e.g. "Beijing weather today"). |
| 类型化标识 / Typed Id | 带类型约束的实体标识值对象。<br>Entity identification value object with type constraints. |
| 可开关命名实体 / Enableable Named Entity | 带名称且可启用或停用的归属实体。<br>An ownership entity with a name that can be enabled or disabled. |
| 可开关描述实体 / Enableable Described Entity | 带名称与描述且可启用或停用的归属实体。<br>An ownership entity with a name and description that can be enabled or disabled. |
| 归属不可变实体 / Owner-Aware Immutable | 按归属键分区且创建后不可变的实体（如：c:{clientId}；u:{accountId}）。<br>Entities that are partitioned by the attribution key and cannot be changed after creation. (e.g. c:{clientId}；u:{accountId}). |
| 不可变实体基类 / Abstract Immutable Entity | 带标识与创建时间的不可变实体基类型（如：创建后不可变基类）。<br>Immutable entity base type with identifier and creation time. (e.g. immutable-after-create base class). |
