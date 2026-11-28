# E2 — Customers & Accounts

← [用户故事地图](../User-Story-Map.md)

## 背景

Customer 与 Account 的 CRUD 支撑组合、支付与 admin 工作流。

---

## US-04 Manage customers

**As an** 投顾 / 运营  
**I want** 创建与更新 Customer  
**So that** 客户记录对组合与支付保持准确

### 验收标准

1. **Scenario** Customer CRUD  
   **GIVEN** 已授权访问  
   **WHEN** 调用 Customer CRUD 接口  
   **THEN** Customer 持久化，并按 API 文档列表 / 筛选

### 状态

已完成

---

## US-05 Manage accounts

**As an** 投顾 / 运营  
**I want** 为 Customer 管理 Account  
**So that** 组合与资金挂到正确账户

### 验收标准

1. **Scenario** Account CRUD  
   **GIVEN** 一个 Customer  
   **WHEN** 使用 Account CRUD  
   **THEN** Account 关联该 Customer，并出现在列表 / 详情 API

### 状态

已完成
