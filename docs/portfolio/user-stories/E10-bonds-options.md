# E10 — Bonds & Options

← [用户故事地图](../User-Story-Map.md)

## 背景

Bond 与 Option 资源扩展 Instrument 模型，覆盖固收与衍生品。

---

## US-21 Bonds

**As an** 运营 / 投资者  
**I want** Bond 资源  
**So that** 固收持仓可关联 Instrument

### 验收标准

1. **Scenario** Bond API  
   **GIVEN** Bond 载荷  
   **WHEN** 调用 Bond API  
   **THEN** Bond 持久化，并按设计关联 Instrument

### 状态

已完成

---

## US-22 Options

**As an** 运营 / 投资者  
**I want** Option 资源  
**So that** 期权合约按 API 契约持久化

### 验收标准

1. **Scenario** Option API  
   **GIVEN** Option 载荷  
   **WHEN** 调用 Option API  
   **THEN** Option 按 API 契约持久化

### 状态

已完成
