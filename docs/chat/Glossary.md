# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 认证 | Auth | 核实主体身份或授予访问权限的过程。 | 登录 / OAuth |
| 用户 | User | 可登录并被授权的人员主体。 | 登录主体 |
| 聊天 | Chat | 参与者之间的会话式交流。 | 私聊或群会话 |
| 消息 | Message | 通信中传递的一条信息单元。 | 一条 IM 消息 |
| 通话 | Call | 实时语音或视频通话。 | WebRTC 语音通话 |
| 群组 | Group | 由多名用户组成的社交群体。 | 社交群 / 权限组 |
| 帖子 | Post | 用户发布到信息流的一条内容。 | 信息流帖子 |
| 信息流 | Feed | 按时间或相关性排列的内容流。 | 首页信息流 |
| 短视频 | Reels | 短视频形态的内容流。 | 短视频流 |
| 探索 | Explore | 发现与浏览内容的入口。 | 探索网格 |
| 评论 | Comment | 对内容发表的附言。 | 帖下评论 |
| 关注 | Follow | 订阅另一用户更新的关系。 | A 关注 B |
| 搜索 | Search | 按条件查找信息的过程。 | 全文 / 用户搜索 |
| 通知 | Notification | 向用户告知事件的提示。 | 点赞提醒 |
| 媒体上传 | Media | 图像、音视频等多媒体内容。 | JPEG / MP4 上传 |
| 状态 | Status | 用户短暂展示的状态更新。 | 24h 状态 |
| 图片生成 | Image Gen | 由提示生成图像。 | 文生图任务 |
| 视频生成 | Video Gen | 由提示生成视频。 | 文生视频任务 |
| 语音合成 | Voice Gen | 由文本生成语音。 | TTS 试听 |
| 视觉审核 | Vision | 对图像或视频进行理解与分析。 | 审核 / 打标 |
| 分析 | Analytics | 对使用与业务数据的度量分析。 | 收益 / 回撤曲线 |
| 广告 | Ads | 面向用户投放的广告。 | 信息流广告位 |
| 管理 | Admin | 系统管理与运营能力。 | 运营后台 |
| 本地 AI | AI Chat | 与模型进行的文本对话。 | Ollama 本地对话 |
| Explore AI 代理 | Explore AI BFF | 面向客户端聚合 AI 能力的后端中间层。 | /api/v1/ai/explore |
| 健康检查 | Health | 系统可用性与就绪状态的检查。 | /actuator/health |

## Preferred Terms | 术语表

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| Chat | Chat | 参与者之间的会话式交流。 | 私聊或群会话 |
| 封面 URL | Cover URL | 媒体内容的封面图像地址。 | 视频封面图 URL |
| 信息流条目 | Feed Entry | 信息流中的一条内容引用。 | Feed 中的 Post 引用 |
| 互动 | Engagement | 用户与内容的互动行为。 | 点赞数 / 收藏数 |
| Explore AI BFF | Explore AI BFF | 面向客户端聚合 AI 能力的后端中间层。 | /api/v1/ai/explore |
| 客户端身份 | Client Identity | 客户端的稳定身份标识。 | 如：客户端身份 |
| 主目的地 | Primary Destination | 应用主导航的目标位置。 | feed；chat；explore |
| AIP REST | AIP REST | 遵循 Google AIP 惯例的资源导向 HTTP API。 | AIP-121/136/158 |
| IAM 模块范围 | IAM Module Scope | 访问令牌上约束模块能力的范围声明。 | write:chat_messaging |
| RPC 状态 | RpcStatus | 表示远程调用错误的状态对象。 | { code, message, details[] } |
| 分页令牌 | Page Token | 用于继续分页的不透明令牌。 | next_page_token |
| 自定义方法 | Custom Method | 在标准 CRUD 之外以动词命名的资源操作。 | POST …/users/{id}:disable |
| 搜索范围 | Search Scope | 搜索所覆盖的对象类型范围。 | posts；users |
| 语音合成目标语言 | Voice Gen Target Language | 语音合成的目标语言。 | 如：语音合成目标语言 |
| 语音翻译目标语言 | Voice Translate Target Language | 语音翻译的目标语言。 | 如：语音翻译目标语言 |
| 客户端消息 ID | Client Message ID | 由发送方生成的消息幂等标识。 | 客户端幂等号 |
| 消息投递状态 | Message Delivery Status | 消息从发送到已读的投递状态。 | sent；delivered；read |
| 会话 ID | Chat Id | 会话的唯一标识。 | chat-… |
| 消息 ID | Message Id | 消息的唯一标识。 | msg-… |
| 本地聊天投影 | Local Chat Projection | 客户端保存的会话与消息只读投影。 | 客户端只读副本 |
| 聊天线程 | Chat Thread | 单一会话的消息时间线。 | 单会话消息轴 |
| 会话目录 | Chat Catalog | 会话列表及其预览。 | 会话列表 |
| 灌库 | Hydrate | 用权威数据填充本地投影。 | REST 灌入本地投影 |
| 接受实时消息 | Accept Live Message | 将实时消息纳入会话时间线。 | 如：接受实时消息 |
| 客户端领域事件 | Domain Event (client) | 领域中已发生事实的记录。 | OrderPaid；MessageAccepted |
| IM 契约 | IM Contract | 即时通讯跨端消息与会话的协议契约。 | im-contract/openapi.yaml |
| 不可变基类 | Abstract Immutable | 创建后不可变实体的基类型。 | @MappedSuperclass 不可变基类 |
| 实体基类 | Abstract Entity | 可变实体的持久化基类型。 | @MappedSuperclass 可变基类 |
| 聚合根基类 | Abstract Aggregate Root | 聚合根实体的基类型。 | 聚合根基类型 |
| 参与者基类 | Abstract Participant | 会话或群组成员的基类型。 | 映射超类 / 基类型 |
| 嵌入值对象基类 | Abstract Embeddable | 可嵌入值对象的基类型。 | @Embeddable 值对象基类 |
| 软删除 | Soft Delete | 标记删除而不立即物理清除记录。 | deleted=true |
| 确保参与者 | Ensure Participant | 确认主体属于会话参与者。 | 如：确保参与者 |
| 断言可发送 | Assert Sendable By | 断言发送方有权向该会话发信。 | 如：断言可发送 |
| Prisma 仓储基类 | Abstract Prisma Repository | 基于 Prisma 的仓储基类型。 | 映射超类 / 基类型 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 会话参与者 | Chat Participant | 属于某一会话并具有角色的用户成员。 | 会话成员 + 角色 |
| 群组成员 | Group Participant | 属于某一社交群组的用户成员。 | 群成员 |
| 会话标题 | Chat Title | 会话的显示名称。 | 周末聚餐 |
| 会话类型 | Chat Type | 私聊、群聊等会话分类。 | DIRECT；GROUP |
| 通话状态 | Call Status | 通话从发起、接通到结束所处的状态。 | RINGING；ACTIVE；ENDED |
| 消息正文 | Message Body | 消息所承载的文本或结构化内容。 | 文本/媒体载荷 |
| 消息类型 | Message Type | 文本、媒体等消息形态分类。 | TEXT；IMAGE |
| 消息反应 | Message Reaction | 用户对消息作出的表情或态度回应。 | 👍 |
| 媒体类型 | Media Type | 图像、视频、音频等内容类型。 | image/jpeg |
| 发送方标识 | Sender Id | 发出消息的用户标识。 | user-… |
| 头像地址 | Avatar URL | 用户头像资源的网络地址。 | https://…/avatar.png |
| 认证令牌对 | Auth Tokens | 登录后用于访问与续期的令牌组合。 | access + refresh |
| 用户关注 | User Follow | 用户之间单向关注关系。 | follower → followee |
| 客户端消息号 | ClientMsgId | 发送端生成的消息幂等编号。 | 客户端幂等号 |
| 消息已读 | Message Read | 接收方已阅读某消息的事实。 | 已读回执 |
| 消息已接受 | Message Accepted | 会话时间线已接纳某消息的事实。 | 写入时间线 |
| 投递状态变更 | Delivery Status Changed | 消息投递状态发生变化的事实。 | 状态枚举值 |
| 线程已灌库 | Thread Hydrated | 本地会话时间线已用权威数据填满的事实。 | 如：线程已灌库 |
| 目录已替换 | Catalog Replaced | 会话目录被完整替换的事实。 | 如：目录已替换 |
| 预览已变更 | Chat Preview Changed | 会话列表预览信息已更新的事实。 | 如：预览已变更 |
| 末条消息已变更 | Last Message Changed | 会话最后一条消息预览已更新的事实。 | 如：末条消息已变更 |
