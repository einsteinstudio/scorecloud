<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/manager/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>管控中心</title>		
		

		<!-- Fav and touch icons -->
		<link rel="shortcut icon" href="<%=basePath %>assets/ico/favicon.ico" type="image/x-icon" />    
	<link rel="stylesheet" href="//${cdnDomain}/cdn/library/upload-img/control/css/zyUpload.css" type="text/css">
	
	    <!-- Css files -->
	    <link href="<%=basePath %>assets/css/bootstrap.min.css" rel="stylesheet">		
		<link href="<%=basePath %>assets/css/jquery.mmenu.css" rel="stylesheet">		
		<link href="<%=basePath %>assets/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=basePath %>assets/css/climacons-font.css" rel="stylesheet">
		<link href="<%=basePath %>assets/plugins/xcharts/css/xcharts.min.css" rel=" stylesheet">		
		<link href="<%=basePath %>assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet">
		<link href="<%=basePath %>assets/plugins/morris/css/morris.css" rel="stylesheet">
		<link href="<%=basePath %>assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
		<link href="<%=basePath %>assets/plugins/jvectormap/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">	    
	    <link href="<%=basePath %>assets/css/style.min.css" rel="stylesheet">
		<link href="<%=basePath %>assets/css/add-ons.min.css" rel="stylesheet">	
				<link href="<%=basePath %>/css/template.css" rel="stylesheet">		
		
		<link href="//${cdnDomain}/cdn/library/jbox-v2.3/jBox/Skins2/Blue/jbox.css" rel="stylesheet">		
		<script src="<%=basePath %>assets/js/jquery-2.1.1.min.js"></script>	
		<script src="<%=basePath %>assets/js/pure-common.js"></script>
		
		
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <style type="text/css">
	      /* 内部样式 */
	     	#uploadForm *  {
			     -webkit-box-sizing: content-box; 
			    -moz-box-sizing: content-box;
			     box-sizing: content-box; 
			}
			
			.jbox-body * {
			     -webkit-box-sizing: content-box; 
			    -moz-box-sizing: content-box;
			     box-sizing: content-box; 
			}
			
			.activity .panel{
    width: 30px;
}
	    </style>
	    	<script>
				$(document).ready(function() {
					$('.nav').each(function(){
						if($(this).find('li').length==0){
							$(this).parent('li').hide();
						}
					});
				});
			</script>
	</head>
</head>

<body>
	<jsp:include  page="header.jsp"/> 

	<div class="container-fluid content">

		
		<jsp:include  page="main_menu.jsp"/> 
		<!-- start: Content -->
		<jsp:include  page="${innerPage}.jsp?${urlParam}"/> 
		<!-- end: Content -->
		<br><br><br>
		
		
		<jsp:include  page="usage.jsp"/> 		
		
	</div><!--/container-->
		

	
	<div class="clearfix"></div>
	
		
	<!-- start: JavaScript-->
	<!--[if !IE]>-->

			<script src="<%=basePath %>assets/js/jquery-2.1.1.min.js"></script>

	<!--<![endif]-->

	<!--[if IE]>
	
		<script src="<%=basePath %>assets/js/jquery-1.11.1.min.js"></script>
	
	<![endif]-->

	<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=basePath %>assets/js/jquery-2.1.1.min.js'>"+"<"+"/script>");
		</script>

	<!--<![endif]-->

	<!--[if IE]>
	
		<script type="text/javascript">
	 	window.jQuery || document.write("<script src='<%=basePath %>assets/js/jquery-1.11.1.min.js'>"+"<"+"/script>");
		</script>
		
	<![endif]-->
	<script src="<%=basePath %>assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="<%=basePath %>assets/js/bootstrap.min.js"></script>	
	
	
	<!-- page scripts -->
	<script src="<%=basePath %>assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
	<script src="<%=basePath %>assets/plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
	<script src="<%=basePath %>assets/plugins/moment/moment.min.js"></script>
	<script src="<%=basePath %>assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>
	<!--[if lte IE 8]>
		<script language="javascript" type="text/javascript" src="<%=basePath %>assets/plugins/excanvas/excanvas.min.js"></script>
	<![endif]-->
	<script src="<%=basePath %>assets/plugins/flot/jquery.flot.min.js"></script>
	<script src="<%=basePath %>assets/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="<%=basePath %>assets/plugins/flot/jquery.flot.stack.min.js"></script>
	<script src="<%=basePath %>assets/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="<%=basePath %>assets/plugins/flot/jquery.flot.time.min.js"></script>
	<script src="<%=basePath %>assets/plugins/flot/jquery.flot.spline.min.js"></script>
	<script src="<%=basePath %>assets/plugins/xcharts/js/xcharts.min.js"></script>
	<script src="<%=basePath %>assets/plugins/autosize/jquery.autosize.min.js"></script>
	<script src="<%=basePath %>assets/plugins/placeholder/jquery.placeholder.min.js"></script>
	<script src="<%=basePath %>assets/plugins/datatables/js/jquery.dataTables.min.js"></script>
	<script src="<%=basePath %>assets/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=basePath %>assets/plugins/raphael/raphael.min.js"></script>
	<script src="<%=basePath %>assets/plugins/morris/js/morris.min.js"></script>
	<script src="<%=basePath %>assets/plugins/jvectormap/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="<%=basePath %>assets/plugins/jvectormap/js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=basePath %>assets/plugins/jvectormap/js/gdp-data.js"></script>	
	<script src="<%=basePath %>assets/plugins/gauge/gauge.min.js"></script>
	
	
	<!-- theme scripts -->
	<script src="<%=basePath %>assets/js/SmoothScroll.js"></script>
	<script src="<%=basePath %>assets/js/jquery.mmenu.min.js"></script>
	<script src="<%=basePath %>assets/js/core.min.js"></script>
	<script src="<%=basePath %>assets/plugins/d3/d3.min.js"></script>	
	
	<!-- inline scripts related to this page -->
	<script src="<%=basePath %>assets/js/pages/index.js"></script>	
	
	<!-- end: JavaScript-->
	
	<!-- self -->
		<script type="text/javascript" src="//${cdnDomain}/cdn/library/laydate/laydate.dev.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>ztree/js/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="/plugins/jquery/jquery.validate.js"></script>
	<script type="text/javascript" src="/plugins/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/template.js"></script>
	<script type="text/javascript" src="//${cdnDomain}/cdn/library/upload-img/core/zyFile.js"></script>
	<script type="text/javascript" src="/manager/js/common/zyUpload.js"></script>
	
	<script type="text/javascript" src="//${cdnDomain}/cdn/library/jbox-v2.3/jBox/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="//${cdnDomain}/cdn/library/jbox-v2.3/jBox/i18n/jquery.jBox-zh-CN.js"></script>


	
</body>
</html>