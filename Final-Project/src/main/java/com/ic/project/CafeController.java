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


	//����ȭ��ҷ�����=====================================================
	@RequestMapping("/Home.do")
	public String Home(Model model) {
		
		session.removeAttribute("show");

		List<CafeVo> list = cafe_service.selectList();

		model.addAttribute("list",list);
		
		return "Homepage";		
	}
	
	//ī�װ��� ����Ʈ�� �Ѿ��==============================================
	@RequestMapping("/cafe_select.do")
	public String category(String local, 
							String tag,
							String page,
						   Model model){
		
		 //����¡ ó���ϱ� 
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
	    
		//��ȸ��ó���ϱ� 
		session.removeAttribute("show");
		
		//��ü����Ʈ���� 5���� ������ ������ ó��
		List<CafeVo> list = cafe_service.selectList();
		int page_su = (int) Math.ceil(list.size()/5.0);
		
		//ī������+�������� �ѹ��� ��������
		List<CafeVo> list_count = cafe_service.cafe_select(map);
		
		model.addAttribute("list_count", list_count);
		model.addAttribute("page_su", page_su);
	 
		return "/content/cafe/cafe_list";
	}
	
	
	//ī�� �󼼳��� ���� ======================================================
	@RequestMapping("/cafe_detail.do")
	public String detail(int c_idx, Model model) {
		
		Object show = session.getAttribute("show");
		
		//��ȸ�� ���� �ڵ�
		if(show==null) {
		     int res= cafe_service.updateCount(c_idx);
		     //���ǿ� ��´� 
		     session.setAttribute("show",true);
		}
		
		//�ϳ��� ī������������
		CafeVo vo = cafe_service.selectOne(c_idx);
		//���䳻�� ��������
		List<ReviewVo> r_list = review_service.selectList(c_idx);
		//��ü�����
		ReviewVo review_vo = review_service.selectOne_full(c_idx);
		
		model.addAttribute("vo", vo);
		model.addAttribute("r_list",r_list);
		model.addAttribute("review_vo", review_vo);
		
		return "/content/cafe/cafe_detail";
	}
	
	//ī���� ������ ======================================================
	@RequestMapping("/cafe_insert_form.do")
	public String cafe_insert_form() {
		
		return "/content/mypage/cafeWrite";
	}
	

	//������ ī�� ����ϱ� ======================================================
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
				//���ε�� ���ϸ�
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
	
	//������ ī����������� ======================================================
	@RequestMapping("/cafe_modify_form.do")
	public String cafe_modify_form(int c_idx, Model model) {
		
		CafeVo vo = cafe_service.selectOne(c_idx);
		
		model.addAttribute("vo",vo);
		
		return "/content/mypage/cafeEdit";
	}
	
	//������ ī������ϱ�(����)======================================================
	@RequestMapping("/cafe_modify.do")
	public String cafe_modify(CafeVo vo, Model model) {

		int res = cafe_service.update(vo);
		
		return "redirect:admin_mypage.do";
	}
	
	//ī�����������ϱ�======================================================
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
