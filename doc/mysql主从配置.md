#### docker创建mysql主从（一主一从）
```
docker-mysql
├── master
│   ├── conf
│   │   └── my.cnf
│   └── data
└── slaver
    ├── conf
    │   └── my.cnf
    └── data
```

- 创建master
```docker run --name mastermysql -d -p 3316:3306 -e MYSQL_ROOT_PASSWORD=root -v E:/sharing-jdbc/docker-mysql/master/data:/var/lib/mysql -v E:/sharing-jdbc/docker-mysql/master/conf/my.cnf:/etc/mysql/my.cnf  mysql:5.7```
```docker run --name mastermysql -d -p 3316:3306 -e MYSQL_ROOT_PASSWORD=root -v E:/sharing-jdbc/docker-mysql/master/data:/var/lib/mysql -v E:/sharing-jdbc/docker-mysql/master/conf/my.cnf:/etc/mysql/my.cnf  mysql:5.7```

- 创建slave
```docker run --name slavermysql -d -p 3326:3306 -e MYSQL_ROOT_PASSWORD=root -v E:/sharing-jdbc/docker-mysql/slaver/data:/var/lib/mysql -v E:/sharing-jdbc/docker-mysql/slaver/conf/my.cnf:/etc/mysql/my.cnf  mysql:5.7```
- 进入master创建账号
```
#创建用于主从复制的账号db_sync，密码db_sync
create user 'db_sync'@'%' identified with mysql_native_password by 'db_sync';
#授权
grant replication slave on *.* to 'db_sync'@'%';
#刷新权限
FLUSH PRIVILEGES;
#记者file和position的值,需要在slave用到 mysql-bin.000001 |      753 
show master status;   
```
- 登录slave服务器进行配置
注意
执行:
```
STOP SLAVE;
CHANGE MASTER TO master_host = '172.18.0.2',master_port = 3306,master_user = 'db_sync',master_password = 'db_sync',master_log_file = 'mysql-bin.000001',master_log_pos = 753;
START SLAVE;
```
查看是否成功
```
show slave status \G;
```
看到这两项即为成功
```Slave_IO_Running: Yes Slave_SQL_Running: Yes```
#### 配置文件被忽略的问题:
进入容器修改cnf文件权限为044