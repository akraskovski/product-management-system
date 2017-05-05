const webpack = require('webpack');
const helpers = require('./helpers');
const CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: {
        'polyfills': './src/polyfills.ts',
        'vendor': './src/vendor.ts',
        'app': './src/main.ts'
    },

    output: {
        path: helpers.root('dist'),
        filename: '[name].js'
    },

    resolve: {
        extensions: ['.ts', '.js', '.css', '.html']
    },

    module: {
        rules: [
            {
                test: /\.ts$/,
                loaders: ['awesome-typescript-loader', 'angular2-template-loader']
            },
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: 'css-loader'
                }),
                include: [helpers.root('src', 'styles')]
            },
            {
                test: /\.(html|css)$/,
                loader: 'raw-loader'
            },
            {
                test: /\.(png|jpg|gif|ico)$/,
                loaders: ['file-loader?name=images/[name].[ext]']
            }
        ]
    },

    plugins: [
        new CommonsChunkPlugin({
            names: ['vendor', 'polyfills'],
        }),

        new HtmlWebpackPlugin({
            template: './src/index.html'
        }),
        new CopyWebpackPlugin([{
            from: './src/images',
            to: './images'
        }]),
        new ExtractTextPlugin('[name].css'),
    ],

    devtool: "source-map",

    devServer: {
        quiet: true,
        proxy: {
            '/**': 'http://localhost:8081'
        }
    }
};