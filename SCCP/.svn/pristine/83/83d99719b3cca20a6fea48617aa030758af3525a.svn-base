package cn.wellcare.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import cn.wellcare.entity.SysCodeMaster;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.service.SysCodeMasterService;

/**
 * 字典初始化类
 * 
 * @author zhaihl
 *
 */
public class CodeManager {

	private static Logger log = Logger.getLogger(CodeManager.class);

	private static SysCodeMasterService codeService;

	public static void setCodeService(SysCodeMasterService codeService) {
		CodeManager.codeService = codeService;
	}

	public static Map<String, List<SysCodeMaster>> codeMap;

	public static Map<String, List<SysCodeMaster>> getCodeMap() {
		return codeMap;
	}

	/**
	 * 初始化字典组件。
	 * <p>
	 * 该方法用于容器启动时初始化字典组件，以及生产环境中数据字典变更时的重新初始化，<br/>
	 * 正式环境由于部署多台，需要将字典数据在发版前导入数据库中。
	 */
	public static void init() {
		Map<String, Object> map = new HashMap<>();
		map.put("useYn", SysCodeMaster.STATUS_USE);
		ServiceResponse<List<SysCodeMaster>> serviceResult = codeService.page(map, null);
		if (!serviceResult.getSuccess()) {
			throw new RuntimeException(serviceResult.getMsgInfo());
		}
		codeMap = new HashMap<String, List<SysCodeMaster>>();
		List<SysCodeMaster> divList = new ArrayList<SysCodeMaster>();
		for (SysCodeMaster code : serviceResult.getData()) {
			divList = codeMap.get(code.getCodeDiv());
			if (divList == null) {
				divList = new ArrayList<SysCodeMaster>();
			}
			divList.add(code);
			codeMap.put(code.getCodeDiv(), divList);
		}
		log.info("=========CodeManager初始化完成===============" + codeMap.size());
	}

	public static List<SysCodeMaster> getCodes(String codeDiv) {
		return codeMap.get(codeDiv);
	}

	public static String getCodesJsonByDivs(Object[] codeDivs) {
		Map<String, Map<String, String>> divMap = new HashMap<String, Map<String, String>>();
		Map<String, String> cdMap = new HashMap<String, String>();
		List<SysCodeMaster> lstCodes = new ArrayList<SysCodeMaster>();
		for (Object codeDiv : codeDivs) {
			lstCodes = codeMap.get(codeDiv.toString());
			if (lstCodes != null) {
				cdMap = new HashMap<String, String>();
				for (SysCodeMaster code : lstCodes) {
					cdMap.put(code.getCodeCd(), code.getCodeText());
				}
				divMap.put(codeDiv.toString(), cdMap);
			}
		}
		return JSON.toJSONString(divMap);
	}

	public static String getCodeText(String codeDiv, String codeCd) {
		String codeText = "";
		List<SysCodeMaster> codes = codeMap.get(codeDiv);
		for (SysCodeMaster code : codes) {
			if (code.getCodeCd().equals(codeCd)) {
				codeText = code.getCodeText();
				break;
			}
		}
		return codeText;
	}

	public static List<SysCodeMaster> getFilterCodes(String codeDiv, List<String> excludeCodeCDs) {
		List<SysCodeMaster> codes = codeMap.get(codeDiv);
		for (SysCodeMaster code : codes) {
			if (excludeCodeCDs.contains(code.getCodeCd())) {
				codes.remove(code);
			}
		}
		return codes;
	}

}