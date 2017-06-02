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
                 url:"/manager/menu/add.json",
                 success: function(data){
                	 //showInfo("info","保存成功");
                	 window.location.href="/manager/menu/list.htm?pId="+$('input[name=parentId]').val();
                 } 
             }); 
         }, 
	});
});
