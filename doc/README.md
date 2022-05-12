
# TODO

- 设计文档
  
- ~~CODE GEN 应当独立出来~~

- fix前端选择角色菜单下拉列表

- 节点执行日志推送设计

> - 当前节点执行时通过ssh进行执行开启管道读取流，一方面存储流，另一方面将其转发到前端socket

- 机器管理模块添加标签或者分组，可设置为builder机器且进行初始化


```sql
use information_schema;

select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables;

select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema='cicd-platform';

select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema='cicd-platform' and table_name='pipeline_logger';
select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema='cicd-platform' and table_name='pipeline_node_logger';

use `cicd-platform`;
truncate pipeline_node_logger;
truncate pipeline_logger;
```