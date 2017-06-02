<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-8 ">
		<div class="panel">
			<c:forEach var="data" items="${flowDatas}">
				<div class="media">
					<a class="pull-left" href="page-activity.html#"> <img
						class="media-object img-rounded" style="width: 30px"
						src="//${cdnDomain}/cdn/icons/photo.png">
					</a>
					<div class="media-body">
						<div class="pull-right small">
							<fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd" />
						</div>
						<a href="javascript:void();">
							<div class="media-heading">
								<strong>${data.nickName}</strong>
							</div>
						</a> <a href="javascript:void();">${data.eventNote}</a>
						<p>${data.note}</p>

						<div class="picGallery row">
							<c:forEach var="pic" items="${data.picAttachs}">
								<div style="margin-bottom: 10px" class="col-md-2">
									<img class="img-thumbnail" src="${pic.path}">
								</div>
							</c:forEach>
						</div>
						<c:if test="${fn:length(data.otherAttachs)>0}">
							<div class="inbox">
								<div class="message">
									<div class="attachments">
										<ul>
											<c:forEach var="other" items="${data.otherAttachs}">
												<li><span class="label label-danger">${other.fileType}</span>
													<b>${other.filename}</b> <i>(${other.fileSizeStr})</i> <span
													class="quickMenu"> <a target="blank"
														href="${other.path}" class="fa fa-cloud-download"><i></i></a>
												</span></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>