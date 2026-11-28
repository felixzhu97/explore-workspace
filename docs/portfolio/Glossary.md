# Glossary | Explore Portfolio

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 认证 / Auth | 投资者或投顾登录并建立会话的过程（如：口令登录；OAuth 授权码）。<br>The process by which investors or investment advisors log in and establish a session. (e.g. password login; OAuth auth code). |
| 客户 / Customer | 接受投顾服务或自行管理资产的投资者主体（如：店面下单用户 alice）。<br>Investors who receive investment advisory services or manage their assets themselves. (e.g. storefront buyer alice). |
| 账户 / Account | 客户名下用于持仓与资金往来的证券或资金账户（如：alice@example.com 账号）。<br>Securities or funds accounts held by the client for holding positions and conducting fund transactions. (e.g. alice@example.com account). |
| 标的 / Instrument | 可报价、委托与持仓的金融工具（如：AAPL 普通股）。<br>Financial instruments that can be quoted, ordered, and held. (e.g. AAPL common stock). |
| 市场数据 / MarketData | 标的价格、成交量等行情观测（如：AAPL 日线 OHLCV）。<br>Observation of market conditions such as the price of the underlying asset and trading volume. (e.g. AAPL daily OHLCV). |
| 组合 / Portfolio | 客户账户下按策略归集的资产集合（如：「稳健」组合净值）。<br>A collection of assets grouped by strategy under a client account. (e.g. "Conservative" portfolio NAV). |
| 持仓 / Position | 组合内对某一标的的数量与成本状态（如：100 股 AAPL 多头）。<br>Quantity and cost status of a specific item within the portfolio. (e.g. long 100 shares of AAPL). |
| 自选 / Watchlist | 客户关注但未必持有的标的列表（如：自选 AAPL, MSFT）。<br>A list of securities that clients are interested in but may not necessarily hold. (e.g. watchlist AAPL, MSFT). |
| 行情 / Quote | 标的在某一时点的买卖报价（如：买一 189.2 / 卖一 189.3）。<br>The bid and ask prices of an underlying asset at a specific point in time. (e.g. bid 189.2 / ask 189.3). |
| 交易 / Trading | 下达委托并达成买卖的过程（如：限价买 100 股 AAPL）。<br>The process of placing an order and executing a trade. (e.g. limit buy 100 shares AAPL). |
| 支付 / Payment | 账户相关资金收付的过程（如：Stripe / 银行卡扣款）。<br>The process of receiving and paying funds related to the account. (e.g. Stripe / card charge). |
| 清算 / Settlement | 成交后证券与资金的交收过程（如：T+1 证券交收）。<br>The process of settling securities and funds after a trade. (e.g. T+1 securities settlement). |
| 分析 / Analytics | 对组合收益、风险与流水的度量分析。<br>Measurement analysis of portfolio returns, risks, and turnover. |
| 债券 / Bond | 约定还本付息的债权类标的。<br>Debt-type underlying assets with agreed principal and interest payments. |
| 期权 / Option | 在约定条件买卖标的的权利合约。<br>A contract granting the right to buy or sell the subject matter under agreed-upon terms. |
| 区块链演示 / Blockchain | 以链式账本演示链上资产或交易的能力。<br>The ability to demonstrate on-chain assets or transactions using a blockchain ledger. |
| 横切 / Common | 跨业务域共享的内核与基础设施能力（如：跨域共享内核（异常、基类））。<br>Core and infrastructure capabilities shared across business domains. (e.g. shared cross-cutting kernel (exceptions, bases)). |

## Core Entities | 核心实体

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 客户 / Customer | 与投顾平台建立业务关系的投资者（如：店面下单用户 alice）。<br>Investors who have established a business relationship with the investment advisory platform. (e.g. storefront buyer alice). |
| 账户 / Account | 记录持仓、资金与交易权限的账户（如：alice@example.com 账号）。<br>An account that records positions, funds, and trading permissions. (e.g. alice@example.com account). |
| 标的 / Instrument | 股票、债券、期权等可交易工具（如：AAPL 普通股）。<br>Tradable instruments such as stocks, bonds, and options. (e.g. AAPL common stock). |
| 组合 / Portfolio | 账户内用于管理持仓的投资组合（如：「稳健」组合净值）。<br>Portfolio used to manage positions within the account. (e.g. "Conservative" portfolio NAV). |
| 持仓 / Position | 组合对某一标的的多空数量与成本（如：100 股 AAPL 多头）。<br>The long and short positions and costs of the portfolio for a particular underlying asset. (e.g. long 100 shares of AAPL). |
| 自选 / Watchlist | 客户自定义的关注标的清单（如：自选 AAPL, MSFT）。<br>Customer-defined list of watch items. (e.g. watchlist AAPL, MSFT). |
| 自选明细 / WatchlistItem | 自选列表中的一条标的记录。<br>A target record from the selected list. |
| 行情 / Quote | 标的最新或深度报价（如：买一 189.2 / 卖一 189.3）。<br>The latest or deep quote for the underlying asset. (e.g. bid 189.2 / ask 189.3). |
| 委托 / Order | 向市场或柜台提交的买卖指令（如：待支付订单 #1001）。<br>Trading orders submitted to the market or over the counter. (e.g. pending-payment order #1001). |
| 成交 / Trade | 委托部分或全部成交后的交易事实。<br>The facts of the transaction after partial or full execution of the order. |
| 支付 / Payment | 一笔资金转入或转出账户的记录（如：Stripe / 银行卡扣款）。<br>A record of funds being transferred into or out of an account. (e.g. Stripe / card charge). |
| 清算 / Settlement | 成交对应的交收状态与结果（如：T+1 证券交收）。<br>Settlement status and result corresponding to the transaction. (e.g. T+1 securities settlement). |
| 资金流水 / CashTransaction | 账户现金增减的流水记录。<br>Transaction history of cash increases and decreases in the account. |
| 风险指标 / RiskMetric | 度量组合或持仓风险的指标。<br>An indicator that measures the risk of a portfolio or position. |
| 市场数据 / MarketData | 持久化保存的行情或市场观测记录（如：AAPL 日线 OHLCV）。<br>Persistently stored market quotes or market observation records. (e.g. AAPL daily OHLCV). |
| 债券 / Bond | 具有面值、票息与到期日的债权工具。<br>Debt instruments with a face value, coupon, and maturity date. |
| 期权 / Option | 看涨或看跌等权利类型的期权合约。<br>Option contracts with right types such as call or put. |
| 会话 / Session | 认证后持续有效的登录会话（如：JSESSIONID 登录会话）。<br>Login session that remains valid after authentication. (e.g. JSESSIONID login session). |
| 用户凭证 / UserCredential | 用于登录校验的凭证材料。<br>Credential material used for login verification. |
| 用户偏好 / UserPreference | 投资者对展示与通知等的偏好。<br>Investor preferences for presentations, notifications, etc. |

## Domain Kernel | 领域内核

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 实体标识 / Entity ID | 聚合或实体的唯一标识（如：UUID 主键）。<br>Unique identifier of the aggregate or entity. (e.g. UUID primary key). |
| 类型化标识 / Typed Id | 带类型约束的标识值对象。<br>An identity value object with type constraints. |
| 值对象基类 / Abstract Embeddable | 可嵌入实体的值对象基类型（如：@Embeddable Money）。<br>Base type of value object that can be embedded in an entity. (e.g. @Embeddable Money). |
| 不可变实体基类 / Abstract Immutable | 创建后不再变更的实体基类型（如：创建后不可变基类）。<br>Entity base type that does not change after creation. (e.g. immutable-after-create base class). |
| 可变实体基类 / Abstract Entity | 允许变更并跟踪更新的实体基类型（如：带 @Version 的可变基类）。<br>The base type of entity that allows changes and tracks updates. (e.g. mutable base class with @Version). |
| 金额 / Money | 由币种与数值构成的货币量。<br>Monetary amount consisting of currency and value. |
| 币种 / Currency Code | ISO 4217 货币代码。<br>ISO4217 currency code. |
| 交易代码 / Symbol | 标的在交易所或目录中的代码。<br>The code of the instrument on the exchange or in the catalog. |
| 数量 / Quantity | 委托、成交或持仓的计量数量。<br>The measured quantity of orders, trades, or positions. |
| 资产类别 / Asset Class | 标的所属的资产分类。<br>The asset classification to which the subject belongs. |
| 账户类型 / Account Type | 资金户、证券户等账户分类。<br>Account classifications such as cash accounts and securities accounts. |
| 账户状态 / Account Status | 账户是否可交易或已冻结等状态。<br>The status of the account, such as whether it is tradable or frozen. |
| 委托方向 / Order Side | 买入或卖出方向。<br>Buy or sell direction. |
| 委托类型 / Order Type | 市价、限价等委托方式。<br>Order types such as market orders and limit orders. |
| 委托状态 / Order Status | 委托从提交到完成或撤销的状态。<br>The status of the request from submission to completion or cancellation. |
| KYC 状态 / Kyc Status | 客户身份核验所处阶段。<br>Stage of customer identity verification. |

## Platforms & Ports | 平台与端口

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| Java API / Java API | 组合业务的主应用编程接口。<br>The main application programming interface for the portfolio business. |
| Python 分析 API / Python Analytics API | 提供量化与风险分析的接口。<br>Provides an interface for quantitative and risk analysis. |
| 管理端 / Admin | 投顾与运营管理客户与账户的界面（如：运营后台管理员）。<br>The interface between investment advisors and operations management for clients and accounts. (e.g. ops back-office admin). |
| 门户 / Portal | 投资者使用的 Web 门户。<br>Web portal used by investors. |
| 移动端 / Mobile | 投资者使用的移动应用。<br>Mobile app used by investors. |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 流水类型 / Cash Txn Type | 资金流入、流出等流水分类。<br>Cash flow categories such as inflows and outflows. |
| 流水状态 / Cash Txn Status | 资金流水从登记到入账所处的状态。<br>The status of the fund flow from registration to entry. |
| 期权类型 / Option Type | 看涨或看跌等期权权利类型。<br>Option right types such as call or put. |
| 支付状态 / Payment Status | 资金收付从发起到完成所处的状态。<br>The status of fund receipts and payments from initiation to completion. |
| 清算状态 / Settlement Status | 交收从待办到完成或失败所处的状态。<br>The status of the settlement, from pending to completed or failed. |
| 账户标识 / Account Id | 账户的唯一标识。<br>Unique identifier for the account. |
| 客户标识 / Customer Id | 客户的唯一标识。<br>Unique identifier for the customer. |
| 标的标识 / Instrument Id | 金融工具的唯一标识。<br>Unique identifier of the financial instrument. |
| 组合标识 / Portfolio Id | 投资组合的唯一标识。<br>Unique identifier for the portfolio. |
| 持仓标识 / Position Id | 持仓记录的唯一标识。<br>Unique identifier for the position record. |
| 委托标识 / Order Id | 买卖委托的唯一标识。<br>Unique identifier for the trading order. |
| 成交标识 / Trade Id | 成交记录的唯一标识。<br>Unique identifier for the transaction record. |
| 自选标识 / Watchlist Id | 自选列表的唯一标识。<br>Unique identifier for the custom list. |
| 自选明细标识 / Watchlist Item Id | 自选明细的唯一标识。<br>Unique identifier for the custom line item. |
| 会话标识 / Session Id | 登录会话的唯一标识（如：sess_01HXYZ…）。<br>Unique identifier for the login session. (e.g. sess_01HXYZ…). |
| 凭证标识 / Credential Id | 用户凭证的唯一标识。<br>Unique identifier for the user credential. |
| 债券标识 / Bond Id | 债券标的的唯一标识。<br>Unique identifier of the bond. |
| 期权标识 / Option Id | 期权合约的唯一标识。<br>Unique identifier of the options contract. |
| 支付标识 / Payment Id | 支付记录的唯一标识。<br>Unique identifier for the payment record. |
| 清算标识 / Settlement Id | 清算记录的唯一标识。<br>Unique identifier for the settlement record. |
| 流水标识 / Cash Transaction Id | 资金流水的唯一标识。<br>Unique identifier for the cash flow. |
| 行情标识 / Market Data Id | 市场数据记录的唯一标识。<br>Unique identifier for market data records. |
| 偏好标识 / Preference Id | 用户偏好的唯一标识。<br>Unique identifier for user preferences. |
| ISIN / ISIN | 证券国际识别码（ISO 6166）。<br>International Securities Identification Number (ISO 6166). |
| MIC / MIC | 交易场所市场识别码（ISO 10383）。<br>Trading Venue Market Identifier Code (ISO 10383). |
| FIX / FIX | 证券交易电子会话与消息协议。<br>Electronic session and messaging protocol for securities trading. |
| CAPM / CAPM | 描述资产预期收益与系统性风险关系的模型。<br>A model that describes the relationship between the expected return on an asset and systematic risk. |
| Markowitz 组合 / Markowitz Portfolio | 基于均值—方差优化的投资组合。<br>Portfolio based on mean-variance optimization. |
