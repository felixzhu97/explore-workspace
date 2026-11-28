# C4 模型


视觉分轨，勿混用：

| 轨 | 文件 | 样式 |
|----|------|------|
| 结构 C4 | C1–C3、Deployment、ZeroTrust-Target | `C4_blue_new` |
| 领域模型 | `C4-Code-Domain-Model*.puml` | 白底黑字黑边框 |
| 动态 | `C4-Dynamic-*` | `!theme plain` 黑白 |

规范见全局 [c4-model](~/.cursor/skills/reference/c4-model.md)。

---

## 文件

| 文件 | 层级 | 说明 |
|------|------|------|
| `C1-Context.puml` | C1 | 人员、Explore IAM 边界、Relying Party、外部 IdP、企业 IdP（SCIM）、Explore AI（开通目标） |
| `C2-Container.puml` | C2 | IAM 单体（:9100）：SCIM 入站 + 出站推送、元数据库 |
| `C3-Component.puml` | C3 | Console + 后端模块（含 SCIM）；`controller → service → domain ← infra` |
| `C4-Code-Domain-Model.puml` | Code | 领域模型：按限界上下文分组（Kernel / Common / Identity / Policy / STS / Federation / Audit） |
| `C4-Deployment.puml` | Deployment | 本地 H2 + Render Starter（Docker） |
| `C4-ZeroTrust-Target.puml` | Container（目标） | IAM 为 PDP；AI / Chat / ML 为 PEP；受众绑定与令牌交换 |
| `C4-Dynamic-Overview.puml` | Dynamic（宏观） | 治理 → 接入 RP → SSO → Bearer → STS → 审计 |
| `C4-Dynamic-ZeroTrust-Target.puml` | Dynamic（目标） | MFA + DPoP → PEP → 在线 PDP → 交换至 ML → 吊销 / SIEM |
| `C4-Dynamic-Sso-Login.puml` | Dynamic | OIDC Authorization Code（机密 + 公共 PKCE） |
| `C4-Dynamic-Native-Pkce-Login.puml` | Dynamic | iOS ASWebAuthenticationSession + PKCE → RP Bearer |
| `C4-Dynamic-Policy-Evaluation.puml` | Dynamic | 临时凭证 + `PolicyEngine`（Deny > Allow > 隐式拒绝） |
| `C4-Dynamic-Scim-Provisioning.puml` | Dynamic | 企业 IdP → IAM `/scim/v2` → 领域事件 → 幂等推送 Explore AI |

---

## 技术栈与端口

| 项 | 值 |
|----|-----|
| 运行时 | Java 25、Spring Boot 4.1 |
| Console | Angular 22（打入 `server/src/main/resources/static`） |
| OIDC | Spring Authorization Server（`/oauth2/*`、`/.well-known/*`） |
| 本地 URL | `http://localhost:9100` |
| 本地库 | H2 `./data/explore-iam`（Liquibase `0.1.xml`） |
| 目标库 | PostgreSQL |

限界上下文（Java）：`identity` / `policy` / `sts` / `federation` / `audit` / `scim` / `common`。

---

## 阅读顺序

1. C1 → C2 → C3（结构）
2. `C4-Code-Domain-Model.puml`（统一语言）
3. `C4-Dynamic-Overview.puml`，再读其他 `C4-Dynamic-*`
4. `C4-Deployment.puml`
5. Zero Trust 目标图 + 动态图

---

## Google Cloud IAM 映射

采用 [Google Cloud IAM](https://cloud.google.com/iam/docs/overview) 词汇。

| Google Cloud IAM | Explore IAM |
|------------------|-------------|
| Principal | `User`、`Group`（聚合根） |
| Role / role binding | `Role`、`RoleAssignment` |
| Allow policy | `Policy`、`Rule`、`PolicyEngine`（领域服务） |
| Permission | `Permission`（值对象 + 目录聚合根） |
| Resource name | `ResourceName`（值对象） |
| Short-lived credentials | `TokenExchange`（聚合根） |
| Workforce / workload federation | OIDC Provider + `FederatedIdentity` |
| OAuth client | `OAuthClient` |
| Cloud Audit Logs | `AdminActivity`、`DataAccessLog` |

---

## 渲染

```bash
cd docs/c4-model
PLANTUML_LIMIT_SIZE=16384 plantuml -tpng -o png C4-Code-Domain-Model.puml
```

在线：[PlantUML server](https://www.plantuml.com/plantuml/uml/)。

---

## 相关内容

- [Glossary](../../Glossary.md)
- [Guideline](../../Guideline.md)
- [User Story Map](../../User-Story-Map.md)
- [README](../../../../iam/README.md)

---

## 领域模型（双语 · 必备）

| 文件 | 内容 |
|------|------|
| `C4-Code-Domain-Model.puml` | 领域模型（字段与行为） |
| `C4-Code-Domain-Model-Relations.puml` | 关系图（仅结构） |
| `C4-Code-Domain-Model-Integration.puml` | 系统对接模型 |
| `C4-Code-Domain-Model-Integration-Relations.puml` | 对接关系图（仅结构） |

文风：标准书面语、断言清晰、删冗。术语：聚合根 / 实体 / 值对象 / 领域事件 / 限界上下文 / 领域服务 / 仓储。
