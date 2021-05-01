package com.ktdsuniversity.admin.syllabus.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.common.paging.PageMaker;
import com.ktdsuniversity.admin.common.util.UploadDataVO;
import com.ktdsuniversity.admin.courseCategory.service.CourseCategoryService;
import com.ktdsuniversity.admin.syllabus.service.SyllabusService;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

@Controller("syllabusController")
public class SyllabusControllerImpl implements SyllabusController{
	
	//경로 설정 해줘야합니다. >> 자기 Workspace 프로젝트 recourses 경로
	private static final String SYLLABUS_IMAGE_REPO = "/home/ubuntu/ktdsuniversity_webapp/resources/image";
	
	@Autowired
	private SyllabusService syllabusService;
	
	@Autowired
	CourseCategoryService courseCategoryService;
	
	@Autowired
	SyllabusVO syllabusVO;
	
	@Override
	@RequestMapping(value="/syllabus/listSyllabuses.do" ,method = RequestMethod.GET)
	public ModelAndView listSyllabuses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt((String)request.getParameter("page"));
		} else {
			page = 1;
		}
		
		int perPage = 0;
		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt((String)request.getParameter("perPage"));
		} else {
			perPage = 10;
		}
		
		List syllabusesList = null;
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		criteria.setPerPageNum(perPage);
		pageMaker.setCriteria(criteria);
		
		if (searchType != null && searchText != null) {
			syllabusesList = syllabusService.listBySearchSyllabuses(searchType, searchText);
			criteria.setPage(page);
			criteria.setSearchText(searchText);
			criteria.setSearchType(searchType);
			pageMaker.setTotalCount(syllabusesList.size());
			syllabusesList = syllabusService.listCriteriaBySearch(criteria);
			
			mav.addObject("searchText", searchText);
			mav.addObject("searchType", searchType);
		} else {
			syllabusesList = syllabusService.listSyllabuses();
			criteria.setPage(page);
			pageMaker.setTotalCount(syllabusesList.size());
			syllabusesList = syllabusService.listCriteria(criteria);
		}
		mav.addObject("page", page);
		mav.addObject("perPage", perPage);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("syllabusesList", syllabusesList);
		mav.addObject("viewName", viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/syllabus/addSyllabus.do" ,method = RequestMethod.POST)
	public ModelAndView addSyllabus(@ModelAttribute("syllabus") SyllabusVO syllabusVO, UploadDataVO uploadDataVO, 
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String searchType = (String)request.getParameter("searchType");
		String searchText = URLEncoder.encode((String)request.getParameter("searchText"),"UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		MultipartFile mFile = uploadDataVO.getUploadFile();
		int result = 0;
		syllabusVO.setBannerImg(mFile.getOriginalFilename());
		result = syllabusService.addSyllabus(syllabusVO);
		
		int folderId = syllabusService.selectMaxSyllabusId();
		
		if(mFile.isEmpty()!=true) { //업로드한 파일이 있다면
			System.out.println("파일의 사이즈 ::"+mFile.getSize()); //getSize() :: 파일의 크기, getOriginalFilename() :: 파일 이름  , getName() :: input type="file"의 name
			System.out.println("업로드된 파일명 ::"+mFile.getOriginalFilename());
			System.out.println("getName()? ::"+mFile.getName());
		}
		
		System.out.println("MultipartFile :: " + mFile);
		
		String path = SYLLABUS_IMAGE_REPO+folderId+"/"; //서버의 경로 지정
		// ** 경로 설정 바꿔줘야 함 ** //
		
		// 경로 폴더가 없으면 생성해라
		File folder = new File(path);
		
		if(!folder.exists()) {
			try {
				folder.mkdir();
				System.out.println(path + "폴더 생성");
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		} else {
			System.out.println("폴더 있네용");
		}
		
		//업로드한 파일의 copy본
		File copyFile = new File(path+mFile.getOriginalFilename()); // java.io import
		mFile.transferTo(copyFile);// 이 경로의 저 파일을 이동시켜라. 카피본이 이동
		System.out.println("path::"+path);
		ModelAndView mav = new ModelAndView("redirect:/syllabus/listSyllabuses.do?page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/syllabus/removeSyllabus.do" ,method = RequestMethod.GET)
	public ModelAndView removeSyllabus(@RequestParam("id") int id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String searchType = (String)request.getParameter("searchType");
		String searchText = URLEncoder.encode((String)request.getParameter("searchText"),"UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		SyllabusVO vo = syllabusService.selectSyllabus(id);
		String path = SYLLABUS_IMAGE_REPO+id+"/";
		System.out.println("폴더경로: " +path);
		File folder = new File(path);
		if (folder.exists()) {
			File file = new File(path+ vo.getBannerImg());
			file.delete();
			System.out.println(vo.getBannerImg()+"파일 삭제 되었습니다.");
			folder.delete();
			System.out.println(vo.getId() + "폴더가 삭제 되었습니다.");
		}
		int result = syllabusService.removeSyllabus(id);
		ModelAndView mav = new ModelAndView("redirect:/syllabus/listSyllabuses.do?page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage);
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/syllabus/removeCheckedSyllabuses.do", method = RequestMethod.POST)
	public int removeCheckedSyllabuses(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(arr.length);
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			System.out.println(Integer.parseInt(arr[i]));
			SyllabusVO vo = syllabusService.selectSyllabus(Integer.parseInt(arr[i]));
			String path = SYLLABUS_IMAGE_REPO+arr[i]+"/";
			System.out.println("폴더경로: " +path);
			File folder = new File(path);
			if (folder.exists()) {
				File file = new File(path+ vo.getBannerImg());
				file.delete();
				System.out.println(vo.getBannerImg()+"파일 삭제 되었습니다.");
				folder.delete();
				System.out.println(vo.getId() + "폴더가 삭제 되었습니다.");
			}
			result = syllabusService.removeSyllabus(Integer.parseInt(arr[i]));
		}
		return result;
	}
	
	@Override
	@RequestMapping(value="/syllabus/updateSyllabusForm.do", method = RequestMethod.GET)
	public ModelAndView updateSyllabusForm(@RequestParam("id") int id, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		SyllabusVO syllabusVO = syllabusService.selectSyllabus(id);
		List courseCategoryList = courseCategoryService.listAllCourseCategories();
		
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage);
		mv.addObject("courseCategoryList", courseCategoryList);
		mv.addObject("syllabusVO", syllabusVO);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/syllabus/updateSyllabus.do", method = RequestMethod.POST)
	public ModelAndView updateSyllabus(@ModelAttribute("syllabus") SyllabusVO syllabusVO, UploadDataVO uploadDataVO,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String searchType = (String)request.getParameter("searchType");
		String searchText = URLEncoder.encode((String)request.getParameter("searchText"),"UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		int result = 0;
		
		
		String originalFileName = syllabusVO.getBannerImg();
		int originalFolderName = syllabusVO.getId();
		
		MultipartFile mFile = uploadDataVO.getUploadFile();
		
		if(mFile.isEmpty()!=true) { //업로드한 파일이 있다면
			syllabusVO.setBannerImg(mFile.getOriginalFilename());
			
			result = syllabusService.updateSyllabus(syllabusVO);
			
			String originalPath = SYLLABUS_IMAGE_REPO+originalFolderName+"/";
			File originalFolder = new File(originalPath);
			
			if (originalFolder.exists()) {
				File file = new File(originalPath+ originalFileName);
				file.delete();
				originalFolder.delete();
			}
			String path = SYLLABUS_IMAGE_REPO+syllabusVO.getId()+"/"; //서버의 경로 지정
			// ** 경로 설정 바꿔줘야 함 ** //
			
			// 경로 폴더가 없으면 생성해라
			File folder = new File(path);
			
			if(!folder.exists()) {
				try {
					folder.mkdir();
					System.out.println(path + "폴더 생성");
			}
			catch(Exception e) {
				e.getStackTrace();
			}
			} else {
				System.out.println("폴더 있네용");
			}
			
			File copyFile = new File(path+mFile.getOriginalFilename()); // java.io import
			mFile.transferTo(copyFile);// 이 경로의 저 파일을 이동시켜라. 카피본이 이동
			System.out.println("path::"+path); //업로드한 파일의 copy본	
		}
		
		result = syllabusService.updateSyllabus(syllabusVO);
		ModelAndView mav = new ModelAndView("redirect:/syllabus/selectSyllabus.do?id="+syllabusVO.getId()+"&page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/syllabus/selectSyllabus.do", method = RequestMethod.GET)
	public ModelAndView selectSyllabus(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage);
		SyllabusVO syllabusVO = syllabusService.selectSyllabus(id);
		mv.addObject("syllabusVO", syllabusVO);
		return mv;
	}
	
	@RequestMapping(value = "/syllabus/searchSyllabusByPopUp.do", method = {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView popUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		
		
		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt((String)request.getParameter("page"));
		} else {
			page = 1;
		}
		
		int perPage = 0;
		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt((String)request.getParameter("perPage"));
		} else {
			perPage = 10;
		}
		System.out.println("전달 받은 페이지 번호 page:" + page); 
		System.out.println("리스트 띄울 개수 Perpage:"+perPage);
		
		List syllabusesList = null;
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		criteria.setPerPageNum(perPage);
		pageMaker.setCriteria(criteria);
		
		if (searchType != null && searchText != null) {
			syllabusesList = syllabusService.listBySearchSyllabuses(searchType, searchText);
			criteria.setPage(page);
			criteria.setSearchText(searchText);
			criteria.setSearchType(searchType);
			pageMaker.setTotalCount(syllabusesList.size());
			syllabusesList = syllabusService.listCriteriaBySearch(criteria);
			
			mav.addObject("searchText", searchText);
			mav.addObject("searchType", searchType);
		} else {
			syllabusesList = syllabusService.listSyllabuses();
			criteria.setPage(page);
			pageMaker.setTotalCount(syllabusesList.size());
			syllabusesList = syllabusService.listCriteria(criteria);
		}
		mav.addObject("perPage", perPage);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("syllabusesList", syllabusesList);
		return mav;
	}
	
	
	@RequestMapping(value = "/syllabus/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
							  @RequestParam(value= "action", required=false) String action,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		System.out.println(viewName);
		HttpSession session = request.getSession();
		session.setAttribute("action", action);  
		ModelAndView mav = new ModelAndView();
		List courseCategoryList = courseCategoryService.listAllCourseCategories();
		mav.addObject("searchType", searchType);
		mav.addObject("searchText", searchText);
		mav.addObject("page", page);
		mav.addObject("perPage", perPage);
		mav.addObject("result",result);
		mav.addObject("courseCategoryList", courseCategoryList);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
}
