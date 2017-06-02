$(function(){
	initMenuForm($('input[name="id"]').val());
	$('#reset').bind('click',function(){
		initMenuForm($('input[name="id"]').val());
	});
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
                 success: function(data){
                	 //showInfo("info","保存成功");
                	 window.location.href="/manager/menu/list.htm?pId="+$('input[name=parentId]').val();
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
	 		$('input[type="checkbox"][name="needLogin"]').attr("checked",result.data.menu.needLogin); 
	 		$('input[type="checkbox"][name="canComment"]').attr("checked",result.data.menu.canComment); 

	 	}
	});
}
