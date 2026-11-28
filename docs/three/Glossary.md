# Glossary | Explore Three

> 统一语言。列仅为 **名称 / Name**、**定义 / Definition**。  
> 名称：中文在前、英文在后。定义：中文在上、英文换行；定义含举例。

## Business Domains | 业务域总览

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 地理 / Geography | 地球表面位置、坐标与球面投影关系（如：WGS84 经纬度）。<br>The relationship between the position on the Earth's surface, coordinates, and spherical projection. (e.g. WGS84 经纬度). |
| 航线 / Route | 地球仪上连接起讫点的飞行路径（如：上海→伦敦飞线）。<br>Flight path connecting the departure and arrival points on the globe. (e.g. 上海→伦敦飞线). |
| 主题 / Theme | 地球仪场景的明暗与配色方案（如：亮色 / 暗色外观）。<br>Light and dark and color schemes for the globe scene. (e.g. 亮色 / 暗色外观). |
| 地球仪 / Globe | 可交互旋转的三维地球可视化（如：可旋转 Three.js 地球仪）。<br>Interactive, rotatable 3D visualization of the Earth. (e.g. 可旋转 Three.js 地球仪). |

## Domain Kernel | 领域内核

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 球面点 / SpherePoint | 单位球面上的三维笛卡尔坐标点（如：单位球面 (x,y,z)）。<br>Three-dimensional Cartesian coordinate points on the unit sphere. (e.g. 单位球面 (x,y,z)). |
| 地理坐标 / GeoCoord | WGS 84 下的纬度与经度位置（如：39.9°N, 116.4°E）。<br>Latitude and longitude position under WGS 84. (e.g. 39.9°N, 116.4°E). |
| 航线 / FlightRoute | 由起点与终点地理坐标定义的大圆航线（如：PVG → LHR）。<br>A great circle route defined by the geographical coordinates of the origin and destination. (e.g. PVG → LHR). |
| 默认航线 / DEFAULT_ROUTES | 预置的一组示范城市对航线（如：六条示范大圆航线）。<br>A preset set of sample city-to-city routes. (e.g. 六条示范大圆航线). |
| 主题标识 / ThemeId | 亮色或科技暗色等主题方案的标识（如：bright；tech）。<br>Icons for theme options such as Light or Dark Tech. (e.g. bright；tech). |
| 主题外观 / ThemeAppearance | 灯光、材质与强调色等主题视觉参数。<br>Visual parameters of the theme, such as lighting, materials, and accent colors. (e.g. 灯光、材质与强调色等主题视觉参数). |
| 航线色 / RouteColor | 航线管状网格的显示颜色。<br>The display color of the route tubular mesh. (e.g. 航线管状网格的显示颜色). |
| 大圆航线 / Great-circle Route | 球面上两点间的最短路径（如：北京→旧金山大圆）。<br>The shortest path between two points on a sphere. (e.g. 北京→旧金山大圆). |
| 场景图 / Scene Graph | 以层级节点组织三维物体与变换的结构。<br>Organize the structure of 3D objects and transformations using hierarchical nodes. (e.g. 以层级节点组织三维物体与变换的结构). |
| WebGL / WebGL | 浏览器中调用 GPU 绘制三维图形的接口。<br>Interface for calling the GPU to render 3D graphics in the browser. (e.g. 浏览器中调用 GPU 绘制三维图形的接口). |

## Domain Model | 领域模型补遗

| 名称 / Name | 定义 / Definition |
| ----------- | ----------------- |
| 地图投影 / Map Projection | 将地球椭球面映射到平面的数学变换。<br>A mathematical transformation that maps the ellipsoid surface of the Earth onto a plane. (e.g. 将地球椭球面映射到平面的数学变换). |
| Phong 光照 / Phong Shading | 基于法线插值的局部光照着色模型。<br>Local illumination shading model based on normal interpolation. (e.g. 基于法线插值的局部光照着色模型). |
| glTF / glTF | 面向实时渲染的三维资产传输格式。<br>A 3D asset transmission format for real-time rendering. (e.g. 面向实时渲染的三维资产传输格式). |
| WGS 84 / WGS 84 | 全球广泛使用的大地坐标参考系。<br>A geodetic coordinate reference system widely used around the world. (e.g. 全球广泛使用的大地坐标参考系). |
| 陆地点阵 / Land Dots | 由陆地蒙版采样得到的球面点集合。<br>A set of spherical points sampled from the land mask. (e.g. 由陆地蒙版采样得到的球面点集合). |
| 航线弧 / Flight Arc | 沿大圆航线绘制的三维弧线网格。<br>A 3D arc grid drawn along great circle routes. (e.g. 沿大圆航线绘制的三维弧线网格). |
| 航线标记 / Route Marker | 航线端点的位置标记。<br>Location marker for the route endpoint. (e.g. 航线端点的位置标记). |
