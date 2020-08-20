<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	//삭제하기----------------------------------------------------
	function del(r_idx){	
		if(confirm('정말삭제하시겠습니까?')==false) return;
		location.href = "review_delete.do?r_idx=" + r_idx;
	}

	
</script>


</head>
<body>

<div class="reviewBox">
     <h3 class="linkBox">오늘 신규 리뷰 <span class="badge">${r_count}</span></h3>
	<table class="table" >
         <tr>
             <th class="col-sm-1">번호</th>
             <th class="col-sm-6">내용</th>
             <th class="col-sm-2">아이디</th>
             <th class="col-sm-2">등록일</th>
             <th class="col-sm-1"></th>
         </tr>

		<c:forEach items="${list_review}" var="vo" varStatus="i" begin="0" end="9">
         <tr>
             <th>${i.count}</th>
             <td><a href="cafe_detail.do?c_idx=${vo.c_idx}">${vo.r_cot}</a></td>
             <td>${vo.m_id}</td>
             <td>${fn:substring(vo.r_regdate,0,10)}</td>
             <td>
                 <button type="button" class="btn" onclick="del('${vo.r_idx}')">삭제</button>
             </td>
         </tr>
         </c:forEach>
     </table>
     <div class="a-btn"><a href="#" class="btn btn-default">더보기</a></div>
</div>
</body>
</html>