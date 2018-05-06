/**
 * 
 */
package com.dd.generator.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dd.generator.model.DataSource;

/**
 * @author hdd
 * 2018年4月22日
 * 
 */
public class JDBCUtil {
	public static Connection OpenConn(DataSource d) throws SQLException{
		return DriverManager.getConnection(d.getUrl(), d.getUserName(), d.getPassword());
	}
	
}
