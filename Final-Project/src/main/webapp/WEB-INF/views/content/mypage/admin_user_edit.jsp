<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    
    <script type="text/javascript">
 
    //정규식
    var pwdID = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    
    var nameID = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
    
    var telID = /^\d{3}-\d{3,4}-\d{4}$/;
    
    var emailID = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
  
    
    $(document).ready(function() {
    
    	 var jb_tel = '${vo.m_tel}';
    	 var Split_tel = jb_tel.split('-');
    	    
    	 $("#m_phone1").val(Split_tel[0]);   
    	 $("#m_phone2").val(Split_tel[1]);   
    	 $("#m_phone3").val(Split_tel[2]); 
    	 
    	 
    	 if( '${ vo.m_grade }' == '일반' ){
			  $("#radio_member").attr("checked",true);
		 	 }else{
			  $("#radio_admin").attr("checked",true);
		  	}
    	

    	 if( '${vo.m_gender}' == '남성' ){
			  $("#m_gender").attr("checked",true);
		  }else{
			  $("#m_gender2").attr("checked",true);
		  }

	});
   
  
    //수정하기------------------------------------------------------------
  	 function update(f) {
  		
  		var m_pwd1 = f.m_pwd1.value.trim();
  		var m_pwd2 = f.m_pwd2.value.trim();
  		var m_name = f.m_name.value.trim();
  		var m_email = f.m_email.value.trim();

  		var m_phone1 = f.m_phone1.value;
  		var m_phone2 = f.m_phone2.value.trim();
  		var m_phone3 = f.m_phone3.value.trim();
  		
  		var m_tel = m_phone1 + "-" + m_phone2 +  "-" + m_phone3;
	
 		f.action = "admin_modify_M.do"; 
 		f.submit();
 	}
  
    </script>
    
    
    
    
</head>

<body>

    <header>
    	<%@ include file="../header/header.jsp" %>
    </header>

    <div id="content" >
		<div class="inner">

			<div id="userJoin">
				<div class="inner-in">

					<div class="titleBox">
						<h2>회원정보 페이지</h2>
					</div>
					
				<div class="profileplace">
					<img class="img-responsive center-block" id="m_photo"
						src="${pageContext.request.contextPath}/img/member_upload/${vo.m_photo}">
				</div>
	
			<form>
				<div hidden="">
					<input type="hidden" name="m_idx" value="${vo.m_idx}">
                 	<input type="password" name="m_pwd1" id="m_pwd1" class="form-control" value="${vo.m_pwd}">
                 	<input type="password" name="m_pwd2" id="m_pwd2" class="form-control" value="${vo.m_pwd}">
                 	<input type="radio" id="m_gender" name="m_gender" value="남성 ">남성
                 	<input type="radio" id="m_gender2" name="m_gender" value="여성 ">여성
                 	<input type="text" name="m_phone1" id="m_phone1" class="form-control">
              		<input type="text" name="m_phone2" id="m_phone2" class="form-control" >
              		<input type="text" name="m_phone3" id="m_phone3" class="form-control">
					<input class="form-control" type="text" name="m_email" maxlength="30" value="${vo.m_email}">
				</div>

				<table class="table">
					<tr>
						<th class="col-sm-3">사용자 ID</th>
						<td class="col-sm-9"><input type="text" name="m_id"
							readonly="readonly" class="form-control" value="${vo.m_id}"></td>
					</tr>

					<tr>
						<th class="col-sm-3">이름</th>
						<td class="col-sm-9"><input type="text" name="m_name"
							id="m_name" readonly="readonly" class="form-control"
							value="${vo.m_name}"></td>
					</tr>
	
					<tr>
						<th>등급</th>
						<td><input id="radio_member" type="radio" name="m_grade"
							value="일반">일반 <input id="radio_admin" type="radio"
							name="m_grade" value="관리자">관리자</td>
					</tr>
					<tr class="btnBox">
						<td colspan="2"><input type="button" class="btn btn-info"
							value="수정" onclick="update(this.form)"></td>
					</tr>
				</table>
			</form>
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