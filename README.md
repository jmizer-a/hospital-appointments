# Hospital appointments

## How to run

### Simple docker-compose
- run a docker deamon 
```bash
sudo dockerd
```
or 
```bash
sudo systemctl start docker
```
- from the root directory of the project run
```bash
sudo docker compose up --build
```
(no sudo if the user is in the docker group - safety risk)

Access the Spring Boot backend at `http://localhost:8080` and the React frontend making a simple API call to backend at `http://localhost:80`.

### Running the backend and frontend separately, by hand
- run a docker deamon (as above)
- run the backend
```bash
cd backend
./mvnw spring-boot:run
```
- run the frontend
```bash
cd frontend
npm install
npm run dev
```
- Access the Spring Boot backend at `http://localhost:8080` and the React frontend making a simple API call to backend at `http://localhost:3000`.