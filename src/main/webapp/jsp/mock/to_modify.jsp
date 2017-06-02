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
                </ul>
            </nav>
        </div>
        <div class="large-9 columns right">
            <form action="/mock/modify.htm" method="post">
                <input type="hidden" name="id" value="${mockUrl.id}"/>
                <label>接口描述<input type="text" name="title" value="${mockUrl.title}"></input></label>
                <label>接口地址<input type="text" name="url" value="${mockUrl.url}"></input></label>
                <label>接口类型<select name="type">
                    <option  <c:if test='${mockUrl.type=="JSON"}'>selected="true"</c:if> value="JSON">JSON</option>
                    <option <c:if test='${mockUrl.type=="HTML"}'>selected="true"</c:if> value="HTML">HTML</option>
                </select></label>
                <label>接口返回<textarea  rows="20" name="content" >${mockUrl.content}</textarea></label>
                <label>接口分组<input type="text" name="groupName" value="${mockUrl.groupName}"></input></label>
                <label>创建者<input placeholder="填写你的邮箱" type="text" name="accountId" value="${mockUrl.accountId}"></input></label>
                 <label>来源<input placeholder="" type="text" name="sourceUrl" value="${mockUrl.sourceUrl}"></input></label>
                <input type="submit" class="button small" value="保存">
            </form>
        </div>
    </div>
    <div class="footer">
        powered by jintianfan@gmail.com
    </div>
    <div style="display:none">
    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256966849'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1256966849' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>
