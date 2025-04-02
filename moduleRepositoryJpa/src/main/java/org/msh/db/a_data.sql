
insert into tbl_color (id_color, color, hex) values(1, "white","#FFF");
insert into tbl_color (id_color, color, hex) values(2, "black","#000");

insert into tbl_product (id_product, sku, brand, model, price)
values (1,"s1", "b1", "m1", 1000);
insert into tbl_product (id_product, sku, brand, model, price)
values (2,"s2", "b2", "m2", 2000);


insert into tbl_rel_product_color (id_product, id_color)
values (1,1);
insert into tbl_rel_product_color (id_product, id_color)
values (2,2);
insert into tbl_rel_product_color (id_product, id_color)
values (2,1);
