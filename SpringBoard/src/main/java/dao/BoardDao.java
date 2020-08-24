package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVo;

public class BoardDao {

	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//��� ��������===========================================================
	public List<BoardVo> selectList(){
		return sqlSession.selectList("board.board_list");
	}

	//��������/���Ǻ�==========================================================
	public List<BoardVo> selectList(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.board_condition_list",map);
	}
	
	//idx�� �ش�Ǵ� �Խù� ���� 1��================================================
	public BoardVo selectOne(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.board_one", idx);
	}

	//�Խñ۵���ϱ�===========================================================
	public int insert(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.board_insert",vo);
	}

	//��ȸ�������ϱ�===========================================================
	public int update_readhit(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_readhit",idx);
	}

	//����� step(�׷�ۼ���)�����ϱ�==============================================
	public int update_step(BoardVo baseVo) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_step",baseVo);
	}

	//����ۼ��ϱ�=============================================================
	public int reply(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.board_reply",vo);
	}

	//�Խñۻ����ϱ�===========================================================
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_delete",idx);
	}

	//�Խñۼ���==============================================================
	public int update(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update",vo);
	}

	//��ü�Խñ۰���==========================================================
	public int selectRowTotal(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.board_condition_row_total",map);
	}

	
	
}




