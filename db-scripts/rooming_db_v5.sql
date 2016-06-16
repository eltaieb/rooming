ALTER TABLE `rooming_db`.`users` ADD COLUMN `accountNonExpired` BINARY NULL  AFTER `token` , ADD COLUMN `accountNonLocked` BINARY NULL  AFTER `accountNonExpired` , ADD COLUMN `credentialsNonExpired` BINARY NULL  AFTER `accountNonLocked` ,
 ADD COLUMN `enabled` BINARY NULL  AFTER `credentialsNonExpired` ;

ALTER TABLE `rooming_db`.`users` CHANGE COLUMN `authorities` `authorities` INT(11) NULL DEFAULT NULL  ;


CREATE  TABLE `rooming_db`.`UserRoles` (
  `id` INT NOT NULL ,
  `authority` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );
ALTER TABLE `rooming_db`.`users` ADD COLUMN `authorities` INT(11) NULL  AFTER `enabled` ;

ALTER TABLE `rooming_db`.`users` 
  ADD CONSTRAINT `fk_users_userroles`
  FOREIGN KEY (`authorities` )
 
 REFERENCES `rooming_db`.`userroles` (`id` )
  ON DELETE RESTRICT
  ON UPDATE RESTRICT
, ADD INDEX `fk_users_userroles_idx` (`authorities` ASC) ;
