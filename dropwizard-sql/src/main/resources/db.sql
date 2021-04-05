create table if not exists students (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(100) NOT NULL,
`phone` varchar(100) NOT NULL,
PRIMARY KEY (id),
CONSTRAINT unique_keys UNIQUE (name, phone)
)  ENGINE = InnoDB DEFAULT CHARSET = utf8;