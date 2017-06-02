require('../bower_components/Swiper/dist/css/swiper.min.css');
require('../less/class-community-view.less');

$(function () {
    $('.loading').hide();
    var mySwiper = new Swiper('.swiper-container', {
        direction: 'horizontal',
        loop: true,
        autoplay: 3000,
        pagination: '.swiper-pagination',
        paginationHide: false,
        parallax: true
    });
});
