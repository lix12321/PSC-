package cn.wellcare.api.stock;

import java.util.List;
import java.util.Map;

import cn.wellcare.card.entity.stock.SkCardStocks;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.pojo.RpcResult;

public interface ISkCardStocksService {

	/**
	 * 根据id取得sk_card_stocks对象
	 * 
	 * @param skCardStocksId
	 * @return
	 */
	RpcResult<SkCardStocks> getSkCardStocksById(Integer skCardStocksId);

	/**
	 * 保存sk_card_stocks对象
	 * 
	 * @param skCardStocks
	 * @return
	 */
	RpcResult<Integer> saveSkCardStocks(SkCardStocks skCardStocks);

	/**
	 * 更新sk_card_stocks对象
	 * 
	 * @param skCardStocks
	 * @return
	 */
	RpcResult<Integer> updateSkCardStocks(SkCardStocks skCardStocks);

	/**
	 * 分页查询
	 * 
	 * @param queryMap
	 * @param pager
	 * @return
	 */
	RpcResult<List<SkCardStocks>> page(Map<String, Object> queryMap, PagerInfo pager);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	RpcResult<Boolean> del(Integer id);
}