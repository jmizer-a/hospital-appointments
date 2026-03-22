#### DB connect instruction

1. visit https://container-registry.oracle.com
2. login
3. select database > enterprise
3. accept license agreement
4. sudo docker login https://container-registry.oracle.com
5. login with the same credentials
6. sudo docker compose up --build
7. on another terminal: sudo docker exec -it hospital-appointments-db-1 /bin/sh
8. sqlplus / as sysdba
9. run commands listed in db/oracledb_scripts/01-users.sh
10. example connection configuration is shown in howtoconnectdb.png
