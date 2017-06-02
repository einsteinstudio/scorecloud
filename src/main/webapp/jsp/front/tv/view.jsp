<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="x-rim-auto-match" content="none">
    <title>${blog.type}</title>
    <style type="text/css">
        .spinner {
            width: 200px;
            height: 200px;
            position: relative;
            top: 500px;
            margin: 100px auto;
        }

        .double-bounce1,
        .double-bounce2 {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #3ABCFF;
            opacity: 0.6;
            position: absolute;
            top: 0;
            left: 0;
            -webkit-animation: bounce 2.0s infinite ease-in-out;
            animation: bounce 2.0s infinite ease-in-out;
        }

        .double-bounce2 {
            -webkit-animation-delay: -1.0s;
            animation-delay: -1.0s;
        }

        @-webkit-keyframes bounce {
            0%,
            100% {
                -webkit-transform: scale(0.0)
            }
            50% {
                -webkit-transform: scale(1.0)
            }
        }

        @keyframes bounce {
            0%,
            100% {
                transform: scale(0.0);
                -webkit-transform: scale(0.0);
            }
            50% {
                transform: scale(1.0);
                -webkit-transform: scale(1.0);
            }
        }
    </style>
</head>

<body>
    <div class="loading" style="display:block; width:100%; height:100%; position:fixed; left:0px; top:0px; z-index:9999; background-color: #fff">
        <div class="spinner">
            <div class="double-bounce1"></div>
            <div class="double-bounce2"></div>
        </div>
    </div>
    <div class="rich_media_area">
      <div class="video_area">
        <div class="video_player">
            <video id="really-cool-video" class="video-js vjs-default-skin" controls preload="auto" autoplay="autoplay" webkit-playsinline>
                <source src="${blog.content}" type="video/mp4">
                <source src="${blog.content}" type="application/x-mpegURL"">
                <p class="vjs-no-js">您的设备暂时无法播放此视频！</p>
            </video>
        </div>
        <div class="video_content">
          <p class="videoTitle">${blog.title}</p>
          <div class="tips">
              <div class="tips_video_type"><i class="icon_logo"></i> 每周视讯 </div>
              <div class="tips_video_tool"><i class="icon_praise_gray"></i> <span class="likeNum">${blog.voteUp}</span></div>
          </div>  
        </div>
      </div>

      <div class="relevant_video">
          <p class="rele_title">推荐视频</p>
          <ul class="newslist">
          </ul>
          <div class="loadmore-div">
              <p class="loadmore-p">点击加载更多</p>
              <img class="loadmore-img" src="/images/loading.gif"></img>
          </div>
          <div class="noResult">暂无数据</div>
          <label class="pageNo">2</label>
      </div>

      <div class="hot_comment">
          <div class="rele_title">
              <div class="cmt-title">热点评论</div>
              <div class="write-cmt">
                <i class="icon_cmt_gray" ></i>
                <i class="cancel-cmt">取消</i>
              </div>
          </div>
          <div class="sendCmt">
              <div class="input-comment">
                  <textarea class="input-txt" row="6" placeholder="我来说两句..."></textarea>
              </div>
              
              <div class="input-btn">
                  <button class="button orange">提交</button>    
              </div>
          </div>
          <div class="history_comment">
          </div>
          <div class="loadmore-div">
              <p class="loadmore-p">点击加载更多</p>
              <img class="loadmore-img" src="/images/loading.gif"></img>
          </div>
          <div class="noResult">暂无数据</div>
          <label class="pageNo">2</label>
      </div>
    </div>
  </div>
  <footer class="copyright">@技术支持：深圳科瑞奇科技</footer>
  <label class="blogType">${blog.type}</label>
  <label class="objectId">${blog.id}</label>
  <script src="/dist/common.js"></script>
  <script src="/dist/tvview.js"></script>
</body>
</html>