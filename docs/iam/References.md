# References

Explore IAM — 身份与访问。总册 [../References.md](../References.md)。

## 目录

1. [概念释义](#概念释义)
2. [规范性引用文件](#规范性引用文件)
   1. [国家标准与行业标准](#国家标准与行业标准)
   2. [国际标准](#国际标准)
   3. [协议与技术规范](#协议与技术规范)
3. [参考文献](#参考文献)
   1. [指南与最佳实践](#指南与最佳实践)
   2. [产品与平台文档](#产品与平台文档)
   3. [专著](#专著)
   4. [论文](#论文)

## 概念释义

1. **OAuth 2.0** — 委托授权；与登录语义分离。
   https://oauth.net/2/

2. **OIDC** — OAuth 之上的身份层（ID Token、Discovery）。
   https://openid.net/developers/how-connect-works/

3. **PKCE** — 公共客户端防授权码拦截。
   https://oauth.net/2/pkce/

4. **JWT** — 可验签的紧凑声明。
   https://jwt.io/introduction

5. **JWK** — 密钥材料的 JSON 发布格式。
   https://www.rfc-editor.org/rfc/rfc7517

6. **SCIM** — 用户与组生命周期供给。
   https://scim.cloud/

7. **WebAuthn** — 公钥凭证 / 通行密钥。
   https://webauthn.guide/

8. **Token Introspection** — 查询令牌有效性与元数据。
   https://www.rfc-editor.org/rfc/rfc7662

9. **Token Exchange** — 兑换短期、受众受限凭证。
   https://www.rfc-editor.org/rfc/rfc8693

10. **SAML 2.0** — 基于断言的企业联邦（次要路径）。
   https://docs.oasis-open.org/security/saml/v2.0/

11. **IdP / RP** — 身份提供者签发；依赖方消费。
   https://openid.net/developers/how-connect-works/

12. **零信任** — 持续验证，不默认信任网络位置。
   https://csrc.nist.gov/pubs/sp/800/207/final

## 规范性引用文件

> 注日期引用仅该版本适用；不注日期引用适用最新版（含修改单）。

### 国家标准与行业标准

1. GB/T 35273—2020 信息安全技术 个人信息安全规范
   https://openstd.samr.gov.cn/bzgk/std/newGbInfo?hcno=4568F276E0F8346EB0FBA097AA0CE05E

2. GB/T 22239—2019 信息安全技术 网络安全等级保护基本要求
   https://std.samr.gov.cn/

3. GB/T 25069—2022 信息安全技术 术语
   https://std.samr.gov.cn/

4. GB/T 20984—2022 信息安全技术 信息安全风险评估方法
   https://std.samr.gov.cn/

### 国际标准

1. ISO/IEC 27001:2022 ISMS Requirements
   https://www.iso.org/standard/27001

2. ISO/IEC 29115:2013 Entity authentication assurance framework
   https://www.iso.org/standard/45138.html

3. ISO/IEC 24760-1:2019 Identity management — Terminology and concepts
   https://www.iso.org/standard/77582.html

### 协议与技术规范

1. IETF RFC 6749:2012, OAuth 2.0
   https://www.rfc-editor.org/rfc/rfc6749

2. OAuth 2.1 (draft guidance)
   https://oauth.net/2.1/

3. IETF RFC 7636:2015, PKCE
   https://www.rfc-editor.org/rfc/rfc7636

4. IETF RFC 7519:2015, JWT
   https://www.rfc-editor.org/rfc/rfc7519

5. IETF RFC 7517:2015, JWK
   https://www.rfc-editor.org/rfc/rfc7517

6. IETF RFC 7662:2015, Token Introspection
   https://www.rfc-editor.org/rfc/rfc7662

7. IETF RFC 8693:2020, Token Exchange
   https://www.rfc-editor.org/rfc/rfc8693

8. IETF RFC 7643:2015, SCIM Core Schema
   https://www.rfc-editor.org/rfc/rfc7643

9. IETF RFC 7644:2015, SCIM Protocol
   https://www.rfc-editor.org/rfc/rfc7644

10. OpenID Connect Core 1.0
   https://openid.net/specs/openid-connect-core-1_0.html

11. OpenID Connect Discovery 1.0
   https://openid.net/specs/openid-connect-discovery-1_0.html

12. OASIS SAML 2.0
   https://docs.oasis-open.org/security/saml/v2.0/

13. W3C WebAuthn Level 3
   https://www.w3.org/TR/webauthn-3/

14. OpenAPI Specification 3.1
   https://spec.openapis.org/oas/latest.html

## 参考文献

### 指南与最佳实践

1. OWASP ASVS
   https://owasp.org/www-project-application-security-verification-standard/

2. OWASP Top 10
   https://owasp.org/www-project-top-ten/

3. IETF RFC 9700:2025, OAuth 2.0 Security BCP
   https://www.rfc-editor.org/rfc/rfc9700

4. OWASP OAuth 2.0 Cheat Sheet
   https://cheatsheetseries.owasp.org/cheatsheets/OAuth2_Cheat_Sheet.html

5. NIST SP 800-63-4 Digital Identity Guidelines
   https://csrc.nist.gov/pubs/sp/800/63/4/final

6. NIST SP 800-207 Zero Trust Architecture
   https://csrc.nist.gov/pubs/sp/800/207/final

7. Google AIP
   https://google.aip.dev/

### 产品与平台文档

1. Spring Authorization Server
   https://docs.spring.io/spring-authorization-server/reference/

2. Spring Security OAuth2
   https://docs.spring.io/spring-security/reference/servlet/oauth2/

3. Google Identity
   https://developers.google.com/identity

4. GitHub OAuth Apps
   https://docs.github.com/en/apps/oauth-apps

5. Okta SCIM
   https://developer.okta.com/docs/concepts/scim/

6. Microsoft Entra SCIM
   https://learn.microsoft.com/en-us/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups

7. Ping Identity SCIM 2 SDK
   https://github.com/pingidentity/scim2

### 专著

1. Richer & Sanso. *OAuth 2 in Action*. Manning, 2017
   https://www.manning.com/books/oauth-2-in-action

2. Parecki. *OAuth 2.0 Simplified*. 2018
   https://www.oauth.com/

### 论文

1. Fett et al. A Comprehensive Formal Security Analysis of OAuth 2.0. *CCS*, 2016
   https://arxiv.org/abs/1601.01229

2. Needham & Schroeder. Using Encryption for Authentication…. *CACM*, 1978
   https://dl.acm.org/doi/10.1145/359657.359659

3. Diffie & Hellman. New Directions in Cryptography. 1976
   https://doi.org/10.1109/TIT.1976.1055638
