-- create db
SELECT 'CREATE DATABASE sugar_shack_maplr_test'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'sugar_shack_maplr_test');
