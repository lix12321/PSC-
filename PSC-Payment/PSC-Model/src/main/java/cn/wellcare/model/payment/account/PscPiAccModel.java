package cn.wellcare.model.payment.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.account.PscPiAccDao;
import cn.wellcare.dao.account.PscPiAccDetailDao;
import cn.wellcare.entity.account.PscPiAccDetail;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.pojo.account.PscPiAcc;
import cn.wellcare.support.EnumerateParameter;

@Component
public class PscPiAccModel {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private PscPiAccDao pscPiAccDao;
	@Resource
	private PscPiAccDetailDao pscPiAccDetailDao;

	/**
	 * 根据id取得psc_pi_acc对象
	 * 
	 * @param pscPiAccId
	 * @return
	 */
	public PscPiAcc getPscPiAccById(Integer pscPiAccId) {
		return pscPiAccDao.get(pscPiAccId);
	}

	/**
	 * 根据id取得psc_pi_acc对象
	 * 
	 * @param
	 * @return
	 */
	public PscPiAcc getPscPiAccBypkPi(String pkPi) {
		return pscPiAccDao.getAccount(pkPi);
	}

	/**
	 * 保存psc_pi_acc对象
	 * 
	 * @param pscPiAcc
	 * @return
	 */
	public Integer savePscPiAcc(PscPiAcc pscPiAcc) {
		this.dbConstrains(pscPiAcc);
		Date nowTime = new Date();
		pscPiAcc.setModityTime(nowTime);
		pscPiAcc.setCreateTime(nowTime);
		pscPiAcc.setEuStatus(EnumerateParameter.ONE);
		pscPiAcc.setDelFlag(EnumerateParameter.ZERO);
		pscPiAcc.setAmtAcc(BigDecimal.ZERO);// 新开账户余额及信用额度置零
		pscPiAcc.setCreditAcc(BigDecimal.ZERO);
		return pscPiAccDao.save(pscPiAcc);
	}

	/**
	 * 更新psc_pi_acc对象
	 * 
	 * @param pscPiAcc
	 * @return
	 */
	public Integer updatePscPiAcc(PscPiAcc pscPiAcc) {
		this.dbConstrains(pscPiAcc);
		Date nowTime = new Date();
		pscPiAcc.setModityTime(nowTime);
		return pscPiAccDao.update(pscPiAcc);
	}

	private void dbConstrains(PscPiAcc pscPiAcc) {
		pscPiAcc.setCodeAcc(CommonUtils.dbSafeString(pscPiAcc.getCodeAcc(), true, 30));
		pscPiAcc.setCreator(CommonUtils.dbSafeString(pscPiAcc.getCreator(), true, 32));
		pscPiAcc.setDelFlag(CommonUtils.dbSafeString(pscPiAcc.getDelFlag(), true, 1));
		pscPiAcc.setEuStatus(CommonUtils.dbSafeString(pscPiAcc.getEuStatus(), true, 2));
		pscPiAcc.setModifier(CommonUtils.dbSafeString(pscPiAcc.getModifier(), true, 32));
		pscPiAcc.setNote(CommonUtils.dbSafeString(pscPiAcc.getNote(), true, 128));
	}

	public List<PscPiAcc> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(queryMap);
		Integer start = 0, size = 0;
		if (pager != null) {
			pager.setRowsCount(pscPiAccDao.getCount(param));
			start = pager.getStart();
			size = pager.getPageSize();
		}
		param.put("start", start);
		param.put("size", size);
		List<PscPiAcc> list = pscPiAccDao.getList(param);
		return list;
	}

	public Integer del(Integer id) {
		return pscPiAccDao.del(id);
	}

	public boolean accRecharge(PayOrder order, Integer accId) {
		try {
			// 1.账户余额
			PscPiAcc acc = pscPiAccDao.get(accId);
			log.debug("账户充值，原余额：" + acc.getAmtAcc().setScale(2, BigDecimal.ROUND_HALF_UP));
			acc.setAmtAcc(acc.getAmtAcc().add(order.getMoneyOrder()).setScale(2, BigDecimal.ROUND_HALF_UP));
			pscPiAccDao.update(acc);
			log.debug("账户充值，充值后余额：" + acc.getAmtAcc().setScale(2, BigDecimal.ROUND_HALF_UP));

			// 2.账户日志
			PscPiAccDetail pscPiAccDetail = new PscPiAccDetail();
			pscPiAccDetail.setAmount(order.getMoneyOrder().setScale(2, BigDecimal.ROUND_HALF_UP));
			pscPiAccDetail.setAmtBalance(acc.getAmtAcc().setScale(2, BigDecimal.ROUND_HALF_UP));

			pscPiAccDetail.setEuOptype(EnumerateParameter.ONE);
			pscPiAccDetail.setEuDirect(Integer.valueOf(EnumerateParameter.ONE));
			pscPiAccDetail.setPkOrg(order.getOrgId());
			pscPiAccDetail.setPkPiacc(accId);
			pscPiAccDetail.setCreator(order.getHandleName());

			Date nowTime = new Date();
			pscPiAccDetail.setModityTime(nowTime);
			pscPiAccDetail.setCreateTime(nowTime);
			pscPiAccDetail.setDelFlag(EnumerateParameter.ZERO);
			pscPiAccDetail.setDateHap(nowTime);

			pscPiAccDetailDao.save(pscPiAccDetail);
			log.debug("账户流水保存成功 ");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}