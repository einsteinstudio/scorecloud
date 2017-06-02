<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta http-equiv="x-rim-auto-match" content="none">
        <title>${blog.title}</title>
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
        <div class="page_content">
            <div class="rich_media_area">
                <h2 class='rich_media_title'>${blog.title}</h2>
                <div class="rich_media_meta_list">
                     <span class="post_date"><fmt:formatDate value="${blog.updateDate}" pattern="yyyy-MM-dd"/></span>
                    <span class="post_type">${blog.type}</span>
                    <span class="post_author">${blog.author}</span>
                </div>
                <p>
                    <img id="seperator" class="seperator" src="/images/view_top.png" />
                </p>
        
                <div class="rich_media_content"><!-- 以下为正文，来源于数据库 -->
                    ${blog.content}
                </div>
 
                <div class="tips">
                    <div class="tips_tool_read">阅读 <span class="readNum">${blog.viewCount}</span></div>
                    <div class="tips_tool_like"><i class="icon_praise_gray"></i> <span class="likeNum">${blog.voteUp}</span></div>
                </div> 
            </div>
        </div>

        <label class="objectId">${blog.id}</label>
        <label class="blogType">${blog.type}</label>
        <script src="/dist/common.js"></script>
        <script src="/dist/detail.js"></script>
    </body>
</html>