package com.ktdsuniversity.admin.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.board.vo.ArticleVO;
import com.ktdsuniversity.admin.board.vo.ArticleFileVO;

public interface BoardController {
	
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView selectArticle(@ModelAttribute("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView selectArticleFile(@ModelAttribute("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeArticle(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	int removeCheckedArticles(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modArticle(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception; 
	ModelAndView addArticleForm(HttpServletRequest request, HttpServletResponse response) throws Exception; 
	ModelAndView modArticleForm(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception; 
	ModelAndView listBySearchArticles(@RequestParam("searchType") String searchType, @RequestParam("searchText")String searchText, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/*
	 * public ModelAndView viewArticle(@RequestParam("id") int id,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception;
	 * 
	 */

}