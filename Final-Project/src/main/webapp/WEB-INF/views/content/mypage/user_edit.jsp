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
	
  		
  		if(pwdID.test(m_pwd1)==false){
 			$("#pwd_msg").html('대소문자,특수문자포함하여 8-15자');
 			f.m_pwd1.value='';
 			f.m_pwd1.focus();
 			return;
 		}
  		
  		if(pwdID.test(m_pwd1)==false){
  			f.m_pwd1.value='';
  			f.m_pwd1.focus();
 		}
 	
 		if(m_pwd1 != m_pwd2){
 			alert('비밀번호가 틀립니다');
 			f.m_pwd1.value='';
 			f.m_pwd2.value='';
 			f.m_pwd1.focus();
 			return;
 		}
 		
 		
 		if(nameID.test(m_name)==false){
 			alert('한글닉네임을 입력하세요')
 			f.m_name.value='';
 			f.m_name.focus();
 			return;
 		}
 		
 		
 		if(telID.test(m_tel)==false){
 			alert('전화번호를 입력하세요')
 			f.m_phone2.value='';
 			f.m_phone3.value='';
 			f.m_phone2.focus();
 			return;
 		}
 		
 		
 		if(emailID.test(m_email)==false){
 			alert('유효하지 않는 이메일입니다.')
 			f.m_email.value='';
 			f.m_email.focus();
 			return;
 		}
 		
 		f.action = "member_modify.do"; 
 		f.submit();
 	}
  
  	 
  	///삭제하기---------------------------------------------------
  	function del(m_idx) {
		
		if(confirm('정말탈퇴하시겠습니까?')==false) return;
		location.href = "member_delete.do?m_idx=" + m_idx;
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

					<!-- 회원정보수정----------------------------------------------------------------------------------->
						<form method="POST" enctype="multipart/form-data">
						    <input type="hidden" name="m_idx" value="${vo.m_idx}">
							<table class="table">
									<tr>
										<th class="col-sm-3">사용자 ID</th>
										<td class="col-sm-9">
											<div class="col-sm-8">
												<input type="text" name="m_id" readonly="readonly"
														class="form-control" value="${vo.m_id}">
											</div>
										</td>
									</tr>

									<tr>
										<th class="col-sm-3">비밀번호</th>
										<td class="col-sm-9"><input type="password" name="m_pwd1"
											id="m_pwd1" class="form-control" value="${vo.m_pwd}">
											<span id="pwd_msg"></span>
										</td>
									</tr>

									<tr>
										<th class="col-sm-3">비밀번호 확인</th>
										<td class="col-sm-9"><input type="password" name="m_pwd2"
											id="m_pwd2" class="form-control" value="${vo.m_pwd}">
										</td>
									</tr>

									<tr>
										<th class="col-sm-3">이름</th>
										<td class="col-sm-9"><input type="text" name="m_name"
											id="m_name" class="form-control" value="${vo.m_name}">
										</td>
									</tr>

									<tr>
										<th class="col-sm-3">성별</th>
										<td class="col-sm-9">
											<label class="radio-inline">
												<input type="radio" id="m_gender" name="m_gender" value="남성">남성
											</label> 
											<label class="radio-inline"> 
												<input type="radio" id="m_gender2" name="m_gender" value="여성">여성
											</label>
										</td>
									</tr>

									<tr>
										<th class="col-sm-3">전화번호</th>
										<td class="col-sm-9">
											<div class="col-sm-4">
												<select class="form-control" name="m_phone1" id="m_phone1">
													<option value="010">010</option>
													<option value="011">011</option>
													<option value="016">016</option>
													<option value="017">017</option>
													<option value="018">018</option>
													<option value="019">019</option>
												</select>
											</div>

											<div class="col-sm-4">
												<input type="text" name="m_phone2" id="m_phone2" class="form-control">
											</div>

											<div class="col-sm-4">
												<input type="text" name="m_phone3" id="m_phone3" class="form-control">
											</div>
										</td>
									</tr>

									<tr>
										<th class="col-sm-3">E-Mail</th>
										<td class="col-sm-9">
											<input class="form-control" type="text" name="m_email" maxlength="30" value="${ vo.m_email}">
										</td>
									</tr>

									<tr hidden="hidden">
										<th>등급</th>
										<td>
											<input id="radio_member" type="radio" name="m_grade" value="일반">일반 
											<input id="radio_admin" type="radio"name="m_grade" value="관리자">관리자
										</td>
									</tr>

									<tr class="btnBox">
										<td colspan="2">
											<input id="nut" type="button" class="btn btn-info" value="수정" onclick="update(this.form);">
											<input type="button" class="btn btn-info" value="탈퇴" onclick="del('${vo.m_idx}');">
										</td>
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