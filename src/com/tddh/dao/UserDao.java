package com.tddh.dao;

import com.tddh.model.UserModel;

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
}
