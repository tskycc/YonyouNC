insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EYX','采购组织',5,0,1,null,null,'pk_org',null,null,'N','Y','N','Y','N','N','N','N','Y','N','N','N','N','Y','Y',null,'Y',9999,null,'==','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-04-01 10:25:09','N','#mainorg#')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EYY','供应商档案',5,1,1,null,0,'pk_supplier',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EYZ','-99',3,2,1,null,null,'dbilldate',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','Y','Y',null,'Y',9999,null,'between@=@>@>=@<@<=@','介于@等于@大于@大于等于@小于@小于等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:50:00','N','#month(0)#,#day(0)#')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ0','-99',0,3,1,null,0,'vbillcode',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ1','物料（多版本）',5,4,1,null,0,'pk_order_b.pk_material',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ2','项目',5,5,1,null,0,'pk_order_b.cprojectid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ3','生产厂商(自定义档案)',5,6,1,null,0,'pk_order_b.cproductorid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ4','计量单位',5,7,1,null,0,'pk_order_b.castunitid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ5','-99',0,8,1,null,0,'pk_order_b.vbatchcode',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ6','客户档案',5,9,1,null,0,'pk_order_b.casscustid',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ7','库存组织',5,10,1,null,0,'pk_order_b.pk_arrvstoorg',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','Y','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ8','-99',0,11,1,null,0,'pk_order_b.vfree1',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZ9','-99',0,12,1,null,0,'pk_order_b.vfree2',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZA','-99',0,13,1,null,0,'pk_order_b.vfree3',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZB','-99',0,14,1,null,0,'pk_order_b.vfree4',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZC','-99',0,15,1,null,0,'pk_order_b.vfree5',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZD','-99',0,16,1,null,0,'pk_order_b.vfree6',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZE','-99',0,17,1,null,0,'pk_order_b.vfree7',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZF','-99',0,18,1,null,0,'pk_order_b.vfree8',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZG','-99',0,19,1,null,0,'pk_order_b.vfree9',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZH','-99',0,20,1,null,0,'pk_order_b.vfree10',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@like@left like@right like@','等于@包含@左包含@右包含@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2014-03-18 10:06:27','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZI','普通特征码档案',5,6,1,null,0,'pk_order_b.cffileid',null,null,'N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=','等于@',0,'@@@@','1001Z810000000004EYW',null,null,2,null,null,'2015-03-20 15:40:17','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1001Z810000000004EZJ','普通特征码档案',5,7,0,null,0,'pk_order_b.cffileid.vskucode','特征码编码',null,'N','N','N','N','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',9999,null,'=@left like@','等于@左包含@',0,'@@@@','1001Z810000000004EYW',null,'24004000-0016',0,null,null,'2015-05-21 20:40:17','N',null)
go

