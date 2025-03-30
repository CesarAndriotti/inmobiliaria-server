-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.32-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para base_inmobiliaria
CREATE DATABASE IF NOT EXISTS `base_inmobiliaria` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `base_inmobiliaria`;

-- Volcando estructura para tabla base_inmobiliaria.address
CREATE TABLE IF NOT EXISTS `address` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Street_Name` varchar(100) NOT NULL,
  `Number` varchar(45) NOT NULL,
  `City_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Adress_City1_idx` (`City_Id`),
  CONSTRAINT `fk_Adress_City1` FOREIGN KEY (`City_Id`) REFERENCES `city` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.address: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.agent
CREATE TABLE IF NOT EXISTS `agent` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Lastname` varchar(100) NOT NULL,
  `Identification_Number` varchar(45) NOT NULL,
  `Date_Of_Birth` date NOT NULL,
  `Phone_Number` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Agent_Registration` varchar(45) NOT NULL,
  `Profile_Photo` varchar(200) DEFAULT NULL,
  `Agent_State_Id` int(10) unsigned NOT NULL,
  `Address_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `unique_Agent_IdentificationNumber` (`Identification_Number`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `Phone_Number_UNIQUE` (`Phone_Number`),
  UNIQUE KEY `Agent_Registration_UNIQUE` (`Agent_Registration`),
  KEY `key_Agent_Name` (`Name`),
  KEY `key_Agent_Lastname` (`Lastname`),
  KEY `fk_Agent_Agent_State1_idx` (`Agent_State_Id`),
  KEY `fk_Agent_Address1_idx` (`Address_Id`),
  CONSTRAINT `fk_Agent_Address1` FOREIGN KEY (`Address_Id`) REFERENCES `address` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agent_Agent_State1` FOREIGN KEY (`Agent_State_Id`) REFERENCES `agent_state` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.agent: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.agent_state
CREATE TABLE IF NOT EXISTS `agent_state` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `State` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.agent_state: ~2 rows (aproximadamente)
INSERT INTO `agent_state` (`Id`, `State`) VALUES
	(1, 'Activo'),
	(2, 'Inactivo');

-- Volcando estructura para tabla base_inmobiliaria.appraisal
CREATE TABLE IF NOT EXISTS `appraisal` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Value` decimal(6,2) NOT NULL,
  `Property_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Appraisal_Property1_idx` (`Property_Id`),
  CONSTRAINT `fk_Appraisal_Property1` FOREIGN KEY (`Property_Id`) REFERENCES `property` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.appraisal: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.city
CREATE TABLE IF NOT EXISTS `city` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `State_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_City_State1_idx` (`State_Id`),
  CONSTRAINT `fk_City_State1` FOREIGN KEY (`State_Id`) REFERENCES `state` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.city: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.construction
CREATE TABLE IF NOT EXISTS `construction` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Covered_Area` float unsigned NOT NULL DEFAULT 0,
  `Age` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Rooms` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Offices` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Bedrooms` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Bathrooms` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Garage` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Pools` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Barbecue` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `Construction_Type_Id` int(10) unsigned NOT NULL,
  `Property_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Construction_Construction_Type1_idx` (`Construction_Type_Id`),
  KEY `fk_Construction_Property1_idx` (`Property_Id`),
  CONSTRAINT `fk_Construction_Construction_Type1` FOREIGN KEY (`Construction_Type_Id`) REFERENCES `construction_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Construction_Property1` FOREIGN KEY (`Property_Id`) REFERENCES `property` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.construction: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.construction_type
CREATE TABLE IF NOT EXISTS `construction_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.construction_type: ~5 rows (aproximadamente)
INSERT INTO `construction_type` (`Id`, `Type`) VALUES
	(1, 'Casa'),
	(2, 'Chalet'),
	(3, 'Departamento'),
	(4, 'Local'),
	(5, 'Galpón');

-- Volcando estructura para tabla base_inmobiliaria.country
CREATE TABLE IF NOT EXISTS `country` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Code` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `key_Country_Code` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.country: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Lastname` varchar(100) NOT NULL,
  `Identification_Number` varchar(45) NOT NULL,
  `Date_Of_Birth` date NOT NULL,
  `Phone_Number` varchar(45) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Customer_Type_Id` int(10) unsigned NOT NULL,
  `Address_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `unique_Customer_IdentificationNumber` (`Identification_Number`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `Phone_Number_UNIQUE` (`Phone_Number`),
  KEY `key_Customer_Name` (`Name`),
  KEY `key_Customer_Lastname` (`Lastname`),
  KEY `fk_Customer_Customer_Type1_idx` (`Customer_Type_Id`),
  KEY `fk_Customer_Address1_idx` (`Address_Id`),
  CONSTRAINT `fk_Customer_Address1` FOREIGN KEY (`Address_Id`) REFERENCES `address` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Customer_Type1` FOREIGN KEY (`Customer_Type_Id`) REFERENCES `customer_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.customer: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.customer_type
CREATE TABLE IF NOT EXISTS `customer_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.customer_type: ~2 rows (aproximadamente)
INSERT INTO `customer_type` (`Id`, `Type`) VALUES
	(1, 'Particular'),
	(2, 'Empresa');

-- Volcando estructura para tabla base_inmobiliaria.document_type
CREATE TABLE IF NOT EXISTS `document_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.document_type: ~2 rows (aproximadamente)
INSERT INTO `document_type` (`Id`, `Type`) VALUES
	(1, 'Boleto Compra-Venta'),
	(2, 'Contrato de alquiler');

-- Volcando estructura para tabla base_inmobiliaria.historical_prices
CREATE TABLE IF NOT EXISTS `historical_prices` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Previous_Price` decimal(6,2) unsigned NOT NULL,
  `After_Price` decimal(6,2) unsigned NOT NULL,
  `Previous_Profit_Percentage` float unsigned NOT NULL,
  `After_Profit_Percentage` float unsigned NOT NULL,
  `Price_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Historica_lPrices_Price1_idx` (`Price_Id`),
  CONSTRAINT `fk_Historica_lPrices_Price1` FOREIGN KEY (`Price_Id`) REFERENCES `price` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.historical_prices: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.money_type
CREATE TABLE IF NOT EXISTS `money_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.money_type: ~2 rows (aproximadamente)
INSERT INTO `money_type` (`Id`, `Type`) VALUES
	(1, 'Pesos'),
	(2, 'Dolares');

-- Volcando estructura para tabla base_inmobiliaria.multimedia_type
CREATE TABLE IF NOT EXISTS `multimedia_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.multimedia_type: ~2 rows (aproximadamente)
INSERT INTO `multimedia_type` (`Id`, `Type`) VALUES
	(1, 'Fotografía'),
	(2, 'Video');

-- Volcando estructura para tabla base_inmobiliaria.operation
CREATE TABLE IF NOT EXISTS `operation` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Operation_State_Id` int(10) unsigned NOT NULL,
  `Operation_Type_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Operation_Operation_State1_idx` (`Operation_State_Id`),
  KEY `fk_Operation_Operation_Type1_idx` (`Operation_Type_Id`),
  CONSTRAINT `fk_Operation_Operation_State1` FOREIGN KEY (`Operation_State_Id`) REFERENCES `operation_state` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Operation_Operation_Type1` FOREIGN KEY (`Operation_Type_Id`) REFERENCES `operation_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.operation: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.operation_details
CREATE TABLE IF NOT EXISTS `operation_details` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Operation_Id` int(10) unsigned NOT NULL,
  `Customer_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Operation_Details_Operation1_idx` (`Operation_Id`),
  KEY `fk_Operation_Details_Customer1_idx` (`Customer_Id`),
  CONSTRAINT `fk_Operation_Details_Customer1` FOREIGN KEY (`Customer_Id`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Operation_Details_Operation1` FOREIGN KEY (`Operation_Id`) REFERENCES `operation` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.operation_details: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.operation_document
CREATE TABLE IF NOT EXISTS `operation_document` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Link` varchar(500) NOT NULL,
  `Operation_Id` int(10) unsigned NOT NULL,
  `Document_Type_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Operation_Document_Operation1_idx` (`Operation_Id`),
  KEY `fk_Operation_Document_Document_Type1_idx` (`Document_Type_Id`),
  CONSTRAINT `fk_Operation_Document_Document_Type1` FOREIGN KEY (`Document_Type_Id`) REFERENCES `document_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Operation_Document_Operation1` FOREIGN KEY (`Operation_Id`) REFERENCES `operation` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.operation_document: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.operation_state
CREATE TABLE IF NOT EXISTS `operation_state` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `State` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.operation_state: ~2 rows (aproximadamente)
INSERT INTO `operation_state` (`Id`, `State`) VALUES
	(1, 'En Curso'),
	(2, 'Finalizada');

-- Volcando estructura para tabla base_inmobiliaria.operation_type
CREATE TABLE IF NOT EXISTS `operation_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.operation_type: ~3 rows (aproximadamente)
INSERT INTO `operation_type` (`Id`, `Type`) VALUES
	(1, 'Venta'),
	(2, 'Alquiler permanente'),
	(3, 'Alquiler temporal');

-- Volcando estructura para tabla base_inmobiliaria.owner
CREATE TABLE IF NOT EXISTS `owner` (
  `Id` int(11) NOT NULL,
  `Since_Date` date NOT NULL,
  `To_Date` date DEFAULT NULL,
  `Property_Id` int(10) unsigned NOT NULL,
  `Customer_Id` int(10) unsigned NOT NULL,
  `Owner_Type_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Owner_Property1_idx` (`Property_Id`),
  KEY `fk_Owner_Customer1_idx` (`Customer_Id`),
  KEY `fk_Owner_Owner_Type1_idx` (`Owner_Type_Id`),
  CONSTRAINT `fk_Owner_Customer1` FOREIGN KEY (`Customer_Id`) REFERENCES `customer` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Owner_Owner_Type1` FOREIGN KEY (`Owner_Type_Id`) REFERENCES `owner_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Owner_Property1` FOREIGN KEY (`Property_Id`) REFERENCES `property` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.owner: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.owner_type
CREATE TABLE IF NOT EXISTS `owner_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.owner_type: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.price
CREATE TABLE IF NOT EXISTS `price` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Price` decimal(6,2) unsigned NOT NULL,
  `Profit_Percentage` float unsigned NOT NULL DEFAULT 3,
  `Money_Type_Id` int(10) unsigned NOT NULL,
  `Publication_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Price_Money_Type1_idx` (`Money_Type_Id`),
  KEY `fk_Price_Publication1_idx` (`Publication_Id`),
  CONSTRAINT `fk_Price_Money_Type1` FOREIGN KEY (`Money_Type_Id`) REFERENCES `money_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Price_Publication1` FOREIGN KEY (`Publication_Id`) REFERENCES `publication` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.price: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.property
CREATE TABLE IF NOT EXISTS `property` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Total_Area` float NOT NULL DEFAULT 0,
  `Water` tinyint(4) NOT NULL,
  `Electricity` tinyint(4) NOT NULL,
  `Gas` tinyint(4) NOT NULL,
  `Sewer` tinyint(4) NOT NULL,
  `Asphalt` tinyint(4) NOT NULL,
  `Summary` varchar(500) NOT NULL,
  `Property_State_Id` int(10) unsigned NOT NULL,
  `Agent_Id` int(10) unsigned NOT NULL,
  `Operation_Type_Id` int(10) unsigned NOT NULL,
  `Property_Type_Id` int(10) unsigned NOT NULL,
  `Address_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Property_Property_State1_idx` (`Property_State_Id`),
  KEY `fk_Property_Agent1_idx` (`Agent_Id`),
  KEY `fk_Property_Operation_Type1_idx` (`Operation_Type_Id`),
  KEY `fk_Property_Property_Type1_idx` (`Property_Type_Id`),
  KEY `fk_Property_Address1_idx` (`Address_Id`),
  CONSTRAINT `fk_Property_Address1` FOREIGN KEY (`Address_Id`) REFERENCES `address` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Property_Agent1` FOREIGN KEY (`Agent_Id`) REFERENCES `agent` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Property_Operation_Type1` FOREIGN KEY (`Operation_Type_Id`) REFERENCES `operation_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Property_Property_State1` FOREIGN KEY (`Property_State_Id`) REFERENCES `property_state` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Property_Property_Type1` FOREIGN KEY (`Property_Type_Id`) REFERENCES `property_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.property: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.property_document
CREATE TABLE IF NOT EXISTS `property_document` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Link` varchar(500) NOT NULL,
  `Property_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Property_Document_Property1_idx` (`Property_Id`),
  CONSTRAINT `fk_Property_Document_Property1` FOREIGN KEY (`Property_Id`) REFERENCES `property` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.property_document: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.property_multimedia
CREATE TABLE IF NOT EXISTS `property_multimedia` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Link` varchar(500) NOT NULL,
  `Multimedia_Type_Id` int(10) unsigned NOT NULL,
  `Property_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Property_Multimedia_Multimedia_Type1_idx` (`Multimedia_Type_Id`),
  KEY `fk_Property_Multimedia_Property1_idx` (`Property_Id`),
  CONSTRAINT `fk_Property_Multimedia_Multimedia_Type1` FOREIGN KEY (`Multimedia_Type_Id`) REFERENCES `multimedia_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Property_Multimedia_Property1` FOREIGN KEY (`Property_Id`) REFERENCES `property` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.property_multimedia: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.property_state
CREATE TABLE IF NOT EXISTS `property_state` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `State` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.property_state: ~2 rows (aproximadamente)
INSERT INTO `property_state` (`Id`, `State`) VALUES
	(1, 'Nuevo'),
	(2, 'Usado');

-- Volcando estructura para tabla base_inmobiliaria.property_type
CREATE TABLE IF NOT EXISTS `property_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.property_type: ~4 rows (aproximadamente)
INSERT INTO `property_type` (`Id`, `Type`) VALUES
	(1, 'Vivienda'),
	(2, 'Lote'),
	(3, 'Comercio'),
	(4, 'Quinta');

-- Volcando estructura para tabla base_inmobiliaria.publication
CREATE TABLE IF NOT EXISTS `publication` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Start_Date` date NOT NULL,
  `Finish_Date` date NOT NULL,
  `Summary` varchar(500) NOT NULL,
  `Publication_Type_Id` int(10) unsigned NOT NULL,
  `Publication_State_Id` int(10) unsigned NOT NULL,
  `Property_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Publication_Publication_Type1_idx` (`Publication_Type_Id`),
  KEY `fk_Publication_Publication_State1_idx` (`Publication_State_Id`),
  KEY `fk_Publication_Property1_idx` (`Property_Id`),
  CONSTRAINT `fk_Publication_Property1` FOREIGN KEY (`Property_Id`) REFERENCES `property` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Publication_Publication_State1` FOREIGN KEY (`Publication_State_Id`) REFERENCES `publication_state` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Publication_Publication_Type1` FOREIGN KEY (`Publication_Type_Id`) REFERENCES `publication_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.publication: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.publication_state
CREATE TABLE IF NOT EXISTS `publication_state` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `State` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.publication_state: ~3 rows (aproximadamente)
INSERT INTO `publication_state` (`Id`, `State`) VALUES
	(1, 'Activa'),
	(2, 'Suspendida'),
	(3, 'Finalizada');

-- Volcando estructura para tabla base_inmobiliaria.publication_type
CREATE TABLE IF NOT EXISTS `publication_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.publication_type: ~2 rows (aproximadamente)
INSERT INTO `publication_type` (`Id`, `Type`) VALUES
	(1, 'Permanente'),
	(2, 'Temporal');

-- Volcando estructura para tabla base_inmobiliaria.session
CREATE TABLE IF NOT EXISTS `session` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Start_Datetime` datetime NOT NULL,
  `Finish_Datetime` datetime NOT NULL,
  `User_Id` int(10) unsigned NOT NULL,
  `Session_State_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Session_User1_idx` (`User_Id`),
  KEY `fk_Session_Session_State1_idx` (`Session_State_Id`),
  CONSTRAINT `fk_Session_Session_State1` FOREIGN KEY (`Session_State_Id`) REFERENCES `session_state` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Session_User1` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.session: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.session_state
CREATE TABLE IF NOT EXISTS `session_state` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `State` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.session_state: ~2 rows (aproximadamente)
INSERT INTO `session_state` (`Id`, `State`) VALUES
	(1, 'Abierta'),
	(2, 'Cerrada');

-- Volcando estructura para tabla base_inmobiliaria.state
CREATE TABLE IF NOT EXISTS `state` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Country_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_State_Country1_idx` (`Country_Id`),
  CONSTRAINT `fk_State_Country1` FOREIGN KEY (`Country_Id`) REFERENCES `country` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.state: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.user
CREATE TABLE IF NOT EXISTS `user` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nick` varchar(60) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Agent_Id` int(10) unsigned NOT NULL,
  `User_Type_Id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_User_Agent1_idx` (`Agent_Id`),
  KEY `fk_User_User_Type1_idx` (`User_Type_Id`),
  CONSTRAINT `fk_User_Agent1` FOREIGN KEY (`Agent_Id`) REFERENCES `agent` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_User_Type1` FOREIGN KEY (`User_Type_Id`) REFERENCES `user_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.user: ~0 rows (aproximadamente)

-- Volcando estructura para tabla base_inmobiliaria.user_type
CREATE TABLE IF NOT EXISTS `user_type` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Volcando datos para la tabla base_inmobiliaria.user_type: ~2 rows (aproximadamente)
INSERT INTO `user_type` (`Id`, `Type`) VALUES
	(1, 'Administrador'),
	(2, 'Vendedor');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
