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
	
	
	//아이디 중복체크하기 ==================================================================================
	@RequestMapping(value="/member/check_id.do",produces="text/json;charset=utf-8;")
	@ResponseBody
	public String check_id(String m_id) {
	
		MemberVo  vo = member_dao.selectOne(m_id);
	
		boolean bResult = false;
		
		//DB내에 등록된 정보가 없을경우 사용가능
		if(vo==null) {
			bResult = true;
		}
		String json = String.format("{\"result\":%b}", bResult);
		return json;
	}

	//회원가입하기 ==================================================================================
	@RequestMapping("/member/insert.do")
	public String insert(MemberVo vo) {
		
		String m_ip = request.getRemoteAddr();
		vo.setM_ip(m_ip);
		
		int res = member_dao.insert(vo);
				
		return "redirect:../board/list.do";
	}

	
	//회원가입폼 불러오기 ==================================================================================
	@RequestMapping("/member/insert_form.do")
	public String insert_form2() {
		return "member/member_insert_form";
	}

	//로그인 하기 json====================================================================================
	@RequestMapping(value="/member/login.do",
			        produces="text/json;charset=utf-8;")
	@ResponseBody
	public String login(String m_id,String m_pwd) {
		
		//user정보 얻어오기
		MemberVo user = member_dao.selectOne(m_id);
		
		String result="";
		
		//user가 없으면 id가 없다.
		if(user==null) {
			result = "fail_id";
		}else {
			//비번체크
			if(user.getM_pwd().equals(m_pwd)==false) {
				result = "fail_pwd";
			}else {
				//로그인 성공했을시 session에 정보를 넣는다
				result = "success";
				session.setAttribute("user", user);
			}
		}
		
		//결과정보(JSON)
		String json = 
				String.format("{\"result\":\"%s\"}",result);
		
		return json;
	}
	
	
	//로그아웃====================================================================================
	@RequestMapping("/member/logout.do")
	public String logout() {
		
		session.removeAttribute("user");
		
		return "redirect:../board/list.do";
		
	}

}










