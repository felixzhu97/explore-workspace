# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 认证 | Auth | 核实主体身份或授予访问权限的过程。 | 口令登录；OAuth 授权码 |
| 用户 | User | 可登录并被授权的人员主体。 | 登录主体 alice |
| 聊天 | Chat | 参与者之间的会话式交流。 | 私聊「你好」；群聊「项目组」 |
| 消息 | Message | 通信中传递的一条信息单元。 | IM「晚上开会」 |
| 通话 | Call | 实时语音或视频通话。 | WebRTC 一对一语音 |
| 群组 | Group | 由多名用户组成的社交群体。 | 「产品组」群聊 |
| 帖子 | Post | 用户发布到信息流的一条内容。 | 图文帖「周末出游」 |
| 信息流 | Feed | 按时间或相关性排列的内容流。 | 首页时间线刷帖 |
| 短视频 | Reels | 短视频形态的内容流。 | 15 秒竖屏短视频 |
| 探索 | Explore | 发现与浏览内容的入口。 | 探索页九宫格 |
| 评论 | Comment | 对内容发表的附言。 | 帖下回复「赞！」 |
| 关注 | Follow | 订阅另一用户更新的关系。 | alice 关注 bob |
| 搜索 | Search | 按条件查找信息的过程。 | 搜索「旅行」帖子 |
| 通知 | Notification | 向用户告知事件的提示。 | 「bob 赞了你的帖」 |
| 媒体上传 | Media | 图像、音视频等多媒体内容。 | 上传 JPG / MP4 |
| 状态 | Status | 用户短暂展示的状态更新。 | 用户短暂展示的状态更新 |
| 图片生成 | Image Gen | 由提示生成图像。 | 由提示生成图像 |
| 视频生成 | Video Gen | 由提示生成视频。 | 由提示生成视频 |
| 语音合成 | Voice Gen | 由文本生成语音。 | 由文本生成语音 |
| 视觉审核 | Vision | 对图像或视频进行理解与分析。 | NSFW 审核打标 |
| 分析 | Analytics | 对使用与业务数据的度量分析。 | 对使用与业务数据的度量分析 |
| 广告 | Ads | 面向用户投放的广告。 | 面向用户投放的广告 |
| 管理 | Admin | 系统管理与运营能力。 | 运营后台管理员 |
| 本地 AI | AI Chat | 与模型进行的文本对话。 | 与模型进行的文本对话 |
| Explore AI 代理 | Explore AI BFF | 面向客户端聚合 AI 能力的后端中间层。 | 面向客户端聚合 AI 能力的后端中间层 |
| 健康检查 | Health | 系统可用性与就绪状态的检查。 | 系统可用性与就绪状态的检查 |

## Preferred Terms | 术语表

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| Chat | Chat | 参与者之间的会话式交流。 | 私聊「你好」；群聊「项目组」 |
| 封面 URL | Cover URL | 媒体内容的封面图像地址。 | 媒体内容的封面图像地址 |
| 信息流条目 | Feed Entry | 信息流中的一条内容引用。 | 信息流中的一条内容引用 |
| 互动 | Engagement | 用户与内容的互动行为。 | 用户与内容的互动行为 |
| Explore AI BFF | Explore AI BFF | 面向客户端聚合 AI 能力的后端中间层。 | 面向客户端聚合 AI 能力的后端中间层 |
| 客户端身份 | Client Identity | 客户端的稳定身份标识。 | App / Web 客户端标识 |
| 主目的地 | Primary Destination | 应用主导航的目标位置。 | 应用主导航的目标位置 |
| AIP REST | AIP REST | 遵循 Google AIP 惯例的资源导向 HTTP API。 | GET /v1/orders/{id} |
| IAM 模块范围 | IAM Module Scope | 访问令牌上约束模块能力的范围声明。 | 访问令牌上约束模块能力的范围声明 |
| RPC 状态 | RpcStatus | 表示远程调用错误的状态对象。 | 表示远程调用错误的状态对象 |
| 分页令牌 | Page Token | 用于继续分页的不透明令牌。 | pageToken=abc 翻页 |
| 自定义方法 | Custom Method | 在标准 CRUD 之外以动词命名的资源操作。 | 在标准 CRUD 之外以动词命名的资源操作 |
| 搜索范围 | Search Scope | 搜索所覆盖的对象类型范围。 | 搜索所覆盖的对象类型范围 |
| 语音合成目标语言 | Voice Gen Target Language | 语音合成的目标语言。 | 语音合成的目标语言 |
| 语音翻译目标语言 | Voice Translate Target Language | 语音翻译的目标语言。 | 语音翻译的目标语言 |
| 客户端消息 ID | Client Message ID | 由发送方生成的消息幂等标识。 | msg_01H… |
| 消息投递状态 | Message Delivery Status | 消息从发送到已读的投递状态。 | sent → delivered → read |
| 会话 ID | Chat Id | 会话的唯一标识。 | sess_01HXYZ… |
| 消息 ID | Message Id | 消息的唯一标识。 | msg_01H… |
| 本地聊天投影 | Local Chat Projection | 客户端保存的会话与消息只读投影。 | 客户端保存的会话与消息只读投影 |
| 聊天线程 | Chat Thread | 单一会话的消息时间线。 | 单一会话的消息时间线 |
| 会话目录 | Chat Catalog | 会话列表及其预览。 | 左侧会话列表 |
| 灌库 | Hydrate | 用权威数据填充本地投影。 | 用权威数据填充本地投影 |
| 接受实时消息 | Accept Live Message | 将实时消息纳入会话时间线。 | 入站实时消息被接受 |
| 客户端领域事件 | Domain Event (client) | 领域中已发生事实的记录。 | OrderPaid；UserProvisioned |
| IM 契约 | IM Contract | 即时通讯跨端消息与会话的协议契约。 | 即时通讯跨端消息与会话的协议契约 |
| 不可变基类 | Abstract Immutable | 创建后不可变实体的基类型。 | 创建后不可变基类 |
| 实体基类 | Abstract Entity | 可变实体的持久化基类型。 | 带 @Version 的可变基类 |
| 聚合根基类 | Abstract Aggregate Root | 聚合根实体的基类型。 | 聚合根实体的基类型 |
| 参与者基类 | Abstract Participant | 会话或群组成员的基类型。 | 会话或群组成员的基类型 |
| 嵌入值对象基类 | Abstract Embeddable | 可嵌入值对象的基类型。 | @Embeddable Money |
| 软删除 | Soft Delete | 标记删除而不立即物理清除记录。 | 标记删除而不立即物理清除记录 |
| 确保参与者 | Ensure Participant | 确认主体属于会话参与者。 | 保证发送方在会话中 |
| 断言可发送 | Assert Sendable By | 断言发送方有权向该会话发信。 | 断言当前用户可发送 |
| Prisma 仓储基类 | Abstract Prisma Repository | 基于 Prisma 的仓储基类型。 | 按 id 查找 / 保存 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 会话参与者 | Chat Participant | 属于某一会话并具有角色的用户成员。 | alice 在会话中 |
| 群组成员 | Group Participant | 属于某一社交群组的用户成员。 | alice ∈「工程」组 |
| 会话标题 | Chat Title | 会话的显示名称。 | 「关于年假政策」 |
| 会话类型 | Chat Type | 私聊、群聊等会话分类。 | DM / GROUP |
| 通话状态 | Call Status | 通话从发起、接通到结束所处的状态。 | 通话从发起、接通到结束所处的状态 |
| 消息正文 | Message Body | 消息所承载的文本或结构化内容。 | 正文「晚上开会」 |
| 消息类型 | Message Type | 文本、媒体等消息形态分类。 | text / image / system |
| 消息反应 | Message Reaction | 用户对消息作出的表情或态度回应。 | 对消息点 👍 |
| 媒体类型 | Media Type | 图像、视频、音频等内容类型。 | 图像、视频、音频等内容类型 |
| 发送方标识 | Sender Id | 发出消息的用户标识。 | 发出消息的用户标识 |
| 头像地址 | Avatar URL | 用户头像资源的网络地址。 | 用户头像资源的网络地址 |
| 认证令牌对 | Auth Tokens | 登录后用于访问与续期的令牌组合。 | 登录后用于访问与续期的令牌组合 |
| 用户关注 | User Follow | 用户之间单向关注关系。 | alice 关注 bob |
| 客户端消息号 | ClientMsgId | 发送端生成的消息幂等编号。 | 客户端去重 id |
| 消息已读 | Message Read | 接收方已阅读某消息的事实。 | 已读回执 |
| 消息已接受 | Message Accepted | 会话时间线已接纳某消息的事实。 | 入站消息验收通过 |
| 投递状态变更 | Delivery Status Changed | 消息投递状态发生变化的事实。 | 消息投递状态发生变化的事实 |
| 线程已灌库 | Thread Hydrated | 本地会话时间线已用权威数据填满的事实。 | 线程消息已灌入内存 |
| 目录已替换 | Catalog Replaced | 会话目录被完整替换的事实。 | 目录快照整体替换 |
| 预览已变更 | Chat Preview Changed | 会话列表预览信息已更新的事实。 | 会话预览文案更新 |
| 末条消息已变更 | Last Message Changed | 会话最后一条消息预览已更新的事实。 | 末条消息指针更新 |
