DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001Z31000000000GECI';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'OA01' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'OA01';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001Z31000000000GECI';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001Z31000000000GECJ';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001Z31000000000GECJ';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000GECK';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000GECL';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000GECM';
