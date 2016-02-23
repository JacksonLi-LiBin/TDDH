package com.tddh.model;

import java.io.Serializable;

/**
 * order model
 * 
 * @author jackson
 *
 */
public class OrderModel implements Serializable {
	private static final long serialVersionUID = -994814000793684553L;
	private int order_id;
	private int order_user_id;
	private int order_seller_id;
	private String order_create_time;
	private String order_pay_time;
	private double order_total_price;
	private int order_submit_state;// customer submit the order 0 false 1 true
	private int order_state;// order state 0 to be shipped 1 shipped 2 be
							// receipt
	private int express_id;
	private String express_number;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getOrder_user_id() {
		return order_user_id;
	}

	public void setOrder_user_id(int order_user_id) {
		this.order_user_id = order_user_id;
	}

	public int getOrder_seller_id() {
		return order_seller_id;
	}

	public void setOrder_seller_id(int order_seller_id) {
		this.order_seller_id = order_seller_id;
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

	public double getOrder_total_price() {
		return order_total_price;
	}

	public void setOrder_total_price(double order_total_price) {
		this.order_total_price = order_total_price;
	}

	public int getOrder_submit_state() {
		return order_submit_state;
	}

	public void setOrder_submit_state(int order_submit_state) {
		this.order_submit_state = order_submit_state;
	}

	public int getOrder_state() {
		return order_state;
	}

	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}

	public int getExpress_id() {
		return express_id;
	}

	public void setExpress_id(int express_id) {
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
		return "{order_id:" + order_id + ", order_user_id:" + order_user_id + ", order_seller_id:" + order_seller_id
				+ ", order_create_time:" + order_create_time + ", order_pay_time:" + order_pay_time
				+ ", order_total_price:" + order_total_price + ", order_submit_state:" + order_submit_state
				+ ", order_state:" + order_state + ", express_id:" + express_id + ", express_number:" + express_number
				+ "}";
	}

}
