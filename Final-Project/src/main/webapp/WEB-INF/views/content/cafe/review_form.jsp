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

	$(document).ready(function() {
		if ('${vo.r_grade}' == 1) {
			$(".val1").attr("checked", true);
		}else if ('${vo.r_grade}' == 2) {
			$(".val2").attr("checked", true);
		}else if ('${vo.r_grade}' == 3) {
			$(".val3").attr("checked", true);
		}else if ('${vo.r_grade}' == 4) {
			$(".val4").attr("checked", true);
		}else if ('${vo.r_grade}' == 5) {
			$(".val5").attr("checked", true);
		}
	});
	
	//리뷰 등록----------------------------------------------------------------
	function review_update(f) {

		var r_cot = f.r_cot.value.trim();
		var r_grade = f.r_grade.value;
		
		f.submit();
	}
	
	//이전페이지로 이동 ----------------------------------------------------------
	function goBack() {
		window.history.back();
	}
	
</script>

<style type="text/css">
	#content .inner-in{margin: 120px auto 0;}
</style>

</head>
<body>

	<header>
		<%@ include file="../header/header.jsp" %>
	</header>

	<div id="content">
		<div class="inner-in">

            <div class="titleBox">
                <h2>리뷰 수정</h2>
            </div>
            
			<!-- 리뷰 작성 ------------------------------------------------------------>
			<div class="reviewWriter">
			   <form action="review_modify.do?m_idx=${vo.m_idx}" method="post">
			       <input type="hidden" name="url"  value="${ param.url }">
			       <div class="panel panel-default">
			           <div class="panel-body">
			               <div class="contentBox">
			               	<input type="hidden" name="c_idx" value="${vo.c_idx}">
			               	<input type="hidden" name="r_idx" value="${vo.r_idx}">
			            	<textarea class="form-control reviewContent r_cot" rows="5" name="r_cot" style="resize: none">${vo.r_cot}</textarea>
			            </div>
			            
			            <div class="input-group review_grade">
			                 <span class="input-group-addon"><i class="glyphicon glyphicon-star-empty"></i></span>
			                 
			                 <div class="form-control">
			                 	<label class="radio-inline"><input type="radio" class="val1" name="r_grade" value="1">1점</label>
			                  <label class="radio-inline"><input type="radio" class="val2" name="r_grade" value="2">2점</label>
			                  <label class="radio-inline"><input type="radio" class="val3" name="r_grade" value="3">3점</label>
			                  <label class="radio-inline"><input type="radio" class="val4" name="r_grade" value="4">4점</label>
			                  <label class="radio-inline"><input type="radio" class="val5" name="r_grade" value="5">5점</label>
			                 </div>
			                 
			             </div>
			            
			            <div class="well">
    
			                <div class="btnBox">
			                	<div class="btn-group">
			                		<p class="reviw_alert_text"></p>
			                		
			                     <input type="button" class="btn btn-default" onclick="review_update(this.form);" value="수정">
			                     <input type="button" class="btn btn-default" onclick="goBack();" value="목록">
			                     </div>
			                   	</div>
			                </div>
			            </div>
			        </div>
			
			    </form>
			</div>
			
			<%@ include file="../etc/scrool.jsp" %>
		</div>	
	</div>

	<footer>
    	<%@ include file="../footer/footer.jsp" %>
    </footer>
</body>
</html>