
CREATE DATABASE /*!32312 IF NOT EXISTS*/`extra_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `tab_phones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `tab_phones`(`id`,`phone`) values (1,'13340650367');

