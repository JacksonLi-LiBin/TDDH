package com.tddh.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.OrderDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.OrderDetailModel;
import com.tddh.model.OrderModel;
import com.tddh.model.OrderProductModel;
import com.tddh.utils.PropertiesUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean addNewOrder(int orderId, int userId, int userAddressId, int productId, int orderCounts,
			String orderCreateTime, String orderPayTime, int orderSubmitState, int orderState, int orderSellerId) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			conn.setAutoCommit(false);
			int ret = -1;
			ret = queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), orderId, userId,
					userAddressId, productId, orderCounts, orderCreateTime, orderPayTime, orderSubmitState, orderState,
					orderSellerId);
			if (ret > 0) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				return false;
			} catch (Exception e1) {
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		return false;
	}

	@Override
	public List<OrderDetailModel> getOrderDetail(int orderType, int userId) {
		Connection conn = null;
		List<OrderModel> orderModels = null;
		List<OrderProductModel> orderProductModels = null;
		List<OrderDetailModel> orderDetailModels = null;
		try {
			conn = DBConnectionUtils.getConnection();
			if (orderType == 0) {
				orderModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_my_order"),
						new BeanListHandler<OrderModel>(OrderModel.class), userId);
			} else if (orderType == 1) {
				orderModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_my_proxy_order"),
						new BeanListHandler<OrderModel>(OrderModel.class), userId);
			}
			if (orderModels.size() > 0) {
				orderDetailModels = new ArrayList<OrderDetailModel>();
				OrderDetailModel orderDetailModel = null;
				for (OrderModel orderModel : orderModels) {
					orderDetailModel = new OrderDetailModel();
					orderDetailModel.setOrderModel(orderModel);
					orderProductModels = queryRunner.query(conn,
							PropertiesUtils.readProperties("sql", "load_order_product"),
							new BeanListHandler<OrderProductModel>(OrderProductModel.class), orderModel.getOrder_id());
					orderDetailModel.setOrderProductModels(orderProductModels);
					orderDetailModels.add(orderDetailModel);
				}
				return orderDetailModels;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {

			}
		}
	}
}
