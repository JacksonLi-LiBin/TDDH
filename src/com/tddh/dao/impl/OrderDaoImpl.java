package com.tddh.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.OrderDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.ProxyModel;
import com.tddh.model.ProxyOrderModel;
import com.tddh.model.ProxyProductModel;
import com.tddh.model.ProxyRecomendPercentModel;
import com.tddh.model.UserModel;
import com.tddh.model.UserProxyProductModel;
import com.tddh.utils.DateUtils;
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

	@Override
	public boolean applyForProductProxy(int userId, int recommendProxyId, int productId, int productCounts,
			int proxyLevel, int payType) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			Integer orderId = (int) (Math.random() * 100000000);
			String createTime = DateUtils.getDateFormat().format(new Date());
			UserModel recommendUser = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "get_user_by_proxyid"),
					new BeanHandler<UserModel>(UserModel.class), recommendProxyId);
			if (recommendUser != null) {
				UserProxyProductModel userIsProxy = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
						new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class), recommendUser.getUser_id(),
						productId);
				if (userIsProxy != null) {
					// recommend user to my upper proxy user
					if (userIsProxy.getUser_superior_id() != null) {
						UserProxyProductModel parentProductProxy = queryRunner.query(conn,
								PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
								new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class),
								userIsProxy.getUser_superior_id(), productId);
						ProxyModel proxyModel = queryRunner.query(conn,
								PropertiesUtils.readProperties("sql", "load_proxy"),
								new BeanHandler<ProxyModel>(ProxyModel.class), parentProductProxy.getProxy_id());
						if (proxyLevel < proxyModel.getProxy_level()) {
							conn.setAutoCommit(false);
							Object[] newOrderParams = { orderId, userId, createTime, null, 0, 0, null, null };
							ProxyProductModel proxyProductModel = queryRunner.query(conn,
									PropertiesUtils.readProperties("sql", "load_product_by_proxy"),
									new BeanHandler<ProxyProductModel>(ProxyProductModel.class), productId,
									proxyModel.getProxy_level());
							ProxyRecomendPercentModel proxyRecomendPercentModel = queryRunner.query(conn,
									PropertiesUtils.readProperties("sql", "get_proxy_percent"),
									new BeanHandler<ProxyRecomendPercentModel>(ProxyRecomendPercentModel.class),
									proxyLevel);
							Double total_price = (proxyProductModel.getProduct_proxy_price() * productCounts);
							Double deduct = total_price * proxyRecomendPercentModel.getDeduct_percent();
							Object[] newOrderProductParams = { orderId, productId, productCounts,
									proxyProductModel.getProduct_proxy_price(), (total_price - deduct) };
							Object[] newOrderDeductParams = { orderId, recommendUser.getUser_id(), productId, deduct,
									0 };
							Object[] newProxyProductParams = {userId,productId,};xX
							queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"),
									newOrderParams);
							if (payType == 0) {
								// pay online
							}
						} else {
							// recommend user to root proxy user
						}
					} else {
						// recommend user to root proxy user
					}
				} else {
					// recommend user to root proxy user
				}
			}

		} catch (Exception e) {
			return false;
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
}
