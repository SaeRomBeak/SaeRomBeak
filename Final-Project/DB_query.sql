/*
 --관리자 계정 추가하기
 insert into c_member values
 (c_member_SEQ.nextval,'admin','admin','관리자','여성','010-111-1111',
	'admin@gamil.com', 'no_file','관리자','127.0.0.1',sysdate);


 --카페테이블에서 
 0.UPDATE c_cafe SET c_hit= 0;
 
 1. select * from c_cafe order by sysdate asc  -- 시간순서
 
 2.	select * from(
	   select * from c_cafe order by sysdate asc
	 ) where ROWNUM <=10  --10개까지만 추출 
	


   select r.r_cot r_cot,
(select avg(r_grade) from c_review where c_idx = 1) avg
from 
   (select * from c_review where c_idx=1) r
where rownum = 1 


	select r.*,
			round((select avg(r_grade) from c_review where c_idx = 42),1) avg
			from 
			   (select * from c_review where c_idx=42 order by r_grade desc) r
		where rownum = 1 
 
 */