require('../bower_components/Swiper/dist/css/swiper.min.css');
require('../less/class-community-list.less');

$(function () {
    var contentHeight = $('body').height() - 420;
    $('.content').height(contentHeight);
    $('.content-item p').css('-webkit-line-clamp', Math.floor((contentHeight - 80) / 24) + '');
    $('.loading').hide();
    var $contentItem = $('.content-item');
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        effect: 'coverflow',
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: 2,
        coverflow: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows: true
        },
        onTransitionEnd: function (swiper) {
            $contentItem.hide();
            $contentItem.eq(swiper.activeIndex).show();
        }
    });

    //滚动新闻
    setInterval(function() {
        $('.scroll-div ul').animate({
            marginTop: '-50px'
        }, 1000, function () {
            $(this).css({marginTop: '0px'}).find('li:first').appendTo(this);
        });
    }, 3000);
});
