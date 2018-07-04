package nc.ui.pu.m20.action;

import nc.ui.pu.pub.action.PUEditAction;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单的编辑按钮动作类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-5 上午10:45:07
 */
public class PraybillEditAction extends PUEditAction {
  private static final long serialVersionUID = -116097492156325868L;

  @Override
  protected boolean isActionEnable() {

    PraybillVO bill = (PraybillVO) this.getModel().getSelectedData();
    return bill != null && ApproveFlowUtil.isCanEdit(bill);
  }

}
