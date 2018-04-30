package com.dd.autoCreate.model;

import lombok.Data;

@Data
public class DataSource {
	private String url;
	private String port;
	private String userName;
	private String password;
	private String dbType;
	private String dbName;
}
