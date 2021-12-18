# Spring Boot Project for LDAP Authentication with JWT

### Get Started
Configure LDAP properties in `src/main/resources/application.properties` 
```ldap.url=ldap://172.0.0.1:389/
ldap.basedn=DC=alpersayin,DC=com,DC=tr
ldap.userdn=ou=User
ldap.userdnpattern=uid={0},ou=User
ldap.managerdn=cn=Manager
ldap.groupdn=ou=Groups
ldap.manager.password=nVrGpc3HY3jx
```
### Testing
For sending data with POST request by using `curl` at the command line;

Windows Terminal:
```
curl -H "Content-Type: application/json" -X POST -d {\"username\":\"<your-username>\",\"password\":\"<password>\"} http://localhost:8888/api/login/
```
Linux Terminal:
```
curl -H "Content-Type: application/json" -X POST -d {"username":"<your-username>","password":"<password>"} http://localhost:8888/api/login/
```