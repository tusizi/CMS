@echo off
rem 本批处理文件用于登录mysql，并执行int.bat文件
rem -uroot表示用root 用户名登录，-pleadfar表示使用leadfar作为密码登录
rem 注意：-u和root与-p和leadfar之间不能有空格
mysql -uroot -proot < all.sql