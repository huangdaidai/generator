package com.dd.autoCreate.service;

import java.sql.SQLException;
import java.util.List;

import com.dd.autoCreate.model.DataSource;
import com.dd.autoCreate.model.Field;
import com.dd.autoCreate.model.Table;

public interface UserDBService {
	List<Table> loadTables(DataSource dataSource) throws SQLException;
}
