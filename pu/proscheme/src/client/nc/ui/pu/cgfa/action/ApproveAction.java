/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年3月22日 下午4:24:53 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;

/** 
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年3月22日 下午4:24:53 
 */
public class ApproveAction extends ApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		super.doAction(e);
		//在后台处理，不放在前台 王梓懿 2018-06-11
		//CgfaSendApprove.reFreshDate(this.getModel());
	}

}
