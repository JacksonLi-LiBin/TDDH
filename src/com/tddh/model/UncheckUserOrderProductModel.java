package com.tddh.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * uncheck user order model
 * 
 * @author jackson
 *
 */
@XmlRootElement
public class UncheckUserOrderProductModel implements Serializable {
	private static final long serialVersionUID = -7004757418798553521L;
	private Integer order_id;
	private Integer order_type;
	private String order_create_time;
	private Integer user_id;
	private String user_name;
	private String user_nickname;
	private Integer product_id;
	private String product_name;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	public String getOrder_create_time() {
		return order_create_time;
	}

	public void setOrder_create_time(String order_create_time) {
		this.order_create_time = order_create_time;
	}

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

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", order_type:" + order_type + ", order_create_time:" + order_create_time
				+ ", user_id:" + user_id + ", user_name:" + user_name + ", user_nickname:" + user_nickname
				+ ", product_id:" + product_id + ", product_name:" + product_name + "}";
	}

}
