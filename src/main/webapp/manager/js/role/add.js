
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


$(document).ready(function() {

//	$.ajax({
//		type : "post",
//		url : "/manager/role/menu/init.json",
//		dataType : "json",
//		success : function(result) {
//			$.fn.zTree.init($("#treeDemo"), setting, result.children);
//			zTree = $.fn.zTree.getZTreeObj("treeDemo");
//			zTree.expandAll(true);
//		}
//	});
	
	$.fn.zTree.init($("#treeDemo"), setting, zData.children);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
	setCheck();
	$("#py").bind("change", setCheck);
	$("#sy").bind("change", setCheck);
	$("#pn").bind("change", setCheck);
	$("#sn").bind("change", setCheck);
});