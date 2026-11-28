# E6 — Orders & Trades

← [用户故事地图](../User-Story-Map.md)

## 背景

Order 下达交易；成交以 Trade 记录呈现给投资者与运营。

---

## US-12 Orders

**As an** 投资者  
**I want** 创建与查看 Order  
**So that** 标的交易可提交并可回查

### 验收标准

1. **Scenario** Order API  
   **GIVEN** 合法委托载荷  
   **WHEN** 调用 Order API  
   **THEN** Order 持久化且可检索

### 状态

已完成

---

## US-13 Trades

**As an** 投资者或运营  
**I want** Trade  
**So that** 成交列表字段与文档一致

### 验收标准

1. **Scenario** Trade API  
   **GIVEN** Trade 记录  
   **WHEN** 调用 Trade API  
   **THEN** 列表 / 详情符合 API 契约（启用时含可选监控字段）

### 状态

已完成
