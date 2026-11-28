# Glossary | 领域术语表

> Explore IAM — Ubiquitous Language（统一语言）

---

## 1. Purpose | 文档说明

本文件定义项目**统一语言**。英文 Preferred Term 为代码、API、架构命名的权威形式；中文仅用于沟通与本地化。

### 维护原则

1. **先 Glossary**：改代码前先增改术语
2. **与代码同步**：聚合根 / 实体 / 值对象 / 枚举变更必须回写本表
3. **Preferred Term**：代码、API、Jira、提交、技术文档一律用英文列

### 引用规则

| 场景 | 规则 |
|------|------|
| Java / API / 提交 | 用 Preferred Term（英文） |
| Jira / 用户故事 | 英文为主；必要时括号附中文 |
| 前端 i18n | 英文术语映射到本地文案 |
| 跨团队沟通 | 先英文，需要时补中文 |

采用 [Google Cloud IAM](https://cloud.google.com/iam/docs/overview) 词汇 与 [OAuth clients](https://support.google.com/cloud/answer/15549257)。值对象落在 `domain.model`（无 `domain.vo`）。

---

## 2. Business Domains | 业务域总览

| Preferred Term | 中文   | Java Package | Frontend Route | API Prefix | Status | Notes |
| -------------- | ------ | ------------ | -------------- | ---------- | ------ | ----- |
| Identity       | 身份   | `com.iam.identity` | `/identity` | `/api/v1/users`, `/api/v1/groups`, `/api/v1/roles` | partial | AIP-121; `User` / Group / Role / RoleAssignment |
| Policy         | 策略   | `com.iam.policy` | `/allow-policies` | `/api/v1/allowPolicies`, `/api/v1/permissions` | partial | Policy Engine + Permission catalog |
| Short-lived credentials | 临时凭证 | `com.iam.sts` | — | `/api/v1/tokenExchanges` | partial | TokenExchange JWT |
| Federation     | 联邦   | `com.iam.federation` | `/oauth-clients` | OIDC + `/api/v1/oauthClients` | partial | SAS Provider + `OAuthClient` |
| Console        | 控制台 | — | `/` | — | partial | Login + OAuth client registration |
| Audit          | 审计   | `com.iam.audit` | `/audit` | `/api/v1/adminActivities`, `/api/v1/dataAccessLogs` | partial | AdminActivity + DataAccessLog |
| Provisioning   | 开通   | `com.iam.scim` | — | `/scim/v2` | implemented | SCIM 2.0 Service Provider (IdP → IAM) + SCIM Client (IAM → Explore AI) |
| Common         | 横切   | `com.iam.common` | — | — | partial | Shared model VOs, security, AIP helpers |

**Frontend route map (canonical)**

| Route        | Preferred Term | API prefix (AIP) |
| ------------ | -------------- | ---------------- |
| `/identity`  | Identity       | `/api/v1/users`, `/api/v1/groups`, `/api/v1/roles` |
| `/allow-policies` | Policy    | `/api/v1/allowPolicies`, `/api/v1/permissions` |
| `/audit`     | Audit          | `/api/v1/adminActivities`, `/api/v1/dataAccessLogs` |
| `/oauth-clients` | OAuth Client | `/api/v1/oauthClients` |
| `/`          | Console        | Control Plane REST |

**AIP REST（管理 API）** — Google [API Improvement Proposals](https://google.aip.dev/)：资源路径 `/api/v1`；标准 Get/List/Create/Update/Delete；AIP-136 自定义方法（`POST …/{resource}:disable`）；AIP-158 分页；AIP-193 `RpcStatus` 错误。

---

## 3. Protocols & Standards | 协议与标准

| Preferred Term (English) | 中文 | Definition | Framework Mapping |
| ------------------------ | ---- | ---------- | ----------------- |
| OAuth 2.0 | OAuth 2.0 | Authorization framework; authorization code and refresh token grants | Spring Authorization Server |
| OpenID Connect (OIDC) | 开放身份连接 | Identity layer on OAuth 2.0; `openid` scope | SAS `oidc()` |
| Authorization Code | 授权码 | Browser redirect flow exchanging code for tokens | `/oauth2/authorize` |
| PKCE | PKCE | Proof Key for Code Exchange for public clients | `ClientSettings.requireProofKey` |
| Access Token | 访问令牌 | Token authorizing resource access | JWT via `OAuth2TokenCustomizer` |
| ID Token | 身份令牌 | OIDC token carrying identity claims | `OidcTokenCustomizerConfig` |
| Refresh Token | 刷新令牌 | Long-lived token to obtain new access tokens | SAS `TokenSettings` |
| JWKS | JWKS | JSON Web Key Set for signature verification | `JWKSource`, `/.well-known/jwks.json` |
| JWK | JWK | Single JSON Web Key in a JWKS | `RSAKey` in `AuthorizationServerConfig` |
| JWT | JWT | Self-contained signed token format | `NimbusJwtDecoder` |
| Token Introspection | 令牌自省 | Resource server validates opaque or JWT tokens | SAS introspection endpoint (optional) |
| OAuth2 Client Authentication | OAuth2 客户端认证 | `client_secret_basic`, `client_secret_post`, `none` | Registered client settings |
| SAML 2.0 | SAML 2.0 | Secondary federation protocol (document only) | Not in current scope |
| SCIM 2.0 | SCIM 2.0 | System for Cross-domain Identity Management; REST provisioning of Users and Groups ([RFC 7643](https://datatracker.ietf.org/doc/html/rfc7643), [RFC 7644](https://datatracker.ietf.org/doc/html/rfc7644)) | UnboundID `scim2-sdk-common` models + Spring MVC |
| SCIM Service Provider | SCIM 服务端 | Server receiving provisioning calls at `/scim/v2` | `com.iam.scim.controller` (IAM); `com.ai.scim` (Explore AI, Users only) |
| SCIM Client | SCIM 客户端 | Caller pushing provisioning changes to a Service Provider | Okta / Entra ID → IAM; `ScimPushService` (IAM → Explore AI) |
| SCIM Media Type | SCIM 媒体类型 | `application/scim+json` request and response body | `ScimJsonHttpMessageConverter` |
| SCIM Discovery | SCIM 发现端点 | `/ServiceProviderConfig`, `/ResourceTypes`, `/Schemas` | `ScimDiscoveryController` |
| SCIM Filter | SCIM 过滤 | `filter` query (`eq`, `and`) on `userName`, `externalId`, `displayName`, `id` | UnboundID `Filter` + `ScimFilterTranslator` |
| SCIM PATCH | SCIM 局部更新 | `PatchOp` with `add` / `replace` / `remove` operations | UnboundID `PatchRequest` |
| List Response | 列表响应 | Paged SCIM list (`startIndex`, `count`, `totalResults`) | UnboundID `ListResponse` |
| SCIM Error | SCIM 错误 | Error body with `status` and `scimType` (RFC 7644 §3.12) | UnboundID `ErrorResponse` |
| Weak ETag | 弱 ETag | `W/"<version>"` from `@Version`; `If-Match` mismatch → 412 | `AbstractEntity.version` |

---

## 4. Spring Security & Framework | Spring Security 与框架映射

将 Explore IAM 安全行为映射到 [Spring Security](https://docs.spring.io/spring-security/reference/index.html) 与 [Spring Authorization Server](https://docs.spring.io/spring-authorization-server/reference/index.html) API。讨论过滤器、Bean、配置时用下列名称——除非下方领域节已列出，否则勿当作聚合根名。

### 4.1 Core Security

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| Spring Security | Spring Security | Servlet security framework for authentication and authorization | `spring-boot-starter-security` | implemented |
| Security Filter Chain | 安全过滤器链 | Ordered chain of servlet filters applying security rules | `SecurityFilterChain` | implemented |
| Authorization Server Filter Chain | 授权服务器过滤器链 | Filter chain scoped to SAS OAuth2/OIDC endpoints (`@Order(1)`) | `authorizationServerSecurityFilterChain` in `WebSecurityConfig` | implemented |
| SCIM Security Filter Chain | SCIM 安全过滤器链 | Stateless Bearer chain for `/scim/v2/**`; requires `SCOPE_write:iam_scim` (`@Order(2)`) | `scimSecurityFilterChain` in `ScimSecurityConfig` | implemented |
| Default Security Filter Chain | 默认安全过滤器链 | Filter chain for SPA, form login, and management APIs (`@Order(3)`) | `defaultSecurityFilterChain` in `WebSecurityConfig` | implemented |
| Authentication | 认证 | Verifying who the caller is | `Authentication`, `AuthenticationManager` | implemented |
| Authorization | 授权 | Deciding whether an authenticated caller may access a resource | `AuthorizationManager`, `@PreAuthorize` | implemented |
| Security Context | 安全上下文 | Thread-local holder for the current `Authentication` | `SecurityContextHolder` | implemented |
| Principal | 安全主体 | Identity attached to `Authentication` (username, JWT `sub`, etc.) | `Authentication.getPrincipal()` | implemented |
| Granted Authority | 授权权限 | Role or scope string granted to a principal (e.g. `ROLE_IAM_ADMIN`) | `GrantedAuthority`, `SimpleGrantedAuthority` | implemented |
| Authentication Entry Point | 认证入口点 | Handles unauthenticated access (redirect to login, 401, etc.) | `OAuthAwareLoginEntryPoint` | implemented |
| Authentication Success Handler | 认证成功处理器 | Post-login redirect (e.g. continue OAuth authorize URL) | `ContinueUrlAuthenticationSuccessHandler` | implemented |
| Authentication Failure Handler | 认证失败处理器 | Handles failed form login | `LoginAuthenticationFailureHandler` | implemented |
| Authentication Event | 认证事件 | Success/failure events published by Spring Security | `AuthenticationSuccessEvent`, `AbstractAuthenticationFailureEvent` | implemented |
| Enable Web Security | 启用 Web 安全 | Activates Spring Security filter chains | `@EnableWebSecurity` on `WebSecurityConfig` | implemented |

### 4.2 Session & Form Login

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| Form Login | 表单登录 | Username/password authentication via HTML form | `HttpSecurity.formLogin()` | implemented |
| Login Page | 登录页 | SPA route served at `/sign-in`; the form posts to `/login` | `form.loginPage("/sign-in").loginProcessingUrl("/login")` | implemented |
| User Details Service | 用户详情服务 | Loads `UserDetails` for form login from IAM store | `IamUserDetailsService` implements `UserDetailsService` | implemented |
| User Details | 用户详情 | Spring Security adapter wrapping IAM user + authorities | `org.springframework.security.core.userdetails.User` | implemented |
| Password Encoder | 密码编码器 | One-way hash for stored passwords (never plaintext) | `BCryptPasswordEncoder` | implemented |
| Logout | 登出 | Invalidates session and clears security context | `HttpSecurity.logout()` → `/logout` | implemented |
| Session | 会话 | Server-side HTTP session binding authenticated principal | `HttpSession` + Spring Security context | implemented |

### 4.2.1 Multi-Factor Authentication

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| MFA | 多因素认证 | Require more than one authentication factor before a full session | Spring Security `FactorGrantedAuthority` + factor authorization rules | implemented |
| Factor Authority | 因素权限 | Authority issued when a factor succeeds (e.g. password, OTT) | `FactorGrantedAuthority` (`FACTOR_PASSWORD`, `FACTOR_OTT`, …) | implemented |
| One-Time Token | 一次性令牌 | Server-generated login token delivered out-of-band (magic link); **not** TOTP | `HttpSecurity.oneTimeTokenLogin()` / `OneTimeTokenService` | implemented |
| Passkey | 通行密钥 | WebAuthn public-key credential for phishing-resistant sign-in | `HttpSecurity.webAuthn()` + JDBC WebAuthn repositories | implemented |
| WebAuthn Credential | WebAuthn 凭证 | Stored public key / credential id for a Passkey | Spring Security WebAuthn JDBC tables | implemented |
| TOTP Credential | TOTP 凭证 | Shared secret for authenticator-app codes (RFC 6238) | Domain `TotpCredential` + custom `FACTOR_TOTP` | implemented |

### 4.3 OAuth 2.0 / OIDC (Authorization Server)

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| OAuth2 Authorization Server | OAuth2 授权服务器 | Spring Authorization Server issuer and token endpoints | `HttpSecurity.oauth2AuthorizationServer()` | implemented |
| Authorization Endpoint | 授权端点 | Browser redirect to obtain authorization code | `/oauth2/authorize` | implemented |
| Token Endpoint | 令牌端点 | Exchange code or refresh token for tokens | `/oauth2/token` | implemented |
| OpenID Provider Configuration | OIDC 发现文档 | Machine-readable issuer metadata | `/.well-known/openid-configuration` via `oidc()` | implemented |
| JWK Set Endpoint | JWK 集端点 | Public signing keys for JWT verification | `/.well-known/jwks.json` | implemented |
| Registered Client Repository | 注册客户端仓库 | Persistence adapter for SAS `RegisteredClient` | `JdbcOAuthClientRepository` → `oauth2_registered_client` | implemented |
| Authorization Server Settings | 授权服务器设置 | Issuer URL and server-wide SAS options | `AuthorizationServerSettings` | implemented |
| Token Settings | 令牌设置 | Access token and refresh token TTL | `AuthorizationServerTokenSettingsConfig` | implemented |
| Client Settings | 客户端设置 | Per-client PKCE, consent, and metadata | `ClientSettings` on `RegisteredClient` | implemented |
| OAuth2 Token Customizer | 令牌定制器 | Adds claims to issued access/ID tokens | `OidcTokenCustomizerConfig` | implemented |
| JWT Encoder | JWT 编码器 | Signs JWT access tokens and assumed-role tokens | `JwtEncoder` bean in `JwtEncoderConfig` | implemented |
| JWT Decoder | JWT 解码器 | Validates JWT signatures on resource APIs | `oauth2ResourceServer().jwt()` | implemented |
| OAuth2 Resource Server | OAuth2 资源服务器 | Protects APIs by validating Bearer JWTs | `HttpSecurity.oauth2ResourceServer()` on SAS chain | implemented |
| Authorization Grant Type | 授权类型 | e.g. `authorization_code`, `refresh_token` | `AuthorizationGrantType` | implemented |
| Client Authentication Method | 客户端认证方式 | e.g. `client_secret_basic`, `client_secret_post`, `none` | `ClientAuthenticationMethod` | implemented |
| Redirect URI | 重定向 URI | Allowed OAuth callback URL for a client | `RedirectUri` VO, `RegisteredClient.redirectUris` | implemented |
| Scope | 范围 | OAuth scope string; OIDC std (`openid`, `profile`, `email`) plus GitHub-style product scopes (`write:ai_chat`, `admin:chat`) | `RegisteredClient.scopes` / Permission `oauthScope` | implemented |
| Scope Catalog | 范围目录 | Curated set of OAuth scopes backed by Permissions | Permission seeds | implemented |
| GitHub-Style Scope | GitHub 风格范围 | `{access}:{resource}` as in GitHub OAuth (`read:user`, `write:packages`, `admin:org`); access ∈ `read` \| `write` \| `admin` | Permission `oauthScope` | implemented |
| Issuer | 签发者 | OIDC issuer identifier URL | `spring.security.oauth2.authorizationserver.issuer` | implemented |

### 4.4 Federation (OAuth2 Client)

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| OAuth2 Client | OAuth2 客户端 | Spring Security client for upstream IdPs | `spring-boot-starter-oauth2-client` | partial |
| OAuth2 Login | OAuth2 登录 | Browser login via external provider | `HttpSecurity.oauth2Login()` | partial |
| Client Registration | 客户端注册 | External IdP client id/secret and endpoints | `ClientRegistration`, `ClientRegistrationRepository` | planned |
| OAuth2 User Service | OAuth2 用户服务 | Maps external user info to local IAM principal | `FederatedLoginService` | partial |
| Federation Link Service | 联邦链接服务 | Resolves or provisions `FederatedIdentityLink` and local user | `FederationLinkService` | partial |
| User Info Endpoint | 用户信息端点 | External IdP profile URL used after OAuth2 login | `oauth2Login().userInfoEndpoint()` | partial |

### 4.5 Method Security & RBAC

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| Method Security | 方法级安全 | RBAC on controller/service methods | `@EnableMethodSecurity` in `MethodSecurityConfig` | implemented |
| PreAuthorize | 预授权 | SpEL expression evaluated before method invocation | `@PreAuthorize("hasRole('IAM_ADMIN')")` | implemented |
| Has Role | 拥有角色 | SpEL helper matching `ROLE_*` granted authority | `hasRole('IAM_ADMIN')` → `ROLE_IAM_ADMIN` | implemented |
| Has Any Role | 拥有任一角色 | SpEL helper matching any of several roles | `hasAnyRole('IAM_ADMIN', 'IAM_AUDITOR')` | implemented |
| Is Authenticated | 已认证 | SpEL helper requiring any authenticated principal | `@PreAuthorize("isAuthenticated()")` on STS | implemented |
| IAM Admin Role | IAM 管理员角色 | Full management API access | `SecurityRoles.IAM_ADMIN` (`ROLE_IAM_ADMIN`) | implemented |
| IAM Auditor Role | IAM 审计员角色 | Read-only audit and list APIs | `SecurityRoles.IAM_AUDITOR` (`ROLE_IAM_AUDITOR`) | implemented |
| User Role | 普通用户角色 | Default role for demo / authenticated users | `SecurityRoles.USER` (`ROLE_USER`) | implemented |

### 4.6 CSRF & HTTP Hardening

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| CSRF Token | CSRF 令牌 | Token validating state-changing requests | `CsrfToken` | implemented |
| Cookie CSRF Token Repository | Cookie CSRF 仓库 | Stores CSRF token in `XSRF-TOKEN` cookie for SPA | `CookieCsrfTokenRepository.withHttpOnlyFalse()` | implemented |
| CSRF Token Request Handler | CSRF 请求处理器 | Resolves token from header (`X-XSRF-TOKEN`) or form field | `SpaCsrfTokenRequestHandler` | implemented |
| CSRF Cookie Filter | CSRF Cookie 过滤器 | Ensures CSRF cookie is written on each response | `CsrfCookieFilter` | implemented |
| Security Headers | 安全响应头 | HSTS, frame options, content-type options, etc. | `HttpSecurity.headers(withDefaults())` | implemented |
| Permit All | 全部放行 | Matcher allowing anonymous access | `authorize.requestMatchers(...).permitAll()` | implemented |

### 4.7 Persistence & Domain Kernel

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| Abstract Immutable | 不可变聚合基类 | Shared domain/JPA base: typed `id` + `createdAt` | `com.explore.kernel.AbstractImmutableEntity` (`shared/kernel`) | implemented |
| Abstract Entity | 可变聚合基类 | Extends immutable; adds `updatedAt` + `@Version` | `com.explore.kernel.AbstractEntity` (`shared/kernel`) | implemented |
| Typed Id | 类型化标识 | Embeddable id per aggregate wrapping the UUID string; column stays `id` | e.g. `UserId`, `GroupId`, `RoleId` | implemented |
| Abstract Named Entity | 具名聚合基类 | Mutable aggregate with unique `name` | `AbstractNamedEntity` | implemented |
| Abstract Audit Event | 不可变审计聚合基类 | Immutable audit aggregate base; `occurred_at` column | `AbstractAuditEvent` | implemented |
| Abstract Embeddable | 可嵌入值对象基类 | Embeddable VO and typed id base | `com.explore.kernel.AbstractEmbeddable` (`shared/kernel`) | implemented |
| Domain Strings | 域字符串校验 | Shared non-blank string validation | `DomainStrings.requireNonBlank` | implemented |
| Semantic Accessor | 语义化访问器 | Prefer Lombok getters when the field name is the Preferred Term; behavior methods use verb + noun | 模式 | e.g. `User.isLoginEnabled`, `Policy.getStatements` | implemented |
| Attribute Converter | 属性转换器 | JPA mapping between domain type and column | e.g. `ArnAttributeConverter`, `RulesJsonConverter` | implemented |
| Optimistic Locking | 乐观锁 | Concurrent update detection via `@Version` | `AbstractEntity.version` | implemented |
| Liquibase Changelog | Liquibase 变更日志 | Versioned SQL schema migrations | `db/changelog/0.1.xml` | implemented |
| Schema Validation | 模式校验 | Hibernate validates entities against DB schema | `spring.jpa.hibernate.ddl-auto: validate` | implemented |
| 领域事件 | 领域事件 | Fact published by an application service after a state change, inside its transaction; a `record` named `*Event` in `<module>/domain/event`, carrying ids only | `ApplicationEventPublisher.publishEvent` | implemented |
| Event Handler | 事件处理方法 | Synchronous `handleXxxEvent` method on the consuming module's service; runs in the publisher's transaction | `@EventListener` | implemented |

### 4.8 Provisioning Delivery

| Preferred Term (English) | 中文 | Definition | Framework Mapping | Status |
| ------------------------ | ---- | ---------- | ----------------- | ------ |
| Event Publication Registry | 事件发布登记表 | Stores each event publication in the business transaction (transactional outbox) | `spring-modulith-starter-jpa` → `event_publication` | implemented |
| Application Module Listener | 应用模块监听器 | Async, after-commit listener in its own transaction | `@ApplicationModuleListener` on `ScimPushListener` | implemented |
| Incomplete Event Publication | 未完成事件发布 | Publication whose listener failed; re-delivered later | `IncompleteEventPublications` | implemented |
| OAuth2 Client HTTP Request Interceptor | OAuth2 客户端请求拦截器 | Adds a `client_credentials` Bearer token to `RestClient` calls | `OAuth2ClientHttpRequestInterceptor` (`iam-scim-outbound`) | implemented |
| Retryable | 可重试 | Retry 5xx / 429 with backoff and jitter; honors `Retry-After` | Spring Framework 7 `@Retryable` | implemented |

---

## 5. Security Controls | 安全控制

| Preferred Term (English) | 中文 | Definition | Framework Capability | Status |
| ------------------------ | ---- | ---------- | -------------------- | ------ |
| CSRF Protection | CSRF 防护 | Prevents cross-site request forgery on session APIs | `CookieCsrfTokenRepository` | implemented |
| Session Fixation Protection | 会话固定防护 | New session ID after authentication | Spring Security `changeSessionId` | default |
| Method Security | 方法级安全 | RBAC on management APIs | `@EnableMethodSecurity` + `@PreAuthorize` | implemented |
| Security Headers | 安全响应头 | HSTS, frame options, content type options | `http.headers()` | implemented |
| Password Hashing | 密码哈希 | Stored credentials never plaintext | `PasswordEncoder` (BCrypt) | implemented |
| Disabled User | 停用用户 | Principal cannot authenticate when disabled | `UserDetails.isEnabled()` | implemented |
| Consent | 授权同意 | User approves OAuth client scopes | `OAuth2AuthorizationConsentService` | planned |
| Persistent Signing Key | 持久化签名密钥 | JWKS stable across restarts | `SigningKey`, `PersistentJwkSourceConfig` | implemented |
| Implicit Deny | 隐式拒绝 | No matching Allow → Deny | `PolicyEngine` default | implemented |
| Reason Code | 理由码 | Machine-readable authz/audit outcome | `ReasonCode` VO | implemented |
| Least Privilege Session | 最小权限会话 | Short TTL temporary credentials | STS + `TokenSettings` | implemented |
| IAM Admin Role | IAM 管理员角色 | Console and management API access | `ROLE_IAM_ADMIN` | implemented |
| IAM Auditor Role | IAM 审计员角色 | Read-only audit API access | `ROLE_IAM_AUDITOR` | implemented |

---

## 5.5 Shared Value Objects | 共享值对象

跨限界上下文值对象，位于 `com.iam.common.domain.model`（Google IAM 资源 / 权限词汇）。

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| Resource Name | 资源名 | 完整资源名（值可仍为 `arn:iam::…` 形态） | 值对象 | `ResourceName` | implemented |
| User Resource Name | 用户资源名 | User 的资源名 | 工厂 | `ResourceName.createUserResourceName` | implemented |
| Role Resource Name | 角色资源名 | Role 的资源名 | 工厂 | `ResourceName.createRoleResourceName` | implemented |
| Principal ID | 主体 ID | 求值中主体标识 | 值对象 | `PrincipalId` | implemented |
| Permission | 权限 | IAM 权限字符串（亦用于允许策略语句） | 值对象 | `com.iam.common.domain.model.Permission` | implemented |
| Resource | 资源 | Permission 作用目标 | 值对象 | `Resource` | implemented |
| Effect | 效力 | `ALLOW` 或 `DENY` | 枚举 | `Effect` | implemented |
| Reason Code | 理由码 | 可机读的鉴权结果 | 值对象 | `ReasonCode` | implemented |
| Explicit Deny Reason | 显式拒绝理由 | 命中 Deny 语句 | 常量 | `ReasonCode.EXPLICIT_DENY` | implemented |
| Explicit Allow Reason | 显式允许理由 | 命中 Allow 语句 | 常量 | `ReasonCode.EXPLICIT_PERMIT` | implemented |
| Implicit Deny Reason | 隐式拒绝理由 | 无 Allow 命中 | 常量 | `ReasonCode.IMPLICIT_DENY` | implemented |

---

## 5.6 Zero Trust | 零信任

| Preferred Term (English) | 中文 | Definition | Explore Mapping | Status |
| ------------------------ | ---- | ---------- | --------------- | ------ |
| Zero Trust | 零信任 | 不因网络位置隐式信任；每请求认证、鉴权并持续校验（NIST SP 800-207） | 生态安全模型 | planned |
| Policy Decision Point (PDP) | 策略决策点 | 判定主体可否访问资源 | explore-iam（令牌签发、`PolicyEngine`、权限点 scope） | partial |
| Policy Enforcement Point (PEP) | 策略执行点 | 在请求路径执行 PDP 决策 | AI / Chat 资源服务器、Chat Socket.IO 网关、ML 网关（目标） | partial |
| Service Identity | 服务身份 | 服务间调用的工作负载主体 | 每工作负载 OAuth2 `client_credentials` 客户端 | planned |
| Token Audience | 令牌受众 | `aud` 限制可接受该令牌的资源服务器（RFC 8707） | 访问令牌按服务受众 | planned |
| Token Exchange | 令牌交换 | 以用户名义将用户令牌换为下游令牌（RFC 8693） | AI / Chat → ML | planned |
| Sender-Constrained Token | 发送方约束令牌 | 令牌绑定客户端密钥，防盗用重放（DPoP RFC 9449、mTLS RFC 8705） | 公共客户端（DPoP）、内部工作负载（mTLS） | planned |

---

## 6. Identity | 身份

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| Principal | 主体 | 可发起请求的身份（Google IAM principal） | 概念 | `Principal` | — |
| User | 用户 | 长期人类或服务身份 | 聚合根 | `User` (`directory_user`) | implemented |
| User Status | 用户状态 | `ACTIVE` 或 `DISABLED` | 枚举 | via `enabled` flag | implemented |
| Enable User | 启用用户 | 重新允许用户表单登录 | 行为 | `User.enableUser` | implemented |
| Disable User | 禁用用户 | 阻止用户表单登录 | 行为 | `User.disableUser` | implemented |
| Change Email | 修改邮箱 | 更新用户联系邮箱 | 行为 | `User.changeEmail` | implemented |
| Create User | 创建用户 | 本地用户工厂 | 行为 | `User.createUser` | implemented |
| Create Federated User | 创建联邦用户 | 外部 IdP 登录用户工厂 | 行为 | `User.createFederatedUser` | implemented |
| External ID | 外部 ID | 开通客户端分配的标识（SCIM `externalId`） | 值对象 | `ExternalId` | implemented |
| Display Name | 显示名 | 可读显示名（SCIM `displayName`） | 属性 | `User.displayName` | implemented |
| Provision User | 开通用户 | SCIM Client 创建的无密码用户工厂 | 行为 | `User.provisionUser` | implemented |
| Update Profile | 更新资料 | 替换用户名、邮箱与显示名 | 行为 | `User.updateProfile` | implemented |
| Change External ID | 修改外部 ID | 设置或清除外部 ID | 行为 | `User.changeExternalId` / `Group.changeExternalId` | implemented |
| Group | 组 | 共享策略绑定的用户集合 | 聚合根 | `Group` | implemented |
| Group Member | 组成员 | 将用户关联到组的子实体 | 实体 | `GroupMember` | implemented |
| Provision Group | 开通组 | SCIM Client 创建的组工厂 | 行为 | `Group.provisionGroup` | implemented |
| Rename Group | 重命名组 | 修改组显示名 | 行为 | `Group.renameGroup` | implemented |
| Replace Members | 替换成员 | 设置完整成员列表；返回增删 id | 行为 | `Group.replaceMembers` | implemented |
| User Provisioned Event | 用户已开通事件 | 用户已创建（控制台 / SCIM / 联邦登录 / 种子） | 领域事件 | `UserProvisionedEvent` | implemented |
| User Profile Changed Event | 用户资料已变更事件 | 用户名 / 邮箱 / 显示名 / 外部 ID 已变更 | 领域事件 | `UserProfileChangedEvent` | implemented |
| User Deactivated Event | 用户已停用事件 | 用户已停用 | 领域事件 | `UserDeactivatedEvent` | implemented |
| User Reactivated Event | 用户已恢复事件 | 用户已恢复 | 领域事件 | `UserReactivatedEvent` | implemented |
| User Password Reset Event | 用户密码已重置事件 | 管理员重置用户密码 | 领域事件 | `UserPasswordResetEvent` | implemented |
| User Role Assigned Event | 用户已分配角色事件 | 角色已绑定到用户 | 领域事件 | `UserRoleAssignedEvent` | implemented |
| User Role Unassigned Event | 用户已解除角色事件 | 角色已从用户解绑 | 领域事件 | `UserRoleUnassignedEvent` | implemented |
| User Deletion Requested Event | 用户删除请求事件 | 用户行删除前发布，供其他模块同事务清理关联 | 领域事件 | `UserDeletionRequestedEvent` | implemented |
| User Deleted Event | 用户已删除事件 | 用户已删除 | 领域事件 | `UserDeletedEvent` | implemented |
| Group Created Event | 组已创建事件 | 组已创建 | 领域事件 | `GroupCreatedEvent` | implemented |
| Group Profile Changed Event | 组资料已变更事件 | 组名 / 外部 ID 已变更 | 领域事件 | `GroupProfileChangedEvent` | implemented |
| Group Membership Changed Event | 组成员已变更事件 | 组成员 id 增删 | 领域事件 | `GroupMembershipChangedEvent` | implemented |
| Group Deleted Event | 组已删除事件 | 组已删除 | 领域事件 | `GroupDeletedEvent` | implemented |
| Role Created Event | 角色已创建事件 | 角色已创建 | 领域事件 | `RoleCreatedEvent` | implemented |
| Role | 角色 | 命名权限集合（Google IAM role） | 聚合根 | `Role` | implemented |
| Role Assignment | 角色绑定 | 将主体绑定到 Role | 实体 | `RoleAssignment` | implemented |
| Assign Role | 分配角色 | 将角色绑定到用户 | 行为 | `User.assignRole` | implemented |
| Impersonation Policy | 模拟策略 | 谁可为 Role 获取临时凭证 | 值对象 | `ImpersonationPolicy` | implemented |
| Federated Identity | 联邦身份 | 外部提供方 + subject 映射到本地用户 | 聚合根 | `FederatedIdentity` | implemented |
| TOTP Credential | TOTP 凭证 | 认证器 App 第二因素（RFC 6238）；密钥静态加密 | 聚合根 | `TotpCredential` | implemented |
| OAuth Client | OAuth 客户端 | 注册为 OAuth / OIDC 客户端的依赖产品 | 聚合根 | `OAuthClient` | implemented |

---

## 7. Policy | 策略

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| Allow Policy | 允许策略 | 语句文档（Effect、Permission、Resource） | 聚合根 | `Policy` | implemented |
| Policy Statement | 策略语句 | 单条 Allow 或 Deny 规则块 | 值对象 | `Rule` | implemented |
| Effect | 效力 | `ALLOW` or `DENY` | 枚举 | `Effect` | see §5.5 |
| Policy Assignment | 策略绑定 | 将允许策略绑定到主体资源名 | 聚合根 | `PolicyAssignment` | implemented |
| Permission | 权限 | 目录项：code、Resource、GitHub 风格 `oauthScope` | 聚合根 | `com.iam.policy.domain.model.Permission` | implemented |
| Access Tuple | 访问元组 | 主体 + 权限 + 资源（排障形状） | 值对象 | `AccessTuple` | implemented |
| Access Decision | 访问决策 | 带理由的 Allow 或 Deny | 值对象 | `AccessDecision` | implemented |
| Policy Engine | 策略引擎 | 策略求值：Deny > Allow > 隐式拒绝 | 领域服务 | `PolicyEngine` | implemented |
| Allow Policy Created Event | 允许策略已创建事件 | 允许策略已创建 | 领域事件 | `PolicyCreatedEvent` | implemented |
| Policy Attached Event | 策略已绑定事件 | 策略绑定已创建 | 领域事件 | `PolicyAttachedEvent` | implemented |
| Permission Created Event | 权限已创建事件 | 权限目录项已创建 | 领域事件 | `PermissionCreatedEvent` | implemented |
| Access Evaluated Event | 访问已评估事件 | 访问元组已求值为访问决策 | 领域事件 | `AccessEvaluatedEvent` | implemented |

---

## 8. Short-lived credentials | 临时凭证

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| Token Exchange | 短期凭证 | 角色扮演后的临时 JWT | 聚合根 | `TokenExchange` | implemented |
| Impersonation Policy | 模拟策略 | 谁可为 Role 创建临时凭证 | 概念 | `ImpersonationPolicy` on `Role` | implemented |
| Create Token Exchange | 创建短期凭证 | 将调用方身份换为角色会话凭证 | 用例 | `TokenExchangeService` | implemented |
| Session Expired | 会话已过期 | 凭证是否已失效 | 行为 | `TokenExchange.isExpired` | implemented |
| Role Assumed Event | 角色已扮演事件 | 调用方已获得 Role 临时凭证 | 领域事件 | `RoleAssumedEvent` | implemented |

---

## 9. Federation | 联邦与 SSO

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| OIDC Provider | OIDC 提供方 | Explore IAM 作为 OIDC 签发方 | 容器 | Spring Authorization Server | implemented |
| OAuth Client | OAuth 客户端 | 依赖方注册的领域聚合根 | 聚合根 | `OAuthClient` | implemented |
| Client ID | 客户端 ID | OAuth2 `client_id` 值对象 | 值对象 | `ClientId` | implemented |
| Public Client | 公共客户端 | 仅以 `none` 认证的客户端 | 行为 | `OAuthClient.isPublicClient` | implemented |
| Relying Party | 依赖方 | 信任 Explore IAM 的应用 | 概念 | — | — |
| Federated Identity | 联邦身份 | 外部提供方 + subject 映射到本地用户 | 聚合根 | `FederatedIdentity` | implemented |
| 创建 OAuth 客户端 | 创建 OAuth 客户端 | 机密 / 公共客户端工厂 | 行为 | `OAuthClient.createOAuthClient` | implemented |
| Restore OAuth Client | 恢复 OAuth 客户端 | 自持久化重建 | 行为 | `OAuthClient.restoreOAuthClient` | implemented |
| SSO Login | 单点登录 | 一次登录服务多个依赖方 | 用例 | OIDC Authorization Code | implemented |
| OAuth Client Registered Event | OAuth 客户端已注册事件 | OAuth 客户端已创建 | 领域事件 | `OAuthClientRegisteredEvent` | implemented |
| OAuth Client Scopes Changed Event | OAuth 客户端范围已变更事件 | 允许的 scope 已替换 | 领域事件 | `OAuthClientScopesChangedEvent` | implemented |

---

## 10. Audit Aggregates | 审计聚合

只追加审计行的不可变**聚合根**（Google Cloud Audit Logs 风格）。

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| Admin Activity | 管理活动 | 含主体、目标、结果的管理面动作 | 聚合根 | `AdminActivity` | implemented |
| Data Access Log | 数据访问日志 | 已持久化的允许/拒绝及理由码 | 聚合根 | `DataAccessLog` | implemented |
| Audit Actor | 审计主体 | 执行管理动作的主体 | 值对象 | `AuditActor` | implemented |
| Audit Target | 审计目标 | 受影响的资源类型与 id | 值对象 | `AuditTarget` | implemented |
| Audit Outcome | 审计结果 | 成功或失败 | 枚举 | `AuditOutcome` | implemented |
| Record Admin Activity | 记录管理活动 | `AdminActivity` 工厂 | 行为 | `AdminActivity.recordAdminActivity` | implemented |
| Record Sign-In Activity | 记录登录活动 | 登录管理活动工厂 | 行为 | `AdminActivity.recordSignInActivity` | implemented |
| Record Data Access Log | 记录数据访问 | `DataAccessLog` 工厂 | 行为 | `DataAccessLog.recordDataAccessLog` | implemented |
| Admin Activity Recorder | 管理活动记录器 | 处理他模块领域事件并持久化管理活动 / 数据访问日志；他模块不得直接调用 | 应用服务 | `AdminActivityRecorder.handleXxxEvent` | implemented |

---

## 10.1 Provisioning | 开通（SCIM）

IAM 为身份中枢：外部 IdP 推入 IAM；IAM 再推用户到下游。协议类型留在 `com.iam.scim`；身份限界上下文不导入 SCIM 类。

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| Provisioning | 开通 | 跨系统创建、更新、停用与删除身份 | 概念 | `com.iam.scim` | implemented |
| Provisioning Target | 开通目标 | IAM 推送到的下游 SCIM 服务端 | 聚合根 | `ProvisioningTarget` | implemented |
| Assignment Group | 分配组 | 可选：限制目标接收用户范围的组 | 属性 | `ProvisioningTarget.assignmentGroupId` | implemented |
| Provisioned Account | 已开通账号 | IAM 用户 id 到目标远端 id 与最近同步版本的映射 | 实体 | `ProvisionedAccount` | implemented |
| SCIM Provisioning Service | SCIM 开通服务 | 经 `UserService` / `GroupService` 编排入站 SCIM | 应用服务 | `ScimProvisioningService` | implemented |
| SCIM Push Service | SCIM 推送服务 | 向开通目标发送幂等 SCIM 调用 | 应用服务 | `ScimPushService` | implemented |
| SCIM Push Listener | SCIM 推送监听器 | 将身份领域事件转为推送 | 监听器 | `ScimPushListener` | implemented |
| SCIM Provisioner Client | SCIM 开通客户端 | 外部 IdP 用 OAuth 客户端（`client_credentials`、`write:iam_scim`） | OAuth Client | `scim-provisioner` | implemented |
| SCIM Outbound Registration | SCIM 出站注册 | IAM 出站推送用 OAuth 客户端（`client_credentials`、`write:ai_scim`） | Client Registration | `iam-scim-outbound` | implemented |

---

## 11. Console | 控制台

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Status |
| ------------------------ | ---- | ---------- | ---- | ------------ | ------ |
| IAM Console | IAM 控制台 | 管理员 Angular SPA | UI | Angular 22 app | partial |
| OAuth Client Registration | OAuth 客户端注册 | 创建 OAuth 客户端 | 用例 | Console + `/api/v1/oauthClients` | implemented |

---

## 11.1 Module Scope Vocabulary | 模块范围词汇（GitHub-style）

Permission `oauthScope` / `code` uses GitHub OAuth scope shape
[`{access}:{resource}`](https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/scopes-for-oauth-apps)
(`read:user`, `write:packages`, `admin:org`). Reject dotted product scopes
(`ai.chat`, `chat.admin`).

| Module | oauthScope | Relying Party use |
|--------|------------|-------------------|
| oidc | `openid`, `profile`, `email` | OIDC standard |
| ai | `write:ai_chat` | Explore AI chat / text / privacy |
| ai | `write:ai_audio` | ASR / TTS |
| ai | `write:ai_rag` | RAG |
| ai | `write:ai_media` | image / vision |
| ai | `write:ai_agent` | Gallery `/api/agents` invoke、A2A Tasks、pipeline / workflow / automation / skill（Agent Registry 总闸；非 per-skill scope） |
| ai | `write:ai_tools` | mcp / tools / eval |
| ai | `write:ai_scim` | IAM → Explore AI SCIM `/scim/v2/Users` |
| iam | `write:iam_scim` | External IdP → IAM SCIM `/scim/v2` |
| chat | `write:chat_messaging` | chats / messages / calls / media / notifications |
| chat | `write:chat_social` | posts / follow / groups / status / search |
| chat | `admin:chat` | Chat admin APIs |

---

## 12. Dev Tooling | 开发工具

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Notes |
| ------------------------ | ---- | ---------- | ---- | ------------ | ----- |
| Orchestrator | 编排器 | 委托子智能体的编排器 | 模式 | `~/.cursor/agents/orchestrator.md` | dev tooling |
| Subagent | 子智能体 | 专用单用途智能体 | 模式 | `~/.cursor/agents/*.md` | dev tooling |

---

## References

- [References](References.md)
- [Guideline](Guideline.md)
- [C4 model](c4-model/)
- [User Story Map](User-Story-Map.md)
