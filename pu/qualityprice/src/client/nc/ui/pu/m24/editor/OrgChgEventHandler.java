package nc.ui.pu.m24.editor;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理主组织（库存组织）的变化事件：
 * <li>清空表头、表体中，相关字段的值，并把其置为修改的行状态
 * <li>处理精度
 * <li>设置参照范围
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-2 上午10:56:23
 */
public class OrgChgEventHandler extends AbstractOrgChangedEventHandler {

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new StockOrg());
  }
}
