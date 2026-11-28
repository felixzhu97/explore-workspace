# References

Explore Commerce — 电商交易。总册 [../References.md](../References.md)。

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

1. **Payment Intent** — Stripe 收单意图，承载付款生命周期。
   https://stripe.com/docs/payments/payment-intents

2. **PCI DSS** — 卡数据安全要求；PAN 不经自建表单落盘。
   https://www.pcisecuritystandards.org/standards/pci-dss/

3. **Saga** — 可补偿的长事务拆分。
   https://microservices.io/patterns/data/saga.html

4. **Outbox** — 业务库与消息投递一致。
   https://martinfowler.com/articles/patterns-of-distributed-systems/outbox.html

5. **Idempotency** — 重复请求结果不变。
   https://stripe.com/docs/api/idempotent_requests

6. **ISO 4217** — 货币代码。
   https://www.iso.org/iso-4217-currency-codes.html

## 规范性引用文件

> 注日期引用仅该版本适用；不注日期引用适用最新版（含修改单）。

### 国家标准与行业标准

1. GB/T 35273—2020 信息安全技术 个人信息安全规范
   https://openstd.samr.gov.cn/bzgk/std/newGbInfo?hcno=4568F276E0F8346EB0FBA097AA0CE05E

2. GB/T 22239—2019 信息安全技术 网络安全等级保护基本要求
   https://std.samr.gov.cn/

3. JR/T 0171—2020 个人金融信息保护技术规范
   https://std.samr.gov.cn/

### 国际标准

1. ISO 4217 Currency codes
   https://www.iso.org/iso-4217-currency-codes.html

2. ISO/IEC 27001:2022 ISMS Requirements
   https://www.iso.org/standard/27001

3. IETF RFC 3339:2002, Date and Time on the Internet
   https://www.rfc-editor.org/rfc/rfc3339

### 协议与技术规范

1. IETF RFC 6749:2012, OAuth 2.0
   https://www.rfc-editor.org/rfc/rfc6749

2. OpenID Connect Core 1.0
   https://openid.net/specs/openid-connect-core-1_0.html

3. IETF RFC 7519:2015, JWT
   https://www.rfc-editor.org/rfc/rfc7519

4. IETF RFC 9110:2022, HTTP Semantics
   https://www.rfc-editor.org/rfc/rfc9110

5. IETF RFC 9457:2023, Problem Details
   https://www.rfc-editor.org/rfc/rfc9457

6. OpenAPI Specification 3.1
   https://spec.openapis.org/oas/latest.html

## 参考文献

### 指南与最佳实践

1. PCI DSS
   https://www.pcisecuritystandards.org/standards/pci-dss/

2. Stripe Security / PCI
   https://stripe.com/docs/security/guide

3. OWASP Top 10
   https://owasp.org/www-project-top-ten/

4. IETF RFC 9700:2025, OAuth 2.0 Security BCP
   https://www.rfc-editor.org/rfc/rfc9700

### 产品与平台文档

1. Stripe API
   https://stripe.com/docs

2. Stripe Payment Intents
   https://stripe.com/docs/payments/payment-intents

3. Google OAuth
   https://developers.google.com/identity/protocols/oauth2

4. GitHub OAuth Apps
   https://docs.github.com/en/apps/oauth-apps

5. Next.js
   https://nextjs.org/docs

6. Angular
   https://angular.dev/

7. MongoDB
   https://www.mongodb.com/docs/

### 专著

1. Evans. *Domain-Driven Design*. Addison-Wesley, 2003
   https://www.domainlanguage.com/ddd/

2. Hohpe & Woolf. *Enterprise Integration Patterns*. Addison-Wesley, 2003
   https://www.enterpriseintegrationpatterns.com/

### 论文

1. Garcia-Molina & Salem. Sagas. *SIGMOD*, 1987
   https://dl.acm.org/doi/10.1145/38713.38742

2. Helland. Life beyond Distributed Transactions. 2007
   https://queue.acm.org/detail.cfm?id=3025012

3. Gray. The Transaction Concept. *VLDB*, 1981
   https://jimgray.azurewebsites.net/papers/theTransactionConcept.pdf
