package com.tddh.dao.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tddh.dao.UserDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.OrderDeductModel;
import com.tddh.model.OrderProductModel;
import com.tddh.model.UserModel;
import com.tddh.utils.PropertiesUtils;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean isUserProxy(int user_id) {
		Connection conn = null;
		UserModel userModel = null;
		try {
			conn = DBConnectionUtils.getConnection();
			userModel = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "user_base_information"),
					new BeanHandler<UserModel>(UserModel.class), user_id);
			if (userModel.getUser_proxy_id() != null && !"".equals(userModel.getUser_proxy_id())) {
				return true;
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

	@Override
	public UserModel getUserBaseInformation(int user_id) {
		Connection conn = null;
		UserModel userModel = null;
		try {
			conn = DBConnectionUtils.getConnection();
			userModel = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "user_base_information"),
					new BeanHandler<UserModel>(UserModel.class), user_id);
			return userModel;
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
	public String getUserPurchaseAmount(int userId) {
		Connection conn = null;
		List<OrderProductModel> orderProductModels = null;
		try {
			conn = DBConnectionUtils.getConnection();
			orderProductModels = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "user_purchase_amount"),
					new BeanListHandler<OrderProductModel>(OrderProductModel.class), userId);
			if (orderProductModels.size() > 0) {
				double amount = 0.0d;
				for (OrderProductModel orderProductModel : orderProductModels) {
					amount += orderProductModel.getProduct_total_price();
				}
				return "" + amount;
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
		return null;
	}

	@Override
	public String getUserDeductAmount(int userId) {
		Connection conn = null;
		List<OrderDeductModel> orderDeduct = null;
		double deductAmount = 0.0d;
		try {
			conn = DBConnectionUtils.getConnection();
			orderDeduct = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "user_deduct_amount"),
					new BeanListHandler<OrderDeductModel>(OrderDeductModel.class), userId);
			for (OrderDeductModel orderDeductModel : orderDeduct) {
				deductAmount += orderDeductModel.getOrder_deduct();
			}
			return "" + deductAmount;
		} catch (Exception e) {
			e.printStackTrace();
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
