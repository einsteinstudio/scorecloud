<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>${blogType}</title>
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
    <div id="fix-both">
        <ul>
            <c:forEach var="item" varStatus="status" items="${menu.parent.children}">
			 	<c:if test="${!item.hide}"><li  ><a <c:if test="${item.name==blogType}">class="cur"</c:if> href="<%=basePath%>front/tv/list.htm?blogType=${item.name}">${item.name}</a></li></c:if>
			 </c:forEach>
        </ul>
        <a class="ui-navigator-fixright" href="http://gmxx.goldskyer.com/front/index.htm">
          <img src="//${storageDomain}/storage/gmxx/images/home_red.png" />
        </a> <!-- 如果只需要左侧固定，请删除这个a标签 -->
    </div>

    <div class="rich_media_area">

		<c:forEach var="blog" items="${blogs}" varStatus="items">
		<c:choose>
		  <c:when test="${blog.subType=='LINK'}">
		      <div class="menu-tap popup-with-form" href="#test-form" data-href="${blog.link}" data-id="${blog.id}" data-valid="${blog.needAuth}">
		  </c:when>
		  <c:otherwise>
		    	<div class="menu-tap popup-with-form" href="#test-form" data-href="<%=basePath%>front/tv/view.htm?id=${blog.id}" data-id="${blog.id}" data-valid="${blog.needAuth}">
		  </c:otherwise>
		</c:choose>
		      <div class="rich_media_list">
		        <div class="media_content">
		          <p class="media_title">${blog.title}</p>

		          <div class="bg_img">
		            <img class="media_image" src="${blog.coverImage}" z-index=1 >
		            <img class="play_img" src="/images/play.png" z-index=2>
		          </div>
		          <div class="tips">
		              <div class="tips_video_type"><i class="icon_logo"></i> ${blog.type} <span class="media_date"><fmt:formatDate value="${blog.updateDate}" pattern="yyyy-MM-dd"/></span></div>
		              <div class="tips_video_tool">赞 <span id="likeNum">${blog.voteUp}</span></div>
		          </div>
		        </div>
		      </div>
		      </div>
        </c:forEach>

    </div>
    <div id="test-form" class="white-popup-block mfp-hide">
        <fieldset style="border:0;">
            <ol>
                <li>
                    <label for="name">验证码</label>
                    <input class="name" id="name" name="name" type="text" placeholder="验证码" required="">
                </li>
            </ol>
            <p class="error"></p>
            <button class="submit-btn">提交</button>
        </fieldset>
    </div>

	<%@ include file="/jsp/front/widgets/list_load.jsp"%>

    <footer class="copyright">@技术支持：深圳科瑞奇科技</footer>

    <script src="/dist/common.js"></script>
    <script src="/dist/tvlist_img.js"></script>

</body>
</html>