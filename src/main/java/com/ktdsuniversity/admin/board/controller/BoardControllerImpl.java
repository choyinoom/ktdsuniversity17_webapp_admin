package com.ktdsuniversity.admin.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.ktdsuniversity.admin.board.service.BoardService;
import com.ktdsuniversity.admin.board.vo.ArticleVO;
import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.common.paging.PageMaker;
import com.ktdsuniversity.admin.member.vo.MemberVO;
import com.ktdsuniversity.admin.board.vo.ArticleFileVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {

	// 파일 업로드 절대경로
	private static final String ARTICLE_FILE_REPO = "/home/ubuntu/ktdsuniversity_webapp/resources/articleFile";
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	@Autowired
	private ArticleFileVO articleFileVO;

	@Override
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		String searchType = (String) request.getParameter("searchType");
		String searchText = (String) request.getParameter("searchText");
		int page = 0;
		// 받은 페이지 값이 있다면 (page 개수 설정)
		if (request.getParameter("page") != null) {
			page = Integer.parseInt((String) request.getParameter("page")); // page 변수에 값을 저장
		} else {
			page = 1; // 아니면 page 1로 기본 지정
		}

		int perPage = 0; // 리스트 개수 값 저장할 변수 생성

		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt((String) request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		} else {
			perPage = 10; // 기본 10개로 지정
		}

		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();

		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정
		
		// page 1 누를 때 and 게시판관리 누를때
		if (page == 1) {
			List imptArticlesList = boardService.listImptArticles(); // 중요(공지) 공지사항
			List articlesList = boardService.listArticles(); // 일반 공지사항
			
			/* 찾는 text(title에서)가 없다면 (perPage 없다면) and page 1 누를 때 */
			if (searchType == null && searchText == null) {
				criteria.setPage(page); // 페이지 설정
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수 설정
				articlesList = boardService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
				
				/* 위와 동일 */
			} else if (searchType == "" && searchText == "") {
				criteria.setPage(page); // 페이지 설정
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수 설정
				articlesList = boardService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
				
			} else  {
				
				imptArticlesList = boardService.listBySearchImptArticles(searchType, searchText);
				articlesList = boardService.listBySearchArticles(searchType, searchText);
				criteria.setPage(page); // page 설정
				criteria.setSearchText(searchText); // 검색 값 설정
				criteria.setSearchType(searchType); // 검색 유형 설정
				pageMaker.setTotalCount(articlesList.size()+imptArticlesList.size()); // 페이지 개수를 전체 리스트 크기로 설정
				articlesList = boardService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
				imptArticlesList = boardService.listCriteriaBySearchImpt(criteria);
				mav.addObject("searchText", searchText);
				mav.addObject("searchType", searchType);
				
			}
			mav.addObject("page", page);
			mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
			mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
			mav.addObject("impArticlesList", imptArticlesList);
			mav.addObject("articlesList", articlesList);
			return mav;

			// page 2 부터
		} else {
			List articlesList = boardService.listArticles(); // 일반 공지사항

			/* 찾는 text(title에서)가 있다면 (perPage 없다면) */
			if (searchType != null && searchText != null) {
				articlesList = boardService.listBySearchArticles(searchType, searchText);
				criteria.setPage(page); // page 설정
				criteria.setSearchText(searchText); // 검색 값 설정
				criteria.setSearchType(searchType); // 검색 유형 설정
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수를 전체 리스트 크기로 설정
				articlesList = boardService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
				mav.addObject("searchText", searchText);
				mav.addObject("searchType", searchType);

			} else /* 찾는 text(title에서)가 없다면 */ {
				criteria.setPage(page); // 페이지 설정
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수 설정
				articlesList = boardService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
			}
			mav.addObject("page",page);
			mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
			mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
			mav.addObject("articlesList", articlesList);
			return mav;
		}
	}

	// 기준에 의해 나눠진 리스트 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
	@RequestMapping(value = "/board/listCriteria.do", method = RequestMethod.GET)
	public String listCriteria(Model model, Criteria criteria) throws Exception {
		model.addAttribute("articles", boardService.listCriteria(criteria));
		return "/board/list_criteria";
	}

	// 페이지 개수가 어떻게 나눠지는지 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
	@RequestMapping(value = "/board/listPaging.do", method = RequestMethod.GET)
	public String listPaging(Model model, Criteria criteria) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(1000);

		model.addAttribute("articles", boardService.listCriteria(criteria));
		model.addAttribute("pageMaker", pageMaker);
		return "/board/list_paging";

	}

	// 공지사항 제목 선택하면 상세내용 나오기
	@Override
	@RequestMapping(value = "/board/selectArticle.do", method = RequestMethod.GET)
	public ModelAndView selectArticle(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String searchType = (String) request.getParameter("searchType"); 
		String searchText = (String) request.getParameter("searchText"); 
		int page = Integer.parseInt(request.getParameter("page")); // page 변수에 값을 저장
		int perPage = Integer.parseInt(request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		ArticleVO vo = boardService.selectArticle(id);
		ArticleFileVO filevo = boardService.selectArticleFile(id);		
		mv.addObject("page", page);
		mv.addObject("perPage", perPage); // 리스트 기준 값 보내기 
		mv.addObject("searchText", searchText);
		mv.addObject("searchType", searchType);
		mv.addObject("vo", vo);
		mv.addObject("filevo", filevo);
		return mv;
	}

	// 공지사항 파일 선택
	@Override
	@RequestMapping(value = "/board/selectArticleFile.do", method = RequestMethod.GET)
	public ModelAndView selectArticleFile(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		ArticleFileVO filevo = boardService.selectArticleFile(id);
		mv.addObject("filevo", filevo);
		return mv;
	}
	
	// 새로운 공지사항 등록
	@Override
	@RequestMapping(value = "/board/addNewArticle.do", method = RequestMethod.POST)
	public ModelAndView addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		String searchType = (String) request.getParameter("searchType");
		String searchText = URLEncoder.encode((String) request.getParameter("searchText"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page")); // page 변수에 값을 저장
		int perPage = Integer.parseInt(request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames(); // 폼에 전송한 파라미터들의 이름을 Enumeration 타입으로 반환, 폼에 있는 input 태그 중 file 속성이 아닌 모든 파라미터들의 이름을 반환
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		String name = upload(multipartRequest);
		articleMap.put("name", name);

		// article file칼럼 T or F 구현
		if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
			articleMap.put("file", 'T');
		} else {
			articleMap.put("file", 'F');
		}
		try {
			boardService.addNewArticle(articleMap);
			boardService.addNewArticleFile(articleMap);
			Object id = articleMap.get("artId");
			if (name != null && name.length() != 0) {
				File srcFile = new File(ARTICLE_FILE_REPO + "/" + "temp" + "/" + name);
				File destDir = new File(ARTICLE_FILE_REPO + "/" + id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_FILE_REPO + "/" + "temp" + "/" + name);
			srcFile.delete();
		}
		ModelAndView mav = new ModelAndView("redirect:/board/listArticles.do?page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage); //&page="+page+"  perPage="+perPage
		return mav;
	}	
	
	// 공지사항 글 추가하는 form
	@RequestMapping(value = "/board/addArticleForm.do", method = RequestMethod.GET)
	public ModelAndView addArticleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		String searchType = (String) request.getParameter("searchType");
		String searchText = (String) request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page")); // page 변수에 값을 저장
		int perPage = Integer.parseInt(request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage); // 리스트 기준 값 보내기 
		mv.addObject("searchText", searchText);
		mv.addObject("searchType", searchType);
		return mv;
	}
	
	// 공지사항 글 제거
	@Override
	@RequestMapping(value = "/board/removeArticle.do", method = RequestMethod.GET)
	public ModelAndView removeArticle(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception { 
		request.setCharacterEncoding("utf-8");
		String searchType = (String) request.getParameter("searchType");
		String searchText = URLEncoder.encode((String) request.getParameter("searchText"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page")); // page 변수에 값을 저장
		int perPage = Integer.parseInt(request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		 
		try {
			boardService.removeArticle(id);
			File destDir = new File(ARTICLE_FILE_REPO + "/" + id);
			FileUtils.deleteDirectory(destDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/board/listArticles.do?page="+page+"&searchType="+searchType+"&searchText="+searchText+"&perPage="+perPage);
		return mav;
	}

	// 공지사항글 체크박스로 제거
	@Override
	@ResponseBody
	@RequestMapping(value = "/board/removeCheckedArticles.do", method = RequestMethod.POST)
	public int removeCheckedArticles(String[] arr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			try {
				result = boardService.removeArticle(Integer.parseInt(arr[i]));
				File destDir = new File(ARTICLE_FILE_REPO + "/" + Integer.parseInt(arr[i]));
				FileUtils.deleteDirectory(destDir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 공지사항 글 수정
	@Override
	@RequestMapping(value = "/board/modArticle.do", method = RequestMethod.POST)
	public ModelAndView modArticle(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		multipartRequest.setCharacterEncoding("utf-8");
		String searchType = (String) request.getParameter("searchType");
		String searchText = URLEncoder.encode((String) request.getParameter("searchText"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page")); // page 변수에 값을 저장
		int perPage = Integer.parseInt(request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		Map<String, Object> articleMap = new HashMap<String, Object>();
		
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		
		String originalFile = (String) articleMap.get("name");
		String newName = upload(multipartRequest);
		articleMap.put("name", newName);
		if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
			articleMap.put("file", 'T');
		} else {
			articleMap.put("file", 'F');
		}
		String id = (String) articleMap.get("id");
		boardService.modArticle(articleMap);
		boardService.modArticleFile(articleMap);
		
		// 수정한 글의 파일 유무에 따라 T or F 바꿈
		if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
			articleMap.replace("file", 'T');
		} else {
			articleMap.replace("file", 'F');
		}
		// 원래 파일이 있었는데 수정하고 난 뒤 파일이 바뀌면 이전파일 삭제
		if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
			File srcFile = new File(ARTICLE_FILE_REPO + "/" + "temp" + "/" + newName);
			File destDir = new File(ARTICLE_FILE_REPO + "/" + id);
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			File oldFile = new File(ARTICLE_FILE_REPO + "/" + id + "/" + originalFile);
			oldFile.delete();

		} else { // 원래 파일 있었는데 수정하고 난 뒤 파일 없으면 이전 파일 삭제
			File srcFile = new File(ARTICLE_FILE_REPO + "/" + id + "/" + originalFile);
			srcFile.delete();
		}
		ModelAndView mav = new ModelAndView("redirect:/board/selectArticle.do?id="+id+"&page="+page+"&searchType="+searchType+"&searchText="+searchText+"&perPage="+perPage);
		return mav;
	}
	
	 // 수정하는 화면
	 @RequestMapping(value = "/board/modArticleForm.do", method = RequestMethod.GET	) 
	 public ModelAndView modArticleForm(@RequestParam int id, HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		String searchType = (String) request.getParameter("searchType");
		String searchText = (String) request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page")); // page 변수에 값을 저장
		int perPage = Integer.parseInt(request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		 request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		ArticleVO vo = boardService.selectArticle(id);
		ArticleFileVO filevo = boardService.selectArticleFile(id);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage); // 리스트 기준 값 보내기 
		mv.addObject("searchText", searchText);
		mv.addObject("searchType", searchType);
		mv.addObject("vo", vo);
		mv.addObject("filevo", filevo);
		return mv;
	 }

	// 검색기능 (제목)
	@Override
	@RequestMapping(value = "/board/listBySearch.do", method = RequestMethod.POST)
	public ModelAndView listBySearchArticles(@RequestParam("searchType") String searchType, @RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String) request.getAttribute("viewName");
		List impArticlesList = boardService.listBySearchArticles(searchType, searchText);
		List articlesList = boardService.listBySearchArticles(searchType, searchText);
		ModelAndView mav = new ModelAndView("/board/listArticles");
		mav.addObject("impArticlesList", impArticlesList);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	// upload 메소드
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String name = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			name = mFile.getOriginalFilename();
			File file = new File(ARTICLE_FILE_REPO + "/" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
					if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 이후 파일 생성
					}
				}
				mFile.transferTo(new File(ARTICLE_FILE_REPO + "/" + "temp" + "/" + name)); // 임시로 저장된 multipartFile을 실제 파일로 전송
			}
		}
		return name;
	}
}