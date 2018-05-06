package com.dd.generator.service;

import java.util.Map;

public interface CreatFrontEndService {
	void creatPageJs(Map<String,Object> param);
	void creatPageFtl(Map<String,Object> param);
	void creatStaticSource();
}
