# Glossary | Explore IAM

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 身份 / Identity | 可被认证与授权的主体身份（如：User / Group / Role）。<br>The identity of the entity that can be authenticated and authorized. (e.g. User / Group / Role). |
| 策略 / Policy | 规定主体对资源访问条件的规则集合（如：Allow iam:user:read）。<br>A set of rules that define the conditions under which a principal can access resources. (e.g. Allow iam:user:read). |
| 临时凭证 / Short-lived credentials | 短有效期的访问凭证（如：Token Exchange 短时 JWT）。<br>Access credentials with a short validity period. (e.g. Token Exchange 短时 JWT). |
| 联邦 / Federation | 跨安全域共享认证与属性的机制（如：企业 IdP OIDC 登录）。<br>Mechanisms for sharing authentication and attributes across security domains. (e.g. 企业 IdP OIDC 登录). |
| 控制台 / Console | 管理与配置控制台（如：IAM 管理控制台）。<br>Management and Configuration Console. (e.g. IAM 管理控制台). |
| 审计 / Audit | 对安全相关活动的可核查记录（如：「创建用户」审计日志）。<br>Verifiable records of safety-related activities. (e.g. 「创建用户」审计日志). |
| 开通 / Provisioning | 供给与生命周期管理身份资源（如：IdP 经 SCIM 开停用户）。<br>Provision and manage identity resources throughout their lifecycle. (e.g. IdP 经 SCIM 开停用户). |
| 横切 / Common | 跨功能共享的横切能力（如：跨域共享内核（异常、基类））。<br>Cross-functional, shared, cross-cutting capabilities. (e.g. 跨域共享内核（异常、基类）). |

## Protocols & Standards | 协议与标准

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| OAuth 2.0 / OAuth 2.0 | 开放授权标准：在不共享口令的前提下委托访问资源（如：授权码许可（RFC 6749））。<br>Open Authorization Standard: Delegate access to resources without sharing passwords. (e.g. 授权码许可（RFC 6749）). |
| 开放身份连接 / OpenID Connect (OIDC) | 基于 OAuth 2.0 的身份层，提供 ID Token 与用户信息（如：ID Token + Discovery）。<br>An OAuth 2.0-based identity layer that provides ID tokens and user information. (e.g. ID Token + Discovery). |
| 授权码 / Authorization Code | 授权码许可中用于兑换令牌的一次性中间凭证（如：回调 ?code=SplxlOBe…）。<br>A one-time intermediary credential used in authorization code grants to exchange for tokens. (e.g. 回调 ?code=SplxlOBe…). |
| PKCE / PKCE | OAuth 公共客户端用于防护授权码拦截的扩展（如：code_verifier + S256）。<br>OAuth public client extension for protection against authorization code interception. (e.g. code_verifier + S256). |
| 访问令牌 / Access Token | 授权访问受保护资源的令牌（如：Authorization: Bearer eyJ…）。<br>Token authorizing access to protected resources. (e.g. Authorization: Bearer eyJ…). |
| 身份令牌 / ID Token | OIDC 中断言终端用户身份的 JWT（如：含 sub/iss/aud 的 JWT）。<br>JWT that asserts the identity of the end user in OIDC. (e.g. 含 sub/iss/aud 的 JWT). |
| 刷新令牌 / Refresh Token | 用于获取新访问令牌的长期凭证（如：换发新的 access_token）。<br>Long-term credentials used to obtain a new access token. (e.g. 换发新的 access_token). |
| JWKS / JWKS | JSON Web Key 集合，用于发布验签公钥（如：GET /.well-known/jwks.json）。<br>JSON Web Key set used to publish the signature verification public key. (e.g. GET /.well-known/jwks.json). |
| JWK / JWK | 表示密钥材料的 JSON 数据结构（RFC 7517）（如：RSA 公钥 JSON（kty=RSA））。<br>JSON data structure representing key material (RFC 7517). (e.g. RSA 公钥 JSON（kty=RSA）). |
| JWT / JWT | 由头部、声明与签名组成的紧凑声明格式（RFC 7519）（如：header.payload.signature）。<br>A compact statement format consisting of a header, a statement, and a signature (RFC 7519). (e.g. header.payload.signature). |
| 令牌自省 / Token Introspection | 查询令牌状态与元数据的协议（RFC 7662）（如：RFC 7662 active=true）。<br>Protocol for querying token status and metadata (RFC 7662). (e.g. RFC 7662 active=true). |
| OAuth2 客户端认证 / OAuth2 Client Authentication | OAuth2 客户端认证（如：client_secret_basic）。<br>OAuth2 client authentication. (e.g. client_secret_basic). |
| SAML 2.0 / SAML 2.0 | 基于 XML 断言的联邦身份标准（如：IdP 签发 SAML Assertion）。<br>A federated identity standard based on XML assertions. (e.g. IdP 签发 SAML Assertion). |
| SCIM 2.0 / SCIM 2.0 | 跨域身份资源供给与管理的 HTTP/JSON 标准（RFC 7643/7644）（如：POST /Users（RFC 7644））。<br>HTTP/JSON standard for cross-domain identity resource provisioning and management (RFC 7643/7644). (e.g. POST /Users（RFC 7644）). |
| SCIM 服务端 / SCIM Service Provider | 对外提供 SCIM 资源接口的服务方（如：本服务 /scim/v2）。<br>The service provider that provides the SCIM resource interface to external parties. (e.g. 本服务 /scim/v2). |
| SCIM 客户端 / SCIM Client | 调用 SCIM 服务方以管理身份资源的客户端。<br>A client that calls the SCIM service provider to manage identity resources. (e.g. 调用 SCIM 服务方以管理身份资源的客户端). |
| SCIM 媒体类型 / SCIM Media Type | SCIM 使用的媒体类型（如：application/scim+json）。<br>Media type used by SCIM. (e.g. application/scim+json). |
| SCIM 发现端点 / SCIM Discovery | 发现 SCIM 服务能力与模式（如：ServiceProviderConfig）。<br>Discover SCIM service capabilities and models. (e.g. ServiceProviderConfig). |
| SCIM 过滤 / SCIM Filter | SCIM 查询过滤表达式（如：userName eq "alice"）。<br>SCIM query filter expression. (e.g. userName eq "alice"). |
| SCIM 局部更新 / SCIM PATCH | SCIM 部分更新操作（如：op=replace path=active）。<br>SCIM partial update operation. (e.g. op=replace path=active). |
| 列表响应 / List Response | 包含资源列表与分页信息的响应（如：Resources[] + totalResults）。<br>Response containing a list of resources and pagination information. (e.g. Resources[] + totalResults). |
| SCIM 错误 / SCIM Error | SCIM 协议错误响应（如：status=404 detail=…）。<br>SCIM protocol error response. (e.g. status=404 detail=…). |
| 弱 ETag / Weak ETag | 允许语义等价表示共享的弱实体标签（如：ETag: W/"123456"）。<br>Allows semantically equivalent representations to share a weak entity tag. (e.g. ETag: W/"123456"). |

## Core Security

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| Spring Security / Spring Security | Spring 生态中的认证与授权框架（如：SecurityFilterChain Bean）。<br>Authentication and authorization framework in the Spring ecosystem. (e.g. SecurityFilterChain Bean). |
| 安全过滤器链 / Security Filter Chain | 按顺序处理安全关注点的过滤器链（如：认证→授权过滤链）。<br>A chain of filters that addresses security concerns in sequence. (e.g. 认证→授权过滤链). |
| 授权服务器过滤器链 / Authorization Server Filter Chain | 授权服务器端点的安全过滤器链。<br>Security filter chain for the authorization server endpoint. (e.g. 授权服务器端点的安全过滤器链). |
| SCIM 安全过滤器链 / SCIM Security Filter Chain | 保护 SCIM 端点的安全过滤链（如：认证→授权过滤链）。<br>Security filter chain protecting the SCIM endpoint. (e.g. 认证→授权过滤链). |
| 默认安全过滤器链 / Default Security Filter Chain | 默认的安全过滤器链（如：认证→授权过滤链）。<br>Default security filter chain. (e.g. 认证→授权过滤链). |
| 认证 / Authentication | 核实主体身份的过程（如：校验口令后建立会话）。<br>The process of verifying the identity of an entity. (e.g. 校验口令后建立会话). |
| 授权 / Authorization | 判定主体可否对资源执行操作（如：判定可否调用 /api/orders）。<br>Determine whether the principal can perform operations on resources. (e.g. 判定可否调用 /api/orders). |
| 安全上下文 / Security Context | 当前认证与授权信息的上下文（如：SecurityContextHolder 主体）。<br>Context of current authentication and authorization information. (e.g. SecurityContextHolder 主体). |
| 安全主体 / Principal | 安全上下文中的已认证主体（如：Authentication.getPrincipal()）。<br>Authenticated principal in the security context. (e.g. Authentication.getPrincipal()). |
| 授权权限 / Granted Authority | 已授予主体的一条权限（如：ROLE_ADMIN）。<br>One permission has been granted to the principal. (e.g. ROLE_ADMIN). |
| 认证入口点 / Authentication Entry Point | 未认证请求的入口处理点。<br>Entry point for handling unauthenticated requests. (e.g. 未认证请求的入口处理点). |
| 认证成功处理器 / Authentication Success Handler | 认证成功后的处理器（如：事件/回调处理器）。<br>Handler after successful authentication. (e.g. 事件/回调处理器). |
| 认证失败处理器 / Authentication Failure Handler | 认证失败后的处理器（如：事件/回调处理器）。<br>Handler after authentication failure. (e.g. 事件/回调处理器). |
| 认证事件 / Authentication Event | 认证过程产生的事件（如：领域事件：已发生事实）。<br>Events generated during the authentication process. (e.g. 领域事件：已发生事实). |
| 启用 Web 安全 / Enable Web Security | 启用 Web 层安全配置（如：@EnableWebSecurity）。<br>Enable web-layer security configuration. (e.g. @EnableWebSecurity). |

## Session & Form Login

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 表单登录 / Form Login | 基于表单提交的口令登录方式（如：POST /login 用户名口令）。<br>Password login method based on form submission. (e.g. POST /login 用户名口令). |
| 登录页 / Login Page | 登录页面（如：GET /login 表单页）。<br>Landing Page. (e.g. GET /login 表单页). |
| 用户详情服务 / User Details Service | 按用户名加载用户详情的服务（如：loadUserByUsername("alice")）。<br>Service for loading user details by username. (e.g. loadUserByUsername("alice")). |
| 用户详情 / User Details | 供认证使用的用户账户详情（如：username + authorities）。<br>User account details for authentication. (e.g. username + authorities). |
| 密码编码器 / Password Encoder | 对口令进行编码或校验的组件（如：BCryptPasswordEncoder）。<br>A component that encodes or verifies passwords. (e.g. BCryptPasswordEncoder). |
| 登出 / Logout | 结束认证会话的过程。<br>The process of ending an authentication session. (e.g. 结束认证会话的过程). |
| 会话 / Session | 主体与系统之间的持续交互上下文（如：JSESSIONID 登录会话）。<br>The ongoing context of interaction between the subject and the system. (e.g. JSESSIONID 登录会话). |

## Multi-Factor Authentication

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 多因素认证 / MFA | 要求多种独立凭证完成认证的机制（如：口令 + TOTP 双因素）。<br>A mechanism that requires multiple independent credentials to complete authentication. (e.g. 口令 + TOTP 双因素). |
| 因素权限 / Factor Authority | 多因素认证中的一个认证因素权威。<br>An authentication factor authority in multi-factor authentication. (e.g. 多因素认证中的一个认证因素权威). |
| 一次性令牌 / One-Time Token | 仅可使用一次的令牌（如：邮件魔法链接码）。<br>A token that can only be used once. (e.g. 邮件魔法链接码). |
| 通行密钥 / Passkey | 基于公钥的无口令登录凭证（如：平台认证器通行密钥）。<br>Passwordless login credentials based on public keys. (e.g. 平台认证器通行密钥). |
| WebAuthn 凭证 / WebAuthn Credential | WebAuthn 注册的认证器公钥凭证（如：公钥凭证 credentialId）。<br>WebAuthn-registered authenticator public key credential. (e.g. 公钥凭证 credentialId). |
| TOTP 凭证 / TOTP Credential | 基于时间的一次性口令凭证（如：Authenticator 6 位码）。<br>Time-based one-time password credential. (e.g. Authenticator 6 位码). |

## OAuth 2.0 / OIDC (Authorization Server)

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| OAuth2 授权服务器 / OAuth2 Authorization Server | 按 OAuth 2.0 签发访问令牌的授权服务器（如：签发 access_token 的 AS）。<br>Authorization server that issues access tokens according to OAuth 2.0. (e.g. 签发 access_token 的 AS). |
| 授权端点 / Authorization Endpoint | 用户授权并返回授权码或令牌的端点（如：GET /oauth2/authorize）。<br>Endpoint where the user authorizes and returns the authorization code or token. (e.g. GET /oauth2/authorize). |
| 令牌端点 / Token Endpoint | 颁发或刷新令牌的端点（如：POST /oauth2/token）。<br>Endpoint for issuing or refreshing tokens. (e.g. POST /oauth2/token). |
| OIDC 发现文档 / OpenID Provider Configuration | OIDC 发现文档中的提供者配置（如：/.well-known/openid-configuration）。<br>Provider configuration in the OIDC discovery document. (e.g. /.well-known/openid-configuration). |
| JWK 集端点 / JWK Set Endpoint | 发布 JSON Web Key 集合的端点（如：JWKS URI）。<br>Endpoint that publishes a JSON Web Key Set. (e.g. JWKS URI). |
| 注册客户端仓库 / Registered Client Repository | 持久化已登记 OAuth 客户端的仓库（如：按 client_id 查客户端）。<br>Persist the repository of registered OAuth clients. (e.g. 按 client_id 查客户端). |
| 授权服务器设置 / Authorization Server Settings | 授权服务器的运行配置（如：issuer 与 endpoint 路径）。<br>Operational configuration of the authorization server. (e.g. issuer 与 endpoint 路径). |
| 令牌设置 / Token Settings | 令牌签发与有效期等设置（如：accessTokenTimeToLive=1h）。<br>Settings such as token issuance and validity period. (e.g. accessTokenTimeToLive=1h). |
| 客户端设置 / Client Settings | OAuth 客户端行为相关设置（如：requireProofKey=true）。<br>Settings related to OAuth client behavior. (e.g. requireProofKey=true). |
| 令牌定制器 / OAuth2 Token Customizer | 定制令牌声明的组件（如：JWT 加自定义 claim）。<br>Component for customizing token claims. (e.g. JWT 加自定义 claim). |
| JWT 编码器 / JWT Encoder | 编码并签发 JWT 的组件（如：NimbusJwtEncoder）。<br>Component that encodes and issues JWTs. (e.g. NimbusJwtEncoder). |
| JWT 解码器 / JWT Decoder | 解析并校验 JWT 的组件（如：NimbusJwtDecoder）。<br>Parse and validate the components of a JWT. (e.g. NimbusJwtDecoder). |
| OAuth2 资源服务器 / OAuth2 Resource Server | 校验访问令牌并托管受保护资源的服务器（如：校验 Bearer JWT 的 API）。<br>Server that validates access tokens and hosts protected resources. (e.g. 校验 Bearer JWT 的 API). |
| 授权类型 / Authorization Grant Type | OAuth 获取令牌的许可类型（如：authorization_code）。<br>OAuth permission type for obtaining tokens. (e.g. authorization_code). |
| 客户端认证方式 / Client Authentication Method | 客户端向令牌端点证明自身身份的方法（如：client_secret_post）。<br>The method by which the client proves its identity to the token endpoint. (e.g. client_secret_post). |
| 重定向 URI / Redirect URI | 授权完成后客户端接收回调的 URI（如：https://app.example/callback）。<br>The URI where the client receives the callback after authorization is completed. (e.g. https://app.example/callback). |
| 范围 / Scope | 访问令牌所授权的权限范围（如：openid profile email）。<br>The scope of permissions granted by the access token. (e.g. openid profile email). |
| 范围目录 / Scope Catalog | 可授予范围的目录（如：系统登记 scope 清单）。<br>Directory of grantable scopes. (e.g. 系统登记 scope 清单). |
| GitHub 风格范围 / GitHub-Style Scope | 以资源:权限形式书写的范围声明（如：repo:status；write:ai_chat）。<br>Scope statement written in the form of resource:permission. (e.g. repo:status；write:ai_chat). |
| 签发者 / Issuer | 签发令牌或断言的权威方（如：iss=https://iam.example）。<br>The authority that issues tokens or assertions. (e.g. iss=https://iam.example). |

## Federation (OAuth2 Client)

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| OAuth2 客户端 / OAuth2 Client | 向授权服务器请求并使用令牌访问受保护资源的客户端。<br>A client that requests a token from an authorization server and uses it to access protected resources. (e.g. 向授权服务器请求并使用令牌访问受保护资源的客户端). |
| OAuth2 登录 / OAuth2 Login | 基于 OAuth 2.0 / OIDC 的登录流程（如：授权码回调完成登录）。<br>OAuth 2.0 / OIDC-based login process. (e.g. 授权码回调完成登录). |
| 客户端注册 / Client Registration | 在授权服务器登记 OAuth 客户端（如：登记 client_id=spa-web）。<br>Register the OAuth client with the authorization server. (e.g. 登记 client_id=spa-web). |
| OAuth2 用户服务 / OAuth2 User Service | 加载 OAuth2/OIDC 用户信息的服务（如：IdP profile → 本地用户）。<br>Service for loading OAuth2/OIDC user information. (e.g. IdP profile → 本地用户). |
| 联邦链接服务 / Federation Link Service | 维护联邦身份关联的服务（如：应用层编排用例）。<br>Services that maintain federated identity associations. (e.g. 应用层编排用例). |
| 用户信息端点 / User Info Endpoint | 返回已认证用户声明的端点（如：GET /userinfo 返回 profile）。<br>Returns the endpoint of the authenticated user's statement. (e.g. GET /userinfo 返回 profile). |

## Method Security & RBAC

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 方法级安全 / Method Security | 方法级访问控制。<br>Method-level access control. (e.g. 方法级访问控制). |
| 预授权 / PreAuthorize | 方法执行前的访问表达式校验。<br>Validation of access expressions before method execution. (e.g. 方法执行前的访问表达式校验). |
| 拥有角色 / Has Role | 判断主体是否具有指定角色。<br>Determines whether the subject has the specified role. (e.g. 判断主体是否具有指定角色). |
| 拥有任一角色 / Has Any Role | 判断主体是否具有任一指定角色。<br>Determine whether the subject has any of the specified roles. (e.g. 判断主体是否具有任一指定角色). |
| 已认证 / Is Authenticated | 判断当前是否已认证。<br>Determine whether authentication has already been completed. (e.g. 判断当前是否已认证). |
| IAM 管理员角色 / IAM Admin Role | IAM 管理员角色（如：ROLE_IAM_ADMIN）。<br>IAM Administrator Role. (e.g. ROLE_IAM_ADMIN). |
| IAM 审计员角色 / IAM Auditor Role | IAM 审计员角色（如：ROLE_IAM_AUDITOR）。<br>IAM Auditor Role. (e.g. ROLE_IAM_AUDITOR). |
| 普通用户角色 / User Role | 用户所具有的角色（如：ROLE_USER）。<br>The roles assigned to the user. (e.g. ROLE_USER). |

## CSRF & HTTP Hardening

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| CSRF 令牌 / CSRF Token | 用于校验请求来自预期站点的防伪令牌（如：XSRF-TOKEN 双提交）。<br>Used to verify anti-forgery tokens from expected sites. (e.g. XSRF-TOKEN 双提交). |
| Cookie CSRF 仓库 / Cookie CSRF Token Repository | 在 Cookie 中存放 CSRF 令牌的仓库（如：XSRF-TOKEN Cookie）。<br>The repository for storing CSRF tokens in cookies. (e.g. XSRF-TOKEN Cookie). |
| CSRF 请求处理器 / CSRF Token Request Handler | 从请求中解析 CSRF 令牌的处理器（如：XSRF-TOKEN 双提交）。<br>Handler that parses the CSRF token from the request. (e.g. XSRF-TOKEN 双提交). |
| CSRF Cookie 过滤器 / CSRF Cookie Filter | 读写 CSRF Cookie 的过滤器（如：请求过滤链一环）。<br>Filter for reading and writing CSRF cookies. (e.g. 请求过滤链一环). |
| 安全响应头 / Security Headers | 增强浏览器安全的 HTTP 响应头。<br>HTTP response headers that enhance browser security. (e.g. 增强浏览器安全的 HTTP 响应头). |
| 全部放行 / Permit All | 允许所有主体访问（如：permitAll() 匿名可访问）。<br>Allow access to all principals. (e.g. permitAll() 匿名可访问). |

## Persistence & Domain Kernel

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 不可变聚合基类 / Abstract Immutable | 创建后不可变实体的基类型（如：创建后不可变基类）。<br>Base type of an immutable entity after creation. (e.g. 创建后不可变基类). |
| 可变聚合基类 / Abstract Entity | 可变实体的持久化基类型（如：带 @Version 的可变基类）。<br>The persistence base type of the mutable entity. (e.g. 带 @Version 的可变基类). |
| 类型化标识 / Typed Id | 带类型约束的标识值。<br>Identity value with type constraint. (e.g. 带类型约束的标识值). |
| 具名聚合基类 / Abstract Named Entity | 带名称属性的实体基类型。<br>Entity base type with name attribute. (e.g. 带名称属性的实体基类型). |
| 不可变审计聚合基类 / Abstract Audit Event | 审计事件的抽象基类型（如：领域事件：已发生事实）。<br>Abstract base type for audit events. (e.g. 领域事件：已发生事实). |
| 可嵌入值对象基类 / Abstract Embeddable | 可嵌入值对象的基类型（如：@Embeddable Money）。<br>The base type of the embeddable value object. (e.g. @Embeddable Money). |
| 域字符串校验 / Domain Strings | 领域字符串的规范化与校验（如：非空、邮箱格式校验）。<br>Normalization and validation of domain strings. (e.g. 非空、邮箱格式校验). |
| 语义化访问器 / Semantic Accessor | 按语义访问声明或属性的访问器。<br>Accessor for accessing declarations or properties by semantics. (e.g. 按语义访问声明或属性的访问器). |
| 属性转换器 / Attribute Converter | 属性与持久化列之间的类型转换器。<br>Type converter between properties and persistent columns. (e.g. 属性与持久化列之间的类型转换器). |
| 乐观锁 / Optimistic Locking | 用版本号检测并发写冲突的策略。<br>Strategy for detecting concurrent write conflicts using version numbers. (e.g. 用版本号检测并发写冲突的策略). |
| Liquibase 变更日志 / Liquibase Changelog | 数据库结构变更的变更日志（如：db/changelog/*.yaml）。<br>Change log for database structure changes. (e.g. db/changelog/*.yaml). |
| 模式校验 / Schema Validation | 按模式校验数据结构是否合规。<br>Validate the compliance of the data structure according to the model. (e.g. 按模式校验数据结构是否合规). |
| 领域事件 / 领域事件 | 领域中已发生事实的记录。<br>Records of facts that have occurred in the field. (e.g. 领域中已发生事实的记录). |
| 事件处理方法 / Event Handler | 响应领域或应用事件的处理器（如：消费 OrderPaid 发邮件）。<br>Handler that responds to domain or application events. (e.g. 消费 OrderPaid 发邮件). |

## Provisioning Delivery

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 事件发布登记表 / Event Publication Registry | 跟踪事件发布状态的登记处（如：Outbox 事件发布登记）。<br>Registry for tracking the status of event publications. (e.g. Outbox 事件发布登记). |
| 应用模块监听器 / Application Module Listener | 监听应用模块事件的组件。<br>Component that listens for application module events. (e.g. 监听应用模块事件的组件). |
| 未完成事件发布 / Incomplete Event Publication | 尚未完成投递的事件发布记录（如：未完成的 Outbox 行）。<br>Event posting records that have not yet been posted. (e.g. 未完成的 Outbox 行). |
| OAuth2 客户端请求拦截器 / OAuth2 Client HTTP Request Interceptor | 为出站请求附加 OAuth2 凭证的拦截器。<br>Interceptor that attaches OAuth2 credentials to outbound requests. (e.g. 为出站请求附加 OAuth2 凭证的拦截器). |
| 可重试 / Retryable | 失败后可重试。<br>You can try again after failure. (e.g. 失败后可重试). |

## Security Controls | 安全控制

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| CSRF 防护 / CSRF Protection | 防止跨站请求伪造的防护机制。<br>Protection mechanism against cross-site request forgery. (e.g. 防止跨站请求伪造的防护机制). |
| 会话固定防护 / Session Fixation Protection | 防止会话固定攻击的保护（如：登录后轮换 session id）。<br>Protection against session fixation attacks. (e.g. 登录后轮换 session id). |
| 密码哈希 / Password Hashing | 将口令变换为不可逆摘要以便存储。<br>Converts passwords into irreversible digests for storage. (e.g. 将口令变换为不可逆摘要以便存储). |
| 停用用户 / Disabled User | 已被停用的用户。<br>User has been deactivated. (e.g. 已被停用的用户). |
| 授权同意 / Consent | 资源所有者对授权的明确同意。<br>Explicit consent of the resource owner to the authorization. (e.g. 资源所有者对授权的明确同意). |
| 持久化签名密钥 / Persistent Signing Key | 用于签发令牌的持久密钥。<br>Persistent key used to issue tokens. (e.g. 用于签发令牌的持久密钥). |
| 隐式拒绝 / Implicit Deny | 无允许规则时的默认拒绝。<br>Default denial when there are no allow rules. (e.g. 无允许规则时的默认拒绝). |
| 理由码 / Reason Code | 解释决定原因的代码。<br>Code explaining the reason for the decision. (e.g. 解释决定原因的代码). |
| 最小权限会话 / Least Privilege Session | 仅授予完成任务所需最小权限的会话。<br>Sessions are granted only the minimum permissions required to complete the task. (e.g. 仅授予完成任务所需最小权限的会话). |

## Shared Value Objects | 共享值对象

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 资源名 / Resource Name | 资源的规范名称路径。<br>The canonical name path of the resource. (e.g. 资源的规范名称路径). |
| 用户资源名 / User Resource Name | 用户资源的规范名称（如：users/alice）。<br>Canonical name of the user resource. (e.g. users/alice). |
| 角色资源名 / Role Resource Name | 角色资源的规范名称。<br>Canonical name of the role resource. (e.g. 角色资源的规范名称). |
| 主体 ID / Principal ID | 主体的标识。<br>Identification of the entity. (e.g. 主体的标识). |
| 资源 / Resource | 受访问控制保护的对象。<br>Objects protected by access control. (e.g. 受访问控制保护的对象). |
| 效力 / Effect | 可组合的视觉或交互动效（如：hover 高亮；过渡动画）。<br>Combinable visual or interactive effects. (e.g. hover 高亮；过渡动画). |
| 显式拒绝理由 / Explicit Deny Reason | 显式拒绝的原因说明。<br>Explanation of the reason for the explicit rejection. (e.g. 显式拒绝的原因说明). |
| 显式允许理由 / Explicit Allow Reason | 显式允许的原因说明。<br>Explanation of the reason for the explicit permission. (e.g. 显式允许的原因说明). |
| 隐式拒绝理由 / Implicit Deny Reason | 默认拒绝的原因说明。<br>Explanation of the reason for the default rejection. (e.g. 默认拒绝的原因说明). |

## Policy | 策略

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 权限 / Permission | 对特定操作或资源的允许权。<br>Permission to perform a specific action or access a specific resource. (e.g. 对特定操作或资源的允许权). |
| 允许策略 / Allow Policy | 显式允许访问的策略。<br>Policies that explicitly allow access. (e.g. 显式允许访问的策略). |
| 策略语句 / Policy Statement | 策略中的一条允许或拒绝陈述。<br>An allow or deny statement in a policy. (e.g. 策略中的一条允许或拒绝陈述). |
| 策略绑定 / Policy Assignment | 将策略绑定到主体或资源的关系。<br>The relationship that binds a policy to a principal or resource. (e.g. 将策略绑定到主体或资源的关系). |
| 访问元组 / Access Tuple | 主体、操作与资源组成的访问三元组。<br>Access triplet consisting of subject, action, and resource. (e.g. 主体、操作与资源组成的访问三元组). |
| 访问决策 / Access Decision | 一次访问控制评估的结论。<br>The conclusion of an access control assessment. (e.g. 一次访问控制评估的结论). |
| 策略引擎 / Policy Engine | 评估策略并输出访问决定的引擎（如：能力引擎实现）。<br>An engine that evaluates policies and outputs access decisions. (e.g. 能力引擎实现). |
| 允许策略已创建事件 / Allow Policy Created Event | 允许策略已创建的事实（如：新建 Allow 策略）。<br>The fact that the Allow policy has been created. (e.g. 新建 Allow 策略). |
| 策略已绑定事件 / Policy Attached Event | 策略已绑定的事实（如：领域事件：已发生事实）。<br>The fact that the policy has been linked. (e.g. 领域事件：已发生事实). |
| 权限已创建事件 / Permission Created Event | 权限已创建的事实（如：新建 iam:user:read）。<br>The fact that the permission has been created. (e.g. 新建 iam:user:read). |
| 访问已评估事件 / Access Evaluated Event | 访问已被评估的事实（如：一次 allow/deny 判定）。<br>Access to facts that have been evaluated. (e.g. 一次 allow/deny 判定). |

## Zero Trust | 零信任

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 零信任 / Zero Trust | 不默认信任网络位置、持续验证访问的安全模型（NIST SP 800-207）（如：每次请求都核验身份与设备）。<br>A security model that does not trust network locations by default and continuously verifies access (NIST SP 800-207). (e.g. 每次请求都核验身份与设备). |
| 策略决策点 / Policy Decision Point (PDP) | 评估访问策略并作出允许/拒绝决定的组件。<br>A component that evaluates access policies and makes allow/deny decisions. (e.g. 评估访问策略并作出允许/拒绝决定的组件). |
| 策略执行点 / Policy Enforcement Point (PEP) | 执行访问决定、拦截未授权请求的组件。<br>Components that enforce access decisions and intercept unauthorized requests. (e.g. 执行访问决定、拦截未授权请求的组件). |
| 服务身份 / Service Identity | 代表服务本身的机器身份（如：svc-billing 机器身份）。<br>Represents the machine identity of the service itself. (e.g. svc-billing 机器身份). |
| 令牌受众 / Token Audience | 令牌预期接收方的声明（如：aud=api://orders）。<br>Statement of the intended recipient of the token. (e.g. aud=api://orders). |
| 令牌交换 / Token Exchange | 将既有令牌兑换为其他令牌的协议（RFC 8693）（如：RFC 8693 换短时令牌）。<br>A protocol for exchanging existing tokens for other tokens (RFC 8693). (e.g. RFC 8693 换短时令牌). |
| 发送方约束令牌 / Sender-Constrained Token | 绑定到持有者密钥、限制被盗用的令牌（如：DPoP / mTLS 绑定令牌）。<br>Bind to the holder's key and restrict the use of stolen tokens. (e.g. DPoP / mTLS 绑定令牌). |

## Identity | 身份

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 用户 / User | 可登录并被授权的人员主体（如：登录主体 alice）。<br>Entities that can log in and be authorized. (e.g. 登录主体 alice). |
| 用户状态 / User Status | 用户账户的状态（如：active / deactivated）。<br>Status of the user account. (e.g. active / deactivated). |
| 启用用户 / Enable User | 启用用户（如：启用 alice 账号）。<br>Enable User. (e.g. 启用 alice 账号). |
| 禁用用户 / Disable User | 停用用户。<br>Deactivate User. (e.g. 停用用户). |
| 修改邮箱 / Change Email | 变更电子邮箱。<br>Change email address. (e.g. 变更电子邮箱). |
| 创建用户 / Create User | 创建用户（如：POST 创建 alice）。<br>Create User. (e.g. POST 创建 alice). |
| 创建联邦用户 / Create Federated User | 创建联邦用户（如：联邦登录后自动建档）。<br>Create a federated user. (e.g. 联邦登录后自动建档). |
| 外部 ID / External ID | 外部系统中的对应标识。<br>The corresponding identifier in an external system. (e.g. 外部系统中的对应标识). |
| 显示名 / Display Name | 用于展示的名称。<br>Name used for display. (e.g. 用于展示的名称). |
| 开通用户 / Provision User | 供给用户资源。<br>Provide users with resources. (e.g. 供给用户资源). |
| 更新资料 / Update Profile | 更新主体资料。<br>Update entity information. (e.g. 更新主体资料). |
| 修改外部 ID / Change External ID | 变更外部标识。<br>Change external ID. (e.g. 变更外部标识). |
| 组 / Group | 可集体授予权限的主体集合（如：「产品组」群聊）。<br>A set of entities to which permissions can be granted collectively. (e.g. 「产品组」群聊). |
| 组成员 / Group Member | 组的成员（如：alice ∈「工程」组）。<br>Members of the group. (e.g. alice ∈「工程」组). |
| 开通组 / Provision Group | 供给组资源。<br>Supply group resources. (e.g. 供给组资源). |
| 重命名组 / Rename Group | 重命名组（如：「工程」→「平台工程」）。<br>Rename Group. (e.g. 「工程」→「平台工程」). |
| 替换成员 / Replace Members | 替换组成员。<br>Replace group members. (e.g. 替换组成员). |
| 用户已开通事件 / User Provisioned Event | 用户已供给的事实（如：SCIM 开通 alice 完成）。<br>Facts provided by the user. (e.g. SCIM 开通 alice 完成). |
| 用户资料已变更事件 / User Profile Changed Event | 用户资料已变更的事实（如：alice 改显示名）。<br>The fact that the user's information has been changed. (e.g. alice 改显示名). |
| 用户已停用事件 / User Deactivated Event | 用户已停用的事实（如：停用 alice）。<br>The fact that the user has been deactivated. (e.g. 停用 alice). |
| 用户已恢复事件 / User Reactivated Event | 用户已重新启用的事实（如：恢复 alice）。<br>The fact that the user has been reactivated. (e.g. 恢复 alice). |
| 用户密码已重置事件 / User Password Reset Event | 用户口令已重置的事实（如：重置 alice 口令）。<br>The fact that the user's password has been reset. (e.g. 重置 alice 口令). |
| 用户已分配角色事件 / User Role Assigned Event | 用户角色已分配的事实（如：alice ← ROLE_ADMIN）。<br>The fact that the user role has been assigned. (e.g. alice ← ROLE_ADMIN). |
| 用户已解除角色事件 / User Role Unassigned Event | 用户角色已解除的事实（如：alice 解除 ROLE_ADMIN）。<br>The fact that the user role has been removed. (e.g. alice 解除 ROLE_ADMIN). |
| 用户删除请求事件 / User Deletion Requested Event | 已请求删除用户的事实（如：请求删除 alice）。<br>The fact that the user has requested to be deleted. (e.g. 请求删除 alice). |
| 用户已删除事件 / User Deleted Event | 用户已删除的事实（如：alice 已删除）。<br>The fact that the user has been deleted. (e.g. alice 已删除). |
| 组已创建事件 / Group Created Event | 组已创建的事实（如：新建组「工程」）。<br>The fact that the group has been created. (e.g. 新建组「工程」). |
| 组资料已变更事件 / Group Profile Changed Event | 组资料已变更的事实（如：组「工程」改名）。<br>The fact that the group information has been changed. (e.g. 组「工程」改名). |
| 组成员已变更事件 / Group Membership Changed Event | 组成员关系已变更的事实（如：alice ∈「工程」组）。<br>The fact that the membership of the group has changed. (e.g. alice ∈「工程」组). |
| 组已删除事件 / Group Deleted Event | 组已删除的事实（如：删除组「工程」）。<br>The fact that the group has been deleted. (e.g. 删除组「工程」). |
| 角色已创建事件 / Role Created Event | 角色已创建的事实（如：新建 ROLE_ADMIN）。<br>The fact that the role has been created. (e.g. 新建 ROLE_ADMIN). |
| 角色 / Role | 可赋予主体的权限集合命名。<br>A name that can be assigned to a set of permissions for a principal. (e.g. 可赋予主体的权限集合命名). |
| 角色绑定 / Role Assignment | 将角色赋予主体的关系。<br>The relationship that assigns roles to subjects. (e.g. 将角色赋予主体的关系). |
| 分配角色 / Assign Role | 为主体分配角色。<br>Assign roles to entities. (e.g. 为主体分配角色). |
| 模拟策略 / Impersonation Policy | 允许代理他人身份访问的策略。<br>Policy that allows access on behalf of another person. (e.g. 允许代理他人身份访问的策略). |
| 联邦身份 / Federated Identity | 由外部身份提供者断言的主体身份。<br>The subject identity asserted by the external identity provider. (e.g. 由外部身份提供者断言的主体身份). |
| OAuth 客户端 / OAuth Client | 已登记的 OAuth 客户端应用（如：client_id=spa-web）。<br>Registered OAuth client application. (e.g. client_id=spa-web). |

## Short-lived credentials | 临时凭证

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 创建短期凭证 / Create Token Exchange | 创建一次令牌兑换（如：RFC 8693 换短时令牌）。<br>Create a one-time token redemption. (e.g. RFC 8693 换短时令牌). |
| 会话已过期 / Session Expired | 会话已过期（如：会话超时需重新登录）。<br>Session expired. (e.g. 会话超时需重新登录). |
| 角色已扮演事件 / Role Assumed Event | 角色已被承担的事实（如：扮演 ROLE_ADMIN）。<br>The fact that the role has been assumed. (e.g. 扮演 ROLE_ADMIN). |

## Federation | 联邦与 SSO

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| OIDC 提供方 / OIDC Provider | 实现 OpenID Connect 的身份提供者。<br>Identity provider that implements OpenID Connect. (e.g. 实现 OpenID Connect 的身份提供者). |
| 客户端 ID / Client ID | OAuth 或客户端的标识符（如：client_id=spa-web）。<br>Identifier for OAuth or client. (e.g. client_id=spa-web). |
| 公共客户端 / Public Client | 无法保密客户端凭证的 OAuth 客户端（如：无 secret 的 SPA/PKCE 客户端）。<br>OAuth client that cannot keep client credentials confidential. (e.g. 无 secret 的 SPA/PKCE 客户端). |
| 依赖方 / Relying Party | 依赖身份提供者断言的应用方。<br>The relying party for the identity provider's assertion. (e.g. 依赖身份提供者断言的应用方). |
| 创建 OAuth 客户端 / 创建 OAuth 客户端 | 登记新的 OAuth 客户端。<br>Register a new OAuth client. (e.g. 登记新的 OAuth 客户端). |
| 恢复 OAuth 客户端 / Restore OAuth Client | 恢复 OAuth 客户端（如：client_id=spa-web）。<br>Restore an OAuth client. (e.g. client_id=spa-web). |
| 单点登录 / SSO Login | 一次登录后访问多个相关系统。<br>Access multiple related systems with a single login. (e.g. 一次登录后访问多个相关系统). |
| OAuth 客户端已注册事件 / OAuth Client Registered Event | OAuth 客户端已登记的事实（如：新客户端 spa-web 已注册）。<br>The fact that the OAuth client has been registered. (e.g. 新客户端 spa-web 已注册). |
| OAuth 客户端范围已变更事件 / OAuth Client Scopes Changed Event | OAuth 客户端范围已变更的事实（如：spa-web 增 openid）。<br>The fact that the OAuth client scope has changed. (e.g. spa-web 增 openid). |

## Audit Aggregates | 审计聚合

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 管理活动 / Admin Activity | 管理员操作的审计记录。<br>Audit records of administrator actions. (e.g. 管理员操作的审计记录). |
| 数据访问日志 / Data Access Log | 数据访问行为的审计日志。<br>Audit log of data access activities. (e.g. 数据访问行为的审计日志). |
| 审计主体 / Audit Actor | 引发审计事件的主体。<br>The entity that triggered the audit event. (e.g. 引发审计事件的主体). |
| 审计目标 / Audit Target | 审计事件所作用的对象。<br>The object affected by the audit event. (e.g. 审计事件所作用的对象). |
| 审计结果 / Audit Outcome | 审计事件的结果状态。<br>The result status of an audit event. (e.g. 审计事件的结果状态). |
| 记录管理活动 / Record Admin Activity | 写入一条管理活动审计。<br>Write an audit of management activities. (e.g. 写入一条管理活动审计). |
| 记录登录活动 / Record Sign-In Activity | 写入一条登录活动审计。<br>Write an audit of login activity. (e.g. 写入一条登录活动审计). |
| 记录数据访问 / Record Data Access Log | 写入一条数据访问审计。<br>Write a data access audit. (e.g. 写入一条数据访问审计). |
| 管理活动记录器 / Admin Activity Recorder | 记录管理活动的组件。<br>Components of records management activities. (e.g. 记录管理活动的组件). |

## Provisioning | 开通（SCIM）

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 开通目标 / Provisioning Target | 接受身份供给的目标系统。<br>The target system that accepts the identity provision. (e.g. 接受身份供给的目标系统). |
| 分配组 / Assignment Group | 用于授权指派的组。<br>Groups used for delegated authorization. (e.g. 用于授权指派的组). |
| 已开通账号 / Provisioned Account | 经供给预先创建的账号。<br>Pre-created accounts provided. (e.g. 经供给预先创建的账号). |
| SCIM 开通服务 / SCIM Provisioning Service | 执行 SCIM 供给用例的服务（如：入站 SCIM 开停账号）。<br>Service for implementing SCIM provisioning use cases. (e.g. 入站 SCIM 开停账号). |
| SCIM 推送服务 / SCIM Push Service | 向下游推送身份变更的服务（如：出站推送用户到下游）。<br>Service for pushing identity changes downstream. (e.g. 出站推送用户到下游). |
| SCIM 推送监听器 / SCIM Push Listener | 接收身份变更推送的监听器。<br>Listener for receiving identity change push notifications. (e.g. 接收身份变更推送的监听器). |
| SCIM 开通客户端 / SCIM Provisioner Client | 执行出站 SCIM 供给的客户端（如：出站 SCIM 客户端）。<br>Client that performs outbound SCIM provisioning. (e.g. 出站 SCIM 客户端). |
| SCIM 出站注册 / SCIM Outbound Registration | 出站 SCIM 目标的登记。<br>Registration of outbound SCIM targets. (e.g. 出站 SCIM 目标的登记). |

## Console | 控制台

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| IAM 控制台 / IAM Console | 身份与访问管理控制台。<br>Identity and Access Management Console. (e.g. 身份与访问管理控制台). |
| OAuth 客户端注册 / OAuth Client Registration | 登记 OAuth 客户端的过程或记录（如：登记 client_id=spa-web）。<br>The process or record of registering an OAuth client. (e.g. 登记 client_id=spa-web). |

## Dev Tooling | 开发工具

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 编排器 / Orchestrator | 协调多个工作者完成任务的组件。<br>Components that coordinate multiple workers to complete tasks. (e.g. 协调多个工作者完成任务的组件). |
| 子智能体 / Subagent | 在编排下执行子任务的智能体。<br>An agent that performs subtasks under orchestration. (e.g. 在编排下执行子任务的智能体). |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 策略规则 / Rule | 策略中单条允许或拒绝条件。<br>A single allow or deny condition in the policy. (e.g. 策略中单条允许或拒绝条件). |
| 策略已创建事件 / Policy Created Event | 访问策略已被创建的事实（如：领域事件：已发生事实）。<br>The fact that an access policy has been created. (e.g. 领域事件：已发生事实). |
| 不可变实体基类 / Abstract Immutable Entity | 带标识与创建时间、创建后不再变更的实体基类型（如：创建后不可变基类）。<br>Entity base type with identifier and creation time, which does not change after creation. (e.g. 创建后不可变基类). |
| 企业身份提供者 / Enterprise IdP | 企业侧签发联邦断言的身份提供者。<br>Identity provider that issues federated assertions on the enterprise side. (e.g. 企业侧签发联邦断言的身份提供者). |
