<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">      
      <div class="item active">
        <img src="${pageContext.request.contextPath}/img/mainSlider/main_1.png" alt="slide_1">
        <div class="carousel-caption"> </div>      
      </div>

      <div class="item">
        <img src="${pageContext.request.contextPath}/img/mainSlider/main_2.png" alt="slide_1">
        <div class="carousel-caption"> </div>      
      </div>

      <div class="item">
        <img src="${pageContext.request.contextPath}/img/mainSlider/main_3.png" alt="slide_3">
        <div class="carousel-caption"></div>      
      </div>

    </div>

      <!-- Left and right controls -->
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>

      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    
  </div>
    	
	</body>
</html>