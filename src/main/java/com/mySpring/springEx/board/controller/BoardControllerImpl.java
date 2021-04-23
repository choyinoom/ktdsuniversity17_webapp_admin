package com.mySpring.springEx.board.controller;

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
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.mySpring.springEx.board.service.BoardService;
import com.mySpring.springEx.board.vo.ArticleVO;
import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.common.paging.PageMaker;
import com.mySpring.springEx.board.vo.ArticleFileVO;


@Controller("boardController")
public class BoardControllerImpl implements BoardController {

	// 주소 박아놓음
	private static final String ARTICLE_FILE_REPO = "C:\\uploadtest";
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	@Autowired
	private ArticleFileVO articleFileVO;

	@Override
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
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
		
		// page 1에만 중요도 : 공지 띄움 / 나머지는 일반 
		if (page == 1) {

			List impArticlesList = boardService.listImptArticles(); // 중요한(공지) 공지사항
			List articlesList = boardService.listArticles(); // 일반 공지사항

			/* 찾는 text(title에서)가 있다면 (perPage 없다면) */
			if (searchType != null && searchText != null) {
				articlesList = boardService.listBySearchArticles(searchType, searchText);
				criteria.setPage(page); // page 설정
				criteria.setSearchText(searchText); // 검색 값 설정
				criteria.setSearchType(searchType); // 검색 유형 설정
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수를 전체 리스트 크기로 설정
				articlesList = boardService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
				System.out.println("page@@@" + page + "번호에 해당하는 리스트 크기" + articlesList.size());
				mav.addObject("searchText", searchText);
				mav.addObject("searchType", searchType);

			} else /* 찾는 text(title에서)가 없다면 */ {
				System.out.println("@@@@@@@@검색필터적용안됨요");
				System.out.println(articlesList.size()); // 전체 사이즈
				criteria.setPage(page); // 페이지 설정
				System.out.println(page);
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수 설정
				System.out.println("*******");

				articlesList = boardService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
			}
			
			mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
			mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
			mav.addObject("impArticlesList", impArticlesList);
			mav.addObject("articlesList", articlesList);
			return mav;

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
				System.out.println("page@@@" + page + "번호에 해당하는 리스트 크기" + articlesList.size());
				mav.addObject("searchText", searchText);
				mav.addObject("searchType", searchType);

			} else /* 찾는 text(title에서)가 없다면 */ {
				System.out.println(articlesList.size()); // 전체 사이즈
				criteria.setPage(page); // 페이지 설정
				System.out.println(page);
				pageMaker.setTotalCount(articlesList.size()); // 페이지 개수 설정

				articlesList = boardService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
			}
			
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
		 return"/board/list_criteria";
	 
	 }
	 
	 // 페이지 개수가 어떻게 나눠지는지 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
	 
	 @RequestMapping(value = "/board/listPaging.do", method = RequestMethod.GET)
	 public String listPaging(Model model, Criteria criteria) throws Exception {
	 
		 PageMaker pageMaker = new PageMaker(); pageMaker.setCriteria(criteria);
		 pageMaker.setTotalCount(1000);
	 
		 model.addAttribute("articles", boardService.listCriteria(criteria));
		 model.addAttribute("pageMaker", pageMaker); 
		 return "/board/list_paging";
	 
	 }

	// 공지사항 제목 선택하면 내용 나오기
	@Override
	@RequestMapping(value = "/board/selectArticle.do", method = RequestMethod.GET)
	public ModelAndView selectArticle(@RequestParam("id") int id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		ArticleVO vo = boardService.selectArticle(id);
		ArticleFileVO filevo = boardService.selectArticleFile(id);
		System.out.println(filevo);
		mv.addObject("vo", vo);
		mv.addObject("filevo", filevo);
		return mv;
	}

	// 공지사항 파일 선택
	@Override
	@RequestMapping(value = "/board/selectArticleFile.do", method = RequestMethod.GET)
	public ModelAndView selectArticleFile(@RequestParam("id") int id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		ArticleFileVO filevo = boardService.selectArticleFile(id);
		System.out.println(filevo);
		mv.addObject("filevo", filevo);
		return mv;
	}

	// 공지사항 글 추가 , 한 개 파일
	@Override
	@RequestMapping(value = "/board/addNewArticle.do", method = RequestMethod.POST)
	public ModelAndView addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames(); // 폼에 전송한 파라미터들의 이름을 Enumeration 타입으로 반환, 폼에 있는 input 태그
																// 중 file 속성이 아닌 모든 파라미터들의 이름을 반환
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		String name = upload(multipartRequest);
		/*
		 * HttpSession session = multipartRequest.getSession(); ManagerVO managerVO =
		 * (ManagerVO) session.getAttribute("manager"); String memId =
		 * managerVO.getId(); articleMap.put("id", memId);
		 */
		articleMap.put("name", name);

		// article file칼럼 T or F 구현
		if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
			articleMap.put("file", 'T');
		} else {
			articleMap.put("file", 'F');
		}
		System.out.println(articleMap);
		try {
			boardService.addNewArticle(articleMap);
			boardService.addNewArticleFile(articleMap);	
			Object id = articleMap.get("artId");
			System.out.println(ARTICLE_FILE_REPO);
			if (name != null && name.length() != 0) {
				File srcFile = new File(ARTICLE_FILE_REPO + "\\" + "temp" + "\\" + name);
				File destDir = new File(ARTICLE_FILE_REPO + "\\" + id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_FILE_REPO + "\\" + "temp" + "\\" + name);
			srcFile.delete();
		}
		ModelAndView mav = new ModelAndView("redirect:/board/listArticles.do");
		return mav;
	}

	// 공지사항 글 제거
	@Override
	@RequestMapping(value = "/board/removeArticle.do", method = RequestMethod.GET)
	public ModelAndView removeArticle(@RequestParam("id") int id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		try {
			boardService.removeArticle(id);
			File destDir = new File(ARTICLE_FILE_REPO + "\\" + id);
			FileUtils.deleteDirectory(destDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/board/listArticles.do");
		return mav;
	}

	// 공지사항글 체크박스로 제거
	@Override
	@ResponseBody
	@RequestMapping(value = "/board/removeCheckedArticles.do", method = RequestMethod.POST)
	public int removeCheckedArticles(String[] arr, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println(arr.length);
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			try {
				result = boardService.removeArticle(Integer.parseInt(arr[i]));
				File destDir = new File(ARTICLE_FILE_REPO + "\\" + Integer.parseInt(arr[i]));
				FileUtils.deleteDirectory(destDir);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("*");
			}
		}
		return result;
	}

	// 공지사항 글 수정
	@Override
	@RequestMapping(value = "/board/modArticle.do", method = RequestMethod.POST)
	public ModelAndView modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		String originalFile = (String) articleMap.get("name");
		System.out.println(")))))))))))))))))))))))))))))))))))))))))))))))))))))");
		System.out.println(originalFile);
		String name = upload(multipartRequest);
		articleMap.put("name", name);
		if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
			articleMap.put("file", 'T') ;
		} else {
			articleMap.put("file", 'F') ;
		}
		String id = (String) articleMap.get("id");	
		
		/*
		 * try { boardService.modArticle(articleMap);
		 * boardService.modArticleFile(articleMap);
		 * 
		 * // article file칼럼 T or F 구현 if (articleMap.get("name") != null && (((String)
		 * articleMap.get("name")).length()) != 0) { articleMap.put("file", 'T'); } else
		 * { articleMap.put("file", 'F'); }
		 */
		
			boardService.modArticle(articleMap);
			boardService.modArticleFile(articleMap);	
			if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
				articleMap.replace("file", 'T') ;
			} else {
				articleMap.replace("file", 'F') ;
			}
			// article file칼럼 T or F 구현
			if (articleMap.get("name") != null && (((String) articleMap.get("name")).length()) != 0) {
				articleMap.replace("file", 'T');
				System.out.println(name + "//" + id);
				File srcFile = new File(ARTICLE_FILE_REPO + "\\" + "temp" + "\\" + name);
				File destDir = new File(ARTICLE_FILE_REPO + "\\" + id);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				String originalFileName = (String) articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_FILE_REPO + "\\" + id + "\\" + originalFileName);
				oldFile.delete(); 
				
				} else {
				articleMap.replace("file", 'F');
				System.out.println(articleMap.get("name"));
				File srcFile = new File(ARTICLE_FILE_REPO + "\\" + id + "\\" + originalFile/*articleMap.get("name")*/);
				System.out.println(srcFile);
				srcFile.delete();	
				}
				
			
			ModelAndView mav = new ModelAndView("redirect:/board/listArticles.do");
			return mav;
	}

	/*
	 * try { boardService.modArticle(articleMap);
	 * boardService.modArticleFile(articleMap);
	 * 
	 * // article file칼럼 T or F 구현 if (articleMap.get("name") != null && (((String)
	 * articleMap.get("name")).length()) != 0) { articleMap.replace("file", 'T'); }
	 * else { articleMap.replace("file", 'F'); }
	 * 
	 * if (name != null && name.length() != 0) { System.out.println(name + "//" +
	 * id); File srcFile = new File(ARTICLE_FILE_REPO + "\\" + "temp" + "\\" +
	 * name); File destDir = new File(ARTICLE_FILE_REPO + "\\" + id);
	 * FileUtils.moveFileToDirectory(srcFile, destDir, true); String
	 * originalFileName = (String) articleMap.get("originalFileName"); File oldFile
	 * = new File(ARTICLE_FILE_REPO + "\\" + id + "\\" + originalFileName);
	 * oldFile.delete(); } } catch (Exception e) { if (articleMap.get("name") !=
	 * null && (((String) articleMap.get("name")).length()) != 0) {
	 * articleMap.replace("file", 'T'); } else { articleMap.replace("file", 'F'); }
	 * File srcFile = new File(ARTICLE_FILE_REPO + "\\" + "temp" + "\\" + name);
	 * srcFile.delete(); System.out.println("exeption"); } ModelAndView mav = new
	 * ModelAndView("redirect:/board/listArticles.do"); return mav; }
	 */
	

	
	@Override
	@RequestMapping(value = "/board/modArticleForm.do", method = RequestMethod.GET)
	public ModelAndView updateArticleForm(@RequestParam int id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		ArticleVO vo = boardService.selectArticle(id);
		ArticleFileVO filevo = boardService.selectArticleFile(id);
		mv.addObject("vo", vo);
		mv.addObject("filevo", filevo);
		return mv;
	}

	// 검색기능 (제목)
	@Override
	@RequestMapping(value = "/board/listBySearch.do", method = RequestMethod.POST)
	public ModelAndView listBySearchArticles(@RequestParam("searchType") String searchType,
			@RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		System.out.println(searchType);
		System.out.println(searchText);
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
			File file = new File(ARTICLE_FILE_REPO + "\\" + fileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // 경로상에 파일이 존재하지 않을 경우
					if (file.getParentFile().mkdirs()) { // 경로에 해당하는 디렉토리들을 생성
						file.createNewFile(); // 이후 파일 생성
					}
				}
				mFile.transferTo(new File(ARTICLE_FILE_REPO + "\\" + "temp" + "\\" + name)); // 임시로 저장된
																								// multipartFile을 실제 파일로
																								// 전송
			}
		}
		return name;
	}
	
	

	/*
	 * int result = boardService.insertNewArticle(articleVO); ModelAndView mav = new
	 * ModelAndView("redirect:/board/listArticles.do"); return mav; }
	 */

	/*
	 * @Override
	 * 
	 * @RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	 * public ModelAndView viewArticle(@RequestParam("id") int id,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception {
	 * String viewName = (String)request.getAttribute("viewName");
	 * articleVO=(ArticleVO) boardService.viewArticle(id).get("article");
	 * ModelAndView mav = new ModelAndView(); mav.setViewName(viewName);
	 * mav.addObject("article", articleVO); return mav; }
	 */

	/*
	 * @Override
	 * 
	 * @RequestMapping(value="/board/addNewArticle.do" ,method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ResponseEntity addNewArticle(MultipartHttpServletRequest
	 * multipartRequest, HttpServletResponse response) throws Exception {
	 * multipartRequest.setCharacterEncoding("utf-8"); Map<String,Object> articleMap
	 * = new HashMap<String, Object>(); Enumeration
	 * enu=multipartRequest.getParameterNames(); while(enu.hasMoreElements()){
	 * String name=(String)enu.nextElement(); String
	 * value=multipartRequest.getParameter(name); articleMap.put(name,value); }
	 * String imageFileName= upload(multipartRequest); HttpSession session =
	 * multipartRequest.getSession(); MemberVO memberVO = (MemberVO)
	 * session.getAttribute("member"); String mem_id = memberVO.getId();
	 * articleMap.put("id", mem_id); articleMap.put("imageFileName", imageFileName);
	 * String message; ResponseEntity resEnt=null; HttpHeaders responseHeaders = new
	 * HttpHeaders();
	 * responseHeaders.add("Content-Type","text/html; charset=utf-8"); try { int id
	 * = boardService.addNewArticle(articleMap); if(imageFileName!=null &&
	 * imageFileName.length()!=0) { File srcFile = new
	 * File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+file); File destDir = new
	 * File(ARTICLE_IMAGE_REPO+"\\"+id); FileUtils.moveFileToDirectory(srcFile,
	 * destDir,true); } message = "<script>"; message += " alert('새글을 추가했습니다.');";
	 * message +=" location.href='"+multipartRequest.getContextPath()
	 * +"/board/listArticles.do'; "; message +=" </script>"; resEnt = new
	 * ResponseEntity(message, responseHeaders, HttpStatus.CREATED); }
	 * catch(Exception e) { File srcFile = new
	 * File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); srcFile.delete();
	 * message = " <script>"; message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
	 * message +=" location.href='"+multipartRequest.getContextPath()
	 * +"/board/articleForm.do'; "; message +=" </script>"; resEnt = new
	 * ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	 * e.printStackTrace(); } return resEnt; }
	 */

	/*
	 * //다중 이미지 보여주기
	 * 
	 * @RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	 * public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception{
	 * String viewName = (String)request.getAttribute("viewName"); Map
	 * articleMap=boardService.viewArticle(articleNO); ModelAndView mav = new
	 * ModelAndView(); mav.setViewName(viewName); mav.addObject("articleMap",
	 * articleMap); return mav; }
	 */

	/*
	 * //다중 이미지 글 추가하기
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value="/board/addNewArticle.do" ,method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ResponseEntity addNewArticle(MultipartHttpServletRequest
	 * multipartRequest, HttpServletResponse response) throws Exception {
	 * multipartRequest.setCharacterEncoding("utf-8"); String imageFileName=null;
	 * 
	 * Map articleMap = new HashMap(); Enumeration
	 * enu=multipartRequest.getParameterNames(); while(enu.hasMoreElements()){
	 * String name=(String)enu.nextElement(); String
	 * value=multipartRequest.getParameter(name); articleMap.put(name,value); }
	 * 
	 * //로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다. HttpSession session =
	 * multipartRequest.getSession(); MemberVO memberVO = (MemberVO)
	 * session.getAttribute("member"); String id = memberVO.getId();
	 * articleMap.put("id",id);
	 * 
	 * 
	 * List<String> fileList =upload(multipartRequest); List<ImageVO> imageFileList
	 * = new ArrayList<ImageVO>(); if(fileList!= null && fileList.size()!=0) {
	 * for(String fileName : fileList) { ImageVO imageVO = new ImageVO();
	 * imageVO.setImageFileName(fileName); imageFileList.add(imageVO); }
	 * articleMap.put("imageFileList", imageFileList); } String message;
	 * ResponseEntity resEnt=null; HttpHeaders responseHeaders = new HttpHeaders();
	 * responseHeaders.add("Content-Type", "text/html; charset=utf-8"); try { int
	 * articleNO = boardService.addNewArticle(articleMap); if(imageFileList!=null &&
	 * imageFileList.size()!=0) { for(ImageVO imageVO:imageFileList) { imageFileName
	 * = imageVO.getImageFileName(); File srcFile = new
	 * File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); File destDir = new
	 * File(ARTICLE_IMAGE_REPO+"\\"+articleNO); //destDir.mkdirs();
	 * FileUtils.moveFileToDirectory(srcFile, destDir,true); } }
	 * 
	 * message = "<script>"; message += " alert('새글을 추가했습니다.');"; message +=
	 * " location.href='"+multipartRequest.getContextPath()
	 * +"/board/listArticles.do'; "; message +=" </script>"; resEnt = new
	 * ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	 * 
	 * 
	 * }catch(Exception e) { if(imageFileList!=null && imageFileList.size()!=0) {
	 * for(ImageVO imageVO:imageFileList) { imageFileName =
	 * imageVO.getImageFileName(); File srcFile = new
	 * File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); srcFile.delete(); }
	 * }
	 * 
	 * 
	 * message = " <script>"; message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
	 * message +=" location.href='"+multipartRequest.getContextPath()
	 * +"/board/articleForm.do'; "; message +=" </script>"; resEnt = new
	 * ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	 * e.printStackTrace(); } return resEnt; }
	 * 
	 */

	@RequestMapping(value = "/board/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	

	/*
	 * //한개 이미지 업로드하기 private String upload(MultipartHttpServletRequest
	 * multipartRequest) throws Exception{ String imageFileName= null;
	 * Iterator<String> fileNames = multipartRequest.getFileNames();
	 * 
	 * while(fileNames.hasNext()){ String fileName = fileNames.next(); MultipartFile
	 * mFile = multipartRequest.getFile(fileName);
	 * imageFileName=mFile.getOriginalFilename(); File file = new
	 * File(ARTICLE_IMAGE_REPO +"\\"+ fileName); if(mFile.getSize()!=0){ //File Null
	 * Check if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
	 * if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성 file.createNewFile();
	 * //이후 파일 생성 } } mFile.transferTo(new File(ARTICLE_IMAGE_REPO
	 * +"\\"+"temp"+ "\\"+imageFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송 } }
	 * return imageFileName; }
	 */

	/*
	 * //다중 이미지 업로드하기 private List<String> upload(MultipartHttpServletRequest
	 * multipartRequest) throws Exception{ List<String> fileList= new
	 * ArrayList<String>(); Iterator<String> fileNames =
	 * multipartRequest.getFileNames(); while(fileNames.hasNext()){ String fileName
	 * = fileNames.next(); MultipartFile mFile = multipartRequest.getFile(fileName);
	 * String originalFileName=mFile.getOriginalFilename();
	 * fileList.add(originalFileName); File file = new File(ARTICLE_IMAGE_REPO
	 * +"\\"+ fileName); if(mFile.getSize()!=0){ //File Null Check if(!
	 * file.exists()){ //경로상에 파일이 존재하지 않을 경우 if(file.getParentFile().mkdirs()){
	 * //경로에 해당하는 디렉토리들을 생성 file.createNewFile(); //이후 파일 생성 } }
	 * mFile.transferTo(new File(ARTICLE_IMAGE_REPO
	 * +"\\"+"temp"+ "\\"+originalFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송 } }
	 * return fileList; }
	 */
}