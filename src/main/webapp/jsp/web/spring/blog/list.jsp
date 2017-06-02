<%@ page language="java" contentType="text/html; charset=utf-8"
 pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>${schoolTitle}</title>


<script language="javascript" type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/prototype.js"></script>
<script language="javascript" type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/scriptaculous.js"></script>
<script type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/util.js"></script>
<script type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/effects.js"></script>
<script type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/dragdrop.js"></script>
<script type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/controls.js"></script>
<script language="javascript" type="text/javascript"
 src="//${cdnDomain}/cdn/spring/index_files/checklogin.js"></script>
<script language="JavaScript" type="text/JavaScript"
 src="//${cdnDomain}/cdn/spring/index_files/menu.js"></script>
<style type="text/css">
 .page-btn-cur {
  color:red;
 }
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
 /*第一种形式 第二种形式 更换显示样式*/
 function setTab(name, cursel, n) {
  for (i = 1; i <= n; i++) {
   var menu = document.getElementById(name + i);
   var con = document.getElementById("con_" + name + "_" + i);
   menu.className = i == cursel ? "hover" : "";
   con.style.display = i == cursel ? "block" : "none";
  }
 }
//-->
</script>
<link href="//${cdnDomain}/cdn/spring/index_files/bhsx.css"
 rel="stylesheet" type="text/css">
</head>
<body>
 <jsp:include page="../header.jsp" />

 <table width="1000" border="0" align="center" cellpadding="0"
  cellspacing="0" bgcolor="#FFFFFF">
  <tbody>
   <tr>
    <td height="14" colspan="2"></td>
   </tr>
   <tr>
    <td height="14" colspan="2"><jsp:include page="../crumbs.jsp" />
    </td>
   </tr>
   <tr>
    <td width="250" align="left" valign="top">
     <table width="250" border="0" cellspacing="0" cellpadding="0">
      <tbody>
       <tr>
        <td height="10"></td>
       </tr>
      </tbody>
     </table>
     <table width="250" border="0" cellspacing="0" cellpadding="0">
      <tbody>
       <tr>
        <td class="userbg">频道导航</td>
       </tr>
       <tr>
        <td align="center" class="daohangbg">
          <c:forEach var="menu" items="${leftMenus}">
            <table width="190" border="0" align="center" cellpadding="0"
             cellspacing="0">
             <tbody>
              <tr>
               <td width="10" valign="middle" class="linetext"><img
                src="//${cdnDomain}/cdn/spring/index_files/article_common4.gif"
                width="9" height="15">
               </td>
               <td width="180" height="24" align="left" valign="middle"
                class="linetext">【<a class=""
                href="/web/spring/blog/list.htm?blogType=${menu.name}"
                title="${menu.name}" target="_blank">${menu.name}</a>】
               </td>
              </tr>
             </tbody>
            </table>
          </c:forEach>
        </td>
       </tr>
      </tbody>
     </table>
     <table width="250" border="0" cellspacing="0" cellpadding="0">
      <tbody>
       <tr>
        <td>&nbsp;</td>
       </tr>
      </tbody>
     </table>
     <table width="250" border="0" cellspacing="0" cellpadding="0">
      <tbody>
       <tr>
        <td height="16"></td>
       </tr>
      </tbody>
     </table>
    </td>

    <td width="750" align="left" valign="top">
     <table width="750" border="0" cellspacing="0" cellpadding="0">
      <tbody>
       <tr>
        <td align="center" valign="top">
          <c:if test="${fn:length(blogTabs)==1}">
            <table border="0" cellpadding="0" cellspacing="0" width="736" align="center">
              <tbody>
               <c:forEach var="tab" items="${blogTabs}" varStatus="stat">
                <tr>
                 <td class="toolBar" height="47" valign="middle"
                  background="//${cdnDomain}/cdn/spring/index_files/article_bg.gif"
                  colspan="3" style="background-repeat: no-repeat;">
                  <table width="650" border="0"
                   align="center" cellpadding="0" cellspacing="0">
                   <tbody>
                    <tr>
                     <td width="504" height="20"></td>
                     <td width="146"></td>
                    </tr>
                    <tr>
                     <td style="font-size: 14px; text-align: left;"><a
                      href="javascript:void();"
                      title=""><b><font color="#FFFFFF">${tabNames[stat.index]}</font></b></a></td>
                     <td align="center"><a target="_blank"
                      href="/web/spring/blog/list.htm?blogType=${tabNames[stat.index]}"
                      title=""><font color="#FFFFFF">更多...</font></a></td>
                    </tr>
                   </tbody>
                  </table>
                  </td>
                </tr>
                <tr>
                 <td height="12" colspan="3" style="font-size:0;">
                 <img src="//${cdnDomain}/cdn/spring/index_files/article02.gif"width="736" height="12">
                 </td>
                </tr>

                <tr>
                 <td background="//${cdnDomain}/cdn/spring/index_files/a-left.gif"
                  width="20"></td>

                 <td width="700">
                  <table border="0" cellspacing="0" cellpadding="0"
                   width="700">
                   <tbody>
                     <tr>
                      <td valign="top">
                      <c:forEach var="blog" items="${tab}" varStatus="stat">
                       <table width="100%" border="0" cellspacing="0"
                        cellpadding="0">
                        <tbody>
                         <tr>
                          <td width="3%" valign="middle"
                           class="linetext"><img
                           src="//${cdnDomain}/cdn/spring/index_files/article_common5.gif"
                           width="9" height="15"></td>
                          <td width="79%" height="24" align="left"
                           valign="middle" class="linetext"><a
                           class=""
                           href="/web/spring/blog/view.htm?id=${blog.id}"
                           title="${blog.title}" target="_blank">${blog.title}</a></td>
                          <td width="18%" align="right" class="linetext"><fmt:formatDate
                            value="${blog.updateDate}"
                            pattern="yyyy-MM-dd" /></td>
                         </tr>
                        </tbody>
                       </table>
                      </c:forEach>                       
                      </td>
                     </tr>
                   </tbody>
                  </table>
                  <jsp:include page="pager.jsp" />
                 </td>

                 <td
                  background="//${cdnDomain}/cdn/spring/index_files/a-right.gif"
                  width="16"></td>
                </tr>

                <tr>
                 <td height="10" colspan="3"><img
                  src="//${cdnDomain}/cdn/spring/index_files/article03.gif"
                  width="736" height="10"></td>
                </tr>
               </c:forEach>                
              </tbody>
            </table>
          </c:if>

          <c:if test="${fn:length(blogTabs)>1}">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="word-break: break-all; Width: fixed">
              <tbody>
               <c:forEach var="tab" items="${blogTabs}" varStatus="stat">
                <tr>
                 <td valign="top" width="100%"><table border="0"
                   cellspacing="0" cellpadding="0" width="736"
                   align="center">
                   <tbody>
                    <tr>
                     <td class="toolBar" height="47" valign="middle"
                      background="//${cdnDomain}/cdn/spring/index_files/article_bg.gif"
                      colspan="3" style="background-repeat: no-repeat;">
                      <table width="650" border="0"
                       align="center" cellpadding="0" cellspacing="0">
                       <tbody>
                        <tr>
                         <td width="504" height="20"></td>
                         <td width="146"></td>
                        </tr>
                        <tr>
                         <td style="font-size: 14px; text-align: left;"><a
                          href="javascript:void();"
                          title=""><b><font color="#FFFFFF">${tabNames[stat.index]}</font></b></a></td>
                         <td align="center"><a target="_blank"
                          href="/web/spring/blog/list.htm?blogType=${tabNames[stat.index]}"
                          title=""><font color="#FFFFFF">更多...</font></a></td>
                        </tr>
                       </tbody>
                      </table>
                      </td>
                    </tr>
                    <tr>
                     <td height="12" colspan="3" style="font-size:0;">
                     <img src="//${cdnDomain}/cdn/spring/index_files/article02.gif"width="736" height="12">
                     </td>
                    </tr>
                    <tr>
                     <td
                      background="//${cdnDomain}/cdn/spring/index_files/a-left.gif"
                      width="20"></td>
                     <td width="700">
                      <table border="0" cellspacing="0" cellpadding="0"
                       width="700">
                       <tbody>
                         <tr>
                          <td valign="top">
                          <c:forEach var="blog" items="${tab}" varStatus="stat">
                           <table width="100%" border="0" cellspacing="0"
                            cellpadding="0">
                            <tbody>
                             <tr>
                              <td width="3%" valign="middle"
                               class="linetext"><img
                               src="//${cdnDomain}/cdn/spring/index_files/article_common5.gif"
                               width="9" height="15"></td>
                              <td width="79%" height="24" align="left"
                               valign="middle" class="linetext"><a
                               class=""
                               href="/web/spring/blog/view.htm?id=${blog.id}"
                               title="${blog.title}" target="_blank">${blog.title}</a></td>
                              <td width="18%" align="right" class="linetext"><fmt:formatDate
                                value="${blog.updateDate}"
                                pattern="yyyy-MM-dd" /></td>
                             </tr>
                            </tbody>
                           </table>
                          </c:forEach>                       
                          </td>
                         </tr>
                       </tbody>
                      </table>
                     </td>
                     <td
                      background="//${cdnDomain}/cdn/spring/index_files/a-right.gif"
                      width="16"></td>
                    </tr>
                    <tr>
                     <td height="10" colspan="3"><img
                      src="//${cdnDomain}/cdn/spring/index_files/article03.gif"
                      width="736" height="10"></td>
                    </tr>
                   </tbody>
                  </table></td>
                </tr>
               </c:forEach>
              </tbody>
            </table>
          </c:if>

        </td>
       </tr>
      </tbody>
     </table> 
     <jsp:include page="../searchBar.jsp" />
    </td>
   </tr>
  </tbody>
 </table>
 <jsp:include page="../footer.jsp" />
</body>
</html>