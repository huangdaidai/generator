package com.dd.generator.service;

import java.util.Map;

public interface CreatBackStageService {
	void creatModel(String url,Map<String,Object> param);
	void createDaos(String url,Map<String,Object> param);
	void createDaoImpls(String url,Map<String,Object> param);
	void createMapper(String url,Map<String,Object> param);
	void creatService(String url,Map<String,Object> param);
	void creatServiceImpl(String url,Map<String,Object> param);
	void creatControllor(String url,Map<String,Object> param);
	void creatConfig(Map<String,Object> param);
}
