<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="include/header.jsp" %>
<script>
function doF(){
	$.ajax({ //ajax는 비동식 방법 현재 페이지에서 백그라운드에서 호출
		type: "post",
		url: "${path}/test/doF",
		success: function(result){   //result는 return 값을 의미한다.
			$("#result").html(  //#은 id를 뜻한다.
					"상품명2"+result.name+",가격"+result.price);
		
		//jackson > 객체 값을 string 값으로 바꿔주는 api
		}
	});
}
</script>
</head>
<body>
<%@ include file="include/menu.jsp" %>
<h2>링크 테스트</h2>
<a href="${path}/test/doA">doA</a><br>
<a href="${path}/test/doB">doB</a><br>
<a href="${path}/test/doC">doC</a><br>
<a href="${path}/test/doD">doD</a><br>
<a href="javascript:doF()">doF</a><br>
<div id="result"></div>
</body>
</html>









