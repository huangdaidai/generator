package com.dd.autoCreate.model;

import java.util.List;

import lombok.Data;

@Data
public class Table {
	private String tableName;
	private String className;
	private List<Field> fieldList;
	private Field primaryKey;
}
