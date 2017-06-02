require('../less/eduresult.less');
require('../js/lib/widget/navigator.iscroll.js');

$(function() {
    $('#fix-both').navigator({     //左右都有fix元素
        isScrollToNext: true,      //出现半个tab时，不跳动到下一个tab
        isShowShadow: false       //不显示左右阴影
    });

    init();

    $('.loadmore-div').on('click', function(){
        $('.loadmore-p').css("display", "none");
        $('.loadmore-img').css("display", "block");

        var blogType = $(".cur").html();
        var pageNo = $(".pageNo").text();

        $.ajax({
          type: "GET",
          url: "http://gmxx.goldskyer.com/front/blog/list.json",
          data: {type:blogType, pageNo:pageNo},
          dataType: "json",
          success: function(data){
              //如果pageNo=1 and count(blogs)=0,则显示暂无数据
              var resultData = data.data;
              var pageNo = resultData.pageNo;
              var blogs = resultData.blogs;
              var totalcnt = resultData.count;
              if (pageNo == 1 && blogs.length == 0) {
                  $('.contents').css("display", "none");
                  $('.noResult').css("display", "block");
                  $(".loadmore-div").css("display", "none");
                  return;
              } else {
                  $('.contents').css("display", "block");
                  $('.noResult').css("display", "none");
              }
              //否则加载并显示数据
              for (var i = 0; i < blogs.length; i++) {
                  var blog = blogs[i];
                  var basePath = "http://gmxx.goldskyer.com/";
                  var blogUrl = basePath + "front/blog/view.htm?id=" + blog.id;
                  var imgSrc;
                  if ("coverImage" in blog){
                      imgSrc = blog.coverImage;                  
                  }else{
                      imgSrc = "http://gmxx.szgm.edu.cn/UploadFile/1511/182034504628960/182033210161026.jpg";
                  }

                  var blog_row = '<a href="' + blogUrl + '">';
                  blog_row += '<div class="rich_media_list">';                 
                  blog_row += '<div class="media_content">'; 
                  blog_row += '<p class="media_title">' + blog.title + '</p>'; 
                  blog_row += '<img class="media_image" src="' + imgSrc +'">';
                  blog_row += '<div class="tips">';
                  blog_row += '<div class="tips_video_type"><i class="icon_logo"></i> 深圳光明小学 <span class="media_date">' + splitTime(blog.updateDate) + '</span></div>';
                  blog_row += '<div class="tips_video_tool">赞 <span class="likeNum">' + blog.voteUp + '</span></div>';
                  blog_row += '</div></div></div></a>';
                  $(".contents").append(blog_row);
              }

              //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
              if (totalcnt - pageNo * 10 > 0) {
                  $(".loadmore-div").css("display", "block");//展示
                  $(".loadmore-p").css("display", "block");
                  $(".loadmore-img").css("display", "none");                
              }else{//否则不现实加载刷新
                  $(".loadmore-div").css("display", "none");
              }
              $(".pageNo").text(pageNo + 1);
          },
          error: function () {
              $(".loadmore-p").css("display", "block");
              $(".loadmore-img").css("display", "none"); 
          }
        });
    });  
});

function init(){
  $('.newsdiv').css("display", "none");
  $('.noResult').css("display", "none");
  $(".loadmore-div").css("display", "none");
  var blogType = $(".cur").html();
  var pageNo = 1;

  $.ajax({
    type: "GET",
    url: "http://gmxx.goldskyer.com/front/blog/list.json",
    data: {type:blogType, pageNo:pageNo},
    dataType: "json",
    success: function(data){
        //如果pageNo=1 and count(blogs)=0,则显示暂无数据
        var resultData = data.data;
        var pageNo = resultData.pageNo;
        var blogs = resultData.blogs;
        var totalcnt = resultData.count;
        if (pageNo == 1 && blogs.length == 0) {
            $('.newsdiv').css("display", "none");
            $('.noResult').css("display", "block");
            $(".loadmore-div").css("display", "none");
            return;
        } else {
            $('.newsdiv').css("display", "block");
            $('.noResult').css("display", "none");
        }

        //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
        if (totalcnt - pageNo * 10 > 0) {
            $(".loadmore-div").css("display", "block");//展示
            $(".loadmore-p").css("display", "block");
            $(".loadmore-img").css("display", "none");                
        }else{//否则不现实加载刷新
            $(".loadmore-div").css("display", "none");
        }
        $(".pageNo").text(pageNo + 1);
    },
    error: function () {
        $(".loadmore-p").css("display", "block");
        $(".loadmore-img").css("display", "none"); 
    }
  });
};

function splitTime(originTime){
  var timeArray = originTime.split(" ");
  return timeArray[0];
};

function clickTap(){
  $('.newsdiv').css("display", "none");
  $('.noResult').css("display", "none");
  $(".loadmore-div").css("display", "none");
  var blogType = $(".cur").html();
  var pageNo = 1;

  $.ajax({
    type: "GET",
    url: "http://gmxx.goldskyer.com/front/blog/list.json",
    data: {type:blogType, pageNo:pageNo},
    dataType: "json",
    success: function(data){
        //如果pageNo=1 and count(blogs)=0,则显示暂无数据
        var resultData = data.data;
        var pageNo = resultData.pageNo;
        var blogs = resultData.blogs;
        var totalcnt = resultData.count;
        if (pageNo == 1 && blogs.length == 0) {
            $('.newsdiv').css("display", "none");
            $('.noResult').css("display", "block");
            $(".loadmore-div").css("display", "none");
            return;
        } else {
            $('.newsdiv').css("display", "block");
            $('.noResult').css("display", "none");
        }
        $(".contents").html = "";
        //否则加载并显示数据
        for (var i = 0; i < blogs.length; i++) {
            var blog = blogs[i];
            var basePath = "http://gmxx.goldskyer.com/";
            var blogUrl = basePath + "front/blog/view.htm?id=" + blog.id;
            var imgSrc;
            if ("coverImage" in blog){
                imgSrc = blog.coverImage;                  
            }else{
                imgSrc = "http://gmxx.szgm.edu.cn/UploadFile/1511/182034504628960/182033210161026.jpg";
            }

            var blog_row = '<a href="' + blogUrl + '">';
            blog_row += '<div class="rich_media_list">';                 
            blog_row += '<div class="media_content">'; 
            blog_row += '<p class="media_title">' + blog.title + '</p>'; 
            blog_row += '<img class="media_image" src="' + imgSrc +'">';
            blog_row += '<div class="tips">';
            blog_row += '<div class="tips_video_type"><i class="icon_logo"></i> 深圳光明小学 <span class="media_date">' + splitTime(blog.updateDate) + '</span></div>';
            blog_row += '<div class="tips_video_tool">赞 <span class="likeNum">' + blog.voteUp + '</span></div>';
            blog_row += '</div></div></div></a>';
            $(".contents").append(blog_row);
        }

        //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
        if (totalcnt - pageNo * 10 > 0) {
            $(".loadmore-div").css("display", "block");//展示
            $(".loadmore-p").css("display", "block");
            $(".loadmore-img").css("display", "none");                
        }else{//否则不现实加载刷新
            $(".loadmore-div").css("display", "none");
        }
        $(".pageNo").text(pageNo + 1);
    },
    error: function () {
        $(".loadmore-p").css("display", "block");
        $(".loadmore-img").css("display", "none"); 
    }
  });
};