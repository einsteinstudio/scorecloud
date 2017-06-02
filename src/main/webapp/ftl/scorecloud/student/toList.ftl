<#include "../templ/global.ftl"> 
<#include "../templ/fields/select_grade_id.ftl">
<@framework m2="listStudent">

<h3 class="page-title">学生查询</h3>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase">学生管理</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div >
								<#if Session.rightMap.STUDENT_ADD?exists>
								<button onclick="javascript:window.location.href='toAdd.htm'" id="sample_editable_1_new" class="btn sbold green">
									学生添加 <i class="fa fa-plus"></i>
								</button>
								</#if>
								<#if Session.rightMap.STUDENT_MODIFY?exists>
								<button  id="edit_grade_btn" class="btn sbold green">
									学生修改 <i class="fa fa-edit"></i>
								</button>
								</#if>
								<#if Session.rightMap.STUDENT_DELETE?exists>
								<button  id="delete_grade_btn" class="btn sbold green">
									学生删除 <i class="fa fa-remove"></i>
								</button>
								</#if>
							</div>
						</div>
						<div class="col-md-6">
							<div class="btn-group pull-right">
								<@select_grade_id selectedVal="${(gradeId)!}"/>
							</div>
						</div>
					</div>
				</div>
				<table
					class="table table-striped table-bordered table-hover table-checkable order-column"
					id="sample_1">
					<thead>
						<tr>
							<th><input type="checkbox" class="group-checkable"
								data-set="#sample_1 .checkboxes" /></th>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>民族</th>
							<th>年级</th>
							<th>班级</th>
							<th>成绩单</th>
						</tr>
					</thead>
					<tbody>
						<#list studentDtos as dto>
						<tr class="odd gradeX">
							<td><input objectId="${dto.id}" type="checkbox" class="checkboxes" value="1" />
							</td>
							<td>${dto.studentNo}</td>
							<td>${dto.studentNameSortKey}</td>
							<td>${(dto.sex)!}</td>
							<td>${(dto.national)!}</td>
							<td>${(dto.gradeName)!}</td>
							<td>${(dto.className)!}</td>
							<td><a href="/scorecloud/myscore/toList.htm?studentId=${dto.id}" target="_blank"
								class="btn btn-outline btn-circle red btn-sm blue"> <i
									class="fa fa-share"></i> 成绩单
							</a></td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
</@framework>
<script>
	var initTable1 = function() {
		var table = $('#sample_1');

		// begin first table
		var oSetting = $.extend(true, {
			 "order": [[ 1, "asc" ]],
			"aoColumnDefs": [
			                    { "sType": "sortKey", "aTargets": [2] },    //指定列号使用自定义排序
			                ],
		}, window.DT_defaultSetting);
		table.dataTable(oSetting);

		var tableWrapper = jQuery('#sample_1_wrapper');

		table.find('.group-checkable').change(function() {
			var set = jQuery(this).attr("data-set");
			var checked = jQuery(this).is(":checked");
			jQuery(set).each(function() {
				if (checked) {
					$(this).prop("checked", true);
					$(this).parents('tr').addClass("active");
				} else {
					$(this).prop("checked", false);
					$(this).parents('tr').removeClass("active");
				}
			});
			jQuery.uniform.update(set);
		});

		table.on('change', 'tbody tr .checkboxes', function() {
			$(this).parents('tr').toggleClass("active");
		});
	}

	var queryCheckIds = function() {
		var checkIds = "";
		$('tbody>tr span.checked').each(function() {
			if ($(this).find('input').attr('objectId') != '') {
				checkIds += $(this).find('input').attr('objectId') + "|";
			}
		})
		if (checkIds.length > 0)
			checkIds = checkIds.substring(0, checkIds.length - 1);
		return checkIds;
	}

	var queryCheckCnt = function() {
		var cnt = 0;
		var checkIds = "";
		$('tbody>tr span.checked').each(function() {
			if ($(this).find('input').attr('objectId') != '') {
				cnt++;
			}
		})
		return cnt;
	}

	$(document).ready(function() {
		initTable1();
		$("#edit_grade_btn").bind("click", function() {
			var cnt = queryCheckCnt();
			if (cnt == 0) {
				popGrowl('请选择一个待编辑的学生', 'danger');
			} else if (cnt > 1) {
				popGrowl('只能同时编辑一个学生', 'danger');
			} else {
				var checkIds = queryCheckIds();
				if (checkIds != '') {
					window.location.href = "toModify.htm?id=" + checkIds;
				} else {
					popGrowl('网络繁忙，请稍后再试', 'danger');
				}
			}
		});
		$("#delete_grade_btn").bind("click", function() {
			var cnt = queryCheckCnt();
			if (cnt == 0) {
				popGrowl('请选择至少选择一个待删除的学生', 'danger');
			} else {
				var checkIds = queryCheckIds();
				if (checkIds != '') {
					window.location.href = "delete.htm?id=" + checkIds;
				} else {
					popGrowl('网络繁忙，请稍后再试', 'danger');
				}
			}
		});
		
		$("select[name='gradeId']").bind("change",function(){
			console.log("gradeId changeed"+$("select[name='gradeId']").val());
			window.location.href="toList.htm?gradeId="+$("select[name='gradeId']").val();
		});
		
	});
</script>
