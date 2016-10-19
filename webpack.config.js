module.exports = {
     entry: {
       'src/main/resources/resources/static/react/dist':'./src/main/resources/resources/static/react/App.jsx',
       'target/classes/resources/static/react/dist':'./src/main/resources/resources/static/react/App.jsx'
     },
     output: {
         path: './',
         filename: '[name]/app.js'
     },
     module: {
         loaders: [{
             test: /\.js$/,
             exclude: /node_modules/,
             loader: 'babel-loader'
         },
         {
           test: /.jsx?$/,
           loader: 'babel-loader',
           exclude: /node_modules/,
           query: {
             presets: ['es2015', 'react']
           }
         }
       ]
     }
 };
