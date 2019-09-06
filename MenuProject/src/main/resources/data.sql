/**
 * CREATE Script for init of DB
 */
INSERT INTO MENU VALUES (1,false, 'menu2','M2',null);

INSERT INTO MENU VALUES (2,true, 'menu2','M2',null);
INSERT INTO MENU VALUES(3,true,'submenu1','S1', '2');
INSERT INTO ITEM VALUES(1,'item desc1',null,null,'I1','10-EUR',5,null,null,2);

INSERT INTO ITEM VALUES(2,'item desc2',null,null,'I2','14-EUR',3,null,null,3);

INSERT INTO ITEM VALUES(3,'item desc3',null,null,'I3','1-EUR',5,null,null,3);