const path = require('path');
const webpack = require('webpack');

module.exports = {
    publicPath: process.env.NODE_ENV == 'production' ? '/' :  '/',
    css: {
        sourceMap: true
    },
    productionSourceMap: false,
    transpileDependencies: [
        /\/node_modules\/vuetify\//,
		/\/node_modules\/vue-echarts\//,
      /\/node_modules\/resize-detector\//
    ],
    configureWebpack: {
          resolve: {
            alias: {
                Api: path.resolve(__dirname, 'src/api/'),
                Components: path.resolve(__dirname, 'src/components/'),
                Constants: path.resolve(__dirname, 'src/constants/'),
                Container: path.resolve(__dirname, 'src/container/'),
                Views: path.resolve(__dirname, 'src/views/'),
                Helpers: path.resolve(__dirname, 'src/helpers/'),
                Themes: path.resolve(__dirname, 'src/themes/')
            },
            extensions: ['*', '.js', '.vue', '.json']
        },
        plugins: [
              //jquery plugin
            new webpack.ProvidePlugin({
                $: 'jquery',
                jquery: 'jquery',
                'window.jQuery': 'jquery',
                jQuery: 'jquery'
            })
        ]
    }
}
