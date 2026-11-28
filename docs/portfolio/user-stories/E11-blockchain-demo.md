# E11 — Blockchain demo

← [用户故事地图](../User-Story-Map.md)

## 背景

Mobile 含钱包连接与测试网 ETH 转账演示路径（如 Sepolia）。

---

## US-23 Blockchain demo

**As an** 投资者（演示）  
**I want** 连接钱包并尝试测试网转账  
**So that** 展示连接状态与转账结果，且不触碰生产资金

### 验收标准

1. **Scenario** 测试网钱包连接  
   **GIVEN** Mobile 区块链页面与测试网配置  
   **WHEN** 连接钱包  
   **THEN** 应用展示连接状态，且不影响生产资金

2. **Scenario** 演示转账反馈  
   **GIVEN** 已在测试网连接钱包  
   **WHEN** 发起演示转账  
   **THEN** 应用展示 JSON-RPC 路径的成功 / 失败反馈

### 状态

已完成
