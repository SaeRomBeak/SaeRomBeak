<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

	var urlID = /(http(s)?:\/\/)([a-z0-9\w]+\.*)+[a-z0-9]{2,4}/gi
	
	var telID = /^\d{2,3}-\d{3,4}-\d{4}$/;

	$(document).ready(function(){
	   

	$("#c_addr").click(function(){
		
		/* 다음 주소창 팝업띄우기 */
		 new daum.Postcode({
		        oncomplete: function(data) {
		
		            $("#c_addr").val(data.address);
		      		alert(data.address);
		        }
		    }).open();

	});//주소창

	
	   if( '${ vo.c_park }' == '가능' ){
			  $("#c_park").attr("checked",true);
		 	 }else{
			  $("#c_park2").attr("checked",true);
		  	}
	     
	});

	//수정하기-----------------------------------------------------
	function update(f) {
	
	var	c_name = f.c_name.value.trim();
	var	c_tel = f.c_tel.value.trim();
	var	c_park = f.c_park.value;
	var	c_price = f.c_price.value;
	var	c_time = f.c_time.value.trim();
	var	c_hday = f.c_hday.value.trim();
	var	c_url = f.c_url.value.trim();
	var	c_menu1 = f.c_menu1.value;
	var	c_menu2 = f.c_menu2.value;
	var	c_menu3 = f.c_menu3.value;
	var	c_menu4 = f.c_menu4.value;
	var	c_tag = f.c_tag.value.trim();
	var	c_map = f.c_map.value.trim();

	if(c_name == ''){
			alert('카페명을입력하세요');
			f.c_name.value='';
			f.c_name.focus();
			return;
		}

	
	if(telID.test(c_tel)==false){
			alert('전화번호를 다시입력하세요')
			f.c_tel.value='';
			f.c_tel.focus();
			return;
		}
	
	if(c_time == ''){
			alert('시간을입력하세요');
			f.c_time.value='';
			f.c_time.focus();
			return;
		}
	
	if(c_hday == ''){
			alert('휴일을입력하세요');
			f.c_hday.value='';
			f.c_hday.focus();
			return;
		}
	
	if(c_menu1 == ''){
			alert('메뉴를입력하세요');
			f.c_menu1.value='';
			f.c_menu1.focus();
			return;
		}
	
	if(c_tag == ''){
			alert('태그를 입력하세요');
			f.c_tag.value='';
			f.c_tag.focus();
			return;
		}
	
	if(c_map == ''){
			alert('위도를입력하세요');
			f.c_map.value='';
			f.c_map.focus();
			return;
		}
	

	
	alert('등록이완료되었습니다.');
	f.action = "cafe_modify.do"; 
	f.submit();

  }

	//이전페이지로 이동----------------------------------------------
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
		<div class="inner">
			<div class="inner-in formBox">
			    <h3 class="linkBox">카페 수정</h3>
			
			  <form  method="post">	
					<input type="hidden"  name="c_idx"  value="${vo.c_idx}">
		           		 <table class="table">
        
			            <tr>
			                <th class="col-sm-1">
			                    <span class="group-addon">@</span>
			                </th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_name" id="c_name" class="form-control" value="${vo.c_name}">
			                </td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-map-marker"></i></span></th>
			                <td class="col-sm-11">
			                    <input  name="c_addr" id="c_addr" class="form-control" value="${vo.c_addr}">
			                </td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-phone-alt"></i></span></th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_tel" id="c_tel" class="form-control" value="${vo.c_tel}">
			                </td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-credit-card"></i></span></th>
			                <td class="col-sm-11">
			                    <select class="form-control" name="c_price" id="c_price">
			                        <option value="2000">2000원대</option>
			                        <option value="4000">4000원대</option>
			                        <option value="6000">6000원대</option>
			                        <option value="8000">8000원대</option>
			                        <option value="10000">10000원대</option>
			                        <option value="12000">12000원대</option>
			                    </select>
			                </td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-road"></i></span></th>
			                <td class="col-sm-11">
			                    <label class="radio-inline">
			                        <input type="radio" name="c_park" id ="c_park"  value="가능" checked>가능
			                    </label>
			                    <label class="radio-inline">
			                        <input type="radio" name="c_park" id ="c_park2" value="불가">불가
			                    </label>
			                </td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-time"></i></span></th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_time" id="c_time" class="form-control" value="${vo.c_time}">
			                </td>
			            </tr>
			            
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-time"></i></span></th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_hday" id="c_hday" class="form-control" value="${vo.c_hday}">
			                </td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-globe"></i></span></th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_url" id="c_url" class="form-control" value="${vo.c_url}">
			                </td>
			            </tr>
			
			            <tr>
			                <th rowspan="4" class="col-sm-1 rowspan">
			                    <span class="group-addon"><i class="glyphicon glyphicon-glass"></i></span>
			                </th>
			                <td class="col-sm-11"><input type="text" id="c_menu1" name="c_menu1" class="form-control" value="${vo.c_menu1}"></td>
			            </tr>
			            <tr>
			                <td class="col-sm-11"><input type="text"  id="c_menu2" name="c_menu2" class="form-control" value="${vo.c_menu2}"></td>
			            </tr>
			            <tr>
			                <td class="col-sm-11"><input type="text"  id="c_menu3" name="c_menu3" class="form-control" value="${vo.c_menu3}"></td>
			            </tr>
			            <tr>
			                <td class="col-sm-11"><input type="text"  id="c_menu4" name="c_menu4" class="form-control" value="${vo.c_menu4}"></td>
			            </tr>
			
			            <tr>
			                <th class="col-sm-1"><span class="group-addon">#</span></th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_tag" id="c_tag" class="form-control" value="${vo.c_tag}">
			                </td>
			            </tr>

			            <tr>
			                <th class="col-sm-1"><span class="group-addon"><i class="glyphicon glyphicon-globe"></i></span></th>
			                <td class="col-sm-11">
			                    <input type="text" name="c_map" id="c_map" class="form-control" value="${vo.c_map}">
			                </td>
			            </tr>
			        </table>
			
			        <div class="a-btn">
			            <div class="btn-group">
			                <input type="button" class="btn" value="수정" onclick="update(this.form)">
			                <input type="button" class="btn" value="목록" onclick="goBack();"> 
			            </div>
			        </div>
			    </form>
			    <br><br>
			
			</div>
		</div>
		
		<%@ include file="../etc/scrool.jsp" %>
	</div>
	
	<footer>
		<%@ include file="../footer/footer.jsp" %>
	</footer>

</body>
</html>