/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/**/*.{js,ts,vue,tsx}",
    './components/**/*.{html,js,vue}',
    "./node_modules/tw-elements/dist/js/**/*.js",
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#FE4A49',
        'secondary': '#FFFFFF',
        'tertiary': '#ADC9C4',

      }
    },
  },
  plugins: [require("tw-elements/dist/plugin.cjs")],
}




