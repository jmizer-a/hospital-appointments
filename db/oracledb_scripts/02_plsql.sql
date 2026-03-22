ALTER SESSION SET CONTAINER = hospital;

CREATE OR REPLACE PROCEDURE drop_app_tables AS
    v_count NUMBER;
BEGIN
    FOR t IN (SELECT * FROM app_tables) LOOP
        SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = UPPER(t.name);
        IF v_count = 1 THEN
            EXECUTE IMMEDIATE 'DROP TABLE '|| t.name || ' CASCADE CONSTRAINTS';
        END IF;
    END LOOP;
END;

/
