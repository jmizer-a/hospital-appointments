ALTER SESSION SET CONTAINER = hospital;

BEGIN
    FOR t in (SELECT * FROM app_tables) LOOP
        EXECUTE IMMEDIATE 'GRANT ALL ON ' || t.name || ' TO app';
    END LOOP;
END;

/
