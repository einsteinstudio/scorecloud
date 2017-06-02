/**
 * alert取值：success,info,warning,danger
 */
function showInfo(level, content) {
	$('.alert-auto').remove();
	$('.navbar .container-fluid')
			.prepend(
					'<div id="alert" class="alert-auto alert"  style="display:none"><button type="button" class="close" data-dismiss="alert">×</button>'
							+ content + '</div>');
	$('#alert').addClass("alert-" + level);
	$('.alert-auto').show();
	window.setTimeout(function() {
		$('.alert-auto').fadeOut(1000, function() {
			$('.alert-auto').remove();
		});
	}, 1000);
}

/**
 * Jquery valdiate校验扩展
 */
jQuery.validator.addMethod("username_check", function(value, element) { // 用jquery
																		// ajax的方法验证电话是不是已存在
	var flag = 0;
	$.ajax({
		type : "POST",
		url : '/manager/user/check_username.json',
		async : false, // 同步方法，如果用异步的话，flag永远为1
		data : {
			'username' : value
		},
		success : function(msg) {
			if (msg.result == 'success') {
				flag = 1;
			}
		}
	});

	if (flag == 1) {
		return true;
	} else {
		return false;
	}

}, "对不起，用户名已经存在");

/**
 * checkbox全选
 * @param cName
 */
function checkAll(cName) {

	var code_Values = $('input[type=checkbox]');

	if (code_Values.length) {
		for (var i = 0; i < code_Values.length; i++) {
			code_Values[i].checked = true;
		}
	} else {
		code_Values.checked = true;
	}
}

/**
 * checkbox全不选
 * @param cName
 */
function uncheckAll(cName) {

	var code_Values = $('input[type=checkbox]');

	if (code_Values.length) {
		for (var i = 0; i < code_Values.length; i++) {
			code_Values[i].checked = false;
		}
	} else {
		code_Values.checked = false;
	}
}

$('.select-all').toggle(checkAll,uncheckAll);