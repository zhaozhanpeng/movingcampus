package edu.hebtu.movingcampus.entity;

import java.io.Serializable;
import java.util.Date;


public class MMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private Boolean readed=false;
	private Date time;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getReaded() {
		return readed;
	}
	public void setReaded(Boolean readed) {
		this.readed = readed;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(java.util.Date date) {
		this.time = date;
	}

}
