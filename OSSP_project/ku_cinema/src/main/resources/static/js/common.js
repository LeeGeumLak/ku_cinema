$(function() {
    $(".redirect-btn").click(function(e) {
        location.href = $(e.currentTarget).children()[0].href
    })
})