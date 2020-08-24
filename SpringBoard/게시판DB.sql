/*

--일련번호 관리객체
create sequence seq_board_idx

--테이블
create table board
(
   idx  	int,				--일련번호
   name 	varchar2(100),		--작성자명 
   m_idx 	int,				--작성자 회원번호
   subject 	varchar2(200),		--제목
   content 	clob,				--내용
   ip 		varchar2(100),		--IP
   regdate 	date,				--등록일자
   readhit 	int,				--조회수
   use_state char(1),			--사용유무(y:사용 n:미사용)
   --  답변형 게시판 정보
   ref 		int,				--메인글번호
   step 	int,				--그룹글순서
   depth 	int 				--답글의 깊이(답글(1)-답글(2)-답글(3))
)

--기본키
alter table board
  add constraint  pk_board_idx primary key(idx)
  
--참조키(외래키)
alter table board
  add constraint  fk_board_m_idx  foreign key(m_idx)
                                  references member(m_idx)

select * from member                                                                    
    
--sample data
--새글쓰기
insert into board values(seq_board_idx.nextVal,
                         '홍길동',
                         2,
                         '내가 1등이다~~',
                         '내가 이런사람이야',
                         '192.168.7.14',
                         sysdate,
                         0,
                         'y',
                         seq_board_idx.currVal,
                         0,
                         0);
--답글쓰기
insert into board values(seq_board_idx.nextVal,
                         '홍길동',
                         2,
                         '미안하네 1등해서',
                         '내가 이런사람이야',
                         '192.168.7.14',
                         sysdate,
                         0,
                         'y',
                         1,
                         1,
                         1);
insert into board values(seq_board_idx.nextVal,
                         '홍길동',
                         2,
                         '다신 안그럴께',
                         '내가 이런사람이야',
                         '192.168.7.14',
                         sysdate,
                         0,
                         'y',
                         1,
                         2,
                         2);
                         
select * from board   
order by ref desc,step asc                                               
                                                                           
commit                                                                                                    
     
                                                                                                                                                      
select * from member                                                                                                                                                                                                                                                                                                                                                                                                                                                        


--페이징 처리를 위하 SQL
select  *  from
(
	select 
	   rank() over(order by ref desc,step asc) no,
	   b.* 
	from 
	  (select * from board) b
)
where no between 6 and 10  


//전체게시물수 구하기
select nvl(count(*),0) from board






*/






