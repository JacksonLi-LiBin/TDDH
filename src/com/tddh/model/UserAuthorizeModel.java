package com.tddh.model;

import java.io.Serializable;

/**
 * user authorize model
 * 
 * @author jacks
 *
 */
public class UserAuthorizeModel implements Serializable {
	private static final long serialVersionUID = 8186025575504806786L;
	private Integer user_id;
	private Integer product_id;
	private Integer authorize_id;
	private String authorize_start_time;// all authorize 3 month
	private Integer authorize_state;// 0 reject or overtime 1 apply 2 pass

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getAuthorize_id() {
		return authorize_id;
	}

	public void setAuthorize_id(Integer authorize_id) {
		this.authorize_id = authorize_id;
	}

	public String getAuthorize_start_time() {
		return authorize_start_time;
	}

	public void setAuthorize_start_time(String authorize_start_time) {
		this.authorize_start_time = authorize_start_time;
	}

	public Integer getAuthorize_state() {
		return authorize_state;
	}

	public void setAuthorize_state(Integer authorize_state) {
		this.authorize_state = authorize_state;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", product_id:" + product_id + ", authorize_id:" + authorize_id
				+ ", authorize_start_time:" + authorize_start_time + ", authorize_state:" + authorize_state + "}";
	}

}
