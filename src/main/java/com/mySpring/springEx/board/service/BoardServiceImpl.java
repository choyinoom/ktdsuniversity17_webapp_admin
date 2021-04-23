package com.mySpring.springEx.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.board.dao.BoardDAO;
import com.mySpring.springEx.board.vo.ArticleVO;
import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.board.vo.ArticleFileVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDAO;

	// 공지사항 리스트 출력
	public List<ArticleVO> listArticles() throws Exception {
		List<ArticleVO> articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}
	
	// 공지인것만 리스트 출력
		public List<ArticleVO> listImptArticles() throws Exception {
			List<ArticleVO> articlesList = boardDAO.selectImptArticlesList();
			return articlesList;
		}

	// 공지사항 글 선택 
	@Override
	public ArticleVO selectArticle(int id) throws DataAccessException {
		return boardDAO.selectArticle(id);
	}
	
	// 공지사항 파일 선택 
	@Override
	public ArticleFileVO selectArticleFile(int id) throws DataAccessException {
		return boardDAO.selectArticleFile(id);
	}
	
	 // 공지사항 글 추가
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		return boardDAO.insertNewArticle(articleMap);
		
	}
	
	// 공지사항 글에 파일 추가
	@Override
	public int addNewArticleFile(Map<String, Object> articleMap) {
		int id = boardDAO.selectNewArticleId();
		articleMap.put("artId", id);
		return boardDAO.insertNewArticleFile(articleMap);
	}
	
	// 공지사항 글 삭제
	 @Override 
	 public int removeArticle(int id) throws Exception {
		 return boardDAO.deleteArticle(id);
	 }
	 
	// 공지사항 글 수정
	@Override 
	public int modArticle(Map<String, Object> articleMap) throws Exception {
		return boardDAO.updateArticle(articleMap); 
	}
	
	// 공지사항 파일 수정
	@Override 
	public int modArticleFile(Map<String, Object> articleMap) throws Exception {
		return boardDAO.updateArticleFile(articleMap); 
	}
	
	// 제목 검색
	@Override
	public List listBySearchArticles(String searchType, String searchText) throws DataAccessException {
		List articlesBySearchList = null;
		articlesBySearchList = boardDAO.selectBySearchArticlesList(searchType, searchText);
		return articlesBySearchList;
	}
	
	//기준 나누는 메서드 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return boardDAO.listCriteria(criteria);
	}
	
	//검색과 기준에 의해 리스트 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List articlesCriteriaBySearch = null;
		articlesCriteriaBySearch = boardDAO.selectCriteriaBySearch(criteria);
		return articlesCriteriaBySearch;
	}
	 
}
	
	
	/*
	 * @Override public Map viewArticle(int id) throws Exception { Map articleMap =
	 * new HashMap(); ArticleVO articleVO = boardDAO.selectArticle(id);
	 * List<ImageVO> imageFileList = boardDAO.selectImageFileList(id);
	 * articleMap.put("article", articleVO); articleMap.put("imageFileList",
	 * imageFileList); return articleMap; }
	 */

	// �뜝�룞�삕�뜝�룞�삕 �뜝�떛諭꾩삕�뜝�룞�삕 �뜝�뙥怨ㅼ삕�뜝�떦源띿삕
	/*
	 * @Override public int addNewArticle(Map articleMap) throws Exception{ int
	 * articleNO = boardDAO.insertNewArticle(articleMap);
	 * articleMap.put("articleNO", articleNO); boardDAO.insertNewImage(articleMap);
	 * return articleNO; }
	 */

	// �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛源띿삕

	/*
	 * //�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떛源띿삕
	 * 
	 * @Override public ArticleVO viewArticle(int articleNO) throws Exception {
	 * ArticleVO articleVO = boardDAO.selectArticle(articleNO); return articleVO; }
	 */




