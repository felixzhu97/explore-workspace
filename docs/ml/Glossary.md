# Glossary | Explore ML

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 推荐 / Recommendation | 根据偏好预测用户可能感兴趣的内容（如：协同过滤荐相关商品）。<br>Predict content that users may be interested in based on their preferences. (e.g. collaborative filtering recommends related products). |
| 视觉审核 / Vision | 对图像或视频进行理解与分析（如：NSFW 审核打标）。<br>Understand and analyze images or videos. (e.g. NSFW moderation labeling). |
| 检索增强 / RAG | 先检索外部知识再据此生成回答的范式（如：先检索手册再答「年假几天」）。<br>A paradigm that first retrieves external knowledge and then generates answers based on it. (e.g. retrieve handbook then answer "how many leave days"). |
| 图像游乐场 / Image Playground | 交互试验图像能力的场所（如：提示词「一只猫」出图）。<br>A place for interactive experimentation with image capabilities. (e.g. prompt "a cat" generates image). |
| 语音 / Speech | 人口头语言的声波形式（如：ASR 听写；TTS 播报）。<br>The acoustic form of human spoken language. (e.g. ASR dictation; TTS playback). |
| 视频生成 / Video | 动态影像内容（如：文生 5 秒短片）。<br>Motion video content. (e.g. text-to-video 5s clip). |
| 测模界面 / Model Test UI | 用于试验模型的界面（如：本地跑一条 prompt）。<br>Interface for test models. (e.g. run one prompt locally). |

## Preferred Terms | 术语表

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 测试数据 / Fixture Data | 用于测试的固定样例数据（如：tests/golden.json）。<br>Fixed sample data for testing. (e.g. tests/golden.json). |
| Explore ML / Explore ML | 探索工作区中的机器学习服务（如：explore-ml 服务入口）。<br>Explore machine learning services in the workspace. (e.g. explore-ml service entry). |
| ML 应用 / ML App | 机器学习应用（如：ML 控制台应用壳）。<br>Machine learning applications. (e.g. ML console app shell). |
| 模块 / Module | 相对独立的功能单元（如：推荐/视觉模块开关）。<br>Relatively independent functional units. (e.g. recommendation/vision module toggles). |
| 设置 / Setting | 系统或用户的配置项（如：temperature=0.7）。<br>Configuration items of the system or user. (e.g. temperature=0.7). |
| 旁路上游 / Loopback Upstream | 回环到本地上游服务（如：127.0.0.1 旁路服务）。<br>Loop back to the local upstream service. (e.g. 127.0.0.1 sidecar service). |
| 语音识别 / ASR | 将语音转换为文本。<br>Convert speech to text. |
| 流式识别 / Streaming ASR | 边接收音频边输出转写的语音识别。<br>Speech recognition that outputs transcriptions while receiving audio. |
| 语音合成 / TTS | 将文本转换为语音。<br>Convert text to speech. |
| ML 代理 / ML Proxy | 机器学习服务的代理。<br>Agent for machine learning services. |
| 模块页 / Module Page | 功能模块对应的页面。<br>Page corresponding to the function module. |
| 图表 / Chart | 数据的图形化表示（如：月销量折线图）。<br>Graphical representation of data. (e.g. monthly sales line chart). |
| 健康报告 / Health Report | 健康检查的报告。<br>Health check report. |

## RAG（`server/rag`）

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 文本块 / Chunk | 文档切分后的一个片段（如：512 token 文档块）。<br>A segment of the document after splitting. (e.g. 512-token document chunk). |
| 分块器 / Text Chunker | 将文本切分为片段的组件（如：512 token 文档块）。<br>A component that divides text into segments. (e.g. 512-token document chunk). |
| 块索引器 / Chunk Indexer | 为片段建立检索索引的组件（如：512 token 文档块）。<br>Component for creating search indexes for fragments. (e.g. 512-token document chunk). |
| 集合 / Collection | 一组相关资源的集合。<br>A collection of related resources. |
| 检索命中 / Search Hit | 一次搜索命中的结果项。<br>The number of results returned in a single search. |
| 回答 / Answer | 对问题给出的回答。<br>The answer given to the question. |
| 重排旁路 / Rerank Sidecar | 旁路运行的重排序服务（如：127.0.0.1 旁路服务）。<br>Reordering service running in bypass mode. (e.g. 127.0.0.1 sidecar service). |
| 导出向量 / Exported Vectors | 导出的向量数据。<br>Exported vector data. |
| 已索引文档 / Indexed Document | 已建立检索索引的文档（如：可检索的已入库存档）。<br>Documents for which a search index has been created. (e.g. searchable indexed archive). |
| 已抓取网页 / Scraped Webpage | 抓取得到的网页内容（如：已抓取 HTML 正文）。<br>The content of the web page obtained by scraping. (e.g. scraped HTML body text). |
| 同步结果 / Sync Result | 一次同步操作的结果（如：同步返回结果对象）。<br>The result of a single synchronization operation. (e.g. synchronously returned result object). |
| 领域错误 / Domain Error | 领域规则校验失败的错误（如：业务规则违反（4xx））。<br>Error due to failed field rule validation. (e.g. business-rule violation (4xx)). |

## Vision（`server/vision`）

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 预测 / Prediction | 模型给出的预测结果。<br>A prediction result produced by a model. |
| 审核类别 / Moderation Category | 内容审核的类别（如：hate / sexual）。<br>Category of content moderation. (e.g. hate / sexual). |
| 帧结论 / Frame Verdict | 对单帧内容的审核判定。<br>Review and assessment of single-frame content. |
| 审核结论 / Moderation Verdict | 内容审核给出的判定结果。<br>The determination result given by the content review. |

## Image Playground and Video

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 生成任务 / Job | 可调度执行的工作单元。<br>Schedulable work units. |
| 任务状态 / Job Status | 任务执行所处的状态。<br>The status of task execution. |
| 任务仓储 / Job Store | 调度任务状态的存储（如：任务 id → 状态）。<br>Storage of scheduling task status. (e.g. job id → status). |
| LoRA 适配器 / LoRA Adapter | 以低秩矩阵适配预训练权重的微调方法。<br>A fine-tuning method for adapting pre-trained weights using a low-rank matrix. |

## Recommendation（`server/recommendation`）

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 排序 / Rank | 结果的排序名次或分数。<br>Ranking or score of the results. |
| 召回 / Recall | 召回率：相关项被检出的比例。<br>Recall rate: The proportion of relevant items that are detected. |
| 特征注册 / Feature Registry | 功能特性的登记处（如：名称 → 实现查找表）。<br>Registry of functional features. (e.g. name → implementation lookup). |
| 向量仓储 / Vector Store | 存储并检索嵌入向量的存储系统（如：pgvector SimilaritySearch）。<br>A storage system that stores and retrieves embedded vectors. (e.g. pgvector SimilaritySearch). |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 数据集 / Dataset | 用于训练或评估的样本集合。<br>A collection of samples used for training or evaluation. |
| 服务端点 / Endpoint | 对外提供模型能力的网络接口（如：GET /v1/health）。<br>Network interface that provides model capabilities externally. (e.g. GET /v1/health). |
| 模型制品 / Model Artifact | 可分发、可加载的模型权重与元数据打包。<br>Distributable, loadable model weights and metadata packages. |
| 训练任务 / Training Job | 一次模型训练或微调的执行单元。<br>The execution unit for a single model training or fine-tuning. |
| 操作员会话 / Operator Session | 操作员使用控制台时的工作会话。<br>The work session of the operator when using the console. |
| 嵌入 / Embedding | 将文本或多媒体映射到向量空间的表示（如：1536 维浮点向量）。<br>A representation that maps text or multimedia to a vector space. (e.g. 1536-dim float embedding). |
| ONNX / ONNX | 跨框架交换机器学习模型的开放格式。<br>An open format for exchanging machine learning models across frameworks. |
| 向量数据库 / Vector Database | 以近似最近邻检索向量的专用存储。<br>Dedicated storage for retrieving vectors with approximate nearest neighbors. |
| 重排 / Rerank | 对初检结果按相关性再次排序。<br>Re-sort the initial screening results by relevance. |
| 模型清单 / Model Manifest | 描述模型制品组成与用途的清单。<br>A list describing the composition and use of the model product. |
| 权重 / Weights | 模型可学习参数的数值集合。<br>The set of numerical values for the model's trainable parameters. |
