-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2020 at 10:25 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asta`
--

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(21, '2013_10_09_101653_rprofession', 1),
(22, '2013_10_09_102447_rorganization', 1),
(23, '2014_10_12_000000_create_users_table', 1),
(24, '2014_10_12_100000_create_password_resets_table', 1),
(25, '2018_10_09_102559_rrequestseminars', 1),
(26, '2018_10_09_102801_treviseseminars', 1),
(27, '2018_10_09_102802_tapprovedseminars', 1),
(28, '2018_10_09_103610_tattendances', 1),
(29, '2018_10_12_110527_t_certificates', 1),
(30, '2018_10_12_110702_r_online_seminars', 1);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_online_seminars`
--

CREATE TABLE `r_online_seminars` (
  `ROS_ID` int(10) UNSIGNED NOT NULL,
  `ROS_TITLE` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `PR_ID` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `ROS_DESC` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `ROS_LINK` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_organizations`
--

CREATE TABLE `r_organizations` (
  `OR_ID` int(10) UNSIGNED NOT NULL,
  `OR_NAME` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `OR_DESC` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `OR_CPD_ACCREDITED` tinyint(1) NOT NULL DEFAULT 0,
  `OR_EMAIL` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `OR_CONTACT_NO` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `OR_WEBSITE` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ACCRED_NO` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ACCRED_DATE` date DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `r_organizations`
--

INSERT INTO `r_organizations` (`OR_ID`, `OR_NAME`, `OR_DESC`, `OR_CPD_ACCREDITED`, `OR_EMAIL`, `OR_CONTACT_NO`, `OR_WEBSITE`, `ACCRED_NO`, `ACCRED_DATE`, `deleted_at`, `created_at`, `updated_at`) VALUES
(1, 'Commits', 'Commits Description', 0, 'commits@commits.com', '09995251071', 'commits', NULL, NULL, NULL, '2020-01-22 01:28:11', '2020-01-22 01:28:11'),
(2, 'Mateus S. Asato', 'Aenean aliquam gravida lacus, dignissim aliquet diam sodales iaculis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin placerat leo ligula, eget vehicula augue tincidunt in. Morbi ullamcorper quis lectus vel aliquet.', 1, 'gfmic@yahoo.com', '09876543253', 'http://gfmic.pupqc.net/', '85427003', '2020-01-16', NULL, '2020-01-22 01:44:37', '2020-01-22 01:44:37');

-- --------------------------------------------------------

--
-- Table structure for table `r_professions`
--

CREATE TABLE `r_professions` (
  `PR_ID` int(10) UNSIGNED NOT NULL,
  `PR_NAME` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `PR_DESC` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `PR_MAX_CPD_UNITS` decimal(10,2) NOT NULL,
  `ACTIVE_FLAG` int(11) NOT NULL DEFAULT 1,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `r_professions`
--

INSERT INTO `r_professions` (`PR_ID`, `PR_NAME`, `PR_DESC`, `PR_MAX_CPD_UNITS`, `ACTIVE_FLAG`, `deleted_at`, `created_at`, `updated_at`) VALUES
(1, 'Accounting', 'Accounting Description', '20.00', 1, NULL, '2020-01-22 01:28:10', '2020-01-22 01:28:10'),
(2, 'Teaching', 'Teaching Description', '20.00', 1, NULL, '2020-01-22 01:28:10', '2020-01-22 01:28:10'),
(3, 'Mathematician', 'Mathematician Description', '50.00', 1, NULL, '2020-01-22 01:28:10', '2020-01-22 01:28:10'),
(4, 'Information Technology', 'Information Technology Description', '100.00', 1, NULL, '2020-01-22 01:28:10', '2020-01-22 01:28:10'),
(5, 'Human Resource', 'Human Resource Description', '50.00', 1, NULL, '2020-01-22 01:28:10', '2020-01-22 01:28:10'),
(6, 'Scientist', 'Scientist Description', '30.00', 1, NULL, '2020-01-22 01:30:30', '2020-01-22 01:30:30');

-- --------------------------------------------------------

--
-- Table structure for table `r_request_seminars`
--

CREATE TABLE `r_request_seminars` (
  `RS_ID` int(10) UNSIGNED NOT NULL,
  `RS_TITLE` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `RS_DESCRIPTION` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `RS_CYCLE` int(10) UNSIGNED NOT NULL DEFAULT 1,
  `RS_FEE` double(10,2) NOT NULL,
  `RS_TIME_START` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `RS_TIME_END` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `RS_PLACE` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `RS_DATE_START` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `RS_UNITS` double(10,2) NOT NULL,
  `PR_ID` int(10) UNSIGNED NOT NULL,
  `OR_ID` int(10) UNSIGNED NOT NULL,
  `RS_STATUS` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Requested',
  `RS_REMARKS` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `r_request_seminars`
--

INSERT INTO `r_request_seminars` (`RS_ID`, `RS_TITLE`, `RS_DESCRIPTION`, `RS_CYCLE`, `RS_FEE`, `RS_TIME_START`, `RS_TIME_END`, `RS_PLACE`, `RS_DATE_START`, `RS_UNITS`, `PR_ID`, `OR_ID`, `RS_STATUS`, `RS_REMARKS`, `deleted_at`, `created_at`, `updated_at`) VALUES
(1, 'ISO 9001:2015 Quality Management System Awareness', 'Morbi rutrum porta nulla, in porttitor lorem porta nec. Maecenas leo metus, tristique ac gravida quis, mattis at metus. In dignissim, lorem vitae rutrum maximus, dolor velit rutrum neque, sed luctus dui turpis vitae urna. Cras ex turpis, ornare vitae velit venenatis, blandit eleifend purus. Sed faucibus sapien quis ex dignissim, vitae venenatis ex eleifend. Cras malesuada pharetra purus vel interdum. Sed consectetur quam eu dui venenatis, eu aliquam elit placerat. Nam sagittis nunc non velit dapibus, eu iaculis ante maximus. Praesent eget maximus ante, in tincidunt elit. Aliquam in nisi in est molestie tristique in vitae ex. Donec laoreet et arcu ac imperdiet. Cras aliquam sapien a mattis bibendum. Curabitur felis tellus, venenatis ac volutpat in, rhoncus sed lacus.\r\n\r\nDonec dapibus faucibus neque, at mollis elit ornare et. Nulla posuere ex sit amet tellus consectetur, iaculis tempor ante consequat. Morbi ex mauris, elementum nec lectus convallis, malesuada pulvinar est. Praesent lacinia aliquam justo, non mollis velit tincidunt eget. Donec tristique condimentum purus. Sed nulla urna, feugiat ut nisi eu, pharetra varius odio. Nulla facilisi. In turpis risus, dictum ut iaculis bibendum, lacinia ut erat. In tincidunt, velit quis scelerisque sollicitudin, quam dui molestie arcu, ut luctus turpis lorem a arcu. Duis odio turpis, blandit quis dapibus sed, commodo in justo. Pellentesque non eleifend mauris. Etiam at dolor non ligula lobortis vehicula.', 1, 3500.00, '01:00', '01:00', 'Shangrila Hotel', '2020-01-22', 10.00, 4, 1, 'Approved', NULL, NULL, '2020-01-22 01:41:33', '2020-01-22 01:41:56'),
(2, 'Audio-Visual and Multimedia Collections Specialists', 'Praesent tempus laoreet porta. Pellentesque venenatis erat eget nunc imperdiet tincidunt. Sed hendrerit posuere odio, eu tempus odio eleifend et. Sed vel tincidunt risus. Suspendisse non congue ligula, a auctor dolor. Etiam consectetur a nibh non imperdiet. Mauris pharetra, lectus id fringilla pulvinar, tortor enim tristique libero, eget consequat nunc leo non ante. Etiam sit amet velit libero. Pellentesque et varius lectus. Fusce quis pharetra elit.', 365, 20000.00, '01:00', '01:00', 'Polytechnic University of the Philippine Quezon City', '2020-01-25', 50.00, 4, 1, 'Approved', NULL, NULL, '2020-01-24 03:25:39', '2020-01-24 03:26:06'),
(3, 'Financial Clerk', 'This job type includes bookkeeping, accounting, and auditing clerks. These clerks produce and maintain financial records for companies. There are also financial clerks who perform less specified duties, such as carrying out financial transactions and helping customers. These clerks typically require a high school diploma, while bookkeepers, accountants, and auditing clerks require some post secondary education.', 365, 25000.00, '01:00', '01:00', 'Polytechnic University of the Philippine Quezon City', '2020-03-31', 53.00, 1, 1, 'Approved', NULL, NULL, '2020-03-02 21:47:08', '2020-03-02 21:49:09'),
(4, 'Bill and Account Collector', 'Bill and account collectors help manage and maintain the finances of a company. They receive payments, record financial information, and arrange for payment of overdue bills. They often help debtors find solutions for paying their overdue bills. They might also perform other related clerical duties.', 6, 4644.00, '01:00', '01:00', 'Polytechnic University of the Philippine Quezon City', '2020-03-19', 65.00, 1, 1, 'Approved', NULL, NULL, '2020-03-02 21:48:22', '2020-03-02 21:49:06'),
(5, 'Cyber Security Specialist', 'Any computer connected to the internet is vulnerable to cyber attacks. Cyber security, or IT security, is the technique used to protect computers and networks from criminal intrusion. Specialists in cyber security are among the most sought-after professionals in the tech sector as businesses and governments seek to fight off an increasingly daring and ruthless cohort of global cyber criminals and hackers. Skilled and dedicated security specialists work in this field that demands a mix of artistry and technical expertise. They need to be constantly one step ahead of the hackers and organised criminals behind a new crime wave.', 9, 20000.00, '0', '0', 'Quezon City', '2020-01-22', 0.00, 4, 1, 'Approved', NULL, NULL, '2020-03-19 17:56:55', '2020-03-19 17:58:37'),
(6, 'Data Visualisation Analysts', 'Data visualisation is about presenting large amounts of information in ways that are universally understandable or easy to interpret and spot patterns, trends and correlations. These representations include charts, graphs, infographics and other pictoral diagrams. Data visualisation analysts use visualisation tools and software to communicate information in these ways, for clients or for their own company. You can be a data visualisation specialist but it is often an aspect of a data analyst or data scientist job.', 9, 25000.00, '01:00', '01:00', 'Manila', '2020-01-22', 9.00, 4, 1, 'Approved', NULL, NULL, '2020-03-19 17:58:10', '2020-03-19 17:58:41');

-- --------------------------------------------------------

--
-- Table structure for table `t_approved_seminars`
--

CREATE TABLE `t_approved_seminars` (
  `AS_ID` int(10) UNSIGNED NOT NULL,
  `RS_ID` int(10) UNSIGNED NOT NULL,
  `RVS_ID` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_approved_seminars`
--

INSERT INTO `t_approved_seminars` (`AS_ID`, `RS_ID`, `RVS_ID`, `created_at`, `updated_at`) VALUES
(1, 1, NULL, '2020-01-22 01:41:55', '2020-01-22 01:41:55'),
(2, 2, NULL, '2020-01-24 03:26:06', '2020-01-24 03:26:06'),
(3, 4, NULL, '2020-03-02 21:49:06', '2020-03-02 21:49:06'),
(4, 3, NULL, '2020-03-02 21:49:09', '2020-03-02 21:49:09'),
(5, 5, NULL, '2020-03-19 17:58:37', '2020-03-19 17:58:37'),
(6, 6, NULL, '2020-03-19 17:58:41', '2020-03-19 17:58:41');

-- --------------------------------------------------------

--
-- Table structure for table `t_attendances`
--

CREATE TABLE `t_attendances` (
  `A_ID` int(10) UNSIGNED NOT NULL,
  `id` int(10) UNSIGNED DEFAULT NULL,
  `AS_ID` int(10) UNSIGNED DEFAULT NULL,
  `A_NAME` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `A_IMG` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `A_CPD_VALUE` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRC_ID` int(10) UNSIGNED DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_attendances`
--

INSERT INTO `t_attendances` (`A_ID`, `id`, `AS_ID`, `A_NAME`, `A_IMG`, `A_CPD_VALUE`, `PRC_ID`, `deleted_at`, `created_at`, `updated_at`) VALUES
(17, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(18, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(19, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(21, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_certificates`
--

CREATE TABLE `t_certificates` (
  `TC_ID` int(10) UNSIGNED NOT NULL,
  `TC_TITLE` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `TC_DIR` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `PRC_ID` int(10) UNSIGNED NOT NULL,
  `TC_DATE_EVALUATED` datetime DEFAULT NULL,
  `TC_IS_EVALUATED` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT 'Pending',
  `CPD_UNITS` int(11) DEFAULT 0,
  `ACTIVE_FLAG` int(11) NOT NULL DEFAULT 1,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_certificates`
--

INSERT INTO `t_certificates` (`TC_ID`, `TC_TITLE`, `TC_DIR`, `PRC_ID`, `TC_DATE_EVALUATED`, `TC_IS_EVALUATED`, `CPD_UNITS`, `ACTIVE_FLAG`, `created_at`, `updated_at`) VALUES
(2, 'ds', 'GREENLIGHT/greenlight2/upload/C360_2020-01-27-11-35-51-166.jpg', 6, '2020-06-19 16:16:38', 'Credited', 5, 1, '2020-01-27 10:15:50', '2020-06-19 08:16:38'),
(3, 'gssft', 'GREENLIGHT/greenlight2/upload/580b57fbd9996e24bc43bdf2.png', 6, NULL, 'Pending', 0, 1, '2020-01-27 10:18:28', NULL),
(5, 'My resume', 'upload/My resume.pdf', 7, NULL, 'Pending', 0, 1, '2020-06-19 15:51:42', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_revise_seminars`
--

CREATE TABLE `t_revise_seminars` (
  `RVS_ID` int(10) UNSIGNED NOT NULL,
  `RVS_TITLE` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RVS_DESCRIPTION` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RVS_CYCLE` int(10) UNSIGNED DEFAULT NULL,
  `RVS_FEE` double(10,2) DEFAULT NULL,
  `RVS_TIME_START` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RVS_TIME_END` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RVS_PLACE` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RVS_DATE_START` varchar(199) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `RVS_UNITS` double(10,2) DEFAULT NULL,
  `PR_ID` int(10) UNSIGNED DEFAULT NULL,
  `RS_ID` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(199) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PR_ID` int(10) UNSIGNED DEFAULT NULL,
  `OR_ID` int(10) UNSIGNED DEFAULT NULL,
  `PRD_ID` int(10) UNSIGNED DEFAULT NULL,
  `CURR_UNITS` int(10) UNSIGNED DEFAULT 0,
  `LICENSE_DATE` date DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `role`, `PR_ID`, `OR_ID`, `PRD_ID`, `CURR_UNITS`, `LICENSE_DATE`, `deleted_at`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'John Patrick', 'admin@admin.com', NULL, '$2y$10$BMBnO9SVGT43dI9fOYsCjOJKbeLHwPWwxdJ1CS/mmg.A58Mo2SFOK', 'admin', NULL, NULL, NULL, 0, NULL, NULL, 'woSMi59uuOxjTV9ZWgOUnLYMUqYM1wDO19YavrSGlU1majJsYBZFM5RtvMXv', '2020-01-22 01:28:11', '2020-01-22 01:28:11'),
(2, 'Bryan', 'peso@peso.com', NULL, '$2y$10$BMBnO9SVGT43dI9fOYsCjOJKbeLHwPWwxdJ1CS/mmg.A58Mo2SFOK', 'prc', NULL, NULL, NULL, 0, NULL, NULL, 'xE5gTy4OQJjGItg4OpyRlnb3AoaX47nmNvNIyvbIY5Omyavy2RhsKTUxw8Dy', '2020-01-22 01:28:11', '2020-01-22 01:28:11'),
(3, 'Commits', 'employer@employer.com', NULL, '$2y$10$BMBnO9SVGT43dI9fOYsCjOJKbeLHwPWwxdJ1CS/mmg.A58Mo2SFOK', 'organization', NULL, 1, NULL, 0, NULL, NULL, 'ohuXY4zxu8TGxKMyfMffl4s07geQrTh5fhEWJCO8PKX96mT8IPgqgg0ZClqG', '2020-01-22 01:28:12', '2020-01-22 01:28:12'),
(4, 'Michaela', 'applicant@applicant.com', NULL, '$2y$10$BMBnO9SVGT43dI9fOYsCjOJKbeLHwPWwxdJ1CS/mmg.A58Mo2SFOK', 'professional', 1, NULL, NULL, 0, NULL, NULL, '9tLtLfaKrdb2A5e8cx0sMtPPSuSJgPeR4Q7VqmflKG9f8Q7lw0wIzcsySV5Q', '2020-01-22 01:28:12', '2020-01-22 01:28:12'),
(5, 'Mateus S. Asato', 'gfmic@yahoo.com', NULL, '$2y$10$BMBnO9SVGT43dI9fOYsCjOJKbeLHwPWwxdJ1CS/mmg.A58Mo2SFOK', 'organization', 1, 1, NULL, 0, NULL, NULL, 'v8TpivgUxIqdEfsrSykfWqJp90LDge6b2zg9ek9RsM1iGaHwtLS6h5LYQenX', '2020-01-22 01:44:42', '2020-01-22 01:46:22'),
(6, 'Sn', 'a@a.com', NULL, '1a1dc91c907325c69271ddf0c944bc72', 'professional', 4, NULL, NULL, 10, '2020-01-19', NULL, NULL, NULL, NULL),
(7, 'smav', 'smav@smav.com', NULL, '4175ed09e5d7a30b4893f7302f3184e2', 'professional', 4, NULL, NULL, 9, '2020-02-19', NULL, NULL, NULL, NULL),
(8, 'Severino Natividad', 'accountant@accountant.com', NULL, '56f97f482ef25e2f440df4a424e2ab1e', 'professional', 1, NULL, NULL, 9, '2020-03-03', NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `r_online_seminars`
--
ALTER TABLE `r_online_seminars`
  ADD PRIMARY KEY (`ROS_ID`);

--
-- Indexes for table `r_organizations`
--
ALTER TABLE `r_organizations`
  ADD PRIMARY KEY (`OR_ID`);

--
-- Indexes for table `r_professions`
--
ALTER TABLE `r_professions`
  ADD PRIMARY KEY (`PR_ID`);

--
-- Indexes for table `r_request_seminars`
--
ALTER TABLE `r_request_seminars`
  ADD PRIMARY KEY (`RS_ID`);

--
-- Indexes for table `t_approved_seminars`
--
ALTER TABLE `t_approved_seminars`
  ADD PRIMARY KEY (`AS_ID`);

--
-- Indexes for table `t_attendances`
--
ALTER TABLE `t_attendances`
  ADD PRIMARY KEY (`A_ID`);

--
-- Indexes for table `t_certificates`
--
ALTER TABLE `t_certificates`
  ADD PRIMARY KEY (`TC_ID`);

--
-- Indexes for table `t_revise_seminars`
--
ALTER TABLE `t_revise_seminars`
  ADD PRIMARY KEY (`RVS_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `r_online_seminars`
--
ALTER TABLE `r_online_seminars`
  MODIFY `ROS_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `r_organizations`
--
ALTER TABLE `r_organizations`
  MODIFY `OR_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `r_professions`
--
ALTER TABLE `r_professions`
  MODIFY `PR_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `r_request_seminars`
--
ALTER TABLE `r_request_seminars`
  MODIFY `RS_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `t_approved_seminars`
--
ALTER TABLE `t_approved_seminars`
  MODIFY `AS_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `t_attendances`
--
ALTER TABLE `t_attendances`
  MODIFY `A_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `t_certificates`
--
ALTER TABLE `t_certificates`
  MODIFY `TC_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `t_revise_seminars`
--
ALTER TABLE `t_revise_seminars`
  MODIFY `RVS_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
