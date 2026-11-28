# Glossary | Explore Low-Code

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 画布 / Canvas | 可视化编排界面的工作区（如：拖放 Button 到布局区）。<br>Workspace for the visual orchestration interface. (e.g. 拖放 Button 到布局区). |
| 组件 / Component | 可复用的界面或功能单元（如：Button / Chart 组件）。<br>Reusable interface or functional unit. (e.g. Button / Chart 组件). |
| 属性配置 / Properties | 对象可配置的属性集合（如：右侧改 width、color）。<br>Set of configurable properties for the object. (e.g. 右侧改 width、color). |
| 模板 / Template | 可复用的预置结构（如：落地页模板一键套用）。<br>Reusable preset structure. (e.g. 落地页模板一键套用). |
| 主题 / Theme | 界面或场景的视觉外观方案（如：亮色 / 暗色外观）。<br>The visual appearance scheme of an interface or scene. (e.g. 亮色 / 暗色外观). |
| 动画 / Animation | 随时间变化的视觉过渡（如：入场 fade-in 200ms）。<br>Visual transitions that change over time. (e.g. 入场 fade-in 200ms). |
| 数据绑定 / Data Binding | 界面与数据源之间的关联（如：Input.value ← user.name）。<br>The association between the interface and the data source. (e.g. Input.value ← user.name). |
| 数据源 / Data Source | 提供数据的来源（如：GET /api/users JSON）。<br>Source of the data provided. (e.g. GET /api/users JSON). |
| 表单构建 / Form Builder | 表单的可视化构造器（如：拖「姓名」「邮箱」项）。<br>Visual builder for forms. (e.g. 拖「姓名」「邮箱」项). |
| 图表 / Chart | 数据的图形化表示（如：月销量折线图）。<br>Graphical representation of data. (e.g. 月销量折线图). |
| 代码导出 / Code Export | 将设计导出为源代码（如：导出 React JSX）。<br>Export the design as source code. (e.g. 导出 React JSX). |
| 历史记录 / History | 过往交互或变更的记录（如：Ctrl+Z 撤销栈）。<br>Records of past interactions or changes. (e.g. Ctrl+Z 撤销栈). |
| 持久化 / Persistence | 将领域状态保存到存储的过程。<br>The process of saving the field state to storage. (e.g. 将领域状态保存到存储的过程). |
| 协作 / Collaboration | 多人同时编辑与共享。<br>Multiple people can edit and share simultaneously. (e.g. 多人同时编辑与共享). |
| 共享 UI / Shared UI | 跨应用共享的用户界面组件。<br>User interface components shared across applications. (e.g. 跨应用共享的用户界面组件). |
| 共享 hooks / Shared hooks | 跨界面共享的钩子逻辑（如：跨页面复用 hooks）。<br>Hook logic shared across interfaces. (e.g. 跨页面复用 hooks). |
| 共享工具 / Shared lib | 跨模块共享的程序库（如：共享工具函数库）。<br>Libraries shared across modules. (e.g. 共享工具函数库). |

## Core Terms | 核心术语

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 组件实例 / Component Instance | 组件的一次具体实例（如：<Button label=提交 />）。<br>A concrete instance of a component. (e.g. <Button label=提交 />). |
| 组件工厂 / Component Factory | 创建组件实例的工厂（如：按类型创建组件实例）。<br>Factory for creating component instances. (e.g. 按类型创建组件实例). |
| 组件管理 / Component Management | 组件的登记与生命周期管理（如：注册/启停组件）。<br>Component registration and lifecycle management. (e.g. 注册/启停组件). |
| 组件面板 / Component Panel | 选择与配置组件的面板（如：左侧组件面板）。<br>Panel for selecting and configuring components. (e.g. 左侧组件面板). |
| 组件树 / Component Tree | 组件的层级树（如：页面组件树）。<br>Hierarchy tree of components. (e.g. 页面组件树). |
| 组件渲染器 / Component Renderer | 将组件渲染为界面的器（如：把节点渲成 React）。<br>A tool that renders components as an interface. (e.g. 把节点渲成 React). |
| 预览画布 / Preview Canvas | 用于预览效果的画布。<br>Canvas for previewing effects. (e.g. 用于预览效果的画布). |
| 设备预览 / Device Preview | 按目标设备预览界面。<br>Preview interface by target device. (e.g. 按目标设备预览界面). |
| 自定义组件 / Custom Component | 用户自定义组件。<br>User-defined components. (e.g. 用户自定义组件). |
| 模板库 / Template Gallery | 模板陈列与选用。<br>Formwork display and selection. (e.g. 模板陈列与选用). |
| 主题配置 / Theme Config | 主题的配置（如：primaryColor、fontFamily）。<br>Theme configuration. (e.g. primaryColor、fontFamily). |
| 撤销 / 重做 / Undo / Redo | 撤销或重做最近变更。<br>Undo or redo the most recent change. (e.g. 撤销或重做最近变更). |
| 本地持久化 / Local Persistence | 在本地存储中的持久化（如：localStorage 存画布）。<br>Persistence in local storage. (e.g. localStorage 存画布). |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 页面 / Page | 由画布与组件构成的可编辑界面单元。<br>An editable interface unit consisting of a canvas and components. (e.g. 由画布与组件构成的可编辑界面单元). |
| 低代码 / Low-code | 以可视化构造为主、编码为辅的开发方式。<br>A development approach primarily based on visual construction, with coding as a secondary component. (e.g. 以可视化构造为主、编码为辅的开发方式). |
| 设计令牌 / Design Tokens | 可跨平台交换的设计决策令牌（如：color.primary=#0A84FF）。<br>Design decision tokens that can be exchanged across platforms. (e.g. color.primary=#0A84FF). |
| 领域特定语言 / DSL | 面向特定领域的专用语言。<br>Domain-specific language. (e.g. 面向特定领域的专用语言). |
| WCAG / WCAG | Web 内容无障碍指南。<br>Web Content Accessibility Guidelines. (e.g. Web 内容无障碍指南). |
| ARIA / ARIA | 为辅助技术描述可访问名称、角色与状态的规范。<br>Specifications for describing accessible names, roles, and states for assistive technology. (e.g. 为辅助技术描述可访问名称、角色与状态的规范). |
| 人机界面指南 / HIG | 平台人机界面设计惯例。<br>Platform human-interface design conventions. (e.g. 平台人机界面设计惯例). |
| 空状态 / Empty State | 尚无内容时的占位呈现。<br>Placeholder display when there is no content yet. (e.g. 尚无内容时的占位呈现). |
| 动效 / Effect | 可组合的视觉或交互动效（如：hover 高亮；过渡动画）。<br>Combinable visual or interactive effects. (e.g. hover 高亮；过渡动画). |
