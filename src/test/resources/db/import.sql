/* location table contains signs and streets with cross streets */
CREATE TABLE `location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `borough` varchar(1) NOT NULL DEFAULT '',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `main_street` varchar(255) NOT NULL DEFAULT '',
  `from_street` varchar(255) NOT NULL DEFAULT '',
  `to_street` varchar(255) NOT NULL DEFAULT '',
  `orientation` varchar(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sign_unique` (`borough`,`sign_number`),
  KEY `main_street_idx` (`main_street`),
  KEY `sign_number_idx` (`sign_number`),
  KEY `from_street_idx` (`from_street`),
  KEY `to_street_idx` (`to_street`)
) ENGINE=InnoDB AUTO_INCREMENT=94632 DEFAULT CHARSET=latin1;

/* signs table contains signs details */
CREATE TABLE `signs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `borough` varchar(1) NOT NULL DEFAULT '',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `sequence` int(11) NOT NULL,
  `distance` int(11) NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `arrow` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sign_unique` (`borough`,`sign_number`,`sequence`),
  KEY `sign_number_idx` (`sign_number`)
) ENGINE=InnoDB AUTO_INCREMENT=754333 DEFAULT CHARSET=utf8;