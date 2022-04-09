set @uuid=REPLACE(UUID(), '-', '');
-- 菜单
INSERT INTO `sys_menu` (`uuid`,`menu_parent_uuid`, `menu_name`, `menu_url`, `menu_perms`, `menu_type`, `menu_icon`, `menu_order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuid,null, '用户表', 'sys/user', NULL, '1', 'config', '6',now(),now());
-- 按钮
set @uuidinfo=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`menu_parent_uuid`, `menu_name`, `menu_url`, `menu_perms`, `menu_type`, `menu_icon`, `menu_order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuidinfo,@uuid, '查看', null, 'sys:user:page,sys:user:info', '2', null, '6',now(),now());

set @uuidadd=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`menu_parent_uuid`, `menu_name`, `menu_url`, `menu_perms`, `menu_type`, `menu_icon`, `menu_order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuidadd,@uuid, '新增', null, 'sys:user:add', '2', null, '6',now(),now());

set @uuidupdate=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`menu_parent_uuid`, `menu_name`, `menu_url`, `menu_perms`, `menu_type`, `menu_icon`, `menu_order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuidupdate,@uuid, '修改', null, 'sys:user:update', '2', null, '6',now(),now());

set @uuiddel=REPLACE(UUID(), '-', '');
INSERT INTO `sys_menu` (`uuid`,`menu_parent_uuid`, `menu_name`, `menu_url`, `menu_perms`, `menu_type`, `menu_icon`, `menu_order`,`gmt_create`,`gmt_modified`)
    VALUES (@uuiddel,@uuid, '删除', null, 'sys:user:delete', '2', null, '6',now(),now());