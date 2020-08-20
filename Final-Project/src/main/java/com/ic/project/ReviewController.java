package com.ic.project;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CafeDao;
import dao.MemberDao;
import dao.ReviewDao;
import service.CafeService;
import service.ReviewService;
import vo.ReviewVo;

@Controller
public class ReviewController {

	@Autowired
	ReviewService review_service;
	
	@Autowired 
	ServletContext application;	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;


	//리뷰등록하기==========================================
	@RequestMapping("/review_insert.do")
	public String review_insert(ReviewVo vo,Model model) {
		
		String r_ip = request.getRemoteAddr(); 
		vo.setR_ip(r_ip);

		int res = review_service.insert(vo);

		model.addAttribute("c_idx", vo.getC_idx() );
		
		return "redirect:cafe_detail.do";

	}
	
	//리뷰수정폼불러오기=========================================
	@RequestMapping("/review_modify_form.do")
	public String review_modify_form(int r_idx,Model model) {
		
		ReviewVo vo = review_service.selectOne(r_idx);
		
		String r_cot = vo.getR_cot().replaceAll("<br>", "\n");
		vo.setR_cot(r_cot);
		
		model.addAttribute("vo", vo);
		
		return "/content/cafe/review_form";
	}
	
	//리뷰수정하기===============================================
	@RequestMapping("/review_modify.do")
	public String review_modify(ReviewVo vo,Model model) {
	
		String r_ip = request.getRemoteAddr();
		vo.setR_ip(r_ip);
		
		int res = review_service.update(vo);
		
		return "redirect:mypage.do";
	}
	
	//삭제하기===================================================
	@RequestMapping("/review_delete.do")
	public String review_delete(int r_idx) {
		
		ReviewVo vo = review_service.selectOne(r_idx);
		
		int res = review_service.delete(r_idx);
		
		if(vo.getM_id().equals("admin")) {
			return "redirect:admin_mypage.do";
		}else
			return "redirect:mypage.do";
		
	}
	
}
