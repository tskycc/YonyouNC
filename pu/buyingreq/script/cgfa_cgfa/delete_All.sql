DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001Z31000000000B7QF';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'JT01' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'JT01';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001Z31000000000B7QF';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001Z31000000000B7QG';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001Z31000000000B7QG';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000B7QH';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000B7QI';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000B7QJ';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001Z31000000000B7Q2';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001Z31000000000B7Q2';
DELETE FROM pub_function WHERE pk_billtype = 'JT01';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001Z31000000000B7Q2';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'JT01';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001Z31000000000B7Q2';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001Z31000000000B7Q3';
DELETE FROM pub_billaction WHERE pk_billaction = '0001Z31000000000B7Q4';
DELETE FROM pub_billaction WHERE pk_billaction = '0001Z31000000000B7Q5';
DELETE FROM pub_billaction WHERE pk_billaction = '0001Z31000000000B7Q6';
DELETE FROM pub_billaction WHERE pk_billaction = '0001Z31000000000B7Q7';
DELETE FROM pub_billaction WHERE pk_billaction = '0001Z31000000000B7Q8';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001Z31000000000B7Q9';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001Z31000000000B7QA';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001Z31000000000B7QB';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001Z31000000000B7QC';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001Z31000000000B7QD';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001Z31000000000B7QE';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001Z31000000000B7Q1';
delete from pub_print_datasource where ctemplateid = '0001Z31000000000B6Y8';
delete from pub_print_cell where ctemplateid = '0001Z31000000000B6Y8';
delete from pub_print_line where ctemplateid = '0001Z31000000000B6Y8';
delete from pub_print_variable where ctemplateid = '0001Z31000000000B6Y8';
delete from pub_print_template where ctemplateid = '0001Z31000000000B6Y8';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001Z31000000000B6Y7';
delete from pub_query_condition where pk_templet = '0001Z31000000000B6W4';
delete from pub_query_templet where id = '0001Z31000000000B6W4';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001Z31000000000B6W3';
delete from pub_billtemplet_b where pk_billtemplet = '0001Z31000000000B6S4';
delete from pub_billtemplet where pk_billtemplet = '0001Z31000000000B6S4';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001Z31000000000B6S4';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001Z31000000000B6S3';
DELETE FROM sm_funcregister WHERE cfunid = '0001Z31000000000B6S1';
DELETE FROM sm_paramregister WHERE pk_param = '0001Z31000000000B6S2';
