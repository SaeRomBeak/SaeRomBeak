<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
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
	   	margin-top: 100px;
	   	margin-bottom: 100px;
	   }
</style>

<script type="text/javascript">	
//게시글 삭제하기 ---------------------------------------------------------------------
	function del(f){
		
		if(confirm("삭제 하시겠습니까?")==false) return;
		
		//삭제 수행	
        //f.action = "delete.do";
        //f.submit();
        location.href="delete.do?idx=${ vo.idx}&page=${ param.page }&search=${param.search}&search_text=${ param.search_text}"
        
	}
	
//게시글 수정하기 ---------------------------------------------------------------------	
	function modify(f){
		
		//로그인이된 상태=>수정폼으로 이동
		location.href="modify_form.do?idx=${ vo.idx}&page=${ param.page }&search=${param.search}&search_text=${ param.search_text}";

    }
    
//답글쓰기 ---------------------------------------------------------------------
	function reply(f){

		//로그인이 안된경우
		if('${ empty user}'=='true'){
			
			if(confirm("로그인후에 답글을 다세요\n로그인 하시겠습니까")==false)return;
			
			location.href="../member/login_form.do?url=" + location.href;			
			return;
		}      
		//답글폼으로 이동
		location.href="reply_form.do?idx=${ vo.idx}&page=${ param.page }";
		
    }
</script>
</head>
<header>
 <div class="header"></div>
</header>



<body>
<div class="container">
  <h2>게시글</h2>
 <br><br>
   <div class="content">
   <form>     
    <input type="hidden"  name="idx"   value="${ vo.idx }">
	<input type="hidden"  name="page"  value="${ param.page }">
  <table class="table table-bordered">
      <tr>
        <th width="120" >제목</th>
       	<td>${ vo.subject }</td>
      </tr>
  
  	   <tr>
        <th width="120" >작성자</th>
     	<td>${ vo.name }</td>
      </tr>
      
      <tr>
		<th width="120">작성일</th>
		<td>${ vo.regdate }</td>
	  </tr>
      
      
       <tr>
        <th width="120" >내용</th>
        <td>${ vo.content }</td>
      </tr>
  </table>
 
  		<div class="btn-group">
  			<input class="btn btn-default" type="button"  value="목록"
            onClick="location.href='list.do?page=${ param.page }&search=${param.search}&search_text=${ param.search_text}'; return false;" >
  
			<!-- 답글달기(메인글이면 보여줘라) -->
			<c:if test="${(vo.depth eq 0) }">
				<input class="btn btn-default" type="button"  value="답글" onClick="reply(this.form); return false;" >
			</c:if>
			
			<!-- 수정:글쓴 본인인 경우 -->
	        <c:if test="${ (vo.m_idx eq user.m_idx) }">
	       		 <input class="btn btn-default" type="button"  value="수정" onClick="modify(this.form); return false;">
	        </c:if>
    
			<!-- 삭제: 본인 또는 관리자 -->
			<c:if test="${ (vo.m_idx eq user.m_idx) or ( user.m_grade eq '관리자' ) }">
				 <input class="btn btn-default" type="button"  value="삭제"  onClick='del(this.form); return false;'>
	        </c:if>     
  		</div>
  	</form>
  </div>
</div>
</body>

<footer>
	 <div class="footer"></div>
</footer>

</HTML>