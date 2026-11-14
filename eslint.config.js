import nx from '@nx/eslint-plugin';

/** Products allowed in the workspace; `nx g @explore/plugin:product` adds new ones. */
const products = ['ai', 'iam'];

export default [
  ...nx.configs['flat/base'],
  ...nx.configs['flat/typescript'],
  ...nx.configs['flat/javascript'],
  {
    ignores: ['**/dist', '**/build', '**/node_modules', '**/.angular', '**/.nx'],
  },
  {
    files: ['**/*.ts', '**/*.tsx', '**/*.js', '**/*.jsx', '**/*.mjs', '**/*.cjs'],
    rules: {
      '@nx/enforce-module-boundaries': [
        'error',
        {
          enforceBuildableLibDependency: true,
          allow: [],
          depConstraints: [
            ...products.map((product) => ({
              sourceTag: `scope:${product}`,
              onlyDependOnLibsWithTags: [`scope:${product}`, 'type:shared'],
            })),
            { sourceTag: 'type:shared', onlyDependOnLibsWithTags: ['type:shared'] },
            { sourceTag: 'type:tool', onlyDependOnLibsWithTags: ['type:tool'] },
            { sourceTag: 'type:infrastructure', onlyDependOnLibsWithTags: [] },
          ],
        },
      ],
    },
  },
  {
    files: ['tools/plugin/**/*.js'],
    languageOptions: { sourceType: 'commonjs' },
    rules: { '@typescript-eslint/no-require-imports': 'off' },
  },
];
