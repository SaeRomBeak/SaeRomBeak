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
	
	//목록 가져오기===========================================================
	public List<BoardVo> selectList(){
		return sqlSession.selectList("board.board_list");
	}

	//페이지별/조건별==========================================================
	public List<BoardVo> selectList(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.board_condition_list",map);
	}
	
	//idx에 해당되는 게시물 정보 1건================================================
	public BoardVo selectOne(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.board_one", idx);
	}

	//게시글등록하기===========================================================
	public int insert(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.board_insert",vo);
	}

	//조회수증가하기===========================================================
	public int update_readhit(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_readhit",idx);
	}

	//답글의 step(그룹글순서)증가하기==============================================
	public int update_step(BoardVo baseVo) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_step",baseVo);
	}

	//답글작성하기=============================================================
	public int reply(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.board_reply",vo);
	}

	//게시글삭제하기===========================================================
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_delete",idx);
	}

	//게시글수정==============================================================
	public int update(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update",vo);
	}

	//전체게시글개수==========================================================
	public int selectRowTotal(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.board_condition_row_total",map);
	}

	
	
}




