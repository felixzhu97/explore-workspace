# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 身份 | Identity | 可被认证与授权的主体身份。 | User / Group / Role |
| 策略 | Policy | 规定主体对资源访问条件的规则集合。 | AllowPolicy 规则集 |
| 临时凭证 | Short-lived credentials | 短有效期的访问凭证。 | Token Exchange JWT |
| 联邦 | Federation | 跨安全域共享认证与属性的机制。 | OIDC 联邦登录 |
| 控制台 | Console | 管理与配置控制台。 | IAM 管理台 |
| 审计 | Audit | 对安全相关活动的可核查记录。 | AdminActivity 记录 |
| 开通 | Provisioning | 供给与生命周期管理身份资源。 | SCIM 开停用户 |
| 横切 | Common | 跨功能共享的横切能力。 | 横切内核 |

## Protocols & Standards | 协议与标准

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| OAuth 2.0 | OAuth 2.0 | 开放授权标准：在不共享口令的前提下委托访问资源。 | 授权码许可；RFC 6749 |
| 开放身份连接 | OpenID Connect (OIDC) | 基于 OAuth 2.0 的身份层，提供 ID Token 与用户信息。 | ID Token；Discovery |
| 授权码 | Authorization Code | 授权码许可中用于兑换令牌的一次性中间凭证。 | 一次性中间码 |
| PKCE | PKCE | OAuth 公共客户端用于防护授权码拦截的扩展。 | code_verifier + S256 |
| 访问令牌 | Access Token | 授权访问受保护资源的令牌。 | Bearer eyJ… |
| 身份令牌 | ID Token | OIDC 中断言终端用户身份的 JWT。 | OIDC 身份断言 JWT |
| 刷新令牌 | Refresh Token | 用于获取新访问令牌的长期凭证。 | offline_access 续期 |
| JWKS | JWKS | JSON Web Key 集合，用于发布验签公钥。 | /.well-known/jwks.json |
| JWK | JWK | 表示密钥材料的 JSON 数据结构（RFC 7517）。 | RSA / EC 公钥 JSON |
| JWT | JWT | 由头部、声明与签名组成的紧凑声明格式（RFC 7519）。 | header.payload.signature |
| 令牌自省 | Token Introspection | 查询令牌状态与元数据的协议（RFC 7662）。 | RFC 7662 |
| OAuth2 客户端认证 | OAuth2 Client Authentication | OAuth2 客户端认证。 | OAuth 流程中的概念 |
| SAML 2.0 | SAML 2.0 | 基于 XML 断言的联邦身份标准。 | SAML Assertion |
| SCIM 2.0 | SCIM 2.0 | 跨域身份资源供给与管理的 HTTP/JSON 标准（RFC 7643/7644）。 | Users；Groups；RFC 7644 |
| SCIM 服务端 | SCIM Service Provider | 对外提供 SCIM 资源接口的服务方。 | /scim/v2 |
| SCIM 客户端 | SCIM Client | 调用 SCIM 服务方以管理身份资源的客户端。 | 出站供给客户端 |
| SCIM 媒体类型 | SCIM Media Type | SCIM 使用的媒体类型。 | SCIM 请求/资源 |
| SCIM 发现端点 | SCIM Discovery | 发现 SCIM 服务能力与模式。 | SCIM 请求/资源 |
| SCIM 过滤 | SCIM Filter | SCIM 查询过滤表达式。 | 请求过滤链中的一环 |
| SCIM 局部更新 | SCIM PATCH | SCIM 部分更新操作。 | SCIM 请求/资源 |
| 列表响应 | List Response | 包含资源列表与分页信息的响应。 | List Response |
| SCIM 错误 | SCIM Error | SCIM 协议错误响应。 | SCIM 请求/资源 |
| 弱 ETag | Weak ETag | 允许语义等价表示共享的弱实体标签。 | Weak ETag |

## Core Security

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| Spring Security | Spring Security | Spring 生态中的认证与授权框架。 | — |
| 安全过滤器链 | Security Filter Chain | 按顺序处理安全关注点的过滤器链。 | Spring Security 过滤链 |
| 授权服务器过滤器链 | Authorization Server Filter Chain | 授权服务器端点的安全过滤器链。 | 认证流程步骤 |
| SCIM 安全过滤器链 | SCIM Security Filter Chain | 保护 SCIM 端点的安全过滤链。 | SCIM 请求/资源 |
| 默认安全过滤器链 | Default Security Filter Chain | 默认的安全过滤器链。 | 安全配置 |
| 认证 | Authentication | 核实主体身份的过程。 | 认证流程步骤 |
| 授权 | Authorization | 判定主体可否对资源执行操作。 | 认证流程步骤 |
| 安全上下文 | Security Context | 当前认证与授权信息的上下文。 | 安全配置 |
| 安全主体 | Principal | 安全上下文中的已认证主体。 | 已认证主体 |
| 授权权限 | Granted Authority | 已授予主体的一条权限。 | 认证流程步骤 |
| 认证入口点 | Authentication Entry Point | 未认证请求的入口处理点。 | 认证流程步骤 |
| 认证成功处理器 | Authentication Success Handler | 认证成功后的处理器。 | 成功/失败回调 |
| 认证失败处理器 | Authentication Failure Handler | 认证失败后的处理器。 | 成功/失败回调 |
| 认证事件 | Authentication Event | 认证过程产生的事件。 | 领域事件实例 |
| 启用 Web 安全 | Enable Web Security | 启用 Web 层安全配置。 | 安全配置 |

## Session & Form Login

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 表单登录 | Form Login | 基于表单提交的口令登录方式。 | 用户名口令表单 |
| 登录页 | Login Page | 登录页面。 | 认证流程步骤 |
| 用户详情服务 | User Details Service | 按用户名加载用户详情的服务。 | 应用服务调用 |
| 用户详情 | User Details | 供认证使用的用户账户详情。 | 用户生命周期操作 |
| 密码编码器 | Password Encoder | 对口令进行编码或校验的组件。 | 口令处理组件 |
| 登出 | Logout | 结束认证会话的过程。 | 结束会话 |
| 会话 | Session | 主体与系统之间的持续交互上下文。 | 登录会话 |

## Multi-Factor Authentication

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 多因素认证 | MFA | 要求多种独立凭证完成认证的机制。 | 口令 + TOTP |
| 因素权限 | Factor Authority | 多因素认证中的一个认证因素权威。 | 认证流程步骤 |
| 一次性令牌 | One-Time Token | 仅可使用一次的令牌。 | 令牌字符串 |
| 通行密钥 | Passkey | 基于公钥的无口令登录凭证。 | 跨设备通行密钥 |
| WebAuthn 凭证 | WebAuthn Credential | WebAuthn 注册的认证器公钥凭证。 | 公钥凭证 |
| TOTP 凭证 | TOTP Credential | 基于时间的一次性口令凭证。 | 6 位时限码 |

## OAuth 2.0 / OIDC (Authorization Server)

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| OAuth2 授权服务器 | OAuth2 Authorization Server | 按 OAuth 2.0 签发访问令牌的授权服务器。 | OAuth 流程中的概念 |
| 授权端点 | Authorization Endpoint | 用户授权并返回授权码或令牌的端点。 | /oauth2/authorize |
| 令牌端点 | Token Endpoint | 颁发或刷新令牌的端点。 | /oauth2/token |
| OIDC 发现文档 | OpenID Provider Configuration | OIDC 发现文档中的提供者配置。 | /.well-known/openid-configuration |
| JWK 集端点 | JWK Set Endpoint | 发布 JSON Web Key 集合的端点。 | JWKS URI |
| 注册客户端仓库 | Registered Client Repository | 持久化已登记 OAuth 客户端的仓库。 | OAuth 客户端字段 |
| 授权服务器设置 | Authorization Server Settings | 授权服务器的运行配置。 | 认证流程步骤 |
| 令牌设置 | Token Settings | 令牌签发与有效期等设置。 | 令牌相关配置 |
| 客户端设置 | Client Settings | OAuth 客户端行为相关设置。 | OAuth 客户端字段 |
| 令牌定制器 | OAuth2 Token Customizer | 定制令牌声明的组件。 | OAuth 流程中的概念 |
| JWT 编码器 | JWT Encoder | 编码并签发 JWT 的组件。 | 令牌/密钥组件 |
| JWT 解码器 | JWT Decoder | 解析并校验 JWT 的组件。 | 令牌/密钥组件 |
| OAuth2 资源服务器 | OAuth2 Resource Server | 校验访问令牌并托管受保护资源的服务器。 | OAuth 流程中的概念 |
| 授权类型 | Authorization Grant Type | OAuth 获取令牌的许可类型。 | 认证流程步骤 |
| 客户端认证方式 | Client Authentication Method | 客户端向令牌端点证明自身身份的方法。 | 认证流程步骤 |
| 重定向 URI | Redirect URI | 授权完成后客户端接收回调的 URI。 | https://app…/callback |
| 范围 | Scope | 访问令牌所授权的权限范围。 | write:ai_chat |
| 范围目录 | Scope Catalog | 可授予范围的目录。 | Scope Catalog |
| GitHub 风格范围 | GitHub-Style Scope | 以资源:权限形式书写的范围声明。 | resource:action |
| 签发者 | Issuer | 签发令牌或断言的权威方。 | iss 声明 |

## Federation (OAuth2 Client)

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| OAuth2 客户端 | OAuth2 Client | 向授权服务器请求并使用令牌访问受保护资源的客户端。 | OAuth 流程中的概念 |
| OAuth2 登录 | OAuth2 Login | 基于 OAuth 2.0 / OIDC 的登录流程。 | OAuth 流程中的概念 |
| 客户端注册 | Client Registration | 在授权服务器登记 OAuth 客户端。 | OAuth 客户端字段 |
| OAuth2 用户服务 | OAuth2 User Service | 加载 OAuth2/OIDC 用户信息的服务。 | 应用服务调用 |
| 联邦链接服务 | Federation Link Service | 维护联邦身份关联的服务。 | 应用服务调用 |
| 用户信息端点 | User Info Endpoint | 返回已认证用户声明的端点。 | /userinfo |

## Method Security & RBAC

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 方法级安全 | Method Security | 方法级访问控制。 | 安全配置 |
| 预授权 | PreAuthorize | 方法执行前的访问表达式校验。 | 认证流程步骤 |
| 拥有角色 | Has Role | 判断主体是否具有指定角色。 | 角色指派 |
| 拥有任一角色 | Has Any Role | 判断主体是否具有任一指定角色。 | 角色指派 |
| 已认证 | Is Authenticated | 判断当前是否已认证。 | 认证流程步骤 |
| IAM 管理员角色 | IAM Admin Role | IAM 管理员角色。 | 角色指派 |
| IAM 审计员角色 | IAM Auditor Role | IAM 审计员角色。 | 角色指派 |
| 普通用户角色 | User Role | 用户所具有的角色。 | 用户生命周期操作 |

## CSRF & HTTP Hardening

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| CSRF 令牌 | CSRF Token | 用于校验请求来自预期站点的防伪令牌。 | 双提交 Cookie |
| Cookie CSRF 仓库 | Cookie CSRF Token Repository | 在 Cookie 中存放 CSRF 令牌的仓库。 | — |
| CSRF 请求处理器 | CSRF Token Request Handler | 从请求中解析 CSRF 令牌的处理器。 | 成功/失败回调 |
| CSRF Cookie 过滤器 | CSRF Cookie Filter | 读写 CSRF Cookie 的过滤器。 | 请求过滤链中的一环 |
| 安全响应头 | Security Headers | 增强浏览器安全的 HTTP 响应头。 | 安全配置 |
| 全部放行 | Permit All | 允许所有主体访问。 | Permit All |

## Persistence & Domain Kernel

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 不可变聚合基类 | Abstract Immutable | 创建后不可变实体的基类型。 | @MappedSuperclass 不可变基类 |
| 可变聚合基类 | Abstract Entity | 可变实体的持久化基类型。 | @MappedSuperclass 可变基类 |
| 类型化标识 | Typed Id | 带类型约束的标识值。 | OrderId；UserId |
| 具名聚合基类 | Abstract Named Entity | 带名称属性的实体基类型。 | 映射超类 / 基类型 |
| 不可变审计聚合基类 | Abstract Audit Event | 审计事件的抽象基类型。 | 领域事件实例 |
| 可嵌入值对象基类 | Abstract Embeddable | 可嵌入值对象的基类型。 | @Embeddable 值对象基类 |
| 域字符串校验 | Domain Strings | 领域字符串的规范化与校验。 | Domain Strings |
| 语义化访问器 | Semantic Accessor | 按语义访问声明或属性的访问器。 | Semantic Accessor |
| 属性转换器 | Attribute Converter | 属性与持久化列之间的类型转换器。 | Attribute Converter |
| 乐观锁 | Optimistic Locking | 用版本号检测并发写冲突的策略。 | Optimistic Locking |
| Liquibase 变更日志 | Liquibase Changelog | 数据库结构变更的变更日志。 | — |
| 模式校验 | Schema Validation | 按模式校验数据结构是否合规。 | Schema Validation |
| 领域事件 | 领域事件 | 领域中已发生事实的记录。 | — |
| 事件处理方法 | Event Handler | 响应领域或应用事件的处理器。 | 成功/失败回调 |

## Provisioning Delivery

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 事件发布登记表 | Event Publication Registry | 跟踪事件发布状态的登记处。 | 名称 → 实现 |
| 应用模块监听器 | Application Module Listener | 监听应用模块事件的组件。 | 功能开关 |
| 未完成事件发布 | Incomplete Event Publication | 尚未完成投递的事件发布记录。 | Incomplete Event Publication 事实 |
| OAuth2 客户端请求拦截器 | OAuth2 Client HTTP Request Interceptor | 为出站请求附加 OAuth2 凭证的拦截器。 | OAuth 流程中的概念 |
| 可重试 | Retryable | 失败后可重试。 | Retryable |

## Security Controls | 安全控制

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| CSRF 防护 | CSRF Protection | 防止跨站请求伪造的防护机制。 | CSRF 防护组件 |
| 会话固定防护 | Session Fixation Protection | 防止会话固定攻击的保护。 | 会话字段 |
| 密码哈希 | Password Hashing | 将口令变换为不可逆摘要以便存储。 | bcrypt / argon2 |
| 停用用户 | Disabled User | 已被停用的用户。 | Disabled User |
| 授权同意 | Consent | 资源所有者对授权的明确同意。 | 授权同意屏 |
| 持久化签名密钥 | Persistent Signing Key | 用于签发令牌的持久密钥。 | Persistent Signing Key |
| 隐式拒绝 | Implicit Deny | 无允许规则时的默认拒绝。 | 无允许则拒绝 |
| 理由码 | Reason Code | 解释决定原因的代码。 | Reason Code |
| 最小权限会话 | Least Privilege Session | 仅授予完成任务所需最小权限的会话。 | 最小权限会话 |

## Shared Value Objects | 共享值对象

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 资源名 | Resource Name | 资源的规范名称路径。 | users/123 |
| 用户资源名 | User Resource Name | 用户资源的规范名称。 | 用户生命周期操作 |
| 角色资源名 | Role Resource Name | 角色资源的规范名称。 | 角色指派 |
| 主体 ID | Principal ID | 主体的标识。 | Principal ID 值 |
| 资源 | Resource | 受访问控制保护的对象。 | Resource |
| 效力 | Effect | 可组合的视觉或交互动效。 | Effect |
| 显式拒绝理由 | Explicit Deny Reason | 显式拒绝的原因说明。 | Explicit Deny Reason |
| 显式允许理由 | Explicit Allow Reason | 显式允许的原因说明。 | Explicit Allow Reason |
| 隐式拒绝理由 | Implicit Deny Reason | 默认拒绝的原因说明。 | Implicit Deny Reason |

## Policy | 策略

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 权限 | Permission | 对特定操作或资源的允许权。 | users.create |
| 允许策略 | Allow Policy | 显式允许访问的策略。 | 策略文档/规则 |
| 策略语句 | Policy Statement | 策略中的一条允许或拒绝陈述。 | Policy Statement |
| 策略绑定 | Policy Assignment | 将策略绑定到主体或资源的关系。 | Policy Assignment |
| 访问元组 | Access Tuple | 主体、操作与资源组成的访问三元组。 | (principal, action, resource) |
| 访问决策 | Access Decision | 一次访问控制评估的结论。 | Access Decision |
| 策略引擎 | Policy Engine | 评估策略并输出访问决定的引擎。 | Policy Engine |
| 允许策略已创建事件 | Allow Policy Created Event | 允许策略已创建的事实。 | 领域事件实例 |
| 策略已绑定事件 | Policy Attached Event | 策略已绑定的事实。 | 领域事件实例 |
| 权限已创建事件 | Permission Created Event | 权限已创建的事实。 | 领域事件实例 |
| 访问已评估事件 | Access Evaluated Event | 访问已被评估的事实。 | 领域事件实例 |

## Zero Trust | 零信任

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 零信任 | Zero Trust | 不默认信任网络位置、持续验证访问的安全模型（NIST SP 800-207）。 | NIST SP 800-207 |
| 策略决策点 | Policy Decision Point (PDP) | 评估访问策略并作出允许/拒绝决定的组件。 | 评估 Allow/Deny |
| 策略执行点 | Policy Enforcement Point (PEP) | 执行访问决定、拦截未授权请求的组件。 | 拦截未授权请求 |
| 服务身份 | Service Identity | 代表服务本身的机器身份。 | Service Identity |
| 令牌受众 | Token Audience | 令牌预期接收方的声明。 | 令牌相关配置 |
| 令牌交换 | Token Exchange | 将既有令牌兑换为其他令牌的协议（RFC 8693）。 | RFC 8693 |
| 发送方约束令牌 | Sender-Constrained Token | 绑定到持有者密钥、限制被盗用的令牌。 | 令牌字符串 |

## Identity | 身份

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 用户 | User | 可登录并被授权的人员主体。 | 登录主体 |
| 用户状态 | User Status | 用户账户的状态。 | 状态枚举值 |
| 启用用户 | Enable User | 启用用户。 | Enable User |
| 禁用用户 | Disable User | 停用用户。 | Disable User |
| 修改邮箱 | Change Email | 变更电子邮箱。 | Change Email |
| 创建用户 | Create User | 创建用户。 | Create User |
| 创建联邦用户 | Create Federated User | 创建联邦用户。 | Create Federated User |
| 外部 ID | External ID | 外部系统中的对应标识。 | IdP 侧用户 id |
| 显示名 | Display Name | 用于展示的名称。 | 展示名 |
| 开通用户 | Provision User | 供给用户资源。 | 视觉能力调用 |
| 更新资料 | Update Profile | 更新主体资料。 | Update Profile |
| 修改外部 ID | Change External ID | 变更外部标识。 | Change External ID 值 |
| 组 | Group | 可集体授予权限的主体集合。 | 社交群 / 权限组 |
| 组成员 | Group Member | 组的成员。 | 组成员操作 |
| 开通组 | Provision Group | 供给组资源。 | 组成员操作 |
| 重命名组 | Rename Group | 重命名组。 | 组成员操作 |
| 替换成员 | Replace Members | 替换组成员。 | Replace Members |
| 用户已开通事件 | User Provisioned Event | 用户已供给的事实。 | 领域事件实例 |
| 用户资料已变更事件 | User Profile Changed Event | 用户资料已变更的事实。 | 领域事件实例 |
| 用户已停用事件 | User Deactivated Event | 用户已停用的事实。 | 领域事件实例 |
| 用户已恢复事件 | User Reactivated Event | 用户已重新启用的事实。 | 领域事件实例 |
| 用户密码已重置事件 | User Password Reset Event | 用户口令已重置的事实。 | 领域事件实例 |
| 用户已分配角色事件 | User Role Assigned Event | 用户角色已分配的事实。 | 领域事件实例 |
| 用户已解除角色事件 | User Role Unassigned Event | 用户角色已解除的事实。 | 领域事件实例 |
| 用户删除请求事件 | User Deletion Requested Event | 已请求删除用户的事实。 | 领域事件实例 |
| 用户已删除事件 | User Deleted Event | 用户已删除的事实。 | 领域事件实例 |
| 组已创建事件 | Group Created Event | 组已创建的事实。 | 领域事件实例 |
| 组资料已变更事件 | Group Profile Changed Event | 组资料已变更的事实。 | 领域事件实例 |
| 组成员已变更事件 | Group Membership Changed Event | 组成员关系已变更的事实。 | 领域事件实例 |
| 组已删除事件 | Group Deleted Event | 组已删除的事实。 | 领域事件实例 |
| 角色已创建事件 | Role Created Event | 角色已创建的事实。 | 领域事件实例 |
| 角色 | Role | 可赋予主体的权限集合命名。 | IAM_ADMIN |
| 角色绑定 | Role Assignment | 将角色赋予主体的关系。 | 角色指派 |
| 分配角色 | Assign Role | 为主体分配角色。 | 角色指派 |
| 模拟策略 | Impersonation Policy | 允许代理他人身份访问的策略。 | 策略文档/规则 |
| 联邦身份 | Federated Identity | 由外部身份提供者断言的主体身份。 | 外部 IdP subject |
| OAuth 客户端 | OAuth Client | 已登记的 OAuth 客户端应用。 | 已登记 client_id |

## Short-lived credentials | 临时凭证

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 创建短期凭证 | Create Token Exchange | 创建一次令牌兑换。 | 令牌相关配置 |
| 会话已过期 | Session Expired | 会话已过期。 | 会话字段 |
| 角色已扮演事件 | Role Assumed Event | 角色已被承担的事实。 | 领域事件实例 |

## Federation | 联邦与 SSO

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| OIDC 提供方 | OIDC Provider | 实现 OpenID Connect 的身份提供者。 | OIDC Provider |
| 客户端 ID | Client ID | OAuth 或客户端的标识符。 | oauth-client-id |
| 公共客户端 | Public Client | 无法保密客户端凭证的 OAuth 客户端。 | SPA / 原生 App |
| 依赖方 | Relying Party | 依赖身份提供者断言的应用方。 | OIDC RP |
| 创建 OAuth 客户端 | 创建 OAuth 客户端 | 登记新的 OAuth 客户端。 | OAuth 流程中的概念 |
| 恢复 OAuth 客户端 | Restore OAuth Client | 恢复 OAuth 客户端。 | OAuth 流程中的概念 |
| 单点登录 | SSO Login | 一次登录后访问多个相关系统。 | 认证流程步骤 |
| OAuth 客户端已注册事件 | OAuth Client Registered Event | OAuth 客户端已登记的事实。 | 领域事件实例 |
| OAuth 客户端范围已变更事件 | OAuth Client Scopes Changed Event | OAuth 客户端范围已变更的事实。 | 领域事件实例 |

## Audit Aggregates | 审计聚合

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 管理活动 | Admin Activity | 管理员操作的审计记录。 | 管理员操作审计 |
| 数据访问日志 | Data Access Log | 数据访问行为的审计日志。 | 读/写数据审计 |
| 审计主体 | Audit Actor | 引发审计事件的主体。 | 审计记录 |
| 审计目标 | Audit Target | 审计事件所作用的对象。 | 审计记录 |
| 审计结果 | Audit Outcome | 审计事件的结果状态。 | 审计记录 |
| 记录管理活动 | Record Admin Activity | 写入一条管理活动审计。 | Record Admin Activity |
| 记录登录活动 | Record Sign-In Activity | 写入一条登录活动审计。 | Record Sign-In Activity |
| 记录数据访问 | Record Data Access Log | 写入一条数据访问审计。 | Record Data Access Log |
| 管理活动记录器 | Admin Activity Recorder | 记录管理活动的组件。 | 交易流水字段 |

## Provisioning | 开通（SCIM）

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 开通目标 | Provisioning Target | 接受身份供给的目标系统。 | 视觉能力调用 |
| 分配组 | Assignment Group | 用于授权指派的组。 | 组成员操作 |
| 已开通账号 | Provisioned Account | 经供给预先创建的账号。 | SCIM 预置账号 |
| SCIM 开通服务 | SCIM Provisioning Service | 执行 SCIM 供给用例的服务。 | 应用服务调用 |
| SCIM 推送服务 | SCIM Push Service | 向下游推送身份变更的服务。 | 应用服务调用 |
| SCIM 推送监听器 | SCIM Push Listener | 接收身份变更推送的监听器。 | SCIM 请求/资源 |
| SCIM 开通客户端 | SCIM Provisioner Client | 执行出站 SCIM 供给的客户端。 | SCIM 请求/资源 |
| SCIM 出站注册 | SCIM Outbound Registration | 出站 SCIM 目标的登记。 | SCIM 请求/资源 |

## Console | 控制台

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| IAM 控制台 | IAM Console | 身份与访问管理控制台。 | IAM Console |
| OAuth 客户端注册 | OAuth Client Registration | 登记 OAuth 客户端的过程或记录。 | OAuth 流程中的概念 |

## Dev Tooling | 开发工具

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 编排器 | Orchestrator | 协调多个工作者完成任务的组件。 | Orchestrator |
| 子智能体 | Subagent | 在编排下执行子任务的智能体。 | Agent 配置/实例 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 策略规则 | Rule | 策略中单条允许或拒绝条件。 | effect=ALLOW + 条件 |
| 策略已创建事件 | Policy Created Event | 访问策略已被创建的事实。 | 策略创建领域事件 |
| 不可变实体基类 | Abstract Immutable Entity | 带标识与创建时间、创建后不再变更的实体基类型。 | 映射超类 / 基类型 |
| 企业身份提供者 | Enterprise IdP | 企业侧签发联邦断言的身份提供者。 | Enterprise IdP |
