insert into tbl_color (id_color, hex, name)
values (1,'FFF','n1')
, (2,'000','n2')
;


insert into tbl_size (id_size, description, name)
values (1,'d1','n1')
, (2,'d2','n2')
;


insert into rel_product_color (id_product, id_color)
values (1,1)
, (2,2);


insert into rel_product_size (id_product, id_size)
values (1,1)
, (2,2);