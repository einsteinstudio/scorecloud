//当前选中的treeNode
curNode= new Object();

/**
 * 初始化表单
 * @param id
 */
function initMenuForm(treeNode)
{
	$('input[name="id"]').val(treeNode.id);
	$('input[name="name"]').val(treeNode.name);
	$('input[name="duty"]').val(treeNode.duty);
	$('input[name="phone"]').val(treeNode.phone);
	$('input[name="weight"]').val(treeNode.weight);
	$('input[name="parentId"]').val(treeNode.parentId);
}

/**
 * 初始化空白的添加表单
 */
function initAddMenuForm()
{
	$('input[name="id"]').val("");
	$('input[name="name"]').val("");
	$('input[name="duty"]').val("");
	$('input[name="phone"]').val("");
	$('input[name="weight"]').val("");
	$('input[name="parentId"]').val($('input[name="id"]')); //当前节点ID变为新增节点的父节点
}

var setting = {
	check : {
		enable : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		onClick:function(event, treeId, treeNode){
			curNode=treeNode;
			initMenuForm(treeNode);
		}
	}
};

$(function() {
	//事件初始化
	$("addBtn").bind("click",function(){
		initAddMenuForm();
		
	});
	$("delBtn").bind("click",function(){
		zTree.removeNode(curNode,true);
		showInfo("success","当前部门删除成功");
	});
	
	
	$.fn.zTree.init($("#treeDemo"), setting, zData);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
	
	$('#addForm').validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : "部门名称不能为空"
			}
		},
		showErrors : function(errorMap, errorList) {
			var element = this.currentElements;
			if (element.next().hasClass('popover')) {
				element.popover('destroy');
			}
			this.defaultShowErrors();
		},
		submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
			$(form).ajaxSubmit({
				type : "post",
				url : "add.json",
				success : function(data) {
					if (data.result == "success") {
						showInfo("success","部门保存成功");
						var nodes = zTree.getSelectedNodes();
						if (nodes.length>0) {
							nodes[0].name=data.data.department.name;
							nodes[0].weight=data.data.department.weight;
							nodes[0].phone=data.data.department.phone;
							nodes[0].duty=data.data.department.duty;
							zTree.updateNode(nodes[0]);
						}
					} else {
						alert(data.msg);
					}
				}
			});
		},
	});
	
});

