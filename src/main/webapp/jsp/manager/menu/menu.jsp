<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
%>
 	<div class="main">
 		
 		<div class="content_wrap">
 			<div class="row">
 				<div class="col-md-6">
		 			<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
						<!-- 
						<div style="align:center;padding-top:10px;padding-left:150px;"><a href="<%=basePath%>menu/save.htm"><input style="width:80px;height:30px;background:#CF7A7A;" type="button" value="保存"/></a></div>
						 -->
						 <div style="align:center;padding-top:10px;padding-left:150px;"><input style="width:80px;height:30px;background:#CF7A7A;" type="button" onclick="doSave();" value="保存"/></div>
					</div>
 				</div>
 				<div class="col-md-6">
 					<div class="panel panel-default">
		            <div class="panel-heading">
		                <h2><i class="fa fa-indent red"></i><strong>栏目详情</strong></h2>
		            </div>
		            <div class="panel-body">
						<form id="menuForm" action="" method="post">
							<input type="hidden" name="id" value="${menu.id}">
			                <div class="form-group">
			                    <label for="nf-email">栏目名称</label>
			                    <input type="input"  name="name" class="form-control" placeholder="栏目名称">
			                </div>
			                <div class="form-group">
			                    <label for="nf-password">栏目类型</label>
			                    <div>
				                    <label class="radio-inline" for="inline-radio1">
			                        	<input type="radio" id="inline-radio1" name="type" value="MULTI"> 多篇
			                        </label>
			                        <label class="radio-inline" for="inline-radio1">
			                        	<input type="radio" id="inline-radio1" name="type" value="SINGLE"> 单篇
			                        </label>
			                        <label class="radio-inline" for="inline-radio1">
			                        	<input type="radio" id="inline-radio1" name="type" value="LINK"> 链接
			                        </label>
		                        </div>
			                </div>
			                <div class="form-group">
			                    <label for="nf-password">链接</label>
			                    <input type="input" name="link" class="form-control" placeholder="链接">
			                </div>
			                <div class="form-group">
			                    <label for="nf-password">资源类型</label>
			                    <div>
				                    <label class="radio-inline" for="inline-radio2">
			                        	<input type="radio" id="mediaType" name="mediaType" value="RICHTEXT">  富文本
			                        </label>
			                        <label class="radio-inline" for="inline-radio2">
			                        	<input type="radio" id="mediaType" name="mediaType" value="hide"> 视频
			                        </label>
			                        <label class="radio-inline" for="inline-radio2">
			                        	<input type="radio" id="mediaType" name="mediaType" value="hide"> 直播
			                        </label>
		                        </div>
			                </div>
			                <div class="form-group">
			                    <label for="nf-password">使用状态</label>
			                    <label class="switch switch-success">
							      <input name="show" type="checkbox" class="switch-input" checked="">
							      <span class="switch-label" data-on="On" data-off="Off"></span>
							      <span class="switch-handle"></span>
							    </label>

			                </div>
			           
					</div>
					<div class="panel-footer">
	                    <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> 提交</button>
                        <button id="reset"  type="button" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> 重置</button>
	                </div>
	                 </form>
		        </div>
 				</div>
 			</div>

		</div>
		<div id="rMenu">
			<ul>
				<li id="m_add" onclick="addTreeNode();">添加  </li>
				<li id="m_del" onclick="removeTreeNode();">删除</li>
				<li id="m_edit" onclick="editTreeNode(true);">编辑</li>
				<li id="m_reset" onclick="resetTree();">撤销</li>
			</ul>
		</div>
 	</div>
 	
<link rel="stylesheet" href="<%=basePath %>ztree/css/zTreeStyle/zTreeStyle.mine.css" type="text/css">
 	<script type="text/javascript" src="<%=basePath %>ztree/js/jquery-1.4.4.min.js"></script>	
 	<script type="text/javascript" src="<%=basePath %>/js/menu/menu.js"></script>

	<style type="text/css">
		div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
		div#rMenu ul li{
			margin: 1px 0;
			padding: 0 5px;
			cursor: pointer;
			list-style: none outside none;
			background-color: #DFDFDF;
		}
	</style>