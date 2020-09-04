package cn.edu.zucc.personplan.model;
import java.util.Date;
public class BeanUser {
	private String userid;
	private String userpwd;
	private Date register_time;
	
	public void setUserid(String userid) {
		this.userid=userid;
	}
	public String getUserid() {
		return userid;
	}
	 public void setUserpwd(String userpwd) {
		 this.userpwd=userpwd;
	 }
	 public String getUserpwd() {
		 return userpwd;
	 }
	 public void setRegister_time(Date register_time) {
		 this.register_time=register_time;
	 }
	 public Date getRegister_time() {
		 return register_time;
	 }
}
