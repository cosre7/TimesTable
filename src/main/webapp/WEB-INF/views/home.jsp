<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>구구단</title>
</head>
<body>
<div>
	<input id="firstTimes" placeholder="첫 단"><br>
	<input id="lastTimes" placeholder="마지막 단"><br>
	<input id="lastMultiplier" placeholder="마지막 곱할 수">
	<button type="button">계산하기</button>
</div>

<div id='resultDiv'></div>
	
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	$('button').on('click', function() {
		var inputValue = {
				'firstTimes' : Number($('#firstTimes').val()),
				'lastTimes' : Number($('#lastTimes').val()),
				'firstMultiplier' : Number('1'),
				'lastMultiplier' : Number($('#lastMultiplier').val())
		}
		
		$.ajax({
			url: "/",
			data: inputValue,
			method: "POST",
			async: false,
			beforeSend : function() {   
			// controller에 data를 보내기 전 오류가 있을 때 데이터 전송을 막을 수 있는 방법
			// beforeSend는 ajax를 요청하기 직전의 콜백함수이기 때문에 실행 전에 막을 수 있다.
				if (inputValue.firstTimes > inputValue.lastTimes) {
					alert("첫 단이 마지막 단보다 작아야합니다.");
					return false;
				}
			}
		})
		// beforeSend에 문제가 없고 데이터도 정상적으로 받아오면 실행되는 함수
		.done(function(returnValue) {
			printTimesTable(returnValue);
		});
	});
	
	function printTimesTable(returnValue) {
		/* Q. inputValue를 파라미터로 받아오는 방식과 지금 방식 중 어떤게 더 좋은 방법일까? */
		var value = JSON.parse(returnValue);
		var firstTimesNumber = value.filter((element) => null === element).length; // null개수 구하기
		// null의 개수 = 시작 단 수
		// [2][1] = 2 * 1 = 2 방식으로 저장되어있기 때문
		
		/* 반복문을 여러 개 돌리는 방식과 함수를 더 만들어서 호출하는 방식 중 어떤게 더 좋은 방법일까? */
		var src = "<table border=1>";
		for (i = firstTimesNumber; i < value.length; i++) { // 첫번째 단 ~ 마지막 단 까지 출력(세로방향)
			src += "<tr>";
			
			for (k = firstTimesNumber; k <= i; k++) { // 한 줄 당 첫번째 단 ~ i 단 까지 출력(가로방향)
				src += "<td>";
				
				for (j = 1; j < value[i].length; j++) { // [2][1] -> 2 * 1 = 2 ... 출력
					var color = (k % 2 == 1) ? "<font color=blue>" : "<font color=orange>";
					src += color + value[k][j] + "<br>";
					
				}
				src += "</td>";
			}
			src += "</tr>";
			
		}
		src += "</table>";
		
		// id=resultDiv 영역에 src 출력
		$("#resultDiv").html(src);
	}
	
</script>
</body>
</html>
