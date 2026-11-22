# FinPulse Portal

React (Vite) portal application with Robinhood-style UI. Part of the FinPulse monorepo.

## Stack

- **React 19** + **TypeScript**
- **Vite 6** – dev server and build
- **Emotion** (@emotion/react, @emotion/styled) – Robinhood-style styling
- **@explore/portfolio-ui** – shared UI components (Radix-based)
- **@explore/portfolio-utils** – shared utilities

## Scripts

| Command   | Description                |
|----------|----------------------------|
| `pnpm dev` | Start dev server (port 3101) |
| `pnpm build` | Production build           |
| `pnpm preview` | Preview production build  |

## Run from workspace root

```bash
pnpm dev portfolio
# or
pnpm --filter portfolio-portal dev
```

## Project layout

- `src/main.tsx` – entry
- `src/App.tsx` – root component
- `src/index.css` – Theme variables (Robinhood-style dark theme)
- `vite.config.ts` – Vite config, `@/` alias
- `index.html` – HTML entry
