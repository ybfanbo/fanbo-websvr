
CREATE DATABASE /*!32312 IF NOT EXISTS*/`websvr_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `tab_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `tab_users`(`id`,`username`,`password`,`email`,`phone`,`create_date`,`update_date`) values (1,'fanbo','1','791659752@qq.com','13340650367','2018-09-04 19:02:43','2018-07-17 01:35:55'),(2,'bfan','123456','237049285@qq.com','18509548785','2018-09-03 20:06:44','2018-07-17 02:38:55'),(3,'test','1','1@qq.com','12545879854','2018-09-10 10:59:49','2018-07-19 17:32:15');
