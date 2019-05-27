-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema movie
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `movie` ;

-- -----------------------------------------------------
-- Table `movie`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`customer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`customer` (
  `ID` VARCHAR(20) NOT NULL,
  `PW` VARCHAR(30) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `cellPhone` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `mileage` INT NULL DEFAULT 0,
  `coupon` INT NULL DEFAULT 0,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`reservation` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`reservation` (
  `reservationID` VARCHAR(30) NOT NULL,
  `ID` VARCHAR(20) NOT NULL,
  `reservation_date` DATE NOT NULL,
  `movie_name` VARCHAR(45) NOT NULL,
  `movie_seat` VARCHAR(255) NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`reservationID`),
  INDEX `ID_idx` (`ID` ASC) VISIBLE,
  CONSTRAINT `ID_from_rev_to_cus`
    FOREIGN KEY (`ID`)
    REFERENCES `movie`.`customer` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`movie` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`movie` (
  `movie_id` VARCHAR(20) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `actor` VARCHAR(45) NOT NULL,
  `movie_name` VARCHAR(45) NOT NULL,
  `synopsis` VARCHAR(45) NOT NULL,
  `poster_route` VARCHAR(255) NOT NULL,
  `opening_date` DATE NOT NULL,
  `closing_date` DATE NOT NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`comment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`comment` (
  `movie_id` VARCHAR(20) NOT NULL,
  `ID` VARCHAR(20) NOT NULL,
  `comment_id` INT NOT NULL,
  `comment` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `ID_idx` (`ID` ASC) VISIBLE,
  INDEX `movie_id_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `ID_from_com_to_cus`
    FOREIGN KEY (`ID`)
    REFERENCES `movie`.`customer` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `movie_id_from_com_to_movie`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie`.`movie` (`movie_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`grade` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`grade` (
  `movie_id` VARCHAR(20) NOT NULL,
  `ID` VARCHAR(20) NOT NULL,
  `grade` DECIMAL(5) NOT NULL,
  PRIMARY KEY (`movie_id`, `ID`),
  INDEX `ID_idx` (`ID` ASC) VISIBLE,
  CONSTRAINT `movie_id_from_grd_to_mov`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie`.`movie` (`movie_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ID_from_grd_to_cus`
    FOREIGN KEY (`ID`)
    REFERENCES `movie`.`customer` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`card` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`card` (
  `card_id` INT NOT NULL,
  `card_corp` VARCHAR(30) NOT NULL,
  `ID` VARCHAR(20) NOT NULL,
  `sum` INT NOT NULL,
  `reservationID` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`card_id`),
  INDEX `ID_idx` (`ID` ASC) VISIBLE,
  INDEX `reservationID_idx` (`reservationID` ASC) VISIBLE,
  CONSTRAINT `ID_from_card_to_cus`
    FOREIGN KEY (`ID`)
    REFERENCES `movie`.`customer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reservationID_from_card_to_rev`
    FOREIGN KEY (`reservationID`)
    REFERENCES `movie`.`reservation` (`reservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`theater`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`theater` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`theater` (
  `theaterID` INT NOT NULL,
  `theater_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`theaterID`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`screen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`screen` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`screen` (
  `screenID` INT NOT NULL,
  `theaterID` INT NOT NULL,
  `movie_id` VARCHAR(45) NOT NULL,
  `seats` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`screenID`),
  INDEX `theaterID_idx` (`theaterID` ASC) VISIBLE,
  INDEX `movie_id_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `theaterID_from_scr_to_thea`
    FOREIGN KEY (`theaterID`)
    REFERENCES `movie`.`theater` (`theaterID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `movie_id_from_scr_to_mov`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`refund`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`refund` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`refund` (
  `reservationID` VARCHAR(30) NOT NULL,
  `ID` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`reservationID`),
  INDEX `ID_from_re_to_cus_idx` (`ID` ASC) VISIBLE,
  CONSTRAINT `ID_from_re_to_cus`
    FOREIGN KEY (`ID`)
    REFERENCES `movie`.`customer` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `reservationID_from_re_to_rev`
    FOREIGN KEY (`reservationID`)
    REFERENCES `movie`.`reservation` (`reservationID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `movie`.`author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`author` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `movie`.`author` (
  `ID` VARCHAR(20) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `ID_from_auth_to_cus`
    FOREIGN KEY (`ID`)
    REFERENCES `movie`.`customer` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
