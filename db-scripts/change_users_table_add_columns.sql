ALTER TABLE `rooming_db`.`users` ADD COLUMN `rest_token` VARCHAR(200) NULL  AFTER `active_url_date` ;

ALTER TABLE `rooming_db`.`users` CHANGE COLUMN `rest_token` `reset_token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;
ALTER TABLE `rooming_db`.`users` CHANGE COLUMN `reset_token` `reset_token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  AFTER `role` , CHANGE COLUMN `active_url_date` `reset_url_validation_time` DATETIME NULL DEFAULT NULL  , ADD COLUMN `is_verified` TINYINT NULL  AFTER `reset_url_validation_time` ;
ALTER TABLE `rooming_db`.`users` ADD COLUMN `activtion_token` VARCHAR(200) NULL  AFTER `reset_token` ;
