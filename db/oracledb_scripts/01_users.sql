ALTER SESSION SET CONTAINER = hospital;
DROP USER APP CASCADE;
CREATE USER app identified by bazydanych;
GRANT CREATE SESSION TO APP;

DROP TABLE app_tables;

CREATE TABLE app_tables (
    name    VARCHAR2(40) UNIQUE
);

INSERT INTO app_tables VALUES ('accounts');
INSERT INTO app_tables VALUES ('appointments');
INSERT INTO app_tables VALUES ('appointment_statuses');
INSERT INTO app_tables VALUES ('consultations');
INSERT INTO app_tables VALUES ('doctors');
INSERT INTO app_tables VALUES ('doctor_schedules');
INSERT INTO app_tables VALUES ('doctor_specialties');
INSERT INTO app_tables VALUES ('doctor_types');
INSERT INTO app_tables VALUES ('doctor_ward');
INSERT INTO app_tables VALUES ('examinations');
INSERT INTO app_tables VALUES ('medical_documents');
INSERT INTO app_tables VALUES ('patients');
INSERT INTO app_tables VALUES ('tests');
INSERT INTO app_tables VALUES ('test_types');
INSERT INTO app_tables VALUES ('wards');
