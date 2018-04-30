package ${package}.dao.impl;

import ${package}.model.${table.className};
import ${package}.dao.${table.className}Dao;
import org.springframework.stereotype.Repository;
/**
* author: ${author}
* ${now?date}
*/
@Repository("${table.className?uncap_first}Dao")
public class ${table.className}DaoImpl extends BaseDaoImpl<${table.className}>
	implements ${table.className}Dao{
   
}