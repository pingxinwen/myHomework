const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: "production",
    entry: './src/calc.js',
    output:{
        path:path.resolve(__dirname,'dist'),
        filename:"bundle.js",
    },
    devServer: {
        host: "127.0.0.1",
        port: "8081",
    },
    plugins:[
        new HtmlWebpackPlugin ({
            template:'src/calc.html',
            filename:"index.html"
        })
    ],
    module:{
        rules:[
            {
                test:/\.css$/,
                use:[
                    'style-loader','css-loader'
                ]
            }
        ]
    }
}