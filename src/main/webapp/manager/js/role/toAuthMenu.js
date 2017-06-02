var setting = {
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true
		}
	}
};

var code;

function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), py = $("#py").attr(
			"checked") ? "p" : "", sy = $("#sy").attr("checked") ? "s" : "", pn = $(
			"#pn").attr("checked") ? "p" : "", sn = $("#sn").attr("checked") ? "s"
			: "", type = {
		"Y" : py + sy,
		"N" : pn + sn
	};

}

$(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zData.children);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
	setCheck();
	$("#py").bind("change", setCheck);
	$("#sy").bind("change", setCheck);
	$("#pn").bind("change", setCheck);
	$("#sn").bind("change", setCheck);

	/**
	 * Menu表单校验
	 */
	$('#authMenuForm')
			.validate(
					{

						submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
							// zTree = $.fn.zTree.getZTreeObj("treeDemo");
							var nodes = zTree.getCheckedNodes(true);
							String
							menuIds = "";
							for ( var n in nodes) {
								menuIds += nodes[n].id + ",";
							}
							menuIds = menuIds.substring(0, menuIds.length - 1);
							$(form)
									.ajaxSubmit(
											{
												type : "post",
												url : "/manager/role/authMenu.json",
												data:{
													"menuIds":menuIds,
													"roleId":roleId
												},
												success : function(data) {
													// showInfo("info","保存成功");
													window.location.href = "/manager/role/list.htm";
												}
											});
						},
					});
});

