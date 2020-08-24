package util;

public class Paging {

//검색기능 페이징처리-------------------------------------------------------------------------------------------------------------
	public static String getPaging(
			String pageURL,
			String search_filter,
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*전체페이지수*/,
            startPage/*시작페이지번호*/,
            endPage;

		boolean  isPrevPage,isNextPage;
		StringBuffer sb; 
		
		
		isPrevPage=isNextPage=false;
		//입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = (int)(rowTotal/blockList);
		
		if(rowTotal%blockList!=0)totalPage++;
		if(nowPage > totalPage)nowPage = totalPage;
		

		//시작 페이지와 마지막 페이지를 구함.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		//마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if(endPage > totalPage)endPage = totalPage;
		
		//마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		if(endPage < totalPage) isNextPage = true;
		
		//시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if(startPage > 1)isPrevPage = true;
	
		
		sb = new StringBuffer();
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a href ='"+pageURL+"?page=");
			sb.append(startPage-1);
			sb.append("&"+ search_filter);
			sb.append("'>◀</a>");
		}
		else
			sb.append("◀");
		
//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
		sb.append("|");
		for(int i=startPage; i<= endPage ;i++){
			if(i>totalPage)break;
			if(i == nowPage){ //현재 있는 페이지
				sb.append("&nbsp;<b><font color='red'>");
				sb.append(i);
				sb.append("</font></b>");
			}
			else{//현재 페이지가 아니면
				sb.append("&nbsp;<a href='"+pageURL+"?page=");
				sb.append(i);
				sb.append("&"+ search_filter);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}// end for
		
		sb.append("&nbsp;|");
		
//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a href='"+pageURL+"?page=");
			sb.append(endPage+1);
			sb.append("&"+ search_filter);
			sb.append("'>▶</a>");
		}
		else
			sb.append("▶");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
}