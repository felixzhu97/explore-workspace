# Glossary | 领域术语表

> Explore Three — Ubiquitous Language（统一语言）

交互式 3D 地球仪（Three.js + Angular）：陆地点阵、闪烁着色器、地理坐标间飞线动画。

---

## 1. Purpose | 文档说明

本文定义项目**统一语言**。英文 Preferred Term 为规范名，须与代码、架构命名一致；中文仅供本地化与干系人沟通。

### Maintenance Principles

1. **Glossary first**：先改术语表，再改代码
2. **Code sync**：领域语言变更必须回写对应词条
3. **Preferred term**：代码、提交与技术文档一律用 Preferred Term（English）

### Naming | 命名

| Kind | Rule | Example |
| --- | --- | --- |
| Product type | 名词 | `GeoCoord`、`FlightRoute`、`ThemeAppearance` |
| Angular page | `*.page.ts` / `*PageComponent` | `globe-light.page.ts` |
| Angular service | `*.service.ts` | `globe.service.ts` |
| Angular component | `*.component.ts` | `globe-host.component.ts` |
| Other files | kebab-case 对齐类名 | `flight-arc.ts` → `FlightArc` |
| Resource cleanup | Three.js `dispose` | `dispose` |
| Barrel `index.ts` | 避免 | 直接 import 具体文件 |

### Layers (by business domain folder)

```
app (shell) → globe / route / theme / geography
```

无顶层 `domain/` 或 `infra/` 包。类型落在所属 `*.service.ts`（地理原语见 `geo-coord.ts`）。Theme 与 geography 文件不得 import `three`。

---

## 2. Business Domains | 业务域总览

| Preferred Term | 中文 | Path | Frontend Route | Notes |
| --- | --- | --- | --- | --- |
| Geography | 地理 | `src/geography/` | — | 经纬度 + 球面投影 |
| Route | 航线 | `src/route/` | — | OD 对、弧线、标记 |
| Theme | 主题 | `src/theme/theme.service.ts` | `/`、`/globe-light`、`/globe-dark` | 亮色 vs 科技暗色 |
| Globe | 地球仪 | `src/globe/` | 同上 | 页面、宿主、场景组 |

**Frontend route map (canonical)**

| Route | Theme |
| --- | --- |
| `/`、`/globe-light` | bright 主题 + 配色 |
| `/globe-dark` | tech 主题 + 配色 |

---

## 3. Domain Kernel | 领域内核

| Preferred Term | 中文 | Definition | Type | Code Mapping |
| --- | --- | --- | --- | --- |
| SpherePoint | 球面点 | 缩放球面上的笛卡尔点 | 值对象 | `geography/geo-coord.ts` |
| GeoCoord | 地理坐标 | 纬度（−90..90）与经度（−180..180） | 值对象 | `GeoCoord.projectToSpherePoint` |
| FlightRoute | 航线 | 起终点坐标 | 实体 | `route.service.ts` |
| DEFAULT_ROUTES | 默认航线 | 六条国际城市对 | Constant | `RouteService.defaultRoutes` |
| ThemeId | 主题标识 | `bright` 或 `tech` | Union | `theme.service.ts` |
| ThemeAppearance | 主题外观 | 灯光、CTA 描边、资源 URL | 值对象 | `ThemeService` |
| RouteColor | 航线色 | RGB 0..1 航线颜色 | 值对象 | `theme.service.ts` |

---

## 4. Globe / Route rendering | 渲染协作

| Name | Path | Role |
| --- | --- | --- |
| `GlobeService.mount` | `src/globe/globe.service.ts` | Scene / Camera / Renderer / Controls / rAF |
| `GlobeGroup` | `src/globe/globe-group.ts` | `Group` — 灯光、底球、点阵、弧线、标记 |
| `LandDotsGroup` | `src/globe/land-dots-group.ts` | 陆地蒙版 → 点阵 |
| `FlightArc` | `src/route/flight-arc.ts` | 航线管状网格 |
| `RouteMarker` | `src/route/route-marker.ts` | 端点圆盘 |
| `globe.config` | `src/globe/globe.config.ts` | 半径、管径、动画、纹理 URL |
| `GlobeHostComponent` | `src/globe/globe-host.component.ts` | Canvas 挂载 + GSAP 交互 |

---

## 5. Terms to Avoid | 避免使用的名称

| Avoid | Prefer | Reason |
| --- | --- | --- |
| `src/domain/` / `src/infra/` 包 | `src/{geography,route,theme,globe}/` | 按业务域组织 |
| `create-globe.ts` | `GlobeService.mount` | Angular 命名；禁止动词文件名 |
| `GlobeRoot` | `GlobeGroup` | 名词对齐 Object3D 职责 |
| Barrel `index.ts` | 具体文件 import | 依赖显式 |
| 强制 `.mesh` / `.group` 后缀 | kebab 类名 | 仅 service/component/page 用后缀 |

---

## 6. Maintenance | 维护

| Related | Path |
| --- | --- |
| C4 model | [docs/c4-model/](./c4-model/) |
| User Story Map | [docs/User-Story-Map.md](./User-Story-Map.md) |
| App shell | `src/app/` |
| Domains | `src/geography/`、`src/route/`、`src/theme/`、`src/globe/` |
