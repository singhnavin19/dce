CREATE TABLE `tenquiry` (
  `first_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `profession` varchar(250) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `mobile_no` varchar(10) DEFAULT NULL,
  `email_id` varchar(50) DEFAULT NULL,
  `course` varchar(250) DEFAULT NULL,
  `fess` varchar(50) DEFAULT NULL,
  `dateOfEnquiry` date DEFAULT NULL,
  `temp_id` varchar(10) NOT NULL DEFAULT '',
  `srno` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`srno`,`temp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `tadmission` (
		  `uid` varchar(10) DEFAULT NULL,
		  `srno` int(11) NOT NULL AUTO_INCREMENT,
		  `first_name` varchar(50) DEFAULT NULL,
		  `middle_name` varchar(50) DEFAULT NULL,
		  `last_name` varchar(50) DEFAULT NULL,
		  `gender` varchar(50) DEFAULT NULL,
		  `qualification` varchar(255) DEFAULT NULL,
		  `profession` varchar(250) DEFAULT NULL,
		  `address` varchar(250) DEFAULT NULL,
		  `mobile_no` varchar(10) DEFAULT NULL,
		  `email_id` varchar(50) DEFAULT NULL,
		  `dateOfAdmission` date DEFAULT NULL,
		  temp_id varchar(10),
		  PRIMARY KEY (`srno`,uid)
		) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `tfees_details` (
  `uid` varchar(10) DEFAULT NULL,
  `installment` int(11) DEFAULT NULL,
  `Due_date` date DEFAULT NULL,
  `Paid_date` date DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `Receipt_No` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `tcourse_details` (
  `uid` varchar(10) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `fee_per_course` int(11) DEFAULT NULL,
  `batch_time` varchar(10) DEFAULT NULL,
  `duration` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tattendance` 
(
		  `uid` varchar(10) NOT NULL,
		  `fullName` varchar(500) DEFAULT NULL,
		  `Course` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tattendance_daily` 
(
		  `uid` varchar(10) DEFAULT NULL,
		  `attendece_date` date DEFAULT NULL,
		  `check_in_datetime` datetime DEFAULT NULL,
		  `check_out_datetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
