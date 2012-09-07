/* location table contains signs and streets with cross streets */
CREATE TABLE `location` (
  `borough` varchar(1) NOT NULL DEFAULT '',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `main_street` varchar(255) NOT NULL DEFAULT '',
  `from_street` varchar(255) NOT NULL DEFAULT '',
  `to_street` varchar(255) NOT NULL DEFAULT '',
  `orientation` varchar(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`borough`,`sign_number`),
  KEY `main_street_idx` (`main_street`),
  KEY `sign_number_idx` (`sign_number`),
  KEY `from_street_idx` (`from_street`),
  KEY `to_street_idx` (`to_street`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* signs table contains signs details */
CREATE TABLE `signs` (
  `borough` varchar(1) NOT NULL DEFAULT '',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `order` int(4) NOT NULL,
  `distance` int(11) NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `direction` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`borough`,`sign_number`,`order`),
  KEY `sign_number_idx` (`sign_number`),
  KEY `description_idx` (`description`),
  CONSTRAINT `signs_ibfk_1` FOREIGN KEY (`borough`, `sign_number`) REFERENCES `location` (`borough`, `sign_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
