module.exports = {
    preset: '@vue/cli-plugin-unit-jest/presets/typescript',
    testMatch: ['**/*.spec.(js|jsx|ts|tsx)|**/__tests__/*.(js|jsx|ts|tsx)'],
    transform: {
        '^.+\\.vue$': 'vue-jest',
    },
};
