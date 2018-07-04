INSERT INTO bd_billtype (ts, iseditableproperty, pk_billtypecode, ncbrcode, parentbilltype, canextendtransaction, istransaction, isbizflowbill, datafinderclz, referclassname, isaccount, pk_org, component, isroot, billtypename, emendenumclass, billcoderule, dr, nodecode, isenablebutton, pk_billtypeid, classname, systemcode, checkclassname, accountclass, islock, forwardbilltype, billtypename2, billtypename3, billtypename4, transtype_class, billtypename5, pk_group, billtypename6, webnodecode, billstyle, def3, isapprovebill, def2, isenabletranstypebcr, wherestring, def1 ) VALUES ('2016-11-17 17:04:40', null, 'OA01', '~', '~', 'Y', 'N', null, null, null, null, 'GLOBLE00000000000000', 'newoabase', null, 'OA审批基础字典-NEW01', null, '~', null, '40019101', null, '0001Z31000000000GECD', null, 'PO', null, null, null, null, null, null, null, null, null, '~', null, '~', null, null, 'Y', null, null, null, null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-17 17:07:35', null, null, '0001Z31000000000GECD', 'N', 'N', '0001Z31000000000GECE', null, 'DELETE', null, null, '删除', null, null, 30, 'N', '3', null, 'OA01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote5, actionnote, actionnote2, actionnote3, action_type, constrictflag, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-11-17 17:07:35', null, null, '0001Z31000000000GECD', 'N', 'N', '0001Z31000000000GECF', null, 'SAVEBASE', null, null, '保存', null, null, 31, 'Y', '1', null, 'OA01', null );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-17 17:07:35', '0001Z31000000000GECD', '~', 'N_OA01_DELETE', 'N', 'DELETE', '~', 0, 'OA01', '0001Z31000000000GECG' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-11-17 17:07:35', '0001Z31000000000GECD', '~', 'N_OA01_SAVEBASE', 'N', 'SAVEBASE', '~', 0, 'OA01', '0001Z31000000000GECH' );
