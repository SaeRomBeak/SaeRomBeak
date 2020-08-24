package com.ic.total;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MemberDao;
import vo.MemberVo;

@Controller
public class MemberController {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	MemberDao member_dao;

	public MemberController(MemberDao member_dao) {
		super();
		this.member_dao = member_dao;
	}
	
	
	//���̵� �ߺ�üũ�ϱ� ==================================================================================
	@RequestMapping(value="/member/check_id.do",produces="text/json;charset=utf-8;")
	@ResponseBody
	public String check_id(String m_id) {
	
		MemberVo  vo = member_dao.selectOne(m_id);
	
		boolean bResult = false;
		
		//DB���� ��ϵ� ������ ������� ��밡��
		if(vo==null) {
			bResult = true;
		}
		String json = String.format("{\"result\":%b}", bResult);
		return json;
	}

	//ȸ�������ϱ� ==================================================================================
	@RequestMapping("/member/insert.do")
	public String insert(MemberVo vo) {
		
		String m_ip = request.getRemoteAddr();
		vo.setM_ip(m_ip);
		
		int res = member_dao.insert(vo);
				
		return "redirect:../board/list.do";
	}

	
	//ȸ�������� �ҷ����� ==================================================================================
	@RequestMapping("/member/insert_form.do")
	public String insert_form2() {
		return "member/member_insert_form";
	}

	//�α��� �ϱ� json====================================================================================
	@RequestMapping(value="/member/login.do",
			        produces="text/json;charset=utf-8;")
	@ResponseBody
	public String login(String m_id,String m_pwd) {
		
		//user���� ������
		MemberVo user = member_dao.selectOne(m_id);
		
		String result="";
		
		//user�� ������ id�� ����.
		if(user==null) {
			result = "fail_id";
		}else {
			//���üũ
			if(user.getM_pwd().equals(m_pwd)==false) {
				result = "fail_pwd";
			}else {
				//�α��� ���������� session�� ������ �ִ´�
				result = "success";
				session.setAttribute("user", user);
			}
		}
		
		//�������(JSON)
		String json = 
				String.format("{\"result\":\"%s\"}",result);
		
		return json;
	}
	
	
	//�α׾ƿ�====================================================================================
	@RequestMapping("/member/logout.do")
	public String logout() {
		
		session.removeAttribute("user");
		
		return "redirect:../board/list.do";
		
	}

}










