require('../less/detail.less');
require('../js/lib/widget/navigator.iscroll.js');
var Fingerprint = require('../bower_components/fingerprint/fingerprint.js');
var clickElement;
$(function() {
    $('.video-js').attr('webkit-playsinline', 'true');

    $('.loading').hide();

    getVoteStatus();

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
});

function getVoteStatus(){
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