# insert into tbl_permission (id_permission, description, name, id_parent)
# values
#     (1, 'root privileges', 'root', null)
#
#     ,(2, 'manage users', 'user_manager', 1)
#      ,(21, 'add user', 'user_add', 2)
#      ,(22, 'edit user', 'user_edit', 2)
#      ,(23, 'delete user', 'user_del', 2)
#      ,(24, 'list of user', 'user_list', 2)
#      ,(25, 'view user info', 'user_info', 2)
#
#      ,(3, 'manage products', 'product_manager', 1)
#      ,(31, 'add product', 'product_add', 3)
#      ,(32, 'edit product', 'product_edit', 3)
#      ,(33, 'delete product', 'product_del', 3)
#      ,(34, 'list of product', 'product_list', 3)
#      ,(35, 'view product info', 'product_info', 3)
#
#
#      ,(4, 'manage current user', 'current_user_manager', 1)
#      ,(41, 'view current user info', 'current_user_info', 4)
#      ,(42, 'edit current user pass', 'current_user_edit_pass', 4)
# ;
