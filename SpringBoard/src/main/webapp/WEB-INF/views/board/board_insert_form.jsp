<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>쓰기</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<!-- Bootstrap Library  -->
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
   	margin-top: 50px;
   	margin-bottom: 50px;
   }
   
   .btn-group{
   	margin-bottom: 20px;
   }
   
  </style>
  
  
<script type="text/javascript">
  
 //새글 등록하기----------------------------------------------------
   function send(f){
	   
	   var subject = f.subject.value.trim();
	   //var name    = f.name.value.trim();
	   var content = f.content.value.trim();
	   
	   if(subject==''){
		   alert('제목을 입력하세요');
		   f.subject.value='';
		   f.subject.focus();
		   return;
	   }
	   
	   if(content==''){
		   alert('내용을 입력하세요');
		   f.content.value='';
		   f.content.focus();
		   return;
	   }
	   
	   f.action = "insert.do";
	   f.submit();
	   
   }
</script>  
  
  
</head>

<header>
 <div class="header"></div>
</header>

<body>
<div class="container">
  <h2>게시물 쓰기</h2>
 <br><br>
   <div class="content">
   <form>     
  <table class="table table-bordered">
      <tr>
        <th>제목</th>
       	<td><input name="subject" type="text" class="search" style="width:250px;"></td>
      </tr>
  
  	   <tr>
        <th>작성자</th>
     	<td><input name="name"  value="${ user.m_name }"  readonly="readonly"  class="search" style="width:250px;"></td>
      </tr>
      
       <tr>
        <th>내용</th>
        <td><TEXTAREA NAME='content' rows="9" cols="65"></TEXTAREA></td>
      </tr>
  </table>
  
    
  	<div class="btn-group">
  	 <input class="btn btn-default" type="button"  value="등록하기"
            onclick="send(this.form);" >
     <input class="btn btn-default"    type="button"  value="목록보기"
            onclick="location.href='list.do'" >
  	
  	</div>
  	</form>
  </div>
</div>

</body>




<footer>
	 <div class="footer"></div>
</footer>
</html>