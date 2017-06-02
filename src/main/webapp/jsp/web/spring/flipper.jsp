<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<td height="195" valign="center" width="281" align="middle"><script src="/manager/assets/js/sohuflash_1.js" language="javascript" type="text/javascript"></script>
<div id="flashcontent01"></div>
<script language="javascript" type="text/javascript">	
var titles = '';
var imgs = "";
var urls = "";
var photoArrys= new Array("http://${storageDomain}/storage/gmxx/upload/images/20160604/1465022388757068245.jpg","http://${storageDomain}/storage/gmxx/upload/images/20160604/1465022613282053029.jpg","http://${storageDomain}/storage/gmxx/upload/images/20160604/1465022501383080951.jpg","http://${storageDomain}/storage/gmxx/upload/images/20160604/1465022756014096739.jpg","http://${storageDomain}/storage/gmxx/upload/images/20160604/1465022651211073278.jpg","http://${storageDomain}/storage/gmxx/upload/images/20160604/1465022461564069994.jpg");
function addtitle(title){
	if(titles!=""){
		titles+="|";
	}
	var reg = new RegExp('"',"g");  
	title=title.replace(reg,"")
	titles+=title;
}

function addphotourl(photourl,i){
	if(imgs!=""){
		imgs+="|";
	}
	if(photourl==""){
		photourl=photoArrys[i%5];
	}
	imgs+=photourl;
}

function addlink(link){
	if(urls!=""){
		urls+="|";
	}
	urls+=link;
}

<c:forEach var="blog" items="${bannerImages}" varStatus="status" >
<c:if test="${not empty blog.coverImage}">
addtitle('${blog.title}');
addphotourl("${blog.coverImage}","${status.index}");
addlink("/web/spring/blog/view.htm?id=${blog.id}");
</c:if>
</c:forEach>




					var speed = 4000;					
					var sohuFlash2 = new sohuFlash("/manager/assets/focus0414a.swf","flashcontent01","275","185","8","#ffffff");
sohuFlash2.addParam("quality", "medium");
sohuFlash2.addParam("wmode", "opaque");	
sohuFlash2.addVariable("speed",speed);
						sohuFlash2.addVariable("p",imgs);	
						sohuFlash2.addVariable("l",urls);
						sohuFlash2.addVariable("icon",titles);
						sohuFlash2.write("flashcontent01");
					</script></td>