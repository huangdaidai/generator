package ${package}.service;

import ${package}.model.${table.className};
import com.github.pagehelper.PageInfo;
import java.util.List;
/**
* author: ${author}
* createdOn: ${now?date}
*/
public interface ${table.className}Service {
	/**
	 * 插入记录
	 * 
	 * @param t
	 * @return
	 */
	public int insert(${table.className} ${table.className?uncap_first});

	/**
	 * 删除记录
	 * 
	 * @param t
	 * @return
	 */
	public int deleteOne(String id);
	/**
	 * 更新记录
	 * 
	 * @param t
	 * @return
	 */
	public int update(${table.className} ${table.className?uncap_first});

	/**
	 * 查询列表
	 * 
	 * @param t
	 * @return
	 */
	public List<${table.className}> select(${table.className} ${table.className?uncap_first});

	/**
	 * 查询列表--分页
	 * 
	 * @param t
	 * @return
	 */
	public PageInfo<${table.className}> selectPage(${table.className} ${table.className?uncap_first});

	/**
	 * 查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public ${table.className} selectOne(String id);
}
