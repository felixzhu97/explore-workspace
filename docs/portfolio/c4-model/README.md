# C4 模型 — FinPulse

FinPulse 单体仓的 PlantUML C4 图。

## Visual tracks

| Track | Files | Style |
| ----- | ----- | ----- |
| **Structural C4** | C1–C3、Deployment | 官方 **`C4_blue_new`** 主题（线框；亦覆盖组件） |
| **Domain + Dynamics** | Code 领域模型、`C4-Dynamic-*` | 经 [`style-zinc.puml`](./style-zinc.puml) 共享 zinc 外观 |

禁止将 `C4_blue_new` 混入领域 / 动态图（亦禁止对结构 C4 套用 `style-zinc.puml`）。优先 `C4_blue_new`，勿仅用裸 `!NEW_C4_STYLE=1`，以保证 Component 图保持线框外观。

## 文件

| File | Level | Description |
| ---- | ----- | ----------- |
| [C1-Context.puml](./C1-Context.puml) | Context | 人员与外部系统 |
| [C2-Container.puml](./C2-Container.puml) | Container | Admin、portal、mobile、Java API、Python analytics、数据存储 |
| [C3-Component.puml](./C3-Component.puml) | Component | **单图**：Java 特性模块 + Python analytics + UI 应用 |
| [C4-Code-Domain-Model.puml](./C4-Code-Domain-Model.puml) | Code | 按**业务域**分包的 DDD 类模型：聚合根 / 实体 / 值对象 + 内核基类 |
| [C4-Deployment.puml](./C4-Deployment.puml) | Deployment | **单图**：本地 / 开发与生产说明合一 |
| [C4-Dynamic-Auth-Login.puml](./C4-Dynamic-Auth-Login.puml) | Dynamic | 登录 → auth 模块 → DB → Bearer token |
| [C4-Dynamic-Portfolio-Aggregate.puml](./C4-Dynamic-Portfolio-Aggregate.puml) | Dynamic | Client → Java 代理 → Python → DB → 聚合 |
| [C4-Dynamic-Quotes-Subscribe.puml](./C4-Dynamic-Quotes-Subscribe.puml) | Dynamic | WS `/ws/quotes` 订阅与行情推送 |
| [style-zinc.puml](./style-zinc.puml) | Shared | 仅用于 Code + Dynamics 的 zinc 样式 |

> Component 与 Deployment **有意**不拆为 Backend/Frontend 或 Production 兄弟文件。

### Code / 领域模型说明

- Stereotype：**Abstract**、**AggregateRoot**、**Entity**、**ValueObject**、**Enum**（见图例）。
- PlantUML package 用业务域名（`Customer`、`Portfolio`、`Order`），不用 Java FQN。
- 组合（`*--`）= 聚合内所有权；空心聚合 / 箭头 = 按 id 引用。
- 类型化 Id / Money / Symbol / Quantity 位于 `domain.model`（无 `domain.vo`）；实体使用 `@EmbeddedId` 类型化 id。
- 行为为动词 + 名词（`createPortfolio`、`updateName`、`changeStatus`）。

### Dynamic 角色配色

`C4-Dynamic-*` 按角色着色参与者（经 `style-zinc.puml` stereotype）：

| Stereotype | Role | Tint |
| ---------- | ---- | ---- |
| (actor) | 投资者 / 用户 | Emerald |
| `<<ui>>` | Portal / Admin / Mobile | Slate |
| `<<java>>` | Java API `:8801` | Blue |
| `<<python>>` | Python Analytics `:8800` | Teal |
| `<<db>>` | PostgreSQL | Amber |
| `<<bus>>` | Redis / Kafka | Orange |
| `<<external>>` | 行情提供方 | Zinc gray |

### 何时打开哪条轨道

- 边界 / 部署拓扑 → Structural C4（`C4_blue_new`）。
- 统一语言 / 聚合根 → Code 领域模型。
- 运行时请求路径（认证、组合聚合、行情）→ `C4-Dynamic-*`。

## 技术栈（当前）

| Container | Tech | Port |
| --------- | ---- | ---- |
| Admin | React + Vite + Emotion | Vite 默认 |
| Portal | React + Vite + Emotion | Vite 默认 |
| Mobile | React Native + Expo | Expo |
| Java API | Spring Boot（Bazel `//:server`） | **8801**（入口） |
| Python Analytics | FastAPI | **8800** |
| DB | Postgres（Docker）/ H2 本地偏好 | — |

## 渲染

```bash
# requires plantuml on PATH
plantuml -tpng docs/c4-model/*.puml
```

PNG 可选；以 `.puml` + 本 README 为 source of truth。在线：[PlantUML server](https://www.plantuml.com/plantuml/uml/)。

## Related

- [Glossary](../../Glossary.md)

## 领域模型（双语 · 必备）

| 文件 | 内容 |
|------|------|
| `C4-Code-Domain-Model.puml` | 领域模型（字段与行为） |
| `C4-Code-Domain-Model-Relations.puml` | 关系图（仅结构） |
| `C4-Code-Domain-Model-Integration.puml` | 系统对接模型 |
| `C4-Code-Domain-Model-Integration-Relations.puml` | 对接关系图（仅结构） |

文风：标准书面语、断言清晰、删冗。术语：聚合根 / 实体 / 值对象 / 领域事件 / 限界上下文。
