<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<!--
* author: ${author}
* ${now?date}
-->
<mapper namespace="${package}.dao.impl.${table.className}DaoImpl">
    <resultMap id="${table.className?uncap_first}" type="${table.className}">
    <#list fields as f>
        <#if f.key=='PRI'>
        <id property="${f.field}" column="${f.field_}"/>
        </#if>
    </#list>
    <#list fields as f>
        <#if f.key!='PRI'>
        <result property="${f.field}" column="${f.field_}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="columns">
       <#list fields as f>${f.field_}<#sep>, </#list>
    </sql>

    <insert id="insert">
        INSERT INTO ${table.tableName}
        (<#list fields as f>
        	${f.field_}<#sep>,</#list>
        )
        VALUES 
        (<#list fields as f>
            ${'#{'}${f.field}${'}'}<#sep>,</#list>
        )
    </insert>

    <delete id="deleteOne">
        DELETE FROM ${table.tableName}
        <trim prefix="where " prefixOverrides="and ">
        <#list fields as f>
            <if test="${f.field} != null">
                AND ${f.field_} = ${r'#{'}${primaryKeyField.field}${r'}'}
            </if>
        </#list>
        </trim>
    </delete>

    <update id="update">
        UPDATE ${table.tableName}
        <set><#list fields as f>
            ${f.field_} = ${r'#{'}${f.field}${r'}'}<#sep>, </#list>
        </set>
        WHERE ID = ${r'#{ID}'}
    </update>

    <select id="select" resultMap="${table.className?uncap_first}">
        SELECT
        <include refid="columns" />
        FROM ${table.tableName}
        <trim prefix="where " prefixOverrides="and ">
        <#list fields as f>
            <if test="${f.field} != null">
            AND ${f.field_} = ${r'#{'}${f.field}${r'}'}
            </if>
        </#list>
        </trim>
    </select>
    
    <select id="selectOne" resultMap="${table.className?uncap_first}">
        SELECT
        <include refid="columns" />
        FROM ${table.tableName}
        WHERE 
        <#list fields as f>
	        <#if f.key=='PRI'>
	        ${f.field_}=${r'#{'}${f.field}${r'}'}
	        </#if>
	    </#list>
    </select>
</mapper>