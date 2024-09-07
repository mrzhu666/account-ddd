-- 在docker里初始化表，idea和datagrip查看表的注释乱码，但是进入docker容器里查看的时候没有乱码，不知道为什么。
-- 解决方法：在idea和datagrip里运行建表代码。
-- 多个初始化表
-- 用户
source /opt/sql/user.sql;