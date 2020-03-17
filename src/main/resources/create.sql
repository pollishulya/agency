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
CREATE TABLE IF NOT EXISTS `agency_db`.`user` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `middlename` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `access` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `iduser_UNIQUE` (`iduser` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`client` (
  `idclient` INT(11) NOT NULL AUTO_INCREMENT,
  `birthday` VARCHAR(45) NULL DEFAULT NULL,
  `points` VARCHAR(45) NULL DEFAULT NULL,
  `discount` VARCHAR(45) NULL DEFAULT NULL,
  `bill` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idclient`),
  CONSTRAINT `user`
    FOREIGN KEY (`idclient`)
    REFERENCES `agency_db`.`user` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`comment` (
  `idcomment` INT(11) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idcomment`),
  CONSTRAINT `client`
    FOREIGN KEY (`idcomment`)
    REFERENCES `agency_db`.`client` (`idclient`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`food` (
  `idfood` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `view` VARCHAR(45) NULL DEFAULT NULL,
  `cuisine` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idfood`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`location` (
  `idlocation` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `capacity` VARCHAR(45) NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idlocation`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`program`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`program` (
  `idprogram` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `duration` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `rating` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idprogram`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `agency_db`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agency_db`.`order` (
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `time` VARCHAR(45) NULL DEFAULT NULL,
  `people` VARCHAR(45) NULL DEFAULT NULL,
  `price` VARCHAR(45) NULL DEFAULT NULL,
  `location_idlocation` INT(11) NOT NULL,
  `program_idprogram` INT(11) NOT NULL,
  `food_idfood` INT(11) NOT NULL,
  `client_idclient` INT(11) NOT NULL,
  PRIMARY KEY (`location_idlocation`, `program_idprogram`, `food_idfood`, `client_idclient`),
  INDEX `fk_order_program1_idx` (`program_idprogram` ASC) VISIBLE,
  INDEX `fk_order_food1_idx` (`food_idfood` ASC) VISIBLE,
  INDEX `fk_order_client1_idx` (`client_idclient` ASC) VISIBLE,
  CONSTRAINT `fk_order_client1`
    FOREIGN KEY (`client_idclient`)
    REFERENCES `agency_db`.`client` (`idclient`),
  CONSTRAINT `fk_order_food1`
    FOREIGN KEY (`food_idfood`)
    REFERENCES `agency_db`.`food` (`idfood`),
  CONSTRAINT `fk_order_location1`
    FOREIGN KEY (`location_idlocation`)
    REFERENCES `agency_db`.`location` (`idlocation`),
  CONSTRAINT `fk_order_program1`
    FOREIGN KEY (`program_idprogram`)
    REFERENCES `agency_db`.`program` (`idprogram`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
