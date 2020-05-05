-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema agency_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema agency_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agency_db` DEFAULT CHARACTER SET utf8 ;
USE `agency_db` ;

-- -----------------------------------------------------
-- Table `agency_db`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(225) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 142
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` ENUM('ROLE_ADMIN', 'ROLE_USER', 'ROLE_COMPANY') NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_uindex` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`account_to_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`account_to_role` (
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `role_id` BIGINT(20) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`account_id`, `role_id`),
  INDEX `fk_account_to_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_account_to_role_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_to_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `agency_db`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_to_role_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`company` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`food` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  `image_url` VARCHAR(225) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `cuisine` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `food_company_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_food_company1_idx` (`food_company_id` ASC) VISIBLE,
  CONSTRAINT `fk_food_company1`
    FOREIGN KEY (`food_company_id`)
    REFERENCES `agency_db`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`booking_food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`booking_food` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `company_id` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `number_person` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `food_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_booking_food_food1_idx` (`food_id` ASC) VISIBLE,
  INDEX `fk_booking_food_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_food_food1`
    FOREIGN KEY (`food_id`)
    REFERENCES `agency_db`.`food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_food_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`location` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `capacity` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  `image_url` VARCHAR(300) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `location_company_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_company1_idx` (`location_company_id` ASC) VISIBLE,
  CONSTRAINT `fk_location_company1`
    FOREIGN KEY (`location_company_id`)
    REFERENCES `agency_db`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1011
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`booking_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`booking_location` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number_person` VARCHAR(45) NULL DEFAULT NULL,
  `company_id` INT(11) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `location_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_booking_location_location1_idx` (`location_id` ASC) VISIBLE,
  INDEX `fk_booking_location_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `agency_db`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_location_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`program` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `duration` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `image_url` VARCHAR(350) NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `progam_company_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_program_company1_idx` (`progam_company_id` ASC) VISIBLE,
  CONSTRAINT `fk_program_company1`
    FOREIGN KEY (`progam_company_id`)
    REFERENCES `agency_db`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2005
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`booking_program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`booking_program` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `company_id` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `number_person` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `program_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_booking_program_program1_idx` (`program_id` ASC) VISIBLE,
  INDEX `fk_booking_program_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_booking_program_program1`
    FOREIGN KEY (`program_id`)
    REFERENCES `agency_db`.`program` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_program_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`comment_food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`comment_food` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(225) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  `food_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_food_food1_idx` (`food_id` ASC) VISIBLE,
  INDEX `fk_comment_food_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_food_food1`
    FOREIGN KEY (`food_id`)
    REFERENCES `agency_db`.`food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_food_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`comment_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`comment_location` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(500) NULL DEFAULT NULL,
  `rating` INT(11) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  `location_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_location_location1_idx` (`location_id` ASC) VISIBLE,
  INDEX `fk_comment_location_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `agency_db`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_location_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`comment_program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`comment_program` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(500) NULL DEFAULT NULL,
  `rating` INT(11) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  `program_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_program_program1_idx` (`program_id` ASC) VISIBLE,
  INDEX `fk_comment_program_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_program_program1`
    FOREIGN KEY (`program_id`)
    REFERENCES `agency_db`.`program` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_program_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
