<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
%>
<div class="row">
				
			<!-- start: Main Menu -->
			<div class="sidebar ">
								
				<div class="sidebar-collapse">
					<div class="sidebar-header t-center">
                        <a href="/manager/index.htm"><span>${cms.title.content}<i class="fa fa-space-shuttle fa-3x blue"></i></span></a>
                    </div>										
					<div class="sidebar-menu">						
						<ul class="nav nav-sidebar">
							<li>
								<a href="#"><i class="fa fa-laptop"></i><span class="text"> 控制面板</span></a>		
								<ul class="nav sub">
									<li><a href="/manager/message/list.htm"><i class="fa fa-car"></i><span class="text">消息中心</span></a></li>
								</ul>						
							</li>
							<li>
								<a href="#"><i class="fa fa-laptop"></i><span class="text"> 校园OA</span></a>								
								<ul class="nav sub">
									<c:if test="${not empty rightMap.WORKFLOW_ADD}"><li><a href="<%=basePath %>workflow/apply_main.htm"><i class="fa fa-car"></i><span class="text">新建流程</span></a></li></c:if>
									<c:if test="${not empty rightMap.WORKFLOW_AUDIT}"><li><a href="<%=basePath %>workflow/mytask/list.htm"><i class="fa fa-car"></i><span class="text">待审流程</span></a></li></c:if>
									<c:if test="${not empty rightMap.WORKFLOW_AUDIT}"><li><a href="/manager/workflow/myhistory/list.htm"><i class="fa fa-car"></i><span class="text">历史审核</span></a></li></c:if>
									<c:if test="${not empty rightMap.WORKFLOW_VIEW}"><li><a href="/manager/workflow/purchaseflow/list.htm"><i class="fa fa-car"></i><span class="text">采购流程</span></a></li></c:if>
									<c:if test="${not empty rightMap.WORKFLOW_VIEW}"><li><a href="/manager/workflow/usecarflow/list.htm"><i class="fa fa-car"></i><span class="text">车管流程</span></a></li></c:if>
									<c:if test="${not empty rightMap.WORKFLOW_VIEW}"><li><a href="/manager/workflow/repaireflow/list.htm"><i class="fa fa-car"></i><span class="text">报修流程</span></a></li></c:if>									
								</ul> 
							</li>
							<li>
								<a href="#"><i class="fa fa-file-text"></i><span class="text">发布管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">
									<c:if test="${not empty rightMap.BLOG_VIEW}"><li><a href="/manager/blog/list.htm"><i class="fa fa-car"></i><span class="text">发布内容</span></a></li></c:if>
									<c:if test="${not empty rightMap.BLOG_ADD}"><li><a href="/manager/workflow/blogflow/list.htm"><i class="fa fa-car"></i><span class="text">我的申请</span></a></li></c:if>
									<c:if test="${not empty rightMap.BLOG_AUDIT}"><li><a href="/manager/workflow/myblog/list.htm"><i class="fa fa-car"></i><span class="text">待审内容</span></a></li></c:if>
									<c:if test="${not empty rightMap.BLOG_AUDIT}"><li><a href="/manager/workflow/blogflow/history/list.htm"><i class="fa fa-car"></i><span class="text">历史审核</span></a></li></c:if>
									<c:if test="${not empty rightMap.LANMU_VIEW}"><li><a href="<%=basePath %>menu/list.htm?menuModule=LANMU"><i class="fa fa-car"></i><span class="text">栏目管理</span></a></li></c:if>
									<c:if test="${not empty rightMap.LANMU_VIEW}"><li><a href="<%=basePath %>menu/list.htm?menuModule=ENLANMU"><i class="fa fa-car"></i><span class="text">英文栏目管理</span></a></li></c:if>
									<c:if test="${ not empty rightMap.CMS_VIEW}"><li><a href="/manager/cms/list.htm"><i class="fa fa-car"></i><span class="text">CMS管理</span></a></li></c:if>
								</ul>	
							</li>
							
							<li>
								<a href="#"><i class="fa fa-file-text"></i><span class="text">系统管理</span> <span class="fa fa-angle-down pull-right"></span></a>
								<ul class="nav sub">
									<c:if test="${ not empty rightMap.USER_VIEW}"><li><a href="<%=basePath %>user/list.htm"><i class="fa fa-car"></i><span class="text">用户管理</span></a></li></c:if>
									<c:if test="${ not empty rightMap.ROLE_VIEW}"><li><a href="<%=basePath %>role/list.htm"><i class="fa fa-car"></i><span class="text">角色管理</span></a></li></c:if>
									<c:if test="${ not empty rightMap.DEPT_VIEW}"><li><a href="/manager/dept/list.htm"><i class="fa fa-car"></i><span class="text">部门管理</span></a></li></c:if>
									<c:if test="${ not empty rightMap.INI_VIEW}"><li><a href="/manager/ini/list.htm"><i class="fa fa-car"></i><span class="text">配置管理</span></a></li></c:if>
									<c:if test="${ not empty rightMap.WORKFLOW_TEMPLATE_VIEW}"><li><a href="/manager/workflow/template/list.htm"><i class="fa fa-car"></i><span class="text">流程模板管理</span></a></li></c:if>
								</ul>	
							</li>
						  <%-- <c:if test="${fn:length(roledMenu.children) > 0}"><ul>中文栏目</ul></c:if>
						  <c:forEach var="menu" items="${roledMenu.children}" >
							 	<c:if test="${menu.checked}">
							     <li>
									<a href="#"><i class="fa fa-file-text"></i><span class="text">${menu.name}</span> <span class="fa fa-angle-down pull-right"></span></a>
									<ul class="nav sub">
										<c:forEach var="mm" items="${menu.children}" >
											<li><a href="<%=basePath %>blog/list.htm?type=${mm.name}"><i class="fa fa-car"></i><span class="text">${mm.name}</span></a></li>					
										</c:forEach>
									</ul>
								</li> 
								</c:if>  
	      				  </c:forEach>  --%>
	      				 <%-- <c:if test="${ fn:length(roledEnMenu.children) > 0}"><ul>英文栏目</ul></c:if>
						  <c:forEach var="menu" items="${roledEnMenu.children}" >
							 	<c:if test="${menu.checked}">
							     <li>
									<a href="#"><i class="fa fa-file-text"></i><span class="text">${menu.name}</span> <span class="fa fa-angle-down pull-right"></span></a>
									<ul class="nav sub">
										<c:forEach var="mm" items="${menu.children}" >
											<li><a href="<%=basePath %>blog/list.htm?type=${mm.name}"><i class="fa fa-car"></i><span class="text">${mm.name}</span></a></li>					
										</c:forEach>
									</ul>
								</li> 
								</c:if>  
	      				  </c:forEach>  --%> 
							
							

						</ul>
					</div>					
				</div>
				<div class="sidebar-footer">					
					
	
					
					<!-- <ul class="sidebar-terms">
						<li><a href="index.html#">Terms</a></li>
						<li><a href="index.html#">Privacy</a></li>
						<li><a href="index.html#">Help</a></li>
						<li><a href="index.html#">About</a></li>
					</ul> -->
					
					<div class="copyright text-center">
						<small>${cms.title.content}版权所有</small>
					</div>					
				</div>	
				
			</div>
			<!-- end: Main Menu -->

		