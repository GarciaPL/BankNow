## BankNow

Web application for teller that provides features like :

**Create account(s)** - a user can create an account, associate a name with it, give it a unique
account number, add a starting balance, etc.

**Make lodgement** - a user can lodge an amount into an account (balance increase)

**Make transfer** - a user can transfer an amount from one account to another (balance
transfer)

**View transactions** - a user can view recent, or all, transactions for an account (statement)

## Technology

##### Environment

- Technology : Java 1.7.0_65
- Framework : Spring Framework 4.0.1.RELEASE

##### Libraries

- Spring Beans – 4.0.1.RELEASE
- Spring Tx – 4.0.1.RELEASE
- Spring Context – 4.0.1.RELEASE
- Spring Context Support – 4.0.1.RELEASE
- Spring Orm – 4.0.1.RELEASE
- Spring Jdbc – 4.0.1.RELEASE
- Spring Web – 4.0.1.RELEASE
- Spring Web MVC – 4.0.1.RELEASE
- Spring Test - 4.0.1.RELEASE
- Joda Money - 0.10.0
- Jackson Core - 2.5.0
- Jackson Databind - 2.5.0
- Jackson Annotations - 2.5.0
- Javax Servlet API – 3.1.0
- JSTL – 1.2
- Hibernate Core - 4.3.5.Final
- Hibernate Entitymanager - 4.3.5.Final
- HSQLDB - 2.3.3
- SLF4J - 1.7.8
- Commons Logging - 1.2
- JUnit - 4.10
- Mockito - 1.9.5
- Hamcrest - 1.3
- Twitter Bootstrap - 3.3.4
- DataTables - 1.10.7
- jqBootstrapValidation - 1.3.6

## Functionalities
1. Create account
2. Make deposit
3. Make transfer
4. View transactions

## Jetty
To run jetty use command **mvn jetty:run** in directory of project. After that application should be accessible under context **http://localhost:9090/banknow/**

## WAR
War can be build using command **mvn war:war** in directory of project. War will be accessible under directory /target and called **banknow.war**

## Screenshots

![Home](https://github.com/GarciaPL/GarciaPL.github.io/blob/master/img/banknow/Home.png "Home")
![CreateAccount](https://github.com/GarciaPL/GarciaPL.github.io/blob/master/img/banknow/CreateAccount.png "Create Account")
![DespoitMoney](https://github.com/GarciaPL/GarciaPL.github.io/blob/master/img/banknow/DepositMoney.png "Despoit Money")
![Transactions](https://github.com/GarciaPL/GarciaPL.github.io/blob/master/img/banknow/Transactions.png "Transactions")

## License
Code released under the MIT License (MIT). Docs released under Creative Commons.

## References
- [Twitter Bootstrap](http://getbootstrap.com)
- [HSQLDB](http://hsqldb.org)
- [Jetty](http://www.eclipse.org/jetty/)
- [DataTables](https://datatables.net)
- [jqBootstrapValidation](http://reactiveraven.github.io/jqBootstrapValidation/)

[![Build Status](https://travis-ci.org/GarciaPL/BankNow.svg?branch=master)](https://travis-ci.org/GarciaPL/BankNow)

[![Known Vulnerabilities](https://snyk.io/test/github/garciapl/banknow/badge.svg)](https://snyk.io/test/github/garciapl/banknow)
