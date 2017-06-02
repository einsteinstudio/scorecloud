<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
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
    <div class="my-header">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <c:forEach var="blog" items="${blogs}" varStatus="items">
                <c:if test="${(items.index)%2==1}">
                    <div class="swiper-slide" style="background-image:url(${blog.coverImage})"></div>
                </c:if>
                </c:forEach>
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
    <div class="my-menu">
    	<c:forEach var="blog" items="${blogs}" varStatus="items">
    	    <div class="space"></div>
            <div data-href="<%=basePath%>front/blog/view.htm?id=${blog.id}" class="menu-tap">
	           <div class="my-item my-item-${(items.index)%4+1}">
	               <div class="my-item-img" style="background-image:url(${blog.coverImage})"></div>
                        <c:if test="${(items.index)%2==1}"><div class="my-item-title"></c:if>
                        <c:if test="${(items.index)%2==0}"><div class="my-item-title my-item-title-animate"></c:if>${blog.title}</div>
	           	</div>
	        </div>
    	</c:forEach>
        <div class="space"></div>
    </div>
    <script src="/dist/common.js"></script>
    <script src="/dist/team.js"></script>
</body>

</html>