# E7 — Payments & Settlements

← [用户故事地图](../User-Story-Map.md)

## 背景

Payment 记录资金活动；Settlement 与 CashTransaction 记录交易后资金变动。

---

## US-14 Payments

**As an** 投资者  
**I want** 提交 Payment  
**So that** 账户入金被记录

### 验收标准

1. **Scenario** Payment API  
   **GIVEN** 合法支付载荷  
   **WHEN** 调用 Payment API  
   **THEN** Payment 持久化（启用评分时可能含欺诈字段）

### 状态

已完成

---

## US-15 Settlements & cash

**As an** 运营  
**I want** Settlement 与 CashTransaction  
**So that** 交易后资金变动可追踪

### 验收标准

1. **Scenario** Settlement 与资金 API  
   **GIVEN** Settlement / 资金载荷  
   **WHEN** 调用对应 API  
   **THEN** 记录按 API 文档持久化

### 状态

已完成
