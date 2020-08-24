package com.ic.total;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.MyConstant;
import dao.BoardDao;
import util.Paging;
import vo.BoardVo;
import vo.MemberVo;

@Controller
public class BoardController {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	BoardDao board_dao;
	
	
	public BoardController(BoardDao board_dao) {
		super();
		this.board_dao = board_dao;
	}

	
	//게시글 리스트 보기======================================================================================
	@RequestMapping("/board/list.do")
	public String list(Integer page,String search,String search_text,Model model) {

		//현재 보여질 페이지변수
		int nowPage = 1;
		if(page!=null) nowPage = page;
		
		//페이지에 따른 가져올 게시물 위치계산
		int start = (nowPage-1)*MyConstant.Board.BLOCK_LIST + 1;
		int end   = start + MyConstant.Board.BLOCK_LIST - 1;
		
		
		Map  map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		//검색내용이 있으면 Map에 검색내용을 추가
		if(search!=null && !search.equals("all")) {
			
			//이름+제목+내용 검색이면 map에 추가하기
			if(search.equals("name_subject_content")) {
				map.put("name", search_text);
				map.put("subject", search_text);
				map.put("content", search_text);
			}else if(search.equals("name")) {
				map.put("name", search_text);
			}else if(search.equals("subject")) {
				map.put("subject", search_text);
			}else if(search.equals("content")) {
				map.put("content", search_text);
			}
		}
		
		//null값들어오는것 방지 목적
		if(search==null) search="all";
		if(search_text==null) search_text="";

		//전체게시물 갯수 구하기
		int rowTotal = board_dao.selectRowTotal(map);
		
		//페이징메뉴 생성		
		String search_filter = 
				String.format("search=%s&search_text=%s", search,search_text);
		
		String pageMenu = Paging.getPaging("list.do", search_filter, nowPage,rowTotal, 
				                            MyConstant.Board.BLOCK_LIST, 
				                            MyConstant.Board.BLOCK_PAGE);
		
		//페이지별 목록가져오기
		List<BoardVo> list = board_dao.selectList(map);
		
	
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		//조회수 증가
		session.removeAttribute("show");

		return "board/board_list";
	}
	
	
	//게시물 상세보기=============================================================================
	@RequestMapping("/board/view.do")
	public String view(int idx,Model model) {
		
		//게시물 정보 가져오기
		BoardVo vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		
		//session에 show가 없으면 조회수가 증가한다.
		if(session.getAttribute("show")==null) {
			
			int res = board_dao.update_readhit(idx);
			
			session.setAttribute("show", true);
		}

		return "board/board_view";
	}
	
	//글쓰기 폼====================================================================================
	@RequestMapping("/board/insert_form.do")
	public String insert_form() {
		return "board/board_insert_form";
	}
	
	//게시글 작성하기==================================================================================
	@RequestMapping("/board/insert.do")
	public String insert(BoardVo vo) {
		
		String ip = request.getRemoteAddr();
		
		//로그인 유저의 m_idx구하기
		MemberVo user = (MemberVo) session.getAttribute("user");
		int m_idx = user.getM_idx();
		
		vo.setIp(ip);
		vo.setM_idx(m_idx);
		
		int res = board_dao.insert(vo);
	
		return "redirect:list.do";
	}
	
	
	//답글쓰기 폼====================================================================================
	@RequestMapping("/board/reply_form.do")
	public String reply_form() {
		return "board/board_reply_form";
	}
	
	//답글쓰기======================================================================================
	@RequestMapping("/board/reply.do")
	public String reply(BoardVo vo,int page,Model model) {
		
		String ip = request.getRemoteAddr();
		
		//로그인 유저의 m_idx구하기
		MemberVo user = (MemberVo) session.getAttribute("user");
		int m_idx = user.getM_idx();
		
		vo.setIp(ip);
		vo.setM_idx(m_idx);
		
		//기준글정보
		BoardVo baseVo = board_dao.selectOne(vo.getIdx());
		
		//기준글아래의 게시물의 step을 1씩 증가
		int res = board_dao.update_step(baseVo);

		vo.setRef(baseVo.getRef());       //ref=ref
		vo.setStep(baseVo.getStep()+1);   //step = step+1
		vo.setDepth(baseVo.getDepth()+1); //depth = depth+1

		//답글달기
		res = board_dao.reply(vo);
		
		//page정보 저장
		model.addAttribute("page", page);
		
		return "redirect:list.do";
	}
	
	//삭제====================================================================================
	@RequestMapping("/board/delete.do")
	public String delete(int idx,int page,String search,String search_text,Model model) {
		
		//삭제하기
		int res = board_dao.delete(idx);
		
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("search_text", search_text);
		
		return "redirect:list.do";
	}
	
	
	//수정 폼====================================================================================
	@RequestMapping("/board/modify_form.do")
	public String modify_form(int idx,Model model) {
		
		//게시글 정보 얻어오기
		BoardVo vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		
		return "board/board_modify_form";
	}
	
	
	//수정하기====================================================================================
	@RequestMapping("/board/modify.do")
	public String modify(BoardVo vo,int page,Model model) {
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);

		int res = board_dao.update(vo);
		
		model.addAttribute("idx", vo.getIdx());
		model.addAttribute("page", page);		
		
		return "redirect:view.do";
	}

}







