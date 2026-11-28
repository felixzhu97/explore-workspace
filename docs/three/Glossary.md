# Glossary

## Business Domains | 业务域总览

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 地理 | Geography | 地球表面位置、坐标与球面投影关系。 | 经纬度 + 投影 |
| 航线 | Route | 地球仪上连接起讫点的飞行路径。 | 地球仪飞线 |
| 主题 | Theme | 地球仪场景的明暗与配色方案。 | 亮色主题 |
| 地球仪 | Globe | 可交互旋转的三维地球可视化。 | /globe-light 地球仪 |

## Domain Kernel | 领域内核

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 球面点 | SpherePoint | 单位球面上的三维笛卡尔坐标点。 | (x,y,z) 球面点 |
| 地理坐标 | GeoCoord | WGS 84 下的纬度与经度位置。 | 39.9°N, 116.4°E |
| 航线 | FlightRoute | 由起点与终点地理坐标定义的大圆航线。 | 上海 → 伦敦 |
| 默认航线 | DEFAULT_ROUTES | 预置的一组示范城市对航线。 | 六条示范航线 |
| 主题标识 | ThemeId | 亮色或科技暗色等主题方案的标识。 | bright；tech |
| 主题外观 | ThemeAppearance | 灯光、材质与强调色等主题视觉参数。 | 灯光 + CTA 色 |
| 航线色 | RouteColor | 航线管状网格的显示颜色。 | RGB 航线色 |
| 大圆航线 | Great-circle Route | 球面上两点间的最短路径。 | 北京 → 旧金山大圆 |
| 场景图 | Scene Graph | 以层级节点组织三维物体与变换的结构。 | Scene → Group → Mesh |
| WebGL | WebGL | 浏览器中调用 GPU 绘制三维图形的接口。 | WebGLRenderingContext |

## Domain Model | 领域模型补遗

| 中文 | English | 定义 | 举例 |
|------|---------|------|------|
| 地图投影 | Map Projection | 将地球椭球面映射到平面的数学变换。 | 球面 → 平面 |
| Phong 光照 | Phong Shading | 基于法线插值的局部光照着色模型。 | 局部高光模型 |
| glTF | glTF | 面向实时渲染的三维资产传输格式。 | .gltf / .glb |
| WGS 84 | WGS 84 | 全球广泛使用的大地坐标参考系。 | EPSG:4326 |
| 陆地点阵 | Land Dots | 由陆地蒙版采样得到的球面点集合。 | 陆地蒙版采样点 |
| 航线弧 | Flight Arc | 沿大圆航线绘制的三维弧线网格。 | 大圆管状弧 |
| 航线标记 | Route Marker | 航线端点的位置标记。 | 端点圆盘 |
