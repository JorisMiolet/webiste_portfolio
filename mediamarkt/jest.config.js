module.exports = {
  preset: '@vue/cli-plugin-unit-jest',
  transformIgnorePatterns: [
    '/node_modules/(?!axios|xlsx)/'
  ],
  globals: {
    "NODE_ENV": "test"
  },
  moduleFileExtensions: [
    "js",
    "vue"
  ],
  moduleDirectories: [
    "node_modules",
    "src/mediamarkt"
  ]
}
