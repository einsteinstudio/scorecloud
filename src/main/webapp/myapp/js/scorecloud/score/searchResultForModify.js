/**
 * 正常的表格更新流程： 1、点击按钮，触发操作； 2、发送请求 3、请求返回成功，刷新页面。所以刷新操作在ajaxAdd的success回调函数中执行
 * 
 * TODO：保存的时候防止重复提交
 */
var TableDatatablesEditable = function() {

	var handleTable = function() {
		// 成功之后刷新表格
		function ajaxSuccess(oTable, nRow, aData) {

			var col = Number($("input[name='col']").val());
			for (var i = 0; i < col; i++) {
				oTable.fnUpdate(aData[i], nRow, 2 + i, false); // 此处开始为科目
			}
			oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 2 + col,
					false);
			oTable.fnDraw();
			nEditing = null;// 编辑成功后，清除该行的编辑状态数据
		}

		/** 添加或者修改,ajax先发送，然后在更新表单 */
		function ajaxAdd(oTable, nRow, aData) {
			//aData 中放的就是成绩, 逗号隔开		
			
			var studentId = $('td.rowId', nRow).attr('rowId'); // 比较好的处理方式
			$.ajax({
				url : "/scorecloud/score/addOrModifyScore.json",
				data : {
					studentId : studentId,
					examId : $('input[name="examId"]').val(),
					scores : aData,
				},
				success : function(ret) {
					// 更新行ID
					if (ret.result == 'success') {
						$('td.rowId', nRow).attr('rowId', ret.data.rowId);
						ajaxSuccess(oTable, nRow, aData);
						popGrowl('保存成功', 'info');
					} else {
						popGrowl(ret.msg, 'danger');
					}
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

		// 编辑行
		function editRow(oTable, nRow) {
			var aData = oTable.fnGetData(nRow);
			var jqTds = $('>td', nRow);
			jqTds[0].innerHTML = aData[0];
			jqTds[1].innerHTML = aData[1];
			var col = Number($("input[name='col']").val());
			for (var i = 0; i < col; i++) {
				jqTds[2 + i].innerHTML = '<input type="number"  class=" form-control input-small" value="'
						+ aData[2 + i] + '">';
			}
			jqTds[2 + col].innerHTML = '<a class="edit" href="">保存</a><a class="cancel" href="">取消</a>';
		}

		function saveRow(oTable, nRow) {
			// 初始化待修改的数据
			var jqInputs = $('input', nRow);
			var jqSelects = $('select[name="subject"]', nRow);
			var aData = new Array();
			var col = Number($("input[name='col']").val());
			for (var i = 0; i < col; i++) {
				aData[i] = jqInputs[i].value; // 第二列
			}
			ajaxAdd(oTable, nRow, aData);// 后台数据更新
		}

		function cancelEditRow(oTable, nRow) {
			var col =Number( $("input[name='col']").val());
			var jqInputs = $('input', nRow);
			for (var i = 0; i < col; i++) {
				oTable.fnUpdate(jqInputs[i].value, nRow, i, false);
			}
			oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 2 + col,
					false);
			oTable.fnDraw();
		}

		var table = $('#sample_editable_1');

		/**
		 * 设置按照第二列升序列排列
		 */
		var oSetting = $.extend(true, oSetting, window.DT_defaultSetting);
		oSetting = $.extend(oSetting, {
			"order" : [ [ 1, "asc" ] ]
		});
		var oTable = table.dataTable(oSetting);

		var tableWrapper = $("#sample_editable_1_wrapper");

		var nEditing = null;
		var nNew = false;



		table.on('click', '.cancel', function(e) {
			e.preventDefault();
			restoreRow(oTable, nEditing);
			nEditing = null;
		});

		table.on('click', '.edit', function(e) {
			e.preventDefault();

			/* Get the row as a parent of the link that was clicked on */
			var nRow = $(this).parents('tr')[0];

			if (nEditing !== null && nEditing != nRow) {
				/*
				 * Currently editing - but not this row - restore the old before
				 * continuing to edit mode
				 */
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

		// main function to initiate the module
		init : function() {
			handleTable();
		}

	};

}();