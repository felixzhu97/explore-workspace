# C4 模型

Explore ML 的架构视图。源文件为 `.puml`；有 PlantUML 时再生成 PNG。术语见 [Glossary](../../Glossary.md)；产品状态见 [User Story Map](../../User-Story-Map.md)。


---

## 文件

| 文件 | 层级 | 说明 |
|------|------|------|
| `C1-Context.puml` | C1 | ML 应用 + 测模 UI；依赖 Qdrant、Ollama/OpenAI、重排旁路、内容 API、推荐存储、本地权重与 Hugging Face Hub |
| `C2-Container.puml` | C2 | 单一 ML 容器、UI `/ml` 代理、RAG / 推荐外部依赖 |
| `C3-Component.puml` | C3 | 应用壳（`server.py`、模块契约）；RAG 分层；UI `shared/`、D3、分模块页 |
| `C4-Deployment.puml` | Deployment | 单 uvicorn、UI 开发服、嵌入式 Qdrant、Ollama、`~/Codes/models` |

---

## 运行时默认值

| 面 | 端口 |
|----|------|
| ML 应用（`server/main.py`，六模块） | `:8000` |
| 测模 UI（`ng serve`） | `:4210` |

UI 经 `/ml` 代理（去前缀、WebSocket 开）；目标见 `:web/proxy.conf.json`。兄弟产品 API 将唯一回环上游指向同一端口。

限界上下文（Python 模块）：`recommendation` / `vision` / `rag` / `image_playground` / `speech` / `video`。

---

## 渲染

```bash
cd docs/c4-model && docker run --rm -v "$PWD":/data plantuml/plantuml -tpng -o png '*.puml'
```

---

## 相关内容

- [Glossary](../../Glossary.md)
- [Guideline](../../Guideline.md)
- [User Story Map](../../User-Story-Map.md)

---

## 领域模型（双语 · 必备）

| 文件 | 内容 |
|------|------|
| `C4-Code-Domain-Model.puml` | 领域模型（字段与行为） |
| `C4-Code-Domain-Model-Relations.puml` | 关系图（仅结构） |
| `C4-Code-Domain-Model-Integration.puml` | 系统对接模型 |
| `C4-Code-Domain-Model-Integration-Relations.puml` | 对接关系图（仅结构） |

文风：标准书面语、断言清晰、删冗。术语：聚合根 / 实体 / 值对象 / 领域事件 / 限界上下文 / 领域服务 / 仓储。
