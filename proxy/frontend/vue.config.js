const { VUE_APP_API_URL = "http://localhost:3000" } = process.env;

module.exports = {
  pluginOptions: { apollo: { lintGQL: true } },
  devServer: {
    proxy: {
      "^/api": {
        target: VUE_APP_API_URL,
      },
    },
  },

  pwa: {
    name: "GLOFOX | Booking Manager",
    themeColor: "#3072C2",
  },

  transpileDependencies: ["vuetify"],
};
