insert into tbl_permission (id_permission, id_parent, description, name)
values
    (1, null, 'root privileges', 'root')

#  :::::::  user
     ,(10, 1, 'manage users', 'user_mng')
     ,(101, 10, 'add user', 'user_add')
     ,(102, 10, 'update user', 'user_upd')
     ,(103, 10, 'delete user', 'user_del')
     ,(104, 10, 'list of user', 'user_lst')
     ,(105, 10, 'view user info', 'user_inf')
     ,(106, 10, 'update user pass', 'user_upd_pass_by_admin')
     ,(107, 10, 'update user pass', 'user_upd_pass_by_user')
     ,(108, 10, 'update user profile', 'user_upd_profile')

     ,(11, 1, 'manage customers', 'customer_mng')
     ,(111, 11, 'add customer', 'customer_add')
     ,(112, 11, 'update customer', 'customer_upd')
     ,(113, 11, 'delete customer', 'customer_del')
     ,(114, 11, 'list of customer', 'customer_lst')
     ,(115, 11, 'view customer info', 'customer_inf')

     ,(12, 1, 'manage dashboards', 'dashboard_mng')
     ,(121, 12, 'add dashboard', 'dashboard_add')
     ,(122, 12, 'update dashboard', 'dashboard_upd')
     ,(123, 12, 'delete dashboard', 'dashboard_del')
     ,(124, 12, 'list of dashboard', 'dashboard_lst')
     ,(125, 12, 'view dashboard info', 'dashboard_inf')

#      ,(13,  13, 'manage current user', 'current_user_mng')
#      ,(131, 13,  'view current user info', 'current_user_inf')
#      ,(132, 13,  'update current user pass', 'current_user_upd_pass')



#  :::::::  product
     ,(20, 1, 'manage products', 'product_mng')
     ,(201, 20, 'add product', 'product_add')
     ,(202, 20, 'update product', 'product_upd')
     ,(203, 20, 'delete product', 'product_del')
     ,(204, 20, 'list of product', 'product_lst')
     ,(205, 20, 'view product info', 'product_inf')

     ,(21, 1, 'manage product categories', 'category_mng')
     ,(211, 21, 'add product category', 'category_add')
     ,(212, 21, 'update product category', 'category_upd')
     ,(213, 21, 'delete product category', 'category_del')
     ,(214, 21, 'list of product category', 'category_lst')
     ,(215, 21, 'view product category info', 'category_inf')

     ,(22, 1, 'manage colors', 'color_mng')
     ,(221, 22, 'add color', 'color_add')
     ,(222, 22, 'update color', 'color_upd')
     ,(223, 22, 'delete color', 'color_del')
     ,(224, 22, 'list of color', 'color_lst')
     ,(225, 22, 'view color info', 'color_inf')

     ,(23, 1, 'manage sizes', 'size_mng')
     ,(231, 23, 'add size', 'size_add')
     ,(232, 23, 'update size', 'size_upd')
     ,(233, 23, 'delete size', 'size_del')
     ,(234, 23, 'list of size', 'size_lst')
     ,(235, 23, 'view size info', 'size_inf')

     ,(24, 1, 'manage invoices/order', 'invoice_order_mng')
     ,(241, 24, 'add invoice/order', 'invoice_order_add')
     ,(242, 24, 'update invoice/order', 'invoice_order_upd')
     ,(243, 24, 'delete invoice/order', 'invoice_order_del')
     ,(244, 24, 'list of invoice/order', 'invoice_order_lst')
     ,(245, 24, 'view invoice/order info', 'invoice_order_inf')

     ,(25, 1, 'manage my invoices/factor', 'invoice_factor_mng')
     ,(251, 25, 'add my invoice/factor', 'invoice_factor_add')
     ,(252, 25, 'update my invoice/factor', 'invoice_factor_upd')
     ,(253, 25, 'delete my invoice/factor', 'invoice_factor_del')
     ,(254, 25, 'list of my invoice/factor', 'invoice_factor_lst')
     ,(255, 25, 'view my invoice/factor info', 'invoice_factor_inf')




#  :::::::  site
     ,(30, 1, 'manage files', 'file_mng')
     ,(301, 30, 'add file', 'file_add')
     ,(302, 30, 'update file', 'file_upd')
     ,(303, 30, 'delete file', 'file_del')
     ,(304, 30, 'list of file', 'file_lst')
     ,(305, 30, 'view file info', 'file_inf')
     ,(306, 30, 'upload file', 'file_upl')

     ,(31, 1, 'manage blogs', 'blog_mng')
     ,(311, 31, 'add blog', 'blog_add')
     ,(312, 31, 'update blog', 'blog_upd')
     ,(313, 31, 'delete blog', 'blog_del')
     ,(314, 31, 'list of blog', 'blog_lst')
     ,(315, 31, 'view blog info', 'blog_inf')

     ,(32, 1, 'manage navs', 'nav_mng')
     ,(321, 32, 'add nav', 'nav_add')
     ,(322, 32, 'update nav', 'nav_upd')
     ,(323, 32, 'delete nav', 'nav_del')
     ,(324, 32, 'list of nav', 'nav_lst')
     ,(325, 32, 'view nav info', 'nav_inf')

     ,(33, 1, 'manage contents', 'content_mng')
     ,(331, 33, 'add content', 'content_add')
     ,(332, 33, 'update content', 'content_upd')
     ,(333, 33, 'delete content', 'content_del')
     ,(334, 33, 'list of content', 'content_lst')
     ,(335, 33, 'view content info', 'content_inf')

     ,(34, 1, 'manage sliders', 'slider_mng')
     ,(341, 34, 'add slider', 'slider_add')
     ,(342, 34, 'update slider', 'slider_upd')
     ,(343, 34, 'delete slider', 'slider_del')
     ,(344, 34, 'list of slider', 'slider_lst')
     ,(345, 34, 'view slider info', 'slider_inf')

;
