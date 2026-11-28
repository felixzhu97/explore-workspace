# C4 模型文档

PlantUML 绘制的 C4 架构模型，描述 Explore Lowcode（低代码）架构。

## 文件

| 文件 | 层级 | 说明 |
| --- | --- | --- |
| `C1-Context.puml` | C1 | 系统上下文（开发者 / 设计师 / Vercel / CDN / Git） |
| `C2-Container.puml` | C2 | 容器图（SPA + 业务域 folders + components/hooks/lib） |
| `C3-Component-Frontend.puml` | C3 | 前端业务域组件图（canvas / component / template / … colocated） |
| `C4-Deployment.puml` | C4 | 本地开发（`pnpm dev` → `:3000`） |
| `C4-Deployment-Production.puml` | C4 | 生产部署（Vercel） |

---

## C1 — 系统上下文图

![C1-Context](png/C1-Context.png)

---

## C2 — 容器图

![C2-Container](png/C2-Container.png)

> 业务域目录变更后 PNG 可能过期；用 PlantUML 从 `.puml` 重新生成。

---

## C3 — 前端组件图

![C3-Component-Frontend](png/C3-Component-Frontend.png)

> 业务域目录变更后 PNG 可能过期；用 PlantUML 从 `.puml` 重新生成。

---

## C4 — 部署图

本地与生产源文件：`C4-Deployment.puml`、`C4-Deployment-Production.puml`。重新生成 PNG 后放入 `png/`。

---

## 技术栈

| 层级 | 技术 |
| --- | --- |
| 运行时 | Next.js 16 + React 19 + TypeScript |
| UI | Radix UI + Tailwind CSS |
| 拖拽 | React DnD |
| 状态 | Zustand |
| 图表 | Recharts |
| 表单 | React Hook Form + Zod |
| 架构 | Business-domain folders（`src/{domain}` + `components` / `hooks` / `lib`） |
| 持久化 | Browser LocalStorage |
| 部署 | Vercel（推荐）；自托管 `pnpm build && pnpm start` |

---

## 重新生成 PNG

```bash
cd docs/c4-model
plantuml -o png *.puml
# 或
docker run --rm -v "$PWD":/data plantuml/plantuml -o png /data/*.puml
```

---

## 关联文档

- [Glossary](../../Glossary.md)
- [User Story Map](../../User-Story-Map.md)

## 领域模型（双语 · 必备）

| 文件 | 内容 |
|------|------|
| `C4-Code-Domain-Model.puml` | 领域模型（字段与行为） |
| `C4-Code-Domain-Model-Relations.puml` | 关系图（仅结构） |
| `C4-Code-Domain-Model-Integration.puml` | 系统对接模型 |
| `C4-Code-Domain-Model-Integration-Relations.puml` | 对接关系图（仅结构） |

文风：标准书面语、断言清晰、删冗。术语：聚合根 / 实体 / 值对象 / 领域事件 / 限界上下文。
