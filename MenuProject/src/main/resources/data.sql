/**
 * CREATE Script for init of DB
 */
/**
*Adding menu and sub menu
*/
INSERT INTO MENU VALUES (1,false, 'Indian stuff','INDIAN',null);

INSERT INTO MENU VALUES (2,true, 'Italian stuff','ITALIAN',null);
INSERT INTO MENU VALUES(3,true,'Italian appitizer','ITALIAN ANRIPASTI', '2');
INSERT INTO MENU VALUES(4,true,'Italian mains','ITALIAN MAINS', '2');

INSERT INTO MENU VALUES (5,true, 'Asian stuff','ORIENTAL',null);
INSERT INTO MENU VALUES(6,true,'Asian appitizer','ORIENTAL STARTERS', '5');
INSERT INTO MENU VALUES(7,true,'Asian mains','ORIENTAL MAINS', '5');


/**
*Adding Items with dummy price separated by delimiter for *currency
*Dummy ratings
*
*/
INSERT INTO ITEM VALUES(1,'bread topped with stuffs',null,null,'BRUSCHETTA','2-EUR',5,null,null,3);

INSERT INTO ITEM VALUES(2,'bread butter',null,null,'BRUSCHETTA PLAIN','1-EUR',3,null,null,3);

INSERT INTO ITEM VALUES(3,'bread sticks',null,null,'STICKS','1-EUR',5,null,null,3);


INSERT INTO ITEM VALUES(4,'pizza with stuffs',null,null,'PIZZA VEG','4-EUR',3,null,null,4);

INSERT INTO ITEM VALUES(5,'vegan pizza',null,null,'PIZZA VEGAN','6-EUR',3,null,null,4);

INSERT INTO ITEM VALUES(6,'pizza pep',null,null,'PIZZA NV','10-EUR',4,null,null,4);


INSERT INTO ITEM VALUES(7,'momo with stuffs',null,null,'MOMO VEG','4-EUR',3,null,null,6);

INSERT INTO ITEM VALUES(8,'vegan momo',null,null,'MOMO VEGAN','6-EUR',3,null,null,6);

INSERT INTO ITEM VALUES(9,'momo pep',null,null,'MOMO NV','10-EUR',4,null,null,6);

INSERT INTO ITEM VALUES(10,'noodles with stuffs',null,null,'NOODLES VEG','4-EUR',3,null,null,7);

INSERT INTO ITEM VALUES(11,'noodles ..',null,null,'NOODLES VEGAN','6-EUR',3,null,null,7);

INSERT INTO ITEM VALUES(12,'noodles pep',null,null,'NOODLES NV','10-EUR',4,null,null,7);


