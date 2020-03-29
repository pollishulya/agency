-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
  `firstname` VARCHAR(45) NOT NULL,
  `middlename` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `access` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `iduser_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`client` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `birthday` VARCHAR(45) NULL DEFAULT NULL,
  `points` VARCHAR(45) NULL DEFAULT NULL,
  `discount` VARCHAR(45) NULL DEFAULT NULL,
  `bill` VARCHAR(45) NULL DEFAULT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_client_user1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_client_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`location` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `capacity` VARCHAR(45) NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`food` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `view` VARCHAR(45) NULL DEFAULT NULL,
  `cuisine` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
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
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  `location_id` INT(11) NOT NULL,
  `food_id` INT(11) NOT NULL,
  `program_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_comment_location1_idx` (`location_id` ASC) VISIBLE,
  INDEX `fk_comment_food1_idx` (`food_id` ASC) VISIBLE,
  INDEX `fk_comment_program1_idx` (`program_id` ASC) VISIBLE,
  INDEX `fk_comment_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `agency_db`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_food1`
    FOREIGN KEY (`food_id`)
    REFERENCES `agency_db`.`food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_program1`
    FOREIGN KEY (`program_id`)
    REFERENCES `agency_db`.`program` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `agency_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  `people` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `client_id` INT(11) NOT NULL,
  `location_idlocation` INT(11) NOT NULL,
  `program_id` INT(11) NOT NULL,
  `food_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_client1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_order_location1_idx` (`location_idlocation` ASC) VISIBLE,
  INDEX `fk_order_program1_idx` (`program_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_food1_idx` (`food_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_client1`
    FOREIGN KEY (`client_id`)
    REFERENCES `agency_db`.`client` (`id`),
  CONSTRAINT `fk_order_location1`
    FOREIGN KEY (`location_idlocation`)
    REFERENCES `agency_db`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_program1`
    FOREIGN KEY (`program_id`)
    REFERENCES `agency_db`.`program` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_food1`
    FOREIGN KEY (`food_id`)
    REFERENCES `agency_db`.`food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
