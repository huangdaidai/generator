package com.dd.generator.service;

import java.sql.SQLException;
import java.util.List;

import com.dd.generator.model.DataSource;
import com.dd.generator.model.Table;

public interface UserDBService {
	List<Table> loadTables(DataSource dataSource) throws SQLException;
}
