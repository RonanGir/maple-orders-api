-- clean fake data which does not respect the constraint
alter table cart_item
    add constraint unique_product_cart unique (product_id, cart_id);



