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
import com.tddh.model.ApplyOrderProductModel;
import com.tddh.model.OrderModel;
import com.tddh.model.OrderProductModel;
import com.tddh.model.ProductModel;
import com.tddh.model.ProxyModel;
import com.tddh.model.ProxyOrderModel;
import com.tddh.model.ProxyProductModel;
import com.tddh.model.ProxyRecomendPercentModel;
import com.tddh.model.UncheckUserOrderModel;
import com.tddh.model.UncheckUserOrderProductModel;
import com.tddh.model.UserAddressModel;
import com.tddh.model.UserModel;
import com.tddh.model.UserPreviousProductProxyModel;
import com.tddh.model.UserProxyModel;
import com.tddh.model.UserProxyProductModel;
import com.tddh.model.UserSubordinateRecommendOrderModel;
import com.tddh.utils.DateUtils;
import com.tddh.utils.PropertiesUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean purchaseOrder(int userId, List<ApplyOrderProductModel> purchaseProducts, int addressId,
			int payType) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			conn.setAutoCommit(false);
			Integer orderId = (int) (Math.random() * 100000000);
			String createTime = DateUtils.getDateFormat().format(new Date());
			Object[] newOrderParams = { orderId, userId, createTime, null, 0, 0, addressId, null, null };
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), newOrderParams);
			for (ApplyOrderProductModel purchaseProduct : purchaseProducts) {
				UserProxyProductModel userProxyProductModel = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
						new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class), userId,
						purchaseProduct.getProductId());
				ProxyProductModel proxyProductModel = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "load_product_by_proxy"),
						new BeanHandler<ProxyProductModel>(ProxyProductModel.class), purchaseProduct.getProductId(),
						userProxyProductModel.getProxy_id());
				Double totalPrice = purchaseProduct.getProductCounts() * proxyProductModel.getProduct_proxy_price();
				Double deduct = 0.0d;
				if (userProxyProductModel.getUser_recommend_id() != null) {
					UserProxyModel userProxyModel = queryRunner.query(conn,
							PropertiesUtils.readProperties("sql", "load_proxy_by_id"),
							new BeanHandler<UserProxyModel>(UserProxyModel.class), userProxyProductModel.getProxy_id());
					ProxyRecomendPercentModel proxyRecomendPercentModel = queryRunner.query(conn,
							PropertiesUtils.readProperties("sql", "get_proxy_percent"),
							new BeanHandler<ProxyRecomendPercentModel>(ProxyRecomendPercentModel.class),
							userProxyModel.getProxy_level());
					deduct = totalPrice * Double.parseDouble(proxyRecomendPercentModel.getDeduct_percent());
					Object[] newOrderDeductParams = { orderId, userProxyProductModel.getUser_recommend_id(),
							purchaseProduct.getProductId(), deduct, 0 };
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_deduct"),
							newOrderDeductParams);
				}
				Object[] newOrderProductParams = { orderId, purchaseProduct.getProductId(),
						purchaseProduct.getProductCounts(), proxyProductModel.getProduct_proxy_price(),
						(totalPrice - deduct) };
				queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_product"),
						newOrderProductParams);
			}
			Object[] uncheckOrderParams = { orderId, 1 };
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_uncheck_order"),
					uncheckOrderParams);
			if (payType == 0) {
				// pay online
			} else if (payType == 1) {
				// pay offline
				conn.commit();
				return true;
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				e.printStackTrace();
				return false;
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
				for (ProxyOrderModel proxyOrderModel : proxyOrderModels) {
					boolean flag = false;
					Iterator iterator = proxyOrder.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<Integer, List<ProxyOrderModel>> entry = (Entry<Integer, List<ProxyOrderModel>>) iterator
								.next();
						if (("" + entry.getKey()).equals("" + proxyOrderModel.getOrder_id())) {
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
	public String applyForProductProxy(int userId, String recommendProxyId, int productId, int productCounts,
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
					return "{\"result\":\"address_null\"}";
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
					if (recommendProxyId == null) {
						return applyProxy(conn, orderId, userId, createTime, userDefaultAddressId, productId,
								userproxyModel.getProxy_id(), proxyLevel, productCounts, null, null, payType);
					} else {
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
												productId, userproxyModel.getProxy_id(), proxyLevel, productCounts,
												null, recommendUser.getUser_id(), payType);
									}
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
							return "{\"result\":\"recommend_disable\"}";
						}
					}
				}
			} else {
				return "{\"result\":\"already_applied\"}";
			}
		} catch (Exception e) {
			try {
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
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), newOrderParams);
			ProxyProductModel proxyProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_product_by_proxy"),
					new BeanHandler<ProxyProductModel>(ProxyProductModel.class), productId, proxyId);
			ProxyRecomendPercentModel proxyRecomendPercentModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "get_proxy_percent"),
					new BeanHandler<ProxyRecomendPercentModel>(ProxyRecomendPercentModel.class), proxyLevel);
			Double total_price = (proxyProductModel.getProduct_proxy_price() * productCounts);
			Double deduct = 0.0d;
			if (recommendId != null) {
				UserProxyProductModel recommendProxyProduct = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
						new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class), recommendId, productId);
				if (recommendProxyProduct.getUser_superior_id() != null) {
					deduct = total_price * Double.parseDouble(proxyRecomendPercentModel.getDeduct_percent());
					Object[] newOrderDeductParams = { orderId, recommendId, productId, deduct, 0 };
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_deduct"),
							newOrderDeductParams);
				}
			}
			Object[] newOrderProductParams = { orderId, productId, productCounts,
					proxyProductModel.getProduct_proxy_price(), (total_price - deduct) };
			Object[] newProxyProductParams = { userId, productId, proxyId, superiorId, recommendId, 0 };
			Object[] uncheckOrderParams = { orderId, 0 };
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_product"),
					newOrderProductParams);
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
				e.printStackTrace();
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

	@Override
	public List<UserSubordinateRecommendOrderModel> getMySubordinateRecommendOrder(Integer reqType, Integer userId,
			Integer productId) {
		Connection conn = null;
		List<UserSubordinateRecommendOrderModel> orderList = null;
		try {
			conn = DBConnectionUtils.getConnection();
			switch (reqType) {
			case 0:// subordinate order
				orderList = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "load_my_subordinate_product_proxy_order"),
						new BeanListHandler<UserSubordinateRecommendOrderModel>(
								UserSubordinateRecommendOrderModel.class),
						userId, productId);
				break;
			case 1:// recommend order
				orderList = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "load_my_recommend_product_proxy_order"),
						new BeanListHandler<UserSubordinateRecommendOrderModel>(
								UserSubordinateRecommendOrderModel.class),
						userId, productId);
				break;
			}
			return orderList;
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
	public String upgradeProductProxy(int userId, int productId, int productCounts, int proxyLevel, int payType) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			List<UserAddressModel> userAddressModels = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_user_all_address"),
					new BeanListHandler<UserAddressModel>(UserAddressModel.class), userId);
			Integer userDefaultAddressId = null;
			for (UserAddressModel addressModel : userAddressModels) {
				if (addressModel.getUser_address_default() == 1) {
					userDefaultAddressId = addressModel.getUser_address_id();
					break;
				}
			}
			Integer orderId = (int) (Math.random() * 100000000);
			String createTime = DateUtils.getDateFormat().format(new Date());
			UserProxyProductModel userProductProxy = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
					new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class), userId, productId);
			UserProxyModel levelProxy = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_proxy_by_level"),
					new BeanHandler<UserProxyModel>(UserProxyModel.class), proxyLevel);
			if (userProductProxy.getUser_superior_id() == null) {
				// no superior proxy update proxy level directly
				return upgradeProductProxy(conn, orderId, userId, createTime, userDefaultAddressId, productId,
						userProductProxy.getProxy_id(), userProductProxy.getUser_superior_id(),
						levelProxy.getProxy_id(), proxyLevel, productCounts, null,
						userProductProxy.getUser_recommend_id(), payType);
			} else {
				UserProxyProductModel superiorProductProxy = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
						new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class),
						userProductProxy.getUser_superior_id(), productId);
				UserProxyModel superiorProxy = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "load_proxy_by_id"),
						new BeanHandler<UserProxyModel>(UserProxyModel.class), superiorProductProxy.getProxy_id());
				if (superiorProxy.getProxy_level() < proxyLevel) {
					// do not change superior proxy user
					return upgradeProductProxy(conn, orderId, userId, createTime, userDefaultAddressId, productId,
							userProductProxy.getProxy_id(), userProductProxy.getUser_superior_id(),
							levelProxy.getProxy_id(), proxyLevel, productCounts, userProductProxy.getUser_superior_id(),
							userProductProxy.getUser_recommend_id(), payType);
				} else {
					// change superior user to company
					return upgradeProductProxy(conn, orderId, userId, createTime, userDefaultAddressId, productId,
							userProductProxy.getProxy_id(), userProductProxy.getUser_superior_id(),
							levelProxy.getProxy_id(), proxyLevel, productCounts, null,
							userProductProxy.getUser_recommend_id(), payType);
				}
			}
		} catch (Exception e) {
			return "false";
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

	private String upgradeProductProxy(Connection conn, Integer orderId, Integer userId, String createTime,
			Integer userDefaultAddressId, Integer productId, Integer prevProxyId, Integer prevSuperiorId,
			Integer proxyId, Integer proxyLevel, Integer productCounts, Integer superiorId, Integer recommendId,
			Integer payType) {
		try {
			conn.setAutoCommit(false);
			Object[] newOrderParams = { orderId, userId, createTime, null, 0, 0, userDefaultAddressId, null, null };
			Object[] prevProxyParams = { userId, productId, prevProxyId, prevSuperiorId };
			Object[] uncheckOrderParams = { orderId, 2 };
			ProxyProductModel proxyProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_product_by_proxy"),
					new BeanHandler<ProxyProductModel>(ProxyProductModel.class), productId, proxyId);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), newOrderParams);
			Double total_price = (proxyProductModel.getProduct_proxy_price() * productCounts);
			Double deduct = 0.0d;
			if (recommendId != null) {
				UserProxyProductModel recommendProxyProduct = queryRunner.query(conn,
						PropertiesUtils.readProperties("sql", "user_is_product_proxy"),
						new BeanHandler<UserProxyProductModel>(UserProxyProductModel.class), recommendId, productId);
				if (recommendProxyProduct.getUser_superior_id() != null) {
					ProxyRecomendPercentModel proxyRecomendPercentModel = queryRunner.query(conn,
							PropertiesUtils.readProperties("sql", "get_proxy_percent"),
							new BeanHandler<ProxyRecomendPercentModel>(ProxyRecomendPercentModel.class), proxyLevel);
					deduct = total_price * Double.parseDouble(proxyRecomendPercentModel.getDeduct_percent());
					Object[] newOrderDeductParams = { orderId, recommendId, productId, deduct, 0 };
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_deduct"),
							newOrderDeductParams);
				}
			}
			Object[] newOrderProductParams = { orderId, productId, productCounts,
					proxyProductModel.getProduct_proxy_price(), (total_price - deduct) };
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_prev_proxy"), prevProxyParams);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_uncheck_order"),
					uncheckOrderParams);
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order_product"),
					newOrderProductParams);
			Object[] updateProxyProductParams = { proxyId, superiorId, 0, userId, productId };
			queryRunner.update(conn, PropertiesUtils.readProperties("sql", "update_product_proxy_by_user_product"),
					updateProxyProductParams);
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
				e.printStackTrace();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UncheckUserOrderModel> getUncheckUserOrder(int orderType) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			List<UncheckUserOrderProductModel> models = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_uncheck_order_pay_offline"),
					new BeanListHandler<UncheckUserOrderProductModel>(UncheckUserOrderProductModel.class), orderType);
			if (models == null || models.size() == 0) {
				return null;
			} else {
				Map<Integer, List<UncheckUserOrderProductModel>> uncheckOrder = new HashMap<Integer, List<UncheckUserOrderProductModel>>();
				List<UncheckUserOrderModel> uncheckOrders = new ArrayList<UncheckUserOrderModel>();
				for (UncheckUserOrderProductModel model : models) {
					boolean flag = false;
					Iterator iterator = uncheckOrder.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<Integer, List<UncheckUserOrderProductModel>> entry = (Entry<Integer, List<UncheckUserOrderProductModel>>) iterator
								.next();
						if (("" + entry.getKey()).equals("" + model.getOrder_id())) {
							List<UncheckUserOrderProductModel> list = entry.getValue();
							list.add(model);
							flag = true;
							break;
						}
					}
					if (!flag) {
						List<UncheckUserOrderProductModel> list = new ArrayList<UncheckUserOrderProductModel>();
						list.add(model);
						uncheckOrder.put(model.getOrder_id(), list);
					}
				}
				Iterator iterator = uncheckOrder.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Integer, List<UncheckUserOrderProductModel>> entry = (Entry<Integer, List<UncheckUserOrderProductModel>>) iterator
							.next();
					List<UncheckUserOrderProductModel> list = entry.getValue();
					List<ProductModel> products = new ArrayList<ProductModel>();
					for (UncheckUserOrderProductModel model : list) {
						ProductModel productModel = new ProductModel();
						productModel.setProduct_id(model.getProduct_id());
						productModel.setProduct_name(model.getProduct_name());
						products.add(productModel);
					}
					UncheckUserOrderModel uncheckUserOrderModel = new UncheckUserOrderModel();
					uncheckUserOrderModel.setOrder_id(list.get(0).getOrder_id());
					uncheckUserOrderModel.setOrder_type(list.get(0).getOrder_type());
					uncheckUserOrderModel.setOrder_create_time(list.get(0).getOrder_create_time());
					uncheckUserOrderModel.setUser_id(list.get(0).getUser_id());
					uncheckUserOrderModel.setUser_name(list.get(0).getUser_name());
					uncheckUserOrderModel.setUser_nickname(list.get(0).getUser_nickname());
					uncheckUserOrderModel.setProductModels(products);
					uncheckOrders.add(uncheckUserOrderModel);
				}
				return uncheckOrders;
			}
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
	public boolean handleUncheckOrder(int orderId, int orderType, int handleType) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			conn.setAutoCommit(false);
			OrderModel orderModel = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "load_order_by_id"),
					new BeanHandler<OrderModel>(OrderModel.class), orderId);
			OrderProductModel orderProductModel = queryRunner.query(conn,
					PropertiesUtils.readProperties("sql", "load_order_product"),
					new BeanHandler<OrderProductModel>(OrderProductModel.class), orderId);
			switch (orderType) {
			case 0:// apply order
				if (handleType == 0) {// pass
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_apply_proxy_product"),
							orderModel.getOrder_user_id(), orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_uncheck_order_by_id"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_proxy_product_deduct"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_proxy_product_order"),
							DateUtils.getDateFormat().format(new Date()), orderId);
				} else {// reject
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_user_apply_proxy_product"),
							orderModel.getOrder_user_id(), orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_uncheck_order_by_id"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_order_product"), orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_user_deduct"), orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_order"), orderId);
				}
				break;
			case 1:// purchase order
				if (handleType == 0) {
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_uncheck_order_by_id"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_proxy_product_deduct"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_proxy_product_order"),
							DateUtils.getDateFormat().format(new Date()), orderId);
				} else {
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_uncheck_order_by_id"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_order_product"), orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_user_deduct"), orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_order"), orderId);
				}
				break;
			case 2:// upgrade order
				if (handleType == 0) {// pass
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_user_prev_product_proxy"),
							orderModel.getOrder_user_id(), orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_apply_proxy_product"),
							orderModel.getOrder_user_id(), orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_uncheck_order_by_id"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_proxy_product_deduct"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "pass_proxy_product_order"),
							DateUtils.getDateFormat().format(new Date()), orderId);
				} else {// reject
					UserPreviousProductProxyModel previousProductProxyModel = queryRunner.query(conn,
							PropertiesUtils.readProperties("sql", "load_user_prev_product_proxy"),
							new BeanHandler<UserPreviousProductProxyModel>(UserPreviousProductProxyModel.class),
							orderModel.getOrder_user_id(), orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "backup_apply_proxy_product"),
							previousProductProxyModel.getPrevious_proxy_id(),
							previousProductProxyModel.getPrevious_superior_id(), orderModel.getOrder_user_id(),
							orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_uncheck_order_by_id"),
							orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_user_prev_product_proxy"),
							orderModel.getOrder_user_id(), orderProductModel.getProduct_id());
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_order_product"), orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_user_deduct"), orderId);
					queryRunner.update(conn, PropertiesUtils.readProperties("sql", "remove_order"), orderId);
				}
				break;
			}
			conn.commit();
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
				return false;
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
		return false;
	}
}
