<#assign metronicPath="http://localhost:8080/app">
<#--<#assign metronicPath="/app">-->
<#assign metronicPath="http://tool.goldskyer.com/metronic/theme">
<#if title??> 
<#else>
	<#assign title="下村小学成绩系统">
</#if>
<#assign titleDesc="深圳下村小学，用爱心托起明天的太阳，用太阳的光辉照亮孩子的心房，让我们的孩子快乐的走向阳光，走向明天……">

<#assign company="2016 © Coricher Company.">


<#assign sidebarSearch=false>  <#--搜索框开关-->

<#--header开关-->
<#assign notificationSwitch=false>  <#--通知开关-->
<#assign inboxSwitch=false>  <#--站内信开关-->
<#assign todoSwitch=false>  <#--待办项开关-->
<#assign quickSiderbarSwitch=false>  <#--弹出滑动框开关-->
<#assign userLoginSwitch=true>  <#--用户下拉列表开关-->


<#--Macro宏定义-->
<#macro framework m2="">
<#include "begin_of_page.ftl">
<#include "page_head.ftl">	
	<div style="display:none" id="m2" value="${m2}"></div>
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <#include "header.ftl">
        
        
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <#include "sidebar.ftl">
            <#--<#include "content.ftl">-->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content" style="min-height:1318px">
            		<#nested>
            	</div>
                <!-- END CONTENT BODY -->
            </div>
            <#include "quick_sidebar.ftl">
        </div>
        <!-- END CONTAINER -->
        <#include "footer.ftl">
        <#include "end_of_page.ftl">
</#macro>