package com.dd.autoCreate.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Table {
	private String tableName;
	private String className;
	private List<Field> fieldList;
	private List<Field> primaryKeyList;
	private Set<String> typeSet;
}
