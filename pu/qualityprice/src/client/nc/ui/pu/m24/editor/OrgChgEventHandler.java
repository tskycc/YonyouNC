package nc.ui.pu.m24.editor;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������֯�������֯���ı仯�¼���
 * <li>��ձ�ͷ�������У�����ֶε�ֵ����������Ϊ�޸ĵ���״̬
 * <li>��������
 * <li>���ò��շ�Χ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-2 ����10:56:23
 */
public class OrgChgEventHandler extends AbstractOrgChangedEventHandler {

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new StockOrg());
  }
}