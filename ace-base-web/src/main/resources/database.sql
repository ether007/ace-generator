DROP TABLE t_base;

CREATE TABLE t_base (
id bigint unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
price decimal(5,2) NOT NULL default 0,
gmt_create datetime NULL,
gmt_modified datetime NULL
);

