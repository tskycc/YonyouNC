/**
 * 
 */
package nc.bs.pu.cgfa.ace.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.arap.pub.UFDoubleTool;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VOSortUtils.VOComparator;

/**
 * @author wangzym
 * @version 2017年5月5日 下午6:09:09
 */
public class RewriteForPrayBillUpdateRule implements ICompareRule<AggCgfa> {

	public RewriteForPrayBillUpdateRule() {
		// TODO 自动生成的构造函数存根
	}

	// 删除的来源主键集合
	// private List<String> delIndex = new ArrayList<String>();
	private HashMap<String, UFDouble> delIndex = new HashMap<String, UFDouble>();
	private HashMap<String, UFDouble> numMoreIndex = new HashMap<String, UFDouble>();
	private HashMap<String, UFDouble> numLessIndex = new HashMap<String, UFDouble>();
	// 通过参照增行可新增行 2017-11-29
	private HashMap<String, UFDouble> lineMoreIndex = new HashMap<String, UFDouble>();

	/*
	 * （非 Javadoc）
	 * 
	 * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(java.lang.Object[],
	 * java.lang.Object[])
	 */
	@Override
	public void process(AggCgfa[] vos, AggCgfa[] originVOs) {
		// TODO 只针对表体
		AggCgfa vo = vos[0];
		if (vo.getChildrenVO().length > 0) {
			// 来源为空，不回写
			if (vo.getChildrenVO()[0].getAttributeValue("srcbillpk") == null) {
				return;
			}
		}

		AggCgfa originVO = originVOs[0];
		HashMap<String, UFDouble> res = compareAgg(vo, originVO);
		// delIndex去重

		reWriteDel(delIndex);
		reWrite(res);
		reWriteForLessNum(numLessIndex);
		rewriteForMoreNum(numMoreIndex);
		// 参照增行回写上游
		rewriteForLineMore(lineMoreIndex);

	}

	/**
	 * @Title: rewriteForLineMore
	 * @Description: TODO
	 * @param lineMoreIndex2
	 * @return: void
	 */
	private void rewriteForLineMore(HashMap<String, UFDouble> lineMoreIndex2) {
		// TODO 自动生成的方法存根
		for (Entry<String, UFDouble> map : lineMoreIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
					PraybillItemVO.class);
			PraybillItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("sumcgfanum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nastnum");
			UFDouble num = sum;
			if (has == null) {
			} else {
				num = sum.sub(has);

			}
			// UFDouble num = sum.sub(has);
			int i = num.compareTo(map.getValue());
			if (i >= 0) {
				// 没问题还能做
				UFDouble newHas = UFDoubleTool.sum(has, value); // has.add(value.intValue());
				PraybillItemVO vo = new PraybillItemVO();
				vo.setPrimaryKey(newpk);
				vo.setAttributeValue("sumcgfanum", newHas);

				vo.setAttributeValue("sts_req", "03");

				UFDateTime serverTime = AppContext.getInstance()
						.getServerTime();
				vo.setAttributeValue("bmodifiedtime", serverTime);

				vo.setSts_req("03");

				// 回写上游直接更新
				VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
				update.update(new PraybillItemVO[] { vo }, new String[] {
						"sumcgfanum", "sts_req", "bmodifiedtime" });

			} else {
				// 出错了
				String pk1 = map.getKey();
				String rowNum = pk1.substring(21);
				String message = "第" + rowNum + "行数量已超出上游可用量";
				ExceptionUtils.wrappBusinessException(message);
			}
		}

	}

	/**
	 * @param numMoreIndex2
	 */
	private void rewriteForMoreNum(HashMap<String, UFDouble> numMoreIndex2) {
		// TODO 自动生成的方法存根
		for (Entry<String, UFDouble> map : numMoreIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
					PraybillItemVO.class);
			PraybillItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("sumcgfanum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nastnum");
			UFDouble num = sum;
			if (has == null) {
			} else {
				num = sum.sub(has);

			}
			// UFDouble num = sum.sub(has);
			int i = num.compareTo(map.getValue());
			if (i >= 0) {
				// 没问题还能做
				UFDouble newHas = UFDoubleTool.sum(has, value); // has.add(value.intValue());
				PraybillItemVO vo = new PraybillItemVO();
				vo.setPrimaryKey(newpk);
				vo.setAttributeValue("sumcgfanum", newHas);
				// 回写上游直接更新
				VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
				update.update(new PraybillItemVO[] { vo },
						new String[] { "sumcgfanum" });

			} else {
				// 出错了
				String pk1 = map.getKey();
				String rowNum = pk1.substring(21);
				String message = "第" + rowNum + "行数量已超出上游可用量";
				ExceptionUtils.wrappBusinessException(message);
			}
		}

	}

	/**
	 * @param numLessIndex2
	 */
	private void reWriteForLessNum(HashMap<String, UFDouble> numLessIndex2) {
		// 因为是减少了所以上游已参照量应该减少，不需要校验
		for (Entry<String, UFDouble> map : numLessIndex2.entrySet()) {
			String pk = map.getKey();
			String newpk = pk.substring(0, 20);
			UFDouble value = map.getValue();
			VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
					PraybillItemVO.class);
			PraybillItemVO[] bvo = query.query(new String[] { newpk });
			UFDouble has = (UFDouble) bvo[0].getAttributeValue("sumcgfanum");
			UFDouble sum = (UFDouble) bvo[0].getAttributeValue("nastnum");

			// 因为是减少了所以上游已参照量应该减少，不需要校验
			UFDouble newHas = has.sub(value);
			PraybillItemVO vo = new PraybillItemVO();
			vo.setPrimaryKey(newpk);
			vo.setAttributeValue("sumcgfanum", newHas);
			// 回写上游直接更新
			VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
			update.update(new PraybillItemVO[] { vo },
					new String[] { "sumcgfanum" });
		}
		// TODO 自动生成的方法存根

	}

	/**
	 * @param delIndex2
	 */
	private void reWriteDel(HashMap<String, UFDouble> delIndex2) {
		// TODO 自动生成的方法存根
		// 没有删除行的操作
		if (delIndex2.size() == 0) {
			return;
		} else {

			List<PraybillItemVO> bvosL = new ArrayList<PraybillItemVO>();

			for (Map.Entry<String, UFDouble> entry : delIndex2.entrySet()) {
				VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
						PraybillItemVO.class);
				PraybillItemVO[] bvo = query.query(new String[] { entry
						.getKey() });
				UFDouble oldNum = (UFDouble) bvo[0]
						.getAttributeValue("sumcgfanum");
				UFDouble newNum = oldNum.sub(entry.getValue());
				PraybillItemVO praybillitemvo = new PraybillItemVO();
				praybillitemvo.setPrimaryKey(entry.getKey());
				praybillitemvo.setAttributeValue("whetherlineclose",
						UFBoolean.FALSE);
				praybillitemvo.setAttributeValue("sts_req", "02");
				// 设置成可以参照
				praybillitemvo.setSts_req("02");
				// 释放上游数量
				praybillitemvo.setAttributeValue("sumcgfanum", newNum);
				bvosL.add(praybillitemvo);
			}

			VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
			String[] names = new String[] { "sts_req", "whetherlineclose",
					"sumcgfanum" };
			update.update(bvosL.toArray(new PraybillItemVO[bvosL.size()]),
					names);
		}

	}

	/**
	 * @param vo
	 * @param originVO
	 * @return
	 */
	private HashMap<String, UFDouble> compareAgg(AggCgfa vo, AggCgfa originVO) {
		// TODO 自动生成的方法存根
		// 比较两个vo的表体长度是否一致
		if (vo.getChildrenVO().length == originVO.getChildrenVO().length) {
			// 两者是否确定没有被修改过
			boolean isOrigin = isOrigin((Cgfab[]) vo.getChildrenVO(),
					(Cgfab[]) originVO.getChildrenVO());
			// 如果一致则直接比较
			// 比较的字段（是否行关闭：whetherlineclose ）
			if (isOrigin) {
				Cgfab[] bvos = (Cgfab[]) vo.getChildrenVO();
				Cgfab[] oriBvos = (Cgfab[]) originVO.getChildrenVO();
				HashMap<String, UFDouble> res = new HashMap<String, UFDouble>();
				for (int i = 0; i < bvos.length; i++) {
					Cgfab cgfab = bvos[i];
					Cgfab oriCgfab = oriBvos[i];
					this.caculateNum(cgfab, oriCgfab);
					String newcg = cgfab.getAttributeValue("whetherlineclose") == null ? null
							: cgfab.getAttributeValue("whetherlineclose")
									.toString();
					String oricg = oriCgfab
							.getAttributeValue("whetherlineclose") == null ? null
							: oriCgfab.getAttributeValue("whetherlineclose")
									.toString();
					int result = 0;
					if (newcg.equals(oricg)) {

					} else if ("Y".equals(newcg)) {
						result = 1;
					}

					// 新的vo有修改则需要回写
					if (result == 1) {
						res.put((String) cgfab.getAttributeValue("csrcid"),
								(UFDouble) cgfab.getAttributeValue("cgfanum"));
					}
				}
				return res;
			} else {
				return diffrentData(vo, originVO);
			}

		}// 两个字段不一样长说明有删行
			// 2017-11-29 可能增行
		else {
			return diffrentData(vo, originVO);
		}

	}

	/**
	 * @Title: isOrigin
	 * @Description: TODO
	 * @param childrenVO
	 * @param childrenVO2
	 * @return
	 * @return: boolean
	 */
	private boolean isOrigin(Cgfab[] newVO, Cgfab[] oriVO) {
		// TODO 自动生成的方法存根
		HashSet<String> newSet = new HashSet<String>();
		HashSet<String> oriSet = new HashSet<String>();
		for (Cgfab cgfab : newVO) {
			newSet.add((String) cgfab.getAttributeValue("csrcid"));
		}
		for (Cgfab cgfab : oriVO) {
			oriSet.add((String) cgfab.getAttributeValue("csrcid"));
		}
		return newSet.containsAll(oriSet);
	}

	/**
	 * @Title: diffrentData
	 * @Description: TODO
	 * @param vo
	 * @param originVO
	 * @return
	 * @return: HashMap<String,UFDouble>
	 */
	private HashMap<String, UFDouble> diffrentData(AggCgfa vo, AggCgfa originVO) {
		// TODO 自动生成的方法存根

		Cgfab[] bvos = (Cgfab[]) vo.getChildrenVO();
		// 数据库里边的数据多有删行
		// 新的子表pk集合（临时）
		List<String> temp = new ArrayList<String>();
		// 原始的数据子表pk集合（临时）
		List<String> temp2 = new ArrayList<String>();
		// 被删除的行，将值赋成未被参照过
		ArrayList<Cgfab> newBvosL = new ArrayList<Cgfab>();
		for (Cgfab cgfab : bvos) {
			temp.add(cgfab.getPrimaryKey());
			newBvosL.add(cgfab);
		}

		ArrayList<Cgfab> oriBvosL = new ArrayList<Cgfab>();
		Cgfab[] oriBvos = (Cgfab[]) originVO.getChildrenVO();
		for (Cgfab cgfab : oriBvos) {
			oriBvosL.add(cgfab);
			// 原始子表pk
			temp2.add(cgfab.getPrimaryKey());
		}
		ArrayList<Cgfab> oriBvosLC = (ArrayList<Cgfab>) oriBvosL.clone();
		// 新vo的克隆
		ArrayList<Cgfab> newBvosLC = (ArrayList<Cgfab>) newBvosL.clone();
		// 原始长度
		int oriNum = oriBvosL.size();
		// 新长度
		int newNum = temp.size();
		for (int i = 0; i < oriBvosL.size(); i++) {
			Cgfab cgfab = oriBvosL.get(i);
			if (temp.contains(cgfab.getPrimaryKey())) {
				continue;
			} else {
				delIndex.put(((String) cgfab.getAttributeValue("csrcid")),
						(UFDouble) cgfab.getAttributeValue("cgfanum"));
				oriBvosLC.remove(cgfab);

			}
		}
		for (int i = 0; i < newNum; i++) {
			Cgfab cgfab = newBvosL.get(i);
			// 原始的包含界面上的子表pk
			if (temp2.contains(cgfab.getPrimaryKey())) {
				continue;
			} else {
				// 新增数据
				lineMoreIndex.put(((String) cgfab.getAttributeValue("csrcid")
						+ "_" + (String) cgfab.getAttributeValue("seq")),
						(UFDouble) cgfab.getAttributeValue("cgfanum"));
				newBvosLC.remove(cgfab);

			}
		}

		// 遍历现有的值，数据库更新是否行关闭
		HashMap<String, UFDouble> res = new HashMap<String, UFDouble>();
		if (oriBvosLC.size() >= bvos.length) {

			for (int i = 0; i < bvos.length; i++) {
				Cgfab cgfab = bvos[i];
				Cgfab oriCgfab = oriBvosLC.toArray(new Cgfab[oriBvosLC.size()])[i];

				String newcg = cgfab.getAttributeValue("whetherlineclose") == null ? null
						: cgfab.getAttributeValue("whetherlineclose")
								.toString();
				String oricg = oriCgfab.getAttributeValue("whetherlineclose") == null ? null
						: oriCgfab.getAttributeValue("whetherlineclose")
								.toString();
				int result = 0;
				if (newcg.equals(oricg)) {

				} else if ("Y".equals(newcg)) {
					result = 1;
				}

				// 新的vo有修改则需要回写
				if (result == 1) {
					res.put((String) cgfab.getAttributeValue("csrcid"),
							(UFDouble) cgfab.getAttributeValue("cgfanum"));
				}
			}
		} else {
			// 有新增的行
			// 寻找共同的数据行
			for (int i = 0; i < oriBvosLC.size(); i++) {
				Cgfab newcgfab = newBvosLC.get(i);
				Cgfab oriCgfab = oriBvosLC.toArray(new Cgfab[oriBvosLC.size()])[i];

				String newcg = newcgfab.getAttributeValue("whetherlineclose") == null ? null
						: newcgfab.getAttributeValue("whetherlineclose")
								.toString();
				String oricg = oriCgfab.getAttributeValue("whetherlineclose") == null ? null
						: oriCgfab.getAttributeValue("whetherlineclose")
								.toString();
				int result = 0;
				if (newcg.equals(oricg)) {

				} else if ("Y".equals(newcg)) {
					result = 1;
				}

				// 新的vo有修改则需要回写
				if (result == 1) {
					res.put((String) newcgfab.getAttributeValue("csrcid"),
							(UFDouble) newcgfab.getAttributeValue("cgfanum"));
				}
			}

		}
		return res;

	}

	/**
	 * @param cgfab
	 * @param oriCgfab
	 */
	private void caculateNum(Cgfab cgfab, Cgfab oriCgfab) {
		// TODO 自动生成的方法存根
		// 新增对数量的判断
		VOComparator<Cgfab> compare = new VOComparator<Cgfab>(
				new String[] { "cgfanum" });
		int res1 = compare.compare(cgfab, oriCgfab);
		if (res1 == 0) {
			// 等于0不回写
		} else if (res1 == 1) {
			// 当前的数量多
			String rowNum = (String) cgfab.getAttributeValue("seq");
			String pk = (String) cgfab.getAttributeValue("csrcid");
			UFDouble num = ((UFDouble) cgfab.getAttributeValue("cgfanum"))
					.sub((UFDouble) oriCgfab.getAttributeValue("cgfanum"));
			pk = pk + "_" + rowNum;
			numMoreIndex.put(pk, num);

		} else if (res1 == -1) {
			// 当前修改的数量减少了
			String pk = (String) cgfab.getAttributeValue("csrcid");
			String rowNum = (String) cgfab.getAttributeValue("seq");
			UFDouble num = ((UFDouble) oriCgfab.getAttributeValue("cgfanum"))
					.sub((UFDouble) cgfab.getAttributeValue("cgfanum"));
			pk = pk + "_" + rowNum;
			numLessIndex.put(pk, num);
		}
	}

	/**
	 * @param res
	 */
	private void reWrite(HashMap<String, UFDouble> res) {
		// TODO 自动生成的方法存根
		// 没有需要回写行关闭的数据
		if (res.size() == 0) {
			return;
		}
		List<PraybillItemVO> bvos = new ArrayList<PraybillItemVO>();
		for (Map.Entry<String, UFDouble> map : res.entrySet()) {
			// 查询行关闭前的上游数量
			VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
					PraybillItemVO.class);
			PraybillItemVO[] bvol = query.query(new String[] { map.getKey() });
			// 回退修改成行关闭的上游数量操作
			UFDouble oldsumcgfanum = (UFDouble) bvol[0]
					.getAttributeValue("sumcgfanum");
			UFDouble newsumcgfanum = oldsumcgfanum.sub(map.getValue());

			PraybillItemVO bvo = new PraybillItemVO();
			bvo.setPrimaryKey(map.getKey());
			bvo.setPk_praybill_b(map.getKey());
			bvo.setAttributeValue("whetherlineclose", UFBoolean.TRUE);
			bvo.setSts_req("02");
			bvo.setAttributeValue("sts_req", "02");
			bvo.setAttributeValue("sumcgfanum", newsumcgfanum);
			bvos.add(bvo);
		}
		// 持久化操作，更新上游的是否行关闭
		VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
		update.update(bvos.toArray(new PraybillItemVO[bvos.size()]),
				new String[] { "whetherlineclose", "sts_req", "sumcgfanum" });

	}

}
