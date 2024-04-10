const HtmlWebpackPlugin = require("html-webpack-plugin");
const {ModuleFederationPlugin} = require("webpack").container;
const ExternalTemplateRemotesPlugin = require("external-remotes-plugin");
const path = require("path");
const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = {
    entry: "./src/index",
    mode: "development",
    devServer: {
        static: path.join(__dirname, "dist"),
        port: 3000,
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
        new ModuleFederationPlugin({
            name: "shell",
            remotes: {
                app1: "app1@http://localhost:3001/remoteEntry.js",
                app2: "app2@http://localhost:3002/remoteEntry.js",
                app3: "app3@http://localhost:3003/remoteEntry.js",
            },
            shared: {
                react: { singleton: true },
                "react-dom": { singleton: true },
            },
        }),
        new ExternalTemplateRemotesPlugin(),
        new HtmlWebpackPlugin({
            template: "./public/index.html",
        }),
    ],
};

