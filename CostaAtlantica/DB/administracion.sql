-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-12-2017 a las 04:53:55
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `administracion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `families`
--

CREATE TABLE `families` (
  `id` int(11) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) NOT NULL,
  `cbu` varchar(22) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `fromDate` date DEFAULT NULL,
  `toDate` date DEFAULT NULL,
  `lapse` varchar(50) NOT NULL,
  `reservation` double DEFAULT NULL,
  `id_house` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `families`
--

INSERT INTO `families` (`id`, `lastname`, `address`, `telephone`, `cbu`, `quantity`, `fromDate`, `toDate`, `lapse`, `reservation`, `id_house`) VALUES
(50, 'Pepon', 'Acacias', '15423654', '1234567896321456987456', 1, '2017-04-11', '2017-12-10', 'Años: 0, Meses: 7, Días: 29', 34, 'Jacarandá'),
(51, 'Ramon', 'Av de mayo 450', '156000988', '1234567896541236547896', 2, '2017-10-30', '2017-12-02', 'Años: 0, Meses: 1, Días: 2', 12333, 'Jacarandá'),
(54, 'Gonzalez', 'Pinzon', '15236541', '1234567894561236547896', 3, '2017-11-10', '2017-11-30', 'Años: 0, Meses: 0, Días: 20', 1563, 'Jacarandá');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `houses`
--

CREATE TABLE `houses` (
  `id` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `houses`
--

INSERT INTO `houses` (`id`, `address`, `telephone`, `quantity`) VALUES
(1, 'Acacias', '15-49735711', 5),
(2, 'Jacarandá', '15-49735711', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objects`
--

CREATE TABLE `objects` (
  `id` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `id_houses` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `objects`
--

INSERT INTO `objects` (`id`, `description`, `id_houses`) VALUES
(1, 'Helader', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `repair`
--

CREATE TABLE `repair` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `action` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `repair`
--

INSERT INTO `repair` (`id`, `name`, `action`, `description`) VALUES
(34, 'Jacarandá', 'Cortar', 'pasto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taxes`
--

CREATE TABLE `taxes` (
  `id` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `id_houses` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `families`
--
ALTER TABLE `families`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `houses`
--
ALTER TABLE `houses`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `objects`
--
ALTER TABLE `objects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_houses` (`id_houses`);

--
-- Indices de la tabla `repair`
--
ALTER TABLE `repair`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `taxes`
--
ALTER TABLE `taxes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_houses` (`id_houses`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `families`
--
ALTER TABLE `families`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT de la tabla `houses`
--
ALTER TABLE `houses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `objects`
--
ALTER TABLE `objects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `repair`
--
ALTER TABLE `repair`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT de la tabla `taxes`
--
ALTER TABLE `taxes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `objects`
--
ALTER TABLE `objects`
  ADD CONSTRAINT `objects_ibfk_1` FOREIGN KEY (`id_houses`) REFERENCES `houses` (`id`);

--
-- Filtros para la tabla `taxes`
--
ALTER TABLE `taxes`
  ADD CONSTRAINT `taxes_ibfk_1` FOREIGN KEY (`id_houses`) REFERENCES `houses` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
