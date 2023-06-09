(function ($) {
    "use strict";
    $('[data-toggle="tooltip"]').tooltip();
    $('.categories-slider').slick({
        slidesToScroll: 3,
        slidesToShow: 8,
        arrows: true,
        responsive: [{
            breakpoint: 768,
            settings: {arrows: false, centerMode: true, centerPadding: '40px', slidesToShow: 3}
        }, {breakpoint: 480, settings: {arrows: false, centerMode: true, centerPadding: '40px', slidesToShow: 3}}]
    });
    $('.promo-slider').slick({
        slidesToShow: 3,
        arrows: true,
        dots: true,
        infinite: true,
        responsive: [{
            breakpoint: 768,
            settings: {arrows: false, centerMode: true, centerPadding: '40px', slidesToShow: 1}
        }, {breakpoint: 480, settings: {arrows: false, centerMode: true, centerPadding: '40px', slidesToShow: 1}}]
    });
    $('.osahan-slider').slick({centerMode: false, slidesToShow: 1, arrows: false, dots: true});
    $('.recommend-slider2').slick({
        infinite: true,
        speed: 300,
        slidesToShow: 1,
        adaptiveHeight: true,
        centerMode: true,
        arrows: false,
        dots: true,
        autoplay: true
    });
    $('.recommend-slider').slick({
        infinite: true,
        speed: 300,
        slidesToShow: 1,
        adaptiveHeight: true,
        arrows: false,
        dots: true,
        autoplay: true
    });
    $('.trending-slider').slick({
        centerPadding: '30px',
        slidesToShow: 4,
        arrows: true,
        autoplay: true,
        responsive: [{
            breakpoint: 768,
            settings: {arrows: true, centerMode: true, centerPadding: '40px', slidesToShow: 1}
        }, {breakpoint: 480, settings: {arrows: true, centerMode: true, centerPadding: '40px', slidesToShow: 1}}]
    });
    var $main_nav = $('#main-nav');
    var $toggle = $('.toggle');
    var defaultOptions = {
        disableAt: false,
        customToggle: $toggle,
        levelSpacing: 40,
        navTitle: 'gurdeeposahan',
        levelTitles: true,
        levelTitleAsBack: true,
        pushContent: '#container',
        insertClose: 2
    };
    var Nav = $main_nav.hcOffcanvasNav(defaultOptions);
    const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
    const currentTheme = localStorage.getItem('theme');
    if (currentTheme) {
        document.documentElement.setAttribute('class', currentTheme);
        if (currentTheme === 'dark') {
            toggleSwitch.checked = true;
        }
    }

    function switchTheme(e) {
        if (e.target.checked) {
            document.documentElement.setAttribute('class', 'dark');
            localStorage.setItem('theme', 'dark');
        } else {
            document.documentElement.setAttribute('class', 'light');
            localStorage.setItem('theme', 'light');
        }
    }

    toggleSwitch.addEventListener('change', switchTheme, false);
})(jQuery);
jQuery(document).ready(function () {
    $('.qtyplus').click(function (e) {
        e.preventDefault();
        fieldName = $(this).attr('field');
        var currentVal = parseInt($('input[name=' + fieldName + ']').val());
        if (!isNaN(currentVal)) {
            $('input[name=' + fieldName + ']').val(currentVal + 1);
        } else {
            $('input[name=' + fieldName + ']').val(1);
        }
    });
    $(".qtyminus").click(function (e) {
        e.preventDefault();
        fieldName = $(this).attr('field');
        var currentVal = parseInt($('input[name=' + fieldName + ']').val());
        if (!isNaN(currentVal) && currentVal > 1) {
            $('input[name=' + fieldName + ']').val(currentVal - 1);
        } else {
            $('input[name=' + fieldName + ']').val(1);
        }
    });
});