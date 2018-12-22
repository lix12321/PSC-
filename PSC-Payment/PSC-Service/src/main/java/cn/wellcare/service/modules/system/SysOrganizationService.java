package cn.wellcare.service.modules.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.Md5;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.core.utils.RandomUtil;
import cn.wellcare.entity.system.SysOrganization;
import cn.wellcare.model.modules.system.SysOrganizationModel;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "sysOrganizationService")
public class SysOrganizationService implements ISysOrganizationService {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private SysOrganizationModel sysOrganizationModel;

	/**
	 * 根据id取得机构
	 * 
	 * @param sysOrganizationId
	 * @return
	 */
	@Override
	public RpcResult<SysOrganization> getSysOrganizationById(Integer sysOrganizationId) {
		RpcResult<SysOrganization> serviceResult = new RpcResult<SysOrganization>();
		try {
			serviceResult.setData(sysOrganizationModel.getSysOrganizationById(sysOrganizationId));
		} catch (Exception e) {
			log.error("[ISysOrganizationService][getSysOrganizationById]根据id[" + sysOrganizationId + "]取得机构时出现未知异常：",
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
	 * 保存机构
	 * 
	 * @param sysOrganization
	 * @return
	 */
	@Override
	public RpcResult<Integer> saveSysOrganization(SysOrganization sysOrganization) {
		RpcResult<Integer> serviceResult = new RpcResult<Integer>();
		try {
			// 机构密码默认为123456
			sysOrganization.setAuthSecret(Md5.getMd5String(BaseParam.ORG_PASSWORD));
			// 认证密钥随机生成16位加密的随机数
			sysOrganization.setAuthSecret(Md5.getMd5String(RandomUtil.randomNumber(16)));
			serviceResult.setData(sysOrganizationModel.saveSysOrganization(sysOrganization));
		} catch (Exception e) {
			log.error("[ISysOrganizationService][saveSysOrganization]保存机构时出现未知异常：", e);
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
	 * 更新机构
	 * 
	 * @param sysOrganization
	 * @return
	 * @see cn.wellcare.service.system.SysOrganizationService#updateSysOrganization(SysOrganization)
	 */
	@Override
	public RpcResult<Integer> updateSysOrganization(SysOrganization sysOrganization) {
		RpcResult<Integer> serviceResult = new RpcResult<Integer>();
		try {
			// 如果密码被修改，
			if (sysOrganization != null && !StringUtils.isEmpty(sysOrganization.getAuthPwd())) {
				sysOrganization.setAuthPwd(Md5.getMd5String(sysOrganization.getAuthPwd()));
			}
			serviceResult.setData(sysOrganizationModel.updateSysOrganization(sysOrganization));
		} catch (Exception e) {
			log.error("[ISysOrganizationService][updateSysOrganization]更新机构时出现未知异常：", e);
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
	public RpcResult<List<SysOrganization>> page(Map<String, Object> queryMap, PagerInfo pager) {
		RpcResult<List<SysOrganization>> serviceResult = new RpcResult<List<SysOrganization>>();
		try {
			serviceResult.setData(sysOrganizationModel.page(queryMap, pager));
		} catch (Exception e) {
			log.error("[SysOrganizationService][page] exception:" + e.getMessage());

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
	public RpcResult<Boolean> del(Integer id) {
		RpcResult<Boolean> serviceResult = new RpcResult<Boolean>();
		try {
			serviceResult.setData(sysOrganizationModel.del(id) > 0);
		} catch (Exception e) {
			log.error("[SysOrganizationService][del] exception:" + e.getMessage());

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
	public RpcResult<List<SysOrganization>> getByAuthName(String username) {
		RpcResult<List<SysOrganization>> serviceResult = new RpcResult<>();
		try {
			serviceResult.setData(sysOrganizationModel.getByAuthName(username));
		} catch (Exception e) {
			log.error("[SysOrganizationService][getByAuthName] exception:" + e.getMessage());

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