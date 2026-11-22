# CI/CD 文档

GitHub Actions 工作流相关的架构图。

## 文件

| 文件 | 工作流 | 说明 |
| ---- | ------ | ---- |
| `cicd-workflow.puml` | 总览 | 门禁 CI 的触发与高层流程 |

## 工作流（源文件）

工作流在 explore-workspace 根目录的 `.github/workflows/` 下。

| 工作流 | 说明 |
| ------ | ---- |
| `ci.yml` | PR 和推送到 main 的门禁：对受影响的项目执行 `nx affected -t lint test build`（ai-server 跑 spotless、checkstyle、test，ai-web 跑 lint、test、build） |
| `ai-ios.yml` | `ai/ios` 有改动时在模拟器上构建并测试 iOS 应用 |
| `ai-image.yml` | `ai/server` 或根 Gradle 构建有改动时构建部署镜像 |

## 查看

- [PlantUML Online Editor](https://www.plantuml.com/plantuml/uml/)
- VS Code PlantUML 插件
- `plantuml -o png *.puml`
