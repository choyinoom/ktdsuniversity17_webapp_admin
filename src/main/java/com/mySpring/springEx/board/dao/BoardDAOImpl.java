package com.mySpring.springEx.board.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.board.vo.ArticleVO;
import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.board.vo.ArticleFileVO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;

	// 공지사항 리스트
	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}
	
	// 공지사항 리스트
	@Override
	public List selectImptArticlesList() throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectImptArticlesList");
		return articlesList;
	}

	// 공지사항 선택
	@Override
	public ArticleVO selectArticle(int id) throws DataAccessException {
		ArticleVO vo = sqlSession.selectOne("mapper.board.selectArticle", id);
		return vo;
	}
	
	// 공지사항 파일 선택
	@Override
	public ArticleFileVO selectArticleFile(int id) throws DataAccessException {
		ArticleFileVO filevo = sqlSession.selectOne("mapper.board.selectArticleFile", id);
		return filevo;
	}
	
	// 공지사항 추가
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {

			int id = selectNewArticleId();
			articleMap.put("id", id);
			sqlSession.insert("mapper.board.insertNewArticle",articleMap);
			return id;
	}
    
	// 공지사항 파일 추가
	@Override
	public int insertNewArticleFile(Map articleMap) throws DataAccessException {
		return sqlSession.insert("mapper.board.insertNewArticleFile", articleMap);
	}
	
	// 공지사항 글 삭제
	@Override 
	public int deleteArticle(int id) throws DataAccessException {
		 int result = sqlSession.delete("mapper.board.deleteArticle", id); 
		 System.out.println(result);
		 return result;
	}
	
	// 공지사항 글 수정
	 @Override 
	 public int updateArticle(Map articleMap) throws DataAccessException { 
		 return sqlSession.update("mapper.board.updateArticle", articleMap); 
	 }
	 
	// 공지사항 파일 수정
	 @Override 
	 public int updateArticleFile(Map articleMap) throws DataAccessException { 
		 return sqlSession.update("mapper.board.updateArticleFile", articleMap); 
	 }
	
	@Override
	public List selectArticleFileList(int artId) {
		List<ArticleFileVO> articleFileList = null	;
		articleFileList = sqlSession.selectList("mapper.board.selectImageFileList",artId);
		return articleFileList;
	}
	
	// search
	@Override
	public List selectBySearchArticlesList(String searchType, String searchText) throws DataAccessException {
		List<ArticleVO> articlesBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		articlesBySearchList = sqlSession.selectList("mapper.board.selectBySearchArticlesList", mapSearch);
		return articlesBySearchList;
	}
	
	@Override
	public int selectNewArticleId() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleId");
	}
	
	@Override
	public int selectNewArticleFileId() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleFileId");
	}
	
	//페이징 리스트 뽑아오기 메서드
	@Override
	public List<ArticleVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.board.selectArticleListByPaging", page);
	}
		
	// 페이지 기준 설정 메서드
	@Override
	public List<ArticleVO> listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.board.listCriteria", criteria);
	}
		
	//criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.board.selectCriteriaBySearchArticleList", criteria);
	}
	
	//占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占싸듸옙
	/*
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		int articleNO = (Integer)articleMap.get("articleNO");
		int imageFileNO = selectNewImageFileNO();
		for(ImageVO imageVO : imageFileList){
			imageVO.setImageFileNO(++imageFileNO);
			imageVO.setArticleNO(articleNO);
		}
		sqlSession.insert("mapper.board.insertNewImage",imageFileList);
	}
	
   */
	
	/*
	 * @Override public List selectImageFileList(int id) throws DataAccessException
	 * { List<ImageVO> imageFileList = null; imageFileList =
	 * sqlSession.selectList("mapper.board.selectImageFileList",id); return
	 * imageFileList; }
	 */
	
	/*
	 * private int selectNewImageFileNO() throws DataAccessException { return
	 * sqlSession.selectOne("mapper.board.selectNewImageFileNO"); }
	 */

}
