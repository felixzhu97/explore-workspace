# E9 — Admin, Portal, Mobile surfaces

← [用户故事地图](../User-Story-Map.md)

## 背景

Admin、portal 与 mobile 共用 Java API 入口与共享包。

---

## US-18 Admin console

**As an** 运营  
**I want** Admin Web 应用  
**So that** 可在浏览器管理客户、组合与流水

### 验收标准

1. **Scenario** Admin 对接 API  
   **GIVEN** `pnpm dev:admin`  
   **WHEN** Admin 指向 :8801  
   **THEN** 核心管理页对接 API 加载

### 状态

已完成

---

## US-19 Portal

**As an** 投资者  
**I want** Portal Web 应用  
**So that** 浏览器可用组合与市场能力

### 验收标准

1. **Scenario** Portal 对接灌数  
   **GIVEN** `pnpm dev:portal`  
   **WHEN** Portal 对接 :8801 运行  
   **THEN** 灌数场景下组合 / 市场流程可用

### 状态

已完成

---

## US-20 Mobile

**As an** 投资者  
**I want** 移动应用  
**So that** 设备上加载仪表盘与自选数据

### 验收标准

1. **Scenario** Mobile 仪表盘  
   **GIVEN** 已配置 `EXPO_PUBLIC_API_BASE_URL` 的 Expo mobile  
   **WHEN** 打开仪表盘 / 自选  
   **THEN** 数据自 API 入口加载

### 状态

已完成
