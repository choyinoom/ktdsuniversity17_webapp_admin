package com.ktdsuniversity.admin.enrollment.vo;

import org.springframework.stereotype.Component;


@Component("EventCompletionLogVO")
public class EventCompletionLogVO {
	
	private int id;
	private String type;
	private String log;
	private String timestamp;
	
	public EventCompletionLogVO() {
	}

	public EventCompletionLogVO(int id, String type, String log, String timestamp) {
		this.id = id;
		this.type = type;
		this.log = log;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
}
