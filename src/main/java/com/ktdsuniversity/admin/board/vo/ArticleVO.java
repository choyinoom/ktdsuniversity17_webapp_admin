package com.ktdsuniversity.admin.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

// 공지사항 VO
@Component("articleVO")
public class ArticleVO {
	private int id;
	private String title;
	private String joinDate;
	private String contents;
	private String file;
	private String important;
	private int hits;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContenst(String contents) {
		this.contents = contents;
	}

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}

	/*
	 * public void setFile(String imageFileName) { try { if(imageFileName!= null &&
	 * imageFileName.length()!=0) { this.imageFileName =
	 * URLEncoder.encode(imageFileName,"UTF-8"); } } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); } }
	 */



	
	
}