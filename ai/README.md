# explore-ai

`explore-ai` is a full-stack platform for conversational AI. You can chat with multiple LLM providers, upload documents for RAG retrieval, call tools (weather, web search, datetime), and run Golden Eval regression checks against chat and RAG quality.

The backend uses Java 25, Spring Boot 4.1, and Spring AI 2.0; the frontend uses Angular 22. A native SwiftUI hello-world app (**AI**) lives under `ios`. Architecture follows `web → application → domain ← infrastructure`. See the [C4 model](../docs/ai/developer/c4-model/) and [Glossary](../docs/ai/Glossary.md) for boundaries and ubiquitous language.

Optional modules—agents, MCP, vision, audio, image generation, metrics, and more—are tracked in the [User Story Map](../docs/ai/product-owner/User-Story-Map.md).

**Live:** [https://www.felixzhu.chat](https://www.felixzhu.chat)

## Get started

### Requirements

You need JDK 25+, Node.js 20+, pnpm 8+, and Git. For local RAG embeddings, install [Ollama](https://ollama.com/) and run `ollama pull qwen3-embedding:0.6b` (re-ingest documents after changing embedding models).

### Initial install

```bash
git clone https://github.com/felixzhu97/explore-workspace.git
cd explore-workspace
cp ai/server/.env.example ai/server/.env   # at minimum set DEEPSEEK_API_KEY
pnpm install
pnpm dev ai   # server http://localhost:9000, web http://localhost:4200
```

`pnpm dev ai` also starts Ollama, or reuses one already listening on port 11434. The server reads `ai/server/.env` at startup. Run `pnpm dev ai iam` to sign in with Explore IAM as well.

For a full walkthrough (API keys, Ollama, troubleshooting), see [Quick Start](../docs/ai/developer/QUICKSTART.md).

### Run your first chat

With the backend running:

```bash
curl -X POST http://localhost:9000/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello"}'
```

Open [http://localhost:4200](http://localhost:4200) for the full UI. See [Quick Start](../docs/ai/developer/QUICKSTART.md) for session flows, RAG upload, and tool calling.

### Stop local services

Press `Ctrl+C` in the terminal running `pnpm dev ai`. To reset local H2 data, remove the directory configured by `H2_URL` (default under `server/data/explore-ai`).

## Configuration

Environment variables override defaults in `server/src/main/resources/application.yml`. Do not commit real secrets.

- **`DEEPSEEK_API_KEY`** — required for the default chat and eval path
- **`OPENAI_API_KEY`** — optional OpenAI-compatible features
- **`SERPER_API_KEY`** — optional web search tool
- **`H2_URL`** — optional database URL override
- **`GOLDEN_EVAL_IT`** — set `true` to enable live Golden Eval integration tests ([guide](../docs/ai/developer/golden-eval.md))
- **`APP_OAUTH_EXPLORE_IAM_ISSUER_URI`** — Explore IAM issuer (default `http://localhost:9100`) for OAuth Login
- **`SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUERURI`** — issuer whose `Authorization: Bearer` tokens are accepted (blank by default, which turns validation off; set `http://localhost:9100` locally). It is separate from the login issuer above, so set both when IAM moves. Set the issuer, not only a JWK set URI, or tokens skip the issuer check. Also enables `/scim/v2/Users`, where Explore IAM provisions, deactivates, and deletes accounts with scope `write:ai_scim` (deleting erases the account's data). Without it, `/scim/v2` denies every request. See [E12 SCIM provisioning](../docs/ai/product-owner/user-stories/E12-scim-provisioning.md).

See [`.env.example`](.env.example) for the full list.

## Next steps

- Take a guided setup in [Quick Start](../docs/ai/developer/QUICKSTART.md)
- Read the [API reference](../docs/ai/developer/api.md)
- Run [Golden Eval](../docs/ai/developer/golden-eval.md) (LLM-as-a-Judge regression)
- Browse the [C4 model](../docs/ai/developer/c4-model/) and [Glossary](../docs/ai/Glossary.md)
- [Architecture Definition (TOGAF)](../docs/ai/developer/togaf/Architecture-Definition-Document.md)
- Browse the AI [Guideline](../docs/ai/Guideline.md)
- Open the native iOS skeleton: [`ios`](ios) (scheme **AI**)
- Explore capabilities on the [User Story Map](../docs/ai/product-owner/User-Story-Map.md)
- Deploy: backend on [Render](https://render.com/docs/compute-plans) via [`render.yaml`](../render.yaml); frontend on [Vercel](https://vercel.com) via [`vercel.json`](web/vercel.json) (proxies `/api/*` to Render)

Run unit tests with `./gradlew :ai-server:test` from the workspace root. Run end-to-end tests with `pnpm e2e` in `web/`; it starts the backend on an in-memory H2 database and the web app, or reuses them if they are already running on ports 9000 and 4200.

## Contributing

See [CONTRIBUTING.md](../CONTRIBUTING.md). Open an issue or pull request on [GitHub](https://github.com/felixzhu97/explore-workspace). Keep domain and API names aligned with the [Glossary](../docs/ai/Glossary.md) when changing behavior or docs. Participation is governed by the [Code of Conduct](../CODE_OF_CONDUCT.md).

## Security

Report vulnerabilities as described in [SECURITY.md](../SECURITY.md).

## Project status

`explore-ai` is under active development. Modules, APIs, and configuration may change before a 1.0 release.

## License

[Apache-2.0](../LICENSE) © 2026 Felix Zhu
