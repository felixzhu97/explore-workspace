# Explore Workspace

One repository for the Explore products. Each product lives in its own
root directory, shared code lives in `shared/`, and one command starts a
product together with the services it depends on.

The migration plan is in the meta repository:
[docs/plans/monorepo-migration.md](https://github.com/felixzhu97/explore/blob/main/docs/plans/monorepo-migration.md).

## Remote cache

CI uses [Nx Cloud](https://nx.app) as a remote cache. Nx Cloud is a
third-party service hosted in the United States; it stores task outputs,
terminal logs and commit metadata (branch, commit, author) for runs.

- Cache artifacts are end-to-end encrypted with `NX_CLOUD_ENCRYPTION_KEY`.
- Only pushes to `main` write to the cache; pull requests read only, and
  fork pull requests fall back to the local cache.
- To read the cache locally, export a read-only access token as
  `NX_CLOUD_ACCESS_TOKEN` and the encryption key as
  `NX_CLOUD_ENCRYPTION_KEY`. Never commit either value.
- To stop using it, remove those two variables from `ci.yml`.

## License

[Apache License 2.0](LICENSE)
