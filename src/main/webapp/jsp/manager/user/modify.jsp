<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="main">
			<form id="addForm" action="" method="get">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h2>
				<i class="fa fa-indent red"></i><strong>账号信息</strong>
			</h2>
		</div>
		<div class="panel-body">
				<input type="hidden" name="account.id" value="${userDto.account.id}">
				<input type="hidden" name="teacher.id" value="${userDto.teacher.id}">
				<input type="hidden" name="account.username" value="${userDto.account.username}">
				<div class="form-group">
					<label for="nf-email">账号名</label>
					<label>${userDto.account.username}</label>
				</div>
				<div class="form-group">
					<label for="nf-email">密码</label> <input type="password"
						name="account.password" class="form-control" placeholder="密码" value="${userDto.account.password}">
				</div>

				
		</div>
		<div class="panel-heading">
			<h2>
				<i class="fa fa-indent red"></i><strong>教师信息</strong>
			</h2>
		</div>
		<div class="panel-body">
				<div class="form-group">
					<select id="select" name="departmentId" class="form-control" size="1">
				    	 <c:forEach var="vo" items="${selectVos}">  
				    	 	<option value="${vo.value}" <c:if test="${vo.value==userDto.teacher.departmentId}">selected="selected"</c:if>>${vo.name}</option>
				    	 </c:forEach>                   
				     </select>
				</div>
				<div class="form-group">
					<label for="nf-email">教师姓名</label> <input type="input"
						name="teacher.name" class="form-control" placeholder="教师姓名"
						value="${userDto.teacher.name}">
				</div>
				<div class="form-group">
					<label for="nf-password">性别</label> <label
						class="switch switch-success"> <input name="teacher.gender"
						type="checkbox" class="switch-input" <c:if test="${'on'==userDto.teacher.gender}">checked=""</c:if>> <span
						class="switch-label" data-on="男" data-off="女" ></span> <span
						class="switch-handle"></span>
					</label>
				</div>
				<div class="form-group">
					<label for="nf-email">家庭住址</label> <input type="input"
						name="teacher.address" class="form-control" placeholder="家庭住址" value="${userDto.teacher.address}">
				</div>
				<div class="form-group">
					<label for="nf-email">QQ号码</label> <input type="input"
						name="teacher.qq" class="form-control" placeholder="QQ号码" value="${userDto.teacher.qq}" >
				</div>
				<div class="form-group">
					<label for="nf-email">邮箱</label> <input type="input"
						name="teacher.email" class="form-control" placeholder="邮箱" value="${userDto.teacher.email}">
				</div>
				<div class="form-group">
					<label for="nf-email">手机号码</label> <input type="input"
						name="teacher.mobile" class="form-control" placeholder="手机号码" value="${userDto.teacher.mobile}">
				</div>
				<div class="form-group">
					<label for="nf-email">办公室电话</label> <input type="input"
						name="teacher.officetel" class="form-control" placeholder="办公室电话" value="${userDto.teacher.officetel}">
				</div>
				<div class="form-group">
					<label for="nf-email">身份证号</label> <input type="input"
						name="teacher.idcardno" class="form-control" placeholder="身份证号" value="${userDto.teacher.idcardno}">
				</div>
				<div class="form-group">
					<label for="nf-email">工号</label> <input type="input"
						name="teacher.jobnum" class="form-control" placeholder="工号" value="${userDto.teacher.jobnum}">
				</div>
				<div class="form-group">
					<label for="nf-email">职级</label> <input type="input"
						name="teacher.joblevel" class="form-control" placeholder="职级" value="${userDto.teacher.joblevel}">
				</div>
				<div class="form-group">
					<label for="nf-email">学科</label> <input type="input"
						name="teacher.discipline" class="form-control" placeholder="学科" value="${userDto.teacher.discipline}">
				</div>
				
				
		</div>
		<div class="panel-heading">
			<h2>
				<i class="fa fa-indent red"></i><strong>分配角色</strong>
			</h2>
		</div>
		<div class="panel-body">
				<c:forEach var="role" items="${roles}" >
			   		<p><input type="checkbox" name="roleId" value="${role.id}" <c:if test="${fn:contains(userDto.roleIds,role.id)}">checked="checked"</c:if>/>${role.roleName}</p>
    			</c:forEach> 
		</div>
		<div class="panel-footer">
			<button type="submit" class="btn btn-sm btn-info">
				<i class="fa fa-dot-circle-o"></i> 提交
			</button>
			<a target="_self" href="list.htm"><button
					id="reset" type="button" class="btn btn-sm btn-info">返回</button></a>
		</div>
	</div>
		</form>
</div>
<script src="/manager/assets/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/manager/js/user/user_toModify.js"></script>
