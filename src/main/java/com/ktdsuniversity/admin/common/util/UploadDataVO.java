package com.ktdsuniversity.admin.common.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadDataVO {
	
	private String uploadName;
	private MultipartFile uploadFile;
	
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@Override
	public String toString() {
		return "UploadDataVO [userName=" + uploadName + ", uploadFile=" + uploadFile;
	}
	
	
	
}
