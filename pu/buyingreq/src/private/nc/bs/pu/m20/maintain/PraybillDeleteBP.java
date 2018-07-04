/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 ����11:02:22
 */
package nc.bs.pu.m20.maintain;

import nc.bs.pu.m20.maintain.rule.ATPAfterUpdateRule;
import nc.bs.pu.m20.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m20.maintain.rule.ReturnBillCodeRule;
import nc.bs.pu.m20.maintain.rule.delete.BillStatusRule;
import nc.bs.pu.m20.maintain.rule.delete.CheckSrcRule;
import nc.bs.pu.m20.maintain.rule.delete.DelOldVersionRule;
import nc.bs.pu.m20.maintain.rule.delete.DeleteAfterEventRule;
import nc.bs.pu.m20.maintain.rule.delete.DeleteBeforeEventRule;
import nc.bs.pu.m20.maintain.rule.delete.RwritegfjktdeldataRule;
import nc.bs.pu.m20.maintain.rule.delete.RwriteksjktdeldataRule;
import nc.bs.pu.m20.maintain.rule.delete.RwritepgjktdeldataRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.pub.rule.WriteBack422xRule;
import nc.vo.pu.pub.rule.WriteBackM4A08Rule;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺��ɾ��BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 ����11:02:22
 */
public class PraybillDeleteBP {

  public void delete(PraybillVO[] vos) {

    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(PraybillPluginPoint.DELETE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    processer.before(vos, vos);
    BillDelete<PraybillVO> bo = new BillDelete<PraybillVO>();
    bo.delete(vos);

    processer.after(vos, vos);

  }

  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer) {
    // ɾ���ϰ汾
    processer.addAfterRule(new DelOldVersionRule());

    // ��д����
    processer.addAfterRule(new PraybillWriteBackSourceRule(EnumOperate.DELETE));
    // ɾ�������������
    processer.addAfterRule(new ATPAfterUpdateRule());
    // �˻ص��ݺ�
    processer.addAfterRule(new ReturnBillCodeRule());
    // дҵ����־
    // processer.addAfterRule(new WriteBusiLogRule<PraybillVO>(
    // PuBusiLogActionCode.delete));

    // �빺����ɾ������ã�Ϊ�˵����빺������ATP����
    // processer.addAfterRule(new Update55A3StatusRule());
    processer.addAfterRule(new WriteBackPIMRule<PraybillVO>(POBillType.PrayBill
        .getCode()));

    // ��д�ʲ����������־λ
    processer.addAfterRule(new WriteBackM4A08Rule());

    processer.addAfterRule(new WriteBack422xRule<PraybillVO>(
        POBillType.PrayBill.getCode()));

    // ����ɾ�����¼�
    processer.addAfterRule(new DeleteAfterEventRule());
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // ����״̬���
    processer.addBeforeFinalRule(new BillStatusRule());
    // �����Դ
    processer.addBeforeFinalRule(new CheckSrcRule());

    // ɾ��ǰ����������
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    // ����ɾ��ǰ�¼�
    processer.addBeforeRule(new DeleteBeforeEventRule());
    
    //��������ͷ��Դ�������������ʸֲɹ��ƻ��ӿ��м��
    processer.addBeforeRule(new RwritepgjktdeldataRule());
    
    //��������ͷ��Դ�����������¹ɷݲɹ��ƻ��ӿ��м��
    processer.addBeforeRule(new RwritegfjktdeldataRule());
    
    //��������ͷ��Դ�����������¿�ɽ�ɹ��ƻ��ӿ��м��
     processer.addBeforeRule(new RwriteksjktdeldataRule());

    // ����ƻ�����ȡ���´�
    // processer.addBeforeRule(new RearOrderDelete());
  }
}