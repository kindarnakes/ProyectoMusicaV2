-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2020 a las 12:43:37
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_musica`
--
CREATE DATABASE IF NOT EXISTS db_musica;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista`
--

CREATE TABLE `artista` (
  `id` int(11) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `nacionalidad` varchar(50) NOT NULL,
  `foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `artista`
--

INSERT INTO `artista` (`id`, `nombre`, `nacionalidad`, `foto`) VALUES
(1, 'Sabina', 'Español', 'http://adsadasdasdasd.com'),
(2, 'Heroes del silencio', 'Español', 'http://sadasdasdsafsdgsdhfhdfh.com'),
(3, 'Leiva', 'Español', 'ecdcdcdcd'),
(4, 'Dani Martin', 'Español', 'frrfrfer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE `cancion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `duracion` int(11) NOT NULL,
  `id_genero` int(11) DEFAULT NULL,
  `id_disco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cancion`
--

INSERT INTO `cancion` (`id`, `nombre`, `duracion`, `id_genero`, `id_disco`) VALUES
(1, '19 dias y 500 noches', 240, 2, 1),
(2, 'Maldito duende', 300, 1, 3),
(6, '19 dias y 500 noches', 858, 1, 4),
(7, 'Portales', 251, NULL, 6),
(8, 'La Mentira', 255, NULL, 6),
(9, 'Godzilla', 164, NULL, 5),
(10, 'Lobos', 142, NULL, 5),
(11, 'Los Cantantes', 137, NULL, 8),
(12, 'Emocional', 133, NULL, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comenta`
--

CREATE TABLE `comenta` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_lista` int(11) NOT NULL,
  `mensaje` text NOT NULL,
  `instante` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comenta`
--

INSERT INTO `comenta` (`id`, `id_usuario`, `id_lista`, `mensaje`, `instante`) VALUES
(1, 1, 2, 'gsdgsdgdsgsdgsdg', '2020-10-29 09:58:21'),
(2, 1, 3, 'fghgfjfgjfgjgfj', '2020-10-29 09:58:30'),
(3, 2, 1, 'xcbxchfdhdfh', '2020-10-29 09:58:36'),
(4, 1, 3, 'dsgsgsdgsdg', '2020-10-20 08:58:37'),
(5, 1, 3, 'rwerewrwerewrewr', '2020-10-06 08:58:50'),
(6, 2, 1, 'hdnfdn', '2020-10-06 08:59:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disco`
--

CREATE TABLE `disco` (
  `id` int(11) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `foto` varchar(255) NOT NULL,
  `id_artista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `disco`
--

INSERT INTO `disco` (`id`, `nombre`, `fecha_publicacion`, `foto`, `id_artista`) VALUES
(1, '19 dias y 500 noches', '2020-05-21', 'http://', 1),
(2, 'Esta boca es mia', '2019-07-12', 'http://', 1),
(3, 'Maldito Duende', '2019-07-09', 'http://', 2),
(4, 'Tierra', '2019-08-12', 'http://', 2),
(5, 'Nuclear', '2019-09-12', 'efefefefefe', 3),
(6, 'La montaña rusa', '2016-11-10', 'rvfrvreververve', 4),
(7, 'Mi Teatro', '2014-11-06', 'defefefefe', 4),
(8, 'Pólvora', '2014-11-20', 'ddedngegkj', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id`, `nombre`) VALUES
(1, 'Rock'),
(2, 'Autor'),
(3, 'Pop');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista_cancion`
--

CREATE TABLE `lista_cancion` (
  `id_lista` int(11) NOT NULL,
  `id_cancion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `lista_cancion`
--

INSERT INTO `lista_cancion` (`id_lista`, `id_cancion`) VALUES
(1, 1),
(2, 2),
(3, 1),
(3, 6),
(4, 2),
(4, 6),
(4, 9),
(4, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista_reproduccion`
--

CREATE TABLE `lista_reproduccion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_creador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `lista_reproduccion`
--

INSERT INTO `lista_reproduccion` (`id`, `nombre`, `descripcion`, `id_creador`) VALUES
(1, 'sabina', 'ggasfasfagsgsdgsdgsdg', 2),
(2, 'Heroes', 'sdggfjyu,fdnhsdg', 1),
(3, 'Mezcla', 'rehfhfhfgj', 1),
(4, 'Los Intocables', 'Somos los intocable nen', 9),
(5, 'aleatiro', 'fnerveruigfper,gpèr', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reproduce`
--

CREATE TABLE `reproduce` (
  `id` int(11) NOT NULL,
  `id_cancion` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `instante` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reproduce`
--

INSERT INTO `reproduce` (`id`, `id_cancion`, `id_usuario`, `instante`) VALUES
(1, 1, 1, '2020-10-29 09:59:21'),
(2, 1, 2, '2020-10-29 09:59:52'),
(3, 6, 1, '2020-10-19 08:59:54'),
(5, 6, 2, '2020-10-15 09:00:09'),
(6, 2, 1, '2020-10-13 09:00:16'),
(7, 2, 2, '2020-10-29 10:00:41'),
(8, 2, 1, '2020-08-04 09:00:42'),
(9, 2, 1, '2020-10-29 10:00:58');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

CREATE TABLE `suscripcion` (
  `id_usuario` int(11) NOT NULL,
  `id_lista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `suscripcion`
--

INSERT INTO `suscripcion` (`id_usuario`, `id_lista`) VALUES
(1, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `correo`, `nombre`, `foto`) VALUES
(1, 'agustin@ejemplo.com', 'agustin', 'http//:notengo.com'),
(2, 'angel@ejemplo.com', 'angel', 'http//:notengo.com'),
(9, 'santos@ejemplo.com', 'santos', 'notieneeee'),
(11, 'anonimo', 'anonimo', 'anonimo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_genero` (`id_genero`),
  ADD KEY `id_disco` (`id_disco`);

--
-- Indices de la tabla `comenta`
--
ALTER TABLE `comenta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usu` (`id_usuario`),
  ADD KEY `id_list` (`id_lista`);

--
-- Indices de la tabla `disco`
--
ALTER TABLE `disco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_artista` (`id_artista`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `lista_cancion`
--
ALTER TABLE `lista_cancion`
  ADD PRIMARY KEY (`id_lista`,`id_cancion`),
  ADD KEY `id_cancion` (`id_cancion`);

--
-- Indices de la tabla `lista_reproduccion`
--
ALTER TABLE `lista_reproduccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_creador` (`id_creador`);

--
-- Indices de la tabla `reproduce`
--
ALTER TABLE `reproduce`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cancion` (`id_cancion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD PRIMARY KEY (`id_usuario`,`id_lista`),
  ADD KEY `id_lista` (`id_lista`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artista`
--
ALTER TABLE `artista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cancion`
--
ALTER TABLE `cancion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `comenta`
--
ALTER TABLE `comenta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `disco`
--
ALTER TABLE `disco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `lista_reproduccion`
--
ALTER TABLE `lista_reproduccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reproduce`
--
ALTER TABLE `reproduce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD CONSTRAINT `id_disco` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `id_genero` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `comenta`
--
ALTER TABLE `comenta`
  ADD CONSTRAINT `id_list` FOREIGN KEY (`id_lista`) REFERENCES `lista_reproduccion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_usu` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `disco`
--
ALTER TABLE `disco`
  ADD CONSTRAINT `id_artista` FOREIGN KEY (`id_artista`) REFERENCES `artista` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `lista_cancion`
--
ALTER TABLE `lista_cancion`
  ADD CONSTRAINT `id_cancion` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_lista` FOREIGN KEY (`id_lista`) REFERENCES `lista_reproduccion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `lista_reproduccion`
--
ALTER TABLE `lista_reproduccion`
  ADD CONSTRAINT `id_creador` FOREIGN KEY (`id_creador`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `reproduce`
--
ALTER TABLE `reproduce`
  ADD CONSTRAINT `reproduce_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `reproduce_ibfk_2` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`id_lista`) REFERENCES `lista_reproduccion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
