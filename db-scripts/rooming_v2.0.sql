SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `rooming_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `rooming_db` ;

-- -----------------------------------------------------
-- Table `rooming_db`.`amenities`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`amenities` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room_advertisers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_advertisers` (
  `first_name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `last_name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `birthday` DATE NULL DEFAULT NULL ,
  `country` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `phone_number` INT(11) NULL DEFAULT NULL ,
  `city` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `building_number` INT(11) NULL DEFAULT NULL ,
  `street` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `floor_number` INT(11) NULL DEFAULT NULL ,
  `profile_image` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `room_advertiserscol` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `token` VARCHAR(150) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `token_valid` TINYINT(4) NULL DEFAULT NULL ,
  `password` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
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
  `postal_code` INT(11) NOT NULL ,
  `description` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `room_advertisor_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_facility__apartment_advertisor` (`room_advertisor_id` ASC) ,
  CONSTRAINT `fk_facility_room_advertiser`
    FOREIGN KEY (`room_advertisor_id` )
    REFERENCES `rooming_db`.`room_advertisers` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`facility_images`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`facility_images` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `facility_id` INT(11) NOT NULL ,
  `image` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_facility_images__facility` (`facility_id` ASC) ,
  CONSTRAINT `fk_facility_images__facility`
    FOREIGN KEY (`facility_id` )
    REFERENCES `rooming_db`.`facility` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`facility_seeker`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`facility_seeker` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `facility_id` INT(11) NULL DEFAULT NULL ,
  `seeker_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `area` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `price` DECIMAL(12,2) NOT NULL ,
  `advertise_date` DATE NOT NULL ,
  `duration` INT(11) NOT NULL ,
  `expandable` TINYINT(1) NOT NULL ,
  `facility_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_room__facility` (`facility_id` ASC) ,
  CONSTRAINT `fk_room__facility`
    FOREIGN KEY (`facility_id` )
    REFERENCES `rooming_db`.`facility` (`id` ))
ENGINE = InnoDB
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `rooming_db`.`room_interests`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_interests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `room_id` INT(11) NOT NULL ,
  `interest_lookup_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_room_intersts__lookup` (`interest_lookup_id` ASC) ,
  INDEX `idx_room_intersts__room` (`room_id` ASC) ,
  CONSTRAINT `fk_room_intersts__lookup`
    FOREIGN KEY (`interest_lookup_id` )
    REFERENCES `rooming_db`.`lookup` (`id` ),
  CONSTRAINT `fk_room_intersts__room`
    FOREIGN KEY (`room_id` )
    REFERENCES `rooming_db`.`room` (`id` ))
ENGINE = InnoDB
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
-- Table `rooming_db`.`room_seekers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rooming_db`.`room_seekers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `password` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `facebook_token` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `country` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `city` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `address` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `phone_number` INT(11) NULL DEFAULT NULL ,
  `mobile_number` INT(11) NULL DEFAULT NULL ,
  `birthdate` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
