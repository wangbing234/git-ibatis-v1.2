drop table if exists ssmk.f_guest_publish;

drop table if exists ssmk.f_guests_extend;

drop table if exists ssmk.f_meb_basic;

drop table if exists ssmk.f_meb_postion_log;

drop table if exists ssmk.f_meb_wallet;

drop table if exists ssmk.f_meb_wallet_log;

drop table if exists ssmk.f_service_type;

drop table if exists ssmk.f_tenant_extend;

drop table if exists ssmk.f_tenant_publish;

drop table if exists ssmk.f_tenant_scope;

drop table if exists ssmk.f_tenant_user_match;

drop table if exists ssmk.f_user_extend;

drop table if exists ssmk.f_user_publis_scope;

drop table if exists ssmk.f_user_publish;

/*==============================================================*/
/* User: ssmk                                                   */
/*==============================================================*/


/*==============================================================*/
/* Table: f_guest_publish                                       */
/*==============================================================*/
create table ssmk.f_guest_publish
(
   id                   int(11) not null auto_increment,
   username             national varchar(45) not null,
   password             national varchar(100) not null,
   number               int(5),
   status               national varchar(2) not null comment '1发送订单2用户确认过来上班3商户确认用户过来上班4商户确认下班5付款',
   createtime           datetime,
   scope_id             int(5),
   teant_id             int(11),
   teant_publish_id     int(11),
   price_paid           float,
   price_total          float,
   finish_time          datetime,
   pay_time             datetime,
   primary key (id)
);

alter table ssmk.f_guest_publish comment '客人预定信息表';

/*==============================================================*/
/* Table: f_guests_extend                                       */
/*==============================================================*/
create table ssmk.f_guests_extend
(
   id                   int(11) not null auto_increment,
   user_id              int(11) not null ,
   age                  int,
   sex                  char,
   heigh                float,
   primary key (id)
);

alter table ssmk.f_guests_extend comment '客户表扩展信息表';

/*==============================================================*/
/* Table: f_meb_basic                                           */
/*==============================================================*/
create table ssmk.f_meb_basic
(
   id                   int(11) not null auto_increment,
   username             national varchar(45) not null,
   createtime           datetime,
   updatetime           datetime,
   referer              int(11) comment '推荐人',
   status               national varchar(2) not null comment '锁定',
   type                 varchar(2) comment '商户或用户',
   nickname             national varchar(45),
   qq                   varchar(20),
   weixin               varchar(20),
   phone                varchar(11) comment '手机号',
   address              national varchar(100) not null,
   longitude            float,
   latitude             float,
   images               varchar(500),
   profile              varchar(100) comment '头像',
   age                  int,
   gender               char,
   heigh                float,
   remark               varchar(500),
   primary key (id)
);

/*==============================================================*/
/* Table: f_meb_postion_log                                     */
/*==============================================================*/
create table ssmk.f_meb_postion_log
(
   id                   int(11) not null auto_increment,
   user_id              national varchar(100) not null,
   address              national varchar(100) not null,
   longitude            float,
   latitude             float,
    createtime           datetime,
   primary key (id)
);

alter table ssmk.f_meb_postion_log comment '用户的地理位置记录';

/*==============================================================*/
/* Table: f_meb_wallet                                          */
/*==============================================================*/
create table ssmk.f_meb_wallet
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   deposit              float comment '押金',
   free                 float,
   freeze               float,
   credit               float comment '信用分',
   opinion              float comment '评分',
   times                int comment '服务次数',
   fail_times           int,
   sucess_times         int comment '达成一致次数',
   primary key (id)
);

alter table ssmk.f_meb_wallet comment '公用会员财务表';

/*==============================================================*/
/* Table: f_meb_wallet_log                                      */
/*==============================================================*/
create table ssmk.f_meb_wallet_log
(
   id                   int(11) not null auto_increment,
   new_amount           national varchar(100) not null,
   change_amount        national varchar(100) not null,
   user_id              national varchar(100) not null,
   reason               char,
   type                 char,
   createtime           datetime,
   remark               national varchar(1000),
   primary key (id)
);

alter table ssmk.f_meb_wallet_log comment '公用会员财务表变更表';

/*==============================================================*/
/* Table: f_service_type                                        */
/*==============================================================*/
create table ssmk.f_service_type
(
   id                   int(11) not null auto_increment,
    pid                  int(11),
   type                 varchar(11),
   name                 varchar(11),
   price                float,
   primary key (id)
);

alter table ssmk.f_service_type comment '服务类型字典表';

/*==============================================================*/
/* Table: f_tenant_extend                                       */
/*==============================================================*/
create table ssmk.f_tenant_extend
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   remark               varchar(1000),
   address              varchar(200),
   primary key (id)
);

alter table ssmk.f_tenant_extend comment '商户扩展信息表';

/*==============================================================*/
/* Table: f_tenant_publish                                      */
/*==============================================================*/
create table ssmk.f_tenant_publish
(
   id                   int(11) not null auto_increment,
    user_id              int(11),
   status               national varchar(2) not null comment '状态',
   address              national varchar(100) not null,
   number               int(5),
   service_type         char,
   price                int(11),
   deadline             datetime,
   createtime           datetime,
   scope_id             int(5),
   primary key (id)
);

alter table ssmk.f_tenant_publish comment '商户发布信息表';

/*==============================================================*/
/* Table: f_tenant_scope                                        */
/*==============================================================*/
create table ssmk.f_tenant_scope
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   scope_id             int(5),
   primary key (id)
);

alter table ssmk.f_tenant_scope comment '商家经营范围';

/*==============================================================*/
/* Table: f_tenant_user_match                                   */
/*==============================================================*/
create table ssmk.f_tenant_user_match
(
   id                   int(11) not null auto_increment,
   status               national varchar(2) not null comment '1，商家通知接单（发短信）
            2，用户确认过来上班。
            3，商户确认用户过来上班。（发短信）
            4，商户确认下班
            5，付款。',
   price_cal            int(11),
   price_paid           int(11),
   price_total          datetime,
   user_id              int(11),
   teant_id             int(11),
   user_publish_id      int(11),
   teant_publish_id     int(11),
   service_type         char,
   tenant_invite_time   datetime,
   user_confirm_time    datetime,
   tenant_confirm_time  datetime,
   finish_time          datetime,
   pay_time             datetime,
   primary key (id)
);

alter table ssmk.f_tenant_user_match comment '商户和用户匹配表';

/*==============================================================*/
/* Table: f_user_extend                                         */
/*==============================================================*/
create table ssmk.f_user_extend
(
   id                   int(11) not null auto_increment,
   user_id              int(11),
   age                  int,
   sex                  char,
   heigh                float,
   primary key (id)
);

alter table ssmk.f_user_extend comment '公关表扩展表';

/*==============================================================*/
/* Table: f_user_publis_scope                                   */
/*==============================================================*/
create table ssmk.f_user_publis_scope
(
   id                   int(11) not null auto_increment,
   publish_id           int(11),
   scope_id             int(5),
   primary key (id)
);

alter table ssmk.f_user_publis_scope comment '公关发布经营范围';

/*==============================================================*/
/* Table: f_user_publish                                        */
/*==============================================================*/
create table ssmk.f_user_publish
(
   id                   int(11) not null auto_increment,
   user_id              national varchar(100) not null,
   address              national varchar(100) not null,
   longitude            float,
   latitude             float,
   servie_type          int(5),
   servie_price         float,
   status               int,
   createtime           datetime,
   deadline             datetime,
   primary key (id)
);

alter table ssmk.f_user_publish comment '公关发布信息表';

create table ssmk.f_system_conf
(
   id                   int(11) not null auto_increment,
   _key                  varchar(50),
   _value               varchar(1000),
   type                 CHAR,
   remark               varchar(1000),
   primary key (id)
);

alter table ssmk.f_system_conf comment '系统设置';


drop table if exists ssmk.f_invite;

/*==============================================================*/
/* Table: f_invite                                              */
/*==============================================================*/
create table ssmk.f_invite
(
   id                   int(11) not null auto_increment,
   status                int comment '',
   user_id              int(11),
   teant_id             int(11),
   publish_id           int(11),
   remark               varchar(100),
   createtime           datetime,
   primary key (id)
);

alter table ssmk.f_invite comment '邀请表';

drop table if exists ssmk.f_tenant_user_match_log;

/*==============================================================*/
/* Table: f_tenant_user_match_log                               */
/*==============================================================*/
create table ssmk.f_tenant_user_match_log
(
   id                   int(11) not null auto_increment,
   status                int ,
   pay_time             datetime,
   remark               varchar(100),
   match_id             int(11),
   primary key (id)
);

alter table ssmk.f_tenant_user_match_log comment '商户和用户匹配日志表';
