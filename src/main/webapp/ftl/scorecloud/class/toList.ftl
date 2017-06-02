<#include "../templ/global.ftl"> <@framework m2="listClass">

<h3 class="page-title">班级数据</h3>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase">班级管理</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div>
								<#if Session.rightMap.CLASS_ADD?exists>
								<button onclick="javascript:window.location.href='toAdd.htm'"
									id="sample_editable_1_new" class="btn sbold green">
									班级添加 <i class="fa fa-plus"></i>
								</button>
								</#if>
								<#if Session.rightMap.CLASS_MODIFY?exists>
								<button id="edit_grade_btn" class="btn sbold green">
									班级修改 <i class="fa fa-edit"></i>
								</button>
								</#if>
								<#if Session.rightMap.CLASS_DELETE?exists>
								<button id="delete_grade_btn" class="btn sbold green">
									班级删除 <i class="fa fa-remove"></i>
								</button>
								</#if>
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
							<th>班级名称</th>
							<th>所在年级</th>
							<th>班主任</th>
							<th>权重</th>
						</tr>
					</thead>
					<tbody>
						<#list classDtos as classDto>
						<tr class="odd gradeX">
							<td><input objectId="${classDto.id}" type="checkbox"
								class="checkboxes" value="1" /></td>
							<td>${classDto.className}</td>
							<td>${classDto.gradeName}</td>
							<td>${(classDto.majorTracherName)!}</td>
							<td>${classDto.weight}</td>
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
		var oSetting=$.extend(true,{},window.DT_defaultSetting);
		oSetting = $.extend(true,oSetting, {
			"drawCallback" : function(settings) {
				var api = this.api();
				var rows = api.rows({
					page : 'current'
				}).nodes();
				var last = null;

				api.column(2, {
					page : 'current'
				}).data().each(
						function(group, i) {
							if (last !== group) {
								$(rows).eq(i).before(
										'<tr class="group"><td colspan="5">'
												+ group + '</td></tr>');

								last = group;
							}
						});
			}
		});
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
				popGrowl('请选择一个待编辑的班级', 'danger');
			} else if (cnt > 1) {
				popGrowl('只能同时编辑一个班级', 'danger');
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
				popGrowl('请选择至少选择一个待删除的班级', 'danger');
			} else {
				var checkIds = queryCheckIds();
				if (checkIds != '') {
					window.location.href = "delete.htm?id=" + checkIds;
				} else {
					popGrowl('网络繁忙，请稍后再试', 'danger');
				}
			}
		})
	});
</script>
