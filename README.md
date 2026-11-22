# Explore Workspace

Explore Workspace is the single repository for the Explore products.
Each product lives in its own top-level directory, shared libraries live
in `shared/`, and one command starts a product together with the
services it needs.

You clone once, install once, and run only what you are working on.

## Get started

### Requirements

- macOS or Linux
- [Node.js](https://nodejs.org/) 24+ (see [`.nvmrc`](.nvmrc))
- [pnpm](https://pnpm.io/) 11+ (see `packageManager` in
  [`package.json`](package.json))
- Git

Some products also need a JDK or other local services. Follow the
product README linked below when you go beyond the first run.

### Initial install

```bash
git clone https://github.com/felixzhu97/explore-workspace.git
cd explore-workspace
pnpm install
```

### Run your first product

```bash
pnpm dev ai
```

This starts Explore AI (API on port 9000, web on port 4200) and the
local services it needs. For API keys, Ollama embeddings, and
troubleshooting, see [`ai/README.md`](ai/README.md).

Start another product in the same command when you need it:

```bash
pnpm dev ai iam
```

## Products

| Command | What starts |
|---------|-------------|
| `pnpm dev ai` | API 9000, web 4200, Ollama 11434 |
| `pnpm dev iam` | API 9100, console 4201 |
| `pnpm dev chat` | API 9001, Socket.IO 9002, web 4000, admin 4001, Kafka, Ollama |
| `pnpm dev ml` | API 8000, web 4210 |
| `pnpm dev portfolio` | API 8801, analytics 8800, admin 4220, portal 3101 |
| `pnpm dev commerce` | API 3001, web 3000, admin 4240, MongoDB 27017 |
| `pnpm dev lowcode` | web 3020 |
| `pnpm dev three` | web 4230 |

Expo mobile apps stay out of `pnpm dev`. Start them with:

```bash
pnpm --dir portfolio/mobile start
pnpm --dir commerce/apps/mobile start
```

## Next steps

- Read a product README for deep setup:
  - [ai](ai/README.md) · [docs](docs/ai/)
  - [iam](iam/README.md) · [docs](docs/iam/)
  - [chat](chat/README.md) · [docs](docs/chat/)
  - [ml](ml/README.md) · [docs](docs/ml/)
  - [portfolio](portfolio/README.md) · [docs](docs/portfolio/)
  - [commerce](commerce/README.md) · [docs](docs/commerce/)
  - [lowcode](lowcode/README.md) · [docs](docs/lowcode/)
  - [three](three/README.md) · [docs](docs/three/)
- Open an issue or discussion when you want to propose a change;
  see [Contributing](#contributing)
- For historical monorepo context, see the meta-repo
  [migration plan](https://github.com/felixzhu97/explore/blob/main/docs/plans/monorepo-migration.md)

## Contributing

Contributions are welcome. Please read
[CONTRIBUTING.md](CONTRIBUTING.md) before opening a pull request.
Participation is governed by the
[Code of Conduct](CODE_OF_CONDUCT.md).

## Security

Report vulnerabilities privately as described in
[SECURITY.md](SECURITY.md). Do not open a public issue for unfixed
security problems.

## Project status

Explore Workspace is under active development. Product APIs, module
flags, and local ports may change before stable 1.0 lines. Prefer
each product's living docs under [`docs/<product>/`](docs/) —
Glossary, C4, TOGAF Architecture Definition Document, and User Story
Map — as the source of truth for naming and boundaries while the
surface evolves.

## CI and remote cache

CI uses [Nx Cloud](https://nx.app) as a remote cache. Nx Cloud is a
third-party service hosted in the United States; it stores task
outputs, terminal logs, and commit metadata (branch, commit, author)
for runs.

- Cache artifacts are end-to-end encrypted with
  `NX_CLOUD_ENCRYPTION_KEY`.
- Only pushes to `main` write to the cache; pull requests read only,
  and fork pull requests fall back to the local cache.
- The workspace ID in `nx.json` grants no access by itself: anyone
  who is not logged in to the workspace gets no cache access.
- To read the cache locally, run `npx nx login` (members read only)
  and export the encryption key as `NX_CLOUD_ENCRYPTION_KEY`. Never
  commit the key.
- To stop using it, remove `nxCloudId` from `nx.json` and the token
  variables from `ci.yml`.

## License

[Apache License 2.0](LICENSE)
