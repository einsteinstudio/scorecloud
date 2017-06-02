require('../less/detail-cmt.less');
require('../js/lib/widget/navigator.iscroll.js');
var Fingerprint = require('../bower_components/fingerprint/fingerprint.js');
var clickElement;

$(function() {
    $('.video-js').attr('webkit-playsinline', 'true');
    
    $('.loading').hide();
    getVoteStatus();
    getCommentList();

    $('.write-cmt').on('click', function(){
        if ($('.sendCmt').css("display") == 'none') {
            $('.sendCmt').css("display", "block");
            $('.icon_cmt_gray').css("display", "none");
            $('.cancel-cmt').css("display", "block");
        } else {
            $('.sendCmt').css("display", "none");
            $('.cancel-cmt').css("display", "none");
            $('.icon_cmt_gray').css("display", "block");
        }
    });

    $('.icon_praise_gray').on('click', function(){
        var fingerprint = new Fingerprint().get();
        var objectIdValue = $('.objectId').text();
        clickElement = $(this);
        $.ajax({
            type: "GET",
            url: "http://gmxx.goldskyer.com/front/blog/voteUp.json",
            data: {
                accountId: fingerprint,
                objectId: objectIdValue,
                objectType: "BLOG",
                fp: fingerprint
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "success") {
                    clickElement.attr('class',"icon_praise_red");
                    var currentlikenum = parseInt($('.likeNum').html());
                    $('.likeNum').html(currentlikenum + 1);
                };
            },
            error: function () {
                clickElement.attr('class',"icon_praise_gray");
            }
        });
    });
	
	$(".button").on('click', function() {
    	var info = $(".input-txt").val();
    	if ($.trim(info) != '') {
	    	var name = new Fingerprint().get();
	    	var objectid = $('.objectId').text();
			$.ajax({
				type: "GET",
				url: "http://gmxx.goldskyer.com/comment/add.json",
                data: {
                    accountId: name,
                    objectId: objectid,
                    content: info,
                    objectType:"BLOG"
                },
				dataType: "json",
				success: function(data){
					var hc_list = "<div class='hc_list'>";
			    	hc_list += "<div class='hc_left'>";
			    	hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
			    	hc_list += "</div>";
			    	hc_list += "<div class='hc_right'>";
			    	hc_list += "<div class='hc_name'>" + data.data.comment.accountId + "</div>";
			    	hc_list += "<div class='hc_info'>" + data.data.comment.content + "</div>";
			    	hc_list += "<div class='hc_time'>" + data.data.comment.createDate + "</div>";
			    	hc_list += "</div></div>";
			    	$(".history_comment").prepend(hc_list);
				},
				error: function () {
					return;
				}
	        });
	        $(".input-txt").val('');  	
	    }							
	});

	$(".input-txt").change(function(){
		if($.trim(this.value) == "" ) {
			$(".button").attr("disabled","disabled");
		}
		else {
			$(".button").removeAttr("disabled");
		}
	});

    $('.loadmore-div').on('click', function(){//加载更多评论
        loadMoreData();
    });
});

function getVoteStatus(){//获取点赞状态
    var fingerprint = new Fingerprint().get();
    var objectIdValue = $('.objectId').text();
    $.ajax({
        type: "GET",
        url: "http://gmxx.goldskyer.com/voteUp/queryStatus.json",
        data: {
            accountId: fingerprint,
            objectId: objectIdValue,
            objectType: "BLOG",
            fp: fingerprint
        },
        dataType: "json",
        success: function (data) {
            if (data.result == "failure") {
                $(".icon_praise_gray").attr('class', "icon_praise_red");
            }
        },
        error: function () {
            $(".icon_praise_gray").attr('class', "icon_praise_gray");
        }
    });
};

function getCommentList(){//获取评论列表
    var objectid = $('.objectId').text();
    var pageNo = 1;
    $.ajax({
        type: "GET",
        url: "http://gmxx.goldskyer.com/comment/list.json",
        data: {
            objectId: objectid,
            objectType:"BLOG",
            pageNo:pageNo
        },
        dataType: "json",
        success: function(data){
            var comments = data.data.comments;
            for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                var hc_list = "<div class='hc_list'>";
                hc_list += "<div class='hc_left'>";
                hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
                hc_list += "</div>";
                hc_list += "<div class='hc_right'>";
                hc_list += "<div class='hc_name'>" + comment.accountId + "</div>";
                hc_list += "<div class='hc_info'>" + comment.content + "</div>";
                hc_list += "<div class='hc_time'>" + comment.createDate + "</div>";
                hc_list += "</div></div>";
                $(".history_comment").prepend(hc_list);
            }
            var totalcnt = data.data.count;
            //当totalcnt - 当前currentcnt >= 10,则加载刷新展现
            if (totalcnt - pageNo * 10 > 0) {
                $(".loadmore-div").css("display", "block");//展示
                $(".loadmore-p").css("display", "block");
                $(".loadmore-img").css("display", "none");                
            }else{//否则不现实加载刷新
                $(".loadmore-div").css("display", "none");
            }
            $(".pageNo").text(pageNo + 1);
        },
        error: function () {
            $(".loadmore-div").css("display", "none");
        }
    });
};

function loadMoreData() {
    $('.loadmore-p').css("display", "none");
    $('.loadmore-img').css("display", "block");

    var pageNo = $(".pageNo").text();
    var objectid = $('.objectId').text();
    $.ajax({
        type: "GET",
        url: "http://gmxx.goldskyer.com/comment/list.json",
        data: {
            objectId: objectid,
            objectType:"BLOG",
            pageNo:pageNo
        },
        dataType: "json",
        success: function(data){
            var comments = data.data.comments;
            var totalcnt = data.data.count;
            for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                var hc_list = "<div class='hc_list'>";
                hc_list += "<div class='hc_left'>";
                hc_list += "<img class='hc_img' src='/images/icons/avatar.jpg' >";
                hc_list += "</div>";
                hc_list += "<div class='hc_right'>";
                hc_list += "<div class='hc_name'>" + comment.accountId + "</div>";
                hc_list += "<div class='hc_info'>" + comment.content + "</div>";
                hc_list += "<div class='hc_time'>" + comment.createDate + "</div>";
                hc_list += "</div></div>";
                $(".history_comment").prepend(hc_list);
            }
            //当totalcnt - 如果currentcnt >= 10,则加载刷新展现
            if (totalcnt - pageNo * 10 > 0) {
                $(".loadmore-div").css("display", "block");//展示
                $(".loadmore-p").css("display", "block");
                $(".loadmore-img").css("display", "none");                
            }else{//否则不现实加载刷新
                $(".loadmore-div").css("display", "none");
            }
            $(".pageNo").text(pageNo + 1);
        },
        error: function () {
            $(".loadmore-p").css("display", "block");
            $(".loadmore-img").css("display", "none"); 
        }
    });
};
