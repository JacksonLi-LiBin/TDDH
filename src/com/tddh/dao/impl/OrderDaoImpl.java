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
import com.tddh.model.UserAddressModel;
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
	public String applyForProductProxy(int userId, int recommendProxyId, int productId, int productCounts,
			int proxyLevel, int payType) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			UserProxyProductModel userProxyProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_product_by_user_product"),
					new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class), userId, productId);
			ProxyModel userproxyModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_proxy_by_level"),
					new BeanHandler<ProxyModel>(ProxyModel.class), proxyLevel);
			if (userProxyProductModel == null) {
				List<UserAddressModel> userAddressModels = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "load_user_all_address"),
						new BeanListHandler<UserAddressModel>(UserAddressModel.class), userId);
				if (userAddressModels == null || userAddressModels.size() == 0) {
					return "address_null";
				} else {
					Integer userDefaultAddressId = null;
					for (UserAddressModel addressModel : userAddressModels) {
						if (addressModel.getUser_address_default() == 1) {
							userDefaultAddressId = addressModel.getUser_address_id();
							break;
						}
					}
					Integer orderId = (int) (Math.random() * 100000000);
					String createTime = DateUtils.getDateFormat().format(new Date());
					UserModel recommendUser = queryRunner.query(conn,
							PropertiesUtils.readProperties("sql", "get_user_by_proxy_id"),
							new BeanHandler<UserModel>(UserModel.class), recommendProxyId);
					if (recommendUser != null) {
						UserProxyProductModel userIsProxy = queryRunner.query(conn,
								PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
								new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class),
								recommendUser.getUser_id(), productId);
						if (userIsProxy != null) {
							// recommend user to my upper proxy user
							if (userIsProxy.getUser_superior_id() != null) {
								UserProxyProductModel parentProductProxy = queryRunner.query(conn,
										PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
										new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class),
										userIsProxy.getUser_superior_id(), productId);
								ProxyModel recomendParentProxyModel = queryRunner.query(conn,
										PropertiesUtils.readProperties("sql", "load_proxy_by_id"),
										new BeanHandler<ProxyModel>(ProxyModel.class),
										parentProductProxy.getProxy_id());
								if (proxyLevel > recomendParentProxyModel.getProxy_level()) {
									return applyProxy(conn, orderId, userId, createTime, userDefaultAddressId,
											productId, userproxyModel.getProxy_id(), proxyLevel, productCounts,
											parentProductProxy.getUser_id(), recommendUser.getUser_id(), payType);
								} else {
									// recommend user to company
									return applyProxy(conn, orderId, userId, createTime, userDefaultAddressId,
											productId, userproxyModel.getProxy_id(), proxyLevel, productCounts, null,
											recommendUser.getUser_id(), payType);
								}
							} else {
								// recommend user to company
								return applyProxy(conn, orderId, userId, createTime, userDefaultAddressId, productId,
										userproxyModel.getProxy_id(), proxyLevel, productCounts, null,
										recommendUser.getUser_id(), payType);
							}
						} else {
							// recommend user to company
							return applyProxy(conn, orderId, userId, createTime, userDefaultAddressId, productId,
									userproxyModel.getProxy_id(), proxyLevel, productCounts, null,
									recommendUser.getUser_id(), payType);
						}
					}
				}
			} else {
				return "already_applied";
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				return "false";
			} catch (Exception e2) {
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
		return "false";
	}

	private String applyProxy(Connection conn, Integer orderId, Integer userId, String createTime,
			Integer userDefaultAddressId, Integer productId, Integer proxyId, Integer proxyLevel, Integer productCounts,
			Integer superiorId, Integer recommendId, Integer payType) {
		try {
			conn.setAutoCommit(false);
			Object[] newOrderParams = { orderId, userId, createTime, null, 0, 0, userDefaultAddressId, null, null };
			ProxyProductModel proxyProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_product_by_proxy"),
					new BeanHandler<ProxyProductModel>(ProxyProductModel.class), productId, proxyId);
			ProxyRecomendPercentModel proxyRecomendPercentModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "get_proxy_percent"),
					new BeanHandler<ProxyRecomendPercentModel>(ProxyRecomendPercentModel.class), proxyLevel);
			Double total_price = (proxyProductModel.getProduct_proxy_price() * productCounts);
			Double deduct = total_price * Double.parseDouble(proxyRecomendPercentModel.getDeduct_percent());
			Object[] newOrderProductParams = { orderId, productId, productCounts,
					proxyProductModel.getProduct_proxy_price(), (total_price - deduct) };
			Object[] newOrderDeductParams = { orderId, recommendId, productId, deduct, 0 };
			Object[] newProxyProductParams = { userId, productId, proxyId, superiorId, recommendId, 0 };
			Object[] uncheckOrderParams = { orderId, 0 };
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), newOrderParams);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_product"),
					newOrderProductParams);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_deduct"),
					newOrderDeductParams);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "user_add_new_product_proxy"),
					newProxyProductParams);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_uncheck_order"),
					uncheckOrderParams);
			if (payType == 0) {
				// pay online
			} else if (payType == 1) {
				// pay offline
				conn.commit();
				return "true";
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				return "false";
			} catch (Exception e2) {
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
		return "false";
	}
}
