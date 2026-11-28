# Glossary | 领域术语表

> FinPulse — Ubiquitous Language（统一语言）

---

## 1. Purpose | 文档说明

本文定义项目 **统一语言**。英文 Preferred Term 为规范名，必须与代码、API、架构命名一致。中文标签仅用于本地化与干系人沟通。

### Maintenance Principles

1. **Glossary first**：术语先入表，再写代码
2. **Code sync**：领域模型变更必须同步更新对应词条
3. **Preferred term**：代码、API、Jira、提交与技术文档一律使用 **Preferred Term (English)**

| Scenario | Rule |
| -------- | ---- |
| Java / Python / API / commits | 使用 Preferred Term (English) |
| Jira / user stories | 英文优先；中文可括注 |
| Frontend i18n | Preferred Term 映射为界面文案 |

---

## 2. Business Domains | 业务域总览

| Preferred Term | 中文 | Java package (`com.finpulse.server`) | Surfaces | API prefix | Notes |
| -------------- | ---- | ------------------------------------- | -------- | ---------- | ----- |
| Auth | 认证 | `auth` | admin / portal / mobile | `/api/v1/auth` | 登录、注册、会话 |
| Customer | 客户 | `customer` | admin / portal | `/api/v1/customers` | 零售 / 投顾客户 |
| Account | 账户 | `account` | admin / portal / mobile | `/api/v1/accounts` | 资金 / 券商账户 |
| Instrument | 标的 | `instrument` | all | `/api/v1/instruments` | 股票及其他上市标的 |
| Bond | 债券 | `bond` | admin / portal | `/api/v1/bonds` | 固收扩展 |
| Option | 期权 | `option` | admin / portal | `/api/v1/options` | 衍生品扩展 |
| Portfolio | 组合 | `portfolio` | all | `/api/v1/portfolios` | 持仓容器 |
| Position | 持仓 | `position` | all | `/api/v1/positions` | 组合内数量 |
| Watchlist | 自选 | `watchlist` | portal / mobile | `/api/v1/watchlists` | 已保存代码 |
| WatchlistItem | 自选明细 | `watchlistitem` | portal / mobile | `/api/v1/watchlist-items` | 代码行 |
| Quote | 行情 | `quote` + Python | all | `/api/v1/quotes` | Java CRUD；Python 实时/历史 |
| Order | 委托 | `order` | portal / mobile / admin | `/api/v1/orders` | 委托单 |
| Trade | 成交 | `trade` | admin / portal | `/api/v1/trades` | 成交记录 |
| Payment | 支付 | `payment` | mobile / admin | `/api/v1/payments` | 入金 / 出金 |
| Settlement | 清算 | `settlement` | admin | `/api/v1/settlements` | 清算指令 |
| CashTransaction | 资金流水 | `cashtransaction` | admin / portal | `/api/v1/cash-transactions` | 账本资金变动 |
| MarketData | 市场数据 | `marketdata` | admin | `/api/v1/market-data` | 已存市场观测 |
| Preference | 偏好 | `preference` | all | `/api/v1/user-preferences` | 用户偏好 |
| Blockchain | 区块链演示 | `blockchain` | mobile | blockchain routes | 演示钱包 / Sepolia |
| Analytics | 分析 | Python (`analytics`) | admin / portal | `/api/v1` analytics / risk / forecast | VaR、事件、MLflow |
| Common | 横切 | `common` | — | — | 共享领域内核基类与值对象 |

---

## 2.5 Domain Kernel | 领域内核

共享持久化与聚合基类位于 `com.finpulse.server.common.domain.model`。特性模块继承这些类型，禁止重复 JPA 映射。

| Preferred Term (English) | 中文 | Definition | Type | Code Mapping | Notes |
| ------------------------ | ---- | ---------- | ---- | ------------ | ----- |
| Entity ID | 实体标识 | 单个聚合的类型化 UUID 标识，用作 `@EmbeddedId` | 值对象 | `@Embeddable` Lombok 类（如 `PortfolioId`，含一个 `UUID value`） | `generateId` / `parseId` / `createId`；不同聚合的 id 永不相等 |
| Abstract Embeddable | 值对象基类 | 嵌入实体的值对象层超类型 | Mapped Superclass | `AbstractEmbeddable` | 实现 `Serializable`（嵌入 id 所需） |
| Abstract Immutable | 不可变实体基类 | 一切实体之根：已分配 id 与创建时间 | Mapped Superclass | `AbstractImmutable<IdT>` | Hibernate 写入 `createdAt`；实现 Spring Data `Persistable` |
| Abstract Entity | 可变实体基类 | 不可变基类 + 更新时间与乐观锁 `@Version` | Mapped Superclass | `AbstractEntity<IdT>` | 脏行 flush 时 Hibernate 写入 `updatedAt` |
| Money | 金额 | 带币种的金额 | 值对象 | `Money` | 嵌入 amount + `CurrencyCode` |
| Currency Code | 币种 | ISO 风格币种代码 | 值对象 | `CurrencyCode` | 嵌入字符串代码 |
| Symbol | 代码 | 交易所 ticker / 标的代码 | 值对象 | `Symbol` | 嵌入字符串 |
| Quantity | 数量 | 持仓或委托的十进制数量 | 值对象 | `Quantity` | 嵌入 `BigDecimal` |

**分层包（按特性模块）：** `controller` → `service` → `domain` ← `infra`（必要时 + `mapper`）。

**领域包：** `domain.model` 承载实体、聚合根、值对象与枚举；`domain.repository` 承载仓储接口。JPA 仓储扩展 Spring Data `Repository<T, ID>`，由 Spring Data 实现。无 `domain.vo`、`domain.service`、`domain.exception` 包。

---

## 3. Core Entities | 核心实体

| Preferred Term | 中文 | Definition |
| -------------- | ---- | ---------- |
| Customer | 客户 | 拥有账户与组合的个人或组织 |
| Account | 账户 | 归属 Customer 的金融账户 |
| Instrument | 标的 | 以 symbol / id 标识的可交易证券 |
| Portfolio | 组合 | Customer / Account 下命名的 Position 集合 |
| Position | 持仓 | Portfolio 内某 Instrument 的数量 |
| Watchlist | 自选 | 用户关注的命名标的列表 |
| Order | 委托 | 买入或卖出 Instrument 的指令 |
| Trade | 成交 | 针对 Order 的成交执行 |
| Payment | 支付 | 与入金或出金相关的资金移动 |
| Settlement | 清算 | 交易后义务清算 |
| Quote | 行情 | Instrument 的价格快照或流 |
| RiskMetric | 风险指标 | Portfolio 的计算风险度量（如 VaR） |

---

## 4. Platforms & Ports | 平台与端口

| Preferred Term | 中文 | Notes |
| -------------- | ---- | ----- |
| Java API | Java API | 主 HTTP 入口，默认 **:8801**（`src/`，Bazel `//:server`） |
| Python Analytics API | Python 分析 API | `analytics` 内 FastAPI，**:8800** |
| Admin | 管理端 | React + Vite（`admin`），通常 :4200 |
| Portal | 门户 | React + Vite（`portal`），通常 :3001 |
| Mobile | 移动端 | React Native + Expo（`mobile`） |

---

## 5. Forbidden synonyms | 禁止同义混用

| Prefer | Avoid |
| ------ | ----- |
| Portfolio | Holding book（作实体名） |
| Position | Holding（意指 Position 时作实体名） |
| Watchlist | Favorites list |
| Instrument | Stock（类型未必是股票时） |
| Customer | User（意指领域 Customer 时） |
| Order | TradeOrder（作 Glossary 首选名；Java 类为 `Order`） |
| Option | OptionContract（作实体名） |
