$(function() {
    $(".redirect-btn").click(function(e) {
        location.href = $(e.currentTarget).children()[0].href
    })
    
    var valid = $("#validation").text();
    
    if(valid == "true") {
        $(".submenu").eq(0).html("<a href='#'>로그아웃</a>");
        $(".submenu").eq(1).html("<a href='#'>마이페이지</a>");
    }
})