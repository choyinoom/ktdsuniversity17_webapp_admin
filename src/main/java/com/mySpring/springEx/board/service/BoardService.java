package com.mySpring.springEx.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.board.vo.ArticleVO;
import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.board.vo.ArticleFileVO;

public interface BoardService {
	
	public List<ArticleVO> listArticles() throws Exception;
	public List<ArticleVO> listImptArticles() throws Exception;
	public ArticleVO selectArticle(int id) throws DataAccessException;
	public ArticleFileVO selectArticleFile(int id) throws DataAccessException;
	public int addNewArticle(Map articleMap) throws Exception;
	public int addNewArticleFile(Map<String, Object> articleMap);
	public int removeArticle(int id) throws Exception;
	public int modArticle(Map<String, Object> articleMap) throws Exception; 
	public int modArticleFile(Map<String, Object> articleMap) throws Exception;
	public List listBySearchArticles(String searchType, String searchText) throws DataAccessException;
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;
	
	/*public ArticleVO viewArticle(int articleNO) throws Exception;
		public Map viewArticle(int id) throws Exception;
	 * 
	 */
}