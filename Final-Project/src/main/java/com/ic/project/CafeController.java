package com.ic.project;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dao.CafeDao;
import dao.ReviewDao;
import service.CafeService;
import service.ReviewService;
import vo.CafeVo;
import vo.ReviewVo;

@Controller
public class CafeController {

	@Autowired
	CafeService cafe_service;
	@Autowired
	ReviewService review_service;
	
	@Autowired 
	ServletContext application;	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;


	//메인화면불러오기=====================================================
	@RequestMapping("/Home.do")
	public String Home(Model model) {
		
		session.removeAttribute("show");

		List<CafeVo> list = cafe_service.selectList();

		model.addAttribute("list",list);
		
		return "Homepage";		
	}
	
	//카테고리별 리스트로 넘어가기==============================================
	@RequestMapping("/cafe_select.do")
	public String category(String local, 
							String tag,
							String page,
						   Model model){
		
		 //페이징 처리하기 
		 int nPage = Integer.parseInt(page);
	        
	     int start = (nPage-1)*5 + 1;
	     int end   = start+5 - 1;
	        
	     Map map = new HashMap();
		 map.put("start", start);
		 map.put("end", end);
			
		 if (local!=null) {
			map.put("local", local);
		 }
		 if (tag!=null) {
			map.put("local", local);
		 }	
	    
		//조회수처리하기 
		session.removeAttribute("show");
		
		//전체리스트에서 5개씩 나눠서 페이지 처리
		List<CafeVo> list = cafe_service.selectList();
		int page_su = (int) Math.ceil(list.size()/5.0);
		
		//카페정보+리뷰정보 한번에 가져오기
		List<CafeVo> list_count = cafe_service.cafe_select(map);
		
		model.addAttribute("list_count", list_count);
		model.addAttribute("page_su", page_su);
	 
		return "/content/cafe/cafe_list";
	}
	
	
	//카페 상세내용 보기 ======================================================
	@RequestMapping("/cafe_detail.do")
	public String detail(int c_idx, Model model) {
		
		Object show = session.getAttribute("show");
		
		//조회수 증가 코드
		if(show==null) {
		     int res= cafe_service.updateCount(c_idx);
		     //세션에 담는다 
		     session.setAttribute("show",true);
		}
		
		//하나의 카페정보얻어오기
		CafeVo vo = cafe_service.selectOne(c_idx);
		//리뷰내용 가져오기
		List<ReviewVo> r_list = review_service.selectList(c_idx);
		//전체리뷰수
		ReviewVo review_vo = review_service.selectOne_full(c_idx);
		
		model.addAttribute("vo", vo);
		model.addAttribute("r_list",r_list);
		model.addAttribute("review_vo", review_vo);
		
		return "/content/cafe/cafe_detail";
	}
	
	//카페등록 폼띄우기 ======================================================
	@RequestMapping("/cafe_insert_form.do")
	public String cafe_insert_form() {
		
		return "/content/mypage/cafeWrite";
	}
	

	//관리자 카페 등록하기 ======================================================
	@RequestMapping("/cafe_insert.do")
	public String cafe_insert(CafeVo vo,
							  @RequestParam("photo") MultipartFile[] photo_array,
							  Model model) throws Exception {
		
		String web_path = "/img/cafe_upload/";
		String abs_path = application.getRealPath(web_path);
		
		for(int i=0; i<photo_array.length; i++) {
			
			MultipartFile photo = photo_array[i];
			
			String c_photo ="no_file";
			
			if(!photo.isEmpty()) {
				//업로드된 파일명
				c_photo = photo.getOriginalFilename();
				
				File f = new File(abs_path,c_photo);

				if(f.exists()) {
					long t = System.currentTimeMillis();
					c_photo = String.format("%d_%s",t,c_photo);
					f = new File(abs_path,c_photo);
				}
				photo.transferTo(f);
			}
		
			if(i==0) 
				vo.setC_photo1(c_photo);
			else if(i==1) 
				vo.setC_photo2(c_photo);
			else if(i==2) 
				vo.setC_photo3(c_photo);
			else if(i==3) 
				vo.setC_photo4(c_photo);
			else if(i==4) 
				vo.setC_photo5(c_photo);
		}

		int res = cafe_service.insert(vo);
		model.addAttribute("vo",vo);

		return "redirect:admin_mypage.do";
	}
	
	//관리자 카페수정폼띄우기 ======================================================
	@RequestMapping("/cafe_modify_form.do")
	public String cafe_modify_form(int c_idx, Model model) {
		
		CafeVo vo = cafe_service.selectOne(c_idx);
		
		model.addAttribute("vo",vo);
		
		return "/content/mypage/cafeEdit";
	}
	
	//관리자 카페수정하기(내용)======================================================
	@RequestMapping("/cafe_modify.do")
	public String cafe_modify(CafeVo vo, Model model) {

		int res = cafe_service.update(vo);
		
		return "redirect:admin_mypage.do";
	}
	
	//카페정보삭제하기======================================================
	@RequestMapping("/cafe_delete.do")
	public String cafe_delete(int c_idx) {
		
		CafeVo vo = cafe_service.selectOne(c_idx);
		
		String web_path = "/img/cafe_upload/";
		
		String abs_path = application.getRealPath(web_path);
		
		File file = new File(abs_path, vo.getC_photo1());
		file.delete();
		
		file = new File(abs_path, vo.getC_photo2());
		file.delete();
		
		file = new File(abs_path, vo.getC_photo3());
		file.delete();
		
		file = new File(abs_path, vo.getC_photo4());
		file.delete();
		
		file = new File(abs_path, vo.getC_photo5());
		file.delete();
		
		
		int res = cafe_service.delete(c_idx);
		
		return "redirect:admin_mypage.do";
	}
	
}
