
drop database meeting_organiserdb;
create database meeting_organiserdb;
use meeting_organiserdb;

CREATE TABLE `users` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL UNIQUE,
	`password` varchar(60) NOT NULL,
	`authority` varchar(10) NOT NULL DEFAULT 'ROLE_USER',
	`enabled` tinyint(1) NOT NULL DEFAULT 1,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `meeting_type` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
	`type` varchar(15) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `company_type` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
	`type` varchar(15) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `company` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
	`name` varchar(25) NOT NULL UNIQUE,
	`company_type` int(5) NOT NULL,
	`username` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (company_type)
		REFERENCES company_type(id),
	FOREIGN KEY (username)
		REFERENCES users(username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `contact` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
	`first_name` varchar(15) NOT NULL,
	`last_name` varchar(20) NOT NULL,
	`email` varchar(25) NOT NULL,
	`company_id` int(5) NOT NULL,
	`phone` varchar(11) NOT NULL,
	`username` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (company_id)
		REFERENCES company(id) ON DELETE CASCADE,
	FOREIGN KEY (username)
		REFERENCES users(username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `meeting` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
	`contact_id` int(11) NOT NULL,
	`company_id` int(5) NOT NULL,
	`meeting_type` int(11) NOT NULL,
	`notes` MEDIUMTEXT,
	`location` varchar(20) NOT NULL,
	`meeting_start` datetime NOT NULL, 
	`meeting_end` datetime NOT NULL, 
	`username` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (contact_id)
		REFERENCES contact(id)
		ON DELETE CASCADE,
	FOREIGN KEY (company_id)
		REFERENCES company(id) ON DELETE CASCADE,
	FOREIGN KEY (meeting_type)
		REFERENCES meeting_type(id),
	FOREIGN KEY (username)
		REFERENCES users(username) ON DELETE CASCADE
        
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
