package com.tddh.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.OrderDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.ProxyOrderModel;
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
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), orderId, userId,
					userAddressId, productId, orderCounts, orderCreateTime, orderPayTime, orderSubmitState, orderState,
					orderSellerId);
			conn.commit();
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
					conn = null;
				}
			} catch (Exception e2) {
			}
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getOrderDetail(int orderType, int userId) {
		Connection conn = null;
		List<ProxyOrderModel> proxyOrderModels = null;
		try {
			conn = DBConnectionUtils.getConnection();
			if (orderType == 0) {
				proxyOrderModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_my_order"),
						new BeanListHandler<ProxyOrderModel>(ProxyOrderModel.class), userId);
			} else if (orderType == 1) {
				proxyOrderModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_my_proxy_order"),
						new BeanListHandler<ProxyOrderModel>(ProxyOrderModel.class), userId);
			}
			Map<Integer, List<ProxyOrderModel>> proxyOrder = null;
			List<String> proxyOrders = null;
			if (proxyOrderModels != null && proxyOrderModels.size() > 0) {
				proxyOrder = new HashMap<Integer, List<ProxyOrderModel>>();
				proxyOrders = new ArrayList<String>();
				System.out.println();
				for (ProxyOrderModel proxyOrderModel : proxyOrderModels) {
					boolean flag = false;
					Iterator iterator = proxyOrder.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<Integer, List<ProxyOrderModel>> entry = (Entry<Integer, List<ProxyOrderModel>>) iterator
								.next();
						if (entry.getKey() == proxyOrderModel.getOrder_id()) {
							List<ProxyOrderModel> poms = entry.getValue();
							poms.add(proxyOrderModel);
							flag = true;
							break;
						}
					}
					if (!flag) {
						List<ProxyOrderModel> poModels = new ArrayList<ProxyOrderModel>();
						poModels.add(proxyOrderModel);
						proxyOrder.put(proxyOrderModel.getOrder_id(), poModels);
					}
				}
				Iterator iterator = proxyOrder.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Integer, List<ProxyOrderModel>> entry = (Entry<Integer, List<ProxyOrderModel>>) iterator
							.next();
					List<ProxyOrderModel> poms = entry.getValue();
					proxyOrders.add("{order_id:" + poms.get(0).getOrder_id() + ",order_state:"
							+ poms.get(0).getOrder_state() + ",order_products:" + poms + "}");
				}
				return "" + proxyOrders;
			}
			return null;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {

			}
		}
	}
}
