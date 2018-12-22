package ${namespacePackage};

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import ${entityPackage}.${entityName};

@Repository
public interface ${daoName} {
 
 	<#list columns as column>
 		<#if keyColumn?? && column.dbColumn == keyColumn><#assign keyJavaType=column.colJavaType keyField=column.beanField></#if>
 	</#list>
 	<#if keyColumn??>
 	/**
 	* 以主键获取<#if tableComment??>${tableComment}</#if>
 	*/
	${entityName} get(${keyJavaType} ${keyField});
 	</#if>
 	
 	/**
 	* 获取<#if tableComment??>${tableComment}</#if>条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<${entityName}> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存<#if tableComment??>${tableComment}</#if>
 	*/
	Integer save(${entityName} ${entityName?uncap_first});
	
	/**
 	* 更新<#if tableComment??>${tableComment}</#if>
 	*/
	Integer update(${entityName} ${entityName?uncap_first});

	/**
 	* 删除<#if tableComment??>${tableComment}</#if>
 	*/
    Integer del(Integer id);
 
}