#!/usr/bin/env node
// Usage: pnpm dev <product|project>...
// A product name selects every project tagged scope:<product>; any other
// name is taken as a project name, e.g. `pnpm dev ai iam` or `pnpm dev ollama`.
import { execFileSync, spawn } from 'node:child_process';
import { createRequire } from 'node:module';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';

const names = process.argv.slice(2);
if (names.length === 0) {
  console.error('Usage: pnpm dev <product|project>...');
  process.exit(1);
}

// readyWhen probes localhost; a machine-wide HTTP(S)/SOCKS proxy must not
// intercept loopback or Nx never sees the process as ready.
const loopback = 'localhost,127.0.0.1,::1';
process.env.NO_PROXY = [process.env.NO_PROXY, loopback].filter(Boolean).join(',');
process.env.no_proxy = [process.env.no_proxy, loopback].filter(Boolean).join(',');

// Call the workspace nx binary directly — `pnpm exec` re-runs install checks
// on every invocation and stalls local serve.
const root = join(dirname(fileURLToPath(import.meta.url)), '..');
const nxBin = createRequire(join(root, 'package.json')).resolve('nx/bin/nx.js');
const nx = (args, options) =>
  execFileSync(process.execPath, [nxBin, ...args], { encoding: 'utf8', cwd: root, ...options });

const projects = names.flatMap((name) => {
  const scoped = JSON.parse(nx(['show', 'projects', '--projects', `tag:scope:${name}`, '--json']));
  return scoped.length > 0 ? scoped : [name];
});

const child = spawn(process.execPath, [nxBin, 'run-many', '--targets', 'serve', '--projects', projects.join(',')], {
  stdio: 'inherit',
  env: process.env,
  cwd: root,
});
child.on('exit', (code) => process.exit(code ?? 0));
