/*

--�Ϸù�ȣ ������ü
create sequence seq_board_idx

--���̺�
create table board
(
   idx  	int,				--�Ϸù�ȣ
   name 	varchar2(100),		--�ۼ��ڸ� 
   m_idx 	int,				--�ۼ��� ȸ����ȣ
   subject 	varchar2(200),		--����
   content 	clob,				--����
   ip 		varchar2(100),		--IP
   regdate 	date,				--�������
   readhit 	int,				--��ȸ��
   use_state char(1),			--�������(y:��� n:�̻��)
   --  �亯�� �Խ��� ����
   ref 		int,				--���α۹�ȣ
   step 	int,				--�׷�ۼ���
   depth 	int 				--����� ����(���(1)-���(2)-���(3))
)

--�⺻Ű
alter table board
  add constraint  pk_board_idx primary key(idx)
  
--����Ű(�ܷ�Ű)
alter table board
  add constraint  fk_board_m_idx  foreign key(m_idx)
                                  references member(m_idx)

select * from member                                                                    
    
--sample data
--���۾���
insert into board values(seq_board_idx.nextVal,
                         'ȫ�浿',
                         2,
                         '���� 1���̴�~~',
                         '���� �̷�����̾�',
                         '192.168.7.14',
                         sysdate,
                         0,
                         'y',
                         seq_board_idx.currVal,
                         0,
                         0);
--��۾���
insert into board values(seq_board_idx.nextVal,
                         'ȫ�浿',
                         2,
                         '�̾��ϳ� 1���ؼ�',
                         '���� �̷�����̾�',
                         '192.168.7.14',
                         sysdate,
                         0,
                         'y',
                         1,
                         1,
                         1);
insert into board values(seq_board_idx.nextVal,
                         'ȫ�浿',
                         2,
                         '�ٽ� �ȱ׷���',
                         '���� �̷�����̾�',
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


--����¡ ó���� ���� SQL
select  *  from
(
	select 
	   rank() over(order by ref desc,step asc) no,
	   b.* 
	from 
	  (select * from board) b
)
where no between 6 and 10  


//��ü�Խù��� ���ϱ�
select nvl(count(*),0) from board






*/






