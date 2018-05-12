package com.dd.generator.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Table {
	private String tableName;

    @JsonIgnore
	private String className;

    @JsonIgnore
	private List<Field> fieldList;

    private List<String> fieldNameList;

    @JsonIgnore
    private List<Field> primaryKeyList;

    @JsonIgnore
    private Set<String> typeSet;
}
