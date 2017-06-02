<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pageing">
		<a  <c:if test="${pager.curPage==1}">class="disabled"</c:if> <c:if test="${pager.curPage!=1}">href="/blog/list.htm?blogType=${blogType}&pageNum=1"</c:if> >首页</a>
		<a <c:if test="${pager.startPage==pager.curPage}">class="disabled"</c:if>   <c:if test="${pager.startPage!=pager.curPage}">href="/blog/list.htm?blogType=${blogType}&pageNum=${pager.curPage-1}"</c:if> >上一页</a>
		<c:forEach  varStatus="status" begin="${pager.startPage}" end="${pager.endPage}" step="1">
			<a <c:if test="${status.index==pager.curPage}">class="page-btn-cur"</c:if>  href="/blog/list.htm?blogType=${blogType}&pageNum=${status.index}">${status.index}</a></li>	
		</c:forEach>	
		<a <c:if test="${pager.curPage==pager.totalPages}">class="disabled"</c:if>  <c:if test="${pager.curPage!=pager.totalPages}">href="/blog/list.htm?blogType=${blogType}&pageNum=${pager.curPage+1}"</c:if>>下一页</a>		
		<a <c:if test="${pager.curPage==pager.totalPages}">class="disabled"</c:if>  <c:if test="${pager.curPage!=pager.totalPages}">href="/blog/list.htm?blogType=${blogType}&pageNum=${pager.totalPages}"</c:if>>尾页</a>  		
</div>