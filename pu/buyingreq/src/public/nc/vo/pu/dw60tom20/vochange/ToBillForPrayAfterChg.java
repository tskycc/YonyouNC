package nc.vo.pu.dw60tom20.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class ToBillForPrayAfterChg implements IChangeVOAdjust {

	@Override
	public AggregatedValueObject adjustBeforeChange(
			AggregatedValueObject srcVO, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
			AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		// 王梓懿 2018-05-16 增加对于单个编辑后的设置
		AggregatedValueObject[] srcVOs = new AggregatedValueObject[] { srcVO };
		AggregatedValueObject[] destVOs = new AggregatedValueObject[] { destVO };
		return this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext)[0];
	}

	@Override
	public AggregatedValueObject[] batchAdjustBeforeChange(
			AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	/*
	 * 1. 根据请购单的来源单据主键查中间表里的物料编码，计量单位名称，组织，
	 * 2.通过中间件表物料编码，计量单位名称，查出对应的相关档案主键，赋值给请购单对应的字段
	 */

	String pk_group = "0001N610000000000IT0";// 组织，集团直接写死
	String pk_org = "0001N610000000000IT0";

	@Override
	public AggregatedValueObject[] batchAdjustAfterChange(
			AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
			ChangeVOAdjustContext adjustContext) throws BusinessException {
		IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);

		if (destVOs == null || destVOs.length == 0) {
			return destVOs;
		}

		PraybillVO newaggvo = new PraybillVO();
		newaggvo.setParentVO(destVOs[0].getParentVO());
		List<PraybillItemVO> newparbvos = new ArrayList<PraybillItemVO>();
		for (AggregatedValueObject aggvo : destVOs) {
			PraybillItemVO[] bvos = (PraybillItemVO[]) aggvo.getChildrenVO();
			for (PraybillItemVO bvo : bvos) {
				String wucode = bvo.getPk_material();// 物料编码
				String wuname = bvo.getMaterialname();// 物料名称
				String sqlmact = "select * from bd_material where code ='"
						+ wucode + "' and nvl(dr,0)=0 " + " and name='"
						+ wuname + "'";// 物料档案
				MaterialVO matervo = (MaterialVO) bs.executeQuery(sqlmact,
						new BeanProcessor(MaterialVO.class));
				if (matervo != null) {
					bvo.setPk_material(matervo.getPk_material());// 请购单物料
					bvo.setPk_srcmaterial(matervo.getPk_material());// 请购单物料信息
					bvo.setCunitid(matervo.getPk_measdoc());// 计量单位
					bvo.setCastunitid(matervo.getPk_measdoc());// 单位
				}
				int size = 0;
				if (newparbvos != null) {
					size = newparbvos.size();
				}
				bvo.setCrowno((size + 1) + "0");// 重新赋值行号
				newparbvos.add(bvo);
			}

		}
		newaggvo.setChildrenVO(newparbvos.toArray(new PraybillItemVO[0]));
		return new AggregatedValueObject[] { newaggvo };
	}
}
