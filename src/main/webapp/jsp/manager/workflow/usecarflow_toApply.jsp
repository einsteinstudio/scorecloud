<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/manager/";
%>
<div class="main">
	<form id="addForm" action="" method="post" class="form">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>
					<i class="fa fa-indent red"></i><strong>基本信息</strong>
				</h2>
			</div>
			<div class="panel-body">
			 	<input type="hidden" name="token" value="${token}">
				<input type="hidden" name="id" value="">
				<div class="form-group">
					<label>部门</label> <input type="input" name="department"
						class="form-control" placeholder="部门" value="">
				</div>
				<div class="form-group">
					<label>用车原因</label> <input type="input" name="useReason"
						class="form-control" placeholder="用车原因" value="">
				</div>
				<div class="form-group">
					<label>出发地</label> <input type="input" name="fromLocation"
						class="form-control" placeholder="出发地" value="">
				</div>
				<div class="form-group">
					<label>目的地</label> <input type="input" name="toLocation"
						class="form-control" placeholder="目的地" value="">
				</div>
				<div class="form-group">
					<label>还车地点</label> <input type="input" name="returnLocation"
						class="form-control" placeholder="还车地点" value="">
				</div>
				<div class="form-group">
					<label for="nf-password">是否返程</label> <label
						class="switch switch-success"> <input name="changeable"
						type="checkbox" class="switch-input"  <c:if test="${ini.returnBack}">checked=""</c:if> ><span
						class="switch-label" data-on="On" data-off="Off"></span> <span
						class="switch-handle"></span>
					</label>
				</div>
				<div class="form-group">
					<label>返程时间</label> <input type="input" id="returnDate" name="returnDate"
						class="form-control" placeholder="返程时间" value="">
				</div>
				<div class="form-group">
					<label>用车开始时间</label> <input type="input" id="fromDate" name="fromDate"
						class="form-control" placeholder="用车开始时间" value="">
				</div>
				<div class="form-group">
					<label>用车结束时间</label> <input type="input" id="toDate" name="toDate"
						class="form-control" placeholder="用车结束时间" value="">
				</div>
				
				
				<div class="form-group">
					<label">备注</label> <input type="input" name="note"
						class="form-control" placeholder="备注">
				</div>
				<div class="form-group">
					<label">相关图片</label>
					<div id="uploadImage" class="demo uploadFrame"></div>	
					<input type="hidden" name="picUrl" value="">	
				</div>		
			</div>

		</div>


		<div class="purchase-detail-wrap ">
			<div class="panel panel-default purchase-detail col-md-6">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-indent red"></i><strong>车辆明细</strong>
					</h2>
					<div class="panel-actions">
						<a class="btn-close-d btn-close-detail"><i class="fa fa-times"></i></a>
					</div>
				</div>
				<div class="panel-body ">
					<input type="hidden" name="id" value="">


					<div class="form-group">
						<label class="col-md-3">车量类型</label>
						<div class="col-md-9">
							<input type="input" name="carType[]" class="form-control"
								placeholder="车量类型" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3">数量</label>
						<div class="col-md-9">
							<input type="input" name=carCount[] class="form-control"
								placeholder="数量" value="">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3">其他要求</label>
						<div class="col-md-9">
							<input type="input" name="other[]" class="form-control"
								placeholder="其他要求" value="">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<button id="add-detail" type="button" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 增加明细
			</button>
			<!-- <button id="reset" type="reset" class="btn btn-sm btn-danger">
				<i class="fa fa-ban"></i> 重置
			</button> -->
			<a target="_self" href="list.htm"><button id="reset"
					type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
	</form>

</div>
<script type="text/javascript" src="//${cdnDomain}/cdn/template/proton/assets/js/jquery-2.1.1.min.js"></script>

<!-- 引用核心层插件 -->
<!-- 引用控制层插件 -->
<script type="text/javascript" src="/manager/js/workflow/usecarflow_toApply.js"></script>
<script>
	$(document).ready(
			function() {
				$("#uploadImage").zyUpload({
					width            :   "90%",                 // 宽度
					height           :   "400px",                 // 宽度
					itemWidth        :   "120px",                 // 文件项的宽度
					itemHeight       :   "100px",                 // 文件项的高度
					url              :   "/manager/upload/addImage.htm",  // 上传文件的路径
					multiple         :   true,                    // 是否可以多个文件上传
					dragDrop         :   true,                    // 是否可以拖动上传文件
					del              :   true,                    // 是否可以删除文件
					finishDel        :   false,  				  // 是否在上传文件完成后删除预览
					/* 外部获得的回调接口 */
					onSelect: function(files, allFiles){                    // 选择文件的回调方法
						console.info("当前选择了以下文件：");
						console.info(files);
						console.info("之前没上传的文件：");
						console.info(allFiles);
					},
					onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
						console.info("当前删除了此文件：");
						console.info(file);
						console.info("当前剩余的文件：");
						console.info(surplusFiles);
					},
					onSuccess: function(file,response){                    // 文件上传成功的回调方法
						console.info("此文件上传成功：");
						console.info(file);
					},
					onFailure: function(file){                    // 文件上传失败的回调方法
						console.info("此文件上传失败：");
						console.info(file);
					},
					onComplete: function(responseInfo){           // 上传完成的回调方法
						console.info("文件上传完成");
						console.info(responseInfo);
					}
				});
				laydate({
					elem : '#fromDate',
					format : 'YYYY-MM-DD hh:mm',
					defSkin : "danlan",
					istime: true,

				});
				laydate({
					elem : '#toDate',
					format : 'YYYY-MM-DD hh:mm',
					defSkin : "danlan",
					istime: true,

				});
				laydate({
					elem : '#returnDate',
					format : 'YYYY-MM-DD hh:mm',
					defSkin : "danlan",
					istime: true,

				});
	$("#add-detail")
								.bind(
										"click",
										function() {
											$(".panel-footer")
													.before(
															'<div class="purchase-detail-wrap ">'
																	+ $(".form")
																			.find(
																					".purchase-detail-wrap")
																			.first()
																			.html()
																	+ "</div>");
											/* if ($(".purchase-detail-wrap").find(
													'.purchase-detail').is(":visible")) {
												
											} else {
												$(".purchase-detail-wrap").find(
														'.purchase-detail').show();
											} */
										});
						//点击隐藏标签
						$(".btn-close-detail")
								.live(
										"click",
										function() {
											if ($(this).parents('#addForm')
													.find('.purchase-detail').length <= 1) {
												showInfo("warning",
														"请至少保留一个明细")
											} else {
												$(this)
														.parents(
																'.purchase-detail-wrap')
														.remove();
											}
										});
					});
</script>



