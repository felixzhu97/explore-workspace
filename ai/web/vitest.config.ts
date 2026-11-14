import { defineConfig } from 'vitest/config';
import angular from '@analogjs/vite-plugin-angular';

export default defineConfig({
  plugins: [angular()],
  test: {
    include: ['src/**/*.spec.ts'],
    environment: 'jsdom',
    globals: true,
    setupFiles: ['src/test-setup.ts'],
    exclude: ['src/app/app.config.spec.ts'],
  },
});
