$(function() {
    $(".redirect-btn").click(function(e) {
        location.href = $(e.currentTarget).children()[0].href
    })
    
    $(".star-rating").rateYo({
        halfStar: true
      });
    var normalFill = $("#rateYo").rateYo("option", "halfStar");
    $(".star-rating").rateYo("option", "halfStar", true);
})