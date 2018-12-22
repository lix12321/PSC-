package ${modelPackage};

import ${namespacePackage}.${daoName};
import ${entityPackage}.${entityName};
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;

@Component
public class ${modelName} {

	private Logger log = Logger.getLogger(this.getClass());
    
    @Resource
    private ${daoName} ${daoName?uncap_first};
    
    /**
     * 根据id取得<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>
     * @param  ${entityName?uncap_first}Id
     * @return
     */
    public ${entityName} get${entityName}ById(Integer ${entityName?uncap_first}Id) {
    	return ${daoName?uncap_first}.get(${entityName?uncap_first}Id);
    }
    
    /**
     * 保存<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>
     * @param  ${entityName?uncap_first}
     * @return
     */
     public Integer save${entityName}(${entityName} ${entityName?uncap_first}) {
     	this.dbConstrains(${entityName?uncap_first});
     	return ${daoName?uncap_first}.save(${entityName?uncap_first});
     }
     
     /**
     * 更新<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>
     * @param  ${entityName?uncap_first}
     * @return
     */
     public Integer update${entityName}(${entityName} ${entityName?uncap_first}) {
     	this.dbConstrains(${entityName?uncap_first});
     	return ${daoName?uncap_first}.update(${entityName?uncap_first});
     }
     
     private void dbConstrains(${entityName} ${entityName?uncap_first}) {
     	<#list columns as column>
 		<#if column.colJavaType == 'java.lang.String'>
		${entityName?uncap_first}.set${column.beanField?cap_first}(CommonUtils.dbSafeString(${entityName?uncap_first}.get${column.beanField?cap_first}(), <#if column.isNullable == 'NO' || column.isNullable == 'no'>false<#else>true</#if>, ${column.characterMaximumLength?c}));
 		</#if>
     	</#list>
     }
     
    public List<${entityName}> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
     	Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(${daoName?uncap_first}.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
 			param.put("start", start);
            param.put("size", size);
            List<${entityName}> list = ${daoName?uncap_first}.getList(param);
        return list;
    }
     
      public Integer del(Integer id) {
        return ${daoName?uncap_first}.del(id);
     }
     
     
}