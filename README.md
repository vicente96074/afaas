# MICROSERVICES

- User Service
- Hardware Store Service
- Pharmacy Service
- Report Service

__Clone the repository__
```bash
git clone git@github.com:vicente96074/microservice-inventory.git
```
__Run the following command to start the services__
```bash
docker-compose up
```
__Stop the services__
```bash
docker-compose down
```

# User Service
- User Service is a microservice that is responsible for managing users in the system. It provides APIs for user registration, login, and user profile management.
 ## Documentation
- [User Service Documentation](http://localhost:8081/auth-service/swagger-ui.html)
 ## Technologies
- Java 17
- Spring Boot
- Spring Security
- MySQL
- Swagger

## Ports
- Gateway: 8080
- Config data: 8081
- Eureka: 8761
- Auth default: 8082

# Edit environment variables
- ```bash
  nano ~/.bashrc
  ```
- Add the following lines like this on linux
```bash
# Services
export MS_INVENTORY_FRONTEND_URL="http://192.168.1.80:4200"
export CONFIG_SERVER_URL="http://192.168.1.80:8081"
export EUREKA_SERVER_URL="http://192.168.1.80:8761/eureka"

# Credentials
export MYSQL_USERNAME="root"
export MYSQL_PASSWORD="your_password"

# Schemas URLs for each service
export TEST_SERVICE_DB_URL="jdbc:mysql://localhost:3306/hs_test?useSSL=false&serverTimezone=America/Guatemala&useLegacyDateTimeCode=false&allowPublicKeyRetrieval=true"
export AUTH_SERVICE_DB_URL="jdbc:mysql://localhost:3306/hs_authentication?useSSL=false&serverTimezone=America/Guatemala&useLegacyDateTimeCode=false&allowPublicKeyRetrieval=true"
export BRANCH_SERVICE_DB_URL="jdbc:mysql://localhost:3306/hs_branch?useSSL=false&serverTimezone=America/Guatemala&useLegacyDateTimeCode=false&allowPublicKeyRetrieval=true"
export CASHIER_SERVICE_DB_URL="jdbc:mysql://localhost:3306/hs_cashier?useSSL=false&serverTimezone=America/Guatemala&useLegacyDateTimeCode=false&allowPublicKeyRetrieval=true"
export CLIENT_SERVICE_DB_URL="jdbc:mysql://localhost:3306/hs_client?useSSL=false&serverTimezone=America/Guatemala&useLegacyDateTimeCode=false&allowPublicKeyRetrieval=true"
export PAYMENT_SERVICE_DB_URL="jdbc:mysql://localhost:3306/hs_payment?useSSL=false&serverTimezone=America/Guatemala&useLegacyDateTimeCode=false&allowPublicKeyRetrieval=true"

# JWT
export JWT_SECRET="secret"
export JWT_EXPIRATION="180"

# Time Zone
export TIME_ZONE="America/Guatemala"

# Email
export EMAIL_USERNAME="your_email@domain.com"
export EMAIL_PASSWORD="your_password"
export EMAIL_HOST="smtp.office365.com"
export EMAIL_PORT="587"
export EMAIL_PROTOCOL="smtp"

# Data encryption
export ENCRYPTION_SECRET="StrongPassword!@#"

# SSL 
export SSL_KEY_ALIAS="test"
export SSL_KEY_STORE="your_path_to_keystore"
export SSL_KEY_STORE_PASSWORD="your_password"
export SSL_KEY_STORE_TYPE="PKCS12"

# Logs
export AUTH_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/auth-service.log"
export BRANCH_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/branch-service.log"
export CASHIER_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/cashier-service.log"
export CLIENT_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/client-service.log"
export PAYMENT_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/payment-service.log"
export USER_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/user-service.log"
export MEDIA_LOCATION_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/media-location-service.log"
export WEBSOCKET_SERVICE_LOG_FILE="/home/augusto/Documentos/Projects/ms/logs/websocket-service.log"

# Media location
export MEDIA_LOCATION="/home/augusto/Documentos/Projects/ms/media"

# Github credentials
export SSH_GITHUB_OWNER_PRIVATE_KEY="your_path_to_private_key"
```
- Save the file and run the following command
```bash
source ~/.bashrc
```

# DATABASE CREATIONS
```
DROP DATABASE IF EXISTS hs_authentication;
CREATE DATABASE hs_authentication;

DROP DATABASE IF EXISTS hs_branch;
CREATE DATABASE hs_branch;

DROP DATABASE IF EXISTS hs_cashier;
CREATE DATABASE hs_cashier;

DROP DATABASE IF EXISTS hs_client;
CREATE DATABASE hs_client;

DROP DATABASE IF EXISTS hs_payment;
CREATE DATABASE hs_payment;

DROP DATABASE IF EXISTS hs_test;
CREATE DATABASE hs_test;
```
