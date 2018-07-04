INSERT INTO bd_billtype (ts, iseditableproperty, pk_billtypecode, ncbrcode, parentbilltype, canextendtransaction, istransaction, isbizflowbill, datafinderclz, isaccount, referclassname, pk_org, component, isroot, billtypename, billcoderule, emendenumclass, dr, nodecode, isenablebutton, pk_billtypeid, classname, systemcode, accountclass, checkclassname, forwardbilltype, islock, billtypename2, billtypename3, billtypename4, transtype_class, billtypename5, pk_group, billtypename6, webnodecode, billstyle, def3, isapprovebill, isenabletranstypebcr, def2, wherestring, def1 ) VALUES ('2016-11-26 10:10:21', null, 'JT01', '~', '~', 'Y', 'N', null, null, null, null, 'GLOBLE00000000000000', 'cgfa', null, '采购方案', '~', null, null, '40040207', null, '0001Z31000000000B7Q2', null, 'PO', null, null, null, null, null, null, null, null, null, '~', null, '~', null, null, 'Y', null, null, null, null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-26 10:13:54', null, null, '0001Z31000000000B7Q2', 'N', 'N', '0001Z31000000000B7Q3', null, 'SAVE', null, null, '送审', null, null, 'N', 10, '1', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-26 10:13:54', null, null, '0001Z31000000000B7Q2', 'N', 'N', '0001Z31000000000B7Q4', null, 'APPROVE', null, null, '审核', null, null, 'N', 11, '2', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-26 10:13:54', null, null, '0001Z31000000000B7Q2', 'Y', 'Y', '0001Z31000000000B7Q5', null, 'UNSAVEBILL', null, null, '收回', null, null, 'N', 13, '3', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-26 10:13:54', null, null, '0001Z31000000000B7Q2', 'N', 'Y', '0001Z31000000000B7Q6', null, 'UNAPPROVE', null, null, '弃审', null, null, 'N', 12, '3', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-26 10:13:54', null, null, '0001Z31000000000B7Q2', 'N', 'N', '0001Z31000000000B7Q7', null, 'DELETE', null, null, '删除', null, null, 'N', 30, '3', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-26 10:13:54', null, null, '0001Z31000000000B7Q2', 'N', 'N', '0001Z31000000000B7Q8', null, 'SAVEBASE', null, null, '保存', null, null, 'Y', 31, '1', null, 'JT01', null );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-26 10:13:54', '0001Z31000000000B7Q2', '~', 'N_JT01_SAVE', 'N', 'SAVE', '~', 0, 'JT01', '0001Z31000000000B7Q9' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-26 10:13:54', '0001Z31000000000B7Q2', '~', 'N_JT01_APPROVE', 'N', 'APPROVE', '~', 0, 'JT01', '0001Z31000000000B7QA' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-26 10:13:54', '0001Z31000000000B7Q2', '~', 'N_JT01_UNSAVEBILL', 'N', 'UNSAVEBILL', '~', 0, 'JT01', '0001Z31000000000B7QB' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-26 10:13:54', '0001Z31000000000B7Q2', '~', 'N_JT01_UNAPPROVE', 'N', 'UNAPPROVE', '~', 0, 'JT01', '0001Z31000000000B7QC' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-26 10:13:54', '0001Z31000000000B7Q2', '~', 'N_JT01_DELETE', 'N', 'DELETE', '~', 0, 'JT01', '0001Z31000000000B7QD' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-26 10:13:54', '0001Z31000000000B7Q2', '~', 'N_JT01_SAVEBASE', 'N', 'SAVEBASE', '~', 0, 'JT01', '0001Z31000000000B7QE' );
