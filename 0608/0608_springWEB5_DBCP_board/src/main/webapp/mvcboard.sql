CREATE TABLE MVCBOARD (
	IDX INTEGER,
	NAME VARCHAR2(20) NOT NULL,
	SUBJECT VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(3000) NOT NULL,
	GUP INTEGER,
	LEV INTEGER,
	SEQ INTEGER,
	HIT INTEGER DEFAULT 0,
	WRITEDATE TIMESTAMP DEFAULT sysdate,
	CONSTRAINT MVCBOARD_PK PRIMARY KEY (IDX)
);

SELECT * FROM MVCBOARD ORDER BY gup DESC, seq ASC;
SELECT count(*) FROM MVCBOARD;

DELETE FROM MVCBOARD;
DROP SEQUENCE mvcboard_idx_seq;
CREATE SEQUENCE mvcboard_idx_seq;

insert into mvcboard (idx, name, subject, content, gup, lev, seq) 
values (mvcboard_idx_seq.nextval, '홍길동', '1등', '1등 입니다.', mvcboard_idx_seq.currval, 0, 0);
insert into mvcboard (idx, name, subject, content, gup, lev, seq) 
values (mvcboard_idx_seq.nextval, '임꺽정', '2등', '2등 입니다.', mvcboard_idx_seq.currval, 0, 0);
insert into mvcboard (idx, name, subject, content, gup, lev, seq) 
values (mvcboard_idx_seq.nextval, '장길산', '3등', '3등 입니다.', mvcboard_idx_seq.currval, 0, 0);
insert into mvcboard (idx, name, subject, content, gup, lev, seq) 
values (mvcboard_idx_seq.nextval, '일지매', '4등', '4등 입니다.', mvcboard_idx_seq.currval, 0, 0);
