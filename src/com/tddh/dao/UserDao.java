package com.tddh.dao;

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
}
