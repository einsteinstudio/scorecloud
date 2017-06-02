<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
	<meta name="baidu_union_verify" content="11b893f7f2790018538c635fb38896f2">
    <link type="text/css" rel="stylesheet" href="../../plugins/foundation/css/foundation.css">
    <script src="../../plugins/jquery/jquery-1.8.3.js"></script>
    <style type="text/css">
        .row {
            max-width: 80rem;
        }
        nav {
            position:fixed;
            left:100px;
            top:100px;
        }
        .header {
            position:fixed;
        }
        .right{
            padding-top:100px;
        }
    </style>
<body>
<div class="header">
<h2>小工具</h2>
</div>
    <div class="row">
        <div class="large-3 columns">
            <nav>
                <ul class="side-nav">
                <li><a href="list.htm">MOCK接口列表</a></li>
                <li><a href="to_add.htm">新增接口</a></li>
                <li><a href="<%=basePath%>/mock/fabu_back.htm">发布后台</a></li>

                </ul>
            </nav>
        </div>
        <div class="large-9 columns right">
            <table>
                <thead><td>标题</td><td>URL</td><td>类型</td><td>分组</td><td>创建者</td><td>操作</td></thead>
                <tbody>
                    <c:forEach var="mockUrl" varStatus="stat" items="${mockUrls}">
                        <tr>
                            <td>${mockUrl.title}</td>
                            <td>${mockUrl.url}</td>
                            <td>${mockUrl.type}</td>
                            <td>${mockUrl.groupName}</td>
                            <td>${mockUrl.accountId}</td>
                            <td><a target="_blank" href='<%=basePath %>${mockUrl.demoUrl}'>演示</a>&nbsp|&nbsp<a target="_blank" href='<%=basePath %>/mock/to_modify?id=${mockUrl.id}'>修改</a>&nbsp|&nbsp<a  class="delete-btn" mockId="${mockUrl.id}">删除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="footer">
        powered by jintianfan@gmail.com
    </div>
    <div style="display:none">
    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256966849'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1256966849' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
    <script type="text/javascript">
        $(function(){
           $(".delete-btn").bind("click",function(){
                var id=$(this).attr("mockId");
                $.ajax({ 
                    type: "post", 
                    url: "/mock/delete?id="+id, 
                    dataType: "json", 
                    success: function (data) { 
                         location.reload();   
                    }, 
                    error: function (XMLHttpRequest, textStatus, errorThrown) { 
                            alert(errorThrown); 
                    } 
                });
           }); 
        });
    </script>
</body>
</html>
