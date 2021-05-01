package com.ktdsuniversity.admin.event_security_log.vo;

import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Component;


@Component("Event_security_logVO")
public class Event_security_logVO {

	private int id;
	private String user;
	private String type;
	private String date;
	private String text;
	
	
	
	public Event_security_logVO() {
	}

	public Event_security_logVO(int id, String user, String type, String date, String text) {
		this.id = id;
		this.user = user;
		this.type = type;
		this.date = date;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	
	


	
	
}
