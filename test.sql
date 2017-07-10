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
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   CODE                 VARCHAR(20) COMMENT '��Ʒ����',
   NAME                 VARCHAR(100) COMMENT '��Ʒ����',
   SHORT_NAME           VARCHAR(100) COMMENT '���',
   PRICE                DECIMAL(12,2) NOT NULL COMMENT '��Ʒ�ۼ�',
   PURCHASE_PRICE       DECIMAL(12,2) NOT NULL COMMENT '��Ʒ���ۣ��ɱ���',
   CLASS_ID             INT COMMENT '��Ʒ����ID',
   SUPPLIER_ID          INT COMMENT '������ID',
   AGENT_PRICE          DECIMAL(12,2) NOT NULL COMMENT '�����',
   ACTIVITY_PRICE       DECIMAL(12,2) COMMENT '���',
   STOCK_NUM            INT COMMENT '�������',
   DELETE_FLAG          CHAR COMMENT 'ɾ����� 1:ɾ�� 0:δɾ��',
   CREATE_DATE          DATETIME COMMENT '����ʱ��',
   CREATOR_ID           INT COMMENT '������',
   MODIFY_DATE          DATETIME COMMENT '�޸�ʱ��',
   MODIFIER_ID          INT COMMENT '�޸���',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_GOODS COMMENT '��Ʒ';

/*==============================================================*/
/* Table: SCDD_GOODS_CLASS                                      */
/*==============================================================*/
CREATE TABLE SCDD_GOODS_CLASS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   NAME                 VARCHAR(30) COMMENT '����',
   PARENT_ID            INT NOT NULL COMMENT '���������',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_GOODS_CLASS COMMENT '��Ʒ����';

/*==============================================================*/
/* Table: SCDD_MEMBER                                           */
/*==============================================================*/
CREATE TABLE SCDD_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   NAME                 VARCHAR(20) NOT NULL COMMENT '����',
   PHONE                VARCHAR(20) NOT NULL COMMENT '�ֻ�����',
   GENDER               CHAR NOT NULL COMMENT '�Ա�',
   LEVEL                CHAR NOT NULL COMMENT '��Ա�ȼ�',
   BIRTHDAY             DATE COMMENT '����',
   POINTS               INT COMMENT '����',
   QQ                   VARCHAR(20) COMMENT 'QQ����',
   WX                   VARCHAR(100) COMMENT '΢���˺�',
   ADDRESS              VARCHAR(100) COMMENT '��ַ',
   DELETE_FLAG          CHAR COMMENT 'ɾ����� 1:ɾ�� 0:δɾ��',
   CREATE_DATE          DATETIME COMMENT '����ʱ��',
   CREATOR_ID           INT COMMENT '������',
   MODIFY_DATE          DATETIME COMMENT '�޸�ʱ��',
   MODIFIER_ID          INT COMMENT '�޸���',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_MEMBER COMMENT '��Ա';

/*==============================================================*/
/* Table: SCDD_ORDER                                            */
/*==============================================================*/
CREATE TABLE SCDD_ORDER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   MEMBER_ID            INT NOT NULL COMMENT '��ԱID',
   USER_ID              INT NOT NULL COMMENT '����¼����ID',
   TRANS_DATE           DATE NOT NULL COMMENT '��������',
   MANUAL_FLAG          CHAR COMMENT '��Ҫ�����ֹ��޸ĵĶ��� 1:�� 0:��',
   AMOUNT               NUMERIC(12,2) COMMENT 'ϵͳ�����ܽ��',
   ACTUAL_AMOUNT        NUMERIC(12,2) COMMENT 'ʵ���տ��ܽ��',
   PURCHASE_AMOUNT      NUMERIC(12,2) COMMENT '��Ʒ�����ܽ��',
   DELIVERY_ADDR        VARCHAR(1000) COMMENT '��ݵ�ַ',
   EXPRESS_COMPANY      VARCHAR(10) COMMENT '��ݹ�˾',
   EXPRESS_NUM          VARCHAR(50) COMMENT '��ݵ���',
   DELETE_FLAG          CHAR COMMENT 'ɾ����� 1:ɾ�� 0:δɾ��',
   CREATE_DATE          DATETIME COMMENT '����ʱ��',
   CREATOR_ID           INT COMMENT '������',
   MODIFY_DATE          DATETIME COMMENT '�޸�ʱ��',
   MODIFIER_ID          INT COMMENT '�޸���',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_ORDER COMMENT '����';

/*==============================================================*/
/* Table: SCDD_ORDER_DETAIL                                     */
/*==============================================================*/
CREATE TABLE SCDD_ORDER_DETAIL
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   GOODS_ID             INT NOT NULL COMMENT '��ƷID',
   ORDER_ID             INT NOT NULL COMMENT '����ID',
   DISCOUNT             NUMERIC(3,2) DEFAULT 1 COMMENT '�ۿ�',
   UNIT_PRICE           NUMERIC(12,2) NOT NULL COMMENT '����',
   PURCHASE_PRICE       NUMERIC(12,2) NOT NULL COMMENT '�ɹ���',
   QUANTITY             INT NOT NULL COMMENT '����',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_ORDER_DETAIL COMMENT '������ϸ';

/*==============================================================*/
/* Table: SCDD_SUPPLIER                                         */
/*==============================================================*/
CREATE TABLE SCDD_SUPPLIER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   NAME                 VARCHAR(50) NOT NULL COMMENT '��Ӧ������',
   PHONE                VARCHAR(20) NOT NULL COMMENT '��Ӧ�̵绰',
   CONTACT              VARCHAR(50) COMMENT '��ϵ��',
   QQ                   VARCHAR(20) COMMENT 'QQ����',
   WX                   VARCHAR(100) COMMENT '΢���˺�',
   ADDRESS              VARCHAR(100) COMMENT '��ַ',
   REMARK               VARCHAR(1000) COMMENT '��ע',
   DELETE_FLAG          CHAR COMMENT 'ɾ����� 1:ɾ�� 0:δɾ��',
   CREATE_DATE          DATETIME COMMENT '����ʱ��',
   CREATOR_ID           INT COMMENT '������',
   MODIFY_DATE          DATETIME COMMENT '�޸�ʱ��',
   MODIFIER_ID          INT COMMENT '�޸���',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_SUPPLIER COMMENT '������';

/*==============================================================*/
/* Table: SCDD_USER                                             */
/*==============================================================*/
CREATE TABLE SCDD_USER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '��������',
   NAME                 VARCHAR(20) NOT NULL COMMENT '����',
   PHONE                VARCHAR(20) NOT NULL COMMENT '�ֻ�����',
   CODE                 VARCHAR(20) NOT NULL COMMENT '�û�����',
   AGENT_FLAG           CHAR DEFAULT '1' COMMENT '������ 1:���� 0:�Ǵ���',
   GENDER               CHAR COMMENT '�Ա� 1:�� 2:Ů',
   BIRTHDAY             DATE COMMENT '����',
   QQ                   VARCHAR(20) COMMENT 'QQ����',
   WX                   VARCHAR(100) COMMENT '΢���˺�',
   ADDRESS              VARCHAR(100) COMMENT '��ַ',
   DELETE_FLAG          CHAR COMMENT 'ɾ����� 1:ɾ�� 0:δɾ��',
   CREATE_DATE          DATETIME COMMENT '����ʱ��',
   CREATOR_ID           INT COMMENT '������',
   MODIFY_DATE          DATETIME COMMENT '�޸�ʱ��',
   MODIFIER_ID          INT COMMENT '�޸���',
   PRIMARY KEY (ID)
);

ALTER TABLE SCDD_USER COMMENT '��Ա';

