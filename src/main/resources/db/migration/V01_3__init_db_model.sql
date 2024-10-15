-- create tables
create table public.customer
(
    id         bigint                      not null primary key,
    name       varchar(255),
    created_at timestamp(6) with time zone not null,
    updated_at timestamp(6) with time zone not null
);

INSERT INTO public.customer (created_at, id, updated_at, name)
VALUES ('2023-01-15 10:45:23+00', 1, '2023-02-10 15:32:11+00', 'John Doe'),
       ('2023-03-12 08:20:45+00', 2, '2023-04-01 12:05:33+00', 'Jane Smith'),
       ('2023-05-10 14:10:10+00', 3, '2023-05-15 09:22:14+00', 'Alice Johnson'),
       ('2023-06-18 09:55:02+00', 4, '2023-07-01 16:43:19+00', 'Bob Williams'),
       ('2023-08-22 11:30:48+00', 5, '2023-09-05 14:01:37+00', 'Emily Davis');

alter sequence customer_seq restart with 6;


create table public.cart
(
    id          bigint                      not null primary key,
    quantity    integer                     not null,
    total_price real                        not null,
    created_at  timestamp(6) with time zone not null,
    updated_at  timestamp(6) with time zone not null,
    status      varchar(255)                not null
        constraint cart_status_check
            check ((status)::text = ANY
                   ((ARRAY ['OPENED'::character varying, 'PENDING'::character varying, 'CLOSED'::character varying])::text[])),
    customer_id bigint                      not null
        constraint fk_cart_customer_id references public.customer
);

INSERT INTO public.cart (quantity, total_price, created_at, id, updated_at, customer_id, status)
VALUES (3, 59.99, '2023-09-01 11:25:33+00', 1, '2023-09-02 13:45:15+00', 1, 'OPENED'),
       (1, 15.49, '2023-09-10 09:10:12+00', 2, '2023-09-11 11:12:22+00', 2, 'PENDING'),
       (5, 120.00, '2023-10-05 14:25:45+00', 3, '2023-10-06 10:35:40+00', 3, 'CLOSED'),
       (2, 45.89, '2023-08-15 08:50:20+00', 4, '2023-08-16 12:21:55+00', 4, 'OPENED'),
       (4, 75.60, '2023-07-22 16:45:33+00', 5, '2023-07-23 17:55:12+00', 5, 'CLOSED');


alter sequence cart_seq restart with 6;

create table public.product
(
    id           bigint                      not null primary key,
    max_quantity integer,
    price        numeric(10, 2),
    stock        integer,
    created_at   timestamp(6) with time zone not null,
    updated_at   timestamp(6) with time zone not null,
    description  varchar(255),
    img          varchar(255),
    name         varchar(255),
    type         varchar(255)
        constraint product_type_check
            check ((type)::text = ANY
                   ((ARRAY ['AMBER'::character varying, 'DARK'::character varying, 'CLEAR'::character varying])::text[]))
);

INSERT INTO public.product (max_quantity, price, stock, created_at, id, updated_at, description, img, name, type)
VALUES (100, 13.5, 50, '2023-01-15 10:00:00+00', 1, '2023-01-16 09:30:00+00', 'Sirop d''érable pur du Vermont.',
        'maple_syrup_amber.jpg', 'Sirop d''érable ambré du Vermont', 'AMBER'),
       (200, 15, 75, '2023-02-10 11:30:00+00', 2, '2023-02-11 10:45:00+00',
        'Sirop d''érable noir riche, idéal pour la pâtisserie', 'maple_syrup_dark.jpg', 'Sirop d''érable noir canadien',
        'DARK'),
       (150, 10.25, 30, '2023-03-05 14:20:00+00', 3, '2023-03-06 13:50:00+00',
        'Sirop d''érable clair et léger, parfait pour les crêpes.', 'maple_syrup_clear.jpg',
        'Sirop d''érable clair du New Hampshire', 'CLEAR');

alter sequence product_seq restart with 6;

create table public.cart_item
(
    id          bigint                      not null primary key,
    quantity    integer                     not null,
    total_price numeric(10, 2)              not null,
    created_at  timestamp(6) with time zone not null,
    updated_at  timestamp(6) with time zone not null,
    status      varchar(255)                not null
        constraint cart_item_status_check
            check ((status)::text = ANY
                   ((ARRAY ['OPENED'::character varying, 'PENDING'::character varying, 'CLOSED'::character varying])::text[])),
    cart_id     bigint                      not null
        constraint fk_cart_item_cart_id references public.cart,
    product_id  bigint                      not null
        constraint fk_cart_item_product_id references public.product,
    customer_id bigint                      not null
        constraint fk_cart_item_customer_id references public.customer
);

INSERT INTO public.cart_item (quantity, total_price, cart_id, created_at, id, product_id, updated_at, customer_id,
                              status)
VALUES (2, 27, 1, '2023-09-01 12:30:45+00', 1, 1, '2023-09-02 09:20:33+00', 1, 'OPENED'),
       (1, 15, 2, '2023-09-10 10:45:12+00', 2, 2, '2023-09-11 11:12:22+00', 2, 'PENDING'),
       (3, 30.75, 3, '2023-10-05 14:40:20+00', 3, 3, '2023-10-06 12:20:40+00', 3, 'CLOSED'),
       (1, 13.5, 4, '2023-08-15 09:50:35+00', 4, 1, '2023-08-16 13:21:00+00', 4, 'OPENED'),
       (2, 30, 5, '2023-07-22 17:25:12+00', 5, 2, '2023-07-23 18:10:55+00', 5, 'CLOSED');

alter sequence cart_item_seq restart with 6;

create table public.purchase_order
(
    id          bigint                      not null primary key,
    quantity    integer                     not null,
    total_price numeric(10, 2)              not null,
    created_at  timestamp(6) with time zone not null,
    updated_at  timestamp(6) with time zone not null,
    status      varchar(255)                not null
        constraint purchase_order_status_check
            check ((status)::text = ANY
                   ((ARRAY ['OPENED'::character varying, 'PENDING'::character varying, 'CLOSED'::character varying])::text[])),
    customer_id bigint                      not null
        constraint fk_purchase_order_customer_id references public.customer
);

INSERT INTO public.purchase_order (quantity, total_price, created_at, id, updated_at, customer_id, status)
VALUES (2, 27, '2023-09-12 10:20:30+00', 1, '2023-09-13 14:30:25+00', 1, 'PENDING'),
       (1, 15, '2023-09-20 12:15:45+00', 2, '2023-09-21 11:25:50+00', 2, 'OPENED'),
       (3, 30.75, '2023-10-02 16:05:12+00', 3, '2023-10-03 18:10:33+00', 3, 'CLOSED'),
       (1, 13.5, '2023-08-05 09:35:50+00', 4, '2023-08-06 10:50:20+00', 4, 'OPENED'),
       (2, 30, '2023-07-18 14:25:33+00', 5, '2023-07-19 16:45:12+00', 5, 'CLOSED');

alter sequence purchase_order_seq restart with 6;

create table public.purchase_order_item
(
    id          bigint                      not null primary key,
    quantity    integer                     not null,
    total_price numeric(10, 2)              not null,
    created_at  timestamp(6) with time zone not null,
    updated_at  timestamp(6) with time zone not null,
    status      varchar(255)                not null
        constraint purchase_order_item_status_check
            check ((status)::text = ANY
                   ((ARRAY ['OPENED'::character varying, 'PENDING'::character varying, 'CLOSED'::character varying])::text[])),
    order_id    bigint                      not null
        constraint fk_purchase_order_item_order_id references public.purchase_order,
    product_id  bigint                      not null
        constraint fk_purchase_order_item_product_id references public.product,
    customer_id bigint                      not null
        constraint fk_purchase_order_item_customer_id references public.customer
);

INSERT INTO public.purchase_order_item (quantity, total_price, created_at, id, order_id, product_id, updated_at,
                                        customer_id, status)
VALUES (2, 27, '2023-09-12 11:45:22+00', 1, 1, 1, '2023-09-13 12:00:10+00', 1, 'PENDING'),
       (1, 15, '2023-09-20 12:45:50+00', 2, 2, 2, '2023-09-21 09:30:50+00', 2, 'OPENED'),
       (3, 30.75, '2023-10-02 16:30:05+00', 3, 3, 3, '2023-10-03 17:45:22+00', 3, 'CLOSED'),
       (1, 13.5, '2023-08-05 10:25:12+00', 4, 4, 1, '2023-08-06 11:15:45+00', 4, 'OPENED'),
       (2, 30, '2023-07-18 15:10:30+00', 5, 5, 2, '2023-07-19 16:50:30+00', 5, 'CLOSED');

alter sequence purchase_order_item_seq restart with 6;
