<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	//수정하기------------------------------------------------------------------------
 	function modify_member(m_idx) {
		
		if(confirm('수정하시겠습니까?')==false) return;
		location.href = "admin_modify_M_form.do?m_idx=" + m_idx;
	}

</script>
</head>
<body>

<div class="memberBox">
     <h3 class="linkBox">오늘 신규 가입자 <span class="badge">${m_count}</span></h3>

         <table class="table">
             <tr>
                 <th class="col-sm-1">번호</th>
                 <th class="col-sm-2">아이디</th>
                 <th class="col-sm-2">이름</th>
                  <th class="col-sm-2">등급</th>
                 <th class="col-sm-4">가입일자</th>
                 <th class="col-sm-2"></th>
             </tr>
			
			<!-- list = MemberVo -->
			<c:forEach items="${list_member}" var="vo" varStatus="i" >
             <tr>
                 <th>${i.count}</th>
                 <td>${vo.m_id}</td>
                 <td>${vo.m_name}</td>
                 <td>${vo.m_grade}</td>
                 <td>${fn:substring(vo.m_regdate,0,10)}</td>
                 <td>
                     <button type="button" class="btn" onclick="modify_member('${vo.m_idx}');">수정</button>
                 </td>
             </tr>
            </c:forEach>
         </table>

     </div>

     <div class="a-btn"><a href="#" class="btn btn-default">더보기</a></div>


</body>
</html>