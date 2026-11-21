# Explore Workspace

One repository for the Explore products. Each product lives in its own
root directory, shared code lives in `shared/`, and one command starts a
product together with the services it depends on.

The migration plan is in the meta repository:
[docs/plans/monorepo-migration.md](https://github.com/felixzhu97/explore/blob/main/docs/plans/monorepo-migration.md).

## Start a product

From the workspace root after `pnpm install`:

```bash
pnpm dev <product>
```

Each product starts only its own apps and the infrastructure it needs.
Start another product in the same command when you need it
(for example `pnpm dev ai iam`).

| Command | What starts |
|---------|-------------|
| `pnpm dev ai` | API 9000, web 4200, Ollama 11434 |
| `pnpm dev iam` | API 9100, console 4201 |
| `pnpm dev chat` | API 9001, Socket.IO 9002, web 4000, admin 4001, Kafka, Ollama |
| `pnpm dev ml` | API 8000, web 4210 |
| `pnpm dev portfolio` | API 8801, analytics 8800, admin 4220, portal 3101 |
| `pnpm dev commerce` | API 3001, web 3000, admin 4240, MongoDB 27017 |

Expo mobile stays out of `pnpm dev`; start it with
`pnpm --dir portfolio/mobile start` or
`pnpm --dir commerce/apps/mobile start`.
| `pnpm dev lowcode` | web 3020 |
| `pnpm dev three` | web 4230 |

## Remote cache

CI uses [Nx Cloud](https://nx.app) as a remote cache. Nx Cloud is a
third-party service hosted in the United States; it stores task outputs,
terminal logs and commit metadata (branch, commit, author) for runs.

- Cache artifacts are end-to-end encrypted with `NX_CLOUD_ENCRYPTION_KEY`.
- Only pushes to `main` write to the cache; pull requests read only, and
  fork pull requests fall back to the local cache.
- The workspace ID in `nx.json` grants no access by itself: anyone who
  is not logged in to the workspace gets no cache access.
- To read the cache locally, run `npx nx login` (members read only) and
  export the encryption key as `NX_CLOUD_ENCRYPTION_KEY`. Never commit
  the key.
- To stop using it, remove `nxCloudId` from `nx.json` and the token
  variables from `ci.yml`.

## License

[Apache License 2.0](LICENSE)
