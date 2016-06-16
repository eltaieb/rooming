ALTER TABLE `rooming_db`.`room` DROP COLUMN `advertise_date` , ADD COLUMN `rent_available` TINYINT(1) NOT NULL  AFTER `facility_id` , ADD COLUMN `is_deleted` TINYINT(1) NULL  AFTER `rent_available` ;


ALTER TABLE `rooming_db`.`facility` ADD COLUMN `is_deleted` TINYINT(1) NULL  AFTER `room_advertisor_id` ;

ALTER TABLE `rooming_db`.`facility_images` ADD COLUMN `is_cover` TINYINT(1) NULL  AFTER `image` ;
