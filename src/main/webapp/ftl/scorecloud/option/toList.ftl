<#include "../templ/global.ftl"> <@framework m2="listOption">

<h3 class="page-title">选项数据</h3>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet light bordered">
			<div class="portlet-title">
				<div class="caption font-dark">
					<i class="icon-settings font-dark"></i> <span
						class="caption-subject bold uppercase">系统选项</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div >
								<button onclick="javascript:window.location.href='toAdd.htm'" id="sample_editable_1_new" class="btn sbold green">
									选项添加 <i class="fa fa-plus"></i>
								</button>
								<button  id="edit_grade_btn" class="btn sbold green">
									选项修改 <i class="fa fa-edit"></i>
								</button>
								<button  id="delete_grade_btn" class="btn sbold green">
									选项删除 <i class="fa fa-remove"></i>
								</button>
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
							<th>配置名称</th>
							<th>内部代码</th>
							<th>显示提示</th>
							<th>提示内容</th>
							<th>权重</th>
						</tr>
					</thead>
					<tbody>
						<#list optionDtos as optionDto>
						<tr class="odd gradeX">
							<td><input objectId="${optionDto.id}" type="checkbox" class="checkboxes" value="1" />
							</td>
							<td>${optionDto.optionName}</td>
							<td>${optionDto.innerCode}</td>
							<#if optionDto.showTip >
								<td>显示</td>
							<#else>
								<td>不显示</td>
							</#if>
							<td>${optionDto.tip}</td>
							<td>${optionDto.weight}</td>
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
<script src="${metronicPath}/assets/global/scripts/datatable.js"
	type="text/javascript"></script> 
<script src="${metronicPath}/assets/global/plugins/datatables/datatables.min.js"
	type="text/javascript"></script>
<script
	src="${metronicPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
	type="text/javascript"></script>

<script>
	var initTable1 = function() {

		var table = $('#sample_1');

		// begin first table
		var oSetting=$.extend(true,{},window.DT_defaultSetting);
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
	
	var queryCheckIds =function(){
			var checkIds="";
			$('tbody>tr span.checked').each(function(){
				if($(this).find('input').attr('objectId')!=''){
					checkIds+=$(this).find('input').attr('objectId')+"|";
				}
			})
			if(checkIds.length>0)
				checkIds=checkIds.substring(0,checkIds.length-1);
			return checkIds;
	}
	
	var queryCheckCnt=function(){
		var cnt=0;
			var checkIds="";
			$('tbody>tr span.checked').each(function(){
				if($(this).find('input').attr('objectId')!=''){
					cnt++;
				}
			})
			return cnt;
	}
	
	$(document).ready(function() {
		initTable1();
		$("#edit_grade_btn").bind("click",function(){
			var cnt=queryCheckCnt();
			if(cnt==0){
				popGrowl('请选择一个待编辑的选项','danger');
			}else if(cnt>1){
				popGrowl('只能同时编辑一个选项','danger');
			}else{
				var checkIds=queryCheckIds();
				if(checkIds!=''){
					window.location.href="toModify.htm?id="+checkIds;
				}else{
					popGrowl('网络繁忙，请稍后再试','danger');
				}
			}
		});
		$("#delete_grade_btn").bind("click",function(){
			var cnt=queryCheckCnt();
			if(cnt==0){
				popGrowl('请选择至少选择一个待删除的选项','danger');
			}else{
				var checkIds=queryCheckIds();
				if(checkIds!=''){
					window.location.href="delete.htm?id="+checkIds;
				}else{
					popGrowl('网络繁忙，请稍后再试','danger');
				}
			}
		})
	});
</script>
