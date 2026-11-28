# E2 统一应用

← [用户故事地图](../User-Story-Map.md)

## 背景

单一回环进程、单一 base URL 暴露全部模块与聚合健康。密钥只进环境文件，不进已提交默认值。

---

## US-05 单应用与聚合健康

**As a** 平台工程师  
**I want** 端口 8000 上一个 FastAPI 应用并提供聚合 `/health`  
**So that** 启动与就绪检查覆盖每个已加载模块

### 验收标准

1. **Scenario** 聚合模块健康  
   **GIVEN** 统一应用已运行  
   **WHEN** `GET /health`  
   **THEN** 每个已加载模块报告 `status` 与 `latency_ms`

2. **Scenario** 一次安装、一个进程  
   **GIVEN** 全新克隆  
   **WHEN** 使用根依赖与 `.env.example`  
   **THEN** 全部模块由同一进程启动

### 状态

已实现

---

## US-06 密钥仅存于 env

**As a** 平台工程师  
**I want** 非密钥设置内联、密钥只进 `.env`  
**So that** 凭证不入库，本地启动仍有文档可循

### 验收标准

1. **Scenario** 密钥留在环境  
   **GIVEN** 应用配置  
   **WHEN** 检查默认值与 `.env.example`  
   **THEN** 密钥仅来自环境；非密钥可为内联默认

### 状态

已实现

---

## US-07 视觉分数与视频审核

**As a** 兄弟 API 开发者  
**I want** 视觉预测分数与按帧视频审核  
**So that** 产品 BFF 可对结构化分数设阈值

### 验收标准

1. **Scenario** 视觉标签分数  
   **GIVEN** Vision 模块已加载  
   **WHEN** 提交图像预测  
   **THEN** 响应含各标签分数

2. **Scenario** 按帧视频审核  
   **GIVEN** Video 模块已加载  
   **WHEN** 提交短片  
   **THEN** 按帧返回审核结果

### 状态

已实现

---

## US-08 UI 代理与分模块健康

**As a** 测模工程师  
**I want** `web/` 操作台统一走 `/ml` 代理并展示模块健康  
**So that** 不必为六模块维护六个源

### 验收标准

1. **Scenario** 经 `/ml` 代理看健康  
   **GIVEN** UI 指向回环应用  
   **WHEN** 打开操作台  
   **THEN** 经 `/ml` 代理可见各模块健康

### 状态

已实现
