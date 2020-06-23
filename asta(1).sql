-- phpMyAdmin SQL Dump
-- version 4.9.2deb1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 24, 2020 at 01:50 AM
-- Server version: 10.3.20-MariaDB-1
-- PHP Version: 7.3.12-1

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

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_jobs` (IN `creator` INT, IN `jobname` VARCHAR(250), IN `jobdesc` VARCHAR(5000), IN `jobroles` VARCHAR(5000), IN `jobrequirements` VARCHAR(5000), IN `startingdate` DATETIME, IN `salary` VARCHAR(250))  BEGIN
insert into r_jobs VALUES( null, creator, jobname, jobdesc, jobroles, jobrequirements,salary, startingdate, CURRENT_TIMESTAMP);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_user` (IN `usertype` INT, IN `fullname` VARCHAR(250), IN `email` VARCHAR(250), IN `userpassword` VARCHAR(250), IN `profession` VARCHAR(1000))  BEGIN
declare checkemail int;

select count(ru_email) into checkemail from r_users where ru_email = email;

if checkemail = 0 then
insert into r_users values( null, usertype, fullname, email, md5(userpassword), profession, CURRENT_TIMESTAMP);
select "1" result;
end if;
if checkemail != 0 THEN
select "0" result;
end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_usertype` (IN `typename` VARCHAR(250))  BEGIN
insert into r_user_type VALUES(null, typename, CURRENT_TIMESTAMP);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getJobDetails` (IN `jobid` INT)  BEGIN
SELECT * from r_jobs where rj_jobid = jobid;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getJobs` ()  BEGIN
select * from r_jobs;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getUserDetails` (IN `userid` INT)  BEGIN
select ru_fullname, ru_email, ru_profession, date(ru_dateadded) ru_dateadded from r_users where ru_userid = userid;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_loginuser` (IN `email` VARCHAR(250), IN `userpassword` VARCHAR(250))  BEGIN
select ru_userid from r_users where ru_email = email and ru_password = md5(userpassword);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_newjob_application` (IN `jobid` INT, IN `applicant` INT, IN `document` VARCHAR(250), IN `sidenote` VARCHAR(5000))  BEGIN
insert into r_job_applications VALUES( null, jobid, applicant, document, sidenote, CURRENT_TIMESTAMP, "Pending For Review");
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_searchjob` (IN `querytext` VARCHAR(250))  BEGIN
select * from r_jobs where rj_jobname like concat('%',querytext,'%') OR
rj_jobdesc  like concat('%',querytext,'%') OR 
rj_jobroles like concat('%',querytext,'%') ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UserJobApplications` (IN `userid` INT)  BEGIN
select * from r_job_applications left join r_jobs on r_jobs.rj_jobid = rja_jobid where r_job_applications.rja_applicant = userid;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `r_jobs`
--

CREATE TABLE `r_jobs` (
  `rj_jobid` int(11) NOT NULL,
  `rj_creator` int(11) NOT NULL,
  `rj_jobname` varchar(250) NOT NULL,
  `rj_jobdesc` varchar(5000) NOT NULL,
  `rj_jobroles` varchar(5000) NOT NULL,
  `rj_requirements` varchar(5000) NOT NULL,
  `rj_salary` varchar(250) NOT NULL,
  `rj_startingdate` date NOT NULL DEFAULT current_timestamp(),
  `rj_datesubmitted` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `r_jobs`
--

INSERT INTO `r_jobs` (`rj_jobid`, `rj_creator`, `rj_jobname`, `rj_jobdesc`, `rj_jobroles`, `rj_requirements`, `rj_salary`, `rj_startingdate`, `rj_datesubmitted`) VALUES
(1, 1, 'Taga Hugas ng Pinggan', 'Taga Hugas ng Pinggan kina Keith', 'Hugas kalang tapos magsasaing ganun', 'madami requiremetns, dapat marunong ka magdilig', '', '2020-06-24', '2020-06-23 00:00:00'),
(2, 1, 'Administrative Officer III', 'Deadline for accepting applications : July 02, 2020\r\nOnly those with Civil Service Eligibility will be processed.  Kindly upload your Certificate of Eligibility or PRC License.\r\nPlace of Assignment : Tawi-Tawi ARMM\r\n\r\nPlantilla Item No. : PAD-02\r\n\r\nSalary/Job/Pay Grade : 18\r\n\r\n', 'An Administrative Officer is responsible to provide administrative support to an organization. Some duties include inventory management, organizing company records, roster scheduling, budget and office reporting, invoicing and customer service.', 'Instruction/Remarks :\r\n\r\nInterested and qualified applicants should signify their interest in writing. Attach the following documents to the application letter and send to the address below not later than July 02, 2020.\r\nDocuments:\r\n\r\n    Fully accomplished Personal Data Sheet (PDS) with recent passport-sized picture (CS Form No. 212, Revised 2017) which can be downloaded at www.csc.gov.ph;\r\n    Performance rating in the last rating period (if applicable);\r\n    Photocopy of certificate of eligibility/rating/license; and\r\n    Photocopy of Transcript of Records.', 'Php 36,573.00', '2020-06-02', '2020-06-23 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `r_job_applications`
--

CREATE TABLE `r_job_applications` (
  `rja_applicationid` int(11) NOT NULL,
  `rja_jobid` int(11) NOT NULL,
  `rja_applicant` int(11) NOT NULL,
  `rja_document` varchar(250) NOT NULL,
  `rja_sidenote` varchar(5000) NOT NULL,
  `rja_datesubmitted` datetime NOT NULL DEFAULT current_timestamp(),
  `rja_status` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `r_job_applications`
--

INSERT INTO `r_job_applications` (`rja_applicationid`, `rja_jobid`, `rja_applicant`, `rja_document`, `rja_sidenote`, `rja_datesubmitted`, `rja_status`) VALUES
(1, 2, 7, 'please kunin niyo nako alaws ako work huhu', 'upload/27.pdf', '2020-06-23 23:50:59', 'Pending For Review'),
(2, 2, 8, 'Im very good to be your dev, i can provide you everything hehe.', 'upload/28.pdf', '2020-06-24 01:46:53', 'Application Approved');

-- --------------------------------------------------------

--
-- Table structure for table `r_users`
--

CREATE TABLE `r_users` (
  `ru_userid` int(11) NOT NULL,
  `ru_usertype` int(11) NOT NULL,
  `ru_fullname` varchar(250) NOT NULL,
  `ru_email` varchar(250) NOT NULL,
  `ru_password` varbinary(250) NOT NULL,
  `ru_profession` varchar(250) NOT NULL,
  `ru_dateadded` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `r_users`
--

INSERT INTO `r_users` (`ru_userid`, `ru_usertype`, `ru_fullname`, `ru_email`, `ru_password`, `ru_profession`, `ru_dateadded`) VALUES
(1, 1, 'a', 'a', 0x3063633137356239633066316236613833316333393965323639373732363631, 'a', '2020-06-23 18:31:54'),
(2, 1, 'a', 'aa', 0x3063633137356239633066316236613833316333393965323639373732363631, 'a', '2020-06-23 18:32:21'),
(3, 1, 'a', 'aaaa', 0x3063633137356239633066316236613833316333393965323639373732363631, 'a', '2020-06-23 20:02:34'),
(6, 1, 'e', 'e', 0x6531363731373937633532653135663736333338306234356538343165633332, 'e', '2020-06-23 21:21:39'),
(7, 1, 'Keith Eyvan Alvior', 'alviorkeitheyvan@gmail.com', 0x3566346463633362356161373635643631643833323764656238383263663939, 'IT practitioner', '2020-06-23 21:27:38'),
(8, 1, 'Rodel Duterte', 'rodel@gmail.com', 0x3566346463633362356161373635643631643833323764656238383263663939, 'IT Practitioner Programmer', '2020-06-24 01:45:19');

-- --------------------------------------------------------

--
-- Table structure for table `r_user_type`
--

CREATE TABLE `r_user_type` (
  `rut_typeid` int(11) NOT NULL,
  `rut_typename` varchar(250) NOT NULL,
  `rut_dateadded` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `r_user_type`
--

INSERT INTO `r_user_type` (`rut_typeid`, `rut_typename`, `rut_dateadded`) VALUES
(1, 'regular_users', '2020-06-23 18:30:54');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `r_jobs`
--
ALTER TABLE `r_jobs`
  ADD PRIMARY KEY (`rj_jobid`),
  ADD KEY `rj_creator` (`rj_creator`);

--
-- Indexes for table `r_job_applications`
--
ALTER TABLE `r_job_applications`
  ADD PRIMARY KEY (`rja_applicationid`),
  ADD KEY `rja_jobid` (`rja_jobid`),
  ADD KEY `rja_applicant` (`rja_applicant`);

--
-- Indexes for table `r_users`
--
ALTER TABLE `r_users`
  ADD PRIMARY KEY (`ru_userid`),
  ADD KEY `ru_usertype` (`ru_usertype`);

--
-- Indexes for table `r_user_type`
--
ALTER TABLE `r_user_type`
  ADD PRIMARY KEY (`rut_typeid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `r_jobs`
--
ALTER TABLE `r_jobs`
  MODIFY `rj_jobid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `r_job_applications`
--
ALTER TABLE `r_job_applications`
  MODIFY `rja_applicationid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `r_users`
--
ALTER TABLE `r_users`
  MODIFY `ru_userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `r_user_type`
--
ALTER TABLE `r_user_type`
  MODIFY `rut_typeid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `r_jobs`
--
ALTER TABLE `r_jobs`
  ADD CONSTRAINT `r_jobs_ibfk_1` FOREIGN KEY (`rj_creator`) REFERENCES `r_users` (`ru_userid`);

--
-- Constraints for table `r_job_applications`
--
ALTER TABLE `r_job_applications`
  ADD CONSTRAINT `r_job_applications_ibfk_1` FOREIGN KEY (`rja_jobid`) REFERENCES `r_jobs` (`rj_jobid`),
  ADD CONSTRAINT `r_job_applications_ibfk_2` FOREIGN KEY (`rja_applicant`) REFERENCES `r_users` (`ru_userid`);

--
-- Constraints for table `r_users`
--
ALTER TABLE `r_users`
  ADD CONSTRAINT `r_users_ibfk_1` FOREIGN KEY (`ru_usertype`) REFERENCES `r_user_type` (`rut_typeid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
