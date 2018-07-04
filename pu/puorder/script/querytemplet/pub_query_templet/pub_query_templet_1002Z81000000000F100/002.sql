insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F101','-99',4,0,1,null,null,'bisoutcustom','已出关',null,'N','Y','N','Y','N','N','N','N','Y','Y','N','N','N','N','Y',null,'N',null,null,'==@','等于@',0,'@@@@','1002Z81000000000F100',null,'1400406100006',2,null,null,'2011-04-26 09:53:02','N','否')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F102','采购组织',5,1,1,null,0,'pk_org',null,null,'N','Y','N','Y','N','N','N','N','Y','N','N','N','N','Y','Y',null,'Y',null,null,'==','等于@',0,'@@@@','1002Z81000000000F100',null,null,2,null,null,'2011-04-26 09:53:03','N','#mainorg#')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F103','-99',0,2,1,null,0,'vbillcode',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',null,null,'=@left like@','等于@左包含@',0,'@@@@','1002Z81000000000F100',null,null,2,null,null,'2011-04-26 09:53:02','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F104','-99',3,3,1,null,null,'dbilldate',null,null,'N','Y','N','Y','N','N','N','N','Y','N','N','N','N','Y','Y',null,'Y',9999,null,'between@=@','介于@等于@',0,'@@@@','1002Z81000000000F100',null,null,2,null,null,'2011-09-06 16:00:47','N','#month(0)#,#day(0)#')
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F105','供应商档案',5,4,0,null,0,'pk_supplier.code',null,null,'N','N','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',null,null,'=@left like@','等于@左包含@',0,'@@@@','1002Z81000000000F100',null,null,0,null,null,'2011-04-26 09:53:02','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F106','供应商档案',5,5,1,null,0,'pk_supplier.name',null,null,'N','N','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',null,null,'=@left like@','等于@左包含@',0,'@@@@','1002Z81000000000F100',null,null,1,null,null,'2011-04-26 09:53:02','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F107','-99',3,6,1,null,null,'pk_order_b.pk_order_bb.dbilldate',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','Y','Y',null,'Y',9999,null,'between@=@','介于@等于@',0,'@@@@','1002Z81000000000F100',null,null,2,null,null,'2011-08-25 15:35:26','N',null)
go

insert into pub_query_condition(id,consult_code,data_type,disp_sequence,disp_type,disp_value,dr,field_code,field_name,guideline,if_attrrefused,if_autocheck,if_datapower,if_default,if_desc,if_group,if_immobility,if_multicorpref,if_must,if_notmdcondition,if_order,if_subincluded,if_sum,if_sysfuncrefused,if_used,instrumentsql,iscondition,limits,max_length,opera_code,opera_name,order_sequence,pk_corp,pk_templet,prerestrict,resid,return_type,table_code,table_name,ts,userdefflag,value) values('1002Z81000000000F108','-99',0,7,1,null,0,'pk_order_b.pk_order_bb.vbillcode',null,null,'N','Y','N','Y','N','N','N','N','N','N','N','N','N','N','Y',null,'Y',null,null,'=@left like@','等于@左包含@',0,'@@@@','1002Z81000000000F100',null,null,2,null,null,'2011-04-26 09:53:02','N',null)
go

