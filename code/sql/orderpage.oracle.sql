select *
  from (select row_number() over(order by CENTER_DELIVERY_DATE desc) as dr, p.*
          from (SELECT * FROM OB_PO_MAIN) p where p.center_delivery_date > 20140606) x
 where dr between 1 and 10;

先排序，后分页
