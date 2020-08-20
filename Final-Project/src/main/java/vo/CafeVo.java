package vo;

public class CafeVo {

	int c_idx,c_hit;
	String c_name, c_addr, c_tel, c_price, c_park,
		   c_time,c_hday, c_url, c_map,
		   c_menu1,c_menu2,c_menu3,c_menu4,c_tag,c_regdate,
		   c_photo1,c_photo2,c_photo3,c_photo4, c_photo5,    
		   local;
			
	String [] c_photo_array = new String[5];
	
	
	//vo는 한건 list는 전부(for문)
	ReviewVo review_list;
	
	
	public CafeVo() {
		// TODO Auto-generated constructor stub
	}

	public int getC_hit() {
		return c_hit;
	}

	public void setC_hit(int c_hit) {
		this.c_hit = c_hit;
	}


	//추가
	public CafeVo(String c_name, String c_addr, String c_tel, String c_price, String c_park, String c_time,
			String c_hday, String c_url, String c_map, String c_menu1, String c_menu2, String c_menu3,
			String c_menu4, String c_tag, String c_photo1,String c_photo2,String c_photo3,String c_photo4,String c_photo5) {
		super();
		this.c_name = c_name;
		this.c_addr = c_addr;
		this.c_tel = c_tel;
		this.c_price = c_price;
		this.c_park = c_park;
		this.c_time = c_time;
		this.c_hday = c_hday;
		this.c_url = c_url;
		this.c_map = c_map;
		this.c_menu1 = c_menu1;
		this.c_menu2 = c_menu2;
		this.c_menu3 = c_menu3;
		this.c_menu4 = c_menu4;
		this.c_photo1 = c_photo1;
		this.c_photo2 = c_photo2;
		this.c_photo3 = c_photo3;
		this.c_photo4 = c_photo4;
		this.c_photo5 = c_photo5;
		this.c_tag = c_tag;
	}


	public ReviewVo getReview_list() {
		return review_list;
	}


	public void setReview_list(ReviewVo review_list) {
		this.review_list = review_list;
	}



	//수정하기
	public CafeVo(int c_idx, String c_name, String c_addr, String c_tel, String c_price, String c_park, String c_time, String c_hday,
			String c_url, String c_map,String c_menu1, String c_menu2, String c_menu3, String c_menu4,
			String c_tag) {
		super();
		this.c_idx = c_idx;
		this.c_name = c_name;
		this.c_addr = c_addr;
		this.c_tel = c_tel;
		this.c_price = c_price;
		this.c_park = c_park;
		this.c_time = c_time;
		this.c_hday = c_hday;
		this.c_url = c_url;
		this.c_map = c_map;
		this.c_menu1 = c_menu1;
		this.c_menu2 = c_menu2;
		this.c_menu3 = c_menu3;
		this.c_menu4 = c_menu4;
		this.c_tag = c_tag;
	}




	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_addr() {
		return c_addr;
	}
	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	public String getC_price() {
		return c_price;
	}
	public void setC_price(String c_price) {
		this.c_price = c_price;
	}
	public String getC_park() {
		return c_park;
	}
	public void setC_park(String c_park) {
		this.c_park = c_park;
	}
	public String getC_hday() {
		return c_hday;
	}
	public void setC_hday(String c_hday) {
		this.c_hday = c_hday;
	}
	public String getC_url() {
		return c_url;
	}
	public void setC_url(String c_url) {
		this.c_url = c_url;
	}
	public String getC_map() {
		return c_map;
	}
	public void setC_map(String c_map) {
		this.c_map = c_map;
	}
	
	public String getC_menu1() {
		return c_menu1;
	}
	public void setC_menu1(String c_menu1) {
		this.c_menu1 = c_menu1;
	}
	public String getC_menu2() {
		return c_menu2;
	}
	public void setC_menu2(String c_menu2) {
		this.c_menu2 = c_menu2;
	}
	public String getC_menu3() {
		return c_menu3;
	}
	public void setC_menu3(String c_menu3) {
		this.c_menu3 = c_menu3;
	}
	public String getC_menu4() {
		return c_menu4;
	}
	public void setC_menu4(String c_menu4) {
		this.c_menu4 = c_menu4;
	}
	public String getC_tag() {
		return c_tag;
	}
	public void setC_tag(String c_tag) {
		this.c_tag = c_tag;
	}

	public String getC_time() {
		return c_time;
	}

	public void setC_time(String c_time) {
		this.c_time = c_time;
	}

	public String getC_regdate() {
		return c_regdate;
	}

	public void setC_regdate(String c_regdate) {
		this.c_regdate = c_regdate;
	}

	public String getC_photo1() {
		return c_photo1;
	}

	public void setC_photo1(String c_photo1) {
		this.c_photo1 = c_photo1;
	}

	public String getC_photo2() {
		return c_photo2;
	}

	public void setC_photo2(String c_photo2) {
		this.c_photo2 = c_photo2;
	}

	public String getC_photo3() {
		return c_photo3;
	}

	public void setC_photo3(String c_photo3) {
		this.c_photo3 = c_photo3;
	}

	public String getC_photo4() {
		return c_photo4;
	}

	public void setC_photo4(String c_photo4) {
		this.c_photo4 = c_photo4;
	}


	public String getC_photo5() {
		return c_photo5;
	}

	public void setC_photo5(String c_photo5) {
		this.c_photo5 = c_photo5;
	}
	
	
}
