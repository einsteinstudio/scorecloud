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
    <title>学校概况</title>
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
    <div class="my-header"></div>
    <div class="my-menu">
    	 <c:forEach var="b" items="${blogs}" varStatus="i">
    	   <div class="space"></div>
	       <div class="my-item my-item-${i.index+1}">
	            <div class="my-item-img" style="background-image:url(${b.coverImage})"></div>
	            <div class="my-item-title">
                    <c:choose> 
                        <c:when test="${b.title=='办学理念'}">
                            <a href="http://gmxx.goldskyer.com/h5/school-concept.html" target="_self">${b.title}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/front/blog/view.htm?id=${b.id}" target="_self">${b.title}</a>
                        </c:otherwise>
                    </c:choose>
                </div>
	       </div>
    	 </c:forEach>	
        <div class="space"></div>
    </div>
    <script src="../dist/common.js"></script>
    <script src="../dist/overview.js"></script>
</body>
</html>
