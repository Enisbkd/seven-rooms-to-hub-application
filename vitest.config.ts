import { fileURLToPath } from 'node:url';
import { mergeConfig, defineConfig } from 'vitest/config';
import viteConfig from './vite.config';

export default mergeConfig(
  viteConfig,
  defineConfig({
    resolve: {
      alias: {
        vue: 'vue',
      },
    },
    test: {
      globals: true,
      environment: 'happy-dom', // happy-dom provides a better performance but doesn't have a default url.
      setupFiles: [fileURLToPath(new URL('./src/main/webapp/app/test-setup.ts', import.meta.url))],
      reporters: ['default', 'vitest-sonar-reporter'],
      outputFile: {
        'vitest-sonar-reporter': fileURLToPath(new URL('./build/test-results/TESTS-results-vitest.xml', import.meta.url)),
      },
      coverage: {
        statements: 85,
        provider: 'v8',
        branches: 75,
        lines: 85,
        reportsDirectory: fileURLToPath(new URL('./target/vite-coverage', import.meta.url)),
      },
    },
  }),
);
