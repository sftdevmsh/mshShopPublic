insert into tbl_permission (id_permission, id_parent, description, name)
values
    (1, null, 'root privileges', 'root')

#  :::::::  user
     ,(10, 1, 'manage users', 'user_manager')
     ,(101, 10, 'add user', 'user_add')
     ,(102, 10, 'edit user', 'user_edit')
     ,(103, 10, 'delete user', 'user_del')
     ,(104, 10, 'list of user', 'user_list')
     ,(105, 10, 'view user info', 'user_info')

     ,(11, 1, 'manage customers', 'customer_manager')
     ,(111, 11, 'add customer', 'customer_add')
     ,(112, 11, 'edit customer', 'customer_edit')
     ,(113, 11, 'delete customer', 'customer_del')
     ,(114, 11, 'list of customer', 'customer_list')
     ,(115, 11, 'view customer info', 'customer_info')

     ,(12, 1, 'manage dashboards', 'dashboard_manager')
     ,(121, 12, 'add dashboard', 'dashboard_add')
     ,(122, 12, 'edit dashboard', 'dashboard_edit')
     ,(123, 12, 'delete dashboard', 'dashboard_del')
     ,(124, 12, 'list of dashboard', 'dashboard_list')
     ,(125, 12, 'view dashboard info', 'dashboard_info')

#      ,(13,  13, 'manage current user', 'current_user_manager')
#      ,(131, 13,  'view current user info', 'current_user_info')
#      ,(132, 13,  'edit current user pass', 'current_user_edit_pass')



#  :::::::  product
     ,(20, 1, 'manage products', 'product_manager')
     ,(201, 20, 'add product', 'product_add')
     ,(202, 20, 'edit product', 'product_edit')
     ,(203, 20, 'delete product', 'product_del')
     ,(204, 20, 'list of product', 'product_list')
     ,(205, 20, 'view product info', 'product_info')

     ,(21, 1, 'manage product categories', 'category_manager')
     ,(211, 21, 'add product category', 'category_add')
     ,(212, 21, 'edit product category', 'category_edit')
     ,(213, 21, 'delete product category', 'category_del')
     ,(214, 21, 'list of product category', 'category_list')
     ,(215, 21, 'view product category info', 'category_info')

     ,(22, 1, 'manage colors', 'color_manager')
     ,(221, 22, 'add color', 'color_add')
     ,(222, 22, 'edit color', 'color_edit')
     ,(223, 22, 'delete color', 'color_del')
     ,(224, 22, 'list of color', 'color_list')
     ,(225, 22, 'view color info', 'color_info')

     ,(23, 1, 'manage sizes', 'size_manager')
     ,(231, 23, 'add size', 'size_add')
     ,(232, 23, 'edit size', 'size_edit')
     ,(233, 23, 'delete size', 'size_del')
     ,(234, 23, 'list of size', 'size_list')
     ,(235, 23, 'view size info', 'size_info')

     ,(24, 1, 'manage invoices/orders', 'invoice_manager')
     ,(241, 24, 'add invoice/order', 'invoice_add')
     ,(242, 24, 'edit invoice/order', 'invoice_edit')
     ,(243, 24, 'delete invoice/order', 'invoice_del')
     ,(244, 24, 'list of invoice/order', 'invoice_list')
     ,(245, 24, 'view invoice/order info', 'invoice_info')

     ,(25, 1, 'manage my invoices/factors', 'my_invoice_manager')
     ,(251, 25, 'add my invoice/factor', 'my_invoice_add')
     ,(252, 25, 'edit my invoice/factor', 'my_invoice_edit')
     ,(253, 25, 'delete my invoice/factor', 'my_invoice_del')
     ,(254, 25, 'list of my invoice/factor', 'my_invoice_list')
     ,(255, 25, 'view my invoice/factor info', 'my_invoice_info')




#  :::::::  site
     ,(30, 1, 'manage files', 'file_manager')
     ,(301, 30, 'add file', 'file_add')
     ,(302, 30, 'edit file', 'file_edit')
     ,(303, 30, 'delete file', 'file_del')
     ,(304, 30, 'list of file', 'file_list')
     ,(305, 30, 'view file info', 'file_info')

     ,(31, 1, 'manage blogs', 'blog_manager')
     ,(311, 31, 'add blog', 'blog_add')
     ,(312, 31, 'edit blog', 'blog_edit')
     ,(313, 31, 'delete blog', 'blog_del')
     ,(314, 31, 'list of blog', 'blog_list')
     ,(315, 31, 'view blog info', 'blog_info')

     ,(32, 1, 'manage navs', 'nav_manager')
     ,(321, 32, 'add nav', 'nav_add')
     ,(322, 32, 'edit nav', 'nav_edit')
     ,(323, 32, 'delete nav', 'nav_del')
     ,(324, 32, 'list of nav', 'nav_list')
     ,(325, 32, 'view nav info', 'nav_info')

     ,(33, 1, 'manage contents', 'content_manager')
     ,(331, 33, 'add content', 'content_add')
     ,(332, 33, 'edit content', 'content_edit')
     ,(333, 33, 'delete content', 'content_del')
     ,(334, 33, 'list of content', 'content_list')
     ,(335, 33, 'view content info', 'content_info')

     ,(34, 1, 'manage sliders', 'slider_manager')
     ,(341, 34, 'add slider', 'slider_add')
     ,(342, 34, 'edit slider', 'slider_edit')
     ,(343, 34, 'delete slider', 'slider_del')
     ,(344, 34, 'list of slider', 'slider_list')
     ,(345, 34, 'view slider info', 'slider_info')

;
