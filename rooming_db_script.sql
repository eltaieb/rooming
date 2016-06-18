SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `rooming_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `rooming_db` ;

-- -----------------------------------------------------
-- Table `rooming_db`.`admin`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`admin` (
  `profile_image` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `ip` VARCHAR(64) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`amenities`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`amenities` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`bookmark`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`bookmark` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `facility_id` INT(11) NULL DEFAULT NULL ,
  `seeker_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`lookup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`lookup` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `category_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_lookup__category` (`category_id` ASC) ,
  CONSTRAINT `fk_lookup__category`
    FOREIGN KEY (`category_id` )
    REFERENCES `rooming_db`.`category` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room_advertisers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_advertisers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `birthday` DATE NULL DEFAULT NULL ,
  `country` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `city` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `phone_number` INT(11) NULL DEFAULT NULL ,
  `building_number` INT(11) NULL DEFAULT NULL ,
  `street` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `floor_number` INT(11) NULL DEFAULT NULL ,
  `profile_image` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT 'index.png' ,
  `identification_number` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `identification_document_path` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `identification_type` INT(11) NULL DEFAULT NULL ,
  `is_verified` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_ROOM_ADVERTISER_LOOKUP_IDENTIFICATION_TYPE_idx` (`identification_type` ASC) ,
  CONSTRAINT `FK_ROOM_ADVERTISER_LOOKUP_IDENTIFICATION_TYPE`
    FOREIGN KEY (`identification_type` )
    REFERENCES `rooming_db`.`lookup` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`facility`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`facility` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `lon` DOUBLE NOT NULL ,
  `lan` DOUBLE NOT NULL ,
  `country` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `city` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `street` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `building_number` INT(11) NOT NULL ,
  `postal_code` INT(11) NULL DEFAULT NULL ,
  `description` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `room_advertisor_id` INT(11) NOT NULL ,
  `is_deleted` TINYINT(1) NULL DEFAULT NULL ,
  `advertise_date` DATE NULL DEFAULT NULL ,
  `gender` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_facility__apartment_advertisor` (`room_advertisor_id` ASC) ,
  CONSTRAINT `fk_facility_room_advertiser`
    FOREIGN KEY (`room_advertisor_id` )
    REFERENCES `rooming_db`.`room_advertisers` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`facility_amenities`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`facility_amenities` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `facility_id` INT(11) NULL DEFAULT NULL ,
  `amenities_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_facility_amenities__amenities` (`amenities_id` ASC) ,
  INDEX `idx_facility_amenities__facility` (`facility_id` ASC) ,
  CONSTRAINT `fk_facility_amenities__amenities`
    FOREIGN KEY (`amenities_id` )
    REFERENCES `rooming_db`.`amenities` (`id` ),
  CONSTRAINT `fk_facility_amenities__facility`
    FOREIGN KEY (`facility_id` )
    REFERENCES `rooming_db`.`facility` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 493
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`facility_images`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`facility_images` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `facility_id` INT(11) NOT NULL ,
  `image` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `is_cover` TINYINT(1) NULL DEFAULT '1' ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_facility_images__facility` (`facility_id` ASC) ,
  CONSTRAINT `fk_facility_images__facility`
    FOREIGN KEY (`facility_id` )
    REFERENCES `rooming_db`.`facility` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`facility_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`facility_roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `facility_id` INT(11) NOT NULL ,
  `role_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 167
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `username` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT 'userID' ,
  `password` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `facebook_token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `google_plus_token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `first_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `last_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `gender` VARCHAR(2) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `role` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `reset_token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `activation_token` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `activated` TINYINT(4) NULL DEFAULT NULL ,
  `reset_url_validation_time` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`log`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `users_id` INT(11) NOT NULL ,
  `date` DATETIME NULL DEFAULT NULL ,
  `value` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_users1_idx` (`users_id` ASC) ,
  CONSTRAINT `fk_log_users1`
    FOREIGN KEY (`users_id` )
    REFERENCES `rooming_db`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `area` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `price` DECIMAL(12,2) NOT NULL ,
  `duration` INT(11) NULL DEFAULT NULL ,
  `expandable` TINYINT(1) NULL DEFAULT NULL ,
  `facility_id` INT(11) NOT NULL ,
  `rent_available` TINYINT(1) NOT NULL ,
  `is_deleted` TINYINT(1) NULL DEFAULT NULL ,
  `advertise_date` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_room__facility` (`facility_id` ASC) ,
  CONSTRAINT `fk_room__facility`
    FOREIGN KEY (`facility_id` )
    REFERENCES `rooming_db`.`facility` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room_seekers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_seekers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `country` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `city` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `address` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `phone_number` INT(11) NULL DEFAULT NULL ,
  `mobile_number` INT(11) NULL DEFAULT NULL ,
  `birthdate` DATE NULL DEFAULT NULL ,
  `profile_image` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT 'index.jpg' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`messages`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`messages` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `owner_id` INT(11) NOT NULL ,
  `seeker_id` INT(11) NOT NULL ,
  `facility_id` INT(11) NOT NULL ,
  `content` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_messages_room_advertisers1_idx` (`owner_id` ASC) ,
  INDEX `fk_messages_room1_idx` (`facility_id` ASC) ,
  INDEX `fk_messages_room_seekers1_idx` (`seeker_id` ASC) ,
  CONSTRAINT `fk_messages_room1`
    FOREIGN KEY (`facility_id` )
    REFERENCES `rooming_db`.`room` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_room_advertisers1`
    FOREIGN KEY (`owner_id` )
    REFERENCES `rooming_db`.`room_advertisers` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_room_seekers1`
    FOREIGN KEY (`seeker_id` )
    REFERENCES `rooming_db`.`room_seekers` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room_images`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_images` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `room_id` INT(11) NOT NULL ,
  `image` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_room_images__room` (`room_id` ASC) ,
  CONSTRAINT `fk_room_images__room`
    FOREIGN KEY (`room_id` )
    REFERENCES `rooming_db`.`room` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `room_id` INT(11) NOT NULL ,
  `role_lookup_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_room_roles__lookup` (`role_lookup_id` ASC) ,
  INDEX `idx_room_roles__room` (`room_id` ASC) ,
  CONSTRAINT `fk_room_roles__lookup`
    FOREIGN KEY (`role_lookup_id` )
    REFERENCES `rooming_db`.`lookup` (`id` ),
  CONSTRAINT `fk_room_roles__room`
    FOREIGN KEY (`room_id` )
    REFERENCES `rooming_db`.`room` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`searcher_interests`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`searcher_interests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `lookup_id` INT(11) NULL DEFAULT NULL ,
  `apartment_searcher_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_searcher_interests__apartment_searcher` (`apartment_searcher_id` ASC) ,
  INDEX `idx_searcher_interests__lookup` (`lookup_id` ASC) ,
  CONSTRAINT `fk_searcher_interests__apartment_searcher`
    FOREIGN KEY (`apartment_searcher_id` )
    REFERENCES `rooming_db`.`room_seekers` (`id` ),
  CONSTRAINT `fk_searcher_interests__lookup`
    FOREIGN KEY (`lookup_id` )
    REFERENCES `rooming_db`.`lookup` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`seeker_favourite_facility`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`seeker_favourite_facility` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `seeker_id` INT(11) NULL DEFAULT NULL ,
  `facility_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`userroles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`userroles` (
  `id` INT(11) NOT NULL ,
  `autherity` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`users_rooms`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`users_rooms` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `apartment_searcher_id` INT(11) NOT NULL ,
  `room_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_users_rooms__apartment_searcher` (`apartment_searcher_id` ASC) ,
  INDEX `idx_users_rooms__room` (`room_id` ASC) ,
  CONSTRAINT `fk_users_rooms__apartment_searcher`
    FOREIGN KEY (`apartment_searcher_id` )
    REFERENCES `rooming_db`.`room_seekers` (`id` ),
  CONSTRAINT `fk_users_rooms__room`
    FOREIGN KEY (`room_id` )
    REFERENCES `rooming_db`.`room` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

USE `rooming_db` ;

-- -----------------------------------------------------
-- Placeholder table for view `rooming_db`.`selection_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rooming_db`.`selection_view` (`facility_id` INT, `description` INT, `building_number` INT, `street` INT, `city` INT, `address` INT, `longitude` INT, `latitude` INT, `price` INT, `roomid` INT, `profile_image` INT, `facility_cover_photo` INT, `subscription_flag` INT, `bookmark_flag` INT);

-- -----------------------------------------------------
-- View `rooming_db`.`selection_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rooming_db`.`selection_view`;
USE `rooming_db`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rooming_db`.`selection_view` AS select `f`.`id` AS `facility_id`,`f`.`description` AS `description`,`f`.`building_number` AS `building_number`,`f`.`street` AS `street`,`f`.`city` AS `city`,`f`.`country` AS `address`,`f`.`lon` AS `longitude`,`f`.`lan` AS `latitude`,`r`.`price` AS `price`,`r`.`id` AS `roomid`,`ra`.`profile_image` AS `profile_image`,`fa`.`image` AS `facility_cover_photo`,`b`.`id` AS `subscription_flag`,`sff`.`id` AS `bookmark_flag` from (((((`rooming_db`.`facility` `f` left join `rooming_db`.`facility_images` `fa` on((`f`.`id` = `fa`.`facility_id`))) join `rooming_db`.`room` `r`) join `rooming_db`.`room_advertisers` `ra`) left join `rooming_db`.`bookmark` `b` on((`f`.`id` = `b`.`facility_id`))) left join `rooming_db`.`seeker_favourite_facility` `sff` on((`f`.`id` = `sff`.`facility_id`))) where ((`r`.`facility_id` = `f`.`id`) and (`f`.`room_advertisor_id` = `ra`.`id`) and (`f`.`is_deleted` = 0) and (`r`.`is_deleted` = 0) and (`r`.`rent_available` = 1));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
