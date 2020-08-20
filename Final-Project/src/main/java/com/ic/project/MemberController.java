package com.ic.project;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.CafeDao;
import dao.MemberDao;
import dao.ReviewDao;
import service.CafeService;
import service.MemberService;
import service.ReviewService;
import vo.CafeVo;
import vo.MemberVo;
import vo.ReviewVo;

@Controller
public class MemberController {

	@Autowired
	CafeService cafe_service;
	@Autowired
	MemberService member_service;
	@Autowired
	ReviewService review_service;
	
	
	@Autowired 
	ServletContext application;	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;


	//로그인 기능 ===============================================
	@RequestMapping(value="/login.do",
					produces="text/json; charset=utf-8;")
	@ResponseBody
	public String login(String m_id, String m_pwd) {
		
		MemberVo user = member_service.selectOne(m_id);
		
		String result="";
		
		if(user==null) {
			result="fail_id";
		}else {
			//비번체크 
			if (user.getM_pwd().equals(m_pwd)==false) {
				result = "fail_pwd";
			}else {
				//세션에 id를  USER로 저장
				result="success";
				
				session.setAttribute("user", user);
			}
		}//else1
		
		String json = String.format("{\"result\":\"%s\"}", result);
		
		return json;
	}
	
	
	//로그아웃 ======================================================
	@RequestMapping("/logout.do")
	public String logout() {
	
		session.removeAttribute("user");

		return "redirect:Home.do";
	}
	
	//회원가입 폼불러오기===============================================
	@RequestMapping("/member_insert_form.do")
	public String member_lnsert_form() {
		
		return "/content/mypage/user_join";
	}

	//아이디중복검사 ===================================================
	@RequestMapping(value="/check_id.do",
					produces="text/json; charset=utf-8;")
	@ResponseBody
	public String checked_id(String m_id) {
		
		MemberVo vo = member_service.selectOne(m_id);
		
		boolean bResult=false; 
		
		if(vo==null){	  //DB내에 등록된 정보가 없을경우
			bResult=true; //사용가능
		}
		
		String json = String.format("{\"result\":%b}" ,bResult);
		
		return json;
	}

	//회원가입 ===============================================
	@RequestMapping("/member_insert.do")  
	public String member_insert(MemberVo vo, String m_pwd1,
								String m_phone1,String m_phone2,String m_phone3,
								@RequestParam MultipartFile photo) throws Exception {
	  
		
	  String web_path = "/img/member_upload/"; 
	  String abs_path = application.getRealPath(web_path);
	  
	  String m_photo = "no_file";

	  if(!photo.isEmpty()) { 
		  m_photo = photo.getOriginalFilename();
	  
		  File f = new File(abs_path,m_photo);
	  
		  if(f.exists()) { 
			  long t = System.currentTimeMillis(); 
			  m_photo = String.format("%d_%s",t,m_photo); 
			  f = new File(abs_path,m_photo); 
		  }
		  	photo.transferTo(f); 
		} 
 
	  vo.setM_photo(m_photo);
	 
	
	  StringBuffer sb = new StringBuffer(); 
	  sb.append(m_phone1); sb.append("-");
	  sb.append(m_phone2); sb.append("-"); 
	  sb.append(m_phone3); 
	  String m_tel = sb.toString();
	  
	  String m_ip = request.getRemoteAddr(); 
	  vo.setM_tel(m_tel); 
	  vo.setM_ip(m_ip);
	  vo.setM_pwd(m_pwd1);
	  
	  int res = member_service.insert(vo);
	  
	  MemberVo user = member_service.selectOne(vo.getM_id());
	  session.setAttribute("user", user);
	  
	  return "redirect:Home.do"; 
	  
	  }
	
	//마이페이지들어가기(user) ===============================================
	@RequestMapping("/mypage.do")
	public String user_page(Model model) {
		
		session.removeAttribute("show");
		
		MemberVo  user = (MemberVo) request.getSession().getAttribute("user");
		
		//내가쓴 리뷰 가져오기
		List<ReviewVo> r_list = review_service.selectList_m(user.getM_idx());
		//내가쓴 리뷰의 수
		int m_count = review_service.review_count(user.getM_idx());
		
		model.addAttribute("r_list",r_list);
		model.addAttribute("m_count",m_count);
		
		return "/content/mypage/user_mypage";
	}
	
	//관리자페이지들어가기 ===============================================
	@RequestMapping("/admin_mypage.do")
	public String admin_page(Model model) {
		
		session.removeAttribute("show");
		
		MemberVo  user = (MemberVo) session.getAttribute("user");
		
		//관리자 영역에서 보여주는 list
		List<MemberVo> list_member = member_service.selectList();	//회원리스트
		List<CafeVo> list_cafe = cafe_service.selectList();			//카페리스트
		List<ReviewVo> list_review = review_service.selectList();	//리뷰리스트
		
		model.addAttribute("list_member",list_member);
		model.addAttribute("list_cafe",list_cafe);
		model.addAttribute("list_review",list_review);
		
		//오늘가입한 멤버수 
		int m_count = member_service.member_count();
		model.addAttribute("m_count",m_count);
		
		//오늘 등록한 카페수
		int c_count = cafe_service.cafe_count();
		model.addAttribute("c_count",c_count);
		
		//오늘 등록된 리뷰
		int r_count = review_service.review_count();
		model.addAttribute("r_count",r_count);
		
		return "/content/mypage/admin_mypage";
	}
	
	
	
	//수정폼 불러오기 ==================================================
	@RequestMapping("/member_modify_form.do")
	public String member_modify_form(int m_idx,Model model) {
		
		//m_idx에 해당하는 멤버 정보불러오기
		MemberVo vo = member_service.selectOne(m_idx);
		
		model.addAttribute("vo",vo);
		
		return "/content/mypage/user_edit";	
	}
	
	
	//회원정보 수정하기==================================================
	@RequestMapping("member_modify.do")
	public String member_modify(MemberVo vo, String m_pwd1,
								String m_phone1,String m_phone2,String m_phone3) {
	
	  
	  StringBuffer sb = new StringBuffer(); 
	  sb.append(m_phone1); sb.append("-");
	  sb.append(m_phone2); sb.append("-"); 
	  sb.append(m_phone3); 
	  
	  String m_tel = sb.toString();
	  
	  String m_ip = request.getRemoteAddr(); 
	  vo.setM_tel(m_tel); 
	  vo.setM_ip(m_ip);
	  vo.setM_pwd(m_pwd1);
	
	  int res = member_service.update(vo);
	  
	  MemberVo user1 = (MemberVo) session.getAttribute("user");
	 
	  String m_grade = user1.getM_grade();
	  

	      MemberVo user = member_service.selectOne(vo.getM_id());   
	      session.setAttribute("user", user);
	
	return "redirect:mypage.do"; 
	}
	
	//수정폼 불러오기 관리자 ==================================================
	@RequestMapping("/admin_modify_M_form.do")
	public String admin_modify_M_form(int m_idx,Model model) {
		
		//m_idx에 해당하는 멤버 정보불러오기
		MemberVo vo = member_service.selectOne(m_idx);
		
		model.addAttribute("vo",vo);
		
		return "/content/mypage/admin_user_edit";	
	}

	
	//관리자 회원정보 수정하기==================================================
	@RequestMapping("admin_modify_M.do")
	public String admin_modify_M(MemberVo vo, String m_pwd1,
								 String m_phone1,String m_phone2,String m_phone3) {
	
	  
	  StringBuffer sb = new StringBuffer(); 
	  sb.append(m_phone1); sb.append("-");
	  sb.append(m_phone2); sb.append("-"); 
	  sb.append(m_phone3); 
	  
	  String m_tel = sb.toString();
	  
	  String m_ip = request.getRemoteAddr(); 
	  vo.setM_tel(m_tel); 
	  vo.setM_ip(m_ip);
	  vo.setM_pwd(m_pwd1);
	
	  int res = member_service.update(vo);
	  

	  return "redirect:admin_mypage.do"; 
	}
	
	
	//탈퇴하기==================================================
	@RequestMapping("/member_delete.do")
	public String member_delete(int m_idx) throws Exception {
		
		MemberVo vo = member_service.selectOne(m_idx);
		
		String web_path = "/img/member_upload/";
		
		String abs_path = application.getRealPath(web_path);

		File file = new File(abs_path, vo.getM_photo());

			file.delete();

		int res = member_service.delete(m_idx);
		
		session.removeAttribute("user");

		return "redirect:Home.do";
	}

}
