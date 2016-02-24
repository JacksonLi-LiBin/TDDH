package com.tddh.dao.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.tddh.dao.UserDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.model.UserModel;
import com.tddh.utils.PropertiesUtils;

public class UserDaoImpl implements UserDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean isUserProxy(int user_id) {
		Connection conn = null;
		UserModel userModel = null;
		try {
			conn = DBConnectionUtils.getConnection();
			userModel = queryRunner.query(conn, PropertiesUtils.readProperties("sql", "user_is_proxy"),
					new BeanHandler<UserModel>(UserModel.class), user_id);
			if (userModel.getUser_proxy_type_id() != null && !"".equals(userModel.getUser_proxy_type_id())) {
				return true;
			}
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		return false;
	}

}
