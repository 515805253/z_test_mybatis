package cn.wanda.entity;

public class Author {
	private Integer id;// 自增主键
	private User user;// 外键引用User表
	private String realName;// 真实姓名
	private String IDCard;// 身份证
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", user=" + user + ", realName=" + realName
				+ ", IDCard=" + IDCard + "]";
	}
	
}
