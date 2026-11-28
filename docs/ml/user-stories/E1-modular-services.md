# E1 模块化服务

← [用户故事地图](../User-Story-Map.md)

## 背景

推荐、视觉、RAG、语音、图像、视频各为独立限界上下文，包结构扁平。兄弟服务只调用稳定路由；模型运算留在 Explore ML 内。

---

## US-01 扁平分模块布局

**As a** 平台工程师  
**I want** 每个模块遵循 `controller/`、`service/`、`domain/`、`infra/`  
**So that** 改一能力不必动其他模块

### 验收标准

1. **Scenario** 模块符合分层  
   **GIVEN** Explore ML 源码树  
   **WHEN** 打开 `server/` 下任一模块  
   **THEN** 遵循 `controller → service → domain ← infra`  
   **AND** 领域类型（聚合根 / 实体 / 值对象）落在 `domain/`

### 状态

已实现

---

## US-02 RAG 导出向量

**As a** 兄弟 API 开发者  
**I want** 经 `documents:exportVectors` 导出文档向量  
**So that** 上游可复用嵌入而不必重新入库

### 验收标准

1. **Scenario** 导出文档向量  
   **GIVEN** RAG 模块已加载且存在测试文档  
   **WHEN** 调用导出向量路由  
   **THEN** 响应含所请求文档的向量

### 状态

已实现

---

## US-03 WebSocket 流式 ASR

**As a** 兄弟 API 开发者  
**I want** 在 `/ws/v1/audios:transcribe` 上流式 ASR  
**So that** 语音客户端无需 HTTP 轮询即可获得中间结果

### 验收标准

1. **Scenario** 流式转写帧  
   **GIVEN** Speech 模块已加载  
   **WHEN** 客户端经 WebSocket 推送音频  
   **THEN** 返回 partial 与 final 转写帧

### 状态

已实现

---

## US-04 微调脚本

**As a** 平台工程师  
**I want** 除 video 外各模块提供 `training/` 脚本  
**So that** 可用测试数据本地再训练

### 验收标准

1. **Scenario** 列出训练脚本  
   **GIVEN** 非 video 模块  
   **WHEN** 列出 `training/`  
   **THEN** 存在微调入口脚本且有文档说明

### 状态

已实现
