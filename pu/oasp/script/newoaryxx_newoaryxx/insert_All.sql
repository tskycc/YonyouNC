INSERT INTO sm_funcregister (ts, funtype, isbuttonpower, fun_name, isenable, funcode, class_name, iscauserusable, fun_desc, fun_property, isfunctype, orgtypecode, cfunid, help_name, pk_group, mdid, parent_id, own_module, dr ) VALUES ('2016-11-30 17:00:10', 0, 'N', 'OA人员基础字典', 'Y', '40019104', 'nc.ui.pubapp.uif2app.ToftPanelAdaptorEx', null, null, 0, 'N', 'GLOBLE00000000000000', '0001Z31000000000MNL6', null, '~', '~', '1001N610000000005930', '4001', 0 );
INSERT INTO sm_paramregister (ts, paramname, pk_param, parentid, dr, paramvalue ) VALUES ('2016-11-30 17:00:10', 'BeanConfigFilePath', '0001Z31000000000MNL7', '0001Z31000000000MNL6', 0, 'nc/ui/pu/newoaryxx/ace/view/Newoaryxx_config.xml' );
INSERT INTO sm_menuitemreg (ts, nodeorder, menuitemname, pk_menu, iconpath, resid, pk_menuitem, ismenutype, menudes, funcode, menuitemcode, dr ) VALUES ('2016-11-30 17:00:11', null, 'OA人员基础字典', '1004ZZ10000000000FFL', null, 'D40019104', '0001Z31000000000MNL8', 'N', null, '40019104', '40019104', 0 );
INSERT INTO pub_billtemplet (ts, BILL_TEMPLETCAPTION, BILL_TEMPLETNAME, NODECODE, PK_BILLTEMPLET, PK_BILLTYPECODE, PK_CORP, METADATACLASS, MODULECODE, LAYER ) VALUES ('2016-11-30 17:00:35', 'OA人员信息字典', 'SYSTEM', '40019104', '0001Z31000000000MNL9', '40019104', '@@@@', 'pu.newoaryxx', '4001', 0 );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'pk_id', 'N', 1, 'N', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLA', 1, 'N', 'N', 0, 1, 1, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.pk_id', 'pk_id', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'pk_group', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLB', 1, 'N', 'N', 1, 2, 2, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.pk_group', 'pk_group', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'pk_org', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLC', 1, 'N', 'N', 1, 3, 3, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.pk_org', 'pk_org', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'pk_org_v', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLD', 1, 'N', 'N', 1, 4, 4, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.pk_org_v', 'pk_org_v', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'code', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLE', 1, 'N', 'N', 1, 5, 5, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.code', 'code', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'name', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLF', 1, 'N', 'N', 1, 6, 6, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.name', 'name', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'creator', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLG', 1, 'N', 'N', 1, 7, 7, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.creator', 'creator', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'creationtime', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLH', 1, 'N', 'N', 1, 8, 8, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.creationtime', 'creationtime', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'modifier', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLI', 1, 'N', 'N', 1, 9, 9, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.modifier', 'modifier', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'modifiedtime', 'N', 1, 'Y', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLJ', 1, 'N', 'N', 1, 10, 10, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.modifiedtime', 'modifiedtime', 'N', 'N' );
INSERT INTO pub_billtemplet_b (ts, CARDFLAG, DATATYPE, EDITFLAG, FOREGROUND, INPUTLENGTH, ITEMKEY, LEAFFLAG, LISTFLAG, LISTSHOWFLAG, LOCKFLAG, NEWLINEFLAG, NULLFLAG, PK_BILLTEMPLET, PK_BILLTEMPLET_B, POS, REVISEFLAG, USERREVISEFLAG, SHOWFLAG, SHOWORDER, LISTSHOWORDER, TABLE_CODE, TABLE_NAME, TOTALFLAG, USERDEFFLAG, USEREDITFLAG, USERFLAG, USERSHOWFLAG, WIDTH, METADATAPROPERTY, METADATAPATH, HYPERLINKFLAG, LISTHYPERLINKFLAG ) VALUES ('2016-11-30 17:00:37', 1, -1, 1, -1, -1, 'ts', 'N', 1, 'N', 0, 'N', 0, '0001Z31000000000MNL9', '0001Z31000000000MNLK', 1, 'N', 'N', 0, 11, 11, 'newoaryxx', 'OA人员信息字典', 0, 'N', 1, 1, 1, 100, 'pu.newoaryxx.ts', 'ts', 'N', 'N' );
INSERT INTO pub_billtemplet_t (ts, position, resid, tabindex, vdef2, vdef1, pk_billtemplet_t, vdef3, metadatapath, tabcode, pos, pk_billtemplet, metadataclass, basetab, mixindex, tabname, pk_layout ) VALUES ('2016-11-30 17:00:38', null, null, 0, null, null, '0001Z31000000000MNLL', null, 'newoaryxx', 'newoaryxx', 1, '0001Z31000000000MNL9', 'pu.newoaryxx', null, null, 'OA人员信息字典', '~' );
INSERT INTO pub_systemplate_base (ts, nodekey, funnode, layer, moduleid, templateid, pk_systemplate, devorg, pk_country, tempstyle, pk_industry, dr ) VALUES ('2016-11-30 17:00:39', 'bt', '40019104', 0, '4001', '0001Z31000000000MNL9', '0001Z31000000000MNLM', 'yonyouBQ', '~', 0, '~', 0 );