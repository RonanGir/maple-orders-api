-- Add product
INSERT INTO public.product (max_quantity, price, stock, created_at, id, updated_at, description, img, name, type)
VALUES (100, 13.5, 50, '2023-01-15 10:00:00+00', 1, '2023-01-16 09:30:00+00', 'created to be deleted',
        'maple_syrup_amber.jpg', 'test product', 'AMBER');

ALTER sequence product_seq restart with 2;


