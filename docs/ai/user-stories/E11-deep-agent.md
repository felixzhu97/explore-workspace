# E11 Deep Agent（已退役）

← [用户故事地图](../User-Story-Map.md)

> **Status: retired.** 独立 Agent 模块（`/agents`、Deep sessions、已保存 Agent 库、`module-agents`）已移除。
>
> 产品方向：**仅 Pipeline workers**。内置多语言定义填充 `/pipelines` 调色板；双击画布节点编辑**图内副本**（name、systemPrompt、toolKeys）。工作流模版以可视化方式选用 Agent（禁止逗号分隔 `agentTypes`）；任务输入位于画布 **Run** 旁。
>
> 后继故事：[E7 Pipeline（工作流）](./E7-multi-agent.md)。

## 历史范围（归档）

| ID | 原能力 | 现状 |
| --- | --- | --- |
| US-32 | 带记忆的多轮 Agent 会话 | 已移除；不再提供独立 Agent 工作区 |
| US-33 | Plan / tool / eval 过程可见 | 已移除 Deep SSE；Pipeline 结果轨展示 handoff / 工具步骤 |
| US-34 | 多语言 Agent 模版库 | 内置目录迁入 Pipeline `agent-templates/{lang}.json`；无客户端 Agent 库 |
