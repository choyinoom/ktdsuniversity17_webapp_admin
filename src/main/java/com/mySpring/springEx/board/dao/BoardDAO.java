package com.mySpring.springEx.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.board.vo.ArticleVO;
import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.board.vo.ArticleFileVO;


public interface BoardDAO {
	
	public List selectAllArticlesList() throws DataAccessException;
	public List selectImptArticlesList() throws DataAccessException;
	public ArticleVO selectArticle(int id) throws DataAccessException;
	public ArticleFileVO selectArticleFile(int id) throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public int insertNewArticleFile(Map articleMap);
	public int selectNewArticleId() throws DataAccessException;
	public int selectNewArticleFileId() throws DataAccessException;
	public int deleteArticle(int id) throws DataAccessException;
	public int updateArticle(Map articleMap) throws DataAccessException;
	public int updateArticleFile(Map articleMap) throws DataAccessException;
	public List selectArticleFileList(int artId) throws DataAccessException;
	public List selectBySearchArticlesList(String searchType, String searchText) throws DataAccessException;
	public List<ArticleVO> listPaging(int page) throws DataAccessException;
	public List<ArticleVO> listCriteria(Criteria criteria) throws DataAccessException;
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException;

}