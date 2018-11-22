package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Custom extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8819471883736027452L;
	private int custom_id; // 
	private String custom_name; // 
	private String custom_phone; // 
	private String custom_address; // 
	private String custom_date; // 

	private String ids;
	private String random;

	public void setCustom_id(int custom_id){
		this.custom_id=custom_id;
	}

	public int getCustom_id(){
		return custom_id;
	}

	public void setCustom_name(String custom_name){
		this.custom_name=custom_name;
	}

	public String getCustom_name(){
		return custom_name;
	}

	public void setCustom_phone(String custom_phone){
		this.custom_phone=custom_phone;
	}

	public String getCustom_phone(){
		return custom_phone;
	}

	public void setCustom_address(String custom_address){
		this.custom_address=custom_address;
	}

	public String getCustom_address(){
		return custom_address;
	}

	public void setCustom_date(String custom_date){
		this.custom_date=custom_date;
	}

	public String getCustom_date(){
		return custom_date;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

}
