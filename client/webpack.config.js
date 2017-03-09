const path = require('path');
const webpack = require('webpack');
const CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
const HtmlWebpackPlugin = require('html-webpack-plugin');

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
        extensions: ['.ts', '.js', '.css', '.html'],
    },

    module: {
        rules: [
            {
                test: /\.ts$/,
                loaders: ['awesome-typescript-loader', 'angular2-template-loader'],
            },

            {
                test: /\.(html|css)$/,
                loader: 'raw-loader'
            }
        ]
    },

    plugins: [
        //delete imports from app which defined in this files
        new CommonsChunkPlugin({
            names: ['vendor', 'polyfills']
        }),

        // Inject script and link tags into html files
        new HtmlWebpackPlugin({
            template: './src/index.html'
        })
    ],

    devServer: {
        contentBase: './src',
        historyApiFallback: true,
        quiet: true,
        stats: 'minimal', // none (or false), errors-only, minimal, normal (or true) and verbose
        proxy: {
            '/user/*': 'http://localhost:8081/SpringRestHibernateJpa',
            '/service/*': 'http://localhost:8081/SpringRestHibernateJpa',
            '/auth/*': 'http://localhost:8081/SpringRestHibernateJpa'
        }
    }
};