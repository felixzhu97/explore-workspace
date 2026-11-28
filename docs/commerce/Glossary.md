# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 商品目录 | Catalog | 商店中可供浏览与选购的商品集合。 | 春季上新目录 |
| 购物车 | Cart | 顾客结账前暂存拟购商品的容器。 | 会话内暂存商品 |
| 订单 | Order | 顾客提交后进入履约与支付流程的购买约定。 | 已提交待支付订单 |
| 支付 | Payment | 为订单清偿价款的收款过程。 | Stripe / 银行卡收款 |
| 认证 | Auth | 确认顾客或管理员身份的过程。 | 登录 / OAuth |
| 管理端 | Admin | 运营人员维护商品、订单与用户的后台。 | 运营后台 |
| 顾客端 | Customer Web | 顾客浏览商品、加购与结账的店面。 | Next.js 店面 |

## Core Terms | 核心术语

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 商品 | Product | 目录中可单独定价与加购的售卖物。 | 蓝 T 恤 M 码 |
| 购物车行 | Cart Item | 购物车中某一商品及其拟购数量。 | 蓝 T 恤 ×2 |
| 订单行 | Order Line | 订单中某一商品及其成交数量与价款。 | SKU × 数量 × 单价 |
| 支付意图 | Payment Intent | 支付服务商侧表示一次收款尝试的对象。 | Stripe PaymentIntent |
| 顾客 | Customer | 在店面浏览、下单并支付的终端用户。 | 店面下单用户 |
| 结账 | Checkout | 从购物车确认订单并发起支付的过程。 | 购物车 → 支付 |
| 履约 | Fulfillment | 订单支付后交付商品或服务的过程。 | 已发货 |
| 库存 | Inventory | 某商品当前可售数量。 | 可售 42 件 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 店面 | Storefront | 面向顾客展示商品与承接加购的销售界面。 | / 商品列表页 |
| 订单状态 | Order Status | 订单从提交、支付到履约或取消所处的状态。 | PENDING；PAID；CANCELLED |
| 支付状态 | Payment Status | 收款从创建到成功或失败所处的状态。 | CREATED；SUCCEEDED；FAILED |
| 取消 | Cancellation | 按规则终止尚未完成履约的订单。 | 未履约前取消 |
| 幂等 | Idempotency | 同一操作重复执行结果保持不变的性质。 | Idempotency-Key 头 |
| Saga | Saga | 由可补偿步骤组成的长事务协作。 | 下单 → 支付 → 补偿取消 |
| Outbox | Outbox | 与业务变更同事务写出、再可靠投递的消息模式。 | 同事务写 outbox 再投递 |
| PCI DSS | PCI DSS | 支付卡数据安全的行业标准要求。 | 不落盘 PAN |
| ISO 4217 | ISO 4217 | 表示货币的国际标准代码体系。 | USD；CNY；EUR |
| AI Agent BFF | AI Agent BFF | 经商务系统代理调用外部智能体能力的中间层。 | Commerce → Gallery Agent |
| SKU | SKU | 库存保管单元，标识可独立库存与计价的商品规格。 | SKU-TEE-BLU-M |
