package com.tddh.dao;

import java.util.List;

import com.tddh.model.ApplyOrderProductModel;
import com.tddh.model.UncheckUserOrderModel;
import com.tddh.model.UserSubordinateRecommendOrderModel;

/**
 * define order database operation functions
 * 
 * @author jackson
 *
 */
public interface OrderDao {
	/**
	 * purchase new order
	 * 
	 * @param userId
	 * @param addressId
	 * @param purchaseProducts
	 * @param payType
	 * @return
	 */
	public boolean purchaseOrder(int userId, List<ApplyOrderProductModel> purchaseProducts, int addressId, int payType);

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
	 * get user subordinate or recommend product proxy order
	 * 
	 * @param reqType
	 *            0 subordinate 1 recommend
	 * @param userId
	 * @param productId
	 * @return
	 */
	public List<List<List<UserSubordinateRecommendOrderModel>>> getMySubordinateRecommendOrder(Integer reqType,
			Integer userId);

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
	public String applyForProductProxy(int userId, String recommendProxyId, int productId, int productCounts,
			int proxyLevel, int payType);

	/**
	 * 
	 * @param userId
	 * @param productId
	 * @param productCounts
	 * @param proxyLevel
	 * @param payType
	 *            0 pay online 1 pay offline
	 * @return
	 */
	public String upgradeProductProxy(int userId, int productId, int productCounts, int proxyLevel, int payType);

	/**
	 * 
	 * @param orderType
	 *            0 apply order 1 purchase order 2 upgrade order
	 * @return
	 */
	public List<UncheckUserOrderModel> getUncheckUserOrder(int orderType);

	/**
	 * 
	 * @param orderId
	 * @param orderType
	 *            0 apply order 1 purchase order 2 upgrade order
	 * @param handleType
	 *            0 pass 1 reject
	 * @return
	 */
	public boolean handleUncheckOrder(int orderId, int orderType, int handleType);
}
