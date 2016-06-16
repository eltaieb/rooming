ALTER TABLE `rooming_db`.`room_seekers` ADD COLUMN `activated` TINYINT NULL  AFTER `profile_image` , ADD COLUMN `activation_url_time` DATETIME NULL  AFTER `activated` ;
