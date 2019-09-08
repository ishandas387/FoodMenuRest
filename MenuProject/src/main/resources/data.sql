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
INSERT INTO ITEM VALUES(1,'Monday to Firday' ,'bread topped with stuffs','2-March','21:00HRS','BRUSCHETTA','2-EUR',5,'2-April','09:00HRS',3);

INSERT INTO ITEM VALUES(2,'Monday to Firday' ,'bread butter','2-March','21:00HRS','BRUSCHETTA PLAIN','1-EUR',3,'2-April','09:00HRS',3);

INSERT INTO ITEM VALUES(3,'Monday to Firday' ,'bread sticks','2-March','21:00HRS','STICKS','1-EUR',5,'2-April','09:00HRS',3);


INSERT INTO ITEM VALUES(4,'Monday to Firday' ,'pizza with stuffs','2-March','21:00HRS','PIZZA VEG','4-EUR',3,'2-April','09:00HRS',4);

INSERT INTO ITEM VALUES(5,'Monday to Firday' ,'vegan pizza','2-March','21:00HRS','PIZZA VEGAN','6-EUR',3,'2-April','09:00HRS',4);

INSERT INTO ITEM VALUES(6,'Monday to Firday' ,'pizza pep','2-March','21:00HRS','PIZZA NV','10-EUR',4,'2-April','09:00HRS',4);


INSERT INTO ITEM VALUES(7,'Monday to Firday' ,'momo with stuffs','2-March','21:00HRS','MOMO VEG','4-EUR',3,'2-April','09:00HRS',6);

INSERT INTO ITEM VALUES(8,'Monday to Firday' ,'vegan momo','2-March','21:00HRS','MOMO VEGAN','6-EUR',3,'2-April','09:00HRS',6);

INSERT INTO ITEM VALUES(9,'Monday to Firday' ,'momo pep','2-March','21:00HRS','MOMO NV','10-EUR',4,'2-April','09:00HRS',6);

INSERT INTO ITEM VALUES(10,'Monday to Firday' ,'noodles with stuffs','2-March','21:00HRS','NOODLES VEG','4-EUR',3,'2-April','09:00HRS',7);

INSERT INTO ITEM VALUES(11,'Monday to Firday' ,'noodles ..','2-March','21:00HRS','NOODLES VEGAN','6-EUR',3,'2-April','09:00HRS',7);

INSERT INTO ITEM VALUES(12,'Monday to Firday' ,'noodles pep','2-March','21:00HRS','NOODLES NV','10-EUR',4,'2-April','09:00HRS',7);