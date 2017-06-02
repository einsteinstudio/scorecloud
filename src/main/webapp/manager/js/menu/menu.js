$(function(){
	/**
	 * Menu表单校验
	 */
	$('#menuForm').validate({
		 rules:{
	         name:{
	             required:true
	         }                 
	     },
	     messages:{
	         name:{
	             required:"菜单名不能为空"
	         }                                  
	     },
	     submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
             $(form).ajaxSubmit({
            	 type:"post",
                 url:"/manager/menu/modify.json",
                 success: function(){
                	 showInfo("info","保存成功");
                 } 
             }); 
         }, 
	});
});

/**
*初始化菜单编辑页面
*/
function initMenuForm(id)
{
	if(id=='' || id==null)
	{
		return;
	}
	$.ajax({
		url:"/menu/item.json?id="+id,
	 	success:function(result){
	 		$('input[name="id"]').val(result.data.menu.id);
	 		$('input[name="name"]').val(result.data.menu.name);
	 		$('input[name="link"]').val(result.data.menu.link);
	 		$('input[name="weight"]').val(result.data.menu.weight);
	 		$('input[type="radio"][name="type"][value="'+result.data.menu.type+'"]').attr("checked",true);  ;
	 		$('input[type="radio"][name="mediaType"][value="'+result.data.menu.mediaType+'"]').attr("checked",true);
	 		$('input[type="checkbox"][name="show"]').attr("checked",!result.data.menu.hide); 
	 	}
	});
}
$('#reset').bind('click',function(){
	initMenuForm($('input[name="id"]').val());
});

var setting = {
	view: {
		dblClickExpand: false
	},
	check: {
		enable: false
	},
	edit: {
		drag: {
			autoExpandTrigger: true,
			prev: dropPrev,
			inner: dropInner,
			next: dropNext
		},
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'id'
		}
	},
	callback: {
		onClick:onClick,
		onRightClick: OnRightClick,
		beforeDrag: beforeDrag,
		beforeDrop: beforeDrop,
		beforeDragOpen: beforeDragOpen,
		onDrag: onDrag,
		onDrop: onDrop,
		onExpand: onExpand
	}
	
};

var zNodes = [];

function onClick(event, treeId, treeNode)
{
	initMenuForm(treeNode.id);
}

function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}
var addCount = 1;
function addTreeNode() {
	hideRMenu();
	var newNode = { name:"newNode " + (addCount++)};
	if (zTree.getSelectedNodes()[0]) {
		newNode.checked = zTree.getSelectedNodes()[0].checked;
		zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
	} else {
		zTree.addNodes(null, newNode);
	}
}
function removeTreeNode() {
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		if (nodes[0].children && nodes[0].children.length > 0) {
			var msg = "If you delete this node will be deleted along with sub-nodes. \n\nPlease confirm!";
			if (confirm(msg)==true){
				zTree.removeNode(nodes[0]);
			}
		} else {
			zTree.removeNode(nodes[0]);
		}
	}
}
function editTreeNode() {
	var nodes = zTree.getSelectedNodes();
	if (nodes && nodes.length>0) {
		zTree.editName(nodes[0]);
	}
	hideRMenu();
}
function resetTree() {
	hideRMenu();
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}


function dropPrev(treeId, nodes, targetNode) {
	var pNode = targetNode.getParentNode();
	if (pNode && pNode.dropInner === false) {
		return false;
	} else {
		for (var i=0,l=curDragNodes.length; i<l; i++) {
			var curPNode = curDragNodes[i].getParentNode();
			if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
				return false;
			}
		}
	}
	return true;
}
function dropInner(treeId, nodes, targetNode) {
	if (targetNode && targetNode.dropInner === false) {
		return false;
	} else {
		for (var i=0,l=curDragNodes.length; i<l; i++) {
			if (!targetNode && curDragNodes[i].dropRoot === false) {
				return false;
			} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
				return false;
			}
		}
	}
	return true;
}
function dropNext(treeId, nodes, targetNode) {
	var pNode = targetNode.getParentNode();
	if (pNode && pNode.dropInner === false) {
		return false;
	} else {
		for (var i=0,l=curDragNodes.length; i<l; i++) {
			var curPNode = curDragNodes[i].getParentNode();
			if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
				return false;
			}
		}
	}
	return true;
}

var log, className = "dark", curDragNodes, autoExpandNode;
function beforeDrag(treeId, treeNodes) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			curDragNodes = null;
			return false;
		} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
			curDragNodes = null;
			return false;
		}
	}
	curDragNodes = treeNodes;
	return true;
}
function beforeDragOpen(treeId, treeNode) {
	autoExpandNode = treeNode;
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
	showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
	return true;
}
function onDrag(event, treeId, treeNodes) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
}
function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
	showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))
}
function onExpand(event, treeId, treeNode) {
	if (treeNode === autoExpandNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
	}
}

function showLog(str) {
	if (!log) log = $("#log");
	log.append("<li class='"+className+"'>"+str+"</li>");
	if(log.children("li").length > 8) {
		log.get(0).removeChild(log.children("li")[0]);
	}
}
function getTime() {
	var now= new Date(),
	h=now.getHours(),
	m=now.getMinutes(),
	s=now.getSeconds(),
	ms=now.getMilliseconds();
	return (h+":"+m+":"+s+ " " +ms);
}

var zTree, rMenu;
$(document).ready(function(){
	jQuery.ajax({
		type: "post",
		url: "/manager/menu/init.json",
		dataType: "json",
		success: function(result){
			zNodes.push($.parseJSON(result.data.menuData));
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		}
		,
		error: function (XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		}
	});
});

function doSave() {
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var jsonData = JSON.stringify(zTree.getNodes()[0]);
	jQuery.ajax({
		type: "post",
		url: "<%=basePath%>menu/save.htm",
		data:{
			"menuData": jsonData
		},
		dataType: "json",
		success: function(result){
			if(result.data.success) {
				jQuery.ajax({
					type: "post",
					url: "<%=basePath%>menu/init.json",
					dataType: "json",
					success: function(result){
						alert("修改菜单项成功！");
						zNodes.push($.parseJSON(result.data.menuData));
						$.fn.zTree.init(zTree, setting, zNodes);
					},
					error: function (XMLHttpRequest, textStatus, errorThrown){
						alert(errorThrown);
					}
				});
			};
		}
		,
		error: function (XMLHttpRequest, textStatus, errorThrown){
			alert(errorThrown);
		}
	});
	return false;
}
