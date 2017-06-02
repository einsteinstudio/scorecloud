<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table width="700px">
	<tr>
		<td height="40" align="center" valign="middle">
			<div class="show_page">${lang.total[n18]} <b>${blogListModel.count}</b> ${lang.articles[n18]}
					<a  <c:if test="${pager.curPage==1}">class="disabled"</c:if> <c:if test="${pager.curPage!=1}">href="/web/spring/blog/list.htm?blogType=${blogType}&pageNum=1"</c:if> >${lang.first[n18]}</a>
					<a <c:if test="${pager.startPage==pager.curPage}">class="disabled"</c:if>   <c:if test="${pager.startPage!=pager.curPage}">href="/web/spring/blog/list.htm?blogType=${blogType}&pageNum=${pager.curPage-1}"</c:if> >${lang.pre[n18]}</a>
					<c:forEach  varStatus="status" begin="${pager.startPage}" end="${pager.endPage}" step="1">
						<a href="/web/spring/blog/list.htm?blogType=${blogType}&pageNum=${status.index}"><span <c:if test="${status.index==pager.curPage}">class="page-btn-cur"</c:if> >${status.index}</span></a>
					</c:forEach>	
					<a <c:if test="${pager.curPage==pager.totalPages}">class="disabled"</c:if>  <c:if test="${pager.curPage!=pager.totalPages}">href="/web/spring/blog/list.htm?blogType=${blogType}&pageNum=${pager.curPage+1}"</c:if>>${lang.next[n18]}</a>		
					<a <c:if test="${pager.curPage==pager.totalPages}">class="disabled"</c:if>  <c:if test="${pager.curPage!=pager.totalPages}">href="/web/spring/blog/list.htm?blogType=${blogType}&pageNum=${pager.totalPages}"</c:if>>${lang.last[n18]}</a>  		
			</div>
		</td>
	</tr>
</table>