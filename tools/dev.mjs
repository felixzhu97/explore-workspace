#!/usr/bin/env node
// Usage: pnpm dev <product|project>...
// A product name selects every project tagged scope:<product>; any other
// name is taken as a project name, e.g. `pnpm dev ai iam` or `pnpm dev ollama`.
import { execFileSync, spawn } from 'node:child_process';

const names = process.argv.slice(2);
if (names.length === 0) {
  console.error('Usage: pnpm dev <product|project>...');
  process.exit(1);
}

const nx = (args, options) => execFileSync('pnpm', ['exec', 'nx', ...args], { encoding: 'utf8', ...options });

const projects = names.flatMap((name) => {
  const scoped = JSON.parse(nx(['show', 'projects', '--projects', `tag:scope:${name}`, '--json']));
  return scoped.length > 0 ? scoped : [name];
});

const child = spawn('pnpm', ['exec', 'nx', 'run-many', '--targets', 'serve', '--projects', projects.join(',')], {
  stdio: 'inherit',
});
child.on('exit', (code) => process.exit(code ?? 0));
