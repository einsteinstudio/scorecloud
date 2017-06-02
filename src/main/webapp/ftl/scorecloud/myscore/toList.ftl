<#include "../templ/global.ftl"> <@framework m2="listMyScore">

<h3 class="page-title">我的成绩单</h3>

<div class="portlet-body">
	<div class="mt-element-list">
		<div class="mt-list-head list-todo opt-2 font-white bg-dark">
			<div class="list-head-title-container">
				<h3 class="list-title">我的成绩单（学号：${(studentDto.studentNo)!} &nbsp;姓名：${(studentDto.studentName)}）</h3>

			</div>

		</div>
		<div class="mt-list-container list-todo opt-2">
			<div class="list-todo-line bg-red"></div>
			<ul>
				<#list myScoreVo.myExamVos as myExamVo>
				<li class="mt-list-item">
					<div class="list-todo-icon bg-white font-blue-steel">
						<i class="fa fa-database"></i>
					</div>
					<div class="list-todo-item item-1">
						<a class="list-toggle-container font-white" data-toggle="collapse"
							href="#task-1-2" aria-expanded="false">
							<div class="list-toggle done uppercase bg-blue-steel">
								<div class="list-toggle-title bold">${(myExamVo.examName)!}</div>
								<div
									class="badge badge-default pull-right bg-white font-dark bold"></div>
							</div>
						</a>
						<div class="task-list panel-collapse collapse in" id="task-1-2">
							<div class="portlet-body">
								<div class="table-scrollable">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>科目</th>
												<th>成绩</th>
												<th>班级排名</th>
												<th>年级排名</th>
											</tr>
										</thead>
										<tbody>
											<#list myExamVo.subjectScoreItems as item>
											<tr>
												<td>${item_index+1}</td>
												<td>${item.subjectName}</td>
												<td>${item.subjectScore}</td>
												<td>${item.classRank}</td>
												<td>${item.gradeRank}</td>
											</tr>
											</#list>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</li> </#list>
			</ul>
		</div>
	</div>
</div>


</@framework>
<script>
	var initTable1 = function() {

		var table = $('#sample_1');

		// begin first table
		var oSetting = $.extend(true, {}, window.DT_defaultSetting);
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
				popGrowl('请选择一个待编辑的年级', 'danger');
			} else if (cnt > 1) {
				popGrowl('只能同时编辑一个年级', 'danger');
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
				popGrowl('请选择至少选择一个待删除的年级', 'danger');
			} else {
				var checkIds = queryCheckIds();
				if (checkIds != '') {
					window.location.href = "delete.htm?id=" + checkIds;
				} else {
					popGrowl('网络繁忙，请稍后再试', 'danger');
				}
			}
		})

		$("#upgrade_grade_btn").bind("click", function() {
			var cnt = queryCheckCnt();
			if (cnt == 0) {
				popGrowl('请选择至少选择一个待删除的年级', 'danger');
			} else {
				var checkIds = queryCheckIds();
				if (checkIds != '') {
					window.location.href = "upgrade.htm?id=" + checkIds;
				} else {
					popGrowl('网络繁忙，请稍后再试', 'danger');
				}
			}
		})

	});
</script>
