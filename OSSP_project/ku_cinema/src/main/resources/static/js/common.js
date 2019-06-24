$(function() {
    $(".redirect-btn").click(function(e) {
        location.href = $(e.currentTarget).children()[0].href
    })
    
    var valid = $("#validation").text();
    
    if(valid == "true") {
        $(".submenu").eq(0).html("<a th:href='@{/}'>로그아웃</a>");
        $(".submenu").eq(1).html("<a href='#'>마이페이지</a>");
    }
    
    var title="", theater="", date="", time="";
    $(".movie__list").click(function() {
        $(".movie__list").removeClass("back-blk");
        $(this).addClass("back-blk");
        title = $(this).text();
        console.log("title: " + title);
    })
    
    $(".th__list").click(function() {
        $(".th__list").removeClass("back-blk");
        $(this).addClass("back-blk");
        theater = $(this).text();
        console.log("theater: " + theater);
    })
    
    function day_chk(day) {
        var str;
        switch(day) {
            case 0:
                str = "<span class='dayweek' style='color: red'>일</span>";
                return str;
            case 1:
                str = "<span class='dayweek'>월</span>";
                return str;
            case 2:
                str = "<span class='dayweek'>화</span>";
                return str;
            case 3:
                str = "<span class='dayweek'>수</span>";
                return str;
            case 4:
                str = "<span class='dayweek'>목</span>";
                return str;
            case 5:
                str = "<span class='dayweek'>금</span>";
                return str;
            case 6:
                str = "<span class='dayweek' style='color: blue'>토</span>";
                return str;
        }
    }
    
    var date_tag="";
    //현재 날짜 받아오기
    var tmp = new Date();
    
    //오늘부터 15일만 나타내기
    var i;
    var year, month, date, day;
    
    //달이 바뀌었는지 체크
    var tmp_mon = tmp.getMonth()+1;
    for(i=1; i<=15; i++) {
        if(i!=1) tmp.setDate(tmp.getDate()+1);
        year = tmp.getFullYear();
        month = tmp.getMonth()+1;
        date = tmp.getDate();
        day = tmp.getDay();
        
        if(i==1 || (tmp_mon != month)) {
            date_tag += "<span class='year'>" + year + "</span>"
                        +"<strong class='month'>" + month + "</strong>";
            tmp_mon = month;
        }
        
        date_tag += "<li class='date-list>" + day_chk(day);
        date_tag += "<span class='day'>" + date + "</span></li>";
    }
    
    $(".date__list").append(date_tag);
    
    $(".date__list li").click(function() {
        $(".date__list li").removeClass("back-blk");
        $(this).addClass("back-blk");
        date = $(this).text();
        console.log("date: " + date);
    })
})