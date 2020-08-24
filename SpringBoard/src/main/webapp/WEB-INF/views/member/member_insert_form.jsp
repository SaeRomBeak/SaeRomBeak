<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap Library  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
<!-- Daum 주소검색 Library  -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
  
<style type="text/css">
	#input_box{
		width: 600px;
		margin: auto;
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
   	margin: auto;
   	margin: 50px, 0, 50px, 0;
   }

</style>  
  
<script type="text/javascript">
//아이디 체크하기-----------------------------------------------------------------------
  var regular_id = /^[a-zA-Z0-9]{4,8}$/;

  $(document).ready(function(){

	  //눌렸다 땔때 아이디 체크하기
	  $("#m_id").keyup(function(event){
		  
		  var m_id = $("#m_id").val();
		  if(regular_id.test(m_id)==false){
			 
			 $("#id_msg").html('4~8자리 영문자숫자조합으로 작성') ;
			 $("#id_msg").css("color","red");
			 $("#btn_register").attr("disabled",true);
			 
			 return;
		  }

		  $.ajax({
			  url : "check_id.do",
			  data:{"m_id":m_id},
			  dataType: "json", 
			  success:function(res_data){
				  if(res_data.result==false){
					  $("#id_msg").html('사용중인 아이디 입니다') ;
					  $("#id_msg").css("color","red");
					  $("#btn_register").attr("disabled",true);
					  return;
				  }
				  
				  $("#id_msg").html('사용가능한 아이디 입니다') ;
				  $("#id_msg").css("color","blue");

				  $("#btn_register").attr("disabled",false);
				  
			  }
		  });
	  }); //중복체크버튼 클릭 
	  
	  
	  //주소검색 클릭
	  $("#btn_find_addr").click(function(){
		  
		  new daum.Postcode({
		        oncomplete: function(data) {

		            $("#m_zipcode").val(data.zonecode);
		            $("#m_addr").val(data.address);	         
		        }
		   }).open();
	  });  
  });//End : document.ready

  
//회원가입하기-----------------------------------------------------------------------
  function send(f){
	  
	  var m_name = f.m_name.value.trim();
	  var m_pwd  = f.m_pwd.value.trim();
	  var m_zipcode = f.m_zipcode.value.trim();
	  var m_addr = f.m_addr.value.trim();
	  
	  //이름체크
	  if(m_name==''){
		  alert('이름을 입력하세요');
		  f.m_name.value='';
		  f.m_name.focus();
		  return;
	  }
	  
	  //비번체크
	  if(m_pwd==''){
		  alert('비밀번호를 입력하세요');
		  f.m_pwd.value='';
		  f.m_pwd.focus();
		  return;
	  }
	  
	  //우편번호 체크
	  if(m_zipcode==''){
		  alert('우편번호를 입력하세요');
		  f.m_zipcode.value='';
		  f.m_zipcode.focus();
		  return;
	  }
	  
	  //주소 체크
	  if(m_addr==''){
		  alert('주소를 입력하세요');
		  f.m_addr.value='';
		  f.m_addr.focus();
		  return;
	  }
	 
	  //전송
	  alert('가입이 완료되었습니다\n로그인후 이용하세요');
	  f.action = "insert.do"; 
	  f.submit();
	   
  }
</script>
</head>
<header>
 <div class="header"></div>
</header>


<body>
  <div id="input_box">
    <div class="panel panel-default class">
      <div class="panel-heading">회원가입</div>
      <div class="panel-body">
	      <form>
	            <input type="hidden"  name="m_grade"  value="일반">
	      		<table class="table table-striped">
	      		   <tr>
	      		      <th>이름</th>
	      		      <td><input name="m_name"></td>
	      		   </tr>
	      		   
	      		   <tr>
	      		      <th>아이디</th>
	      		      <td>
	      		         <input  name="m_id"  id="m_id">
	      		         <span id="id_msg"></span>
	      		      </td>
	      		   </tr>
	      		   
	      		   <tr>
	      		      <th>비밀번호</th>
	      		      <td><input type="password" name="m_pwd"></td>
	      		   </tr>
	      		   
	      		   <tr>
	      		      <th>우편번호</th>
	      		      <td>
	      		         <input name="m_zipcode"  id="m_zipcode">
	      		         <input type="button"  value="주소검색"  id="btn_find_addr">
	      		      </td>
	      		   </tr>
	      		   
	      		   <tr>
	      		      <th>주소</th>
	      		      <td><input name="m_addr"  id="m_addr" size="50"></td>
	      		   </tr>
	      		</table>
	       		<div align="center">
	      		   <div>	      
	                    <input id="btn_register" disabled="disabled" type="button"  value="회원가입" onclick="send(this.form);">
	                    <input type="button"  value="목록보기" onclick="location.href='../board/list.do'">                            	      		      		      
	      		   </div>
	      		</div>
	      </form>		
      </div>
    </div>
    	
  </div>

</body>

<footer>
	 <div class="footer"></div>
</footer>

</html>