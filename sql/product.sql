drop table product;

 CREATE TABLE product (
  pid       NUMBER(10),
  pname 	VARCHAR2(30),
  count 	NUMBER(10),
  price     NUMBER(10)
  );

  alter table product add constraint product_pid_pk primary key(pid);

  drop sequence product_pid_seq;
  create sequence product_pid_seq;


  insert into product(pid,pname,count,price)
       values(product_pid_seq.nextval, '컴퓨터', 5, 1000000);

  insert into product(pid,pname,count,price)
       values(product_pid_seq.nextval, '모니터', 5, 500000);

  insert into product(pid,pname,count,price)
       values(product_pid_seq.nextval, '프린터', 1, 300000);

  --조회--
  select pid, pname, count, price
  from product
  where pid = 2;