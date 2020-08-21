<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

	<body>
	<div class="cafeCategroy">
	
        <div class="title">
            <h3># 어디로 가볼까?</h3>
        </div>
	
        <ul>
            <li>
                <a href="cafe_select.do?local=&page=1">
                    <img src="${pageContext.request.contextPath}/img/category/best.png" class="img-rounded" alt="Cinque Terre">
                    <h3>이번주 베스트 </h3>
                </a>
            </li>
            
            <li>
                <a href="cafe_select.do?local=서울&page=1">
                    <img src="${pageContext.request.contextPath}/img/category/seoul.jpg" class="img-rounded" alt="Cinque Terre">
                    <h3>서울은 여기로</h3>
       		 </a>
            </li>
            
            <li>
                <a href="cafe_select.do?local=경기&page=1">
                    <img src="${pageContext.request.contextPath}/img/category/gyeonggi.jpg" class="img-rounded" alt="Cinque Terre">
                    <h3>경기는 여기로</h3>
             </a>
            </li>
            
            <li>
                <a href="cafe_select.do?local=인천&page=1">
                    <img src="${pageContext.request.contextPath}/img/category/inchon.jpg" class="img-rounded" alt="Cinque Terre">
                    <h3>인천은 여기로</h3>
              </a>
            </li>
            
            <li>
                <a href="cafe_select.do?local=강원&page=1">
                    <img src="${pageContext.request.contextPath}/img/category/gangwondo.png" class="img-rounded" alt="Cinque Terre">
                    <h3>강원은 여기로</h3>
                </a>
            </li>
            
            <li>
                <a href="cafe_select.do?local=부산&page=1">
                    <img src="${pageContext.request.contextPath}/img/category/busan.jpg" class="img-rounded" alt="Cinque Terre">
                    <h3>부산은 여기로</h3>
                </a>
            </li>
        </ul>
			
    </div>	
    
	</body>
</html>