package service;

import java.util.List;

import vo.ReviewVo;

public interface ReviewService {

	public List<ReviewVo> selectList();				//�����ڿ� ��ȸ
	public int insert(ReviewVo vo);					//�߰���
	public List<ReviewVo> selectList(int c_idx);	//ī��idx�� �ش��ϴ� ���� ������ 
	public List<ReviewVo> selectList_m(int m_idx);	//���� �� ���丮��Ʈ
	public ReviewVo selectOne(int r_idx);			//�����ѰǴ� ���� ������ 
	public int delete(int r_idx);					//�������
	public int update(ReviewVo vo);					//�������
	public int review_count(int m_idx);			//���θ�������ϱ�
	public ReviewVo selectOne_full(int c_idx);		//��ü���䱸�ϱ�
	public int review_count();						//���� �ۼ��� ����� ���ϱ�

}
