-- create db
DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM pg_database
            WHERE datname = 'sugar_shack_maplr_test'
        ) THEN
            EXECUTE 'CREATE DATABASE sugar_shack_maplr_test';
        END IF;
    END $$;
