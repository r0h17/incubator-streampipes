/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

const UglifyJSPlugin = require('uglifyjs-webpack-plugin');
const { HashedModuleIdsPlugin } = require('webpack');
const TerserPlugin = require('terser-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin')

const merge = require('webpack-merge');
const baseConfig = require('./webpack.config.base.js');

module.exports = merge(baseConfig, {
    mode: 'production',
    plugins: [
        new CompressionPlugin
    ],
    optimization: {
        // noEmitOnErrors: true,
        // runtimeChunk: 'single',
        // splitChunks: {
        //     cacheGroups: {
        //         default: {
        //             chunks: 'async',
        //             minChunks: 2,
        //             priority: 10
        //         },
        //         common: {
        //             name: 'common',
        //             chunks: 'async',
        //             minChunks: 2,
        //             enforce: true,
        //             priority: 5
        //         },
        //         vendors: false,
        //         vendor: false
        //     }
        // },
        minimizer: [
            // new HashedModuleIdsPlugin(),
            new TerserPlugin({
                terserOptions: {
                    ecma: undefined,
                    warnings: false,
                    parse: {},
                    compress: {},
                    mangle: true, // Note `mangle.properties` is `false` by default.
                    module: false,
                    output: null,
                    toplevel: false,
                    nameCache: null,
                    ie8: false,
                    keep_classnames: false,
                    keep_fnames: false,
                    safari10: false,
                },
            }),
        ]
    }
});