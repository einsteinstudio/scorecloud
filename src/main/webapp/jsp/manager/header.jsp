<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
%>
<!-- start: Header -->
<div class="navbar" role="navigation">

	<div class="container-fluid">		
		
		<ul class="nav navbar-nav navbar-actions navbar-left">
			<li class="visible-md visible-lg"><a href="#" id="main-menu-toggle"><i class="fa fa-th-large"></i></a></li>
			<li class="visible-xs visible-sm"><a href="#" id="sidebar-menu"><i class="fa fa-navicon"></i></a></li>			
		</ul>
		
		<form class="navbar-form navbar-left">
			<button type="submit" class="fa fa-search"></button>
			<input type="text" class="form-control" placeholder="Search..."></a>
		</form>
		<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >免费模板</a></div>
        <ul class="nav navbar-nav navbar-right">
			<%-- <li class="dropdown visible-md visible-lg">
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope-o"></i><span class="badge">5</span></a>
        		<ul class="dropdown-menu">
					<li class="dropdown-menu-header">
						<strong>Messages</strong>
						<div class="progress thin">
						  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%">
						    <span class="sr-only">30% Complete (success)</span>
						  </div>
						</div>
					</li>
					<li class="avatar">
						<a href="page-inbox.html">
							<img class="avatar" src="<%=basePath %>assets/img/avatar1.jpg">
							<div>New message</div>
							<small>1 minute ago</small>
							<span class="label label-info">NEW</span>
						</a>
					</li>
					<li class="avatar">
						<a href="page-inbox.html">
							<img class="avatar" src="<%=basePath %>assets/img/avatar2.jpg">
							<div>New message</div>
							<small>3 minute ago</small>
							<span class="label label-info">NEW</span>
						</a>
					</li>
					<li class="avatar">
						<a href="page-inbox.html">
							<img class="avatar" src="<%=basePath %>assets/img/avatar3.jpg">
							<div>New message</div>
							<small>4 minute ago</small>
							<span class="label label-info">NEW</span>
						</a>
					</li>
					<li class="avatar">
						<a href="page-inbox.html">
							<img class="avatar" src="<%=basePath %>assets/img/avatar4.jpg">
							<div>New message</div>
							<small>30 minute ago</small>
						</a>
					</li>
					<li class="avatar">
						<a href="page-inbox.html">
							<img class="avatar" src="<%=basePath %>assets/img/avatar5.jpg">
							<div>New message</div>
							<small>1 hours ago</small>
						</a>
					</li>						
					<li class="dropdown-menu-footer text-center">
						<a href="page-inbox.html">View all messages</a>
					</li>	
        		</ul>
      		</li> --%>
			<!-- <li class="dropdown visible-md visible-lg">
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell-o"></i><span class="badge">3</span></a>
        		<ul class="dropdown-menu">
					<li class="dropdown-menu-header">
						<strong>Notifications</strong>
						<div class="progress thin">
						  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%">
						    <span class="sr-only">30% Complete (success)</span>
						  </div>
						</div>
					</li>							
                    <li class="clearfix">
						<i class="fa fa-comment"></i> 
                        <a href="page-activity.html" class="notification-user"> Sharon Rose </a> 
                        <span class="notification-action"> replied to your </span> 
                        <a href="page-activity.html" class="notification-link"> comment</a>
                    </li>
                    <li class="clearfix">
                        <i class="fa fa-pencil"></i> 
                        <a href="page-activity.html" class="notification-user"> Nadine </a> 
                        <span class="notification-action"> just write a </span> 
                        <a href="page-activity.html" class="notification-link"> post</a>
                    </li>
                    <li class="clearfix">
                        <i class="fa fa-trash-o"></i> 
                        <a href="page-activity.html" class="notification-user"> Lorenzo </a> 
                        <span class="notification-action"> just remove <a href="#" class="notification-link"> 12 files</a></span> 
                    </li>                        
					<li class="dropdown-menu-footer text-center">
						<a href="page-activity.html">View all notification</a>
					</li>
        		</ul>
      		</li> -->
			<!-- <li class="dropdown visible-md visible-lg">
				 <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i></a>					
				<ul class="dropdown-menu update-menu" role="menu">
					<li><a href="#"><i class="fa fa-database"></i> Database </a>
                    </li>
                    <li><a href="#"><i class="fa fa-bar-chart-o"></i> Connection </a>
                    </li>
                    <li><a href="#"><i class="fa fa-bell"></i> Notification </a>
                    </li>
                    <li><a href="#"><i class="fa fa-envelope"></i> Message </a>
                    </li>
                    <li><a href="#"><i class="fa fa-flash"></i> Traffic </a>
                    </li>
					<li><a href="#"><i class="fa fa-credit-card"></i> Invoices </a>
                    </li>
                    <li><a href="#"><i class="fa fa-dollar"></i> Finances </a>
                    </li>
                    <li><a href="#"><i class="fa fa-thumbs-o-up"></i> Orders </a>
                    </li>
					<li><a href="#"><i class="fa fa-folder"></i> Directories </a>
                    </li>
                    <li><a href="#"><i class="fa fa-users"></i> Users </a>
                    </li>		
				</ul>
			</li> -->
			<li class="dropdown visible-md visible-lg">
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img class="user-avatar" src="${sessionScope.account.photo}" alt="user-mail">${sessionScope.username}</a>
        		<ul class="dropdown-menu">
					<li class="dropdown-menu-header">
						<strong>账号</strong>
					</li>						
					<li><a href="/manager/user/modifyMine.htm"><i class="fa fa-wrench"></i> 个人信息</a></li>
					<li class="divider"></li>						
					<li><a href="/manager/logout.htm"><i class="fa fa-sign-out"></i> Logout</a></li>	
        		</ul>
      		</li>
			<li><a href="/manager/logout.htm"><i class="fa fa-power-off"></i></a></li>
		</ul>
		
	</div>
	
</div>
<!-- end: Header -->