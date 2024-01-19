module.exports = {
    verbose: true,
    preset: "@vue/cli-plugin-unit-jest",
    transform: {
        "^.+\\.js$": "babel-jest"
    },
    transformIgnorePatterns:["node_modules/(?!axios)/"],
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
