# E4 — Portfolios & Positions

← [用户故事地图](../User-Story-Map.md)

## 背景

Portfolio 聚合持仓；Position 记录各标的数量。

---

## US-08 Portfolio CRUD

**As an** 投资者或运营  
**I want** Portfolio  
**So that** 持仓按账户或委托分组

### 验收标准

1. **Scenario** Portfolio CRUD  
   **GIVEN** Account / Customer 上下文  
   **WHEN** 使用 Portfolio CRUD  
   **THEN** Portfolio 持久化且可列表

### 状态

已完成

---

## US-09 Positions

**As an** 投资者  
**I want** Portfolio 内的 Position  
**So that** 可见各 Instrument 数量

### 验收标准

1. **Scenario** Position CRUD  
   **GIVEN** Portfolio 与 Instrument  
   **WHEN** 使用 Position CRUD / batch  
   **THEN** Position 反映数量与 Instrument 引用

### 状态

已完成
