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
        var age;
        age = $(this).attr("id");
        
        if(age == "all") age = "전체관람가";
        else if(age == "18") age = "청소년 관람불가";
        else if(age == undefined) age = "미정";
        else age = age + "세관람가";
        
        $(".select-movie").html("<img src='../static/img/thumb/81774_1000.jpg' class='select_thumb'><p style='margin-bottom: 24px;'>" + title + "</p><br><p>" + age + "</p>");
        console.log("title: " + title, age);
    })
    
    $(".th__list").click(function() {
        $(".th__list").removeClass("back-blk");
        $(this).addClass("back-blk");
        theater = $(this).text();
        
        if($(".select-theater").text() == "극장선택") {
            $(".select-theater").html("<table>"
                                      +"<tr><th>극장</th><td>" + theater + "</td></tr>"
                                      +"<tr><th>일시</th><td></td></tr>"
                                      +"<tr><th>상영관</th><td></td></tr>"
                                      +"<tr><th>인원</th><td></td></tr>"
                                      +"</table>")
        }
        else {
            $(".select-theater table tr").eq(0).children("td").html(theater);
        }
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
                str = "<span class='dayweek' style='color: cornflowerblue'>토</span>";
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
        
        var idx = $(".date__list li").index(this);
        var date_str = new Date();
        date_str.setDate(date_str.getDate() + idx);
        var day_str = date_str.getDay();
        switch(day_str) {
            case 0:
                day_str = "일";
                break;
            case 1:
                day_str = "월";
                break;
            case 2:
                day_str = "화";
                break;
            case 3:
                day_str = "수";
                break;
            case 4:
                day_str = "목";
                break;
            case 5:
                day_str = "금";
                break;
            case 6:
                day_str = "토"; 
                break;
        }
        
        date_str = date_str.getFullYear() +"." + String(date_str.getMonth()+1) + "." + date_str.getDate() + "("+ day_str+")";
        console.log(date_str);
        if($(".select-theater").text() == "극장선택") {
            $(".select-theater").html("<table>"
                                      +"<tr><th>극장</th><td></td></tr>"
                                      +"<tr><th>일시</th><td>" + date_str + "</td></tr>"
                                      +"<tr><th>상영관</th><td></td></tr>"
                                      +"<tr><th>인원</th><td></td></tr>"
                                      +"</table>")
        }
        else {
            $(".select-theater table tr").eq(1).children("td").html(date_str);
        }
    })
    
    //예매 영화 리스트 연령 아이콘 설정
    $(".movie__list").each(function() {
        var id = $(this).attr("id");
        if(id != undefined){
            $(this).prepend("<img src='../static/img/icon/" + id+ ".png'>");
        }
    })
    
    $(".time__list strong").each(function(index) {
        //상영시간 리스트 조조/심야 아이콘 설정
        var id = $(this).attr("id");
        if(id != undefined){
            $(this).after("<img src='../static/img/icon/" + id+ ".png'>");
        }
    })
    
    $(".time__list").click(function() {
        $(".time").removeClass("back-blk");
        $(this).children(".time").addClass("back-blk");
        time = $(this).children(".time").text();
        var scr = $(this).prev(".cine-info").children(".cine-name").text();
        console.log(scr);
        
        time = $(".select-theater table tr").eq(1).children("td").text() + " " + time;
        $(".select-theater table tr").eq(1).children("td").html(time);
        $(".select-theater table tr").eq(2).children("td").html(scr);
    })

    //예약정보 선택 초기 상태
    if($(".select-movie").text() == "") {
        $(".select-movie").html("<span class='info-msg'>영화선택</span>");
    }
    if($(".select-theater").text() == "") {
        $(".select-theater").html("<span class='info-msg'>극장선택</span>");
    }
    if($(".select-seat").text() == "") {
        $(".select-seat").html("<span class='info-msg'>좌석선택</span>");
    }
    if($(".payment").text() == "") {
        $(".payment").html("<span class='info-msg'>결제</span>");
    }
    
    $(".seat-page").click(function() {
        $(".reservation-info").css("display", "none");
        $(".seatMap-wrapper").css("dispaly", "block");
    })
})