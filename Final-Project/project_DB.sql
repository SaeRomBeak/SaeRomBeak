/*
--카페테이블=========================================== 
CREATE SEQUENCE c_cafe_seq;
select * from c_cafe

CREATE TABLE c_cafe
(
    c_idx      INT, 
    c_name     VARCHAR2(100) NOT NULL, 
    c_addr     VARCHAR2(300) NOT NULL, 
    c_tel      VARCHAR2(100) NOT NULL, 
    c_price    VARCHAR2(100) NOT NULL, 
    c_park     VARCHAR2(100) NOT NULL, 
    c_time     VARCHAR2(100) NOT NULL, 
    c_hday     VARCHAR2(100) NOT NULL, 
    c_url      VARCHAR2(100), 
    c_map      VARCHAR2(100) NOT NULL, 
    c_photo1    VARCHAR2(100) NOT NULL, 
    c_photo2    VARCHAR2(100),
    c_photo3    VARCHAR2(100),
    c_photo4    VARCHAR2(100),
    c_photo5    VARCHAR2(100),
    c_menu1    VARCHAR2(100), 
    c_menu2    VARCHAR2(100), 
    c_menu3    VARCHAR2(100), 
    c_menu4    VARCHAR2(100), 
    c_tag      VARCHAR2(100) NOT NULL,
    c_hit	   INT default 0,
  	c_regdate  DATE ,
    CONSTRAINT C_CAFE_PK PRIMARY KEY (c_idx)
);

ALTER TABLE c_cafe ADD c_regdate DATE ; 


select * from c_member
--멤버테이블=========================================== 
CREATE SEQUENCE c_member_seq;

CREATE TABLE c_member
(
    m_idx        INT              , 
    m_id         VARCHAR2(100)    NOT NULL, 
    m_pwd        VARCHAR2(100)    NOT NULL, 
    m_name       VARCHAR2(100)    NOT NULL, 
    m_gender     VARCHAR2(100)    NOT NULL, 
    m_tel        VARCHAR2(100)    NOT NULL, 
    m_email      VARCHAR2(100)    NOT NULL, 
    m_photo      VARCHAR2(100)    , 
    m_grade      VARCHAR2(100)    NOT NULL, 
    m_ip         VARCHAR2(100)    NOT NULL, 
    m_regdate    DATE             , 
    CONSTRAINT C_MEMBER_PK PRIMARY KEY (m_idx)
);


--리뷰테이블=========================================== 
CREATE SEQUENCE c_review_seq;

CREATE TABLE c_review
(
    r_idx        INT              , 
    r_cot        VARCHAR2(1000)    NOT NULL, 
    m_idx        INT               NOT NULL, 
    c_idx        INT               NOT NULL, 
    r_grade      INT               NOT NULL, 
    r_pwd        VARCHAR2(100)     NOT NULL, 
    r_ip         VARCHAR2(100)     NOT NULL, 
    r_regdate    DATE             , 
    CONSTRAINT C_REVIEW_PK PRIMARY KEY (r_idx)
);

ALTER TABLE c_review
    ADD CONSTRAINT FK_cafe_c_idx FOREIGN KEY (c_idx)
        REFERENCES c_cafe (c_idx);

ALTER TABLE c_review
    ADD CONSTRAINT FK_member_m_idx FOREIGN KEY (m_idx)
        REFERENCES c_member (m_idx);



-- 리뷰+멤버테이블=========================================== 
-- 필요한 데이터만 조인해서 사용할수있다. 후에 필요하게 되면 속성만 추가한다.
drop view detail_review_view

create or replace view detail_review_view as
select 
	m.m_idx m_idx, m.m_photo m_photo, m.m_id m_id,
	r.r_idx r_idx, r.r_cot r_cot, r.c_idx c_idx, r.r_grade r_grade, 
	r.r_pwd r_pwd, r.r_regdate r_regdate
from 
	c_review r left outer join c_member m on r.m_idx = m.m_idx
order by r.r_idx desc


--
CREATE SEQUENCE seq_cafe_image_idx;

create table cafe_image
(
    ci_idx int,
    c_idx int,
    ci_filename varchar2(100)
)

alter table cafe_image
	add constraint pk_cafe_image_ci_idx primary key(ci_idx);
	
alter table cafe_image
	add constraint fk_cafe_image_c_idx foreign key(c_idx) references cafe(c_idx);
	
	
	
	
		
	





*/