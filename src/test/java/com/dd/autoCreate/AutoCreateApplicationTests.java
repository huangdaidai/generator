package com.dd.autoCreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class AutoCreateApplicationTests {

	@Test
	public void contextLoads() {
        Connection dbConnection = null;
        try {
//        	Class.forName("com.mysql.jdbc.Driver");
            dbConnection= DriverManager
                    .getConnection("jdbc:oracle:thin:@58.62.207.50:5352:dkdz","db_dkdz_dev","foresee1");
//            dbConnection= DriverManager
//            		.getConnection("jdbc:mysql://127.0.0.1:3306/account?useSSL=false","root","root");
            System.out.println(dbConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
