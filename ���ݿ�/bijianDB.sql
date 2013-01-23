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
  `province` VARCHAR(45) NULL ,
  `city` VARCHAR(45) NULL ,
  `area` VARCHAR(45) NULL ,
  `constellation` VARCHAR(45) NULL ,
  `photo` VARCHAR(100) NULL ,
  `createTime` DATETIME NULL ,
  `attentionNum` INT NULL ,
  `followingNum` INT NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`userID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `articleobject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `articleobject` (
  `articleObjectID` BIGINT NOT NULL AUTO_INCREMENT ,
  `authorID` BIGINT NULL ,
  `labelsJson` VARCHAR(400) NULL ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`articleObjectID`) ,
  INDEX `articleobject_author` (`authorID` ASC) ,
  CONSTRAINT `articleobject_author`
    FOREIGN KEY (`authorID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sentence` (
  `sentenceID` BIGINT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(400) NULL ,
  `fromPlace` VARCHAR(200) NULL ,
  `createTime` DATETIME NULL ,
  `goodNum` INT NULL ,
  `commentNum` INT NULL ,
  `forwardingNum` INT NULL ,
  PRIMARY KEY (`sentenceID`) ,
  INDEX `sentence_id` (`sentenceID` ASC) ,
  CONSTRAINT `sentence_id`
    FOREIGN KEY (`sentenceID` )
    REFERENCES `articleobject` (`articleObjectID` )
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
-- Table `diary`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `diary` (
  `diaryID` BIGINT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(200) NULL ,
  `content` VARCHAR(2000) NULL ,
  `createTime` DATETIME NULL ,
  `goodNum` INT NULL ,
  `commentNum` INT NULL ,
  `openKind` TINYINT NULL COMMENT '公开类型，私有，公开，好友可见' ,
  `moodValue` INT NULL ,
  PRIMARY KEY (`diaryID`) ,
  INDEX `diary_id` (`diaryID` ASC) ,
  CONSTRAINT `diary_id`
    FOREIGN KEY (`diaryID` )
    REFERENCES `articleobject` (`articleObjectID` )
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
  `objectID` BIGINT NULL ,
  `objectType` TINYINT NULL ,
  `commentType` TINYINT NULL COMMENT '值只能是1,2,3.分别代表该comment回复的对象是sentence，句子的第一个comment，其他的comment。可以产生层级关系' ,
  `isValid` TINYINT NULL ,
  PRIMARY KEY (`commentID`) ,
  INDEX `comment_from` (`fromUserID` ASC) ,
  INDEX `comment_to` (`toUserID` ASC) ,
  INDEX `comment_object` (`objectID` ASC) ,
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
  CONSTRAINT `comment_object`
    FOREIGN KEY (`objectID` )
    REFERENCES `articleobject` (`articleObjectID` )
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
  `authorID` BIGINT NULL ,
  `objectID` BIGINT NULL ,
  `objectType` TINYINT NULL ,
  `createTime` DATETIME NULL ,
  PRIMARY KEY (`labelID`) ,
  INDEX `lable_author` (`authorID` ASC) ,
  INDEX `label_object` (`objectID` ASC) ,
  CONSTRAINT `lable_author`
    FOREIGN KEY (`authorID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `label_object`
    FOREIGN KEY (`objectID` )
    REFERENCES `articleobject` (`articleObjectID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `userRelatedObject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `userRelatedObject` (
  `userRelatedObjectID` BIGINT NOT NULL AUTO_INCREMENT ,
  `userID` BIGINT NULL ,
  `objectID` BIGINT NULL ,
  `objectType` TINYINT NULL ,
  `isObjectActive` TINYINT NULL ,
  `createTime` DATETIME NULL ,
  PRIMARY KEY (`userRelatedObjectID`) ,
  INDEX `R_user` (`userID` ASC) ,
  INDEX `R_object` (`objectID` ASC) ,
  CONSTRAINT `R_user`
    FOREIGN KEY (`userID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `R_object`
    FOREIGN KEY (`objectID` )
    REFERENCES `articleobject` (`articleObjectID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lovesentence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lovesentence` (
  `loveSentenceID` BIGINT NOT NULL ,
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
  `subscribeLabelID` BIGINT NOT NULL ,
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



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
