# E8 — Analytics & Risk

← [用户故事地图](../User-Story-Map.md)

## 背景

Python Analytics 提供组合聚合、风险摘要、行为事件与预测钩子。

---

## US-16 Portfolio analytics

**As an** 投资者或管理员  
**I want** 组合聚合与风险摘要  
**So that** 无需临时 SQL 即可获得敞口数据

### 验收标准

1. **Scenario** Analytics 接口  
   **GIVEN** 后端服务已启动  
   **WHEN** 调用 analytics / portfolio 与 risk 接口（经 :8801 入口或 :8800）  
   **THEN** 返回聚合 / 风险载荷

### 状态

已完成

---

## US-17 Behavior & forecast

**As an** 管理员  
**I want** 行为事件与预测钩子  
**So that** 产品分析与模型演示具备可存输入输出

### 验收标准

1. **Scenario** 行为事件  
   **GIVEN** 事件载荷  
   **WHEN** `POST /api/v1/analytics/events`  
   **THEN** 事件入库且可列表

2. **Scenario** 预测  
   **GIVEN** MLflow model URI  
   **WHEN** 调用 forecast model 接口  
   **THEN** 返回预测或明确错误

### 状态

已完成
