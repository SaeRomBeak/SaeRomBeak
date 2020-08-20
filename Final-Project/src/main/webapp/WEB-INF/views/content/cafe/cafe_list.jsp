<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Document</title>
    
  <style type="text/css">
  	.inner-in{width: 800px !important; margin: 0 auto;}
  </style>  
</head>

<body>

    <header>
    	<%@ include file="../header/header.jsp" %>
    </header>

    <div id="content" >
    	<div class="jumbotron" id="main_text">
		    <h2>우리동네 카페 베스트</h2>      
		    <p>각 지역별 유명한 카페를 한눈에! <br> WOW <br> ^오^ </p>
		 </div>
		  
		<div class="inner-in">
			
			<div class="cafe_list">
				
			<div class="listBox">
				<ul class="list">
				<c:forEach items="${list_count}" var="vo" >
					<li>
					
						<div class="cafeListBox">
						    <div class="cafeInfoImg">
						      <a href="cafe_detail.do?c_idx=${vo.c_idx}"><img src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo1}"></a>
						    </div>
						    <div class="cafeInfoBox">			
						
						 	 <h4 class="cafeInfoTitle">▶  ${vo.c_name}  ::  평점 ${vo.review_list.avg}</h4>	
	
							<!-- 작성된 리뷰가 없으면 ----------------------------------------------------------->
						 	 <c:if test="${vo.review_list.r_idx eq null}">
						 	  	 <br><br><br>
						 	  	 <h5>review를 등록해주세요</h5>  
						     </c:if>
						   
							<!-- 작성된 리뷰가 있으면 ----------------------------------------------------------->
						  	  <c:if test="${vo.review_list ne null}">						 
						    	  <h3>Best review!</h3>  
								  <h4>${ vo.review_list.r_cot }</h4>					      	
							</c:if>					      	
							      <a href="cafe_detail.do?c_idx=${vo.c_idx}" id="more">more</a>
						    </div>
						   
					  </div>
					</li>
				</c:forEach>		
				</ul>
			</div>
	
			<div class="pageBox">
		        <ul class="pagination">
		         <c:forEach  varStatus="i" begin="1" end="${page_su}">
		            <li><a href="cafe_select.do?local=${param.local}&page=${i.count}">${i.count}</a></li>
		         </c:forEach>
		        </ul>
	     	</div>
					
		</div>
			
		</div>
		
		<%@ include file="../etc/scrool.jsp" %>
	</div>

    <footer>
    	<%@ include file="../footer/footer.jsp" %>
    </footer>

</body>

</html>