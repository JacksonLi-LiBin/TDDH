package com.tddh.model;

import java.io.Serializable;

public class OrderModel implements Serializable {
	private static final long serialVersionUID = -994814000793684553L;
	private Integer order_id;
	private Integer order_user_id;
	private String order_create_time;
	private String order_pay_time;
	private Integer order_submit_state;
	private Integer order_state;
	private Integer order_user_address_id;
	private String express_id;
	private String express_number;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getOrder_user_id() {
		return order_user_id;
	}

	public void setOrder_user_id(Integer order_user_id) {
		this.order_user_id = order_user_id;
	}

	public String getOrder_create_time() {
		return order_create_time;
	}

	public void setOrder_create_time(String order_create_time) {
		this.order_create_time = order_create_time;
	}

	public String getOrder_pay_time() {
		return order_pay_time;
	}

	public void setOrder_pay_time(String order_pay_time) {
		this.order_pay_time = order_pay_time;
	}

	public Integer getOrder_submit_state() {
		return order_submit_state;
	}

	public void setOrder_submit_state(Integer order_submit_state) {
		this.order_submit_state = order_submit_state;
	}

	public Integer getOrder_state() {
		return order_state;
	}

	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}

	public Integer getOrder_user_address_id() {
		return order_user_address_id;
	}

	public void setOrder_user_address_id(Integer order_user_address_id) {
		this.order_user_address_id = order_user_address_id;
	}

	public String getExpress_id() {
		return express_id;
	}

	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}

	public String getExpress_number() {
		return express_number;
	}

	public void setExpress_number(String express_number) {
		this.express_number = express_number;
	}

	@Override
	public String toString() {
		return "{order_id:" + order_id + ", order_user_id:" + order_user_id + ", order_create_time:" + order_create_time
				+ ", order_pay_time:" + order_pay_time + ", order_submit_state:" + order_submit_state + ", order_state:"
				+ order_state + ", order_user_address_id:" + order_user_address_id + ", express_id:" + express_id
				+ ", express_number:" + express_number + "}";
	}

}
