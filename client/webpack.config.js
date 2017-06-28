const webpack = require('webpack');
const CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
    entry: {
        'polyfills': './src/polyfills.ts',
        'vendor': './src/vendor.ts',
        'app': './src/main.ts'
    },

    output: {
        path: __dirname + '/dist',
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
                test: /\.(html|css)$/,
                // exclude: './src/assets/styles',
                loader: 'raw-loader'
            },
            {
                test: /\.(png|jpg|gif|ico)$/,
                loaders: ['file-loader?name=assets/img/[name].[ext]']
            }
        ]
    },

    plugins: [
        new CommonsChunkPlugin({
            names: ['vendor', 'polyfills']
        }),

        new HtmlWebpackPlugin({
            template: 'src/index.html'
        }),
        new CopyWebpackPlugin([
            {
                from: 'src/assets/img',
                to: 'images'
            },
            {
                from: 'src/assets/styles',
                to: 'styles'
            },
        ])
    ],

    devtool: "source-map",

    devServer: {
        quiet: true,
        proxy: {
            '/**': 'http://localhost:8080'
        }
    }
};