create table `FUNCTION`
(
  ID bigint not null,
  `NAME` varchar(100) not null,
  `HIERARCHY` varchar(50) not null
);

create table BOX
(
  ID bigint not null auto_increment,
  constraint PK_BOX primary key (ID),
  OPEN_DATE datetime,
  PRINTER_SERIAL_NUMBER varchar(30) not null,
  ECF_NUMBER varchar(10) not null,
  OPERATOR varchar(10) not null,
  constraint FK_BOX_OPERATOR foreign key (OPERATOR) references OPERATOR(`KEY`),
  INITIAL_COUNTER varchar(30),
  FINISH_COUNT varchar(30),
  FINISH_DATE datetime,
  AUTORIZED_BY VARCHAR(10) not null,
  `STATUS` varchar(20) not null
);

create table OPERATOR
(
  `KEY` varchar(10) not null,
  CONSTRAINT PK_OPERATOR primary key (`KEY`),
  `PASSWORD` varchar(50) not null,
  `NAME` varchar(300) not null,
  `STATUS` varchar(20) not null,
  `HIERARCHY` varchar(50) not null
);




create table CATEGORY
(
 ID bigint not null  auto_increment,
 constraint PK_CATEGORY primary key (ID),
 CATEGORY bigint, 
 NAME varchar(200) not null
);

CREATE TABLE `ICMS` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(50) NOT NULL,
  `PERCENTAGE` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PRODUCT` (
  `ID` bigint(20) NOT NULL,
  `STATUS` varchar(20) NOT NULL,
  `DATE_REGISTER` datetime NOT NULL,
  `ICMS` int(11) NOT NULL,
  `SELL_PRICE` decimal(15,2) NOT NULL,
  `NAME` varchar(300) NOT NULL,
  `SHORT_NAME` varchar(300) NOT NULL,
  `CATEGORY` bigint not null,
  PRIMARY KEY (`ID`),
  KEY `FK_PRODUCT_ICMS` (`ICMS`),
  CONSTRAINT `FK_PRODUCT_ICMS` FOREIGN KEY (`ICMS`) REFERENCES `ICMS` (`ID`),
  CONSTRAINT `FK_PRODUCT_CATEGORY` FOREIGN KEY (`CATEGORY`) REFERENCES `CATEGORY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



