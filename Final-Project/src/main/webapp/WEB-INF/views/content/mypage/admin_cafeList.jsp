<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	//수정하기--------------------------------------------------
	function modify_cafe(c_idx) {
		
		if(confirm('카페정보를 수정하시겠습니까?')==false) return;
		location.href="cafe_modify_form.do?c_idx=" + c_idx;
	}

	//삭제하기--------------------------------------------------
	function delete_cafe(c_idx) {
		
		if(confirm('카페정보를 삭제하시겠습니까?')==false) return;
		location.href="cafe_delete.do?c_idx=" + c_idx;
	}
	
</script>

</head>
<body>

<div class="cafeBox">
    <h3 class="linkBox">카페 목록  <span class="badge">${c_count}</span></h3>

    <table class="table">
        <tr>
            <th class="col-sm-1">번호</th>
            <th class="col-sm-3">카페이름</th>
            <th class="col-sm-4">카페주소</th>
            <th class="col-sm-2">등록일</th>
            <th class="col-sm-2"></th>
        </tr>

	<c:forEach items="${list_cafe}" var="vo" begin="0" end="9" varStatus="i">
        <tr>
            <td>${i.count}</td>
            <td><a href="cafe_detail.do?c_idx=${vo.c_idx}">${vo.c_name}</a></td>
            <td>${vo.c_addr}</td>
            <td>${fn:substring(vo.c_regdate,0,10)}</td>
            <td>
                <div class="btn-group">
                	<!-- /admin_mypage.jsp?admin=edit -->
                    <input type="button" class="btn btn-default" value="수정"  
                    	onclick="modify_cafe('${vo.c_idx}');">
                    <input type="button" class="btn btn-default" value="삭제"
                    	 onclick="delete_cafe('${vo.c_idx}');">
                </div>
            </td>
        </tr>
       
        </c:forEach>
    </table>

    <div class="a-btn"><a href="#" class="btn btn-default">더보기</a></div>
</div>

</body>
</html>