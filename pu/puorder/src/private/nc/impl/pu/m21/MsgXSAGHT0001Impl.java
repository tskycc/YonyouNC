<<<<<<< .mine
///**
// * $文件说明$
// * 
// * @author zhaoyha
// * @version 6.0
// * @see
// * @since 6.0
// * @time 2010-1-13 下午03:40:25
// */
//package nc.impl.pu.m21;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import nc.bs.dao.BaseDAO;
//import nc.bs.dao.DAOException;
//import nc.itf.pu.m21.IMsgXSAGHT0001;
//import nc.jdbc.framework.SQLParameter;
//import nc.jdbc.framework.processor.ResultSetProcessor;
//import nc.md.data.access.NCObject;
//import nc.md.persist.framework.IMDPersistenceQueryService;
//import nc.md.persist.framework.MDPersistenceService;
//import nc.vo.am.status.StatusVO;
//import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
//import nc.vo.et.m5730.entity.DetailBVO;
//import nc.vo.et.m5730.entity.DetailHVO;
//import nc.vo.et.m5730.entity.DetailVO;
//import nc.vo.pu.m21.entity.MsgXSAGHT0001Head;
//import nc.vo.pu.m21.entity.OrderHeaderVO;
//import nc.vo.pu.m21.entity.OrderItemVO;
//import nc.vo.pu.m21.entity.OrderVO;
//import nc.vo.pu.m21.entity.MsgXSAGHT0001;
//import nc.vo.pub.AggregatedValueObject;
//import nc.vo.pub.VOStatus;
//import nc.vo.pub.lang.UFDate;
//import nc.vo.pubapp.AppContext;
//import nc.vo.so.m4331.entity.DeliveryBVO;
//import nc.vo.so.m4331.entity.DeliveryHVO;
//import nc.vo.so.m4331.entity.DeliveryVO;
//import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
//import nc.vo.lm.intertable.AggLmpgjkHVO;
//import nc.vo.lm.intertable.LmpgjkHVO;
//import nc.vo.lm.intertable.LmpgjkaBVO;
//import nc.vo.lm.intertable.LmpgjkbBVO;
//import nc.vo.lm.intertable.LmpgjkcBVO;
//
//
///**
// * 2017-03-06 攀枝花生产订单接口接口实现类 
//
// */
//public class MsgXSAGHT0001Impl implements IMsgXSAGHT0001 {
//	
//	@Override
//	public String MXSAGHT0001Head(MsgXSAGHT0001Head msgxsaght0001head) {
//		// TODO 自动生成的方法存根		
//		Map<String, Object> map = new HashMap<>();
////		  boolean flag = this.zjbList(list);
//		  //业务处理
//		  String flag="2";
//		try {
//			flag = this.MsgXSAGHT0001(msgxsaght0001head);
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}finally{
//			//构建中间表
//			AggLmpgjkHVO agglmpgjkhvo =this.TranszjbVOMainVO(msgxsaght0001head,flag);
//			  try {
//					NCObject objs = NCObject.newInstance(agglmpgjkhvo);//AGGVO对象
//					MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//				} catch (Exception e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
//		}
//		  /*map.put("flag", flag);
//		  if(flag){
//			  map.put("reason", "保存成功"); 
//		  }else{
//			  map.put("reason", "保存失败");
//		  }*/
//		  return "1";
////		return null;
//	}
//	
//	public String MsgXSAGHT0001(MsgXSAGHT0001Head msgxsaght0001head) {
//		// TODO 自动生成的方法存根
//		//构建中间表
//		AggLmpgjkHVO agglmpgjkhvo = this.TranszjbVOMainVO(msgxsaght0001head,"1");
//		BaseDAO baseDao = new BaseDAO();
//		Map<String, Object> map = new HashMap<>();
//		for (int i = 0; i < msgxsaght0001head.getMsgBody().size(); i++) {
//			MsgXSAGHT0001 msgXSAGHT0001=msgxsaght0001head.getMsgBody().get(i);
//			String expFlag = msgXSAGHT0001.getExpFlag();//内贸、出口业务标识
//			/*
//			 * 内贸时，处理采购合同和发货单
//			 */
//			if(expFlag.equals("N")){				
//			    int lengthOrder = this.FindSaleOrderNo(msgXSAGHT0001,"reqOrder");
//				if(lengthOrder==0){
//					//当采购订单内无数据时，向采购订单主表和采购订单子表内插入数据
//					/*OrderVO aggOrderVo = new OrderVO();
//					OrderHeaderVO orderHeaderVO = TransMainVO(msgXSAGHT0001);
//					OrderItemVO[] orderItemVO = TransSubVO(msgXSAGHT0001);					
//					aggOrderVo.setParentVO(orderHeaderVO);				
//					aggOrderVo.setChildrenVO(orderItemVO);
////					List<OrderVO> lsOrderVO= new ArrayList<>();
////					lsOrderVO.add(aggOrderVo);					
//					try {
//						//String[] ids = baseDao.insertVOList(lsOrderVO);
//						NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}	*/	
//					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
//							agglmpgjkhvo //中间表
// 						    };
//					OrderVO[] aggOrderVo = PfServiceScmUtil.executeVOChange("30-Cxx-pgscdd", "21", srcVosAfterFilter);//30-Cxx-pgscdd是上游单据交易类型，21是下游单据类型
//					try {
//						NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//				}else{
//					//当采购订单内无数据时，只向采购订单子表内插入数据
//					OrderVO aggOrderVo = null;
//					OrderHeaderVO orderHeaderVO = null;				
//					OrderItemVO[] orderItemVO = null;	
//					int lengthSub = this.FindSubOrderNo(msgXSAGHT0001,"reqOrder");
//					if(lengthSub==0){
//						String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//						try{
//							NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "saleorderno = "+pasorderNumPf, false);
//							aggOrderVo = (OrderVO)ncObjects[0].getContainmentObject();
//						} catch(Exception e){
//							
//							return "查询生产采购订单内合同号为"+pasorderNumPf+"的主信息失败！";				
//						}
//						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
//								agglmpgjkhvo //中间表
//	 						    };
//						OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("", "21", srcVosAfterFilter);
//						aggordervo[0].setParentVO(aggOrderVo.getParentVO());
//						try {
//							NCObject objs = NCObject.newInstance(aggordervo[0]);//AGGVO对象
//							MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//						} catch (Exception e) {
//							// TODO 自动生成的 catch 块
//							e.printStackTrace();
//						}
//						//orderItemVO[0].setStatus(VOStatus.NEW);
//					}else{
//						//orderItemVO = null;
//					}				
//				}
//				/*
//				 * 处理发货单
//				 */
//			    int lengthSend = this.FindSaleOrderNo(msgXSAGHT0001,"sendDetails");
//				if(lengthSend==0){
//					//当采购订单内无数据时，向采购订单主表和采购订单子表内插入数据
//					DeliveryVO aggDeliveryVO = new DeliveryVO();
//					DeliveryHVO deliveryHVO = TransSendMainVO(msgXSAGHT0001);
//					DeliveryBVO[] deliveryBVO = TransSendSubVO(msgXSAGHT0001);					
//					aggDeliveryVO.setParentVO(deliveryHVO);
//					aggDeliveryVO.setChildrenVO(deliveryBVO);				
//					try {
//						NCObject objs = NCObject.newInstance(aggDeliveryVO);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}					
//				}else{
//					//当采购订单内无数据时，只向采购订单子表内插入数据
//					DeliveryVO aggDeliveryVO = new DeliveryVO();
//					DeliveryHVO deliveryHVO = null;
//					DeliveryBVO[] deliveryBVO = null;
//					int lengthSub = this.FindSubOrderNo(msgXSAGHT0001,"sendDetails");
//					if(lengthSub==0){
//						deliveryBVO = TransSendSubVO(msgXSAGHT0001);
//					}else{
//						deliveryBVO = null;
//					}
//					String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//					try{
//						NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DeliveryHVO.class , "saleorderno = "+pasorderNumPf, false);
//						aggDeliveryVO = (DeliveryVO)ncObjects[0].getContainmentObject();
//						deliveryHVO = (DeliveryHVO)aggDeliveryVO.getParentVO();
//					} catch(Exception e){
//						
//						return "查询发货单内攀钢销售订单号为"+pasorderNumPf+"的主信息失败！";				
//					}
//					aggDeliveryVO.setParentVO(deliveryHVO);
//					aggDeliveryVO.setChildrenVO(deliveryBVO);					
//					try {
//						NCObject objs = NCObject.newInstance(aggDeliveryVO);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}					
//				}			
//			}
//			/*
//			 * 
//			 */
//			if(expFlag.equals("C")){				
//			    int length = this.FindSaleOrderNo(msgXSAGHT0001,"reqOrder");
//				if(length==0){
//				OrderVO aggOrderVo = new OrderVO();
//				OrderHeaderVO orderHeaderVO = TransMainVO(msgXSAGHT0001);
//				OrderItemVO[] orderItemVO = TransSubVO(msgXSAGHT0001);					
//				aggOrderVo.setParentVO(orderHeaderVO);	
//				aggOrderVo.setChildrenVO(orderItemVO);				
//				try {
//					NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO对象
//					MDPersistenceService.lookupPersistenceService().saveBill(objs);
//				} catch (Exception e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}				
//				}else{
//					OrderVO aggOrderVo = new OrderVO();
//					OrderHeaderVO orderHeaderVO = null;			
//					OrderItemVO[] orderItemVO = null;	
//					int lengthSub = this.FindSubOrderNo(msgXSAGHT0001,"reqOrder");
//					if(lengthSub==0){
//						orderItemVO = TransSubVO(msgXSAGHT0001);
//						orderItemVO[0].setStatus(VOStatus.NEW);
//					}else{
//						orderItemVO = null;
//					}				
//					String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//					try{
//						NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "saleorderno = "+pasorderNumPf, false);
//						aggOrderVo = (OrderVO)ncObjects[0].getContainmentObject();
//						orderHeaderVO = (OrderHeaderVO)aggOrderVo.getParentVO();
//					} catch(Exception e){						
//						return "查询生产采购订单内合同号为"+pasorderNumPf+"的主信息失败！";				
//					}
//					aggOrderVo.setParentVO(orderHeaderVO);			
//					aggOrderVo.setChildrenVO(orderItemVO);
//					try {
//						NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//				}
//				/*
//				 * 处理出口明细
//				 */
//			    int lengthDetail = this.FindSaleOrderNo(msgXSAGHT0001,"details");
//				if(lengthDetail==0){
//					//当采购订单内无数据时，向采购订单主表和采购订单子表内插入数据
//					DetailVO aggDetailVO = new DetailVO();
//					DetailHVO detailHVO = TransDetailHVOMainVO(msgXSAGHT0001);
//					DetailBVO[] detailBVO = TransDetailBVOSubVO(msgXSAGHT0001);					
//					aggDetailVO.setParentVO(detailHVO);
//					aggDetailVO.setChildrenVO(detailBVO);					
//					try {
//						NCObject objs = NCObject.newInstance(aggDetailVO);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}					
//				}else{
//					//当采购订单内无数据时，只向采购订单子表内插入数据
//					DetailVO aggDetailVO = new DetailVO();
//					DetailHVO detailHVO = null;	
//					DetailBVO[] detailBVO = null;
//					int lengthSubDetail = this.FindSubOrderNo(msgXSAGHT0001,"details");
//					if(lengthSubDetail==0){
//						detailBVO = TransDetailBVOSubVO(msgXSAGHT0001);
//					}else{
//						detailBVO = null;
//					}
//					String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//					try{
//						NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DetailHVO.class , "saleorderno = "+pasorderNumPf, false);
//						aggDetailVO = (DetailVO)ncObjects[0].getContainmentObject();
//						detailHVO = (DetailHVO)aggDetailVO.getParentVO();
//					} catch(Exception e){
//						
//						return "查询出口明细中订单号为"+pasorderNumPf+"的主信息失败！";				
//					}
//					aggDetailVO.setParentVO(detailHVO);
//					aggDetailVO.setChildrenVO(detailBVO);
////					List<DetailVO> lsDetailVO= new ArrayList<>();
////					lsDetailVO.add(aggDetailVO);					
//					try {
////						String[] ids = baseDao.insertVOList(lsDetailVO);
//						NCObject objs = NCObject.newInstance(aggDetailVO);//AGGVO对象
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}						
//				}		
//			}
//			/**
//			 * 生成对照表
//			 */
//			int length = this.FindSaleOrderNo(msgXSAGHT0001, "pgpdtorderrelation");
//			if(length==0){
//				
//			}else{
//				return "接口数据处理失败！";
//			}
//			
//		}
//		return "OK";
//}
//	/**
//	 * 根据订单号查询相应的主表内是否有该订单的数据
//	 * Parma msgXSAGHT0001接口传递的采购订单数据
//	 * type查询表的类型：reqOrder-采购订单，sendDetails-发货单，details-出口明细
//	 */
//	public int FindSaleOrderNo(MsgXSAGHT0001 msgXSAGHT0001,String type) {
//		String expFlag = msgXSAGHT0001.getExpFlag();//内贸、出口业务标识
//		BaseDAO baseDao = new BaseDAO();
//		String sqlStr = "";
//		SQLParameter sp = new SQLParameter();
//		if(type.equals("reqOrder")){
//			sqlStr = "select count(*) from po_order where saleorderno=?";	
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//		}else if(type.equals("sendDetails")){
//			sqlStr = "select count(*) from so_delivery where saleorderno=?";
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//		}else if(type.equals("details")){
//			sqlStr = "select count(*) from et_detail where saleorderno=?";
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//		}else if(type.equals("pgpdtorderrelation")){
//			sqlStr = "select count(*) from lm_pgpdtorderrelation where saleorderno=? and productorderrowno=?";
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}
//		String li = "0";
//		try {
//			li = (String) baseDao.executeQuery(sqlStr, sp,
//					new ResultSetProcessor() {
//						@Override
//						public String handleResultSet(ResultSet rs)
//								throws SQLException {
//							rs.next();
//							return rs.getString(1);
//						}
//					});
//		} catch (Exception e) {
//			ExceptionUtils.wrappBusinessException("执行失败!");
//		}
//		return Integer.parseInt(li);
//	}	
//	/**
//	 * 根据订单号查询相应的子表内是否有该订单的数据
//	 * Parma msgXSAGHT0001接口传递的采购订单数据
//	 * type查询表的类型：reqOrder-采购订单，sendDetails-发货单，details-出口 明细
//	 */
//	public int FindSubOrderNo(MsgXSAGHT0001 msgXSAGHT0001,String type) {
//		String expFlag = msgXSAGHT0001.getExpFlag();//内贸、出口业务标识
//		BaseDAO baseDao = new BaseDAO();
//		String sqlStr = "";
//		SQLParameter sp = new SQLParameter();
//		if(type.equals("reqOrder")){
//			sqlStr = "select count(*) from po_order_b where productorderrowno=?";	
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}else if(type.equals("sendDetails")){
//			sqlStr = "select count(*) from so_delivery_b where productorderrowno=?";
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}else if(type.equals("details")){
//			sqlStr = "select count(*) from et_detail_b where productorderrowno=?";
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}
//		String li = "0";
//		try {
//			li = (String) baseDao.executeQuery(sqlStr, sp,
//					new ResultSetProcessor() {
//						@Override
//						public String handleResultSet(ResultSet rs)
//								throws SQLException {
//							rs.next();
//							return rs.getString(1);
//						}
//					});
//		} catch (Exception e) {
//			ExceptionUtils.wrappBusinessException("执行失败!");
//		}
//		return Integer.parseInt(li);
//	}
//	/*
//	 * 根据接口中间层传递的参数，转换采购订单主Vo
//	 */
//	public OrderHeaderVO TransMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		OrderHeaderVO orderHeaderVO = new OrderHeaderVO();
//		//orderHeaderVO.setAttributeValue("vbillcode", );//订单编号
//		orderHeaderVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		orderHeaderVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//攀钢生产订单号
//		orderHeaderVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC合同号
//		orderHeaderVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//订单日期
////		orderHeaderVO.setAttributeValue("pk_supplier", AppContext.getInstance().getServerTime());//供应商
////		orderHeaderVO.setAttributeValue("pk_invcsupllier", AppContext.getInstance().getServerTime());//开票供应商
//		orderHeaderVO.setAttributeValue("billstatus ", "1");//审批状态
////		orderHeaderVO.setAttributeValue("pk_group ", "1");//所属集团
////		orderHeaderVO.setAttributeValue("pk_org ", "1");//采购组织版本信息
////		orderHeaderVO.setAttributeValue("pk_org_v ", "1");//采购组织
//		return orderHeaderVO;
//	}
//	/*
//	 * 根据接口中间层传递的参数，转换采购订单明细Vo
//	 */
//	public OrderItemVO[] TransSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		OrderItemVO[] orderItemVO = new OrderItemVO[1];
//		orderItemVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		orderItemVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
//		orderItemVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号
////		orderItemVO[0].setAttributeValue("pk_material", msgXSAGHT0001.getMdOrderNo());//物料版本信息
//		String expFlag = msgXSAGHT0001.getExpFlag();//内贸、出口业务标识
//		if(expFlag.equals("N")){
//			orderItemVO[0].setAttributeValue("cfirsttypecode ", "30");//源头单据类型		
//		}else{
//			orderItemVO[0].setAttributeValue("cfirsttypecode ", "5720");//源头单据类型	
//		}
////		orderItemVO[0].setAttributeValue("cfirstid ", msgXSAGHT0001.getErpOrderNo());//源头单据
//		orderItemVO[0].setAttributeValue("cfirstbid  ", msgXSAGHT0001.getErpOrderNo());//源头单据明细
//		orderItemVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
//		orderItemVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
//		if(expFlag.equals("N")){
//			orderItemVO[0].setAttributeValue("csourcetypecode", "30");//源头单据类型		
//		}else{
//			orderItemVO[0].setAttributeValue("csourcetypecode", "5720");//源头单据类型	
//		}
////		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//来源单据
//		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//来源单据明细
//		orderItemVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
//		orderItemVO[0].setAttributeValue("mainamount", msgXSAGHT0001.getContOrderNum());//主数量
//		return orderItemVO;
//	}	
//	/*
//	 * 根据接口中间层传递的参数，转换发货单主Vo
//	 */
//	public DeliveryHVO TransSendMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DeliveryHVO deliveryHVO = new DeliveryHVO();		
////		deliveryHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//单据号		
//		deliveryHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//单据日期
//		deliveryHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		deliveryHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//攀钢生产订单号
//		deliveryHVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC合同号
//		deliveryHVO.setAttributeValue("fstatusflag", "2");//单据状态
////		deliveryHVO.setAttributeValue("pk_group", "2");//单据状态
////		deliveryHVO.setAttributeValue("pk_org", "2");//单据状态
////		deliveryHVO.setAttributeValue("pk_org_v", "2");//单据状态
//		return deliveryHVO;
//	}
//	/*
//	 * 根据接口中间层传递的参数，转换发货单明细Vo
//	 */
//	public DeliveryBVO[] TransSendSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DeliveryBVO[] deliveryBVO = new DeliveryBVO[1];
//		deliveryBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		deliveryBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
//		deliveryBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号
//		deliveryBVO[0].setAttributeValue("vsrctype", "30");//源单据类型
//		deliveryBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
//		//deliveryBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNumPf());//来源单据表头ID
//		deliveryBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//来源单据表体ID
//		deliveryBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
//		deliveryBVO[0].setAttributeValue("vfirsttype", "30");//源头单据类型
//		deliveryBVO[0].setAttributeValue("cfirstbid", msgXSAGHT0001.getErpOrderNo());//源头单据表体ID
////		deliveryBVO[0].setAttributeValue("cfirstid", msgXSAGHT0001.getErpOrderNo());//源头单据表头ID
//		deliveryBVO[0].setAttributeValue("cmaterialid", msgXSAGHT0001.getErpOrderNo());//物料档案
//		deliveryBVO[0].setAttributeValue("cmaterialvid", msgXSAGHT0001.getErpOrderNo());//物料编码
////		deliveryBVO[0].setAttributeValue("cordercustid", msgXSAGHT0001.getErpOrderNo());//客户编码
////		deliveryBVO[0].setAttributeValue("cinvoicecustid", msgXSAGHT0001.getErpOrderNo());//开票客户
//		deliveryBVO[0].setAttributeValue("csaleorgvid", msgXSAGHT0001.getErpOrderNo());//销售组织
//		deliveryBVO[0].setAttributeValue("csettleorgid", msgXSAGHT0001.getErpOrderNo());//结算财务组织最新版本
//		deliveryBVO[0].setAttributeValue("csettleorgvid", msgXSAGHT0001.getErpOrderNo());//结算财务组织
//		deliveryBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
//		deliveryBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//主数量
//		return deliveryBVO;
//	}
//	/*
//	 * 根据接口中间层传递的参数，转换出口明细主Vo
//	 */
//	public DetailHVO TransDetailHVOMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DetailHVO detailHVO = new DetailHVO();
//		detailHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//单据日期
//		detailHVO.setAttributeValue("dmakedate", AppContext.getInstance().getServerTime());//制单日期
////		detailHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//出口明细单号
//		detailHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		detailHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//攀钢生产订单号
////		detailHVO.setAttributeValue("carorgid", msgXSAGHT0001.getMdOrderNumPf());//应收组织最新版本
////		detailHVO.setAttributeValue("carorgvid", msgXSAGHT0001.getMdOrderNumPf());//应收组织
////		detailHVO.setAttributeValue("csettleorgid", msgXSAGHT0001.getMdOrderNumPf());//结算财务组织最新版本
////		detailHVO.setAttributeValue("csettleorgvid", msgXSAGHT0001.getMdOrderNumPf());//结算财务组织
////		detailHVO.setAttributeValue("ctradewordid", msgXSAGHT0001.getMdOrderNumPf());//贸易术语
////		detailHVO.setAttributeValue("ctransporttypeid", msgXSAGHT0001.getMdOrderNumPf());//运输方式
////		detailHVO.setAttributeValue("ccustomerid", msgXSAGHT0001.getMdOrderNumPf());//客户
////		detailHVO.setAttributeValue("cinvoicecustid", msgXSAGHT0001.getMdOrderNumPf());//收票客户
////		detailHVO.setAttributeValue("cdeptid", msgXSAGHT0001.getMdOrderNumPf());//部门最新版本
////		detailHVO.setAttributeValue("cdeptvid", msgXSAGHT0001.getMdOrderNumPf());//部门
////		detailHVO.setAttributeValue("cloadportid", msgXSAGHT0001.getMdOrderNumPf());//起运港
////		detailHVO.setAttributeValue("cdestportid", msgXSAGHT0001.getMdOrderNumPf());//目的港
////		detailHVO.setAttributeValue("corigcurrencyid", msgXSAGHT0001.getMdOrderNumPf());//币种
////		detailHVO.setAttributeValue("pk_group", msgXSAGHT0001.getMdOrderNumPf());//集团
////		detailHVO.setAttributeValue("pk_org", msgXSAGHT0001.getMdOrderNumPf());//销售组织
////		detailHVO.setAttributeValue("pk_org_v", msgXSAGHT0001.getMdOrderNumPf());//销售组织版本
//		detailHVO.setAttributeValue("fpfstatusflag", "1");//审批流状态
//		detailHVO.setAttributeValue("fstatusflag", "3");//单据状态
//		return detailHVO;
//	}
//	/*
//	 * 根据接口中间层传递的参数，转换出口明细子Vo
//	 */
//	public DetailBVO[] TransDetailBVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DetailBVO[] detailBVO = new DetailBVO[1];	
//		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
//		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号		
////		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//来源单据主表
//		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//来源单据附表
//		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
//		detailBVO[0].setAttributeValue("vsrctype", "5720");//来源单据类型
////		detailBVO[0].setAttributeValue("cfirstbid", );//源头单据子表
////		detailBVO[0].setAttributeValue("cfirstid", );//源头单据主表
//		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
//		detailBVO[0].setAttributeValue("vfirsttype", "5720");//来源单据类型
////		detailBVO[0].setAttributeValue("cmaterialid", "5720");//物料最新版本
////		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//物料编码
//		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
//		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//主数量	
////		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//单价
////		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//主单价	
//		return detailBVO;
//	}
//	
//	/*
//	 * 根据接口中间层传递的参数，转换对照表VO
//	 */
//	public DetailBVO[] TransdzbVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DetailBVO[] detailBVO = new DetailBVO[1];	
//		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
//		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
//		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号		
////		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//来源单据主表
//		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//来源单据附表
//		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
//		detailBVO[0].setAttributeValue("vsrctype", "5720");//来源单据类型
////		detailBVO[0].setAttributeValue("cfirstbid", );//源头单据子表
////		detailBVO[0].setAttributeValue("cfirstid", );//源头单据主表
//		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
//		detailBVO[0].setAttributeValue("vfirsttype", "5720");//来源单据类型
////		detailBVO[0].setAttributeValue("cmaterialid", "5720");//物料最新版本
////		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//物料编码
//		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
//		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//主数量	
////		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//单价
////		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//主单价	
//		return detailBVO;
//	}
//	
//	/*
//	 * 根据接口中间层传递的参数，转换中间表VO
//	 */
//	public AggLmpgjkHVO TranszjbVOMainVO(MsgXSAGHT0001Head msgxsaght0001head,String flag){
//		
//		AggLmpgjkHVO agglmpgjkhvo = new AggLmpgjkHVO();
//		
//		LmpgjkHVO lmpgjkHVO = new LmpgjkHVO();	
//		lmpgjkHVO.setAttributeValue("dwid", msgxsaght0001head.getMsgId());//电文ID
//		lmpgjkHVO.setAttributeValue("sendtype", "1");//方向类型 1.接收
//		lmpgjkHVO.setAttributeValue("interfacetype", "2");//接口类型 2.攀钢基地销售订单
//		lmpgjkHVO.setAttributeValue("flag", flag);//操作标识 1.成功 2.失败
//		lmpgjkHVO.setAttributeValue("receivetime", AppContext.getInstance().getServerTime());//接收时间 
//		lmpgjkHVO.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getOrderNumPf());//攀钢销售订单号
//		lmpgjkHVO.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getMdOrderNumPf());//买断订单号
//		lmpgjkHVO.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getErpOrderNumPf());//ERP合同号
//		lmpgjkHVO.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getOrderStatusPf());//订单状态_PF
//		lmpgjkHVO.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderCertNo());//订货凭据
//		lmpgjkHVO.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getOrderTypeCode());//订单性质代码
//		lmpgjkHVO.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getExpFlag());//内外销标志 
//		lmpgjkHVO.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getOrderPeriodPf());//订货期别_PF
//		lmpgjkHVO.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getResoPeriodPf());//资源期别_PF
//		lmpgjkHVO.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getSaleNetworkPf());//销售网络
//		lmpgjkHVO.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getProdClassCode());//产品大类代码
//		lmpgjkHVO.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getCompanyCodePf());//公司别_PF
//		lmpgjkHVO.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getTradeMode());//贸易方式
//		lmpgjkHVO.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getSettleMode());//结算方式代码
//		lmpgjkHVO.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getSignSitePf());//履约地点代码_PF
//		lmpgjkHVO.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getPriceFormulaMode());//计价方式
//		lmpgjkHVO.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getRmaNoPf());//异议号_PF
//		lmpgjkHVO.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getContInDate());//合约录入日期
//		lmpgjkHVO.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getContInPerson());//合约录入人
//		lmpgjkHVO.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getOrderModiMark());//订单变更中标志
//		lmpgjkHVO.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getOrderUserCodePf());//订货用户代码_PF
//		lmpgjkHVO.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getOrderUserCnamePf());//订货用户中文名称_PF
//		lmpgjkHVO.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNum().toString());//订货用户地址码 
//		lmpgjkHVO.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNamePf());//订货用户地址_PF
//		lmpgjkHVO.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getSettleUserNum());//需方用户代码
//		lmpgjkHVO.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getSettleUserNamePf());//需方用户名称_PF
//		lmpgjkHVO.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getSettUserAddrNum().toString());//需方用户地址码
//		lmpgjkHVO.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getSettUserAddrPf());//需方用户地址_PF
//		lmpgjkHVO.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctNum().toString());//需方用户帐户序号
//		lmpgjkHVO.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctPf());//需方用户帐户_PF
//		lmpgjkHVO.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getContOrderNum().toString());//合约子项数量 
//		lmpgjkHVO.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getContTotWt().toString());//合约总重量
//		lmpgjkHVO.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getContTotAmt().toString());//合约总金额 
//		lmpgjkHVO.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getContTrnpTotAmt().toString());//合约运费总金额 
//		lmpgjkHVO.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getContDepositNum().toString());//合约押金量
//		lmpgjkHVO.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getContDepositAmt().toString());//合约押金金额 
//		lmpgjkHVO.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getContPrepayRate().toString());//合约定金比例 
//		lmpgjkHVO.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getContPrepayLackAmt().toString());//合约应收定金额
//		lmpgjkHVO.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getTaxRatePf().toString());//税率_PF 
//		lmpgjkHVO.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getOrderNo());//合同号
//		lmpgjkHVO.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getMdOrderNo());//买断订单子项号 
//		lmpgjkHVO.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getErpOrderNo());//ERP合同子项号
//		lmpgjkHVO.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getOrderModiNum().toString());//合同变更次数
//		lmpgjkHVO.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderLastSeqNo());//合同子项序号
//		lmpgjkHVO.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getSaleId());//销售业务员工号
//		lmpgjkHVO.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getRecCreateTime());//记录创建时间  
//		lmpgjkHVO.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPartOrderFlag());//套裁订单标志
//		lmpgjkHVO.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getCutNum().toString());//纵切条数 
//		lmpgjkHVO.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getOrderItemStatusCode());//销售订单子项状态
//		lmpgjkHVO.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getOrderItemModiMark());//订单子项变更中标记
//		lmpgjkHVO.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getModiCause());//变更原因 
//		lmpgjkHVO.setStatus(VOStatus.NEW);
//		
//		LmpgjkaBVO[] lmpgjkabvos = new LmpgjkaBVO[1];
//		LmpgjkaBVO lmpgjkabvo = new LmpgjkaBVO();
//		lmpgjkabvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getModiDate());//变更日期
//		lmpgjkabvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getModiOperPerson());//合同变更操作人
//		lmpgjkabvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getModiType());//变更性质 
//		lmpgjkabvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getFinUserNum());//最终用户代码
//		lmpgjkabvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getFinUserName());//最终用户名称
//		lmpgjkabvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getFinUserTrade());//最终用户的行业代码
//		lmpgjkabvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getUserCredit());//用户信誉度
//		lmpgjkabvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getConsignNum());//收货用户代码
//		lmpgjkabvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getConsignNamePf());//收货用户名称_PF
//		lmpgjkabvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum().toString());//收货用户地址码
//		lmpgjkabvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressPf());//收货用户地址_PF
//		lmpgjkabvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getConsignNum1());//收货用户代码1
//		lmpgjkabvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getConsignName1Pf());//收货用户名称1_PF
//		lmpgjkabvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum1().toString());//收货用户地址码1
//		lmpgjkabvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getCnsgAddress1Pf());//收货用户地址1_PF
//		lmpgjkabvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleCode());//运费发票抬头代码
//		lmpgjkabvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleText());//运费发票抬头
//		lmpgjkabvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getPsr());//产品规范码 
//		lmpgjkabvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getApn());//最终用途 
//		lmpgjkabvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getMsc());//冶金规范码 
//		lmpgjkabvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getNewProductFlag());//新试产品标志
//		lmpgjkabvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getProductDscr());//产品名称
//		lmpgjkabvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getShopsignPf());//牌号_PF
//		lmpgjkabvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getSteelStd());//产品标准
//		lmpgjkabvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getCapabYnFlag());//性能是否要求标志 
//		lmpgjkabvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getRainCoatFlag());//加盖雨布标志
//		lmpgjkabvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getQcType());//质保书类型_PF
//		lmpgjkabvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getCertiNum().toString());//质保书份数
//		lmpgjkabvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPackingCode());//包装代码_PF
//		lmpgjkabvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getOrderPriority());//订单优先级 
//		lmpgjkabvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getMetFeetFlag());//公英制标志
//		lmpgjkabvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getOrderThick().toString());//订货厚 
//		lmpgjkabvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getOrderWidthPf().toString());//订货宽
//		lmpgjkabvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getOrderLenLow().toString());//订货长度下限 
//		lmpgjkabvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderLenUp().toString());//订货长度上限
//		lmpgjkabvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getThickEng());//英制厚度
//		lmpgjkabvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWidthEng());//英制宽度
//		lmpgjkabvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getLengthMinEng());//英制长度下限
//		lmpgjkabvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getLengthMaxEng());//英制长度上限
//		lmpgjkabvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getWeightMethod());//计重方式
//		lmpgjkabvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getOrderUnit());//订货计量单位
//		lmpgjkabvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getOrderWtPf().toString());//订货重量_PF
//		lmpgjkabvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getPreAlcaWtPf().toString());//预合同分配量_PF
//		lmpgjkabvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderPiece().toString());//订货件数 
//		lmpgjkabvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getUnitWtUpPf().toString());//单件最大重量_PF
//		lmpgjkabvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnitWtLowPf().toString());//单件最小重量_PF
//		lmpgjkabvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getDelivyTolUpper().toString());//公差上限
//		lmpgjkabvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getDelivyTolLower().toString());//公差下限 
//		lmpgjkabvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getCoilInsideDimPf().toString());//内径_PF
//		lmpgjkabvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getShortSizeRatePf().toString());//订货短尺率_PF
//		lmpgjkabvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getShortSizeLowPf().toString());//订货短尺下限_PF
//		lmpgjkabvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getShortSizeUpPf().toString());//订货短尺上限_PF
//		lmpgjkabvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getShortSize1().toString());//短尺长度1 
//		lmpgjkabvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getShortSize2().toString());//短尺长度2 
//		lmpgjkabvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getShortSize3().toString());//短尺长度3 
//		lmpgjkabvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getShortSize4().toString());//短尺长度4 
//		lmpgjkabvo.setStatus(VOStatus.NEW);
//		
//		lmpgjkabvos[0]=lmpgjkabvo;
//		
//		LmpgjkbBVO[] lmpgjkbbvos = new LmpgjkbBVO[1];
//		LmpgjkbBVO lmpgjkbbvo = new LmpgjkbBVO();
//		lmpgjkbbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getShortSize5().toString());//短尺长度5
//		lmpgjkbbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getDeliveryDateChr());//约定交货日期
//		lmpgjkbbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getDelivyPeriodPf());//交货期别_PF
//		lmpgjkbbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getDeliveryWeekMark());//按周交货标志
//		lmpgjkbbvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderReadyDate());//订单备妥日期 
//		lmpgjkbbvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getLaunchTime());//下发时间 
//		lmpgjkbbvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getLaunchId());//下发人工号
//		lmpgjkbbvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstTime());//初次下发时间 
//		lmpgjkbbvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstId());//初次下发人工号
//		lmpgjkbbvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getVersionNumPf().toString());//版本号_PF
//		lmpgjkbbvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getOrderPriceMode());//合同计价方式 
//		lmpgjkbbvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getPriceBaseDate());//价格执行基准日
//		lmpgjkbbvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getSaleUnitPrice().toString());//销售 单价 
//		lmpgjkbbvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getAgreemtCode());//协议价格单号
//		lmpgjkbbvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getAgreePrice().toString());//协议价格 
//		lmpgjkbbvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getOrderAmtPf().toString());//订单金额_PF
//		lmpgjkbbvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getSaleRemark());//销售备注
//		lmpgjkbbvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getA_flag());//A项标志  
//		lmpgjkbbvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getUserSpecDesc());//用户质量特殊要求
//		lmpgjkbbvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getDrewMode());//开票方式
//		lmpgjkbbvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getDepositN().toString());//押金数
//		lmpgjkbbvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getDepositAmtPf().toString());//押金金额_PF
//		lmpgjkbbvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getTransType());//运输类型 
//		lmpgjkbbvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getTrnpModeCodePf());//运输方式代码_PF
//		lmpgjkbbvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCodePf());//终到站港代码_PF
//		lmpgjkbbvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName());//终到站港描述
//		lmpgjkbbvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCode1Pf());//终到站港代码1_PF
//		lmpgjkbbvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName1());//终到站港描述1
//		lmpgjkbbvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteCodePf());//专用线代码_PF
//		lmpgjkbbvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteNamePf());//专用线描述_PF
//		lmpgjkbbvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getTrnpPricePf().toString());//运费单价_PF
//		lmpgjkbbvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getTrnpAmtPf().toString());//运费金额_PF
//		lmpgjkbbvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getGuideMeasureAq());//镀层厚度计量方式
//		lmpgjkbbvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getEdgeType());//切边形态
//		lmpgjkbbvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderTechnicDes());//合同单技术参数描述
//		lmpgjkbbvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getOrderByprodDegreeCode());//合同副产品等级代码
//		lmpgjkbbvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWeightPlate());//锌层重量
//		lmpgjkbbvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getGrainCode());//粒度代码
//		lmpgjkbbvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getPatternSalient().toString());//纹高
//		lmpgjkbbvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getSectionCode());//截面代码
//		lmpgjkbbvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getSectionDesc());//截面描述
//		lmpgjkbbvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getRecognCode());//产品标识代码
//		lmpgjkbbvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getEndLineCode());//终到站省份 
//		lmpgjkbbvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getEndLineName());//终到站地区别
//		lmpgjkbbvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getTransProdCode());//运输产品大类
//		lmpgjkbbvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnloadPoint());//卸车地点
//		lmpgjkbbvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPriceMode());//定价模式
//		lmpgjkbbvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getProdCode());//销售品种
//		lmpgjkbbvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getStraragemMark());//客商合作关系等级
//		lmpgjkbbvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getPriceModeCname());//定价模式名称 长度:10
//		lmpgjkbbvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getProdCodeCname());//定价模式名称  长度:30 
//		lmpgjkbbvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getStraragemMarkCname());//客商合作关系等级名称
//		lmpgjkbbvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getInvoiceType());//营改增发票类型
//		lmpgjkbbvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getInvoiceTypeName());//发票类型说明 
//		lmpgjkbbvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getUserChineseName());//受票方名称 
//		lmpgjkbbvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getTaxNum());//纳税人识别号
//		lmpgjkbbvo.setStatus(VOStatus.NEW);
//		
//		lmpgjkbbvos[0] = lmpgjkbbvo;
//		
//		LmpgjkcBVO[] lmpgjkcbvos = new LmpgjkcBVO[1];
//		LmpgjkcBVO lmpgjkcbvo = new LmpgjkcBVO();
//		lmpgjkcbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getAddressLocationc());//地址
//		lmpgjkcbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getBankBranchName());//开户银行 
//		lmpgjkcbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getAccountNum());//账号
//		lmpgjkcbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getTelNum());//电话
//		lmpgjkcbvo.setStatus(VOStatus.NEW);
//		
//		lmpgjkcbvos[0] = lmpgjkcbvo;
//		
//		agglmpgjkhvo.setParentVO(lmpgjkHVO);
//		agglmpgjkhvo.setChildren(LmpgjkaBVO.class, lmpgjkabvos);	
//		agglmpgjkhvo.setChildren(LmpgjkbBVO.class, lmpgjkbbvos);	
//		agglmpgjkhvo.setChildren(LmpgjkcBVO.class, lmpgjkcbvos);	
//		return agglmpgjkhvo;
//	}
//
//	
//}
//
=======
/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:40:25
 */
package nc.impl.pu.m21;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IMsgXSAGHT0001;
import nc.itf.pu.m21.IMsgXSAGHT0001Save;
import nc.itf.uif.pub.IUifService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pub.billcode.itf.IBillcodeManage;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.et.m5720.entity.ContractBVO;
import nc.vo.et.m5720.entity.ContractHVO;
import nc.vo.et.m5720.entity.ContractVO;
import nc.vo.et.m5730.entity.DetailBVO;
import nc.vo.et.m5730.entity.DetailCLVO;
import nc.vo.et.m5730.entity.DetailCMSNVO;
import nc.vo.et.m5730.entity.DetailDOCVO;
import nc.vo.et.m5730.entity.DetailFEEVO;
import nc.vo.et.m5730.entity.DetailHVO;
import nc.vo.et.m5730.entity.DetailInfoVO;
import nc.vo.et.m5730.entity.DetailMatchVO;
import nc.vo.et.m5730.entity.DetailPTVO;
import nc.vo.et.m5730.entity.DetailPayVO;
import nc.vo.et.m5730.entity.DetailVO;
import nc.vo.et.m5730.entity.DetailBBBVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.lm.pgdzb.Pgpdtorderrelation;
import nc.vo.lm.pgnxscddxx.AggXsaght0001HVO;
import nc.vo.lm.pgnxscddxx.Xsaght0001HVO;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;


/**
 * 2017-03-06 攀枝花生产订单接口接口实现类 

 */
public class MsgXSAGHT0001Impl implements IBackgroundWorkPlugin,IMsgXSAGHT0001 {
	
	@Override
	public String mXSAGHT0001Head_RequiresNew() {
		// TODO 自动生成的方法存根	
		try {

			NCObject[] ncobjects = 
					MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(Xsaght0001HVO.class, " msgflag='0' and dr=0", false);
			if(ncobjects==null){
				return "无未处理数据";
			}
			for (int i = 0; i < ncobjects.length; i++) {
				AggXsaght0001HVO  aggvo =(AggXsaght0001HVO) ncobjects[i].getContainmentObject();
				this.MsgXSAGHT0001(aggvo);
				 }
		} catch (MetaDataException e1) {
			// TODO 自动生成的 catch 块
			/*Logger.debug("==========================================");
			Logger.error(e1.getMessage(), e1);*/
			e1.printStackTrace();
		}
		  return null;
  }
	
	public String MsgXSAGHT0001(AggXsaght0001HVO aggvo) {
		// TODO 自动生成的方法存根    erpordernumpf   erporderno
		String expFlag = (String) aggvo.getParentVO().getAttributeValue("expflag");//内贸、出口业务标识
		String erpordernumpf = (String) aggvo.getParentVO().getAttributeValue("erpordernumpf");//erp合同号
		String erporderno = (String) aggvo.getParentVO().getAttributeValue("erporderno");//erp合同子项号
		int contordernum =(int) aggvo.getParentVO().getAttributeValue("contordernum");//合约子项数量
		String ordernumpf =(String) aggvo.getParentVO().getAttributeValue("ordernumpf");//内销订单号
		//InvocationInfoProxy.getInstance().setGroupId("0001N610000000000IT0");
			/*
			 * 内贸时，处理采购合同和发货单
			 */
		NCObject objsDZB=null;
		DetailVO aggDetailVO = new DetailVO();
		SaleOrderVO aggsaleordervo= new SaleOrderVO();
		OrderVO aggOrderVos = new OrderVO();
		DeliveryVO aggDeliveryVO = new DeliveryVO();
		NCObject[] ncobject = new NCObject[3];
		
		IUifService queryService = NCLocator.getInstance().lookup(IUifService.class);
		
			if(expFlag.equals("N")){				
			    
				try {
			    NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(SaleOrderHVO.class , "dr=0 and csaleorderid = '"+erpordernumpf+"'", false);
			    SaleOrderVO saleordervo = (SaleOrderVO) ncObjects[0].getContainmentObject();
				String id = saleordervo.getParent().getPrimaryKey();
				
				SaleOrderBVO[] saleorderbvo = (SaleOrderBVO[]) queryService.queryByCondition(SaleOrderBVO.class, "csaleorderid = '" + id
						+ "'"+" and csaleorderbid = '"+erporderno+"' and dr=0");
				
				int count = this.findCount(ordernumpf);
                if(contordernum!=count){
                	aggvo.getParent().setAttributeValue("msgflag", "2");
		        	aggvo.getParent().setAttributeValue("msginfo", "合约子项数量与采购合同数量不符");
					this.writeBack(aggvo);
                	return "";
                }
				aggsaleordervo.setParent(saleordervo.getParent());
				aggsaleordervo.setChildrenVO(saleorderbvo);
			        }catch (Exception e) {
			        	aggvo.getParent().setAttributeValue("msgflag", "2");
			        	aggvo.getParent().setAttributeValue("msginfo", "查询内贸合同信息失败");
						this.writeBack(aggvo);
						return "";
					}
				int lengthOrder = this.FindSaleOrderNo(aggvo,"reqOrder");
				if(lengthOrder==0){
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							aggsaleordervo //中间表
 						    };
					try {
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
						aggOrderVos = (OrderVO) PfUtilTools.runChangeData("30-Cxx-pgscdd", "21", srcVosAfterFilter[0]);//30-Cxx-pgscdd是上游单据交易类型，21是下游单据类型
						aggOrderVos.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggOrderVos.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//攀钢生产订单号
						//aggOrderVo[0].getParentVO().setAttributeValue("contractno", arg1);//
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
						aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//数量
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//主数量
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//含税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//主含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//来源单据
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//来源单据明细
						UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
						UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
						UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
						UFDouble nqttaxnetprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqttaxnetprice");
						//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						 
						//df.format(你要格式化的数字);
						double nmny = nqtprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", df.format(nmny));//本币无税金额
						double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", df.format(norigmny));//无税金额
						double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", df.format(norigtaxmny));//价税合计
						double ntaxmny =nqttaxnetprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", df.format(ntaxmny));//本币价税合计
						//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", df.format(norigmny));//计成本金额
						//===== 生成单据编码 ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("21", aggOrderVos.getParentVO().getAttributeValue("pk_group").toString(), aggOrderVos.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggOrderVos.getParent()});
						aggOrderVos.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== 生成单据编码 ====
						VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });
						/*objsNPO = NCObject.newInstance(aggOrderVo);//AGGVO对象
						ncobject[0]=objsNPO;*/
						//MDPersistenceService.lookupPersistenceService().saveBill(objs); 
					} catch (Exception e) {
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息转换失败");
						this.writeBack(aggvo);
						return "";
					}
				}/*else{
					//回写中间表 
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息已存在");
					this.writeBack(aggvo);
					return "";
				}*/	
				else{
					OrderVO aggordervo = null;
					int lengthSub = this.FindSubOrderNo(aggvo,"reqOrder");
					if(lengthSub==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						String id="";
						try{
							NCObject[] ncObjects1 = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "po_order.dr=0 and saleorderno = '"+pasorderNumPf+"'", false);
							aggordervo = (OrderVO)ncObjects1[0].getContainmentObject();
							id = aggordervo.getParent().getPrimaryKey();
						} catch(Exception e){
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "查询生产采购订单内合同号为"+pasorderNumPf+"的主信息失败！");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								aggsaleordervo //中间表
	 						    };
						//OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("30-Cxx-pgscdd_b", "21", srcVosAfterFilter);
						try {
							java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
							aggOrderVos= (OrderVO) PfUtilTools.runChangeData("30-Cxx-pgscdd_b", "21", srcVosAfterFilter[0]);
							aggOrderVos.setParentVO(aggordervo.getParentVO());
							aggOrderVos.getParent().setStatus(VOStatus.UPDATED);
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
							aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//数量
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//主数量
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//含税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//主含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//来源单据
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//来源单据明细
							aggOrderVos.getChildrenVO()[0].setAttributeValue("pk_order", aggOrderVos.getParent().getAttributeValue("pk_order"));//上层单据主键
							UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
							UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
							UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
							UFDouble nqttaxnetprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqttaxnetprice");
							//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							double nmny = nqtprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", nmny);//本币无税金额
							double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", norigmny);//无税金额
							double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", norigtaxmny);//价税合计
							double ntaxmny =nqttaxnetprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", ntaxmny);//本币价税合计
							//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", norigmny);//计成本金额
							aggOrderVos.getChildrenVO()[0].setStatus(VOStatus.NEW);
							VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });

                            OrderItemVO[] itemvo  = (OrderItemVO[])queryService.queryByCondition(OrderItemVO.class, "pk_order = '" + id
									+ "' and dr=0");
							
							OrderItemVO[] orderitemvo = new OrderItemVO[itemvo.length+1];
							
							orderitemvo[0] = (OrderItemVO)aggOrderVos.getChildren(OrderItemVO.class)[0];
							for(int i=0;i<itemvo.length;i++){
								orderitemvo[i+1]=itemvo[i];
							}
							
							aggOrderVos.setChildren(OrderItemVO.class, orderitemvo);
							
						} catch (Exception e) {
							//回写中间表 
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息转换失败");
							this.writeBack(aggvo);
							return "";
							}
					}else{
						//回写中间表 
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息已存在");
						this.writeBack(aggvo);
						return "";
					}				
				}
				/*
				 * 处理发货单
				 */
				int lengthSend = this.FindSaleOrderNo(aggvo,"sendDetails");
				if(lengthSend==0){
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							aggsaleordervo //中间表
 						    };
					//DeliveryVO[] aggDeliveryVO = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd", "4331", srcVosAfterFilter);//30-Cxx-pgscdd是上游单据交易类型，4331是下游单据类型
					try {
						aggDeliveryVO= (DeliveryVO) PfUtilTools.runChangeData("30-Cxx-pgscdd", "4331", srcVosAfterFilter[0]);
						aggDeliveryVO.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggDeliveryVO.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//攀钢生产订单号
						aggDeliveryVO.getParent().setStatus(VOStatus.NEW);
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//数量
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//主数量
						aggDeliveryVO.getChildrenVO()[0].setStatus(VOStatus.NEW);
						//===== 生成单据编码 ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("4331", aggDeliveryVO.getParentVO().getAttributeValue("pk_group").toString(), aggDeliveryVO.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggDeliveryVO.getParent()});
						aggDeliveryVO.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== 生成单据编码 ====
						VORowNoUtils.setVOsRowNoByRule(new DeliveryVO[] { aggDeliveryVO });
						
						//objsNPD = NCObject.newInstance(aggDeliveryVO);//AGGVO对象
						//ncobject[1]=objsNPD;
					} catch (Exception e) {
						//回写中间表
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "发货单转换失败");
						this.writeBack(aggvo);
						return "";
					}				
				}/*else{
					//回写中间表
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "发货单信息已存在");
					this.writeBack(aggvo);
					return "";
				}*/
				else{
					//当采购订单内无数据时，只向采购订单子表内插入数据
					DeliveryVO deliveryvo = new DeliveryVO();
					int lengthSubD = this.FindSubOrderNo(aggvo,"sendDetails");
					if(lengthSubD==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						try{
							NCObject[] ncObject = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DeliveryHVO.class , "dr=0 and saleorderno = '"+pasorderNumPf+"'", false);
							deliveryvo = (DeliveryVO)ncObject[0].getContainmentObject();
						} catch(Exception e){
							//回写中间表
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "查询发货单内攀钢销售订单号为"+pasorderNumPf+"的主信息失败！");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								aggsaleordervo //中间表
	 						    };
						//DeliveryVO[] aggordervo = PfServiceScmUtil.executeVOChange("30-Cxx-pgscdd_b", "4331", srcVosAfterFilter);
						try {
							aggDeliveryVO= (DeliveryVO) PfUtilTools.runChangeData("30-Cxx-pgscdd_b", "4331", srcVosAfterFilter[0]);
							aggDeliveryVO.setParentVO(deliveryvo.getParentVO());
							aggDeliveryVO.getParent().setStatus(VOStatus.UPDATED);
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//数量
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//主数量
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("cdeliveryid", aggDeliveryVO.getParent().getAttributeValue("cdeliveryid"));//上层单据主键
							aggDeliveryVO.getChildrenVO()[0].setStatus(VOStatus.NEW);
							VORowNoUtils.setVOsRowNoByRule(new DeliveryVO[] { aggDeliveryVO });
							//objsNPD= NCObject.newInstance(aggordervo);//AGGVO对象
							//ncobject[1]=objsNPD;
						} catch (Exception e) {
							//回写中间表
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "发货单信息转换失败");
							this.writeBack(aggvo);
							return "";
							}
					}else{
						//回写中间表
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "发货单信息已存在");
						this.writeBack(aggvo);
						return "";
					}				
				}			
			}
			/*
			 * 
			 */
			if(expFlag.equals("C")){		
				ContractVO contractvoz= new ContractVO();
				try {
			    NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(ContractHVO.class , "dr=0 and pk_contract = '"+erpordernumpf+"'", false);
			    ContractVO contractvo = (ContractVO) ncObjects[0].getContainmentObject();
				String id = contractvo.getParent().getPrimaryKey();
				
				ContractBVO[] contractbvo  = (ContractBVO[])queryService.queryByCondition(ContractBVO.class, "pk_contract = '" + id
						+ "'"+" and pk_contract_b = '"+erporderno+"' and dr=0");
				
				int count = this.findCount(ordernumpf);
				if(contordernum!=count){
					aggvo.getParent().setAttributeValue("msgflag", "2");
		        	aggvo.getParent().setAttributeValue("msginfo", "合约子项数量与出口合同数量不符");
					this.writeBack(aggvo);
					return "";
				}
				contractvoz.setParent(contractvo.getParent());
				contractvoz.setChildrenVO(contractbvo);
			        }catch (Exception e) {
			        	aggvo.getParent().setAttributeValue("msgflag", "2");
			        	aggvo.getParent().setAttributeValue("msginfo", "查询出口合同信息失败！");
						this.writeBack(aggvo);
						return "";
						
			        }
				int lengthC = this.FindSaleOrderNo(aggvo,"reqOrder");
				if(lengthC==0){
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							contractvoz //中间表
 						    };
					//OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd", "21", srcVosAfterFilter);
					try {
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
						aggOrderVos= (OrderVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd", "21", srcVosAfterFilter[0]);
						aggOrderVos.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggOrderVos.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//攀钢生产订单号
						aggOrderVos.getParent().setStatus(VOStatus.NEW);
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
						aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//数量
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//主数量
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//含税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//主含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//来源单据
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//来源单据明细
						aggOrderVos.getChildrenVO()[0].setStatus(VOStatus.NEW);
						//nqttaxnetprice=nexchangerate*norigprice       nqtprice=(nqtorigprice*nexchangerate)/1.17
						UFDouble nexchangerate = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nexchangerate");
						UFDouble norigprice = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("norigprice");
						UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
						//UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
						UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
						
						double nqtprice =(nqtorigprice.doubleValue()*nexchangerate.doubleValue())/1.17;
						double nqttaxnetprice =nexchangerate.doubleValue()*norigprice.doubleValue();
						//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						double nmny = nqtprice*nastnum.doubleValue();
						 
						//df.format(你要格式化的数字);
						
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", df.format(nmny));//本币无税金额
						double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", df.format(norigmny));//无税金额
						double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", df.format(norigtaxmny));//价税合计
						double ntaxmny =nqttaxnetprice*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", df.format(ntaxmny));//本币价税合计
						//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", df.format(norigmny));//计成本金额
						//===== 生成单据编码 ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("21", aggOrderVos.getParentVO().getAttributeValue("pk_group").toString(), aggOrderVos.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggOrderVos.getParent()});
						aggOrderVos.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== 生成单据编码 ====
						VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });
						//===================测试临时数据=====================
						/*
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nnetprice", 1.0);//主本币无税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxnetprice", 1.0);//主本币含税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nprice", 1.0);//主本币无税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxprice", 1.0);//主本币含税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtnetprice", 1.0);//本币无税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", 1.0);//本币含税净价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtprice", 1.0);//本币无税单价
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", 1.0);//本币含税单价
						*/
						//========================================
						//NCObject objsCPO = NCObject.newInstance(aggordervo);//AGGVO对象
						//ncobject[0]=objsCPO;
						//MDPersistenceService.lookupPersistenceService().saveBill(objs); 
					} catch (Exception e) {
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息转换失败！");
						this.writeBack(aggvo);
						return "";
						}		
				}/*else{
					//回写中间表
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息已存在");
					this.writeBack(aggvo);
					return "";
				}*/
				else{
					OrderVO aggordervo = new OrderVO();
					int lengthSubO = this.FindSubOrderNo(aggvo,"reqOrder");
					if(lengthSubO==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						String id ="";
						try{
							NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "po_order.dr=0 and saleorderno = '"+pasorderNumPf+"' ", false);
							aggordervo = (OrderVO)ncObjects[0].getContainmentObject();
							id = aggordervo.getParent().getPrimaryKey();
						} catch(Exception e){
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "查询生产采购订单内合同号为"+pasorderNumPf+"的主信息失败！");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								contractvoz //中间表
	 						    };
						//OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd_b", "21", srcVosAfterFilter);
						try {
							java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"); 
							aggOrderVos= (OrderVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd_b", "21", srcVosAfterFilter[0]);
							aggOrderVos.setParentVO(aggordervo.getParentVO());
							aggOrderVos.getParent().setStatus(VOStatus.UPDATED);
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
							aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//数量
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//主数量
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//本币含税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//含税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//主含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//来源单据
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//来源单据明细
							aggOrderVos.getChildrenVO()[0].setAttributeValue("pk_order", aggOrderVos.getParent().getAttributeValue("pk_order"));//上层单据主键
							aggOrderVos.getChildrenVO()[0].setStatus(VOStatus.NEW);
							/*UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
							UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
							UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
							UFDouble nqttaxnetprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqttaxnetprice");
							//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							double nmny = nqtprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", nmny);//本币无税金额
							double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", norigmny);//无税金额
							double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", norigtaxmny);//价税合计
							double ntaxmny =nqttaxnetprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", ntaxmny);//本币价税合计
							//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", norigmny);//计成本金额*/

							UFDouble nexchangerate = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nexchangerate");
							UFDouble norigprice = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("norigprice");
							UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
							//UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
							UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
							
							double nqtprice =(nqtorigprice.doubleValue()*nexchangerate.doubleValue())/1.17;
							double nqttaxnetprice =nexchangerate.doubleValue()*norigprice.doubleValue();
							//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							double nmny = nqtprice*nastnum.doubleValue();
							
							//df.format(你要格式化的数字);
							
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", df.format(nmny));//本币无税金额
							double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", df.format(norigmny));//无税金额
							double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", df.format(norigtaxmny));//价税合计
							double ntaxmny =nqttaxnetprice*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", df.format(ntaxmny));//本币价税合计
							//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", df.format(norigmny));//计成本金额
							VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });
							
							OrderItemVO[] itemvo  = (OrderItemVO[])queryService.queryByCondition(OrderItemVO.class, "pk_order = '" + id
									+ "' and dr=0");
							
							OrderItemVO[] orderitemvo = new OrderItemVO[itemvo.length+1];
							
							orderitemvo[0] = (OrderItemVO)aggOrderVos.getChildren(OrderItemVO.class)[0];
							for(int i=0;i<itemvo.length;i++){
								orderitemvo[i+1]=itemvo[i];
							}
							
							aggOrderVos.setChildren(OrderItemVO.class, orderitemvo);
							
							//===================测试临时数据=====================
							/*aggOrderVos.getChildrenVO()[0].setAttributeValue("nnetprice", 1.0);//主本币无税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxnetprice", 1.0);//主本币含税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nprice", 1.0);//主本币无税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxprice", 1.0);//主本币含税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtnetprice", 1.0);//本币无税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", 1.0);//本币含税净价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtprice", 1.0);//本币无税单价
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", 1.0);//本币含税单价*/
							//========================================
							//NCObject objsCCO = NCObject.newInstance(aggordervo);//AGGVO对象
							//ncobject[0]=objsCCO;
							//MDPersistenceService.lookupPersistenceService().saveBill(objs);
						} catch (Exception e) {
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息转换失败");
							this.writeBack(aggvo);
							return "";
							}
					}else{
						//回写中间表
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "生产采购订单信息已存在");
						this.writeBack(aggvo);
						return "";
					}				
				}
				/*
				 * 处理出口明细
				 */
				int lengthDetail = this.FindSaleOrderNo(aggvo,"details");
				if(lengthDetail==0){
					
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							contractvoz //中间表DetailHVO
 						    };
					
					//DetailVO[] aggDetailVO = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd", "5730", srcVosAfterFilter);//30-Cxx-pgscdd是上游单据交易类型，4331是下游单据类型
					try {
						aggDetailVO = (DetailVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd", "5730", srcVosAfterFilter[0]);
						aggDetailVO.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggDetailVO.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//攀钢生产订单号
						aggDetailVO.getParentVO().setStatus(VOStatus.NEW);
						aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
						aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
						aggDetailVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
						aggDetailVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//数量
						aggDetailVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//主数量
						aggDetailVO.getChildrenVO()[0].setStatus(VOStatus.NEW);//
						
						aggDetailVO.setChildren(DetailCLVO.class, new DetailCLVO[]{});
						aggDetailVO.setChildren(DetailCMSNVO.class, new DetailCMSNVO[]{});
						aggDetailVO.setChildren(DetailDOCVO.class, new DetailDOCVO[]{});
						aggDetailVO.setChildren(DetailFEEVO.class, new DetailFEEVO[]{});
						aggDetailVO.setChildren(DetailInfoVO.class, new DetailInfoVO[]{});
						aggDetailVO.setChildren(DetailMatchVO.class, new DetailMatchVO[]{});
						aggDetailVO.setChildren(DetailPayVO.class, new DetailPayVO[]{});
						
						//===== 生成单据编码 ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("5730", aggDetailVO.getParentVO().getAttributeValue("pk_group").toString(), aggDetailVO.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggDetailVO.getParent()});
						aggDetailVO.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== 生成单据编码 ====
						VORowNoUtils.setVOsRowNoByRule(new DetailVO[] { aggDetailVO });

						//NCObject objsCPD = NCObject.newInstance(aggDetailVO);//AGGVO对象
						//ncobject[1]=objsCPD;
					} catch (Exception e) {
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "出口明细信息转换失败");
						this.writeBack(aggvo);
						return "";
						}
					}/*else{
						//回写中间表
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "出口明细信息已存在");
						this.writeBack(aggvo);
						return "";
					}*/
				else{
					//当采购订单内无数据时，只向采购订单子表内插入数据
					DetailVO detailvo = new DetailVO();
					int lengthSubDetail = this.FindSubOrderNo(aggvo,"details");
					if(lengthSubDetail==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						String id="";
						try{
							NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DetailHVO.class , "dr=0 and saleorderno = '"+pasorderNumPf+"'", false);
							detailvo = (DetailVO)ncObjects[0].getContainmentObject();
							id = detailvo.getParent().getPrimaryKey();
						} catch(Exception e){
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "查询出口明细中订单号为"+pasorderNumPf+"的主信息失败");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								contractvoz //中间表
	 						    };
						//DetailVO[] aggordervo = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd_b", "5730", srcVosAfterFilter);
						try {
							aggDetailVO = (DetailVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd_b", "5730", srcVosAfterFilter[0]);
							aggDetailVO.setParentVO(detailvo.getParentVO());
							aggDetailVO.getParent().setStatus(VOStatus.UPDATED);
							aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//攀钢销售订单号
							aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//攀钢销售订单子项号
							aggDetailVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//攀钢生产订单子项号
							aggDetailVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//数量
							aggDetailVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//主数量
							aggDetailVO.getChildrenVO()[0].setAttributeValue("pk_detail", id);//上层单据主键
							aggDetailVO.getChildrenVO()[0].setStatus(VOStatus.NEW);
							

							DetailBVO[] detailbvo  = (DetailBVO[])queryService.queryByCondition(DetailBVO.class, "pk_detail = '" + id
									+ "' and dr=0");
							
							DetailBVO[] detailbvos = new DetailBVO[detailbvo.length+1];
							
							detailbvos[0] = (DetailBVO)aggDetailVO.getChildren(DetailBVO.class)[0];
							for(int i=0;i<detailbvo.length;i++){
								detailbvos[i+1]=detailbvo[i];
							}
							
							aggDetailVO.setChildren(DetailBVO.class, detailbvos);
							VORowNoUtils.setVOsRowNoByRule(new DetailVO[] { aggDetailVO });
							/*DetailCLVO detailclvo=new DetailCLVO();
							detailclvo.setStatus(VOStatus.UNCHANGED);*/
							aggDetailVO.setChildren(DetailCLVO.class, new DetailCLVO[]{});
							aggDetailVO.setChildren(DetailCMSNVO.class, new DetailCMSNVO[]{});
							aggDetailVO.setChildren(DetailDOCVO.class, new DetailDOCVO[]{});
							aggDetailVO.setChildren(DetailFEEVO.class, new DetailFEEVO[]{});
							aggDetailVO.setChildren(DetailInfoVO.class, new DetailInfoVO[]{});
							aggDetailVO.setChildren(DetailMatchVO.class, new DetailMatchVO[]{});
							aggDetailVO.setChildren(DetailPTVO.class, new DetailPTVO[]{});
							aggDetailVO.setChildren(DetailBBBVO.class, new DetailBBBVO[]{});
							aggDetailVO.setChildren(DetailPayVO.class, new DetailPayVO[]{});
							
							
						} catch (Exception e) {
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "出口明细信息转换失败");
							this.writeBack(aggvo);
							return "";
						}	
					}else{
						//回写中间表
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "出口明细信息已存在");
						this.writeBack(aggvo);
						return "";
					}
										
				}		
			}
			/**
			 * 生成对照表
			 */
			int length = this.FindSaleOrderNo(aggvo, "pgpdtorderrelation");
			if(length==0){
				Pgpdtorderrelation pgpdtorderrelation = new Pgpdtorderrelation();
				
				if(expFlag.equals("N")){
					pgpdtorderrelation.setBusinesstype("30");
					String vbillcode =this.Findvbillcode(aggvo, "reqOrder");
					pgpdtorderrelation.setVbillcode(vbillcode);
				}else if(expFlag.equals("C")){
					pgpdtorderrelation.setBusinesstype("5720");
					String vbillcode =this.Findvbillcode(aggvo, "sendDetails");
					pgpdtorderrelation.setVbillcode(vbillcode);
				}
				try{
				pgpdtorderrelation.setExpflag(expFlag);
				pgpdtorderrelation.setPk_contract(erpordernumpf);
				pgpdtorderrelation.setPk_contract_b(erporderno);
				pgpdtorderrelation.setSaleorderno(aggvo.getParent().getAttributeValue("ordernumpf").toString());
				pgpdtorderrelation.setSaleorderrowno(aggvo.getParent().getAttributeValue("orderno").toString());
				pgpdtorderrelation.setProductorderno(aggvo.getParent().getAttributeValue("mdordernumpf").toString());
				pgpdtorderrelation.setProductorderrowno(aggvo.getParent().getAttributeValue("mdorderno").toString());
				pgpdtorderrelation.setIs_interface(aggvo.getParent().getAttributeValue("directdelivyflag").equals("N")?UFBoolean.FALSE:UFBoolean.TRUE);
				Date date = new Date();  
				UFDateTime systime= new UFDateTime(date.getTime());
				pgpdtorderrelation.setCreationtime(systime);
				pgpdtorderrelation.setStatus(VOStatus.NEW);
				objsDZB = NCObject.newInstance(pgpdtorderrelation);//AGGVO对象
				ncobject[2]=objsDZB;
				}catch(Exception e){
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "对照表信息转换失败");
					this.writeBack(aggvo);
					return "";
				}
			}else{
				aggvo.getParent().setAttributeValue("msgflag", "2");
				aggvo.getParent().setAttributeValue("msginfo", "对照表信息已存在");
				this.writeBack(aggvo);
				return "";
			}
			
		
			
			String flag="";
					try {//SaleOrderVO aggsaleordervo= new SaleOrderVO();
						IMsgXSAGHT0001Save is = NCLocator.getInstance().lookup(IMsgXSAGHT0001Save.class);
						flag = is.mXSAGHT0001Head_RequiresNew(ncobject,aggOrderVos,aggDeliveryVO,aggDetailVO,aggsaleordervo,expFlag);
					}catch(Exception e){
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", e.getMessage());
						this.writeBack(aggvo);
						return "";
					}
					if(flag.equals("ok")){
						aggvo.getParent().setAttributeValue("msgflag", "1");
						aggvo.getParent().setAttributeValue("msginfo", "接口数据处理成功");
					}else{
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "接口数据处理失败");
					}
					this.writeBack(aggvo);
					
			
		
		return "OK";
}
	
	public void writeBack(AggXsaght0001HVO aggvo){
		
			Date date = new Date();  
			UFDateTime systime= new UFDateTime(date.getTime());
			aggvo.getParent().setAttributeValue("modifiedtime", systime);
			aggvo.getParent().setStatus(VOStatus.UPDATED);
			NCObject objs = NCObject.newInstance(aggvo);
			IMsgXSAGHT0001Save is = NCLocator.getInstance().lookup(IMsgXSAGHT0001Save.class);
			is.mXSAGHT0001Head_RequiresNew(objs);
	}
	/**
	 * 根据订单号查询相应的主表内是否有该订单的数据
	 * Parma msgXSAGHT0001接口传递的采购订单数据
	 * type查询表的类型：reqOrder-采购订单，sendDetails-发货单，details-出口明细
	 */
	public int FindSaleOrderNo(AggXsaght0001HVO aggvo,String type) {
		//ordernumpf//mdorderno
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "";
		SQLParameter sp = new SQLParameter();
		if(type.equals("reqOrder")){
			sqlStr = "select count(*) from po_order where saleorderno=? and dr=0";	
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
		}else if(type.equals("sendDetails")){
			sqlStr = "select count(*) from so_delivery where saleorderno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
		}else if(type.equals("details")){
			sqlStr = "select count(*) from et_detail where saleorderno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
		}else if(type.equals("pgpdtorderrelation")){
			sqlStr = "select count(*) from lm_pgpdtorderrelation where saleorderno=? and productorderrowno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}
		String li = "0";
		try {
			li = (String) baseDao.executeQuery(sqlStr, sp,
					new ResultSetProcessor() {
						@Override
						public String handleResultSet(ResultSet rs)
								throws SQLException {
							rs.next();
							return rs.getString(1);
						}
					});
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException("执行失败!");
		}
		return Integer.parseInt(li);
	}	
	/**
	 * 根据订单号查询相应的子表内是否有该订单的数据
	 * Parma msgXSAGHT0001接口传递的采购订单数据
	 * type查询表的类型：reqOrder-采购订单，sendDetails-发货单，details-出口 明细
	 */
	public int FindSubOrderNo(AggXsaght0001HVO aggvo,String type) {
		//String expFlag = msgXSAGHT0001.getExpFlag();//内贸、出口业务标识
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "";
		SQLParameter sp = new SQLParameter();
		if(type.equals("reqOrder")){
			sqlStr = "select count(*) from po_order_b where productorderrowno=? and dr=0";	
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}else if(type.equals("sendDetails")){
			sqlStr = "select count(*) from so_delivery_b where productorderrowno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}else if(type.equals("details")){
			sqlStr = "select count(*) from et_detail_b where productorderrowno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}
		String li = "0";
		try {
			li = (String) baseDao.executeQuery(sqlStr, sp,
					new ResultSetProcessor() {
						@Override
						public String handleResultSet(ResultSet rs)
								throws SQLException {
							rs.next();
							return rs.getString(1);
						}
					});
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException("执行失败!");
		}
		return Integer.parseInt(li);
	}
	public String Findvbillcode(AggXsaght0001HVO aggvo,String type) {
		//ordernumpf//mdorderno
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "";
		SQLParameter sp = new SQLParameter();
		if(type.equals("reqOrder")){
			sqlStr = "select vbillcode from so_saleorder where csaleorderid=?";	
			sp.addParam(aggvo.getParent().getAttributeValue("erpordernumpf"));
		}else if(type.equals("sendDetails")){
			sqlStr = "select vbillcode from et_contract where pk_contract=?";
			sp.addParam(aggvo.getParent().getAttributeValue("erpordernumpf"));
		}
		String li = "0";
		try {
			li = (String) baseDao.executeQuery(sqlStr, sp,
					new ResultSetProcessor() {
						@Override
						public String handleResultSet(ResultSet rs)
								throws SQLException {
							rs.next();
							return rs.getString(1);
						}
					});
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException("执行失败!");
		}
		return li;
	}
	

	public int findCount(String ordernumpf) {
		//ordernumpf//mdorderno
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "select count(*) from msg_xsaght0001 where ordernumpf=? and dr=0";
		SQLParameter sp = new SQLParameter();
		sp.addParam(ordernumpf);
		String li = "0";
		try {
			li = (String) baseDao.executeQuery(sqlStr, sp,
					new ResultSetProcessor() {
						@Override
						public String handleResultSet(ResultSet rs)
								throws SQLException {
							rs.next();
							return rs.getString(1);
						}
					});
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException("执行失败!");
		}
		return Integer.parseInt(li);
	}
	/*
	 * 根据接口中间层传递的参数，转换采购订单主Vo
	 */
	/*public OrderHeaderVO TransMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
		OrderHeaderVO orderHeaderVO = new OrderHeaderVO();
		//orderHeaderVO.setAttributeValue("vbillcode", );//订单编号
		orderHeaderVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		orderHeaderVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//攀钢生产订单号
		orderHeaderVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC合同号
		orderHeaderVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//订单日期
//		orderHeaderVO.setAttributeValue("pk_supplier", AppContext.getInstance().getServerTime());//供应商
//		orderHeaderVO.setAttributeValue("pk_invcsupllier", AppContext.getInstance().getServerTime());//开票供应商
		orderHeaderVO.setAttributeValue("billstatus ", "1");//审批状态
//		orderHeaderVO.setAttributeValue("pk_group ", "1");//所属集团
//		orderHeaderVO.setAttributeValue("pk_org ", "1");//采购组织版本信息
//		orderHeaderVO.setAttributeValue("pk_org_v ", "1");//采购组织
		return orderHeaderVO;
	}*/
	/*
	 * 根据接口中间层传递的参数，转换采购订单明细Vo
	 */
	/*public OrderItemVO[] TransSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		OrderItemVO[] orderItemVO = new OrderItemVO[1];
		orderItemVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		orderItemVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
		orderItemVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号
//		orderItemVO[0].setAttributeValue("pk_material", msgXSAGHT0001.getMdOrderNo());//物料版本信息
		String expFlag = msgXSAGHT0001.getExpFlag();//内贸、出口业务标识
		if(expFlag.equals("N")){
			orderItemVO[0].setAttributeValue("cfirsttypecode ", "30");//源头单据类型		
		}else{
			orderItemVO[0].setAttributeValue("cfirsttypecode ", "5720");//源头单据类型	
		}
//		orderItemVO[0].setAttributeValue("cfirstid ", msgXSAGHT0001.getErpOrderNo());//源头单据
		orderItemVO[0].setAttributeValue("cfirstbid  ", msgXSAGHT0001.getErpOrderNo());//源头单据明细
		orderItemVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
		orderItemVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
		if(expFlag.equals("N")){
			orderItemVO[0].setAttributeValue("csourcetypecode", "30");//源头单据类型		
		}else{
			orderItemVO[0].setAttributeValue("csourcetypecode", "5720");//源头单据类型	
		}
//		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//来源单据
		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//来源单据明细
		orderItemVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
		orderItemVO[0].setAttributeValue("mainamount", msgXSAGHT0001.getContOrderNum());//主数量
		return orderItemVO;
	}*/	
	/*
	 * 根据接口中间层传递的参数，转换发货单主Vo
	 */
	/*public DeliveryHVO TransSendMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DeliveryHVO deliveryHVO = new DeliveryHVO();		
//		deliveryHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//单据号		
		deliveryHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//单据日期
		deliveryHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		deliveryHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//攀钢生产订单号
		deliveryHVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC合同号
		deliveryHVO.setAttributeValue("fstatusflag", "2");//单据状态
//		deliveryHVO.setAttributeValue("pk_group", "2");//单据状态
//		deliveryHVO.setAttributeValue("pk_org", "2");//单据状态
//		deliveryHVO.setAttributeValue("pk_org_v", "2");//单据状态
		return deliveryHVO;
	}*/
	/*
	 * 根据接口中间层传递的参数，转换发货单明细Vo
	 */
	/*public DeliveryBVO[] TransSendSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DeliveryBVO[] deliveryBVO = new DeliveryBVO[1];
		deliveryBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		deliveryBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
		deliveryBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号
		deliveryBVO[0].setAttributeValue("vsrctype", "30");//源单据类型
		deliveryBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
		//deliveryBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNumPf());//来源单据表头ID
		deliveryBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//来源单据表体ID
		deliveryBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
		deliveryBVO[0].setAttributeValue("vfirsttype", "30");//源头单据类型
		deliveryBVO[0].setAttributeValue("cfirstbid", msgXSAGHT0001.getErpOrderNo());//源头单据表体ID
//		deliveryBVO[0].setAttributeValue("cfirstid", msgXSAGHT0001.getErpOrderNo());//源头单据表头ID
		deliveryBVO[0].setAttributeValue("cmaterialid", msgXSAGHT0001.getErpOrderNo());//物料档案
		deliveryBVO[0].setAttributeValue("cmaterialvid", msgXSAGHT0001.getErpOrderNo());//物料编码
//		deliveryBVO[0].setAttributeValue("cordercustid", msgXSAGHT0001.getErpOrderNo());//客户编码
//		deliveryBVO[0].setAttributeValue("cinvoicecustid", msgXSAGHT0001.getErpOrderNo());//开票客户
		deliveryBVO[0].setAttributeValue("csaleorgvid", msgXSAGHT0001.getErpOrderNo());//销售组织
		deliveryBVO[0].setAttributeValue("csettleorgid", msgXSAGHT0001.getErpOrderNo());//结算财务组织最新版本
		deliveryBVO[0].setAttributeValue("csettleorgvid", msgXSAGHT0001.getErpOrderNo());//结算财务组织
		deliveryBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
		deliveryBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//主数量
		return deliveryBVO;
	}*/
	/*
	 * 根据接口中间层传递的参数，转换出口明细主Vo
	 */
	/*public DetailHVO TransDetailHVOMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DetailHVO detailHVO = new DetailHVO();
		detailHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//单据日期
		detailHVO.setAttributeValue("dmakedate", AppContext.getInstance().getServerTime());//制单日期
//		detailHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//出口明细单号
		detailHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		detailHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//攀钢生产订单号
//		detailHVO.setAttributeValue("carorgid", msgXSAGHT0001.getMdOrderNumPf());//应收组织最新版本
//		detailHVO.setAttributeValue("carorgvid", msgXSAGHT0001.getMdOrderNumPf());//应收组织
//		detailHVO.setAttributeValue("csettleorgid", msgXSAGHT0001.getMdOrderNumPf());//结算财务组织最新版本
//		detailHVO.setAttributeValue("csettleorgvid", msgXSAGHT0001.getMdOrderNumPf());//结算财务组织
//		detailHVO.setAttributeValue("ctradewordid", msgXSAGHT0001.getMdOrderNumPf());//贸易术语
//		detailHVO.setAttributeValue("ctransporttypeid", msgXSAGHT0001.getMdOrderNumPf());//运输方式
//		detailHVO.setAttributeValue("ccustomerid", msgXSAGHT0001.getMdOrderNumPf());//客户
//		detailHVO.setAttributeValue("cinvoicecustid", msgXSAGHT0001.getMdOrderNumPf());//收票客户
//		detailHVO.setAttributeValue("cdeptid", msgXSAGHT0001.getMdOrderNumPf());//部门最新版本
//		detailHVO.setAttributeValue("cdeptvid", msgXSAGHT0001.getMdOrderNumPf());//部门
//		detailHVO.setAttributeValue("cloadportid", msgXSAGHT0001.getMdOrderNumPf());//起运港
//		detailHVO.setAttributeValue("cdestportid", msgXSAGHT0001.getMdOrderNumPf());//目的港
//		detailHVO.setAttributeValue("corigcurrencyid", msgXSAGHT0001.getMdOrderNumPf());//币种
//		detailHVO.setAttributeValue("pk_group", msgXSAGHT0001.getMdOrderNumPf());//集团
//		detailHVO.setAttributeValue("pk_org", msgXSAGHT0001.getMdOrderNumPf());//销售组织
//		detailHVO.setAttributeValue("pk_org_v", msgXSAGHT0001.getMdOrderNumPf());//销售组织版本
		detailHVO.setAttributeValue("fpfstatusflag", "1");//审批流状态
		detailHVO.setAttributeValue("fstatusflag", "3");//单据状态
		return detailHVO;
	}*/
	/*
	 * 根据接口中间层传递的参数，转换出口明细子Vo
	 */
	/*public DetailBVO[] TransDetailBVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DetailBVO[] detailBVO = new DetailBVO[1];	
		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号		
//		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//来源单据主表
		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//来源单据附表
		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
		detailBVO[0].setAttributeValue("vsrctype", "5720");//来源单据类型
//		detailBVO[0].setAttributeValue("cfirstbid", );//源头单据子表
//		detailBVO[0].setAttributeValue("cfirstid", );//源头单据主表
		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
		detailBVO[0].setAttributeValue("vfirsttype", "5720");//来源单据类型
//		detailBVO[0].setAttributeValue("cmaterialid", "5720");//物料最新版本
//		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//物料编码
		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//主数量	
//		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//单价
//		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//主单价	
		return detailBVO;
	}*/
	
	/*
	 * 根据接口中间层传递的参数，转换对照表VO
	 */
	/*public DetailBVO[] TransdzbVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DetailBVO[] detailBVO = new DetailBVO[1];	
		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//攀钢销售订单号
		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//攀钢销售订单子项号
		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//攀钢生产订单子项号		
//		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//来源单据主表
		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//来源单据附表
		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//来源单据号
		detailBVO[0].setAttributeValue("vsrctype", "5720");//来源单据类型
//		detailBVO[0].setAttributeValue("cfirstbid", );//源头单据子表
//		detailBVO[0].setAttributeValue("cfirstid", );//源头单据主表
		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//源头单据号
		detailBVO[0].setAttributeValue("vfirsttype", "5720");//来源单据类型
//		detailBVO[0].setAttributeValue("cmaterialid", "5720");//物料最新版本
//		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//物料编码
		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//数量
		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//主数量	
//		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//单价
//		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//主单价	
		return detailBVO;
	}*/
	
	/*
	 * 根据接口中间层传递的参数，转换中间表VO
	 */
	/*public AggLmpgjkHVO TranszjbVOMainVO(MsgXSAGHT0001Head msgxsaght0001head,String flag){
		
		AggLmpgjkHVO agglmpgjkhvo = new AggLmpgjkHVO();
		
		LmpgjkHVO lmpgjkHVO = new LmpgjkHVO();	
		lmpgjkHVO.setAttributeValue("dwid", msgxsaght0001head.getMsgId());//电文ID
		lmpgjkHVO.setAttributeValue("sendtype", "1");//方向类型 1.接收
		lmpgjkHVO.setAttributeValue("interfacetype", "2");//接口类型 2.攀钢基地销售订单
		lmpgjkHVO.setAttributeValue("flag", flag);//操作标识 1.成功 2.失败
		lmpgjkHVO.setAttributeValue("receivetime", AppContext.getInstance().getServerTime());//接收时间 
		lmpgjkHVO.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getOrderNumPf());//攀钢销售订单号
		lmpgjkHVO.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getMdOrderNumPf());//买断订单号
		lmpgjkHVO.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getErpOrderNumPf());//ERP合同号
		lmpgjkHVO.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getOrderStatusPf());//订单状态_PF
		lmpgjkHVO.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderCertNo());//订货凭据
		lmpgjkHVO.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getOrderTypeCode());//订单性质代码
		lmpgjkHVO.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getExpFlag());//内外销标志 
		lmpgjkHVO.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getOrderPeriodPf());//订货期别_PF
		lmpgjkHVO.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getResoPeriodPf());//资源期别_PF
		lmpgjkHVO.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getSaleNetworkPf());//销售网络
		lmpgjkHVO.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getProdClassCode());//产品大类代码
		lmpgjkHVO.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getCompanyCodePf());//公司别_PF
		lmpgjkHVO.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getTradeMode());//贸易方式
		lmpgjkHVO.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getSettleMode());//结算方式代码
		lmpgjkHVO.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getSignSitePf());//履约地点代码_PF
		lmpgjkHVO.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getPriceFormulaMode());//计价方式
		lmpgjkHVO.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getRmaNoPf());//异议号_PF
		lmpgjkHVO.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getContInDate());//合约录入日期
		lmpgjkHVO.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getContInPerson());//合约录入人
		lmpgjkHVO.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getOrderModiMark());//订单变更中标志
		lmpgjkHVO.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getOrderUserCodePf());//订货用户代码_PF
		lmpgjkHVO.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getOrderUserCnamePf());//订货用户中文名称_PF
		lmpgjkHVO.setAttributeValue("attrname22", null == msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNum()? "":msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNum().toString());//订货用户地址码 
		lmpgjkHVO.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNamePf());//订货用户地址_PF
		lmpgjkHVO.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getSettleUserNum());//需方用户代码
		lmpgjkHVO.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getSettleUserNamePf());//需方用户名称_PF
		lmpgjkHVO.setAttributeValue("attrname26", null == msgxsaght0001head.getMsgBody().get(0).getSettUserAddrNum()? "":msgxsaght0001head.getMsgBody().get(0).getSettUserAddrNum().toString());//需方用户地址码
		lmpgjkHVO.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getSettUserAddrPf());//需方用户地址_PF
		lmpgjkHVO.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctNum().toString());//需方用户帐户序号
		lmpgjkHVO.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctPf());//需方用户帐户_PF
		lmpgjkHVO.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getContOrderNum().toString());//合约子项数量 
		lmpgjkHVO.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getContTotWt().toString());//合约总重量
		lmpgjkHVO.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getContTotAmt().toString());//合约总金额 
		lmpgjkHVO.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getContTrnpTotAmt().toString());//合约运费总金额 
		lmpgjkHVO.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getContDepositNum().toString());//合约押金量
		lmpgjkHVO.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getContDepositAmt().toString());//合约押金金额 
		lmpgjkHVO.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getContPrepayRate().toString());//合约定金比例 
		lmpgjkHVO.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getContPrepayLackAmt().toString());//合约应收定金额
		lmpgjkHVO.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getTaxRatePf().toString());//税率_PF 
		lmpgjkHVO.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getOrderNo());//合同号
		lmpgjkHVO.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getMdOrderNo());//买断订单子项号 
		lmpgjkHVO.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getErpOrderNo());//ERP合同子项号
		lmpgjkHVO.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getOrderModiNum().toString());//合同变更次数
		lmpgjkHVO.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderLastSeqNo());//合同子项序号
		lmpgjkHVO.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getSaleId());//销售业务员工号
		lmpgjkHVO.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getRecCreateTime());//记录创建时间  
		lmpgjkHVO.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPartOrderFlag());//套裁订单标志
		lmpgjkHVO.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getCutNum().toString());//纵切条数 
		lmpgjkHVO.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getOrderItemStatusCode());//销售订单子项状态
		lmpgjkHVO.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getOrderItemModiMark());//订单子项变更中标记
		lmpgjkHVO.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getModiCause());//变更原因 
		lmpgjkHVO.setStatus(VOStatus.NEW);
		
		LmpgjkaBVO[] lmpgjkabvos = new LmpgjkaBVO[1];
		LmpgjkaBVO lmpgjkabvo = new LmpgjkaBVO();
		lmpgjkabvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getModiDate());//变更日期
		lmpgjkabvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getModiOperPerson());//合同变更操作人
		lmpgjkabvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getModiType());//变更性质 
		lmpgjkabvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getFinUserNum());//最终用户代码
		lmpgjkabvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getFinUserName());//最终用户名称
		lmpgjkabvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getFinUserTrade());//最终用户的行业代码
		lmpgjkabvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getUserCredit());//用户信誉度
		lmpgjkabvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getConsignNum());//收货用户代码
		lmpgjkabvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getConsignNamePf());//收货用户名称_PF
		lmpgjkabvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum().toString());//收货用户地址码
		lmpgjkabvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressPf());//收货用户地址_PF
		lmpgjkabvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getConsignNum1());//收货用户代码1
		lmpgjkabvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getConsignName1Pf());//收货用户名称1_PF
		lmpgjkabvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum1().toString());//收货用户地址码1
		lmpgjkabvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getCnsgAddress1Pf());//收货用户地址1_PF
		lmpgjkabvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleCode());//运费发票抬头代码
		lmpgjkabvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleText());//运费发票抬头
		lmpgjkabvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getPsr());//产品规范码 
		lmpgjkabvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getApn());//最终用途 
		lmpgjkabvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getMsc());//冶金规范码 
		lmpgjkabvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getNewProductFlag());//新试产品标志
		lmpgjkabvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getProductDscr());//产品名称
		lmpgjkabvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getShopsignPf());//牌号_PF
		lmpgjkabvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getSteelStd());//产品标准
		lmpgjkabvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getCapabYnFlag());//性能是否要求标志 
		lmpgjkabvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getRainCoatFlag());//加盖雨布标志
		lmpgjkabvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getQcType());//质保书类型_PF
		lmpgjkabvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getCertiNum().toString());//质保书份数
		lmpgjkabvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPackingCode());//包装代码_PF
		lmpgjkabvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getOrderPriority());//订单优先级 
		lmpgjkabvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getMetFeetFlag());//公英制标志
		lmpgjkabvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getOrderThick().toString());//订货厚 
		lmpgjkabvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getOrderWidthPf().toString());//订货宽
		lmpgjkabvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getOrderLenLow().toString());//订货长度下限 
		lmpgjkabvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderLenUp().toString());//订货长度上限
		lmpgjkabvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getThickEng());//英制厚度
		lmpgjkabvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWidthEng());//英制宽度
		lmpgjkabvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getLengthMinEng());//英制长度下限
		lmpgjkabvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getLengthMaxEng());//英制长度上限
		lmpgjkabvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getWeightMethod());//计重方式
		lmpgjkabvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getOrderUnit());//订货计量单位
		lmpgjkabvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getOrderWtPf().toString());//订货重量_PF
		lmpgjkabvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getPreAlcaWtPf().toString());//预合同分配量_PF
		lmpgjkabvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderPiece().toString());//订货件数 
		lmpgjkabvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getUnitWtUpPf().toString());//单件最大重量_PF
		lmpgjkabvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnitWtLowPf().toString());//单件最小重量_PF
		lmpgjkabvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getDelivyTolUpper().toString());//公差上限
		lmpgjkabvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getDelivyTolLower().toString());//公差下限 
		//lmpgjkabvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getCoilInsideDimPf().toString());//内径_PF
		lmpgjkabvo.setAttributeValue("attrname48",0);//内径_PF
		lmpgjkabvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getShortSizeRatePf().toString());//订货短尺率_PF
		lmpgjkabvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getShortSizeLowPf().toString());//订货短尺下限_PF
		lmpgjkabvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getShortSizeUpPf().toString());//订货短尺上限_PF
		lmpgjkabvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getShortSize1().toString());//短尺长度1 
		lmpgjkabvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getShortSize2().toString());//短尺长度2 
		lmpgjkabvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getShortSize3().toString());//短尺长度3 
		lmpgjkabvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getShortSize4().toString());//短尺长度4 
		lmpgjkabvo.setStatus(VOStatus.NEW);
		
		lmpgjkabvos[0]=lmpgjkabvo;
		
		LmpgjkbBVO[] lmpgjkbbvos = new LmpgjkbBVO[1];
		LmpgjkbBVO lmpgjkbbvo = new LmpgjkbBVO();
		lmpgjkbbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getShortSize5().toString());//短尺长度5
		lmpgjkbbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getDeliveryDateChr());//约定交货日期
		lmpgjkbbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getDelivyPeriodPf());//交货期别_PF
		lmpgjkbbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getDeliveryWeekMark());//按周交货标志
		lmpgjkbbvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderReadyDate());//订单备妥日期 
		lmpgjkbbvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getLaunchTime());//下发时间 
		lmpgjkbbvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getLaunchId());//下发人工号
		lmpgjkbbvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstTime());//初次下发时间 
		lmpgjkbbvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstId());//初次下发人工号
		lmpgjkbbvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getVersionNumPf().toString());//版本号_PF
		lmpgjkbbvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getOrderPriceMode());//合同计价方式 
		lmpgjkbbvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getPriceBaseDate());//价格执行基准日
		lmpgjkbbvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getSaleUnitPrice().toString());//销售 单价 
		lmpgjkbbvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getAgreemtCode());//协议价格单号
		lmpgjkbbvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getAgreePrice().toString());//协议价格 
		lmpgjkbbvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getOrderAmtPf().toString());//订单金额_PF
		lmpgjkbbvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getSaleRemark());//销售备注
		lmpgjkbbvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getA_flag());//A项标志  
		lmpgjkbbvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getUserSpecDesc());//用户质量特殊要求
		lmpgjkbbvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getDrewMode());//开票方式
		lmpgjkbbvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getDepositN().toString());//押金数
		lmpgjkbbvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getDepositAmtPf().toString());//押金金额_PF
		lmpgjkbbvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getTransType());//运输类型 
		lmpgjkbbvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getTrnpModeCodePf());//运输方式代码_PF
		lmpgjkbbvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCodePf());//终到站港代码_PF
		lmpgjkbbvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName());//终到站港描述
		lmpgjkbbvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCode1Pf());//终到站港代码1_PF
		lmpgjkbbvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName1());//终到站港描述1
		lmpgjkbbvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteCodePf());//专用线代码_PF
		lmpgjkbbvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteNamePf());//专用线描述_PF
		lmpgjkbbvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getTrnpPricePf().toString());//运费单价_PF
		lmpgjkbbvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getTrnpAmtPf().toString());//运费金额_PF
		lmpgjkbbvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getGuideMeasureAq());//镀层厚度计量方式
		lmpgjkbbvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getEdgeType());//切边形态
		lmpgjkbbvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderTechnicDes());//合同单技术参数描述
		lmpgjkbbvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getOrderByprodDegreeCode());//合同副产品等级代码
		lmpgjkbbvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWeightPlate());//锌层重量
		lmpgjkbbvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getGrainCode());//粒度代码
		lmpgjkbbvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getPatternSalient().toString());//纹高
		lmpgjkbbvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getSectionCode());//截面代码
		lmpgjkbbvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getSectionDesc());//截面描述
		lmpgjkbbvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getRecognCode());//产品标识代码
		lmpgjkbbvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getEndLineCode());//终到站省份 
		lmpgjkbbvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getEndLineName());//终到站地区别
		lmpgjkbbvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getTransProdCode());//运输产品大类
		lmpgjkbbvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnloadPoint());//卸车地点
		lmpgjkbbvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPriceMode());//定价模式
		lmpgjkbbvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getProdCode());//销售品种
		lmpgjkbbvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getStraragemMark());//客商合作关系等级
		lmpgjkbbvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getPriceModeCname());//定价模式名称 长度:10
		lmpgjkbbvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getProdCodeCname());//定价模式名称  长度:30 
		lmpgjkbbvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getStraragemMarkCname());//客商合作关系等级名称
		lmpgjkbbvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getInvoiceType());//营改增发票类型
		lmpgjkbbvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getInvoiceTypeName());//发票类型说明 
		lmpgjkbbvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getUserChineseName());//受票方名称 
		lmpgjkbbvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getTaxNum());//纳税人识别号
		lmpgjkbbvo.setStatus(VOStatus.NEW);
		
		lmpgjkbbvos[0] = lmpgjkbbvo;
		
		LmpgjkcBVO[] lmpgjkcbvos = new LmpgjkcBVO[1];
		LmpgjkcBVO lmpgjkcbvo = new LmpgjkcBVO();
		lmpgjkcbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getAddressLocationc());//地址
		lmpgjkcbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getBankBranchName());//开户银行 
		lmpgjkcbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getAccountNum());//账号
		lmpgjkcbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getTelNum());//电话
		lmpgjkcbvo.setStatus(VOStatus.NEW);
		
		lmpgjkcbvos[0] = lmpgjkcbvo;
		
		agglmpgjkhvo.setParentVO(lmpgjkHVO);
		agglmpgjkhvo.setChildren(LmpgjkaBVO.class, lmpgjkabvos);	
		agglmpgjkhvo.setChildren(LmpgjkbBVO.class, lmpgjkbbvos);	
		agglmpgjkhvo.setChildren(LmpgjkcBVO.class, lmpgjkcbvos);	
		return agglmpgjkhvo;
	}*/

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		this.mXSAGHT0001Head_RequiresNew();
		// TODO 自动生成的方法存根
		return null;
	}
	
	/*public void loadBodyData(int row) {
		try {
			// 获得主表ID
			//查询子表VO数组，有几个子VO,就写几个VO数组，queryByCondition(vo类名，子表外键+过滤条件)
			//询价单子表vo
			XjdzbBodyVO[] xjdzbBodyVO = (XjdzbBodyVO[]) HYPubBO_Client
					.queryByCondition(XjdzbBodyVO.class, "PK_WLXJD = '" + id
							+ "'"+" and dr=0");
			//询价单包装清单vo
			WlxjdBzqdVO[] wlxjdBzqdVO = (WlxjdBzqdVO[]) HYPubBO_Client
					.queryByCondition(WlxjdBzqdVO.class, "PK_WLXJD = '" + id
							+ "'"+" and dr=0");
			getbillListPanel().getBillListData().setBodyValueVO("id_xjdzb", xjdzbBodyVO);
			getbillListPanel().getBillListData().setBodyValueVO("id_wlxjdbzqd", wlxjdBzqdVO);
			getbillListPanel().getBodyBillModel().execLoadFormula();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}
	}
*/
	
}

>>>>>>> .r4829
