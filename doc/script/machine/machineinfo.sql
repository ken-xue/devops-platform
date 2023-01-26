set @uuid=REPLACE(UUID(), '-', '');
-- 菜单
INSERT INTO `sys_menu` (`uuid`,`parent_uuid`, `name`, `url`, `method`, `type`, `icon`, `order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuid,null, '服务器节点', '/machine/machineinfo', NULL, '1', 'component', '6',now(),now());
-- 按钮
set @uuidinfo=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`parent_uuid`, `name`, `api`, `method`, `type`, `icon`, `order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuidinfo,@uuid, '查看', '/machine/machineinfo/info', 'get', '2', null, '6',now(),now());

set @uuidadd=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`parent_uuid`, `name`, `api`, `method`, `type`, `icon`, `order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuidadd,@uuid, '新增', '/machine/machineinfo/add', 'post', '2', null, '6',now(),now());

set @uuidupdate=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`parent_uuid`, `name`, `api`, `method`, `type`, `icon`, `order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuidupdate,@uuid, '修改', '/machine/machineinfo/update', 'put', '2', null, '6',now(),now());

set @uuiddel=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`parent_uuid`, `name`, `api`, `method`, `type`, `icon`, `order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuiddel,@uuid, '删除', '/machine/machineinfo/delete', 'delete', '2', null, '6',now(),now());