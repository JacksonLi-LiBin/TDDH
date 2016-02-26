package com.tddh.dao;

/**
 * define order database operation functions
 * 
 * @author jackson
 *
 */
public interface OrderDao {
	/**
	 * add new order into database
	 * 
	 * @param orderId
	 * @param userId
	 * @param userAddressId
	 * @param productId
	 * @param orderCounts
	 * @param orderCreateTime
	 * @param orderPayTime
	 * @param orderSubmitState
	 * @param orderState
	 * @param orderSellerId
	 * @return
	 */
	public boolean addNewOrder(int orderId, int userId, int userAddressId, int productId, int orderCounts,
			String orderCreateTime, String orderPayTime, int orderSubmitState, int orderState, int orderSellerId);

	/**
	 * get user order
	 * 
	 * @param orderType
	 *            0 my order 1 proxy order
	 * @param userId
	 * @return
	 */
	public String getOrderDetail(int orderType, int userId);

	/**
	 * 
	 * @param userId
	 * @param recommendProxyId
	 * @param productId
	 * @param proxyId
	 * @param payType
	 *            0 pay online 1 pay offline
	 * @return
	 */
	public String applyForProductProxy(int userId, int recommendProxyId, int productId, int productCounts,
			int proxyLevel, int payType);
}
