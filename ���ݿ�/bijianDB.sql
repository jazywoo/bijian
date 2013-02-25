SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `user` (
  `userID` BIGINT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NULL ,
  `nickname` VARCHAR(45) NULL ,
  `password` VARCHAR(45) NULL ,
  `sex` TINYINT NULL ,
  `age` INT NULL ,
  `photo` VARCHAR(100) NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  `hotValue` INT NULL ,
  `attentionNum` INT NULL ,
  `followingNum` INT NULL ,
  `sentenceNum` INT NULL ,
  `visitNum` INT NULL ,
  PRIMARY KEY (`userID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sentence` (
  `sentenceID` BIGINT NOT NULL AUTO_INCREMENT ,
  `authorID` BIGINT NULL ,
  `content` VARCHAR(400) NULL ,
  `fromPlace` VARCHAR(200) NULL ,
  `createTime` DATETIME NULL ,
  `goodNum` INT NULL ,
  `commentNum` INT NULL ,
  `forwardingNum` INT NULL ,
  `hotValue` INT NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`sentenceID`) ,
  INDEX `sentence_author` (`authorID` ASC) ,
  CONSTRAINT `sentence_author`
    FOREIGN KEY (`authorID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `message` (
  `messageID` BIGINT NOT NULL AUTO_INCREMENT ,
  `fromUserID` BIGINT NULL ,
  `toUserID` BIGINT NULL ,
  `content` VARCHAR(1000) NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`messageID`) ,
  INDEX `message_from` (`fromUserID` ASC) ,
  INDEX `message_to` (`toUserID` ASC) ,
  CONSTRAINT `message_from`
    FOREIGN KEY (`fromUserID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `message_to`
    FOREIGN KEY (`toUserID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `attention`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `attention` (
  `attentionID` BIGINT NOT NULL AUTO_INCREMENT ,
  `selfID` BIGINT NULL ,
  `attentionerID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`attentionID`) ,
  INDEX `attention_self` (`selfID` ASC) ,
  INDEX `attention_attentioner` (`attentionerID` ASC) ,
  CONSTRAINT `attention_self`
    FOREIGN KEY (`selfID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `attention_attentioner`
    FOREIGN KEY (`attentionerID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `following`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `following` (
  `followingID` BIGINT NOT NULL AUTO_INCREMENT ,
  `selfID` BIGINT NULL ,
  `followingerID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`followingID`) ,
  INDEX `following_followinger` (`followingerID` ASC) ,
  INDEX `following_self` (`selfID` ASC) ,
  CONSTRAINT `following_followinger`
    FOREIGN KEY (`followingerID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `following_self`
    FOREIGN KEY (`selfID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `userSystemInfo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `userSystemInfo` (
  `userID` BIGINT NOT NULL AUTO_INCREMENT ,
  `loginState` TINYINT NULL ,
  PRIMARY KEY (`userID`) ,
  INDEX `userSystemInfoId` (`userID` ASC) ,
  CONSTRAINT `userSystemInfoId`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chat` (
  `chatID` BIGINT NOT NULL AUTO_INCREMENT ,
  `fromUserID` BIGINT NULL ,
  `toUserID` BIGINT NULL ,
  `content` VARCHAR(200) NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`chatID`) ,
  INDEX `chat_fromUser` (`fromUserID` ASC) ,
  INDEX `chat_toUser` (`toUserID` ASC) ,
  CONSTRAINT `chat_fromUser`
    FOREIGN KEY (`fromUserID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `chat_toUser`
    FOREIGN KEY (`toUserID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reportSentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reportSentence` (
  `reportSentenceID` BIGINT NOT NULL AUTO_INCREMENT ,
  `setenceID` BIGINT NULL ,
  `reporterID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`reportSentenceID`) ,
  INDEX `reported_sentence` (`setenceID` ASC) ,
  INDEX `reporters` (`reporterID` ASC) ,
  CONSTRAINT `reported_sentence`
    FOREIGN KEY (`setenceID` )
    REFERENCES `sentence` (`sentenceID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reporters`
    FOREIGN KEY (`reporterID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friendgroup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `friendgroup` (
  `friendGroupID` BIGINT NOT NULL AUTO_INCREMENT ,
  `authorID` BIGINT NULL ,
  `groupName` VARCHAR(40) NULL ,
  `createTime` DATETIME NULL ,
  PRIMARY KEY (`friendGroupID`) ,
  INDEX `friendgroup_author` (`authorID` ASC) ,
  CONSTRAINT `friendgroup_author`
    FOREIGN KEY (`authorID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friendtable`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `friendtable` (
  `friendTableID` BIGINT NOT NULL AUTO_INCREMENT ,
  `selfID` BIGINT NULL ,
  `groupID` BIGINT NULL ,
  `friendID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`friendTableID`) ,
  INDEX `friendtable_m1` (`selfID` ASC) ,
  INDEX `friendtable_m2` (`friendID` ASC) ,
  INDEX `friendtable_group` (`groupID` ASC) ,
  CONSTRAINT `friendtable_m1`
    FOREIGN KEY (`selfID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `friendtable_m2`
    FOREIGN KEY (`friendID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `friendtable_group`
    FOREIGN KEY (`groupID` )
    REFERENCES `friendgroup` (`friendGroupID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `comment` (
  `commentID` BIGINT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(800) NULL ,
  `createTime` DATETIME NULL ,
  `fromUserID` BIGINT NULL ,
  `toUserID` BIGINT NULL ,
  `sentenceID` BIGINT NULL ,
  `commentType` TINYINT NULL COMMENT '值只能是1,2,3.分别代表该comment回复的对象是sentence，句子的第一个comment，其他的comment。可以产生层级关系' ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`commentID`) ,
  INDEX `comment_from` (`fromUserID` ASC) ,
  INDEX `comment_to` (`toUserID` ASC) ,
  INDEX `comment_sentence` (`sentenceID` ASC) ,
  CONSTRAINT `comment_from`
    FOREIGN KEY (`fromUserID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_to`
    FOREIGN KEY (`toUserID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_sentence`
    FOREIGN KEY (`sentenceID` )
    REFERENCES `sentence` (`sentenceID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `notice`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `notice` (
  `noticeID` BIGINT NOT NULL AUTO_INCREMENT ,
  `userID` BIGINT NULL ,
  `title` VARCHAR(400) NULL ,
  `content` VARCHAR(2000) NULL ,
  `createTime` DATETIME NULL ,
  `readed` TINYINT NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`noticeID`) ,
  INDEX `noice_user` (`userID` ASC) ,
  CONSTRAINT `noice_user`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `label`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `label` (
  `labelID` BIGINT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(200) NULL ,
  `createTime` DATETIME NULL ,
  `hotValue` INT NULL ,
  `usedNum` INT NULL ,
  `subscribedNum` INT NULL ,
  PRIMARY KEY (`labelID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `userRelatedSentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `userRelatedSentence` (
  `userRelatedSentenceID` BIGINT NOT NULL AUTO_INCREMENT ,
  `userID` BIGINT NULL ,
  `sentenceID` BIGINT NULL ,
  `isSentenceActive` TINYINT NULL ,
  `createTime` DATETIME NULL ,
  PRIMARY KEY (`userRelatedSentenceID`) ,
  INDEX `R_user` (`userID` ASC) ,
  INDEX `R_sentence` (`sentenceID` ASC) ,
  CONSTRAINT `R_user`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `R_sentence`
    FOREIGN KEY (`sentenceID` )
    REFERENCES `sentence` (`sentenceID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `loveSentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `loveSentence` (
  `loveSentenceID` BIGINT NOT NULL AUTO_INCREMENT ,
  `userID` BIGINT NULL ,
  `sentenceID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`loveSentenceID`) ,
  INDEX `love_userID` (`userID` ASC) ,
  INDEX `love_sentenceID` (`sentenceID` ASC) ,
  CONSTRAINT `love_userID`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `love_sentenceID`
    FOREIGN KEY (`sentenceID` )
    REFERENCES `sentence` (`sentenceID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `subscribeLabel`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `subscribeLabel` (
  `subscribeLabelID` BIGINT NOT NULL AUTO_INCREMENT ,
  `userID` BIGINT NULL ,
  `labelID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  `isValid` TINYINT NULL ,
  INDEX `sub_userID` (`userID` ASC) ,
  PRIMARY KEY (`subscribeLabelID`) ,
  INDEX `sub_labelID` (`labelID` ASC) ,
  CONSTRAINT `sub_userID`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sub_labelID`
    FOREIGN KEY (`labelID` )
    REFERENCES `label` (`labelID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labelSentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `labelSentence` (
  `labelSentenceID` BIGINT NOT NULL AUTO_INCREMENT ,
  `sentenceID` BIGINT NULL ,
  `labelID` BIGINT NULL ,
  PRIMARY KEY (`labelSentenceID`) ,
  INDEX `ls_sentence` (`sentenceID` ASC) ,
  INDEX `ls_label` (`labelID` ASC) ,
  CONSTRAINT `ls_sentence`
    FOREIGN KEY (`sentenceID` )
    REFERENCES `sentence` (`sentenceID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ls_label`
    FOREIGN KEY (`labelID` )
    REFERENCES `label` (`labelID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `labelUser`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `labelUser` (
  `labelUserID` INT NOT NULL AUTO_INCREMENT ,
  `userID` BIGINT NULL ,
  `labelID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  PRIMARY KEY (`labelUserID`) ,
  INDEX `lu_user` (`userID` ASC) ,
  INDEX `lu_label` (`labelID` ASC) ,
  CONSTRAINT `lu_user`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lu_label`
    FOREIGN KEY (`labelID` )
    REFERENCES `label` (`labelID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forwarding`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `forwarding` (
  `forwardingID` BIGINT NOT NULL AUTO_INCREMENT ,
  `sentenceID` BIGINT NULL ,
  `userID` BIGINT NULL ,
  `createTime` DATETIME NULL ,
  PRIMARY KEY (`forwardingID`) ,
  INDEX `f_sentenceID` (`sentenceID` ASC) ,
  INDEX `f_userID` (`userID` ASC) ,
  CONSTRAINT `f_sentenceID`
    FOREIGN KEY (`sentenceID` )
    REFERENCES `sentence` (`sentenceID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `f_userID`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
