/* location table contains signs and streets with cross streets */
CREATE TABLE `location` (
  `borough` char(1) NOT NULL DEFAULT 'B',
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
  `length` double DEFAULT NULL,
  `validated` tinyint(1) NOT NULL,
  `offset_lat` double NOT NULL DEFAULT '0',
  `offset_lng` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`borough`,`sign_number`),
  KEY `main_street_idx` (`main_street`),
  KEY `location_sign_number_idx` (`sign_number`),
  KEY `from_street_idx` (`from_street`),
  KEY `to_street_idx` (`to_street`)
);

/* signs table contains signs details */
CREATE TABLE `signs` (
  `borough` char(1) NOT NULL DEFAULT 'B',
  `sign_number` varchar(8) NOT NULL DEFAULT '',
  `sign_order` int(4) NOT NULL,
  `distance` int(11) NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `direction` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`borough`,`sign_number`,`sign_order`),
  KEY `sings_sign_number_idx` (`sign_number`),
  KEY `description_idx` (`description`),
  KEY `sign_number` (`sign_number`,`borough`),
  CONSTRAINT `signs_ibfk_1` FOREIGN KEY (`sign_number`, `borough`) REFERENCES `location` (`sign_number`, `borough`)
);

INSERT INTO `location` (`borough`, `sign_number`, `main_street`, `from_street`, `to_street`, `orientation`, `from_lat`, `from_lng`, `to_lat`, `to_lng`, `center_lat`, `center_lng`, `length`, `validated`, `offset_lat`, `offset_lng`)
VALUES
	('B','P-004958','RANDALL AVENUE','FAILE STREET','COSTER STREET','N',40.8122541,-73.8858952,40.8121307,-73.8867966,40.8121924,-73.8863459,0.0009098074081949518,1,0,0),
	('K','P-234103','BRIGHTON BEACH AVENUE','BRIGHTON 4 STREET','BRIGHTON 3 STREET','N',40.5772774,-73.9633395,40.5770855,-73.9642472,40.57718145,-73.96379335,0.0009277633857817685,1,0,0),
	('K','P-234112','BRIGHTON BEACH AVENUE','BRIGHTON 1 STREET','OCEAN PARKWAY','N',40.5764614,-73.9670876,40.5764123,-73.9682752,40.57643685,-73.9676814,0.0011886145590503832,1,0,0),
	('K','P-234113','BRIGHTON BEACH AVENUE','BRIGHTON 5 STREET','BRIGHTON 4 STREET','N',40.5774713,-73.9623709,40.5772774,-73.9633395,40.57737435,-73.9628552,0.000987817376853585,1,0,0),
	('K','P-234114','BRIGHTON 1 STREET','BRIGHTON BEACH AVENUE','OCEAN VIEW AVENUE','W',40.5764614,-73.9670876,40.5786226,-73.96674879999999,40.577542,-73.9669182,0.0021875947705231272,1,0,0),
	('K','P-234116','BRIGHTON BEACH AVENUE','OCEAN PARKWAY','BRIGHTON 1 ROAD','S',40.5764123,-73.9682752,40.5764988,-73.9664402,40.576455550000006,-73.9673577,0.001837037628901385,1,0,0),
	('K','P-234119','63 STREET','19 AVENUE','18 AVENUE','S',40.619013,-73.986854,40.620303,-73.988993,40.619658,-73.9879235,0.002497883303917888,1,0,0),
	('K','P-234120','61 STREET','19 AVENUE','18 AVENUE','S',40.620131,-73.985693,40.621435,-73.98785099999999,40.620783,-73.986772,0.00252138454028121,1,0,0),
	('K','P-234121','NEW UTRECHT AVENUE','70 STREET','71 STREET','W',40.6197349,-73.9988381,40.6189503,-73.9990689,40.619342599999996,-73.9989535,0.0008178421608061509,1,0,0),
	('K','P-234179','FLATBUSH AVENUE','PROSPECT PLACE','7 AVENUE','W',40.678639,-73.97350899999999,40.6779961,-73.97305109999999,40.67831755,-73.97328005,0.0007892989420965733,1,0,0),
	('K','P-234194','4 AVENUE','37 STREET','36 STREET','E',40.6537148,-74.0049336,40.6542671,-74.0043493,40.65399095,-74.00464145000001,0.0008040160321752086,1,0,0),
	('K','P-234578','CORTELYOU ROAD','EAST 17 STREET','EAST 16 STREET','N',40.6420192,-73.9626477,40.6416128,-73.9635564,40.641816,-73.96310205,0.0009954379187051277,1,0,0),
	('K','P-234583','CORTELYOU ROAD','STRATFORD ROAD','CONEY ISLAND AVENUE','N',40.6396561,-73.967917,40.639018,-73.968722,40.63933705,-73.9683195,0.0010272276330024284,1,0,0),
	('K','P-234684','NEW UTRECHT AVENUE','72 STREET','73 STREET','W',40.6181893,-73.9992996,40.61740289999999,-73.99951070000002,40.61779609999999,-73.99940515,0.0008142408550434457,1,0,0),
	('K','P-234686','BRIGHTON 1 STREET','BRIGHTON BEACH AVENUE','BRIGHTON 1 PLACE','E',40.5764614,-73.9670876,40.5773834,-73.96700229999999,40.5769224,-73.96704495,0.0009259374114953141,1,0,0),
	('K','P-234974','FLATBUSH AVENUE','FOSTER AVENUE','EAST 26 STREET','W',40.638423,-73.95358259999999,40.6369031,-73.951972,40.63766305,-73.9527773,0.0022145266695144904,1,0,0),
	('K','P-235267','SMITH STREET','2 STREET','CARROLL STREET','E',40.679482,-73.995373,40.6802,-73.994889,40.679840999999996,-73.995131,0.0008658983774086111,1,0,0),
	('K','P-235275','PITKIN AVENUE','CHESTER STREET','ROCKAWAY AVENUE','S',40.669543,-73.91166199999999,40.6696789,-73.9107456,40.66961095,-73.9112038,0.0009264220258555355,1,0,0),
	('K','P-235276','PITKIN AVENUE','SARATOGA AVENUE','STRAUSS STREET','S',40.6687038,-73.91732669999999,40.6688404,-73.9163464,40.6687721,-73.91683655,0.0009897715140320023,1,0,0),
	('K','P-235277','PITKIN AVENUE','HERZL STREET','AMBOY STREET','S',40.6689736,-73.9154331,40.6691149,-73.9145204,40.66904425,-73.91497675,0.0009235729424360452,1,0,0),
	('K','P-235278','PITKIN AVENUE','ROCKAWAY AVENUE','CHESTER STREET','N',40.6696789,-73.9107456,40.669543,-73.91166199999999,40.66961095,-73.9112038,0.0009264220258555355,1,0,0),
	('K','P-235279','PITKIN AVENUE','OSBORN STREET','WATKINS STREET','S',40.6699779,-73.9088919,40.6583446,-73.904985,40.66416125,-73.90693845,0.012271818793480672,1,0,0),
	('K','P-235280','PITKIN AVENUE','THOMAS BOYLAND STREET','BRISTOL STREET','S',40.6692495,-73.9135787,40.6694003,-73.91262549999999,40.6693249,-73.9131021,0.0009650548585562799,1,0,0),
	('K','P-235281','BELMONT AVENUE','THATFORD AVENUE','OSBORN STREET','N',40.6685491,-73.9094798,40.6686863,-73.90856289999999,40.6686177,-73.90902134999999,0.000927108111286026,1,0,0),
	('K','P-235283','BELMONT AVENUE','THATFORD AVENUE','OSBORN STREET','S',40.6685491,-73.9094798,40.6686863,-73.90856289999999,40.6686177,-73.90902134999999,0.000927108111286026,1,0,0),
	('K','P-235288','PITKIN AVENUE','OSBORN STREET','THATFORD STREET','N',40.6699779,-73.9088919,40.6698293,-73.90981359999999,40.6699036,-73.90935275,0.0009336020833225712,1,0,0),
	('K','P-235291','PITKIN AVENUE','BRISTOL STREET','THOMAS BOYLAND STREET','N',40.6694003,-73.91262549999999,40.6692495,-73.9135787,40.6693249,-73.9131021,0.0009650548585562799,1,0,0),
	('K','P-235292','PITKIN AVENUE','AMBOY STREET','HERZL STREET','N',40.6691149,-73.9145204,40.6689736,-73.9154331,40.66904425,-73.91497675,0.0009235729424360452,1,0,0),
	('K','P-235305','PITKIN AVENUE','STRAUSS STREET','SARATOGA AVENUE','N',40.6688404,-73.9163464,40.6687038,-73.91732669999999,40.6687721,-73.91683655,0.0009897715140320023,1,0,0),
	('K','P-235306','PITKIN AVENUE','LEGION STREET','GRAFTON STREET','N',40.6685571,-73.9182873,40.6684224,-73.9192011,40.66848975,-73.91874419999999,0.0009236744718713193,1,0,0),
	('K','S-113531','STILLWELL AVENUE','BAY PARKWAY','AVENUE P','W',40.6081648,-73.9873492,40.6072554,-73.987268,40.6077101,-73.9873086,0.0009130179625807724,1,0,0);

INSERT INTO `signs` (`borough`, `sign_number`, `sign_order`, `distance`, `description`, `direction`)
VALUES
	('B','P-004958',1,0,'Curb Line',NULL),
	('B','P-004958',2,9,'Property Line',NULL),
	('B','P-004958',3,30,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) MIDNIGHT TO 3AM TUES & FRI <-->',NULL),
	('B','P-004958',4,30,'1 HOUR PARKING 9AM-7PM EXCEPT SUNDAY',NULL),
	('B','P-004958',5,208,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) MIDNIGHT TO 3AM TUES & FRI <-->',NULL),
	('B','P-004958',6,208,'1 HOUR PARKING 9AM-7PM EXCEPT SUNDAY',NULL),
	('B','P-004958',7,218,'Property Line',NULL),
	('B','P-004958',8,232,'Curb Line',NULL),
	('K','P-234112',1,0,'Curb Line',NULL),
	('K','P-234112',2,15,'Building Line',NULL),
	('K','P-234112',3,68,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW',NULL),
	('K','P-234112',4,68,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234112',5,68,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234112',6,133,'NO PARKING (SANITATION BROOM SYMBOL) 8-9AM MON WED FRI (SINGLE ARROW)','W'),
	('K','P-234112',7,133,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234112',8,133,'PAY AT MUNI-METER W/ SINGLE ARROW NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (DO NOT USE AS PER J.S-K 10/23/09)','W'),
	('K','P-234112',9,214,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)','W'),
	('K','P-234112',10,214,'PAY AT MUNI-METER W/ SINGLE ARROW NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (DO NOT USE AS PER J.S-K 10/23/09)','E'),
	('K','P-234112',11,254,'Building Line',NULL),
	('K','P-234112',12,270,'Curb Line',NULL),
	('K','P-234113',1,0,'Curb Line',NULL),
	('K','P-234113',2,14,'Building Line',NULL),
	('K','P-234113',3,108,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','E'),
	('K','P-234113',4,108,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234113',5,108,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234113',6,165,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-234113',7,165,'2 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <---->',NULL),
	('K','P-234113',8,215,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-234113',9,215,'2 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <---->',NULL),
	('K','P-234113',10,215,'PAY AT MUNI-METER W/ SINGLE ARROW (SUPERSEDES SP-160DA)','E'),
	('K','P-234113',11,230,'Curb Line',NULL),
	('K','P-234114',1,0,'Curb Line',NULL),
	('K','P-234114',2,23,'Building Line',NULL),
	('K','P-234114',3,73,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',4,73,'METERS ARE NOT IN EFFECT ABOVE TIMES (RIDER)',NULL),
	('K','P-234114',5,73,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234114',6,73,'NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (SUPERSEDES SP-186D',NULL),
	('K','P-234114',7,116,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',8,116,'METERS ARE NOT IN EFFECT ABOVE TIMES (RIDER)',NULL),
	('K','P-234114',9,116,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY W/ SINGLE ARROW','S'),
	('K','P-234114',10,116,'PAY AT MUNI-METER W/ SINGLE ARROW NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (DO NOT USE AS PER J.S-K 10/23/09)','S'),
	('K','P-234114',11,201,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',12,325,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',13,445,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',14,591,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',15,724,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM MON & THURS <---->',NULL),
	('K','P-234114',16,760,'Property Line',NULL),
	('K','P-234114',17,773,'Curb Line',NULL),
('K','P-234116',1,0,'Curb Line',NULL),
	('K','P-234116',2,10,'Property Line',NULL),
	('K','P-234116',3,74,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)','W'),
	('K','P-234116',4,221,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','W'),
	('K','P-234116',5,221,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234116',6,221,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234116',7,291,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-234116',8,291,'2 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234116',9,291,'PAY AT MUNI-METER W/ SINGLE ARROW NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (DO NOT USE AS PER J.S-K 10/23/09)','E'),
	('K','P-234116',10,357,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-234116',11,357,'2 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234116',12,357,'NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (SUPERSEDES SP-186D',NULL),
	('K','P-234116',13,413,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-234116',14,413,'2 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234116',15,413,'NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (SUPERSEDES SP-186D',NULL),
	('K','P-234116',16,493,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-234116',17,493,'2 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234116',18,493,'PAY AT MUNI-METER W/ SINGLE ARROW NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (DO NOT USE AS PER J.S-K 10/23/09)',NULL),
	('K','P-234116',19,494,'Building Line',NULL),
	('K','P-234116',20,505,'Curb Line',NULL),
	('K','P-234119',1,0,'Curb Line',NULL),
	('K','P-234119',2,9,'Building Line',NULL),
	('K','P-234119',3,79,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-10AM FRI <--->(SUPERSEDES SP-399C DATED 7-9-97)',NULL),
	('K','P-234119',4,237,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-10AM FRI <--->(SUPERSEDES SP-399C DATED 7-9-97)',NULL),
	('K','P-234119',5,358,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-10AM FRI <--->(SUPERSEDES SP-399C DATED 7-9-97)',NULL),
	('K','P-234119',6,489,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-10AM FRI <--->(SUPERSEDES SP-399C DATED 7-9-97)',NULL),
	('K','P-234119',7,606,'NO PARKING (SANITATION BROOM SYMBOL)8:30-10AM FRI W/SINGLE ARROW (SUPERSEDES SP-399CA DATED 7-9-97)','E'),
	('K','P-234119',8,606,'METERS ARE NOT IN EFFECT ABOVE TIMES (RIDER)',NULL),
	('K','P-234119',9,606,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY W/ SINGLE ARROW','W'),
	('K','P-234119',10,674,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-10AM FRI <--->(SUPERSEDES SP-399C DATED 7-9-97)',NULL),
	('K','P-234119',11,674,'METERS ARE NOT IN EFFECT ABOVE TIMES (RIDER)',NULL),
	('K','P-234119',12,674,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234119',13,698,'Building Line',NULL),
	('K','P-234119',14,713,'Curb Line',NULL),
('K','P-234121',1,0,'Curb Line',NULL),
	('K','P-234121',2,16,'Building Line',NULL),
	('K','P-234121',3,48,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-9AM EXCEPT SUN <----> (SUPERSEDES SP-183C DATED 7/10/97)',NULL),
	('K','P-234121',4,48,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234121',5,148,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-9AM EXCEPT SUN <----> (SUPERSEDES SP-183C DATED 7/10/97)',NULL),
	('K','P-234121',6,148,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234121',7,204,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-9AM EXCEPT SUN <----> (SUPERSEDES SP-183C DATED 7/10/97)',NULL),
	('K','P-234121',8,204,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234121',9,239,'Building Line',NULL),
	('K','P-234121',10,255,'Curb Line',NULL),
	('K','P-234179',1,0,'Curb Line',NULL),
	('K','P-234179',2,18,'Building Line',NULL),
	('K','P-234179',3,76,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)','S'),
	('K','P-234179',4,76,'NO STANDING 4-7PM MON THRU FRI (SINGLE ARROW)','N'),
	('K','P-234179',5,76,'1 HOUR PARKING 8AM-4PM EXCEPT SUNDAY W/SINGLE ARROW','N'),
	('K','P-234179',6,115,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)','N'),
	('K','P-234179',7,160,'NO STANDING 4PM-7PM MON THRU FRI <----->',NULL),
	('K','P-234179',8,160,'1 HOUR PARKING 8AM-4PM EXCEPT SUNDAY <-----> SUPERSEDES R7-170R',NULL),
	('K','P-234179',9,207,'NO STANDING EXCEPT TRUCKS LOADING & UNLOADING 8AM-4PM MON THRU FRI (SINGLE ARROW)','S'),
	('K','P-234179',10,207,'1 HOUR PARKING 8AM-4PM SATURDAY W/ SINGLE ARROW','S'),
	('K','P-234179',11,270,'NO STANDING 4PM-7PM MON THRU FRI <----->',NULL),
	('K','P-234179',12,270,'NO STANDING EXCEPT TRUCKS LOADING & UNLOADING 8AM-4PM MON THRU FRI (SINGLE ARROW)','S'),
	('K','P-234179',13,270,'1 HOUR PARKING 8AM-4PM SATURDAY <----->',NULL),
	('K','P-234179',14,276,'Building Line',NULL),
	('K','P-234179',15,300,'Curb Line',NULL),
	('K','P-234194',1,0,'Curb Line',NULL),
	('K','P-234194',2,6,'NO PARKING 7-10AM MON THRU FRI (ARROW)','N'),
	('K','P-234194',3,6,'2 HOUR PARKING 10AM-7PM EXCEPT SUNDAY W/SINGLE ARROW (SUPERSEDES R7-186RA)','N'),
	('K','P-234194',4,9,'Property Line',NULL),
	('K','P-234194',5,150,'NO PARKING 7-10AM MON THRU FRI',NULL),
	('K','P-234194',6,150,'2 HOUR PARKING 10AM-7PM EXCEPT SUNDAY <-----> (SUPERSEDES R7-186R)',NULL),
	('K','P-234194',7,215,'Building Line',NULL),
	('K','P-234194',8,224,'NO PARKING 7-10AM MON THRU FRI (ARROW)','S'),
	('K','P-234194',9,224,'2 HOUR PARKING 10AM-7PM EXCEPT SUNDAY W/SINGLE ARROW (SUPERSEDES R7-186RA)','S'),
	('K','P-234194',10,231,'Curb Line',NULL),
('K','P-234578',1,0,'Curb Line',NULL),
	('K','P-234578',2,14,'Property Line',NULL),
	('K','P-234578',3,181,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) MIDNIGHT TO-3AM MON WED FRI <----->(SUPERSEDES SP-338C DATED 7-10-97)',NULL),
	('K','P-234578',4,181,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY (ARROW)','E'),
	('K','P-234578',5,240,'Property Line',NULL),
	('K','P-234578',6,246,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) MIDNIGHT TO-3AM MON WED FRI W/ SINGLE ARROW (SUPERSEDES SP-338CA DATED 7-10-97)','E'),
	('K','P-234578',7,246,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY (ARROW)','E'),
	('K','P-234578',8,259,'Curb Line',NULL),
	('K','P-234583',1,0,'Curb Line',NULL),
	('K','P-234583',2,9,'Property Line',NULL),
	('K','P-234583',3,49,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) MIDNIGHT TO-3AM MON WED FRI <----->(SUPERSEDES SP-338C DATED 7-10-97)',NULL),
	('K','P-234583',4,49,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234583',5,148,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) MIDNIGHT TO-3AM MON WED FRI <----->(SUPERSEDES SP-338C DATED 7-10-97)',NULL),
	('K','P-234583',6,148,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234583',7,216,'Property Line',NULL),
	('K','P-234583',8,232,'Curb Line',NULL),
	('K','P-234684',1,0,'Curb Line',NULL),
	('K','P-234684',2,14,'Building Line',NULL),
	('K','P-234684',3,36,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)','N'),
	('K','P-234684',4,50,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-9AM EXCEPT SUN <----> (SUPERSEDES SP-183C DATED 7/10/97)',NULL),
	('K','P-234684',5,50,'1 HOUR PARKING 9AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234684',6,160,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)','S'),
	('K','P-234684',7,160,'NO PARKING (SANITATION BROOM SYMBOL) 8:30-9AM EXCEPT SUNW/SINGLE ARROW (SUPERSEDES SP-183CA DATED 7/10/97)','N'),
	('K','P-234684',8,160,'1 HOUR PARKING 9AM-7PM EXCEPT SUNDAY (ARROW)','N'),
	('K','P-234684',9,220,'NO STANDING ANYTIME (SINGLE ARROW) (REPLACES R7-20RA)',NULL),
	('K','P-234684',10,237,'Building Line',NULL),
	('K','P-234684',11,249,'Curb Line',NULL),
	('K','P-234686',1,0,'Curb Line',NULL),
	('K','P-234686',2,21,'Building Line',NULL),
	('K','P-234686',3,67,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM TUES & FRI<---->',NULL),
	('K','P-234686',4,67,'METERS ARE NOT IN EFFECT ABOVE TIMES (RIDER)',NULL),
	('K','P-234686',5,67,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY <----->',NULL),
	('K','P-234686',6,67,'NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (SUPERSEDES SP-186D',NULL),
	('K','P-234686',7,132,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM TUES & FRI<---->',NULL),
	('K','P-234686',8,132,'METERS ARE NOT IN EFFECT ABOVE TIMES (RIDER)',NULL),
	('K','P-234686',9,132,'2 HOUR PARKING 9AM-7PM EXCEPT SUNDAY W/ SINGLE ARROW','S'),
	('K','P-234686',10,132,'PAY AT MUNI-METER W/ SINGLE ARROW NYC PARKING CARD AVAILABLE (PARKING CARDS LOGO) FOR INFORMATION VISIT WWW.NYC.GOV/DOT OR CALL 311 (DO NOT USE AS PER J.S-K 10/23/09)','S'),
	('K','P-234686',11,187,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM TUES & FRI<---->',NULL),
	('K','P-234686',12,361,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM TUES & FRI<---->',NULL),
	('K','P-234686',13,509,'NO PARKING (SANITATION BROOM SYMBOL) 8-9:30AM TUES & FRI<---->',NULL),
	('K','P-234686',14,529,'Building Line',NULL),
	('K','P-234686',15,534,'Curb Line',NULL),
('K','P-234974',1,0,'Curb Line',NULL),
	('K','P-234974',2,21,'Property Line',NULL),
	('K','P-234974',3,102,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING <----->',NULL),
	('K','P-234974',4,146,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','N'),
	('K','P-234974',5,146,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234974',6,146,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234974',7,146,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-234974',8,211,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) 3AM-6AM MON WED FRI <-->',NULL),
	('K','P-234974',9,211,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234974',10,338,'NIGHT REGULATION (MOON & STARS SYMBOLS) NO PARKING (SANITATION BROOM SYMBOL) 3AM-6AM MON WED FRI <-->',NULL),
	('K','P-234974',11,338,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-234974',12,411,'Property Line',NULL),
	('K','P-234974',13,418,'Curb Line',NULL),
	('K','P-235267',1,0,'Curb Line',NULL),
	('K','P-235267',2,12,'Property Line',NULL),
	('K','P-235267',3,95,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING <----->',NULL),
	('K','P-235267',4,95,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235267',5,95,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235267',6,103,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN W/ SINGLE ARROW','N'),
	('K','P-235267',7,103,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY (ARROW)','N'),
	('K','P-235267',8,147,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235267',9,147,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235267',10,220,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235267',11,220,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235267',12,257,'Property Line',NULL),
	('K','P-235267',13,270,'Curb Line',NULL),
	('K','P-235275',1,0,'Curb Line',NULL),
	('K','P-235275',2,9,'Building Line',NULL),
	('K','P-235275',3,36,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235275',4,36,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235275',5,214,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235275',6,214,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235275',7,214,'Property Line',NULL),
	('K','P-235275',8,223,'Curb Line',NULL),
	('K','P-235276',1,0,'Curb Line',NULL),
	('K','P-235276',2,18,'Property Line',NULL),
	('K','P-235276',3,103,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','W'),
	('K','P-235276',4,103,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235276',5,103,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235276',6,120,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235276',7,120,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235276',8,198,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235276',9,198,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235276',10,220,'Property Line',NULL),
	('K','P-235276',11,235,'Curb Line',NULL),
('K','P-235277',1,0,'Curb Line',NULL),
	('K','P-235277',2,15,'Property Line',NULL),
	('K','P-235277',3,92,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','W'),
	('K','P-235277',4,92,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235277',5,92,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235277',6,115,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235277',7,115,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235277',8,177,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235277',9,177,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235277',10,217,'Property Line',NULL),
	('K','P-235277',11,231,'Curb Line',NULL),
	('K','P-235278',1,0,'Curb Line',NULL),
	('K','P-235278',2,15,'Property Line',NULL),
	('K','P-235278',3,106,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','E'),
	('K','P-235278',4,106,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235278',5,106,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235278',6,214,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN W/ SINGLE ARROW','E'),
	('K','P-235278',7,214,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235278',8,217,'Property Line',NULL),
	('K','P-235278',9,230,'Curb Line',NULL),
	('K','P-235279',1,0,'Curb Line',NULL),
	('K','P-235279',2,15,'Property Line',NULL),
	('K','P-235279',3,95,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','W'),
	('K','P-235279',4,95,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235279',5,95,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235279',6,199,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235279',7,199,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235279',8,217,'Property Line',NULL),
	('K','P-235279',9,232,'Curb Line',NULL),
	('K','P-235280',1,0,'Curb Line',NULL),
	('K','P-235280',2,19,'Property Line',NULL),
	('K','P-235280',3,97,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','W'),
	('K','P-235280',4,97,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235280',5,97,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235280',6,119,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235280',7,119,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235280',8,194,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235280',9,194,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235280',10,221,'Property Line',NULL),
	('K','P-235280',11,235,'Curb Line',NULL),
	('K','P-235281',1,0,'Curb Line',NULL),
	('K','P-235281',2,15,'Building Line',NULL),
	('K','P-235281',3,21,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235281',4,21,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235281',5,112,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235281',6,112,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235281',7,167,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN <----> (SUPERSEDES SP-182C)',NULL),
	('K','P-235281',8,167,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY <--->',NULL),
	('K','P-235281',9,216,'NO PARKING (SANITATION BROOM SYMBOL) 8-8:30AM EXCEPT SUN W/ SIGNLE ARROW (SUPERSEDES SP-182CA)','W'),
	('K','P-235281',10,216,'1 HOUR PARKING 8:30AM-7PM EXCEPT SUNDAY W/SINGLE ARROW','W'),
	('K','P-235281',11,216,'Building Line',NULL),
	('K','P-235281',12,232,'Curb Line',NULL),
('K','P-235283',1,0,'Curb Line',NULL),
	('K','P-235283',2,15,'Property Line',NULL),
	('K','P-235283',3,21,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235283',4,21,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235283',5,111,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235283',6,111,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235283',7,166,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235283',8,166,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235283',9,216,'Building Line',NULL),
	('K','P-235283',10,232,'Curb Line',NULL),
	('K','P-235288',1,0,'Curb Line',NULL),
	('K','P-235288',2,14,'Property Line',NULL),
	('K','P-235288',3,95,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','E'),
	('K','P-235288',4,95,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235288',5,95,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235288',6,187,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235288',7,187,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235288',8,217,'Property Line',NULL),
	('K','P-235288',9,232,'Curb Line',NULL),
	('K','P-235291',1,0,'Curb Line',NULL),
	('K','P-235291',2,14,'Property Line',NULL),
	('K','P-235291',3,92,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','E'),
	('K','P-235291',4,92,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235291',5,92,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235291',6,185,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235291',7,185,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235291',8,217,'Property Line',NULL),
	('K','P-235291',9,237,'Curb Line',NULL),
	('K','P-235292',1,0,'Curb Line',NULL),
	('K','P-235292',2,14,'Property Line',NULL),
	('K','P-235292',3,97,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','E'),
	('K','P-235292',4,97,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235292',5,97,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235292',6,196,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235292',7,196,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235292',8,217,'Property Line',NULL),
	('K','P-235292',9,231,'Curb Line',NULL),
	('K','P-235305',1,0,'Curb Line',NULL),
	('K','P-235305',2,15,'Property Line',NULL),
	('K','P-235305',3,97,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','E'),
	('K','P-235305',4,97,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235305',5,97,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','P-235305',6,192,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235305',7,192,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235305',8,216,'Property Line',NULL),
	('K','P-235305',9,222,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235305',10,222,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235305',11,234,'Curb Line',NULL),
('K','P-235306',1,0,'Curb Line',NULL),
	('K','P-235306',2,13,'Property Line',NULL),
	('K','P-235306',3,13,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235306',4,13,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235306',5,111,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235306',6,111,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235306',7,217,'NO PARKING (SANITATION BROOM SYMBOL) 7:30-8AM EXCEPT SUN <----->',NULL),
	('K','P-235306',8,217,'1 HOUR PARKING 8AM-7PM EXCEPT SUNDAY',NULL),
	('K','P-235306',9,232,'Curb Line',NULL),
	('K','S-113531',1,0,'Curb Line',NULL),
	('K','S-113531',2,15,'Property Line',NULL),
	('K','S-113531',3,107,'BUS STOP SIGN (BUS & HANDICAP SYMBOLS) NO STANDING W/ SINGLE ARROW','N'),
	('K','S-113531',4,107,'M 18 LTD (12\"X 6\") 8 STREET 4 AVENUE (16\"X 6\") (ROUTE DESTINATION PANEL 032\"X 06\")(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','S-113531',5,107,'14 STREET & UNION SQ (BOTTOM LOCATION PANEL)(USE AS EXAMPLE FOR DIFFERENT LOCATION)',NULL),
	('K','S-113531',6,184,'Property Line',NULL),
	('K','S-113531',7,190,'NO PARKING (SANITATION BROOM SYMBOL) 11:30AM TO 1PM THURS <---->',NULL),
	('K','S-113531',8,210,'Curb Line',NULL);
