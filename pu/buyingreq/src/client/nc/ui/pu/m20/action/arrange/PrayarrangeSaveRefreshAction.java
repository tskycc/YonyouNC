/**
 * 
 */
package nc.ui.pu.m20.action.arrange;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.IBatchValidationService;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m20.action.LotsArrangePraybillsAction;
import nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.uif2.editor.BatchBillTable;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-26 下午4:07:35
 */
public class PrayarrangeSaveRefreshAction extends BatchSaveAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -2395913125669020523L;

  private IModelDataManager dataManager;

  /*
   * 父类方法重写
   * @see
   * nc.ui.uif2.actions.batch.BatchSaveAction#doAction(java.awt.event.ActionEvent
   * )
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.check();
    super.doAction(e);
    this.getDataManager().refresh();
  }

  /**
   * @return the dataManager
   */
  public IModelDataManager getDataManager() {
    return this.dataManager;
  }

  /**
   * @param dataManager the dataManager to set
   */
  public void setDataManager(IModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  private void check() {
    List<Object> views = this.getModel().getRows();
    List<Integer> rows = new ArrayList<Integer>();
	for (int j = 0; j < views.size(); j++) {
      PrayarrangeViewVO view = (PrayarrangeViewVO) views.get(j);
	      
      if (view.getPk_purchaseorg() == null) {
        rows.add(Integer.valueOf(j + 1));
      }
    }
    if (rows.size() == 0) {
      return;
    }
    String errMsg =
        NCLangRes.getInstance().getStrByID("4004020_0", "04004020-0112", null,
            new String[] {
              rows.toString()
            })/* 第{0}行采购组织不允许为空！ */;
    ExceptionUtils.wrappBusinessException(errMsg);
  }
}
