/**
 * Creates `<product>/<role>` projects named `<product>-<role>`, tagged
 * `scope:<product>`, and registers the product's module boundary.
 *
 * @see https://nx.dev/docs/extending-nx/local-generators
 */
module.exports = async function productGenerator(tree, { name, roles = ['server', 'web'] }) {
  if (tree.exists(name)) {
    throw new Error(`${name}/ already exists.`);
  }
  const tags = [`scope:${name}`];

  tree.write(`${name}/README.md`, `# ${name}\n\nRoles: ${roles.map((role) => `\`${role}\``).join(', ')}.\n`);

  for (const role of roles) {
    const root = `${name}/${role}`;
    const project = `${name}-${role}`;
    if (role === 'server') {
      tree.write(`${root}/build.gradle.kts`, 'plugins {\n    id("explore.java-conventions")\n}\n');
      tree.write(`${root}/project.json`, json({ name: project, tags }));
      includeGradleProject(tree, project, root);
    } else if (role === 'ios') {
      tree.write(`${root}/README.md`, `# ${project}\n`);
    } else {
      tree.write(
        `${root}/package.json`,
        json({ name: project, version: '0.0.0', private: true, type: 'module', nx: { tags } }),
      );
      tree.write(`${root}/src/index.ts`, 'export {};\n');
    }
  }

  addProductBoundary(tree, name);
};

function json(value) {
  return `${JSON.stringify(value, null, 2)}\n`;
}

// A flat Gradle path such as `:iam-server` matches the Nx project name and
// avoids an implicit `:iam` parent project.
function includeGradleProject(tree, project, root) {
  const settings = tree.read('settings.gradle.kts', 'utf-8');
  tree.write(
    'settings.gradle.kts',
    `${settings.trimEnd()}\n\ninclude(":${project}")\nproject(":${project}").projectDir = file("${root}")\n`,
  );
}

function addProductBoundary(tree, name) {
  const config = tree.read('eslint.config.js', 'utf-8');
  const pattern = /const products = \[(.*?)\];/s;
  const match = config.match(pattern);
  if (!match) {
    throw new Error('eslint.config.js has no `const products = [...]` list.');
  }
  const products = [...match[1].matchAll(/'([^']+)'/g)].map(([, product]) => product);
  const updated = [...new Set([...products, name])].sort();
  tree.write(
    'eslint.config.js',
    config.replace(pattern, `const products = [${updated.map((product) => `'${product}'`).join(', ')}];`),
  );
}
