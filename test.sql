/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/7 18:47:22                            */
/*==============================================================*/


DROP TABLE IF EXISTS SCDD_GOODS;

DROP TABLE IF EXISTS SCDD_GOODS_CLASS;

DROP TABLE IF EXISTS SCDD_MEMBER;

DROP TABLE IF EXISTS SCDD_ORDER;

DROP TABLE IF EXISTS SCDD_ORDER_DETAIL;

DROP TABLE IF EXISTS SCDD_SUPPLIER;

DROP TABLE IF EXISTS SCDD_USER;

/*==============================================================*/
/* Table: SCDD_GOODS                                            */
/*==============================================================*/
CREATE TABLE SCDD_GOODS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物理主键',
   CODE                 VARCHAR(20) COMMENT '商品编码',
   NAME                 VARCHAR(100) COMMENT '商品名称',
   SHORT_NAME           VARCHAR(100) COMMENT '简称',
   PRICE                DECIMAL(12,2) NOT NULL COMMENT '商品售价',
   PURCHASE_PRICE       DECIMAL(12,2) NOT NULL COMMENT '商品进价，成本价',
   CLASS_ID             INT COMMENT '商品分类ID',
   SUPPLIER_ID          INT COMMENT '供货商ID',
   AGENT_PRICE          DECIMAL(12,2) NOT NULL COMMENT '代理价',
   ACTIVITY_PRICE       DECIMAL(12,2) COMMENT '活动价',
   STOCK_NUM            INT COMMENT '库存数量',
   DELETE_FLAG          CHAR COMMENT '删除标记 1:删除 0:未删除',
   CREATE_DATE          DATETIME COMMENT '创建时间',
   CREATOR_ID           INT COMMENT '创建者',
   MODIFY_DATE          DATETIME COMMENT '修改时间',
   MODIFIER_ID          INT COMMENT '修改者',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_GOODS COMMENT '商品';

/*==============================================================*/
/* Table: SCDD_GOODS_CLASS                                      */
/*==============================================================*/
CREATE TABLE SCDD_GOODS_CLASS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物理主键',
   NAME                 VARCHAR(30) COMMENT '名称',
   PARENT_ID            INT NOT NULL COMMENT '父类别名称',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_GOODS_CLASS COMMENT '商品分类';

/*==============================================================*/
/* Table: SCDD_MEMBER                                           */
/*==============================================================*/
CREATE TABLE SCDD_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物流主键',
   NAME                 VARCHAR(20) NOT NULL COMMENT '姓名',
   PHONE                VARCHAR(20) NOT NULL COMMENT '手机号码',
   GENDER               CHAR NOT NULL COMMENT '性别',
   LEVEL                CHAR NOT NULL COMMENT '会员等级',
   BIRTHDAY             DATE COMMENT '生日',
   POINTS               INT COMMENT '积分',
   QQ                   VARCHAR(20) COMMENT 'QQ号码',
   WX                   VARCHAR(100) COMMENT '微信账号',
   ADDRESS              VARCHAR(100) COMMENT '地址',
   DELETE_FLAG          CHAR COMMENT '删除标记 1:删除 0:未删除',
   CREATE_DATE          DATETIME COMMENT '创建时间',
   CREATOR_ID           INT COMMENT '创建者',
   MODIFY_DATE          DATETIME COMMENT '修改时间',
   MODIFIER_ID          INT COMMENT '修改者',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_MEMBER COMMENT '会员';

/*==============================================================*/
/* Table: SCDD_ORDER                                            */
/*==============================================================*/
CREATE TABLE SCDD_ORDER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物理主键',
   MEMBER_ID            INT NOT NULL COMMENT '会员ID',
   USER_ID              INT NOT NULL COMMENT '订单录入者ID',
   TRANS_DATE           DATE NOT NULL COMMENT '交易日期',
   MANUAL_FLAG          CHAR COMMENT '需要后续手工修改的订单 1:是 0:否',
   AMOUNT               NUMERIC(12,2) COMMENT '系统计算总金额',
   ACTUAL_AMOUNT        NUMERIC(12,2) COMMENT '实际收款总金额',
   PURCHASE_AMOUNT      NUMERIC(12,2) COMMENT '商品进价总金额',
   DELIVERY_ADDR        VARCHAR(1000) COMMENT '快递地址',
   EXPRESS_COMPANY      VARCHAR(10) COMMENT '快递公司',
   EXPRESS_NUM          VARCHAR(50) COMMENT '快递单号',
   DELETE_FLAG          CHAR COMMENT '删除标记 1:删除 0:未删除',
   CREATE_DATE          DATETIME COMMENT '创建时间',
   CREATOR_ID           INT COMMENT '创建者',
   MODIFY_DATE          DATETIME COMMENT '修改时间',
   MODIFIER_ID          INT COMMENT '修改者',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_ORDER COMMENT '订单';

/*==============================================================*/
/* Table: SCDD_ORDER_DETAIL                                     */
/*==============================================================*/
CREATE TABLE SCDD_ORDER_DETAIL
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物理主键',
   GOODS_ID             INT NOT NULL COMMENT '商品ID',
   ORDER_ID             INT NOT NULL COMMENT '订单ID',
   DISCOUNT             NUMERIC(3,2) DEFAULT 1 COMMENT '折扣',
   UNIT_PRICE           NUMERIC(12,2) NOT NULL COMMENT '单价',
   PURCHASE_PRICE       NUMERIC(12,2) NOT NULL COMMENT '采购价',
   QUANTITY             INT NOT NULL COMMENT '数量',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_ORDER_DETAIL COMMENT '订单明细';

/*==============================================================*/
/* Table: SCDD_SUPPLIER                                         */
/*==============================================================*/
CREATE TABLE SCDD_SUPPLIER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物理主键',
   NAME                 VARCHAR(50) NOT NULL COMMENT '供应商名称',
   PHONE                VARCHAR(20) NOT NULL COMMENT '供应商电话',
   CONTACT              VARCHAR(50) COMMENT '联系人',
   QQ                   VARCHAR(20) COMMENT 'QQ号码',
   WX                   VARCHAR(100) COMMENT '微信账号',
   ADDRESS              VARCHAR(100) COMMENT '地址',
   REMARK               VARCHAR(1000) COMMENT '备注',
   DELETE_FLAG          CHAR COMMENT '删除标记 1:删除 0:未删除',
   CREATE_DATE          DATETIME COMMENT '创建时间',
   CREATOR_ID           INT COMMENT '创建者',
   MODIFY_DATE          DATETIME COMMENT '修改时间',
   MODIFIER_ID          INT COMMENT '修改者',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_SUPPLIER COMMENT '供货商';

/*==============================================================*/
/* Table: SCDD_USER                                             */
/*==============================================================*/
CREATE TABLE SCDD_USER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '物理主键',
   NAME                 VARCHAR(20) NOT NULL COMMENT '名字',
   PHONE                VARCHAR(20) NOT NULL COMMENT '手机号码',
   CODE                 VARCHAR(20) NOT NULL COMMENT '用户代码',
   AGENT_FLAG           CHAR DEFAULT '1' COMMENT '代理标记 1:代理 0:非代理',
   GENDER               CHAR COMMENT '性别 1:男 2:女',
   BIRTHDAY             DATE COMMENT '生日',
   QQ                   VARCHAR(20) COMMENT 'QQ号码',
   WX                   VARCHAR(100) COMMENT '微信账号',
   ADDRESS              VARCHAR(100) COMMENT '地址',
   DELETE_FLAG          CHAR COMMENT '删除标记 1:删除 0:未删除',
   CREATE_DATE          DATETIME COMMENT '创建时间',
   CREATOR_ID           INT COMMENT '创建者',
   MODIFY_DATE          DATETIME COMMENT '修改时间',
   MODIFIER_ID          INT COMMENT '修改者',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_USER COMMENT '店员';

