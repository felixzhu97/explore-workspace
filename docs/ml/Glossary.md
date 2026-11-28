# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 推荐 | Recommendation | 根据偏好预测用户可能感兴趣的内容。 | Feed 排序 |
| 视觉审核 | Vision | 对图像或视频进行理解与分析。 | 审核 / 打标 |
| 检索增强 | RAG | 先检索外部知识再据此生成回答的范式。 | 先检索文档再生成回答 |
| 图像游乐场 | Image Playground | 交互试验图像能力的场所。 | 文生图试验台 |
| 语音 | Speech | 人口头语言的声波形式。 | ASR + TTS |
| 视频生成 | Video | 动态影像内容。 | 文生视频任务 |
| 测模界面 | Model Test UI | 用于试验模型的界面。 | 本地测模页 |

## Preferred Terms | 术语表

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 测试数据 | Fixture Data | 用于测试的固定样例数据。 | golden.json |
| Explore ML | Explore ML | 探索工作区中的机器学习服务。 | explore-ml 服务 |
| ML 应用 | ML App | 机器学习应用。 | 统一 ML 控制台 |
| 模块 | Module | 相对独立的功能单元。 | 功能开关 |
| 设置 | Setting | 系统或用户的配置项。 | Setting |
| 旁路上游 | Loopback Upstream | 回环到本地上游服务。 | Loopback Upstream |
| 语音识别 | ASR | 将语音转换为文本。 | 语音 → 文本 |
| 流式识别 | Streaming ASR | 边接收音频边输出转写的语音识别。 | 边说边出字 |
| 语音合成 | TTS | 将文本转换为语音。 | 文本 → 语音 |
| ML 代理 | ML Proxy | 机器学习服务的代理。 | BFF → ML |
| 模块页 | Module Page | 功能模块对应的页面。 | 功能开关 |
| 图表 | Chart | 数据的图形化表示。 | 折线 / 柱状图 |
| 健康报告 | Health Report | 健康检查的报告。 | ok / degraded |

## RAG（`server/rag`）

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 文本块 | Chunk | 文档切分后的一个片段。 | 512 token 文档片段 |
| 分块器 | Text Chunker | 将文本切分为片段的组件。 | 知识库对象 |
| 块索引器 | Chunk Indexer | 为片段建立检索索引的组件。 | 知识库对象 |
| 集合 | Collection | 一组相关资源的集合。 | 向量集合名 |
| 检索命中 | Search Hit | 一次搜索命中的结果项。 | 命中文档标题+摘要 |
| 回答 | Answer | 对问题给出的回答。 | RAG 最终回答 |
| 重排旁路 | Rerank Sidecar | 旁路运行的重排序服务。 | Rerank Sidecar |
| 导出向量 | Exported Vectors | 导出的向量数据。 | 向量检索 |
| 已索引文档 | Indexed Document | 已建立检索索引的文档。 | 知识库对象 |
| 已抓取网页 | Scraped Webpage | 抓取得到的网页内容。 | Scraped Webpage |
| 同步结果 | Sync Result | 一次同步操作的结果。 | Sync Result |
| 领域错误 | Domain Error | 领域规则校验失败的错误。 | Domain Error |

## Vision（`server/vision`）

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 预测 | Prediction | 模型给出的预测结果。 | 标签 + 置信度 |
| 审核类别 | Moderation Category | 内容审核的类别。 | Moderation Category |
| 帧结论 | Frame Verdict | 对单帧内容的审核判定。 | 单帧审核结论 |
| 审核结论 | Moderation Verdict | 内容审核给出的判定结果。 | ALLOW / BLOCK |

## Image Playground and Video

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 生成任务 | Job | 可调度执行的工作单元。 | image-gen job |
| 任务状态 | Job Status | 任务执行所处的状态。 | PENDING；RUNNING；DONE |
| 任务仓储 | Job Store | 调度任务状态的存储。 | Job Store |
| LoRA 适配器 | LoRA Adapter | 以低秩矩阵适配预训练权重的微调方法。 | 低秩增量权重 |

## Recommendation（`server/recommendation`）

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 排序 | Rank | 结果的排序名次或分数。 | 排序分 |
| 召回 | Recall | 召回率：相关项被检出的比例。 | 召回候选集 |
| 特征注册 | Feature Registry | 功能特性的登记处。 | 推荐特征名表 |
| 向量仓储 | Vector Store | 存储并检索嵌入向量的存储系统。 | 相似度 Top-K 检索 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 数据集 | Dataset | 用于训练或评估的样本集合。 | 训练/评估样本集 |
| 服务端点 | Endpoint | 对外提供模型能力的网络接口。 | /v1/recommend |
| 模型制品 | Model Artifact | 可分发、可加载的模型权重与元数据打包。 | 权重 + 清单打包 |
| 训练任务 | Training Job | 一次模型训练或微调的执行单元。 | 一次微调任务 |
| 操作员会话 | Operator Session | 操作员使用控制台时的工作会话。 | 控制台操作会话 |
| 嵌入 | Embedding | 将文本或多媒体映射到向量空间的表示。 | [0.12, -0.03, …] |
| ONNX | ONNX | 跨框架交换机器学习模型的开放格式。 | .onnx 模型文件 |
| 向量数据库 | Vector Database | 以近似最近邻检索向量的专用存储。 | ANN 索引库 |
| 重排 | Rerank | 对初检结果按相关性再次排序。 | 初检后再排序 |
| 模型清单 | Model Manifest | 描述模型制品组成与用途的清单。 | model card + 文件列表 |
| 权重 | Weights | 模型可学习参数的数值集合。 | .safetensors / GGUF |
