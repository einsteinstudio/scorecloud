require('../less/overview.less');

$(function () {
    var winWidth = $(window).width();
    var itemHeight = ($('.my-menu').height() - 50) / 4;
    $('.my-item').height(itemHeight);
    var $myItemTitle = $('.my-item-title');
    $myItemTitle.css('top', '-' + itemHeight + 'px');
    $('.loading').hide();
    var leftEnd = winWidth - 170 + 'px';
    $myItemTitle.animate({
        left: leftEnd,
        opacity: 1
    }, 2000, 'ease-in-out', function () {
        $myItemTitle.css('left', leftEnd);
        $myItemTitle.css('opacity', 1);
    });
});