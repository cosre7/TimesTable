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
			// ajax 요청을 보내기 전 먼저 실행되는 함수
				if (inputValue.firstTimes > inputValue.lastTimes) {
					alert("첫 단이 마지막 단보다 작아야합니다.");
					return false; 
					// 이 함수가 실행되면 ajax 요청이 보내지지 않도록 해주는 부분 -> beforeSend 시점에서 중단!
				}
			},
			success : function(returnValue) {
				printTimesTable(returnValue);
				console.log(returnValue);
			}
		});
	});
	
	function printTimesTable(returnValue) {
		/* inputValue를 파라미터로 받아오는 방식과 지금 방식 중 어떤게 더 좋은 방법일까? */
		var value = JSON.parse(returnValue);
		var firstTimesNumber = value.filter((element) => null === element).length; // null개수 구하기
		// null의 개수 = 시작 단 수
		// [2][1] = 2 * 1 = 2 방식으로 저장되어있기 때문
		
		/* 반복문을 여러 개 돌리는 방식과 함수를 더 만들어서 호출하는 방식 중 어떤게 더 좋은 방법일까? 
				-> 속도는 똑같다. 더 직관적인 방식을 추천!
				-> 속도를 개선하고 싶다면 같은 반복작업은 임시변수에 저장하는 방식을 사용해보자! */
		var temp = "";
		var src = "<table border=1>";
		for (i = firstTimesNumber; i < value.length; i++) { // 첫번째 단 ~ 마지막 단 까지 출력(세로방향)
			src += "<tr>";
			
			// <td>~</td> 내용을 temp에 저장하고 이를 src에 넣어준다 -> 같은 반복을 여러 번 해줄 필요가 없어진다.
			// ex) 첫번째 반복 -> 2단 저장, 두번째 반복 -> 2단 + 3단 저장, 세번째 반복 -> 2단 + 3단 + 4단 저장 ..
			temp += "<td>";
			for (j = 1; j < value[i].length; j++) { // [2][1] -> 2 * 1 = 2 ... 출력
				var color = (i % 2 == 1) ? "<font color=blue>" : "<font color=orange>";
				temp += color + value[i][j] + "<br>";
			}
			temp += "</td>";
				
			src += temp;
			src += "</tr>";
			
			
		}
		src += "</table>";
		
		// id=resultDiv 영역에 src 출력
		$("#resultDiv").html(src);
		console.log(value);
	}
	
</script>
</body>
</html>
