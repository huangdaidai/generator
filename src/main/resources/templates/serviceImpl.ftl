package ${package}.service.impl;

import ${package}.model.${table.className};
import ${package}.dao.${table.className}Dao;
import ${package}.service.${table.className}Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
* author: ${author}
* createdOn: ${now?date}
*/
@Service("${table.className?uncap_first}Service")
public class ${table.className}ServiceImpl implements ${table.className}Service{
	
	@Autowired
	${table.className}Dao ${table.className?uncap_first}Dao;
	
	@Override
	public int insert(${table.className} ${table.className?uncap_first}) {
		return ${table.className?uncap_first}Dao.insert(${table.className?uncap_first});
	}

	@Override
	public int deleteOne(String id) {
		return ${table.className?uncap_first}Dao.deleteOne(id);
	}

	@Override
	public int update(${table.className} ${table.className?uncap_first}) {
		return ${table.className?uncap_first}Dao.update(${table.className?uncap_first});

	}

	@Override
	public List<${table.className}> select(${table.className} ${table.className?uncap_first}) {
		return ${table.className?uncap_first}Dao.select(${table.className?uncap_first});
	}

	@Override
	public PageInfo<${table.className}> selectPage(${table.className} ${table.className?uncap_first}) {
		return ${table.className?uncap_first}Dao.selectPage(${table.className?uncap_first});
	}

	@Override
	public ${table.className} selectOne(String id) {
		return ${table.className?uncap_first}Dao.selectOne(id);
	}
}
