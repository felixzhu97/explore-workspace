# E1 — Auth

← [用户故事地图](../User-Story-Map.md)

## 背景

注册、登录与会话接口门禁 FinPulse 的 portal、admin 与 mobile。

---

## US-01 Register

**As a** 访客  
**I want** 以邮箱与密码注册  
**So that** 形成可供后续登录的 Customer 记录

### 验收标准

1. **Scenario** 合法注册  
   **GIVEN** 合法注册载荷  
   **WHEN** `POST /api/v1/auth/register`  
   **THEN** API 返回 token + customer，并持久化用户

2. **Scenario** 拒绝重复邮箱  
   **GIVEN** 已存在的邮箱  
   **WHEN** 尝试注册  
   **THEN** API 返回客户端错误，不创建第二用户

### 状态

已完成

---

## US-02 Login

**As a** 已注册用户  
**I want** 登录  
**So that** 获得调用 API 的 Bearer token

### 验收标准

1. **Scenario** 合法凭证  
   **GIVEN** 合法凭证  
   **WHEN** `POST /api/v1/auth/login`  
   **THEN** API 返回 `{ token, customer }`

2. **Scenario** 拒绝非法凭证  
   **GIVEN** 非法凭证  
   **WHEN** 尝试登录  
   **THEN** API 拒绝请求

### 状态

已完成

---

## US-03 Session

**As a** 已登录用户  
**I want** `/auth/me`、登出与改密  
**So that** 可查看或结束当前会话

### 验收标准

1. **Scenario** 当前用户  
   **GIVEN** 合法 Bearer token  
   **WHEN** `GET /api/v1/auth/me`  
   **THEN** 返回当前 customer

2. **Scenario** 登出  
   **GIVEN** 合法 Bearer token  
   **WHEN** `POST /api/v1/auth/logout`  
   **THEN** 会话失效（204）

### 状态

已完成
