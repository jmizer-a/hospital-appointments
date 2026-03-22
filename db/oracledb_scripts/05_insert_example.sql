ALTER SESSION SET CONTAINER = hospital;

INSERT INTO accounts (login, password)
VALUES ('pkow', '123');

INSERT INTO patients (birthdate, name, surname, gender, account_id)
VALUES (
  TO_DATE('1990-06-23', 'YYYY-MM-DD'),
  'Pacjent',
  'Kowalski',
  'M',
  (SELECT id FROM accounts WHERE login = 'pkow')
);
COMMIT;

INSERT INTO appointment_statuses (id, name)
VALUES (1, 'scheduled');
COMMIT;

INSERT INTO doctors (name, surname, date_employed)
VALUES ('Doktor', 'Doktorski', TO_DATE('2023-09-01', 'YYYY-MM-DD'));
COMMIT;