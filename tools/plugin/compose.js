const { readFileSync } = require('node:fs');
const { dirname, join } = require('node:path');
const { parse } = require('yaml');

/**
 * Infers an Nx project from each Compose file that defines a service, so
 * infrastructure such as `ollama` can be a `dependsOn` target of product
 * servers. The project root is the directory of the Compose file, so each
 * file defines at most one service.
 *
 * @see https://nx.dev/docs/extending-nx/project-graph-plugins
 */
exports.createNodes = [
  '**/compose.yaml',
  async (files, _options, context) =>
    files.map((file) => {
      const compose = parse(readFileSync(join(context.workspaceRoot, file), 'utf8')) ?? {};
      const services = Object.entries(compose.services ?? {});
      if (services.length === 0) return [file, {}];
      if (services.length > 1) {
        throw new Error(`${file} defines ${services.length} services; keep one service per Compose file.`);
      }
      const [[service, definition]] = services;
      const port = hostPort(definition);
      return [
        file,
        {
          projects: {
            [dirname(file)]: {
              name: service,
              tags: ['type:infrastructure'],
              targets: {
                serve: {
                  continuous: true,
                  command: `node tools/plugin/compose-serve.js ${service} ${port ?? ''}`.trim(),
                  options: { cwd: '{workspaceRoot}' },
                },
              },
            },
          },
        },
      ];
    }),
];

function hostPort(definition) {
  const [first] = definition?.ports ?? [];
  if (first === undefined) return undefined;
  if (typeof first === 'object') return first.published;
  const parts = String(first).split(':');
  return parts.length > 1 ? parts.at(-2) : undefined;
}
