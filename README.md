# KATA-SG
Bank account kata Think of your personal bank account experience When in doubt, go for the simplest solution Requirements
- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing

---

### User Stories
#### US 1:
In order to save money, as a bank client, I want to make a deposit in my account

#### US 2:
In order to retrieve some or all of my savings, as a bank client, i want to make a withdrawal from my account

#### US 3:
In order to check my operations, as a bank client, I want to see the history (operation, date, amount, balance) of my operations,

---

### Project

______________

#### How to launch
Project is based on JDK21, first we need to package. In ./java-app run :
<code>./mvnw clean package</code>  
Then we want to run the docker with postgres db and java-app, to do so in the main directory run :
<code>docker-compose up</code>

______________

#### Unit test
You can launch unit testing by running in ./java-app :
<code>./mvnw test</code>  
Unit test are non-exhaustive and are only here to demonststrate skill

______________

#### How to use
This project expose a rest api wich allows :
| Action                 | Method | URL                                                 | Body                                                                    |
|------------------------|--------|-----------------------------------------------------|-------------------------------------------------------------------------|
| Create an account      | GET    | localhost:8080/account/create                       |                                                                         |
| Get an account by id   | GET    | localhost:8080/account/get/{accountId}              |                                                                         |
| Get account balance    | GET    | localhost:8080/operation/balance/{accountId}        |                                                                         |
| Deposit money          | POST   | localhost:8080/operation/deposit                    | {     "accountId": 1,     "value": 2000,     "description": "Salaire" } |
| Withdraw money         | POST   | localhost:8080/operation/withdraw                   | {     "accountId": 1,     "value": -500,     "description": "Loyer" }   |
| Show operation history | GET    | localhost:8080/operation/accountHistory/{accountId} |                                                                         |
