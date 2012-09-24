/* For all import data do the following steps:
1. Replace multiple spaces with one
2. Trim empty spaces at the end of lines
3. Replace empty columns with NULL
*/

/* location table contains signs and streets with cross streets */
CREATE TABLE `location` (
  `borough` enum('B','K','Q','M','S') NOT NULL DEFAULT 'B',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `main_street` varchar(255) NOT NULL DEFAULT '',
  `from_street` varchar(255) NOT NULL DEFAULT '',
  `to_street` varchar(255) NOT NULL DEFAULT '',
  `orientation` varchar(1) NOT NULL DEFAULT '',
  `from_lat` double DEFAULT NULL,
  `from_lng` double DEFAULT NULL,
  `to_lat` double DEFAULT NULL,
  `to_lng` double DEFAULT NULL,
  `center_lat` double DEFAULT NULL,
  `center_lng` double DEFAULT NULL,
  `validated` tinyint(1) NOT NULL,
  PRIMARY KEY (`borough`,`sign_number`),
  KEY `main_street_idx` (`main_street`),
  KEY `sign_number_idx` (`sign_number`),
  KEY `from_street_idx` (`from_street`),
  KEY `to_street_idx` (`to_street`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* signs table contains signs details */
CREATE TABLE `signs` (
  `borough` enum('B','K','Q','M','S') NOT NULL DEFAULT 'B',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `order` int(4) NOT NULL,
  `distance` int(11) NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `direction` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`borough`,`sign_number`,`order`),
  KEY `sign_number_idx` (`sign_number`),
  KEY `description_idx` (`description`),
  KEY `sign_number` (`sign_number`,`borough`),
  CONSTRAINT `signs_ibfk_1` FOREIGN KEY (`sign_number`, `borough`) REFERENCES `location` (`sign_number`, `borough`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
