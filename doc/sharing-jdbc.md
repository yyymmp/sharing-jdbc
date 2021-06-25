#### 垂直分表
充分发挥热门数据的操作效率，高效率字段不会被低效率字段所拖累
- 把不常用的字段单独放在一张表
- 把text、blob等大字段拆分出来放在附表中
- 经常组合查询的列放在一种表中
#### 垂直分库
根据业务划分，将数据表分布到不同的数据库中，每个数据库的业务独立

#### 公共表
实际的应用场景中，参数表、数据字典表（固定表）等都是数据量较小，变动少，而且属于高频联合查询的依赖表，例如地址区域表。
可以将这类表在每个数据库都保存一份，**所有对公共表的更新操作都同时发送到所有分库执行。**

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

- 创建slave
```docker run --name slavermysql -d -p 3326:3306 -e MYSQL_ROOT_PASSWORD=root -v E:/sharing-jdbc/docker-mysql/slaver/data:/var/lib/mysql -v E:/sharing-jdbc/docker-mysql/slaver/conf/my.cnf:/etc/mysql/my.cnf  mysql:5.7```
