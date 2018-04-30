/**
 * 
 */
package com.dd.autoCreate.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dd.autoCreate.common.exception.AutoCreateUnCheckException;
import com.dd.autoCreate.common.util.DB2ClassUtil;
import com.dd.autoCreate.common.util.JDBCUtil;
import com.dd.autoCreate.model.DataSource;
import com.dd.autoCreate.model.Field;
import com.dd.autoCreate.model.Table;
import com.dd.autoCreate.service.UserDBService;

/**
 * @author hdd 2018年4月22日
 * 
 */
@Service("userDBService")
public class UserDBServiceImpl implements UserDBService {

	@Override
	public List<Table> loadTables(DataSource dataSource) throws SQLException {
		Connection conne = JDBCUtil.OpenConn(dataSource);
		String getTableSql = "show tables;";
		ResultSet rs = conne.prepareStatement(getTableSql).executeQuery();
		if (rs == null) {
			throw new AutoCreateUnCheckException("数据库表为空，请先创建表！");
		}
		List<Table> tableList=new ArrayList<>();
		while (rs.next()) {
			String tableName=rs.getString(1);
			Table t= new Table();
			t.setTableName(tableName);
			t.setClassName(DB2ClassUtil.a_b2AB(tableName));
			String getFieldSql="show full columns from "+tableName+";";
	        List<Field> fieldList=new ArrayList<Field>();
	        ResultSet fieldsRs=conne.prepareStatement(getFieldSql).executeQuery();
            while(fieldsRs.next()){
                Field field=new Field();
                field.setFieldNameInDB(fieldsRs.getString(1));
                field.setFieldNameInClass(DB2ClassUtil.a_b2AB(fieldsRs.getString(1)));
                field.setTypeInDB(fieldsRs.getString(2));
                field.setCollation(fieldsRs.getString(3));
                field.setNvll(fieldsRs.getString(4));
                field.setKey(fieldsRs.getString(5));
                field.setDefaults(fieldsRs.getString(6));
                field.setExtra(fieldsRs.getString(7));
                field.setPrivileges(fieldsRs.getString(8));
                field.setComment(fieldsRs.getString(9));
                fieldList.add(field);
                if("PRI".equalsIgnoreCase(field.getKey())){
                	t.setPrimaryKey(field);
                }
            }
            t.setFieldList(fieldList);
			
		}

		return tableList;
	}


}
