# Explore ML

`explore-ml` is an optional Python FastAPI app you can run beside sibling
Explore products. It serves recommendation, vision, RAG, and media
generation — including speech transcription — on loopback.

Product clients never call it directly. A Spring (or other) API proxies over
localhost. Six modules live under `server/` as packages of one process on
port `8000`. `web/` is an Angular model-test front end with D3 charts for
every module; `data/` holds small fixtures.

## Get started

### Requirements

You need Python 3.11+ and Git. The optional model-test UI needs Node.js
22.22+ or 24.15+. Redis is optional for recommendation Celery workers. Some
modules also expect Ollama, a GPU stack, or local model weights — see each
guide.

### Initial setup

```bash
git clone https://github.com/felixzhu97/explore-workspace.git
cd explore-workspace
pnpm install
cp ml/server/.env.example ml/server/.env
```

### Run the app

From the workspace root:

```bash
pnpm dev ml
```

This starts the FastAPI app on port `8000` and the Angular model-test UI on
`4210`. `GET /health` reports each module's status. Details:
[`server/README.md`](server/README.md).

Product-only (without Nx):

```bash
cd server
uv venv && source .venv/bin/activate
uv pip install -r requirements.txt --override overrides.txt
uvicorn main:app --port 8000
```

| Module           | Package            | Operations under `/api/v1`                      |
| ---------------- | ------------------ | ----------------------------------------------- |
| Recommendation   | `recommendation`   | `{feeds,explores,reels}:rank`, `feeds:recall`   |
| Vision           | `vision`           | `images:predict`, `{images,videos}:moderate`    |
| RAG              | `rag`              | `documents:query`, `documents:streamQuery`, …   |
| Image Playground | `image_playground` | `images:generate`, `imageJobs/{id}`             |
| Speech           | `speech`           | `voices:synthesize`, `audios:transcribe`, WS    |
| Video            | `video`            | `videos:generate`, `videoJobs/{id}`             |

Wire your product’s upstream to `http://127.0.0.1:8000`. For a fuller

### Start the model test UI (optional)

`pnpm dev ml` already starts `web/` at <http://localhost:4210>. Product-only:

```bash
cd web
pnpm install
pnpm start
```

The UI proxies `/ml/*` to the app on port `8000` without CORS setup. Each
module has one page where you can call all of its endpoints and see the
results charted with D3. See [`web/README.md`](web/README.md).

### Configuration

Settings are constants in `server/config.py` and each
`<module>/config.py`; edit them in place. `server/.env` holds only
secrets (`DATABASE_URL`, `REDIS_PASSWORD`, `OPENAI_API_KEY`); never commit
it. Large model weights stay out of git under `~/Codes/models`
(`LOCAL_MODELS_ROOT`); download them with the
Playground, Speech, RAG rerank and the recommendation feed ranker read
those paths; Video loads a Hugging Face id (`COGVIDEOX_MODEL`). Put small
fixtures under `data/`.

### Checks

```bash
cd server && pytest -q tests <module>/tests
```

For the UI, run `npm test` and `npm run build` in `web/`.

GitHub Actions runs on every pull request and every push to `main`: `pytest`
for the app and RAG (Python 3.12) and the UI tests and build (Node 22). Run
the other modules' suites locally.

## Next steps

  loopback integration.
- Download checkpoints with the
- Read the [Guideline](../docs/ml/Guideline.md) for ML practice boundaries.
- Learn the shared
- Browse the [Glossary](../docs/ml/Glossary.md),
  [Reference](../docs/ml/Reference.md), and
  [C4 model](../docs/ml/c4-model/).
- Review the [User Story Map](../docs/ml/User-Story-Map.md).

## Repository layout

```text
server/     One FastAPI app (recommendation / vision / rag / image_playground / speech / video)
:web/            Model-test UI (Angular, NG-ZORRO, Tailwind, D3)
data/          Test / fixture data
docs/          Glossary, Guideline, user-guide, C4, product-owner
```

## Contributing

See [CONTRIBUTING.md](../CONTRIBUTING.md). Prefer a single English kebab-case
branch slug, small PRs with a clear why and References, and keep Glossary
plus C4 in sync when module boundaries change. Participation is governed by
the [Code of Conduct](../CODE_OF_CONDUCT.md).

## Security

Report vulnerabilities as described in [SECURITY.md](../SECURITY.md).

## License

[Apache-2.0](../LICENSE) © 2026 Felix Zhu
