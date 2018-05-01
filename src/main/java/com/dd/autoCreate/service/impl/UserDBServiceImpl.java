/**
 *
 */
package com.dd.autoCreate.service.impl;

import com.dd.autoCreate.common.exception.AutoCreateUnCheckException;
import com.dd.autoCreate.common.util.DB2ClassUtil;
import com.dd.autoCreate.common.util.JDBCUtil;
import com.dd.autoCreate.model.DataSource;
import com.dd.autoCreate.model.Field;
import com.dd.autoCreate.model.Table;
import com.dd.autoCreate.service.UserDBService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hdd 2018年4月22日
 */
@Service("userDBService")
public class UserDBServiceImpl implements UserDBService {

    @Override
    public List<Table> loadTables(DataSource dataSource) throws SQLException {
        if ("mysql".equals(dataSource.getDbType())) {
            return loadMysqlTables(dataSource);
        } else {
            return loadOracleTables(dataSource);
        }
    }

    private List<Table> loadMysqlTables(DataSource dataSource) throws SQLException {
        Connection conne = JDBCUtil.OpenConn(dataSource);
        ResultSet rs = conne.prepareStatement("show tables;").executeQuery();
        if (rs == null) {
            throw new AutoCreateUnCheckException("数据库表为空，请先创建表！");
        }
        List<Table> tableList = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString(1);
            Table t = new Table();
            t.setTableName(tableName);
            t.setClassName(DB2ClassUtil.a_b2AB(tableName));
            String getFieldSql = "show full columns from " + tableName + ";";
            List<Field> fieldList = new ArrayList<>();
            List<Field> keyList = new ArrayList<>();
            ResultSet fieldsRs = conne.prepareStatement(getFieldSql).executeQuery();
            while (fieldsRs.next()) {
                Field field = new Field();
                field.setFieldNameInDB(fieldsRs.getString(1));
                field.setFieldNameInClass(DB2ClassUtil.a_b2AB(fieldsRs.getString(1)));
                field.setTypeInDB(fieldsRs.getString(2));
                field.setKey(fieldsRs.getString(5));
                field.setComment(fieldsRs.getString(9));
                fieldList.add(field);
                if ("PRI".equalsIgnoreCase(field.getKey())) {
                    keyList.add(field);
                }
            }
            t.setFieldList(fieldList);
            t.setPrimaryKeyList(keyList);
        }
        return tableList;
    }

    private List<Table> loadOracleTables(DataSource dataSource) throws SQLException {
        Connection conne = JDBCUtil.OpenConn(dataSource);
        ResultSet rs = conne.prepareStatement("select table_name from tabs").executeQuery();
        if (rs == null) {
            throw new AutoCreateUnCheckException("数据库表为空，请先创建表！");
        }
        List<Table> tableList = new ArrayList<>();
        while (rs.next()) {
            String tableName = rs.getString(1);
            Table t = new Table();
            t.setTableName(tableName);
            t.setClassName(DB2ClassUtil.a_b2AB(tableName));
            String getFieldSql = "select col.column_name,col.data_type,case when cons.position is null then '' else 'PRI' end as position,com.comments " +
                    "from user_tab_columns col " +
                    "left join user_col_comments com on col.table_name=com.table_name and col.column_name=com.column_name " +
                    "left join user_cons_columns cons on cons.table_name = col.table_name and cons.column_name=col.column_name  and cons.position is not null " +
                    "where col.table_Name='" + tableName +
                    "'order by col.column_id asc";
            List<Field> fieldList = new ArrayList<>();
            List<Field> keyList = new ArrayList<>();
            Set<String> typeSet = new HashSet<>();
            ResultSet fieldsRs = conne.prepareStatement(getFieldSql).executeQuery();
            while (fieldsRs.next()) {
                Field field = new Field();
                field.setFieldNameInDB(fieldsRs.getString(1));
                field.setFieldNameInClass(DB2ClassUtil.a_b2aB(fieldsRs.getString(1)));
                field.setTypeInDB(fieldsRs.getString(2));
                field.setTypeInClass(DB2ClassUtil.db2JavaType(fieldsRs.getString(2)));
                field.setKey(fieldsRs.getString(3));
                field.setComment(fieldsRs.getString(4));
                fieldList.add(field);
                if ("PRI".equalsIgnoreCase(field.getKey())) {
                    keyList.add(field);
                }
                typeSet.add(DB2ClassUtil.db2UniqueType(fieldsRs.getString(2)));
            }
            t.setFieldList(fieldList);
            t.setPrimaryKeyList(keyList);
            t.setTypeSet(typeSet);
            tableList.add(t);
        }
        rs.close();
        conne.close();
        return tableList;
    }

}
