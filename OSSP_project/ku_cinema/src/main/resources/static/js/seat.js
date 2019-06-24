$(function() {
        //콤마함수
    function comma(str) {
        str = String(str);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    }

    //콤마풀기
    function uncomma(str) {
        str = String(str);
        return str.replace(/[^\d]+/g, '');
    }
    function setSeat(str) {
        /*
        str은 좌석배열의 정보를 공백문자로 구분하여 받음.
        첫 문자는 좌석 분단 개수, 분단별 열의 개수, 마지막 문자는 분단의 행의 개수(행은 모든 분단이 같은 것으로 가정.)
        */

        //입력받은 문자열 배열에 담기
        var arr = str.split(" ");
        var len = arr.length;

        //좌석도에 나타낼 행 이름.
        var rowName;

        //동적으로 삽입할 태그정의.
        var tag="";

        $(".seatMap-wrapper").append("<table class='seatMap'></table");
        for(var r=0; r<arr[len-1]; r++) {
            tag += "<tr>";

            //td태그를 생성할 열의 개수
            var tdCnt = 0;
            for(var j=1; j<=len-2; j++) {
                tdCnt += Number(arr[j]);
            }

            for(var k=1; k<=tdCnt; k++) {
                tag += "<td class='seat' ";
                //td 태그에 특정조건에 따라 id="span" 속성 추가
                var sum = 0;
                for(var l=1; l<len-1; l++) {
                    sum += Number(arr[l]);
                    if(k == sum) {
                        tag += "id='span'";
                    }
                }
                tag += ">";
                //화면에 표현할 좌석번호 담기
                rowName = 65 + r;
                rowName = String.fromCharCode(rowName);
                tag += "<p>" + rowName + k + "</p>";
                tag += "</td>";
            }
            tag += "</tr>";
        }
         $(".seatMap").append(tag);
    }

    var str = "3 3 6 2 5";
    setSeat(str);

    //배열로 
    $("#selectedBtn").click(function(){
        var list = $(".selectColor").map(function(){ 
            return $.makeArray($(this).html());
        }).get();
        // 콤마기준으로 값 확인(하나의 string으로 출력)
        /*
            var seq = $(".selectColor").map(function(){ 
                return $(this).html();
            }).get().join(',');
        */
        console.log(list);
    })

    $(".seat").click(function() {
        var select = 0;
        var tmp_cnt = $(".reserveCnt").eq(0).text();
        tmp_cnt = Number(tmp_cnt);
        select += tmp_cnt;
        tmp_cnt = $(".reserveCnt").eq(1).text();
        tmp_cnt = Number(tmp_cnt);
        select += tmp_cnt;
        tmp_cnt = $(".reserveCnt").eq(2).text();
        tmp_cnt = Number(tmp_cnt);
        select += tmp_cnt;
        
        if(select > 0) {
            if($(".selectColor").length < select) {
                var seatName;
                var row = $(this).parent().index();
                row += 65;
                row = String.fromCharCode(row);
                var col = $(this).parent().children().index(this)+1;
                setName = row + col;
                console.log(setName);
                $(this).children("p").toggleClass("selectColor");

                var seq = $(".selectColor").map(function(){ 
                        return $(this).html();
                    }).get().join(' ');

                $(".select-seat").html("<table>"
                                              +"<tr><th>좌석번호</th><td>" + seq + "<span class='select-time'></span></td></tr>"
                                              +"</table>");
            }
        }
        else {alert("인원을 선택해주세요!");}
    })
    $(".seat").mouseover(function() {
        $(this).children("p").addClass("hoverColor");
    }).mouseout(function() {
        $(this).children("p").removeClass("hoverColor");
    })
    
    $(".plus").click(function(){
        var tmp_price = $(this).parent().children("label").attr("data");
        tmp_price = Number(tmp_price);
        var tmp_num = $(this).parent().children(".reserveCnt").text();
        tmp_num = Number(tmp_num);
        if($("#adult .reserveCnt").text() == 0 && $("#student .reserveCnt").text() == 0 && $("#child .reserveCnt").text() == 0) {
            $(".payment").html("<table>"
                                              +"<tr><th>총금액</th><td><strong id='ticketTotalPrice'>0</strong>원</td></tr>"
                                              +"</table>")
        }
        tmp_num++;
        $(this).parent().children(".reserveCnt").text(tmp_num);
        var tmp_total = $("#ticketTotalPrice").text();
        tmp_total = uncomma(tmp_total)
        tmp_total = Number(tmp_total);
        tmp_total += tmp_price;
        console.log(tmp_total);
        $("#ticketTotalPrice").text(comma(tmp_total));
    })

    $(".minus").click(function(){
        var tmp_price = $(this).parent().children("label").attr("data");
        tmp_price = Number(tmp_price);
        var tmp_num = $(this).parent().children(".reserveCnt").text();
        tmp_num = Number(tmp_num);
        tmp_num--;
        var tmp_total = $("#ticketTotalPrice").text();
        tmp_total = uncomma(tmp_total)
        tmp_total = Number(tmp_total);
        if(tmp_num < 0) {
            tmp_num = 0;
        }
        else {
            tmp_total = tmp_total - tmp_price;
        }
        if(tmp_total <= 0) tmp_total = 0;
        $(this).parent().children(".reserveCnt").text(tmp_num);
        $("#ticketTotalPrice").text(comma(tmp_total));
    })
})