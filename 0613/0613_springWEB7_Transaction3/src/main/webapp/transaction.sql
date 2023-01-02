CREATE TABLE "CARD" (
    "CONSUMERID" VARCHAR2(20 BYTE), 
	"AMOUNT" VARCHAR2(20 BYTE)
);

CREATE TABLE "TICKET" (
    "CONSUMERID" VARCHAR2(20 BYTE), 
	"AMOUNT" VARCHAR2(20 BYTE),
    CONSTRAINT "CK_TICKET_AMOUNT" CHECK ( amount < 5) ENABLE
);

delete from card;
delete from ticket;
commit;

select * from card;
select * from ticket;

insert into card (consumerid, amount) values ('aaa', '4');
insert into ticket(consumerid, amount ) VALUES ('aaa', '4');

insert into card(consumerid, amount ) VALUES ('bbb', '5');
insert into ticket(consumerid, amount ) VALUES ('bbb', '5');
