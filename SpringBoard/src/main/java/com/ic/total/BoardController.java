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

	
	//�Խñ� ����Ʈ ����======================================================================================
	@RequestMapping("/board/list.do")
	public String list(Integer page,String search,String search_text,Model model) {

		//���� ������ ����������
		int nowPage = 1;
		if(page!=null) nowPage = page;
		
		//�������� ���� ������ �Խù� ��ġ���
		int start = (nowPage-1)*MyConstant.Board.BLOCK_LIST + 1;
		int end   = start + MyConstant.Board.BLOCK_LIST - 1;
		
		
		Map  map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		//�˻������� ������ Map�� �˻������� �߰�
		if(search!=null && !search.equals("all")) {
			
			//�̸�+����+���� �˻��̸� map�� �߰��ϱ�
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
		
		//null�������°� ���� ����
		if(search==null) search="all";
		if(search_text==null) search_text="";

		//��ü�Խù� ���� ���ϱ�
		int rowTotal = board_dao.selectRowTotal(map);
		
		//����¡�޴� ����		
		String search_filter = 
				String.format("search=%s&search_text=%s", search,search_text);
		
		String pageMenu = Paging.getPaging("list.do", search_filter, nowPage,rowTotal, 
				                            MyConstant.Board.BLOCK_LIST, 
				                            MyConstant.Board.BLOCK_PAGE);
		
		//�������� ��ϰ�������
		List<BoardVo> list = board_dao.selectList(map);
		
	
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		//��ȸ�� ����
		session.removeAttribute("show");

		return "board/board_list";
	}
	
	
	//�Խù� �󼼺���=============================================================================
	@RequestMapping("/board/view.do")
	public String view(int idx,Model model) {
		
		//�Խù� ���� ��������
		BoardVo vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		
		//session�� show�� ������ ��ȸ���� �����Ѵ�.
		if(session.getAttribute("show")==null) {
			
			int res = board_dao.update_readhit(idx);
			
			session.setAttribute("show", true);
		}

		return "board/board_view";
	}
	
	//�۾��� ��====================================================================================
	@RequestMapping("/board/insert_form.do")
	public String insert_form() {
		return "board/board_insert_form";
	}
	
	//�Խñ� �ۼ��ϱ�==================================================================================
	@RequestMapping("/board/insert.do")
	public String insert(BoardVo vo) {
		
		String ip = request.getRemoteAddr();
		
		//�α��� ������ m_idx���ϱ�
		MemberVo user = (MemberVo) session.getAttribute("user");
		int m_idx = user.getM_idx();
		
		vo.setIp(ip);
		vo.setM_idx(m_idx);
		
		int res = board_dao.insert(vo);
	
		return "redirect:list.do";
	}
	
	
	//��۾��� ��====================================================================================
	@RequestMapping("/board/reply_form.do")
	public String reply_form() {
		return "board/board_reply_form";
	}
	
	//��۾���======================================================================================
	@RequestMapping("/board/reply.do")
	public String reply(BoardVo vo,int page,Model model) {
		
		String ip = request.getRemoteAddr();
		
		//�α��� ������ m_idx���ϱ�
		MemberVo user = (MemberVo) session.getAttribute("user");
		int m_idx = user.getM_idx();
		
		vo.setIp(ip);
		vo.setM_idx(m_idx);
		
		//���ر�����
		BoardVo baseVo = board_dao.selectOne(vo.getIdx());
		
		//���ر۾Ʒ��� �Խù��� step�� 1�� ����
		int res = board_dao.update_step(baseVo);

		vo.setRef(baseVo.getRef());       //ref=ref
		vo.setStep(baseVo.getStep()+1);   //step = step+1
		vo.setDepth(baseVo.getDepth()+1); //depth = depth+1

		//��۴ޱ�
		res = board_dao.reply(vo);
		
		//page���� ����
		model.addAttribute("page", page);
		
		return "redirect:list.do";
	}
	
	//����====================================================================================
	@RequestMapping("/board/delete.do")
	public String delete(int idx,int page,String search,String search_text,Model model) {
		
		//�����ϱ�
		int res = board_dao.delete(idx);
		
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("search_text", search_text);
		
		return "redirect:list.do";
	}
	
	
	//���� ��====================================================================================
	@RequestMapping("/board/modify_form.do")
	public String modify_form(int idx,Model model) {
		
		//�Խñ� ���� ������
		BoardVo vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		
		return "board/board_modify_form";
	}
	
	
	//�����ϱ�====================================================================================
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







