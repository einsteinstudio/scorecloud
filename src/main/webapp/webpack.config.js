var webpack = require('webpack');

module.exports = {
    //插件项
    plugins: [
        // new webpack.optimize.UglifyJsPlugin({
        //     compress: {
        //         warnings: false
        //     },
        //     mangle: {
        //         except: ['$super', '$', 'exports', 'require', 'module']
        //     }
        // }),
        //提公用js到common.js文件中
        new webpack.optimize.CommonsChunkPlugin('common.js'),
        new webpack.ProvidePlugin({
            $: 'zepto',
            videojs: 'videojs',
            Swiper: 'swiper'
        })
    ],
    //页面入口文件配置
    entry: {
        index: './js/index.js',
        scenery: './js/scenery.js',
        overview: './js/overview.js',
        team: './js/team.js',
        lianxi: './js/lianxi.js',
        detail: './js/detail.js',
        tvlist_img: './js/tvlist_img.js',
        tvview: './js/tvview.js',
        detail_cmt:'./js/detail_cmt.js',
        learncenter: './js/learncenter.js',
        specialedu: './js/specialedu.js',
        schoolnews: './js/schoolnews.js',
        eduresult: './js/eduresult.js',
        classCommunityList: './js/class-community-list.js',
        classCommunityView: './js/class-community-view.js'
    },
    //入口文件输出配置
    output: {
        path: 'dist',
        filename: '[name].js'
    },
    module: {
        //加载器配置
        loaders: [{
            test: /\.woff|\.woff2|\.svg|.eot|\.ttf/,
            loader: 'url-loader?limit=30000&name=../dist/[name].[ext]'
        }, {
            test: /\.less$/,
            loader: 'style-loader!css-loader!less-loader'
        }, {
            test: /\.css$/,
            loader: 'style-loader!css-loader'
        }, {
            test: /\.(jpg|png)$/,
            loader: "url?limit=8192&name=../dist/[name].[ext]"
        }]
    },
    //其它解决方案配置
    resolve: {
        root: './',
        extensions: ['', '.js', '.less', '.css'],
        alias: {
            zepto: 'js/lib/zepto.js',
            swiper: 'bower_components/Swiper/dist/js/swiper.jquery.min.js',
            videojs: 'bower_components/video.js/dist/video.min.js',
            popup: 'bower_components/magnific-popup/dist/jquery.magnific-popup.js'
        }
    }
};
