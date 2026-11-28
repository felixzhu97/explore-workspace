# Glossary | Explore Commerce

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 商品目录 / Catalog | 商店中可供浏览与选购的商品集合（如：春季上新 SKU 目录）。<br>A collection of products available for browsing and purchasing in the store. (e.g. spring new-arrival SKU catalog). |
| 购物车 / Cart | 顾客结账前暂存拟购商品的容器（如：会话内暂存 2 件商品）。<br>A container for temporarily holding items that customers intend to purchase before checkout. (e.g. 2 items staged in session cart). |
| 订单 / Order | 顾客提交后进入履约与支付流程的购买约定（如：待支付订单 #1001）。<br>Purchase agreements that enter the fulfillment and payment process after submission by the customer. (e.g. pending-payment order #1001). |
| 支付 / Payment | 为订单清偿价款的收款过程（如：Stripe / 银行卡扣款）。<br>The process of collecting payment for the settlement of an order. (e.g. Stripe / card charge). |
| 认证 / Auth | 确认顾客或管理员身份的过程（如：口令登录；OAuth 授权码）。<br>The process of confirming the identity of a customer or administrator. (e.g. password login; OAuth auth code). |
| 管理端 / Admin | 运营人员维护商品、订单与用户的后台（如：运营后台管理员）。<br>Operations personnel maintain the back-end for products, orders, and users. (e.g. ops back-office admin). |
| 顾客端 / Customer Web | 顾客浏览商品、加购与结账的店面（如：Next.js 店面首页）。<br>The storefront where customers browse products, add items to their cart, and check out. (e.g. Next.js storefront home). |

## Core Terms | 核心术语

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 商品 / Product | 目录中可单独定价与加购的售卖物（如：蓝 T 恤 M 码）。<br>Items in the catalog that can be priced separately and purchased as add-ons. (e.g. blue T-shirt size M). |
| 购物车行 / Cart Item | 购物车中某一商品及其拟购数量（如：蓝 T 恤 ×2）。<br>An item in the shopping cart and the quantity intended to be purchased. (e.g. blue T-shirt ×2). |
| 订单行 / Order Line | 订单中某一商品及其成交数量与价款（如：SKU×数量×单价）。<br>An item in the order and its transaction quantity and price. (e.g. SKU × qty × unit price). |
| 支付意图 / Payment Intent | 支付服务商侧表示一次收款尝试的对象（如：Stripe PaymentIntent pi_xxx）。<br>Represents the object of a single payment attempt on the payment service provider side. (e.g. Stripe PaymentIntent pi_xxx). |
| 顾客 / Customer | 在店面浏览、下单并支付的终端用户（如：店面下单用户 alice）。<br>End users who browse, place orders, and make payments in the store. (e.g. storefront buyer alice). |
| 结账 / Checkout | 从购物车确认订单并发起支付的过程（如：购物车 → 填地址 → 支付）。<br>The process of confirming an order from the shopping cart and initiating payment. (e.g. cart → address → pay). |
| 履约 / Fulfillment | 订单支付后交付商品或服务的过程（如：已支付订单发货）。<br>The process of delivering goods or services after an order has been paid for. (e.g. ship a paid order). |
| 库存 / Inventory | 某商品当前可售数量（如：蓝 T 恤可售 42 件）。<br>The current available quantity of a product. (e.g. blue T-shirt stock 42). |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 店面 / Storefront | 面向顾客展示商品与承接加购的销售界面。<br>A sales interface for displaying products to customers and accepting additional purchases. |
| 订单状态 / Order Status | 订单从提交、支付到履约或取消所处的状态。<br>The status of an order from submission, payment to fulfillment or cancellation. |
| 支付状态 / Payment Status | 收款从创建到成功或失败所处的状态。<br>The status of the payment from creation to success or failure. |
| 取消 / Cancellation | 按规则终止尚未完成履约的订单。<br>Terminate orders that have not yet been fulfilled in accordance with the rules. |
| 幂等 / Idempotency | 同一操作重复执行结果保持不变的性质。<br>The property that the results of repeated executions of the same operation remain unchanged. |
| Saga / Saga | 由可补偿步骤组成的长事务协作。<br>Long transaction collaboration consisting of compensatable steps. |
| Outbox / Outbox | 与业务变更同事务写出、再可靠投递的消息模式。<br>A message model that writes messages in the same transaction as business changes and delivers them reliably again. |
| PCI DSS / PCI DSS | 支付卡数据安全的行业标准要求。<br>Industry standard requirements for payment-card data security. |
| ISO 4217 / ISO 4217 | 表示货币的国际标准代码体系。<br>An international standard code system for representing currencies. |
| AI Agent BFF / AI Agent BFF | 经商务系统代理调用外部智能体能力的中间层。<br>An intermediate layer that enables the business system to invoke external agent capabilities through proxy. |
| SKU / SKU | 库存保管单元，标识可独立库存与计价的商品规格。<br>Inventory storage unit, identifying product specifications that can be inventoried and valued independently. |
