CREATE TABLE `langs` (
  `id` char(2) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ban_reason` (
  `id` tinyint(4) NOT NULL COMMENT 'id Блокировки',
  `lang` char(2) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT 'Имя Блокировки',
  `description` text NOT NULL COMMENT 'Описание Блокировки',
  PRIMARY KEY (`id`,`lang`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `langs_idx` (`lang`),
  CONSTRAINT `langs` FOREIGN KEY (`lang`) REFERENCES `langs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица для описания причин блок';

CREATE TABLE `account` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT COMMENT 'ID Пользователя.',
  `login` varchar(20) NOT NULL COMMENT 'Имя Пользователя',
  `password` varchar(20) NOT NULL COMMENT 'Пароль Пользователя',
  `email` varchar(40) NOT NULL COMMENT 'Почта пользователя',
  `status` tinyint(4) DEFAULT '0' COMMENT 'Роль: Администратор/Пользователь(Новичек,Опытный,Элитный).\nРейтинг пользователям выставляется по логике описанной в коментарии к схеме.',
  `nickname` varchar(50) NOT NULL COMMENT 'Имя отображаемое другим пользователям',
  `data_registered` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'дата регистрации',
  `ban` tinyint(4) DEFAULT NULL COMMENT 'Причина бана.\nЕсли null - не забанен.',
  `ban_date` datetime DEFAULT NULL COMMENT 'Дата и время до которого забанен.\nЕсли null - то не забанен.',
  `image` varchar(40) DEFAULT 'default.jpg',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `e-mail_UNIQUE` (`email`),
  KEY `ban_idx` (`ban`),
  CONSTRAINT `account_ban_reason_id_fk` FOREIGN KEY (`ban`) REFERENCES `ban_reason` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Таблица содержащ';

CREATE TABLE `film` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID Фильма',
  `avg_raiting` decimal(2,1) DEFAULT NULL COMMENT 'Средняя оценка фильма которая вычисляется на основе оценок пользователей.\nВычисление поля производится с помощью тригера.',
  `image_widescreen` varchar(40) NOT NULL,
  `date_primer` date NOT NULL,
  `image_poster` varchar(40) DEFAULT NULL,
  `duration` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Таблица содержащая в себе данные';

CREATE TABLE `film_info` (
  `lang` char(2) NOT NULL,
  `film_id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL COMMENT 'Название фильма',
  `ganre` varchar(70) NOT NULL COMMENT 'Жанр Фильма. Используется 50 символов так как бывают составные жанры.\n',
  `director` varchar(40) NOT NULL COMMENT 'Режисер фильма',
  `description` text,
  PRIMARY KEY (`film_id`,`lang`),
  KEY `lang_idx` (`lang`),
  KEY `fk_Film_info_Film1_idx` (`film_id`),
  CONSTRAINT `fk_Film_info_Film1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lang` FOREIGN KEY (`lang`) REFERENCES `langs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mark` (
  `id_user` mediumint(9) NOT NULL COMMENT 'Пользователь поставивший оценку',
  `id_film` int(11) NOT NULL COMMENT 'Оцениваемый фильм',
  `user_mark` tinyint(4) NOT NULL COMMENT 'Оценка Пользователя',
  `user_comment` text COMMENT 'Комментарий Пользователя',
  `date_post` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`,`id_film`),
  KEY `film_idx` (`id_film`),
  CONSTRAINT `film` FOREIGN KEY (`id_film`) REFERENCES `film` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user` FOREIGN KEY (`id_user`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица оценки п�';

delimiter |
CREATE TRIGGER `tr_avg_raiting` AFTER INSERT ON `mark` 
FOR EACH ROW BEGIN
    UPDATE `film` SET `film`.`avg_raiting` = 
    (Select avg(`user_mark`) from `mark` where NEW.`id_film` = `mark`.`id_film`) 
    WHERE NEW.`id_film` = `film`.`id`;
END
|
delimiter ;

-- DROP TRIGGER IF EXISTS `tr_avg_rating`;




