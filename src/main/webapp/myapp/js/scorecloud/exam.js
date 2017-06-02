/**
 * 正常的表格更新流程：
 * 1、点击按钮，触发操作；
 * 2、发送请求
 * 3、请求返回成功，刷新页面。所以刷新操作在ajaxAdd的success回调函数中执行
 * 
 * TODO：保存的时候防止重复提交
 */
var TableDatatablesEditable = function () {

    var handleTable = function () {
    	//成功之后刷新表格
    	function ajaxSuccess(oTable, nRow,aData)
    	{
    		oTable.fnUpdate(aData[0], nRow, 0, false);
            oTable.fnUpdate(aData[1], nRow, 1, false);
            oTable.fnUpdate(aData[2], nRow, 2, false);
            oTable.fnUpdate(aData[3], nRow, 3, false);
            oTable.fnUpdate(aData[4], nRow, 4, false);
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 5, false);
            oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 6, false);
            oTable.fnDraw();
            nEditing = null;//编辑成功后，清除该行的编辑状态数据
    	}
        
    	
    	/**添加或者修改,ajax先发送，然后在更新表单*/
    	function ajaxAdd(oTable, nRow,aData)
    	{
    		var idVal=$('td.rowId', nRow).attr('rowId'); //比较好的处理方式
    		$.ajax({
    			url:"/scorecloud/exam/subject/addOrModify.json",
    			data:{
    				subjectName:aData[0],
    				weight:aData[1],
    				id:idVal,
    				hegeScore:aData[2],
    				examId:$('input[name="id"]').val(),
    				youliangScore:aData[3],
    				excellantScore:aData[4],
    			},
    			success:function(ret)
    			{
    				//更新行ID
    				if(ret.result=='success'){
        				$('td.rowId', nRow).attr('rowId',ret.data.rowId);
        				ajaxSuccess(oTable, nRow,aData);
        	    		popGrowl('保存成功','info');
    				}else{
    					popGrowl(ret.msg,'danger');
    				}
    			}
    		});
    	}
    	
    	function ajaxDelete(oTable, nRow)
    	{
    		var idVal=$('td.rowId', nRow).attr('rowId'); //比较好的处理方式
    		$.ajax({
    			url:"/scorecloud/exam/subject/delete.json",
    			data:{
    				id:idVal,
    			},
    			success:function(ret)
    			{
    	    		popGrowl('删除选项值成功','info')
    			}
    		});
    	}
    	
        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);

            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                oTable.fnUpdate(aData[i], nRow, i, false);
            }

            oTable.fnDraw();
        }

        //编辑行
        function editRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            
            
            var jqTds = $('>td', nRow);
            $('div.subject-temp>select').find("option[value='"+aData[0]+"']").attr("selected",true);
            jqTds[0].innerHTML = $('div.subject-temp').html();
            jqTds[1].innerHTML = '<input type="number"  class=" form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<input type="number"  class=" form-control input-small" value="' + aData[2] + '">';
            jqTds[3].innerHTML = '<input type="number" class="form-control input-small" value="' + aData[3] + '">';            
            jqTds[4].innerHTML = '<input type="number" class="form-control input-small" value="' + aData[4] + '">';            
            	//'<input type="text" class="form-control input-small" value="' + aData[2] + '">';
            jqTds[5].innerHTML = '<a class="edit" href="">保存</a>';
            jqTds[6].innerHTML = '<a class="cancel" href="">取消</a>';
        }

        function saveRow(oTable, nRow) {
        	//初始化待修改的数据
        	var jqInputs = $('input', nRow);
            var jqSelects=$('select[name="subject"]', nRow);
        	var aData=new Array();
        	aData[0]=jqSelects.find("option:selected").text(); //第一列
        	aData[1]=jqInputs[0].value; //第二列
        	aData[2]=jqInputs[1].value;//第三列
        	aData[3]=jqInputs[2].value;//第四列
        	aData[4]=jqInputs[3].value;//第四列
            ajaxAdd(oTable, nRow,aData);//后台数据更新
        }

        function cancelEditRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[0].value, nRow, 3, false);
            oTable.fnUpdate(jqInputs[0].value, nRow, 4, false);
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 5, false);
            oTable.fnDraw();
        }

        var table = $('#sample_editable_1');

        /**
         * 设置按照第二列升序列排列
         */
		var oSetting=$.extend(true,oSetting,window.DT_defaultSetting);
        oSetting=$.extend(oSetting,{
        	"order": [
                      [1, "asc"]
                  ] 
        }); 
        var oTable = table.dataTable(oSetting);

        var tableWrapper = $("#sample_editable_1_wrapper");

        var nEditing = null;
        var nNew = false;

        //添加行按钮
        $('#sample_editable_1_new').click(function (e) {
            e.preventDefault();

            if (nNew && nEditing) {
                if (confirm("之前的行还没有保存. 是否想要保存?")) {
                    saveRow(oTable, nEditing); // save
                    //$(nEditing).find("td:first").html("Untitled"); 没用的
                    nEditing = null;
                    nNew = false;

                } else {
                    oTable.fnDeleteRow(nEditing); // cancel
                    nEditing = null;
                    nNew = false;
                    
                    return;
                }
            }

            var aiNew = oTable.fnAddData(['', '0','60', '80', '90','','']); //有多少列
            var nRow = oTable.fnGetNodes(aiNew[0]);
            editRow(oTable, nRow);
            nEditing = nRow;
            nNew = true;
        });
        
        table.on('click', '.delete', function (e) {
            e.preventDefault();

     	    nRow = $(this).parents('tr')[0];
            bootbox.confirm("确定要删除这行 ?", function(result) {
            	console.log("bootbox result:"+result)
               if(result==false){
            	   return;
               }else{
                   oTable.fnDeleteRow(nRow);
                   ajaxDelete(oTable, nRow)
               }
             }); 

        });

        table.on('click', '.cancel', function (e) {
            e.preventDefault();
            if (nNew) {
                oTable.fnDeleteRow(nEditing);
                nEditing = null;
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });

        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];

            if (nEditing !== null && nEditing != nRow) {
                /* Currently editing - but not this row - restore the old before continuing to edit mode */
                restoreRow(oTable, nEditing);
                editRow(oTable, nRow);
                nEditing = nRow;
            } else if (nEditing == nRow && this.innerHTML == "保存") {
                /* Editing this row and want to save it */
                saveRow(oTable, nEditing);                
            } else {
                /* No edit in progress - let's start one */
                editRow(oTable, nRow);
                nEditing = nRow;
            }
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            handleTable();
        }

    };
}();