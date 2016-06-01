package cn.wanda.entity;

import java.util.Date;

/**
 * 用户访问表
 */
public class Visit {

	private Integer id;
	private Date visitDate;
	private String visitIP;
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitIP() {
		return visitIP;
	}

	public void setVisitIP(String visitIP) {
		this.visitIP = visitIP;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", visitDate=" + visitDate + ", visitIP="
				+ visitIP + ", jikeUser=" + user + "]";
	}

}
