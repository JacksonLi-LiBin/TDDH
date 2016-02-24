package com.tddh.model;

import java.io.Serializable;

/**
 * user model
 * 
 * @author jackson
 *
 */
public class UserModel implements Serializable {
	private static final long serialVersionUID = -5862207866544447688L;
	private Integer user_id;
	private String user_name;
	private String user_nickname;
	private String user_phone;
	private String user_proxy_id;
	private Integer user_integral;
	private Integer user_recommend_id;
	private String user_superior_id;
	private String user_qr_code;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_proxy_id() {
		return user_proxy_id;
	}

	public void setUser_proxy_id(String user_proxy_id) {
		this.user_proxy_id = user_proxy_id;
	}

	public Integer getUser_integral() {
		return user_integral;
	}

	public void setUser_integral(Integer user_integral) {
		this.user_integral = user_integral;
	}

	public Integer getUser_recommend_id() {
		return user_recommend_id;
	}

	public void setUser_recommend_id(Integer user_recommend_id) {
		this.user_recommend_id = user_recommend_id;
	}

	public String getUser_superior_id() {
		return user_superior_id;
	}

	public void setUser_superior_id(String user_superior_id) {
		this.user_superior_id = user_superior_id;
	}

	public String getUser_qr_code() {
		return user_qr_code;
	}

	public void setUser_qr_code(String user_qr_code) {
		this.user_qr_code = user_qr_code;
	}

	@Override
	public String toString() {
		return "{user_id:" + user_id + ", user_name:" + user_name + ", user_nickname:" + user_nickname + ", user_phone:"
				+ user_phone + ", user_proxy_id:" + user_proxy_id + ", user_integral:" + user_integral
				+ ", user_recommend_id:" + user_recommend_id + ", user_superior_id:" + user_superior_id
				+ ", user_qr_code:" + user_qr_code + "}";
	}

}
