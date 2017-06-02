require('../bower_components/Swiper/dist/css/swiper.min.css');
require('../less/team.less');

$(function () {
    var winWidth = $(window).width();
    $('.loading').hide();
    var leftEnd = winWidth - 145 + 'px';
    $('.my-item-title-animate').animate({
        left: leftEnd,
        opacity: 0.85
    }, 2000, 'ease-in-out', function () {
        $('.my-item-title-animate').css('left', leftEnd);
        $('.my-item-title-animate').css('opacity', 0.85);
    });
    $('.menu-tap').on('tap', function () {
        location.href = $(this).data('href');
    });
    var mySwiper = new Swiper('.swiper-container', {
        direction: 'horizontal',
        loop: true,
        autoplay: 3000,
        pagination: '.swiper-pagination',
        paginationHide: false,
        parallax: true
    });
});