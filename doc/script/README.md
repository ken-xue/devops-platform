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