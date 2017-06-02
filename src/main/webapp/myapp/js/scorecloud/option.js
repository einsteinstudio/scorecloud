var TableDatatablesEditable = function () {

    var handleTable = function () {
    	
    	
    	/**添加或者修改*/
    	function ajaxAdd(oTable, nRow)
    	{
    		var aData = oTable.fnGetData(nRow);
    		var idVal=$('td.rowId', nRow).attr('rowId'); //比较好的处理方式
    		$.ajax({
    			url:"/scorecloud/option/optionValue/addOrModify.json",
    			data:{
    				descrp:aData[0],
    				id:idVal,
    				value:aData[1],
    				optionId:$('input[name="id"]').val(),
    				weight:aData[2],
    				checked:aData[3]=='选中'?1:0
    			},
    			success:function(ret)
    			{
    				//更新行ID
    				$('td.rowId', nRow).attr('rowId',ret.data.rowId);
    	    		popGrowl('选项值保存成功','info')
    			}
    		});
    	}
    	
    	function ajaxDelete(oTable, nRow)
    	{
    		var idVal=$('td.rowId', nRow).attr('rowId'); //比较好的处理方式
    		$.ajax({
    			url:"/scorecloud/option/optionValue/delete.json",
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
            jqTds[0].innerHTML = '<input type="text"  class=" form-control input-small" value="' + aData[0] + '">';
            jqTds[1].innerHTML = '<input type="text"  class=" form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<input type="number" class="form-control input-small" value="' + aData[2] + '">';            
            if(aData[3]=='不选中'){
            	jqTds[3].innerHTML = '<select class="option-check bs-select form-control"><option checked="checked">不选中</option><option>选中</option></select>';
            }else{
            	jqTds[3].innerHTML = '<select class="option-check bs-select form-control"><option >不选中</option><option checked="checked">选中</option></select>';
            }
            	//'<input type="text" class="form-control input-small" value="' + aData[2] + '">';
            jqTds[4].innerHTML = '<a class="edit" href="">保存</a>';
            jqTds[5].innerHTML = '<a class="cancel" href="">取消</a>';
        }

        function saveRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            var jqSelects=$('select.option-check', nRow);

            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqSelects.find("option:selected").text(), nRow, 3, false);
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 4, false);
            oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 5, false);
            oTable.fnDraw();
            ajaxAdd(oTable, nRow);
        }

        function cancelEditRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[0].value, nRow, 3, false);
            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 4, false);
            oTable.fnDraw();
        }

        var table = $('#sample_editable_1');

        /**
         * 设置按照第二列升序列排列
         */
        var oSetting=$.extend(true,oSetting,window.DT_defaultSetting);
        oSetting=$.extend(oSetting,{
        	"order": [
                      [2, "asc"]
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
                    $(nEditing).find("td:first").html("Untitled");
                    nEditing = null;
                    nNew = false;

                } else {
                    oTable.fnDeleteRow(nEditing); // cancel
                    nEditing = null;
                    nNew = false;
                    
                    return;
                }
            }

            var aiNew = oTable.fnAddData(['', '', '', '', '','']); //有多少列
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
                nEditing = null;
               
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