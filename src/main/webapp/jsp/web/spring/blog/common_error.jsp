<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">

<meta http-equiv="x-ua-compatible" content="ie=7">
<title>${blog.title}</title>
	<style type="text/css">
	.sendCmt {
	font-size: 16px;
	color: #8B636C;
	text-align: center;
	display: none;
	}

	.input-comment {
	position: relative;
	background-color: #fff;
	margin-top: 5px;
	margin-left: auto;
	margin-right: auto;
	max-width: 94%;
	padding: 5px;
	border: 1px solid #d3d3d3;
	}

	.input-txt {
	resize: none;
	border:0px;
	width:100%;
	height:64px;
	font-size:14px;
	}

	.input-btn {
	text-align: center;
	margin: 5px 0 10px 0;
	}
	.orange:hover {
	background: #f47c20;
	background: -webkit-gradient(linear, left top, left bottom, from(#f88e11), to(#f06015));
	background: -moz-linear-gradient(top, #f88e11, #f06015);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11', endColorstr='#f06015');
	}
	.button:hover {
	text-decoration: none;
	}
	.orange {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#faa51a), to(#f47a20));
	background: -moz-linear-gradient(top, #faa51a, #f47a20);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a', endColorstr='#f47a20');
	}
	.button {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 16px/100% 'Microsoft yahei',Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0,0,0,.3);
	-webkit-border-radius: 3em;
	-moz-border-radius: 3em;
	border-radius: 3em;
	-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
	-moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
	box-shadow: 0 1px 2px rgba(0,0,0,.2);
	}

	.hot_comment {
	margin-top: 5px;
	padding: 5px 15px 5px 15px;
	background-color:#FFF;
	}

	.rele_title {
	padding-bottom: 5px;
	height:20px;
	}

	.cmt-title {
	/*font:15px Arial;*/
	float:left;
	}

	.write-cmt {
	float:right;
	width: 30px;
	text-alignt: center;
	}

	.cancel-cmt {
	font:14px Arial;
	display: none;
	}

	.icon_cmt_gray {
	background: transparent url(//${storageDomain}/storage/gmxx/images/write_cmt.png) no-repeat 0 0;
	width: 16px;
	height: 16px;
	vertical-align: middle;
	display: inline-block;
	-webkit-background-size: 100% auto;
	background-size: 100% auto;
	}

	.history_comment {
	margin-bottom: 15px;
	}

	.hc_list {
	margin-top: 5px;
	border-top: solid 1px #ecede8;
	padding-top: 5px;
	}

	.hc_left {
	float:left;
	width:40px;
	}
	.hc_right {
	margin-right:15px;
	margin-left:40px;
	text-align: left;
	padding: 5px;
	}
	.hc_name {
	font-size: 14px;
	color: #8c8c8c;
	position:relative;
	}

	.hc_img {
	float: left;
	margin: 5px;
	width: 30px;
	height: 30px;
	}

	.hc_info{
	font-size:14px;
	position:relative;
	}
	.hc_time {
	font:12px Arial;
	color: #8c8c8c;
	position:relative;
	}

	.loadmore-div {
	height:40px;
	padding-top: 15px;
	padding-bottom: 10px;
	text-align: center;
	}

	.loadmore-p {
	text-align: center;
	display:block;
	color: #8c8c8c;
	font-size: 14px;
	}

	.loadmore-img {
	display:none;
	height:20px;
	margin:0 auto;
	}
	.pageNo {display:none}
	.blogType {display:none}
	.objectId {display:none}
	</style>
<script language="javascript" type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/prototype.js"></script>
<script language="javascript" type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/scriptaculous.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/spring/index_files/util.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/spring/index_files/effects.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/spring/index_files/dragdrop.js"></script>
<script type="text/javascript" src="//${cdnDomain}/cdn/spring/index_files/controls.js"></script>
<script language="javascript" type="text/javascript"
	src="//${cdnDomain}/cdn/spring/index_files/checklogin.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="//${cdnDomain}/cdn/spring/index_files/menu.js"></script>
<script language="JavaScript" type="text/JavaScript">
<!--
/*第一种形式 第二种形式 更换显示样式*/
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}
//-->
</script>
<script language="javascript" type="text/javascript">
    //双击鼠标滚动屏幕的代码
    var currentpos, timer;
    function initialize() {
        timer = setInterval("scrollwindow ()", 30);
    }
    function sc() {
        clearInterval(timer);
    }
    function scrollwindow() {
        currentpos = document.body.scrollTop;
        window.scroll(0, ++currentpos);
        if (currentpos != document.body.scrollTop)
            sc();
    }
    document.onmousedown = sc
    document.ondblclick = initialize

    //更改字体大小
    var status0 = '';
    var curfontsize = 10;
    var curlineheight = 18;
    function fontZoomA() {
        if (curfontsize > 8) {
            document.getElementById('fontzoom').style.fontSize = (--curfontsize) + 'pt';
            document.getElementById('fontzoom').style.lineHeight = (--curlineheight) + 'pt';
        }
    }
    function fontZoomB() {
        if (curfontsize < 64) {
            document.getElementById('fontzoom').style.fontSize = (++curfontsize) + 'pt';
            document.getElementById('fontzoom').style.lineHeight = (++curlineheight) + 'pt';
        }
    }
</script>
<link href="//${cdnDomain}/cdn/spring/index_files/bhsx.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
<!--
//改变图片大小
function resizepic(thispic)
{
  return true;
}
//无级缩放图片大小
function bbimg(o)
{
  return true;
}
-->
</script>

</head>

<body>
	
	<jsp:include page="../header.jsp" />
	
	<table width="1000" border="0" align="center" cellpadding="0"
		cellspacing="0" bgcolor="#FFFFFF">
		<tbody>
			<tr>
				<td height="14"></td>
			</tr>
			<tr>
				<td align="center" valign="top">
					<jsp:include page="../crumbs.jsp" />
					<table border="0" cellspacing="0" cellpadding="0" width="980"
						align="center">
						<tbody>
							<tr>
								<td height="12" colspan="3"><img
									src="//${cdnDomain}/cdn/spring/index_files/zw02.gif" width="980" height="12"></td>
							</tr>
							<tr>
								<td background="//${cdnDomain}/cdn/spring/index_files/zw-left.gif" width="20"></td>
								<td width="944">
									<table width="944" border="0" cellspacing="0" cellpadding="0">
										<tbody>
											<tr>
												<td>${errorMsg}<a target="_blank" href="/web/spring/index.htm"> <font color="#FF0000">返回首页<font color="#FF0000"></a></td>
											</tr>
											<tr style="height:300px"></tr>
											
											
											<tr>
												<td height="33" align="left" valign="middle">
												<li>${lang.preBlog[n18]}：
													<c:if test="${empty preBlog}">${lang.emptyResult[n18]}</c:if>
													<c:if test="${!empty preBlog}">
														<a class="LinkPrevArticle" target="_blank"
														href="/web/spring/blog/view.htm?id=${preBlog.id}"
														title="${preBlog.title}">${preBlog.title}</a>
													</c:if>
												</li>
												<br>
												<li>${lang.nextBlog[n18]}： 
													<c:if test="${empty nextBlog}">${lang.emptyResult[n18]}</c:if>
													<c:if test="${!empty nextBlog}">
														<a class="LinkPrevArticle"
														href="/web/spring/blog/view.htm?id=${nextBlog.id}" target="_blank"
														title="${nextBlog.title}">${nextBlog.title}</a>
													</c:if>
												</li></td>
											</tr>
											
										</tbody>
									</table>
								</td>
								<td background="//${cdnDomain}/cdn/spring/index_files/zw-right.gif" width="16"></td>
							</tr>
							<tr>
								<td height="10" colspan="3"><img
									src="//${cdnDomain}/cdn/spring/index_files/zw03.gif" width="980" height="10"></td>
							</tr>
							<tr>
								<td height="10" colspan="3"></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>

		<jsp:include page="../footer.jsp" />
	<script language="JavaScript" type="text/JavaScript"
	src="https://${cdnDomain}/cdn/spring/index_files/jquery.min.js"></script>
	<script>
	
	jQuery.noConflict();
	var accountId="${sessionScope.accountId}";
		var isLogin = accountId!=null && accountId.length>0;
	jQuery(function () {
	jQuery('.my-comment').on('click', function() {
				if (!isLogin) {
					alert("${lang.pleaseLoginFirst[n18]}");
				} else {
	jQuery('.sendCmt').toggle();
				}
			});
	jQuery(".button").on('click', function() {
				var info = jQuery(".input-txt").val();
				if (jQuery.trim(info) != '') {
					var objectid = "${blog.id}";
				jQuery.ajax({
					type: "GET",
					url: "/comment/add.json",
					data: {
						accountId: accountId,
						objectId: objectid,
						content: info,
						objectType:"BLOG"
					},
					dataType: "json",
					success: function(data){
						 var hc_list = "<div class='hc_list'>";
						hc_list += "<div class='hc_left'>";
						hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
						hc_list += "</div>";
						hc_list += "<div class='hc_right'>";
						hc_list += "<div class='hc_name'>" + data.data.comment.username + "</div>";
						hc_list += "<div class='hc_info'>" + data.data.comment.content + "</div>";
						hc_list += "<div class='hc_time'>" + data.data.comment.createDate + "</div>";
						hc_list += "</div></div>";
						jQuery(".history_comment").prepend(hc_list); 
					},
					error: function () {
						return;
					}
				});
	jQuery(".input-txt").val('');
			}
		});

	jQuery(".input-txt").change(function(){
			if(jQuery.trim(this.value) == "" ) {
	jQuery(".button").attr("disabled","disabled");
			}
			else {
	jQuery(".button").removeAttr("disabled");
			}
		});
		
		
		});
		
		
	</script>
</body>
</html>