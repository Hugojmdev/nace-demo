create database nace_demo;

use nace_demo;

SET GLOBAL local_infile=true;

SHOW GLOBAL VARIABLES LIKE 'local_infile';

create table if not exists nace_details(
`id` bigint not null auto_increment,
`order` int not null,
`level` int null,
`code` varchar(50) null,
`parent` varchar(50) null,
`description` varchar(1000) null,
`includes` varchar(3000) null,
`also_includes` varchar(3000) null,
`excludes` varchar(3000) null,
`rulings` VARCHAR(3000) null,
`reference_isic_rev_4` varchar(50) null,
primary key(`id`));

LOAD DATA LOCAL INFILE  
'D:/SVAM/resources/NACE_REV2_20220820_173741.csv' -- Change the path according to where you have stored your csv file with example data
INTO TABLE nace_details 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(`order`, 
`level` , 
`code`, 
`parent`, 
`description`, 
`includes`, 
`also_includes`, 
`excludes`, 
`rulings`, 
`reference_isic_rev_4`);

ALTER TABLE nace_details CHANGE `order` order_number int;

desc nace_details;
select * from nace_details where order_number = 321456;
-- delete from nace_details where id = 1025 ;
-- drop table nace_details;




