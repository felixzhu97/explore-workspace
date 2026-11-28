# Explore Workspace · 整体 C4

工作区级架构真相源。产品分册见 `docs/{product}/c4-model/`。**此处只画全局**：外部系统、内部系统、限界上下文与跨产品领域模型。

黑白、正交直线、双语领域图、DDD 通行中文术语。

| 文件 | 层级 | 断言 |
|------|------|------|
| `C1-Context.puml` | 系统上下文 | 人 · 外部系统 · 内部八产品 |
| `C2-Container.puml` | 容器 | 限界上下文边界内的运行时容器与主链路 |
| `C4-Code-Domain-Model.puml` | 代码 / 领域 | 八限界上下文聚合与跨上下文虚线（含字段） |
| `C4-Code-Domain-Model-Relations.puml` | 代码 / 关系 | 同上，仅结构 |
| `C4-Code-Domain-Model-Integration.puml` | 代码 / 对接 | 工作区 ↔ Google IdP / Google AI 等外部平台（含字段） |
| `C4-Code-Domain-Model-Integration-Relations.puml` | 代码 / 对接关系 | 同上，仅结构 |
| `style.puml` | 样式 | 全体图共用：黑白 · 正交 |
| `style-c4.puml` | 样式 | C4 结构 / 动态 / 部署图（含元素着色令牌） |

## 样式引入

| 图种 | 引入 |
|------|------|
| C1 / C2 / C3 / Deployment / Dynamic | `!ROUNDED_STYLE=0` → `!include C4_*` → `!include …/c4-model/style-c4.puml` |
| 领域类图（Code Domain*） | `!include …/c4-model/style.puml` |

产品分册路径示例：`!include ../../c4-model/style-c4.puml`。

## 限界上下文 ↔ 产品

| 限界上下文 | 产品路径 |
|------------|----------|
| 身份与访问 | [`docs/iam/`](../iam/) |
| 对话与智能体 | [`docs/ai/`](../ai/) |
| 社交与即时通讯 | [`docs/chat/`](../chat/) |
| 电商交易 | [`docs/commerce/`](../commerce/) |
| 机器学习平台 | [`docs/ml/`](../ml/) |
| 财富与投组 | [`docs/portfolio/`](../portfolio/) |
| 低代码创作 | [`docs/lowcode/`](../lowcode/) |
| 三维可视化 | [`docs/three/`](../three/) |

跨限界上下文只允许 ID、领域事件与公开契约；细节以产品分册为准。

## 动态图命名（扁平，不单立目录）

按 C4 规范，目录保持**扁平**；动态图与结构图同级，不另建文件夹。

| 规则 | 形式 |
|------|------|
| 文件名 | `C4-Dynamic-<Scenario>.puml` |
| `<Scenario>` | PascalCase，多词可用连字符（如 `Rag-Ask`、`Iam-Native-Sign-In`） |
| 一文件一场景 | 关键运行路径单独成图 |

现有产品动态图均已落在各 `docs/{product}/c4-model/` 根下。

## 渲染

```bash
cd docs/c4-model && mkdir -p png && plantuml -tpng -charset UTF-8 -o png \
  C1-Context.puml C2-Container.puml \
  C4-Code-Domain-Model.puml C4-Code-Domain-Model-Relations.puml \
  C4-Code-Domain-Model-Integration.puml \
  C4-Code-Domain-Model-Integration-Relations.puml
```

边界一变，先改此图，再改产品分册。
