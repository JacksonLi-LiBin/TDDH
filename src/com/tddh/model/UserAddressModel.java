package com.tddh.model;

import java.io.Serializable;

/**
 * user address model
 * 
 * @author jacks
 *
 */
public class UserAddressModel implements Serializable {
	private static final long serialVersionUID = 4697174454978201170L;
	private Integer user_id;
	private Integer user_address_id;
	private String user_address;
	private Integer user_address_default;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getUser_address_id() {
		return user_address_id;
	}

	public void setUser_address_id(Integer user_address_id) {
		this.user_address_id = user_address_id;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public Integer getUser_address_default() {
		return user_address_default;
	}

	public void setUser_address_default(Integer user_address_default) {
		this.user_address_default = user_address_default;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", user_address_id:" + user_address_id + ", user_address:" + user_address
				+ ", user_address_default:" + user_address_default + "}";
	}

}
