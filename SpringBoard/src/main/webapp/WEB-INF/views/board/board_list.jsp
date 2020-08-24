<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

        
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>목록보기</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html;">

<!-- Bootstrap Library  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
  #login{
      width:1100px;
       margin:  auto;
       margin-top: 30px;
   }
	
	
   .header{
   		height:200px;
   		background-color: LightGray;
   }
   
   .footer{
   		height:200px;
   		background-color: LightGray;
   }
      
   .container{
   	width: 700px;
   	margin: auto;
   	margin-top: 50px;
   	margin-bottom: 50px;
   }

</style>

<script type="text/javascript">

//로그인기능----------------------------------------------------------------------
	function login(f){
	  
		  var m_id  = f.m_id.value.trim();
		  var m_pwd = f.m_pwd.value.trim();
		  
		  if(m_id==''){
			  alert('아이디를 입력하세요');
			  f.m_id.value='';
			  f.m_id.focus();
			  return;
		  }
		  
		  if(m_pwd==''){
			  alert('비밀번호를 입력하세요');
			  f.m_pwd.value='';
			  f.m_pwd.focus();
			  return;
		  }
	
		  $.ajax({
			  type:"POST",
			  url: "../member/login.do",
			  data:{"m_id":m_id,"m_pwd":m_pwd},
			  dataType: "json",
			  success:function(res_data){
				  if(res_data.result=='fail_id'){
					  alert('아이디가 존재하지 않습니다');
					  return;
				  }
				  
				  if(res_data.result=='fail_pwd'){
					  alert('비밀번호가 틀립니다');
					  return;
				  }
	
				  if('${ empty param.url}'=='true'){
				      location.href="../board/list.do";
				  }else{
					  location.href = '${ param.url}';
				  }
			  }		  		  
		  });	  
	}
  
//글쓰기 폼 띄우기---------------------------------------------------------------------
   function insert_form(){
	   
	   //로그인 안된경우
	   if('${ empty user }'=='true'){
		   
		   if(confirm("글쓰기는 로그인 하신후 이용해주세요")==false)return;

		   return;
	   }
	   
	   location.href="insert_form.do";
   }

//검색기능 --------------------------------------------------------------------- -->
	//jQuery초기화
	$(document).ready(function(){
	  //검색메뉴 초기화
	  $("#search").val("${ (empty param.search) ? 'all' : param.search }");

	  if("${ param.search }"=="all"){
		  $("#search_text").val("");
	  }
	
	  $("#btn_find").click(function(){
		  
		  var search      = $("#search").val();
		  var search_text = $("#search_text").val();
		  
		  //검색카테고리 전체검색이 아닌데 검색어가 비어있으면
		  if(search!='all' && search_text==''){
			  alert('검색어를 입력하세요!!');
			  return;
		  }
		  //조회요청하기
		  location.href="list.do?search=" + search +
				        "&search_text=" + 
				        encodeURIComponent(search_text,"utf-8");
	  }); 
	});

</script>
</head>

<header>
 <div class="header"></div>
</header>

	
	<body>
	<div id="login" align="right"  >
	   <!-- 로그인이 된경우----------------------------- -->
	   <c:if test="${ not empty user }">
	       ${ user.m_name }님 환영합니다
	       <input class="btn btn-default" type="button"  value="로그아웃" 
	              onclick="location.href='../member/logout.do'">
	   </c:if>
	
	
	   <!-- 로그인이 안된경우--------------------------- -->
	   <c:if test="${ empty user }">
	   <button type="button" class="btn btn-default" data-toggle="modal" 
	   								data-target="#myModal">로그인</button>
	   </c:if> 
	   
	  <!-- Modal창띄우기----------------------------------- -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title" align="left">로그인</h4>
	        </div>
	        <div class="modal-body">
	          <form>
		          <table class="table">
		             <tr>
		                 <th>아이디</th>
		                 <td><input name="m_id"></td>
		             </tr>
		             <tr>
		                 <th>비밀번호</th>
		                 <td>
		                   <input type="password" name="m_pwd"></td>
		             </tr>
		          </table>
		          <div class="modal-footer">
		            <input class="btn btn-default mybutton_style" type="button"  value="로그인"
		                 onclick="login(this.form);">
		            <input class="btn btn-default mybutton_style"    type="button"  value="회원가입"
		                 onclick="location.href='../member/insert_form.do'">
		           </div>      
		      </form>    
	         
	        </div>
	      </div>
	      
	    </div>
	  </div>  
	</div>
	
	<!-- 게시판영역------------------------------------------------------------------------------ -->
	
	<div class="container" align="left">
	  <h2>게시판입니다</h2>
		
	 <div class="content">
	  <table class="table table-hover" >
	    <thead>
	      <tr>
	        <th>번호</th>
	        <th>제목</th>
	        <th>작성자</th>
	        <th>작성일</th>
	        <th>조회수</th>
	      </tr>
	    </thead>
	     <c:forEach var="vo"  items="${ list }">
	    <tr>
	      <td align="center">${ vo.no }(${ vo.idx })</td>
	       
	         <!-- 삭제된 게시물 -->
	         <c:if test="${ vo.use_state eq 'n' }">
	              <td>
	               	  <font color="red">${ vo.subject }(삭제된 게시물입니다)</font>
	               </td>
	         </c:if>
	         
	          <!-- 삭제되지 않은 게시물의 경우 -->
	          <c:if test="${ vo.use_state eq 'y' }">					
				<td>
					<!-- 들여쓰기 -->
					<c:forEach begin="1" end="${ vo.depth }">
	                       &nbsp;						
					</c:forEach>
					<!-- 답글이면 -->
					<c:if test="${ vo.depth ne 0 }">
				  	     ㄴ
				    </c:if>
				    <!-- 제목을 누르면 상세보기로 넘어가기 -->
					 <a href="view.do?idx=${ vo.idx }&page=${ (empty param.page) ? 1 : param.page }&search=${param.search}
					 								&search_text=${ param.search_text}" class="num">
						${ vo.subject }
					</a>
				</td>
			</c:if>
			
			<td>${ vo.name }(${ vo.ip })</td>
			<td>${ fn:substring(vo.regdate,0,10) }</td>
			<td>${ vo.readhit }</td>
	        
	   </c:forEach>
	   		
	   		<!-- 데이터가 없는 경우 -->
	 		<c:if test="${ empty list }">
				<tr>
					<td align="center" colspan="11" width="100%" height="50" style="border:1 solid #efefef">
						현재 등록된 글이 없습니다.
					</td>
				</tr>
			</c:if>
		</table>
		
			<input class="btn btn-default" type="button" value="글쓰기"  onclick="insert_form();">
		</div>
		
	<!-- 검색메뉴 -------------------------------------------------------------- -->
		<div>
		 <form class="form-inline">
		   <div align="center">
		       <select  class="form-control" id="search">
		           <option value="all">전체보기</option>
		           <option value="name">이름</option>
		           <option value="subject">제목</option>
		           <option value="content">내용</option>
		           <option value="name_subject_content">이름+제목+내용</option>
		       </select>
		       
		       <input class="form-control" id="search_text"  value="${ param.search_text }">
		       <input class="form-control btn btn-info" id="btn_find"  type="button"  value="검색">
		       
		   </div>
	   </form>
		</div>
		<br>
		
		<!-- 페이지메뉴 -------------------------------------------------------------- -->
		<div class="f11"  style="font-size: 12pt;" align="center">
			${ pageMenu }	
		</div>
		<br>	
	   </div> 
	</body>   
	 
<footer>
	 <div class="footer"></div>
</footer>

</html>