package com.tddh.model;

import java.io.Serializable;
import java.util.List;

public class OrderDetailModel implements Serializable {
	private static final long serialVersionUID = 353029662671937261L;
	private OrderModel orderModel;
	private List<OrderProductModel> orderProductModels;

	public OrderModel getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(OrderModel orderModel) {
		this.orderModel = orderModel;
	}

	public List<OrderProductModel> getOrderProductModels() {
		return orderProductModels;
	}

	public void setOrderProductModels(List<OrderProductModel> orderProductModels) {
		this.orderProductModels = orderProductModels;
	}

	@Override
	public String toString() {
		return "{orderModel:" + orderModel + ", orderProductModels:" + orderProductModels + "}";
	}

}
