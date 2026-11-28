# E5 — Watchlists & Quotes

← [用户故事地图](../User-Story-Map.md)

## 背景

投资者在自选中跟踪代码，经 REST 或可用流式通道读取行情。

---

## US-10 Watchlists

**As an** 投资者  
**I want** Watchlist 与明细  
**So that** 关注代码跨会话持久

### 验收标准

1. **Scenario** Watchlist CRUD  
   **GIVEN** 已认证用户  
   **WHEN** 调用 Watchlist 与 WatchlistItem API  
   **THEN** 列表与明细持久化

### 状态

已完成

---

## US-11 Quotes

**As an** 投资者  
**I want** 标的行情  
**So that** portal 与 mobile 可获得当前价格

### 验收标准

1. **Scenario** Quote API  
   **GIVEN** 一组 symbol  
   **WHEN** 调用 Quote API（Java 与/或 Python 聚合 / WS）  
   **THEN** 按 API 文档返回或推送行情载荷

### 状态

已完成
