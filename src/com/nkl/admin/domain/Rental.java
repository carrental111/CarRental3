package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Rental extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private int row_num; // 
	private int rental_id;
	private String apply_date;
	private int custom_id;
	private int car_id;
	private int rental_count;
	private String rental_date1;
	private String rental_date2;
	private double rental_money;
	private int rental_flag;//1:租赁中 2:续租中 3:已归还

	private String custom_name;
	private String custom_phone;
	private String car_name;
	private String car_model;
	private String ids; //  
	private String random;
	private String rental_dateMin;
	private String rental_dateMax;
	
	public String getRental_flagDesc() {
		switch (rental_flag) {
		case 1:
			return "租赁中";
		case 2:
			return "续租中";
		case 3:
			return "已归还";
		default:
			return "";
		}
	}
	
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public int getRental_id() {
		return rental_id;
	}
	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}
	 
	public String getApply_date() {
		return apply_date;
	}

	public int getCar_id() {
		return car_id;
	}

	public int getRental_count() {
		return rental_count;
	}

	public double getRental_money() {
		return rental_money;
	}

	public int getRental_flag() {
		return rental_flag;
	}

	public String getCar_name() {
		return car_name;
	}

	public String getCar_model() {
		return car_model;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public String getRental_dateMin() {
		return rental_dateMin;
	}

	public String getRental_dateMax() {
		return rental_dateMax;
	}

	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public void setRental_count(int rental_count) {
		this.rental_count = rental_count;
	}

	public void setRental_money(double rental_money) {
		this.rental_money = rental_money;
	}

	public void setRental_flag(int rental_flag) {
		this.rental_flag = rental_flag;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public void setRental_dateMin(String rental_dateMin) {
		this.rental_dateMin = rental_dateMin;
	}

	public void setRental_dateMax(String rental_dateMax) {
		this.rental_dateMax = rental_dateMax;
	}

	public int getCustom_id() {
		return custom_id;
	}

	public String getRental_date1() {
		return rental_date1;
	}

	public String getRental_date2() {
		return rental_date2;
	}

	public String getCustom_name() {
		return custom_name;
	}

	public String getCustom_phone() {
		return custom_phone;
	}

	public void setCustom_id(int custom_id) {
		this.custom_id = custom_id;
	}

	public void setRental_date1(String rental_date1) {
		this.rental_date1 = rental_date1;
	}

	public void setRental_date2(String rental_date2) {
		this.rental_date2 = rental_date2;
	}

	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}

	public void setCustom_phone(String custom_phone) {
		this.custom_phone = custom_phone;
	}

}
