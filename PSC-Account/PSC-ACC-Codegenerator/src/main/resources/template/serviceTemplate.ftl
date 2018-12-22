package ${servicePackage}.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.pojo.common.ServiceResult;
import ${modelPackage}.${modelName};
import ${servicePackage}.${serviceName};
import ${entityPackage}.${entityName};

@Service(value = "${serviceName?uncap_first}")
public class ${serviceName} implements I${serviceName} {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ${modelName} ${modelName?uncap_first};
    
     /**
     * 根据id取得<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>
     * @param  ${entityName?uncap_first}Id
     * @return
     */
    @Override
    public ServiceResult<${entityName}> get${entityName}ById(Integer ${entityName?uncap_first}Id) {
        ServiceResult<${entityName}> serviceResult = new ServiceResult<${entityName}>();
        try {
            serviceResult.setData(${modelName?uncap_first}.get${entityName}ById(${entityName?uncap_first}Id));
        } catch (Exception e) {
            log.error("[I${serviceName}][get${entityName}ById]根据id["+${entityName?uncap_first}Id+"]取得<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>时出现未知异常：",
                e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
    }
    
    /**
     * 保存<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>
     * @param  ${entityName?uncap_first}
     * @return
     */
     @Override
     public ServiceResult<Integer> save${entityName}(${entityName} ${entityName?uncap_first}) {
     	ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setData(${modelName?uncap_first}.save${entityName}(${entityName?uncap_first}));
        } catch (Exception e) {
            log.error("[I${serviceName}][save${entityName}]保存<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>时出现未知异常：",
                e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
     }
     
     /**
     * 更新<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>
     * @param  ${entityName?uncap_first}
     * @return
     * @see ${servicePackage}.${serviceName}#update${entityName}(${entityName})
     */
     @Override
     public ServiceResult<Integer> update${entityName}(${entityName} ${entityName?uncap_first}) {
     	ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setData(${modelName?uncap_first}.update${entityName}(${entityName?uncap_first}));
        } catch (Exception e) {
            log.error("[I${serviceName}][update${entityName}]更新<#if tableComment?? && tableComment?length &gt; 0>${tableComment}<#else>${tableName}对象</#if>时出现未知异常：",
                e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
     }
     
     
    @Override
    public ServiceResult<List<${entityName}>> page(Map<String, Object> queryMap, PagerInfo pager) {
        ServiceResult<List<${entityName}>> serviceResult = new ServiceResult<List<${entityName}>>();
        try {
              serviceResult.setData(${modelName?uncap_first}.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[${entityName}Service][page] exception:" + e.getMessage());
            
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
 		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setData(${modelName?uncap_first}.del(id) > 0);
        } catch (Exception e) {
            log.error("[${entityName}Service][del] exception:" + e.getMessage());
            
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
        }
        return serviceResult;
    }
}