package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanPlan {
	private int id;
	private String userid;
	private String plan_order;
	private String plan_name;
	private Date create_time;
	private int step_count;
	private int start_step_count;
	private int finished_step_count;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPlan_name() {
		return plan_order;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public int getStart_step_count() {
		return step_count;
	}
	public void setStart_step_count(int start_step_count) {
		this.start_step_count = start_step_count;
	}
	
	public int getFinished_step_count() {
		return finished_step_count;
	}
	public void setFinished_step_count(int finished_step_count) {
		this.finished_step_count = finished_step_count;
	}
	
}
