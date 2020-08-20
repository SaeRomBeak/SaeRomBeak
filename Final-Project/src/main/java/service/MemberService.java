package service;

import java.util.List;
import java.util.Map;

import vo.CafeVo;
import vo.MemberVo;

public interface MemberService {

	public List<MemberVo> selectList();		//ȸ�����
	public int member_count();				//���ð����� ���
	public MemberVo selectOne(String m_id);	//�� ���̵� ���� ���� �����ϱ� 
	public int insert(MemberVo vo);			//ȸ������ �߰��ϱ� 
	public MemberVo selectOne(int m_idx);	//idx�� ���� ������ ������  (������ �������)
	public int update(MemberVo vo);			//ȸ������ �����ϱ�
	public int delete(int m_idx);			//ȸ������ �����ϱ� 
	public int image_update(MemberVo vo);	//�����ʻ�������
}
