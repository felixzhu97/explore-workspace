# Shared kernel and errors

`portfolio-server` depends on `:shared-kernel` and `:shared-errors` from the explore-workspace root.

## Done

- `com.finpulse.server.common.domain.model.{AbstractEmbeddable,AbstractImmutable,AbstractEntity}` are thin subclasses of `com.explore.kernel.*` so existing imports keep working.

## Follow-up

- Point domain ID/value types at `com.explore.kernel.AbstractEmbeddable` directly and delete the portfolio aliases.
- Replace `ApiExceptionHandler` with `shared-errors` RFC 7807 handlers where response shape can change.
- Add blockchain tables to Liquibase or align `ddl-auto` for production.
- Refresh portfolio docs that still mention Bazel (`README.md`, `docs/developer/**`, CI diagrams).