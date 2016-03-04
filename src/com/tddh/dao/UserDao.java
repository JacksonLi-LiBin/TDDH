package com.tddh.dao;

import java.util.List;

import com.tddh.model.UserModel;
import com.tddh.model.UserMoneyModel;
import com.tddh.model.UserProxyProductDetailModel;

/**
 * define user database operation functions
 * 
 * @author jackson
 *
 */
public interface UserDao {
	/**
	 * judge whether user is proxy
	 * 
	 * @param user_id
	 * @return
	 */
	public boolean isUserProxy(int user_id);

	/**
	 * get user base information
	 * 
	 * @param user_id
	 * @return
	 */
	public UserModel getUserBaseInformation(int user_id);

	/**
	 * get user purchase amount
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserPurchaseAmount(int userId);

	/**
	 * get user deduct amount
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserDeductAmount(int userId);

	/**
	 * get user proxy product detail id/name/price
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserProxyProductDetailModel> getUserProxyProductDetail(int userId);

	/**
	 * get user money
	 * 
	 * @param userId
	 * @return
	 */
	public UserMoneyModel getUserMoney(int userId);
}
