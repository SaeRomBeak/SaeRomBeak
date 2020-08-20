<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<style type="text/css">
.cafeList li{
	border: 1px solid black;
}
</style>

</head>

<body>
	<div class="cafeList">
		<div class="title"><h3>CAFE가 모였다</h3></div>
			
		<div class="list">
			<ul>
			<c:forEach var="vo" items="${list}" varStatus="i" begin="0" end="7">
				<li>
					<a class="imgBox" href="cafe_detail.do?c_idx=${vo.c_idx}">
						<img src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo1}" >
					</a>	
					<h5>
					<a href="cafe_detail.do?c_idx=${vo.c_idx}">
						${vo.c_name}
					</a>
					</h5>	
					<p>${vo.c_addr}</p>
				</li>
				</c:forEach>
			</ul>
		</div>

		<div class="more">
			<div class="a-btn"><a href="cafe_select.do?local=&page=1" class="btn btn-default">더보기</a></div>
		</div>
				
	</div>


	<script type="text/javascript">
      $('.cafeList .list ul').masonry({
          // set itemSelector so .grid-sizer is not used in layout
          itemSelector: '.cafeList .list ul li',
          // use element for option
          columnWidth: '.cafeList .list ul li',
          percentPosition: true
      })
   </script>   
		
	</body>
</html>