package dao;

import java.util.List;

import vo.MemberVo;

public interface MemberDao {
	
	public List<MemberVo> selectList();		//ȸ�����
	public int member_count();				//���ð����� ���
	public MemberVo selectOne(String m_id);	//�� ���̵� ���� ���� �����ϱ� 
	public int insert(MemberVo vo);			//ȸ������ �߰��ϱ� 
	public MemberVo selectOne(int m_idx);	//idx�� ���� ������ ������  (������ �������)
	public int update(MemberVo vo);			//ȸ������ �����ϱ�
	public int delete(int m_idx);			//ȸ������ �����ϱ� 
	public int image_update(MemberVo vo);	//�����ʻ�������
}