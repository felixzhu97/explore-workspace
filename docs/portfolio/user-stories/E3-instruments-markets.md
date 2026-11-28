# E3 — Instruments & Markets

← [用户故事地图](../User-Story-Map.md)

## 背景

Position 与 Order 引用标的目录及已存市场观测。

---

## US-06 Instrument catalog

**As a** 用户  
**I want** 经 API 获取 Instrument  
**So that** Position 与 Order 使用稳定的 symbol 与 id

### 验收标准

1. **Scenario** 列出 Instrument  
   **GIVEN** 已灌入的 Instrument  
   **WHEN** `GET /api/v1/instruments`  
   **THEN** 返回带稳定 id / symbol 的 Instrument

### 状态

已完成

---

## US-07 Market data records

**As an** 运营  
**I want** MarketData CRUD  
**So that** 历史观测持久化，供分析与行情使用

### 验收标准

1. **Scenario** MarketData CRUD  
   **GIVEN** MarketData 载荷  
   **WHEN** 调用 MarketData 接口  
   **THEN** 记录按 API 契约持久化

### 状态

已完成
