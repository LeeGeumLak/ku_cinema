$(function() {
    /*
        2. 이미지갤러리 3초마다 자동실행
    */
    
    function startFnc() {
        // 1. 현재 index 번호를 알기위한 index를 담은 변수
        // 최초 0번째 이미지부터 오토슬라이드 시작
        var currentIndex = $(".sm_mv_slide > dt > span").index($(".sm_mv_slide > dt > span.on"));
        // 2. class sm_mv_bg 태그의 갯수(이미지 갯수) 
        var len = $(".sm_mv_bg").length;

        // 3. 레이아웃의 넓이만큼 이동해야 하기 때문에 class sm_mv_slide 태그의 넓이 값을 구하여 변수에 담는다

        var wdt = $(".sm_mv_slide").width();

        // 4.다음 인덱스 구하는 공식 : (currentIndex + 1) % (2)에서 구한 변수의 결과를 새로운변수에 담는다
        //   (ex : var nextIndex = (currentIndex + 1) % (2)에서 구한 변수)

        var nextIndex = (currentIndex + 1) % len;

        // 5. class sm_mv태그의 자손 dd 태그중 (1)번째 태그에 애니메이션 효과를 준다
        // 애니메이션 속성 = left : -(이미지넓이) 만큼 이동시킨다
        // 동작시간 0.5초

        $(".sm_mv dd").eq(currentIndex).animate({
            "left" : -wdt
        }, 500)

        // 6. class sm_mv 태그의 자손 dd 태그 중 (4)번째 태그에 left 속성값을 이미지 넓이(3)만큼 적용한다

        $(".sm_mv dd").eq(nextIndex).css("left", wdt);

        // 7. class sm_mv 태그의 자손 dd 태그 중 (4)번째 태그를 보여준다

        $(".sm_mv dd").eq(nextIndex).show();

        // 8. class sm_mv 태그의 자손 dd 태그 중 (4)번째 태그에 애니메이션 효과를 준다
        // 애니메이션 속성 = left : 0 만큼 이동시킨다
        // 동작시간 0.5초

        $(".sm_mv dd").eq(nextIndex).animate({
            "left" : "0px"
        }, 500)

        // 9. class  sm_mv_slide 태그의  자식 dt태그의 자식 a 태그를 셀렉터로 잡아서 on 클래스를 제거한다

        $(".sm_mv_slide > dt > span").removeClass("on");

        // 10 class  sm_mv_slide 태그의  자식 dt태그의 (4) 번째 태그의 자식 a 태그를 셀렉터로 잡아서 on 클래스를 추가한다

        $(".sm_mv_slide > dt").eq(nextIndex).children("span").addClass("on");
    }
    set = setInterval(function() {
        startFnc();
    }, 3000);
    
    $("#stp_btn").click(function(){
        if($("#stp_btn").val() == "중지") {
            clearInterval(set);
            $("#stp_btn").attr("value", "시작");
            $("#stp_btn").attr("src", "img/icon/start_btn.png");
        }
        else if($("#stp_btn").val() == "시작") {
            set = setInterval(function(){
               startFnc();
            }, 2000);
            $("#stp_btn").attr("value", "중지");
            $("#stp_btn").attr("src", "img/icon/stp_btn.png");
        }
    })
})