package com.tddh.model;

import java.io.Serializable;

/**
 * unchecked order order_type 0 apply for product proxy permission 1 purchase 2
 * upgrade product proxy product
 * 
 * @author jacks
 *
 */
public class UncheckOrderModel implements Serializable {
	private static final long serialVersionUID = -200467169789524107L;
	private Integer check_order_id;
	private Integer order_type;

	public Integer getCheck_order_id() {
		return check_order_id;
	}

	public void setCheck_order_id(Integer check_order_id) {
		this.check_order_id = check_order_id;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	@Override
	public String toString() {
		return "{check_order_id:" + check_order_id + ", order_type:" + order_type + "}";
	}

}
