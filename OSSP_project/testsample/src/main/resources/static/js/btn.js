$(function() {
    $(".btn_close").click(function() {
        $(".banner-wrapper").slideUp(1500);
    })
                
    $(".like-btn").click(function() {
        if($(this).attr("src") == "img/empty-heart.png") {
            $(this).attr("src", "img/filled-heart.png");
        }
        else {
            $(this).attr("src", "img/empty-heart.png");
        }
    })
})