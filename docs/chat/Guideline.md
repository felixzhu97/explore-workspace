# Guideline

## Introduce

Chat 将社交连接带入日常：发布 Post、浏览 Feed 与 Reels、在 Explore 发现他人、在 Chat 私信、发起 Call——简洁、清晰。

社交即时通讯产品规定：如何发帖、如何发现、如何持续对话、如何保障安全。坚持单一 API 边界（仓库根 Spring Boot）、稳定 Preferred Term，以及与持久历史互补的实时投递。本指南约束体验设计，使人始终可控、集成始终可互操作。

## Best practices

**坚持单一客户端 API 边界。**

Web、Mobile、Admin 经 Spring `/api/v1`（REST 与 Socket.IO）获取 Feed、Chat、Call、Media 与 AI 能力。客户端不得直连 image-playground、speech、video、vision、recommendation、RAG 或 Explore AI。上游凭证只留在服务端。

**让人掌控对话与发布状态。**

优先可见操作——发送、重试、挂断、离开 Group、打开 Notification——而非静默自动化。生成辅助创作时，须在发布或发送前允许关闭、重试或编辑。

**默认保护私密会话。**

Message 历史、Call 状态与个人 Media 仅在授权会话内。仅请求功能所需的相机、麦克风与文件权限。

**持久化历史；实时投递为补充。**

Socket.IO 提升在线感知与延迟。API 上的 Chat 与 Message 存储仍是重连后的权威源——流式事件须与持久读对账，避免缺口被误认为静默丢数据。

**分离 Call 信令与媒体。**

API 负责邀请、振铃、接听、挂断。WebRTC 承载音视频。信令成功而媒体失败时，须给出可区分反馈。

**仅在价值明确时提供生成能力。**

可选 AI 旁路不可用时，Feed、Chat 与 Calls 仍须可用。

**统一使用 Preferred Term。**

Feed、Post、Chat、Message、Call、Notification 等术语出自 Glossary。禁止在 UI 与 API 间用同义拆词破坏统一语言。

## Auth and User

### User

**将 User 视为长期主体。** 显示名与头像可变。Follow、Chat 成员与 Call 参与者挂靠稳定、可禁用的 User。会话会过期；账号仍可审计。

### JWT

**在 HTTPS 上使用短时 JWT 认证。** 优先 Bearer（[RFC 6750](https://datatracker.ietf.org/doc/html/rfc6750)）。访问令牌不得放查询串。严格校验 `iss`、`aud`、`exp` 与算法（[RFC 7519](https://datatracker.ietf.org/doc/html/rfc7519)、[RFC 8725](https://datatracker.ietf.org/doc/html/rfc8725)）。刷新与登出须有意轮换或作废访问令牌。参见 [OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)。

### Follow

**将 Follow 建模为显式社交边。** 粉丝与关注列表与 API 对账。它们是授权相关关系，不是装饰性 UI 状态。[DeepWalk](https://arxiv.org/abs/1403.6652) 说明社交边如何支撑超越手调规则的发现；大型产品常以专用存储服务社交图（如 Meta [TAO](https://www.usenix.org/conference/atc13/technical-sessions/presentation/bronson)）。

## Feed and discovery

### Post

**将 Post 视为可持久分享单元。** 文本、`mediaUrls` 与 Cover URL 属于 Post。Feed Entry 引用 Post——禁止另造仅客户端卡片模型。

### Feed

**将 Feed 呈现为首页时间线。** 用户经 REST `GET /api/v1/posts/feed`（Reels 经 `GET /api/v1/posts/reels`）接触 Post。排序与缓存可改顺序；时间线须连贯、可刷新。经典协同过滤与因子分解（[Item-based Collaborative Filtering](https://dl.acm.org/doi/10.1145/371920.372071)、[Matrix Factorization Techniques for Recommender Systems](https://dl.acm.org/doi/10.1109/MC.2009.263)）支撑后续深度模型。工业排序谱系含 [Deep Neural Networks for YouTube Recommendations](https://research.google/pubs/pub45530/) 与 [Wide & Deep Learning for Recommender Systems](https://arxiv.org/abs/1606.07792)。

### Reels

**将 Reels 保持为连续短视频。** GraphQL `reels` 优化竖屏观看。禁止把 Reels 并入 Explore 或 Feed。会话与序列推荐如 [GRU4Rec](https://arxiv.org/abs/1511.06939)、[SASRec](https://arxiv.org/abs/1808.09781) 说明近期观看如何驱动下一候选集。

### Explore

**将 Explore 保持为发现面。** Explore 网格用于浏览与发现首页 Feed 之外的内容。经典两阶段系统分离召回与排序（[YouTube DNN](https://research.google/pubs/pub45530/)）；大规模近邻检索（[FAISS](https://arxiv.org/abs/1702.08734)）常支撑第一阶段。Explore 职责须与 Feed 个性化分离。

### Status

**将 Status 视为 24 小时短暂内容。** 禁止与持久 Post 混用生命周期规则。

### Engagement

**使 Engagement 显式。** 点赞、收藏、Comment、Follow 与 API 对账。乐观 UI 是呈现，不是权威源。互动标签是工业推荐的监督信号（[YouTube DNN](https://research.google/pubs/pub45530/)、[Wide & Deep](https://arxiv.org/abs/1606.07792)、[DLRM](https://arxiv.org/abs/1906.00091)）。

### Media

**经 API 上传 Media 后再附着。** 发帖与 Chat 附件使用 Media 端点。大文件须展示进度与失败。Vision 经 API——不安全上传失败闭合；推荐可降级，安全不可。多模态编码器如 [CLIP](https://arxiv.org/abs/2103.00020) 说明视觉模型如何支撑审核与打标。

### Search

**按已文档化范围搜索。** Search `type` 为 `posts`、`users`、`hashtags`。UI「全部」仅客户端聚合——不得作为 API type。[DSSM](https://www.microsoft.com/en-us/research/publication/learning-deep-structured-semantic-models-for-web-search-using-clickthrough-data/) 说明查询与文档如何共享嵌入空间。

### Primary Destination

**主目的地须一目了然。** 跨端导航身份为 `feed`、`chat`、`reels`、`explore`、`user`、`search`；path 与 tab 映射可因端而异。

## Messaging

### Chat

**将 Chat 视为会话容器。** 会话列表须在重连后仍在，并作为 Message 历史入口。大规模聊天写路径见 [How Discord Stores Billions of Messages](https://discord.com/blog/how-discord-stores-billions-of-messages)。

### Message

**持久化 Message；经 Socket.IO 投递实时更新。** 空正文不渲染气泡。重连后须将流式事件与 API 上 Message 历史对账。传输基于 [WebSocket (RFC 6455)](https://datatracker.ietf.org/doc/html/rfc6455)；Chat 用 [Socket.IO](https://socket.io/docs/v4/) 承载 Message、在线状态与 Notification 信号。

### Group

**将 Group 成员资格视为授权边界。** 仅成员收发群 Message。移除后须停止继续投递。

### Notification

**呈现可行动的 Notification。** 打开通知抽屉即知变更——Message、Follow、Comment——而非仅靠不透明角标。优先命名事件如 `notification:new`。

### Delivery

**按不完美网络设计。** 禁止暗示尚未发生的投递。行业投递与在线模型见 [XMPP Core (RFC 6120)](https://datatracker.ietf.org/doc/html/rfc6120)、[XMPP IM (RFC 6121)](https://datatracker.ietf.org/doc/html/rfc6121)、[Matrix Client-Server API](https://spec.matrix.org/latest/client-server-api/)。若纳入端到端或群加密，优先经审设计：[Signal Protocol](https://signal.org/docs/)、[Double Ratchet](https://signal.org/docs/specifications/doubleratchet/)（形式化见 [A Formal Security Analysis of the Signal Messaging Protocol](https://eprint.iacr.org/2016/1013)）、[Messaging Layer Security (RFC 9420)](https://datatracker.ietf.org/doc/html/rfc9420)、[WhatsApp Encryption Overview](https://www.whatsapp.com/security/WhatsApp-Security-Whitepaper.pdf)。

## Calls

### Signaling

**经 API 信令 Call。** 邀请、振铃、接听、拒绝、挂断使用 Call API 与 Socket.IO 网关。振铃但未建媒体，与拒绝邀请不同。

### WebRTC

**用 WebRTC 承载 Call 媒体。** 音视频走 IM/RTC 客户端路径（[WebRTC Overview RFC 8825](https://datatracker.ietf.org/doc/html/rfc8825)、[JSEP RFC 9429](https://datatracker.ietf.org/doc/html/rfc9429)、[SDP RFC 8866](https://datatracker.ietf.org/doc/html/rfc8866)；[MDN WebRTC API](https://developer.mozilla.org/en-US/docs/Web/API/WebRTC_API)）。优先具体状态，而非单一未决「Connecting…」。超 1:1 规模时，行业语音栈常用 SFU（如 [How Discord Handles Two and Half Million Concurrent Voice Users](https://discord.com/blog/how-discord-handles-two-and-half-million-concurrent-voice-users-using-webrtc)）。

## Generative assistance

**生成能力与 Explore AI 一律经 API。** 图片、视频、语音与 AI Chat 可辅助创作，且均为可选。披露辅助内容；旁路宕机时 Feed、Chat、Calls 仍须可用。共同基础含 Transformer（[Attention Is All You Need](https://arxiv.org/abs/1706.03762)）、[Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401)、[Latent Diffusion Models](https://arxiv.org/abs/2112.10752)。

## Related

[Bearer Token (RFC 6750)](https://datatracker.ietf.org/doc/html/rfc6750)

[JWT (RFC 7519)](https://datatracker.ietf.org/doc/html/rfc7519)

[JWT BCP (RFC 8725)](https://datatracker.ietf.org/doc/html/rfc8725)

[WebSocket Protocol (RFC 6455)](https://datatracker.ietf.org/doc/html/rfc6455)

[Socket.IO](https://socket.io/docs/v4/)

[GraphQL](https://graphql.org/learn/)

[WebRTC Overview (RFC 8825)](https://datatracker.ietf.org/doc/html/rfc8825)

[JSEP (RFC 9429)](https://datatracker.ietf.org/doc/html/rfc9429)

[SDP (RFC 8866)](https://datatracker.ietf.org/doc/html/rfc8866)

[MDN WebRTC API](https://developer.mozilla.org/en-US/docs/Web/API/WebRTC_API)

[XMPP Core (RFC 6120)](https://datatracker.ietf.org/doc/html/rfc6120)

[XMPP IM (RFC 6121)](https://datatracker.ietf.org/doc/html/rfc6121)

[Matrix Client-Server API](https://spec.matrix.org/latest/client-server-api/)

[Messaging Layer Security (RFC 9420)](https://datatracker.ietf.org/doc/html/rfc9420)

[Signal Protocol](https://signal.org/docs/)

[Double Ratchet](https://signal.org/docs/specifications/doubleratchet/)

[WhatsApp Encryption Overview](https://www.whatsapp.com/security/WhatsApp-Security-Whitepaper.pdf)

[OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)

[TAO (Facebook social graph)](https://www.usenix.org/conference/atc13/technical-sessions/presentation/bronson)

[Item-based Collaborative Filtering](https://dl.acm.org/doi/10.1145/371920.372071)

[Matrix Factorization Techniques for Recommender Systems](https://dl.acm.org/doi/10.1109/MC.2009.263)

[Deep Neural Networks for YouTube Recommendations](https://research.google/pubs/pub45530/)

[Wide & Deep Learning for Recommender Systems](https://arxiv.org/abs/1606.07792)

[DLRM](https://arxiv.org/abs/1906.00091)

[GRU4Rec](https://arxiv.org/abs/1511.06939)

[SASRec](https://arxiv.org/abs/1808.09781)

[DeepWalk](https://arxiv.org/abs/1403.6652)

[FAISS](https://arxiv.org/abs/1702.08734)

[DSSM](https://www.microsoft.com/en-us/research/publication/learning-deep-structured-semantic-models-for-web-search-using-clickthrough-data/)

[CLIP](https://arxiv.org/abs/2103.00020)

[A Formal Security Analysis of the Signal Messaging Protocol](https://eprint.iacr.org/2016/1013)

[How Discord Stores Billions of Messages](https://discord.com/blog/how-discord-stores-billions-of-messages)

[How Discord Handles Concurrent Voice Users](https://discord.com/blog/how-discord-handles-two-and-half-million-concurrent-voice-users-using-webrtc)

[Attention Is All You Need](https://arxiv.org/abs/1706.03762)

[Retrieval-Augmented Generation](https://arxiv.org/abs/2005.11401)

[Latent Diffusion Models](https://arxiv.org/abs/2112.10752)

## Developer documentation

[Glossary](Glossary.md)

[Spring Boot](https://docs.spring.io/spring-boot/documentation.html)

[Next.js](https://nextjs.org/docs)

[Expo](https://docs.expo.dev/)

[Socket.IO](https://socket.io/docs/v4/)

[WebRTC](https://webrtc.org/)
