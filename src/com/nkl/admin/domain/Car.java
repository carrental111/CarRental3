package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Car extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private int row_num; // 
	private int car_id; // 
	private String car_name; // 
	private String car_model; // 
	private String car_desc; // 
	
	private String ids; //  
	private String random;
	
	public int getRow_num() {
		return row_num;
	}
	public int getCar_id() {
		return car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public String getCar_model() {
		return car_model;
	}
	public String getCar_desc() {
		return car_desc;
	}
	public String getIds() {
		return ids;
	}
	public String getRandom() {
		return random;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public void setCar_desc(String car_desc) {
		this.car_desc = car_desc;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public void setRandom(String random) {
		this.random = random;
	}

}
