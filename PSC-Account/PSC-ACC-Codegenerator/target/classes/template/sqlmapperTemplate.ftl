<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#macro mapperEl value>${r"#{"}${value}}</#macro>
<#if keyColumn??>
	<#list columns as column>
		<#if column.dbColumn == keyColumn><#assign beanKeyField = column.beanField></#if>
	</#list>
</#if>

<!-- namespace和dao对应  -->
<mapper namespace="${namespacePackage}.${daoName}">
	<resultMap id="${resultMapId}" type="${entityPackage}.${entityName}">
		<#list columns as column>
			<result property="${column.beanField}" column="${column.dbColumn}" />
		</#list>
	</resultMap>
	
	<select id="get" parameterType="Integer" resultMap="${resultMapId}">
		select
		<#list columns as column>
			${column.dbColumn}<#if column_index != (columns?size - 1)>,</#if>
		</#list>
		from ${tableName}
		<#if keyColumn??>
		where ${keyColumn} = <@mapperEl beanKeyField/>
		</#if>
	</select>
	
	<select id="getCount" parameterType="java.util.Map"
		resultType="java.lang.Integer">
		select count(1) from ${tableName} t1
		<include refid="whereConditions" />
	</select>
	
	<select id="getList" resultType="${entityPackage}.${entityName}">
		select 
		<#list columns as column>
			${column.dbColumn}<#if column_index != (columns?size - 1)>,</#if>
		</#list>
		from ${tableName}
		<include refid="whereConditions" />
	</select>
	
	<!-- 查询条件 -->
	<sql id="whereConditions">
		where 1=1
		<trim  suffixOverrides="," >
		<#list columns as column>
		<if test="${column.beanField} != null"  > 
			and ${column.dbColumn}= <@mapperEl column.beanField/>
		</if>
		</#list>
		</trim>
	</sql>
	
	<delete id="del">
        delete from ${tableName} where id = ${r'#{id}'}
	</delete>
	
	<#if keyColumn??>
	<update id="update" parameterType="${entityPackage}.${entityName}">
        update ${tableName}
    	<set>
		<#list columns as column>
			<if test="${column.beanField} != null">${column.dbColumn}= <@mapperEl column.beanField/><#if column_index != (columns?size - 1)>,</#if></if>
		</#list>
	    </set>
        where ${keyColumn} = <@mapperEl beanKeyField/>
	</update>
	
	<insert id="save" parameterType="${entityPackage}.${entityName}" useGeneratedKeys="true" keyProperty="${beanKeyField}" keyColumn="${keyColumn}">
		insert into 
			${tableName}
		(
		<#list columns as column>
			<#if column.dbColumn != keyColumn>${column.dbColumn}<#if column_index != (columns?size - 1)>,</#if></#if>
		</#list>
		)
		values
		(
	    <#list columns as column>
			<#if column.dbColumn != keyColumn><@mapperEl column.beanField/><#if column_index != (columns?size - 1)>,</#if></#if>
		</#list>
		)
	</insert>
	</#if>
	
</mapper>