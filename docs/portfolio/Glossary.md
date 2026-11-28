# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 认证 | Auth | 投资者或投顾登录并建立会话的过程。 | 登录 / OAuth |
| 客户 | Customer | 接受投顾服务或自行管理资产的投资者主体。 | 店面下单用户 |
| 账户 | Account | 客户名下用于持仓与资金往来的证券或资金账户。 | 账户/持仓字段 |
| 标的 | Instrument | 可报价、委托与持仓的金融工具。 | 股票 / 债券 / 期权 |
| 市场数据 | MarketData | 标的价格、成交量等行情观测。 | 日线 / Tick |
| 组合 | Portfolio | 客户账户下按策略归集的资产集合。 | 客户稳健组合 |
| 持仓 | Position | 组合内对某一标的的数量与成本状态。 | 100 股 AAPL 多头 |
| 自选 | Watchlist | 客户关注但未必持有的标的列表。 | 科技股关注列表 |
| 行情 | Quote | 标的在某一时点的买卖报价。 | 买一/卖一价 |
| 交易 | Trading | 下达委托并达成买卖的过程。 | 下单 → 成交 |
| 支付 | Payment | 账户相关资金收付的过程。 | Stripe / 银行卡收款 |
| 清算 | Settlement | 成交后证券与资金的交收过程。 | T+1 交收 |
| 分析 | Analytics | 对组合收益、风险与流水的度量分析。 | 收益 / 回撤曲线 |
| 债券 | Bond | 约定还本付息的债权类标的。 | 附息国债 |
| 期权 | Option | 在约定条件买卖标的的权利合约。 | 欧式看涨 |
| 区块链演示 | Blockchain | 以链式账本演示链上资产或交易的能力。 | Sepolia 测试网演示 |
| 横切 | Common | 跨业务域共享的内核与基础设施能力。 | 横切内核 |

## Core Entities | 核心实体

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 客户 | Customer | 与投顾平台建立业务关系的投资者。 | 店面下单用户 |
| 账户 | Account | 记录持仓、资金与交易权限的账户。 | 账户/持仓字段 |
| 标的 | Instrument | 股票、债券、期权等可交易工具。 | 股票 / 债券 / 期权 |
| 组合 | Portfolio | 账户内用于管理持仓的投资组合。 | 客户稳健组合 |
| 持仓 | Position | 组合对某一标的的多空数量与成本。 | 100 股 AAPL 多头 |
| 自选 | Watchlist | 客户自定义的关注标的清单。 | 科技股关注列表 |
| 自选明细 | WatchlistItem | 自选列表中的一条标的记录。 | 自选中的 AAPL |
| 行情 | Quote | 标的最新或深度报价。 | 买一/卖一价 |
| 委托 | Order | 向市场或柜台提交的买卖指令。 | 已提交待支付订单 |
| 成交 | Trade | 委托部分或全部成交后的交易事实。 | 部分成交回报 |
| 支付 | Payment | 一笔资金转入或转出账户的记录。 | Stripe / 银行卡收款 |
| 清算 | Settlement | 成交对应的交收状态与结果。 | T+1 交收 |
| 资金流水 | CashTransaction | 账户现金增减的流水记录。 | 入金 10,000 CNY |
| 风险指标 | RiskMetric | 度量组合或持仓风险的指标。 | VaR；波动率 |
| 市场数据 | MarketData | 持久化保存的行情或市场观测记录。 | 日线 / Tick |
| 债券 | Bond | 具有面值、票息与到期日的债权工具。 | 附息国债 |
| 期权 | Option | 看涨或看跌等权利类型的期权合约。 | 欧式看涨 |
| 会话 | Session | 认证后持续有效的登录会话。 | 登录会话 |
| 用户凭证 | UserCredential | 用于登录校验的凭证材料。 | 密码哈希凭证 |
| 用户偏好 | UserPreference | 投资者对展示与通知等的偏好。 | 默认币种=CNY |

## Domain Kernel | 领域内核

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 实体标识 | Entity ID | 聚合或实体的唯一标识。 | UUID / cuid |
| 类型化标识 | Typed Id | 带类型约束的标识值对象。 | OrderId；UserId |
| 值对象基类 | Abstract Embeddable | 可嵌入实体的值对象基类型。 | @Embeddable 值对象基类 |
| 不可变实体基类 | Abstract Immutable | 创建后不再变更的实体基类型。 | @MappedSuperclass 不可变基类 |
| 可变实体基类 | Abstract Entity | 允许变更并跟踪更新的实体基类型。 | @MappedSuperclass 可变基类 |
| 金额 | Money | 由币种与数值构成的货币量。 | CNY 100.00 |
| 币种 | Currency Code | ISO 4217 货币代码。 | CNY |
| 交易代码 | Symbol | 标的在交易所或目录中的代码。 | AAPL |
| 数量 | Quantity | 委托、成交或持仓的计量数量。 | 100 股 |
| 资产类别 | Asset Class | 标的所属的资产分类。 | Equity；Bond；Option |
| 账户类型 | Account Type | 资金户、证券户等账户分类。 | CASH；MARGIN |
| 账户状态 | Account Status | 账户是否可交易或已冻结等状态。 | ACTIVE；FROZEN |
| 委托方向 | Order Side | 买入或卖出方向。 | BUY；SELL |
| 委托类型 | Order Type | 市价、限价等委托方式。 | MARKET；LIMIT |
| 委托状态 | Order Status | 委托从提交到完成或撤销的状态。 | PENDING；PAID；CANCELLED |
| KYC 状态 | Kyc Status | 客户身份核验所处阶段。 | PENDING；VERIFIED |

## Platforms & Ports | 平台与端口

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| Java API | Java API | 组合业务的主应用编程接口。 | Spring 组合 API |
| Python 分析 API | Python Analytics API | 提供量化与风险分析的接口。 | 风险/分析服务 |
| 管理端 | Admin | 投顾与运营管理客户与账户的界面。 | 运营后台 |
| 门户 | Portal | 投资者使用的 Web 门户。 | 投资者 Web 门户 |
| 移动端 | Mobile | 投资者使用的移动应用。 | 投资者 App |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 流水类型 | Cash Txn Type | 资金流入、流出等流水分类。 | DEPOSIT；WITHDRAW |
| 流水状态 | Cash Txn Status | 资金流水从登记到入账所处的状态。 | POSTED；PENDING |
| 期权类型 | Option Type | 看涨或看跌等期权权利类型。 | CALL；PUT |
| 支付状态 | Payment Status | 资金收付从发起到完成所处的状态。 | CREATED；SUCCEEDED；FAILED |
| 清算状态 | Settlement Status | 交收从待办到完成或失败所处的状态。 | PENDING；SETTLED |
| 账户标识 | Account Id | 账户的唯一标识。 | Account Id 值 |
| 客户标识 | Customer Id | 客户的唯一标识。 | Customer Id 值 |
| 标的标识 | Instrument Id | 金融工具的唯一标识。 | Instrument Id 值 |
| 组合标识 | Portfolio Id | 投资组合的唯一标识。 | Portfolio Id 值 |
| 持仓标识 | Position Id | 持仓记录的唯一标识。 | Position Id 值 |
| 委托标识 | Order Id | 买卖委托的唯一标识。 | Order Id 值 |
| 成交标识 | Trade Id | 成交记录的唯一标识。 | Trade Id 值 |
| 自选标识 | Watchlist Id | 自选列表的唯一标识。 | Watchlist Id 值 |
| 自选明细标识 | Watchlist Item Id | 自选明细的唯一标识。 | Watchlist Item Id 值 |
| 会话标识 | Session Id | 登录会话的唯一标识。 | Session Id 值 |
| 凭证标识 | Credential Id | 用户凭证的唯一标识。 | Credential Id 值 |
| 债券标识 | Bond Id | 债券标的的唯一标识。 | Bond Id 值 |
| 期权标识 | Option Id | 期权合约的唯一标识。 | Option Id 值 |
| 支付标识 | Payment Id | 支付记录的唯一标识。 | Payment Id 值 |
| 清算标识 | Settlement Id | 清算记录的唯一标识。 | Settlement Id 值 |
| 流水标识 | Cash Transaction Id | 资金流水的唯一标识。 | Cash Transaction Id 值 |
| 行情标识 | Market Data Id | 市场数据记录的唯一标识。 | Market Data Id 值 |
| 偏好标识 | Preference Id | 用户偏好的唯一标识。 | Preference Id 值 |
| ISIN | ISIN | 证券国际识别码（ISO 6166）。 | US0378331005 |
| MIC | MIC | 交易场所市场识别码（ISO 10383）。 | XNYS |
| FIX | FIX | 证券交易电子会话与消息协议。 | FIX 4.4 NewOrderSingle |
| CAPM | CAPM | 描述资产预期收益与系统性风险关系的模型。 | E(R)=Rf+β(Rm−Rf) |
| Markowitz 组合 | Markowitz Portfolio | 基于均值—方差优化的投资组合。 | 均值—方差有效前沿 |
