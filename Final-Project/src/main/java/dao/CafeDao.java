package dao;

import java.util.List;
import java.util.Map;

import vo.CafeVo;

public interface CafeDao {

	public List<CafeVo> selectList();			//ī������ȸ 
	public List<CafeVo> cafe_select(Map map);	//5���� �̾ƿ���+����
	public int insert(CafeVo vo);				//�߰���
	public CafeVo selectOne(int c_idx);			//�����ϳ� �����ϱ�
	public int update(CafeVo vo);				//ī����������
	public int delete(int c_idx);				//ī����������
	public int updateCount(int c_idx);			//ī����ȸ��
	public int cafe_count();					//���� ����� ī�� �� 
	public int selectRowTotal();				//����¡ó��
	public int update_image(CafeVo vo);			//�̹�������
	
}