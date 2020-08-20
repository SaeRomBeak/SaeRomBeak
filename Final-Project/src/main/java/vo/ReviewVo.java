package vo;

public class ReviewVo {

	int r_idx, m_idx, c_idx;
	String r_cot, r_pwd, r_ip, r_regdate, r_grade,
			m_id, m_name, m_photo;

	double avg;
	int total, bad, notBad, soso, likes, god;

	String image;

	public ReviewVo() {
		// TODO Auto-generated constructor stub
	}

	public ReviewVo(int m_idx, int c_idx, String r_grade, String r_cot, String r_pwd, String r_ip) {
		super();
		this.m_idx = m_idx;
		this.c_idx = c_idx;
		this.r_cot = r_cot;
		this.r_pwd = r_pwd;
		this.r_grade = r_grade;
		this.r_ip = r_ip;
	}

	
	 public String getImage() { 
		  switch (r_grade) { 
		  case "1" : image="bad"; break; 
		  case "2" : image="notbad"; break; 
		  case "3" : image="soso"; break; 
		  case "4" : image="likes"; break; 
		  case"5" : image="god"; break; 
		  }
	return image; }
	
	 
	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getR_idx() {
		return r_idx;
	}

	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}

	public int getM_idx() {
		return m_idx;
	}

	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}

	public int getC_idx() {
		return c_idx;
	}

	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}

	public String getR_cot() {
		return r_cot;
	}

	public void setR_cot(String r_cot) {
		this.r_cot = r_cot;
	}

	public String getR_pwd() {
		return r_pwd;
	}

	public void setR_pwd(String r_pwd) {
		this.r_pwd = r_pwd;
	}

	public String getR_ip() {
		return r_ip;
	}

	public void setR_ip(String r_ip) {
		this.r_ip = r_ip;
	}

	public String getR_regdate() {
		return r_regdate;
	}

	public void setR_regdate(String r_regdate) {
		this.r_regdate = r_regdate;
	}

	public String getR_grade() {
		return r_grade;
	}

	public void setR_grade(String r_grade) {
		this.r_grade = r_grade;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_photo() {
		return m_photo;
	}

	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public int getNotBad() {
		return notBad;
	}

	public void setNotBad(int notBad) {
		this.notBad = notBad;
	}

	public int getSoso() {
		return soso;
	}

	public void setSoso(int soso) {
		this.soso = soso;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getGod() {
		return god;
	}

	public void setGod(int god) {
		this.god = god;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
