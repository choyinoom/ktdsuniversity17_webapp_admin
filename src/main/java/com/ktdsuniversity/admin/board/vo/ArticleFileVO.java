package com.ktdsuniversity.admin.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

//공지사항 파일 VO
@Component("articleFileVO")
public class ArticleFileVO {
	private int fileId;
	private String name;
	private int artId;
	
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getartId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}


}