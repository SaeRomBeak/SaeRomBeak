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
   <script type="text/javascript">
   //정규식
    var ID = /^[a-z]+[a-z0-9]{3,19}$/;
    
    var pwdID = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    
    var nameID = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
    
    var telID = /^\d{3}-\d{3,4}-\d{4}$/;
    
    var emailID = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
  
 
	//초기화
  	$(document).ready(function() {

  		//아이디체크하기
  		$("#confirm").click(function() {
    	
  			var m_id = $("#m_id2").val().trim();
  			
    		if(ID.test(m_id)==false){
    			
    			$("#id_msg").html('4~8자리 영문자,숫자조합으로 작성하세요');
				$("#m_id2").val(""); 
				$("#m_id2").focus(); 
				
				return;
			}

    		
    		 $.ajax({
    			url:'check_id.do',
 		   		data:{"m_id":m_id},
 		   		dataType:"json",   
 		   		success:function(res_data){
 		   			//res_data = {"result":true}
 		   			if(res_data.result==false){
 		   				$("#id_msg").html('사용중인아이디입니다');
						$("#id_msg").css("color","red");
						$("#nut").attr("disabled",true);
 		   				return;
 		   			}
 		   				$("#id_msg").html('사용가능한아이디입니다');
 		   				$("#id_msg").css("color","blue");
 		   				//1.아이디편집못하도록
 		   				$("#m_id2").attr("readonly",true)
 		   				//2.회원가입버튼활성화
 		   				$("#confirm").attr("disabled",false);
 		   		}
 		    });
		});
  	});//아이디체크
  	
  	
  	
  	//미리보기------------------------------------------------------
 	function showPreview(objFileInput) {
 	    if (objFileInput.files[0]) {
 	        var fileReader = new FileReader();
 	        fileReader.onload = function (e) {
 				$("#img-responsive").attr("src",e.target.result);
 	        }
 			fileReader.readAsDataURL(objFileInput.files[0]);
 	    }
 	}
  	
  	
 	//가입하기-------------------------------------------------
  	 function send(f) {
  		
  		var m_pwd1 = f.m_pwd1.value.trim();
  		var m_pwd2 = f.m_pwd2.value.trim();
  		var m_name = f.m_name.value.trim();
  		var m_email = f.m_email.value.trim();
  		var photo = f.photo.value;
  		var m_gender = f.m_gender.value;
  		
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
 			$("#pwd_msg").html('비밀번호가 틀립니다.');
 			f.m_pwd1.value='';
 			f.m_pwd2.value='';
 			f.m_pwd1.focus();
 			return;
 		}
 		
 		
 		if(nameID.test(m_name)==false){
 			alert('한글로 된 닉네임을 입력하세요')
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

 		f.action="member_insert.do"
 		f.submit();
 		
 		alert('가입이 완료되었습니다 ');
 	}
  
 
  	//가입취소-------------------------------------------------
  	function clean(f) {
  	
  		if(confirm('가입을 취소하시겠습니까??')==false) return;
		location.href = "Home.do";
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
		                <img class="img-responsive center-block" id="img-responsive" 
		                	src="${pageContext.request.contextPath}/img/mypage/user.jpg">
		            </div>
		
		            <form  method="POST" enctype="multipart/form-data">
		
						<input type="hidden" name="m_grade" value="일반">
		                <table class="table">
		                    <tr>
		                        <th class="col-sm-3">사용자 ID</th>
		                        <td class="col-sm-9">
		                            <div class="col-sm-8">
		                                <input type="text" name="m_id" id="m_id2" class="form-control">
		                  				<span id="id_msg"></span>
		                            </div>
		                            <input id="confirm" type="button" class="btn btn-success" value="중복검사">
		                        </td>
		                    </tr>
		
		                    <tr>
		                        <th class="col-sm-3">비밀번호</th>
		                        <td class="col-sm-9"><input type="password" name="m_pwd1" id="m_pwd1" class="form-control">
		                 						      <span id="pwd_msg"></span>
		                        </td>
		                    </tr>
		
		                    <tr>
		                        <th class="col-sm-3">비밀번호 확인</th>
		                        <td class="col-sm-9"><input type="password" name="m_pwd2" id="m_pwd2" class="form-control"></td>
		                 
		                    </tr>
		
		                    <tr>
		                        <th class="col-sm-3">닉네임</th>
		                        <td class="col-sm-9"><input type="text" name="m_name" id="m_name" class="form-control"></td>
		                    </tr>
		
		                    <tr>
		                        <th class="col-sm-3">성별</th>
		                        <td class="col-sm-9">
		                            <label class="radio-inline">
		                                <input type="radio" name="m_gender" value="남성"checked>남성
		                            </label>
		                            <label class="radio-inline">
		                                <input type="radio" name="m_gender" value="여성">여성
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
		                        <td class="col-sm-9"><input class="form-control" type="text" id="m_email" name="m_email" maxlength="30"></td>
		                    </tr>
		
		                    <tr>
		                        <th class="col-sm-3">Image</th>
		                        <td class="col-sm-9">
		                        	<input class="form-control" type="file" name="photo" maxlength="30" onChange="showPreview(this);">
		                        </td>
		                    </tr>
		
							 
		                    <tr class="btnBox">
		                    	<td colspan="2">
		                    	<input id="nut" type="button" class="btn btn-info" value="가입"
		                    	onclick="send(this.form)">
		                    	<input id="nut2" type="button" class="btn btn-info" value="취소"
		                    	onclick="clean(this.form)">
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