# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 画布 | Canvas | 可视化编排界面的工作区。 | 拖放 Button 到布局区 |
| 组件 | Component | 可复用的界面或功能单元。 | Button / Chart 组件 |
| 属性配置 | Properties | 对象可配置的属性集合。 | 右侧改 width、color |
| 模板 | Template | 可复用的预置结构。 | 落地页模板一键套用 |
| 主题 | Theme | 界面或场景的视觉外观方案。 | 亮色 / 暗色外观 |
| 动画 | Animation | 随时间变化的视觉过渡。 | 入场 fade-in 200ms |
| 数据绑定 | Data Binding | 界面与数据源之间的关联。 | Input.value ← user.name |
| 数据源 | Data Source | 提供数据的来源。 | GET /api/users JSON |
| 表单构建 | Form Builder | 表单的可视化构造器。 | 拖「姓名」「邮箱」项 |
| 图表 | Chart | 数据的图形化表示。 | 月销量折线图 |
| 代码导出 | Code Export | 将设计导出为源代码。 | 导出 React JSX |
| 历史记录 | History | 过往交互或变更的记录。 | Ctrl+Z 撤销栈 |
| 持久化 | Persistence | 将领域状态保存到存储的过程。 | 将领域状态保存到存储的过程 |
| 协作 | Collaboration | 多人同时编辑与共享。 | 多人同时编辑与共享 |
| 共享 UI | Shared UI | 跨应用共享的用户界面组件。 | 跨应用共享的用户界面组件 |
| 共享 hooks | Shared hooks | 跨界面共享的钩子逻辑。 | 跨页面复用 hooks |
| 共享工具 | Shared lib | 跨模块共享的程序库。 | 共享工具函数库 |

## Core Terms | 核心术语

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 组件实例 | Component Instance | 组件的一次具体实例。 | <Button label=提交 /> |
| 组件工厂 | Component Factory | 创建组件实例的工厂。 | 按类型创建组件实例 |
| 组件管理 | Component Management | 组件的登记与生命周期管理。 | 注册/启停组件 |
| 组件面板 | Component Panel | 选择与配置组件的面板。 | 左侧组件面板 |
| 组件树 | Component Tree | 组件的层级树。 | 页面组件树 |
| 组件渲染器 | Component Renderer | 将组件渲染为界面的器。 | 把节点渲成 React |
| 预览画布 | Preview Canvas | 用于预览效果的画布。 | 用于预览效果的画布 |
| 设备预览 | Device Preview | 按目标设备预览界面。 | 按目标设备预览界面 |
| 自定义组件 | Custom Component | 用户自定义组件。 | 用户自定义组件 |
| 模板库 | Template Gallery | 模板陈列与选用。 | 模板陈列与选用 |
| 主题配置 | Theme Config | 主题的配置。 | primaryColor、fontFamily |
| 撤销 / 重做 | Undo / Redo | 撤销或重做最近变更。 | 撤销或重做最近变更 |
| 本地持久化 | Local Persistence | 在本地存储中的持久化。 | localStorage 存画布 |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 页面 | Page | 由画布与组件构成的可编辑界面单元。 | 由画布与组件构成的可编辑界面单元 |
| 低代码 | Low-code | 以可视化构造为主、编码为辅的开发方式。 | 以可视化构造为主、编码为辅的开发方式 |
| 设计令牌 | Design Tokens | 可跨平台交换的设计决策令牌。 | color.primary=#0A84FF |
| 领域特定语言 | DSL | 面向特定领域的专用语言。 | 面向特定领域的专用语言 |
| WCAG | WCAG | Web 内容无障碍指南。 | Web 内容无障碍指南 |
| ARIA | ARIA | 为辅助技术描述可访问名称、角色与状态的规范。 | 为辅助技术描述可访问名称、角色与状态的规范 |
| 人机界面指南 | HIG | 平台人机界面设计惯例。 | 平台人机界面设计惯例 |
| 空状态 | Empty State | 尚无内容时的占位呈现。 | 尚无内容时的占位呈现 |
| 动效 | Effect | 可组合的视觉或交互动效。 | hover 高亮；过渡动画 |
