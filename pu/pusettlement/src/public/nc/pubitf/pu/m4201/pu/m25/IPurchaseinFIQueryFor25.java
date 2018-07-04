package nc.pubitf.pu.m4201.pu.m25;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 采购入副本 提供给发票的查询服务
 * 
 * @since 6.0
 * @version 2011-4-8 下午06:32:05
 * @author 田锋涛
 */

public interface IPurchaseinFIQueryFor25 {
  /**
   * 根据入库行id查询改行对应的物料，物料满足：
   * <ul>
   * <li>非免检
   * <li>具有批次管理属性
   * </ul>
   * 
   * @param itemIds
   * @return
   * @throws BusinessException
   */
  Map<String, String> queryQualityMaterial(String[] itemIds)
      throws BusinessException;
}
