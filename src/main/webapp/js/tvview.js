require('../less/tvview.less');
require('../js/lib/widget/navigator.iscroll.js');
var Fingerprint = require('../bower_components/fingerprint/fingerprint.js');
var clickElement;

$(function () {
    $('.loading').hide();

    $('.relevant_video .loadmore-div').on('click', function(){
        loadMoreTVData();
    });

    $('.history_comment .loadmore-div').on('click', function(){
        loadMoreCommentsData();
    });

    init();//获取推荐视频列表
    getVoteStatus();//获取点赞状态
    getCommentList();//获取评论列表

    $('.write-cmt').on('click', function(){
        if ($('.sendCmt').css("display") == 'none') {
            $('.sendCmt').css("display", "block");
            $('.icon_cmt_gray').css("display", "none");
            $('.cancel-cmt').css("display", "block");
        } else {
            $('.sendCmt').css("display", "none");
            $('.cancel-cmt').css("display", "none");
            $('.icon_cmt_gray').css("display", "block");
        }
        
    });

    $('.icon_praise_gray').on('click', function(){
        var fingerprint = new Fingerprint().get();
        var objectIdValue = $('.objectId').text();
        clickElement = $(this);
        $.ajax({
            type: "GET",
            url: "http://gmxx.goldskyer.com/front/blog/voteUp.json",
            data: {
                accountId: fingerprint,
                objectId: objectIdValue,
                objectType: "BLOG",
                fp: fingerprint
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "success") {
                    clickElement.attr('class',"icon_praise_red");
                    var currentlikenum = parseInt($('.likeNum').html());
                    $('.likeNum').html(currentlikenum + 1);
                    clickElement = null;
                };
            },
            error: function () {
                clickElement.attr('class',"icon_praise_gray");
                clickElement = null;
            }
        });
    });

    $(".button").on('click', function() {
        var info = $(".input-txt").val();
        if ($.trim(info) != '') {
          var name = new Fingerprint().get();
          var objectid = $('.objectId').text();
        $.ajax({
          type: "GET",
          url: "http://gmxx.goldskyer.com/comment/add.json",
                  data: {
                      accountId: name,
                      objectId: objectid,
                      content: info,
                      objectType:"BLOG"
                  },
          dataType: "json",
          success: function(data){
            var hc_list = "<div class='hc_list'>";
              hc_list += "<div class='hc_left'>";
              hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
              hc_list += "</div>";
              hc_list += "<div class='hc_right'>";
              hc_list += "<div class='hc_name'>" + data.data.comment.accountId + "</div>";
              hc_list += "<div class='hc_info'>" + data.data.comment.content + "</div>";
              hc_list += "<div class='hc_time'>" + data.data.comment.createDate + "</div>";
              hc_list += "</div></div>";
              $(".history_comment").prepend(hc_list);
          },
          error: function () {
            return;
          }
            });
            $(".input-txt").val('');    
        }             
    });

});

function init(){
    $('.newslist').css("display", "none");
    $('.relevant_video .noResult').css("display", "none");
    $(".relevant_video .loadmore-div").css("display", "none");
    var blogType = $(".blogType").text();
    var objectIdValue = $('.objectId').text();
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
              $('.newslist').css("display", "none");
              $('.relevant_video .noResult').css("display", "block");
              $(".relevant_video .loadmore-div").css("display", "none");
              return;
          } else {
              $('.newslist').css("display", "block");
              $('.relevant_video .noResult').css("display", "none");
          }

          for (var i = 0; i < blogs.length; i++) {
              var blog = blogs[i];
              if (objectIdValue == blog.id) {continue;}
              var basePath = "http://gmxx.goldskyer.com/";
              var blogUrl = basePath + "front/tv/view.htm?id=" + blog.id;
              var imgSrc;
              if ("coverImage" in blog){
                  imgSrc = blog.coverImage;                  
              }else{
                  imgSrc = "http://gmxx.szgm.edu.cn/UploadFile/1511/182034504628960/182033210161026.jpg";
              }

              var blog_row = '<a href="' + blogUrl + '"><li class="newscontent">';
              blog_row += '<div class="media_content">';
              blog_row += '<div class="bg_img">';
              blog_row += '<img class="newsimage" src="' + imgSrc + '" z-index=1>';
              blog_row += '<img class="play_img" src="/images/play.png" z-index=2>';
              blog_row += '</div>';
              blog_row += '<p class="newstitle">' + blog.title + '</p>';
              blog_row += '<p class="newsdesc">' + splitTime(blog.updateDate) + '</p>';
              blog_row += '</li></a>';
              $(".newslist").append(blog_row);
          }

          //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
          if (totalcnt - pageNo * 10 > 0) {
              $(".relevant_video .loadmore-div").css("display", "block");//展示
              $(".relevant_video .loadmore-p").css("display", "block");
              $(".relevant_video .loadmore-img").css("display", "none");                
          }else{//否则不现实加载刷新
              $(".relevant_video .loadmore-div").css("display", "none");
          }
          $(".relevant_video .pageNo").text(pageNo + 1);
      },
      error: function () {
          $(".relevant_video .loadmore-p").css("display", "block");
          $(".relevant_video .loadmore-img").css("display", "none"); 
      }
    });
};

function loadMoreTVData() {
  $('.relevant_video .loadmore-p').css("display", "none");
    $('.relevant_video .loadmore-img').css("display", "block");

    var blogType = $(".blogType").text();
    var pageNo = $(".relevant_video .pageNo").text();

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
              $('.newslist').css("display", "none");
              $('.relevant_video .noResult').css("display", "block");
              return;
          } else {
              $('.newslist').css("display", "block");
              $('.relevant_video .noResult').css("display", "none");
          }
          //否则加载并显示数据
          for (var i = 0; i < blogs.length; i++) {
              var blog = blogs[i];
              var basePath = "http://gmxx.goldskyer.com/";
              var blogUrl = basePath + "front/tv/view.htm?id=" + blog.id;
              var imgSrc;
              if ("coverImage" in blog){
                  imgSrc = blog.coverImage;                  
              }else{
                  imgSrc = "http://gmxx.szgm.edu.cn/UploadFile/1511/182034504628960/182033210161026.jpg";
              }

              var blog_row = '<a href="' + blogUrl + '"><li class="newscontent">';
              blog_row += '<div class="media_content">';
              blog_row += '<div class="bg_img">';
              blog_row += '<img class="newsimage" src="' + imgSrc + '" z-index=1>';
              blog_row += '<img class="play_img" src="/images/play.png" z-index=2>';
              blog_row += '</div>';
              blog_row += '<p class="newstitle">' + blog.title + '</p>';
              blog_row += '<p class="newsdesc">' + splitTime(blog.updateDate) + '</p>';
              blog_row += '</li></a>';
              $(".newslist").append(blog_row);
          }

          //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
          if (totalcnt - pageNo * 10 > 0) {
              $(".relevant_video .loadmore-div").css("display", "block");//展示
              $(".relevant_video .loadmore-p").css("display", "block");
              $(".relevant_video .loadmore-img").css("display", "none");                
          }else{//否则不现实加载刷新
              $(".relevant_video .loadmore-div").css("display", "none");
          }
          $(".relevant_video .pageNo").text(pageNo + 1);
      },
      error: function () {
          $(".relevant_video .loadmore-p").css("display", "block");
          $(".relevant_video .loadmore-img").css("display", "none"); 
      }
    });
};

function getVoteStatus(){//获取点赞状态
    var fingerprint = new Fingerprint().get();
    var objectIdValue = $('.objectId').text();
    $.ajax({
        type: "GET",
        url: "http://gmxx.goldskyer.com/voteUp/queryStatus.json",
        data: {
            accountId: fingerprint,
            objectId: objectIdValue,
            objectType: "BLOG",
            fp: fingerprint
        },
        dataType: "json",
        success: function (data) {
            if (data.result == "failure") {
                $(".icon_praise_gray").attr('class', "icon_praise_red");
            }
        },
        error: function () {
            $(".icon_praise_gray").attr('class', "icon_praise_gray");
        }
    });
};

function splitTime(originTime){//从接口中，获取日期
  var timeArray = originTime.split(" ");
  return timeArray[0];
};

function getCommentList(){//获取评论列表
    var name = new Fingerprint().get();
    var objectid = $('.objectId').text();
    var pageNo = 1;
    $.ajax({
        type: "GET",
        url: "http://gmxx.goldskyer.com/comment/list.json",
        data: {
            objectId: objectid,
            objectType:"BLOG",
            pageNo:pageNo
        },
        dataType: "json",
        success: function(data){
            var comments = data.data.comments;
            for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                var hc_list = "<div class='hc_list'>";
                hc_list += "<div class='hc_left'>";
                hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
                hc_list += "</div>";
                hc_list += "<div class='hc_right'>";
                hc_list += "<div class='hc_name'>" + comment.accountId + "</div>";
                hc_list += "<div class='hc_info'>" + comment.content + "</div>";
                hc_list += "<div class='hc_time'>" + comment.createDate + "</div>";
                hc_list += "</div></div>";
                $(".history_comment").prepend(hc_list);
            }
            var totalcnt = data.data.count;
            // if (totalcnt == 0) {//是否需要展示暂无数据，如果不需要的话，暂时不加入
            //     $('.hot_comment .noResult').css("display", "block");
            //     $(".hot_comment .loadmore-div").css("display", "none");
            // };
            //当totalcnt - 当前currentcnt >= 10,则加载刷新展现
            if (totalcnt - pageNo * 10 > 0) {
                $(".hot_comment .loadmore-div").css("display", "block");//展示
                $(".hot_comment .loadmore-p").css("display", "block");
                $(".hot_comment .loadmore-img").css("display", "none");                
            }else{//否则不现实加载刷新
                $(".hot_comment .loadmore-div").css("display", "none");
            }
            $(".hot_comment .pageNo").text(pageNo + 1);
        },
        error: function () {
            $(".hot_comment .loadmore-div").css("display", "none");
        }
    });
};

function loadMoreCommentsData() {
    $('hot_comment .loadmore-p').css("display", "none");
    $('.hot_comment .loadmore-img').css("display", "block");

    var pageNo = $(".history_comment .pageNo").text();
    var objectid = $('.objectId').text();
    $.ajax({
        type: "GET",
        url: "http://gmxx.goldskyer.com/comment/list.json",
        data: {
            objectId: objectid,
            objectType:"BLOG",
            pageNo:pageNo
        },
        dataType: "json",
        success: function(data){
            var comments = data.data.comments;
            var totalcnt = data.data.count;
            for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                var hc_list = "<div class='hc_list'>";
                hc_list += "<div class='hc_left'>";
                hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
                hc_list += "</div>";
                hc_list += "<div class='hc_right'>";
                hc_list += "<div class='hc_name'>" + comment.accountId + "</div>";
                hc_list += "<div class='hc_info'>" + comment.content + "</div>";
                hc_list += "<div class='hc_time'>" + comment.createDate + "</div>";
                hc_list += "</div></div>";
                $(".history_comment").prepend(hc_list);
            }
            //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
            if (totalcnt - pageNo * 10 > 0) {
                $(".hot_comment .loadmore-div").css("display", "block");//展示
                $(".hot_comment .loadmore-p").css("display", "block");
                $(".hot_comment .loadmore-img").css("display", "none");                
            }else{//否则不现实加载刷新
                $(".hot_comment .loadmore-div").css("display", "none");
            }
            $(".hot_comment .pageNo").text(pageNo + 1);
        },
        error: function () {
            $(".hot_comment .loadmore-p").css("display", "block");
            $(".hot_comment .loadmore-img").css("display", "none"); 
        }
    });
};
