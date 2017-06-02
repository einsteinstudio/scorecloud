$(function () {
  $("#decode-base-btn").bind('click',function(){
      $("#encode-base-btn").nextAll('.alert-box').hide();
      $.ajax({
          url:"/base64decode",
          type:"post",
          data:{
            input:$('#decode-input').val()
          },
          success:function(jsonData)
          {
            if(jsonData.result=='success')
            {
                $('#decode-base-output').val(jsonData.msg);
            }
            else{
                if(jsonData.result=='success')
                {
                    $('#decode-base-output').val(jsonData.msg);
                }
                else{
                    $("#encode-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
          }
      });
  });
    $("#encode-base-btn").bind('click',function(){
        $("#encode-base-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/base64encode",
            type:"post",
            data:{
                input:$('#decode-input').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#decode-base-output').val(jsonData.msg);
                }
                else{
                    $("#encode-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#url-encode-btn").bind('click',function(){
        $("#url-encode-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/urlencode",
            type:"post",
            data:{
                input:$('#url-input').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#url-output').val(jsonData.msg);
                }
                else{
                    $("#url-encode-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#url-decode-btn").bind('click',function(){
        $("#url-decode-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/urldecode",
            type:"post",
            data:{
                input:$('#url-input').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#url-output').val(jsonData.msg);
                }
                else{
                    $("#url-encode-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#gen-hex-btn").bind('click',function(){
        $("#gen-hex-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/generateSignPair",
            type:"post",
            data:{
                type:'hex'
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#pair-pri').val(jsonData.data.priKey);
                    $('#pair-pub').val(jsonData.data.pubKey);
                }
                else{
                    $("#verify-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#gen-base-btn").bind('click',function(){
        $("#gen-base-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/generateSignPair",
            type:"post",
            data:{
                type:'base64'
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#pair-pri').val(jsonData.data.priKey);
                    $('#pair-pub').val(jsonData.data.pubKey);
                }
                else{
                    $("#verify-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#verify-hex-btn").bind('click',function(){
        $("#verify-hex-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/checkSign",
            type:"post",
            data:{
                type:"hex",
                priKey:$('#pair-pri').val(),
                pubKey:$('#pair-pub').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    if(jsonData.msg=="true")
                    {
                        $("#verify-base-btn").after('<div data-alert class="alert-box success round">匹配</div>');
                    }
                    else
                    {
                        $("#verify-base-btn").after('<div data-alert class="alert-box warning round">验证失败</div>');
                    }
                }
                else{
                    $("#verify-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#verify-base-btn").bind('click',function(){
        $("#verify-base-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/checkSign",
            type:"post",
            data:{
                type:"base64",
                priKey:$('#pair-pri').val(),
                pubKey:$('#pair-pub').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    if(jsonData.msg=="true")
                    {
                        $("#verify-base-btn").after('<div data-alert class="alert-box success round">匹配</div>');
                    }
                    else
                    {
                        $("#verify-base-btn").after('<div data-alert class="alert-box warning round">验证失败</div>');
                    }
                }
                else{
                    $("#verify-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#hex-sign-btn").bind('click',function(){
        $("#hex-sign-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/getSign",
            type:"post",
            data:{
                type:"hex",
                priKey:$('#sign-pri').val(),
                src:$('#sign-src').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#sign-sign').val(jsonData.msg);
                }
                else{
                    $("#base-sign-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#base-sign-btn").bind('click',function(){
        $("#base-sign-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/getSign",
            type:"post",
            data:{
                type:"base64",
                priKey:$('#sign-pri').val(),
                src:$('#sign-src').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#sign-sign').val(jsonData.msg);
                }
                else{
                    $("#base-sign-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#verify-hex-sign-btn").bind('click',function() {
        $("#verify-hex-sign-btn").nextAll('.alert-box').hide();
        $.ajax({
            url: "/verifySign",
            type: "post",
            data: {
                type: "hex",
                pubKey: $('#verify-pub').val(),
                src: $('#verify-src').val(),
                sign: $('#verify-sign').val()
            },
            success: function (jsonData) {
                if (jsonData.result == 'success') {
                    if (jsonData.msg == "true") {
                        $("#verify-base-sign-btn").after('<div data-alert class="alert-box success round">验签成功</div>');
                    }
                    else {
                        $("#verify-base-sign-btn").after('<div data-alert class="alert-box warning round">验证失败</div>');
                    }
                }
                else {
                    $("#verify-base-sign-btn").after('<div data-alert class="alert-box alert round">' + jsonData.msg + '</div>');
                }
            }
        });
    });

        $("#verify-base-sign-btn").bind('click',function(){
            $("#verify-base-sign-btn").nextAll('.alert-box').hide();
            $.ajax({
                url:"/verifySign",
                type:"post",
                data:{
                    type:"base64",
                    pubKey:$('#verify-pub').val(),
                    src:$('#verify-src').val(),
                    sign:$('#verify-sign').val()
                },
                success:function(jsonData)
                {
                    if(jsonData.result=='success')
                    {
                        if(jsonData.msg=="true")
                        {
                            $("#verify-base-sign-btn").after('<div data-alert class="alert-box success round">验签成功</div>');
                        }
                        else
                        {
                            $("#verify-base-sign-btn").after('<div data-alert class="alert-box warning round">验证失败</div>');
                        }
                    }
                    else{
                        $("#verify-base-sign-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                    }
                }
            });
    });

    $("#convert-hex-btn").bind('click',function(){
        $("#convert-hex-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/base64AndHex",
            type:"post",
            data:{
                type:"hex",
                input:$('#convert-input').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#convert-output').val(jsonData.msg);
                }
                else{
                    $("#convert-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#convert-base-btn").bind('click',function(){
        $("#convert-base-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/base64AndHex",
            type:"post",
            data:{
                type:"base64",
                input:$('#convert-input').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    $('#convert-output').val(jsonData.msg);
                }
                else{
                    $("#convert-base-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $("#verify-identity-btn").bind('click',function(){
        $("#verify-identity-btn").nextAll('.alert-box').hide();
        $.ajax({
            url:"/validateIdentityNo",
            type:"post",
            data:{
                input:$('#identity-input').val()
            },
            success:function(jsonData)
            {
                if(jsonData.result=='success')
                {
                    if(jsonData.msg=="true")
                    {
                        $("#verify-identity-btn").after('<div data-alert class="alert-box success round">身份证合法</div>');
                    }
                    else
                    {
                        $("#verify-identity-btn").after('<div data-alert class="alert-box warning round">身份证不合法</div>');
                    }
                }
                else{
                    $("#verify-identity-btn").after('<div data-alert class="alert-box alert round">'+jsonData.msg+'</div>');
                }
            }
        });
    });

    $(".live-input").bind('focus',function(){
       $(this).parent().parent().find('.alert-box').hide();
    });

    $("#md5-upper-btn").bind('click',function(){
        $("#md5-upper-btn").nextAll('.alert-box').hide();
        $("#md5-output").val(hex_md5($("#md5-input").val()).toUpperCase());
    });

    $("#md5-lower-btn").bind('click',function(){
        $("#md5-lower-btn").nextAll('.alert-box').hide();
        $("#md5-output").val(hex_md5($("#md5-input").val()).toLowerCase());
    });


});