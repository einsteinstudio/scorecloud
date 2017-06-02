<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
		<div class="col-md-12">
			<div class="panel-footer ">
				<c:if test="${canOp}">
					<button type="button" class="btn btn-sm btn-info agree">
						<i class="fa fa-dot-circle-o"></i> 同意
					</button>
					<button type="button" class="btn btn-sm btn-info reject">
						<i class="fa fa-dot-circle-o"></i> 拒绝
					</button>
				</c:if>
				<button type="button" class="btn btn-sm btn-info comment">
					<i class="fa fa-dot-circle-o"></i> 评论
				</button>
				<c:if test="${!task.finished}">
				<button type="button" class="btn btn-sm btn-info revoke">
					<i class="fa fa-dot-circle-o"></i> 回收
				</button>
				</c:if>
				<!-- <button id="reset" type="reset" class="btn btn-sm btn-danger">
				<i class="fa fa-ban"></i> 重置
			</button> -->
				<a target="_self" href="list.htm"><button id="reset"
						type="button" class="btn btn-sm btn-info">返回</button></a>
			</div>
		</div>
	</div>