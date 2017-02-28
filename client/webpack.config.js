// Helper: root() is defined at the bottom
const path = require('path');
const webpack = require('webpack');

// Webpack Plugins
const CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
const autoprefixer = require('autoprefixer');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = function makeWebpackConfig() {

    let config = {};
    config.devtool = 'source-map';
    config.entry = {
        'polyfills': './src/polyfills.ts',
        'vendor': './src/vendor.ts',
        'app': './src/main.ts' // our angular app
    };

    config.output = {
        path: root('dist'),
        filename: 'js/[name].js'
    };

    config.resolve = {
        extensions: ['.ts', '.js', '.json', '.css', '.html'],
    };

    config.module = {
        rules: [
            // Support for .ts files.
            {
                test: /\.ts$/,
                loaders: ['awesome-typescript-loader?', 'angular2-template-loader', '@angularclass/hmr-loader'],
            },

            // copy those assets to output
            {
                test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: 'file-loader?name=fonts/[name].[hash].[ext]?'
            },

            // Support for *.json files.
            {test: /\.json$/, loader: 'json-loader'},

            {
                test: /\.css$/,
                exclude: root('src', 'app'),
                loader: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: ['css-loader', 'postcss-loader']
                })
            },

            {
                test: /\.css$/,
                include: root('src', 'app'),
                loader: 'raw-loader!postcss-loader'
            },

            // support for .html as raw text
            {
                test: /\.html$/,
                loader: 'raw-loader',
                exclude: root('src', 'public')
            }
        ]
    };

    config.plugins = [

        new webpack.ContextReplacementPlugin(
            /angular(\\|\/)core(\\|\/)(esm(\\|\/)src|src)(\\|\/)linker/,
            root('./src') // location of your src
        ),

        new webpack.LoaderOptionsPlugin({
            options: {
                tslint: {
                    emitErrors: false,
                    failOnHint: false
                },

                postcss: [
                    autoprefixer({
                        browsers: ['last 2 version']
                    })
                ]
            }
        })
    ];

    config.plugins.push(
        new CommonsChunkPlugin({
            name: ['vendor', 'polyfills']
        }),

        // Inject script and link tags into html files
        new HtmlWebpackPlugin({
            template: './src/public/index.html',
            chunksSortMode: 'dependency'
        }),

        new ExtractTextPlugin({filename: 'css/[name].css', disable: false})
    );

    // Add build specific plugins
    config.plugins.push(
        new webpack.NoEmitOnErrorsPlugin(),

        new webpack.optimize.UglifyJsPlugin({sourceMap: true, mangle: {keep_fnames: true}}),

        new CopyWebpackPlugin([{
            from: root('src/public')
        }])
    );

    config.devServer = {
        contentBase: './src/public',
        historyApiFallback: true,
        quiet: true,
        stats: 'minimal' // none (or false), errors-only, minimal, normal (or true) and verbose
    };

    return config;
}();

// Helper functions
function root(args) {
    args = Array.prototype.slice.call(arguments, 0);
    return path.join.apply(path, [__dirname].concat(args));
}