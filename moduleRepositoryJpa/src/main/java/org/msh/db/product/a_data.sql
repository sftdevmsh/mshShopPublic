#
# insert into tbl_color (id_color, name, hex) values(1, "white","#FFF");
# insert into tbl_color (id_color, name, hex) values(2, "black","#000");
#
# insert into tbl_product
#     (id_product, title, price, add_date, description, enabled, exist, visit_count, img_id_file, id_product_category)
#     values (1,"t1",1000, now(),"d1", true, true, 10, 1, 1)
#     , (2,"t2",2000, now(),"d2", true, true, 20, 1, 1);
#
#
#
# insert into rel_product_color (id_product, id_color)
# values (1,1);
# insert into rel_product_color (id_product, id_color)
# values (2,2);
# insert into rel_product_color (id_product, id_color)
# values (2,1);
