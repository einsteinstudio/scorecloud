require('../bower_components/font-awesome/css/font-awesome.min.css');
require('../bower_components/Swiper/dist/css/swiper.min.css');
require('../less/index.less');

$(function() {
    var mySwiper = new Swiper('.swiper-container', {
        direction: 'horizontal',
        loop: true,
        autoplay: 3000,
        pagination: '.swiper-pagination',
        paginationHide: false,
        parallax: true
    });
    var items = document.querySelectorAll('.menuItem');

    for (var i = 0, l = items.length; i < l; i++) {
        items[i].style.left = (50 - 35 * Math.cos(-0.5 * Math.PI - 2 * (1 / l) * i * Math.PI)).toFixed(4) + "%";

        items[i].style.top = (50 + 35 * Math.sin(-0.5 * Math.PI - 2 * (1 / l) * i * Math.PI)).toFixed(4) + "%";
    }

    var margin = ($('body').width() - $('.circle').width()) / 2;
    $('.circle').css('margin', '100px ' + margin + 'px 0');
    $('.loading').hide();
    setTimeout(function() {
        $('.circle').addClass('open');
    }, 400);

    var preX, preY; //上一次触摸点的坐标
    var curX, curY; //本次触摸点的坐标
    var preAngle; //上一次触摸点与圆心的X轴形成的角度(弧度单位)
    var transferAngle; //当前触摸点与上一次preAngle之间变化的角度

    var a = 0;
    var centerX = $('.center').offset().left + 42;
    var centerY = $('.center').offset().top + 42;

    $('body').on('touchstart', function(event) {
        event.preventDefault();
    });

    $('.menuItem').on('tap', function() {
        location.href = $(this).attr('href');
    });

    //点击事件
    $(".circle").on('touchstart', function(event) {
        preX = event.touches[0].clientX;
        preY = event.touches[0].clientY;
        //计算当前触摸点与圆心的X轴的夹角(弧度) --> 上半圆为负(0 ~ -180), 下半圆未正[0 ~ 180]
        preAngle = Math.atan2(preY - centerY, preX - centerX);
        //移动事件
        $(".circle").on('touchmove', function(event) {
            curX = event.touches[0].clientX;
            curY = event.touches[0].clientY;
            //计算当前触摸点与圆心的X轴的夹角(弧度) --> 上半圆为负(0 ~ -180), 下半圆未正[0 ~ 180]
            var curAngle = Math.atan2(curY - centerY, curX - centerX);
            transferAngle = curAngle - preAngle;
            a += (transferAngle * 180 / Math.PI);
            $('.circle').animate({ rotate: a + 'deg' });

            $('.menuItem').animate({ rotate: -a + 'deg' });
            preX = curX;
            preY = curY;
            preAngle = curAngle;
        });
    });
    $('.center').on('tap', function(e) {
        //e.preventDefault();
        $('.circle').toggleClass('open');
    });
});
