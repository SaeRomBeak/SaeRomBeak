<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   </head>

<body>

    <header>
    	<%@ include file="../header/header.jsp" %>
    </header>

    <div class="bgBox">
        <div class="bg"></div>
    </div>

    <section id="content">
        <div class="topMypage jumbotron">
            <div class="inner">
                <h2>MyPage</h2>

                <div class="imgBox">
                    <div class="row">
                        <a class="col-sm-3" href="">
                        <!-- 프로필사진을 넣지 않았을때  -->
                       <c:if test="${user.m_photo eq 'no_file' }">	
   					  	<img src="${pageContext.request.contextPath}/img/mypage/user.jpg" class="img-circle" alt="프로필 이미지">
              			</c:if>
              			
              			
              			<!-- 프로필사진을 넣었을때  -->
              			 <c:if test="${user.m_photo ne 'no_file' }">	
                        <img src="${pageContext.request.contextPath}/img/member_upload/${user.m_photo}" class="img-circle" alt="프로필 이미지">  
               			 </c:if>
                        </a>
                        
                        <!-- 세션에 저장된 로그인정보  -->
                        <div class="textBox col-sm-9">
                            <h3>Hello ${ user.m_name }</h3>
							
							<!-- 관리자일때 ----------------------------------->
							<c:if test="${ user.m_grade eq '관리자'}">
								<div class="linkBox">                           
	                                <a href="admin_mypage.do?m_idx=${user.m_idx}">관리자페이지</a>
	                                <a href="#">내가 쓴 글 <span class="badge">${m_count}</span></a>
	                                <a href="member_modify_form.do?m_idx=${user.m_idx}">회원정보 수정</a> 
	                            </div>
							</c:if>
							
							<!-- 일반회원일때----------------------------------->
							<c:if test="${ user.m_grade eq '일반'}">
                            <div class="linkBox">                           
                                <a href="#">내가 쓴 글 <span class="badge">${m_count}</span></a>
                                <a href="member_modify_form.do?m_idx=${user.m_idx}">회원정보 수정</a> 
                            </div>
                           </c:if>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="bottomMypage">
            <div class="inner">
 
			<%@ include file="./user_reviewList.jsp" %>
   						
            </div>
        </div>
        
        <%@ include file="../etc/scrool.jsp" %>
    </section>

    <footer>
    	<%@ include file="../footer/footer.jsp" %>
    </footer>

</body>

</html>