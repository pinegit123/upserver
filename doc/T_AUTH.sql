-- Create table
create table T_AUTH
(
  hotel_id    VARCHAR2(22) not null,
  auth_code   VARCHAR2(64),
  auth_time   VARCHAR2(10),
  auth_status NUMBER(1)
)
tablespace HOTEL_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_AUTH
  is '旅馆授权认证表';
-- Add comments to the columns 
comment on column T_AUTH.hotel_id
  is '旅馆ID';
comment on column T_AUTH.auth_code
  is '授权认证吗';
comment on column T_AUTH.auth_time
  is '授权时间';
comment on column T_AUTH.auth_status
  is '授权状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_AUTH
  add constraint PK_T_AUTH primary key (HOTEL_ID)
  using index 
  tablespace HOTEL_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
