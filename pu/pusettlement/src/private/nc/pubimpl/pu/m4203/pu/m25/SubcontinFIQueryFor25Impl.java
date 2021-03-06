package nc.pubimpl.pu.m4203.pu.m25;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m4203.pu.m25.ISubcontinFIQueryFor25;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 委托加工入副本提供给发票的查询服务实现
 * 
 * @since 6.0
 * @version 2011-4-8 下午06:41:59
 * @author 田锋涛
 */

public class SubcontinFIQueryFor25Impl implements ISubcontinFIQueryFor25 {

  @Override
  public Map<String, String> queryQualityMaterial(String[] itemIds)
      throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_5.name());
      sql.append("select po_subcontinfi_b.pk_stockps_b,");// bid
      sql.append("       po_subcontinfi_b.pk_batchcode ");// 批次
      sql.append("  from po_subcontinfi_b inner join bd_materialstock ");
      sql.append("    on po_subcontinfi_b.pk_material");
      sql.append("       =bd_materialstock.pk_material");
      sql.append("   and po_subcontinfi_b.pk_org");
      sql.append("       = bd_materialstock.pk_org ");// 库存组织
      sql.append(" where po_subcontinfi_b.dr=0 ");
      sql.append("   and  ");
      sql.append(builder.buildSQL("po_subcontinfi_b.pk_stockps_b", itemIds));// bid
      sql.append("   and isnull(bd_materialstock.wholemanaflag,'N')='Y' ");// 批次管理
      sql.append("   and isnull(bd_materialstock.chkfreeflag,'N')='N'");// 非免检

      DataAccessUtils util = new DataAccessUtils();
      IRowSet rowSet = util.query(sql.toString());
      Map<String, String> idMap = new HashMap<String, String>();
      while (rowSet.next()) {
        idMap.put(rowSet.getString(0), rowSet.getString(1));
      }
      return idMap;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
