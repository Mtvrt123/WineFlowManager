const HtmlWebpackPlugin = require('html-webpack-plugin');
const { ModuleFederationPlugin } = require('webpack').container;
const path = require('path');

module.exports = {
    entry: "./src/index",
    mode: "development",
    devServer: {
        static: path.join(__dirname, "dist"),
        port: 3001,
        host: "0.0.0.0",
        proxy: {
            "/api": {
                target: "http://web-gateway:8080",
                changeOrigin: true,
                secure: false,
            },
        },
    },
    output: {
        publicPath: "auto",
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loader: "babel-loader",
                exclude: /node_modules/,
                options: {
                    presets: ["@babel/preset-react"],
                },
            },
        ],
    },
    plugins: [
        // To learn more about the usage of this plugin, please visit https://webpack.js.org/plugins/module-federation-plugin/
        new ModuleFederationPlugin({
            name: "app1",
            filename: "remoteEntry.js",
            exposes: {
                "./App": "./src/App",
                "./Navbar": "./src/Components/Navbar",
                "./Users": "./src/Components/Users",
            },
            shared: {
                react: { singleton: true },
                "react-dom": { singleton: true },
            },
        }),
        new HtmlWebpackPlugin({
            template: "./public/index.html",
        }),
    ],
};