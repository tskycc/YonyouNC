insert into pub_pluginitem(pk_pluginitem,dr,industrytype,iorder,localtype,ts,vcomponentname,vdescription,veventtype,vextendmodule,vextendpointname,vextendtype,vmodulename,vruleclass,vtargetclass) values('1001Z8PRAYBTEMPSAVE1',0,'0',101,'~','2015-10-08 19:09:21','20','请购单保存时删除暂存数据','before','scmpub','UPDATE','addBefore','PO','nc.bs.scmpub.rule.DeleteTempDataBeforeRule',null)
go

insert into pub_pluginitem(pk_pluginitem,dr,industrytype,iorder,localtype,ts,vcomponentname,vdescription,veventtype,vextendmodule,vextendpointname,vextendtype,vmodulename,vruleclass,vtargetclass) values('1001Z8PRAYBTEMPSAVE2',0,'0',101,'~','2015-10-08 19:09:21','20','请购单保存时删除暂存数据','before','scmpub','INSERT','addBefore','PO','nc.bs.scmpub.rule.DeleteTempDataBeforeRule',null)
go

