package com.mySpring.springEx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
	 // 다운로드 파일의 컨텐트 타입을 지정 
	 @Override
	 public String getContentType() {
	  // 8비트로 된 일련의 데이터..application의 데이타 형식을 굳이 지정하지 않을떄 사용함.
	  return "application/octet-stream";
	 }

	 @Override
	 protected void renderMergedOutputModel(Map<String, Object> map,
	   HttpServletRequest request, HttpServletResponse response) throws Exception {
	  
	  System.out.println("downloadView 실행~~~"+map.get("path"));
	  String path=(String)map.get("path");
	  String fileName=request.getParameter("filename");
	  //업로드 경로가 저장된 파일 객체
	  File file=new File(path+fileName);
	  
	  // 파일 다운로드 
	    response.setContentType(this.getContentType());
	    response.setContentLength((int)file.length());//파일 크기 설정 
	    // 다운로드 파일에 대한 설정 
	    response.setHeader("Content-Disposition", 
	      "attachment; fileName="
	    +new String(file.getName().getBytes("UTF-8"),"8859_1"));
	    //데이터 인코딩이 바이너리 파일임을 명시
	    response.setHeader("Content-Transfer-encoding", "binary");
	    // 실제 업로드 파일을 inputStream 으로 읽어서 
	    // response 에 연결된 OutputStream으로 전송하겠다. 
	    OutputStream os=response.getOutputStream();
	    FileInputStream fis=new FileInputStream(file);
	    FileCopyUtils.copy(fis, os);
	    System.out.println("다운로드.."+file.getName());
	 }
	}   