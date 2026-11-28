# Explore Workspace 文档

活文档按**产品**分册，册内**扁平**：术语、模型、契约、故事与运维说明并列，不再按职衔分夹。

## 文风

凡本树文档，一律：

| 要求 | 要义 |
|------|------|
| 标准书面语 | 用规范术语，拒口语与含混直译 |
| 强有力 | 断言边界与义务，少用「可以」「或许」 |
| 精简 | 一句一事；能删则删 |
| 修辞 | 对仗、递进、对照服务清晰，不为辞藻 |

领域用语取 DDD 通行译名（聚合根、实体、值对象、领域事件等），不以字面硬译。

凡 PlantUML 图：黑白为默认；**连线一律正交**，禁止曲线。
样式唯一入口：[`c4-model/style.puml`](c4-model/style.puml)（类图）与 [`c4-model/style-c4.puml`](c4-model/style-c4.puml)（C4 结构/动态/部署）。
动态图命名 `C4-Dynamic-<Scenario>.puml`，与结构图同目录扁平存放，不单立文件夹。

## 总览与产品

- **工作区总览**：[c4-model/](c4-model/) — 外部系统 · 内部系统 · 限界上下文 · 领域模型
- **权威源**：[References.md](References.md) — 目录 · 概念 · 规范性引用 · 参考文献（去冗）
- **产品分册**：`ai` · `chat` · `commerce` · `iam` · `lowcode` · `ml` · `portfolio` · `three`

| 产品 | Glossary | References | Guideline | Story Map | C4 |
|------|----------|-----------|-----------|-----------|-----|
| ai | [Glossary](ai/Glossary.md) | [References](ai/References.md) | [Guideline](ai/Guideline.md) | [USM](ai/User-Story-Map.md) | [c4-model](ai/c4-model/) |
| chat | [Glossary](chat/Glossary.md) | [References](chat/References.md) | [Guideline](chat/Guideline.md) | [USM](chat/User-Story-Map.md) | [c4-model](chat/c4-model/) |
| commerce | [Glossary](commerce/Glossary.md) | [References](commerce/References.md) | — | [USM](commerce/User-Story-Map.md) | [c4-model](commerce/c4-model/) |
| iam | [Glossary](iam/Glossary.md) | [References](iam/References.md) | [Guideline](iam/Guideline.md) | [USM](iam/User-Story-Map.md) | [c4-model](iam/c4-model/) |
| lowcode | [Glossary](lowcode/Glossary.md) | [References](lowcode/References.md) | — | [USM](lowcode/User-Story-Map.md) | [c4-model](lowcode/c4-model/) |
| ml | [Glossary](ml/Glossary.md) | [References](ml/References.md) | [Guideline](ml/Guideline.md) | [USM](ml/User-Story-Map.md) | [c4-model](ml/c4-model/) |
| portfolio | [Glossary](portfolio/Glossary.md) | [References](portfolio/References.md) | — | [USM](portfolio/User-Story-Map.md) | [c4-model](portfolio/c4-model/) |
| three | [Glossary](three/Glossary.md) | [References](three/References.md) | — | [USM](three/User-Story-Map.md) | [c4-model](three/c4-model/) |

## 目录

```text
docs/
├── README.md
├── References.md                 # 工作区横切权威源 + 产品索引
├── c4-model/                    # 工作区整体 C4（全局）
│   ├── C1-Context.puml
│   ├── C2-Container.puml
│   ├── C4-Code-Domain-Model.puml
│   └── C4-Code-Domain-Model-Relations.puml
└── {product}/                   # 产品分册（扁平）
    ├── Glossary.md              # 统一语言
    ├── References.md             # 完整目录 · 规范性引用 · 参考文献
    ├── Guideline.md             # 可选
    ├── User-Story-Map.md
    ├── user-stories/
    ├── c4-model/                # 产品级 C1–C4 与领域四视图
    └── api/openapi.yaml         # HTTP 契约
```

## 产品级 C4 领域四视图

置于 `docs/{product}/c4-model/`。有模型必有关系；有对接必有对接关系。

| 文件 | 职分 |
|------|------|
| `C4-Code-Domain-Model.puml` | 领域模型（双语 · 含字段与行为） |
| `C4-Code-Domain-Model-Relations.puml` | 关系图（双语 · 仅结构） |
| `C4-Code-Domain-Model-Integration.puml` | 系统对接模型（双语 · 完整） |
| `C4-Code-Domain-Model-Integration-Relations.puml` | 对接关系图（双语 · 仅结构） |

## DDD 中文术语

| English | 中文 |
|---------|------|
| Bounded Context | 限界上下文 |
| Aggregate Root | 聚合根 |
| Entity | 实体 |
| Value Object | 值对象 |
| Domain Event | 领域事件 |
| Domain Service | 领域服务 |
| Repository | 仓储 |
