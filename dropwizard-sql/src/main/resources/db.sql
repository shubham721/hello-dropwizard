create table if not exists students (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(100) NOT NULL,
`phone` varchar(100) NOT NULL,
PRIMARY KEY (id),
CONSTRAINT unique_keys UNIQUE (name, phone)
)  ENGINE = InnoDB DEFAULT CHARSET = utf8;


create table if not exists Employee (
    ID int unsigned NOT NULL AUTO_INCREMENT,
    EMAIL varchar(100) not null,
    FIRST_NAME varchar(100) not null,
    LAST_NAME varchar(100) not null,
    primary key (ID));

create table if not exists
    ACCOUNT (ID int unsigned NOT NULL AUTO_INCREMENT,
    ACC_NUMBER varchar(100) not null,
    employee_ID integer,
    primary key (ID));

create table if not exists EMPLOYEE_ACCOUNT (
    EMPLOYEE_ID integer not null,
    ACCOUNT_ID integer not null,
     primary key (EMPLOYEE_ID, ACCOUNT_ID));

create table if not exists EmployeeNEW(
                                        ID int unsigned NOT NULL AUTO_INCREMENT,
                                        EMAIL varchar(100) not null,
                                        FIRST_NAME varchar(100) not null,
                                        LAST_NAME varchar(100) not null,
                                        primary key (ID));

create table if not exists
    ACCOUNTNEW (ID int unsigned NOT NULL AUTO_INCREMENT,
             ACC_NUMBER varchar(100) not null,
             primary key (ID));

create table if not exists EMPLOYEE_ACCOUNT (
                                                EMPLOYEE_ID integer not null,
                                                ACCOUNT_ID integer not null,
                                                primary key (EMPLOYEE_ID, ACCOUNT_ID));

