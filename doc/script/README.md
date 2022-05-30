```sql
use information_schema;
select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables;
select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema='devops-platform';
select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema='devops-platform' and table_name='pipeline_logger';
select concat(round(sum(data_length/1024/1024),2),'MB') as data from tables where table_schema='devops-platform' and table_name='pipeline_node_logger';
use `devops-platform`;
truncate pipeline_node_logger;
truncate pipeline_logger;
```