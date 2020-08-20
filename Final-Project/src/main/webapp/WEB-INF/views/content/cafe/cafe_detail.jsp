<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
  <!-- 슬라이드 css -->
  <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">	

  <title>Document</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=860379d84d4d31052749822019332925"></script>
  <script src="https://kit.fontawesome.com/a076d05399.js"></script>
  <style type="text/css">
  	.swiper-container {
      width: 100%;
      height: 350px;
    }

    .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;

      /* Center slide text vertically */
      display: -webkit-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      -webkit-box-pack: center;
      -ms-flex-pack: center;
      -webkit-justify-content: center;
      justify-content: center;
      -webkit-box-align: center;
      -ms-flex-align: center;
      -webkit-align-items: center;
      align-items: center;
    }
    
    .swiper-slide img{width: 100%;}
  </style>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			var map = "${vo.c_map}";
			var split = map.split(',');
	
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(split[0], split[1]), // 지도의 중심좌표
		        level: 4, // 지도의 확대 레벨
		        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
		    }; 
	
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			var markerPosition  = new kakao.maps.LatLng(split[0], split[1]); 
			
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});

			marker.setMap(map);
		
		});
		
		//리뷰등록-----------------------------------------
		function send(f) {
			
			var c_idx = f.c_idx.value;
			var m_idx = f.m_idx.value;
			var r_cot = f.r_cot.value.trim();
			var r_pwd = f.r_pwd.value.trim();
			var r_grade = f.r_grade.value;
			
			if(r_cot == ''){
				alert('내용을 입력하세요');
				f.r_cot.value='';
				f.r_cot.focus();
				return;
			}
			
			if(r_pwd == ''){
				alert('비밀번호를 입력하세요');
				f.r_pwd.value='';
				f.r_pwd.focus();
				return;
			}
			
			if( r_grade == ''){
				alert('평점을 선택해주세요');
			}
	
	
			alert('리뷰가 등록되었습니다');
	 		f.submit();
		}
		
		//이전페이지이동-------------------------------
		function goBack() {
			window.history.back();
		}
	
	</script>


</head>

<body>

    <header>
    	<%@ include file="../header/header.jsp" %>
    </header>

    <div id="content" >
    	<div class="cafe_imgBox swiper-container">
    		<ul class="swiper-wrapper">
    			<li class="swiper-slide">
    				<img alt="cafe 이미지" src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo1}">
    				<img alt="cafe 이미지" src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo2}">
    				<img alt="cafe 이미지" src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo3}">
    				<img alt="cafe 이미지" src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo4}">
    				<img alt="cafe 이미지" src="${pageContext.request.contextPath}/img/cafe_upload/${vo.c_photo5}">
    			</li>    			
    		</ul>
    	</div>
    
		<div class="inner">
			<!-- 카페정보 -->
			
			<div class="cafeTitle">
				<h2>${vo.c_name}</h2>
				<p>
					<span class="glyphicon glyphicon-eye-open"></span> 
					<span> 조회수  ${vo.c_hit}</span> 
					<span class="glyphicon glyphicon-pencil"></span> 
					<span> 리뷰수  ${review_vo.total}</span> 
				</p>
			</div>
			
			<div id="cafeInfo">
				<table class="table" >
		        <tr>
		            <th class="col-sm-2">주소</th>
		            <td class="col-sm-8">${vo.c_addr} <br>
		            </td>
		        </tr>
		
		        <tr>
		            <th class="col-sm-2">전화번호</th>
		            <td class="col-sm-8">${vo.c_tel} </td>
		        </tr>	 
		
		        <tr>
		            <th class="col-sm-2">가격대</th>
		            <td class="col-sm-8">${vo.c_price} </td>
		          </tr>
		  
		          <tr>
		            <th class="col-sm-2">주차</th>
		            <td class="col-sm-8">${vo.c_park} </td>
		          </tr>
		
		          <tr>
		            <th class="col-sm-2">영업시간</th>
		            <td class="col-sm-8">${vo.c_time} </td>
		          </tr>

		          <tr>
		            <th class="col-sm-2">휴일</th>
		            <td class="col-sm-8">${vo.c_hday} </td>
		          </tr>
		          
		          <tr>
		            <th class="col-sm-2">홈페이지</th>
		            <td class="col-sm-8"><a href="${vo.c_url}">${vo.c_url}</a></td>
		          </tr>
		          
		        <tr>
		        	<td colspan="2" class="tag">
		        		<ul>
		        		<c:set var="tag" value="${fn:split(vo.c_tag,',')}"/>
		        			<c:forEach var="t" begin="0" end="${fn:length(tag)-1}">
			        			<li>
			        				<input type="button" class="btn btn-info" value="${tag[t]}" 
			        				onclick="location.href='cafe_select.do?tag=${vo.tag}&page=1'">
			        			</li>
		        			</c:forEach>
		        		</ul>
		        	</td>
		        </tr> 
		          
		        <tr>
		            <th class="col-sm-2">메뉴</th>
		            <td class="col-sm-8">
		                <ul id="memuList">
		                    <li class="menu">
		                    
		                    <c:set var="menu1" value="${fn:split(vo.c_menu1,',')}"/>
		                    <c:forEach begin="0" end="0">
		                        <span class="menuName" >${menu1[0]}</span>
		                        <span class="menuPrice" >${menu1[1]}</span>
		                     </c:forEach>
		                    </li>
		                    
		                    <li class="menu">
		                    <c:set var="menu2" value="${fn:split(vo.c_menu2,',')}"/>
		                    <c:forEach begin="0" end="0">
		                        <span class="menuName">${menu2[0]}</span>
		                        <span class="menuPrice">${menu2[1]}</span>
		            		</c:forEach>
		                    </li>
		                    
		                    <li class="menu">
		                    <c:set var="menu3" value="${fn:split(vo.c_menu3,',')}"/>
		                    <c:forEach begin="0" end="0">
		                        <span class="menuName">${menu3[0]}</span>
		                        <span class="menuPrice">${menu3[1]}</span>
		                    </c:forEach>
		                    </li>
		                    
		                    <li class="menu">
		                    <c:set var="menu4" value="${fn:split(vo.c_menu4,',')}"/>
		                    <c:forEach begin="0" end="0">
		                        <span class="menuName">${menu4[0]}</span>
		                        <span class="menuPrice">${menu4[0]}</span>
		                    </c:forEach> 
		                    </li>
		                </ul>
		            </td>
		        </tr>
		    </table>
			</div>
		  
		    <div id="map" style="width:100%;height:400px;">
		    
		    
		    </div>
		    <hr>
		    
		    
		    
		    <!--=== 리뷰 작성 =========================================================-->
		   <c:if test="${user.m_idx ne null}">
		      <div class="reviewWriter">
	            <form action="review_insert.do" method="get">
	 			 <input type="hidden" name="c_idx" value="${vo.c_idx}">
	                <div class="panel panel-default">
	                    <div class="panel-heading">
	                       <div class="input-group">
	                           <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	                           <input type="hidden" name="m_idx" value="${user.m_idx}">
	                           <input id="name" type="text" class="form-control" name="m_name" value="${user.m_name}" readonly="readonly" >
	                       </div>
	                    </div>
	                    <div class="panel-body">
	                        <div class="contentBox">
	                        	<textarea class="form-control reviewContent" rows="5" id="r_cot" name="r_cot" style="resize: none"></textarea>
	                        </div>
	                        
	                        <div class="well">
	                            <div class="input-group">
	                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	                                <input id="pwd" type="password" class="form-control" id="r_pwd" name="r_pwd" placeholder="Password">
	                            </div>
	                            <div class="input-group">
	                                <span class="input-group-addon"><i class="fas fa-heart"></i></span>
	                                <div class="form-control">
	                            	    <label class="radio-inline">
									      <input type="radio" name="r_grade" value="1">1점
									    </label>
									    <label class="radio-inline">
									      <input type="radio" name="r_grade" value="2">2점
									    </label>
	                                    <label class="radio-inline">
									      <input type="radio" name="r_grade" value="3">3점
									    </label>
									    <label class="radio-inline">
									      <input type="radio" name="r_grade" value="4">4점
									    </label>
									    <label class="radio-inline">
									      <input type="radio" name="r_grade" value="5">5점									      
									    </label>
	                                </div>
	                            </div>
	                            
	                            
	                            <div class="btnBox">
	                            	<div class="btn-group">
		                                <input type="button" class="btn btn-default" onclick="send(this.form);" value="등록">
		                                <input type="button" class="btn btn-default" onclick="goBack();" value="취소">
		                            </div>
                            	</div>
	                        </div>
	                    </div>
	                </div>
	
	            </form>
	        </div>
		</c:if>
		    
		    
		    
		    
		    <!--==== 리뷰목록 ========================================================= -->
		    <div class="reviewBox">
		    	<div class="reviewText">
		    		<div class="reviewL">리뷰</div>
		    		
		    		<div class="reviewR">
		    			<ul>
		    				<li>전체(${review_vo.total})</li>
		    				<li>1점(${review_vo.bad})</li>
		    				<li>2점(${review_vo.notBad})</li>
		    				<li>3점(${review_vo.soso})</li>
		    				<li>4점(${review_vo.likes})</li>
		    				<li>5점(${review_vo.god})</li>
	    				</ul>
		    		</div>
		    	</div>
		    	
		    	<ul class="reviewUser">
				<c:forEach var="r_vo" items="${r_list}">
		    		<li>
		    			<div class="cafeReview">
						    <div class="userImg">
						    <input type="hidden"  name="c_idx"  value="${vo.c_idx}">
								
							<c:if test="${r_vo.m_photo eq 'no_file'}">
								<img src="${pageContext.request.contextPath}/img/mypage/user.jpg">
							</c:if>
							<c:if test="${r_vo.m_photo ne 'no_file'}">
					       		<img src="${pageContext.request.contextPath}/img/member_upload/${r_vo.m_photo}">
					      	</c:if>
						      	<span>${r_vo.m_id}</span>

						    </div>
						    <div class="review">
						   <img class="point" name="r_grade" src="${pageContext.request.contextPath}/img/score/${r_vo.image}.JPG">
						    
						      <span>여기는 어떤가요?</span>
						      <p>${r_vo.r_cot}</p>
						    </div>
					  </div>
		    		</li>
		    		</c:forEach>
		    	</ul>
		    </div>
		    
		</div>
		
		<%@ include file="../etc/scrool.jsp" %>
	</div>

    <footer>
    	<%@ include file="../footer/footer.jsp" %>
    </footer>
    
	<!-- Swiper JS -->
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<!-- Initialize Swiper -->
	<script>
	    var swiper = new Swiper('.swiper-container', {
	      slidesPerView: 3,
	      loop: true,
	      mousewheel: true,
	      autoplay: {
	          delay: 2500,
	          disableOnInteraction: false,
	        },
	    });
	</script>

</body>

</html>