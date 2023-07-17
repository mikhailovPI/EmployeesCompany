# EmployeesCompany

**Программа предназначена для предоставления личных карточек рабочих.**

<u>***Возможности приложения:***</u>

- Создание личной карточки рабочего;
- Редактирование личное карточки рабочего;
- Удаление личной карточки рабочего;
- Посмотреть личную карточку рабочего;

**В приложении имеются следующие роли:**

- USER - имеет возможность получить личную карточку для просмотра;
- ADMIN - имеет возможность создать, редактировать и удалить личную карточку рабочего,
  а также посмотреть все личные карточки рабочих

**Используемый стек: Java 11, Spring Boot, Spring Data JPA, Spring Security, Vaadin, Hibernate, Maven, PostgreSQL**

<u>***Для запуска приложения необходимо:***</u>

- Склонировать репозиторий на ПК в необходимую папку;
- Создать БД со следующими свойствами:
    - HOST: localhost;
    - PORT: 5432;
    - user и password см. в
      [application.properties](
      https://github.com/mikhailovPI/EmployeesCompany/blob/master/src/main/resources/application.properties);
- Запустить файл
  [schema.sql](https://github.com/mikhailovPI/EmployeesCompany/blob/master/src/main/resources/schema.sqls);
- Произвести запуск приложения (class EmployeesCompanyApplication);
- Запустить Postman на [localhost:8080](http://localhost:8080);
- Запустить коллекцию
  [тестов](
  https://github.com/mikhailovPI/EmployeesCompany/blob/master/info/EmployeeCompany.postman_collection.json)
  в Postman;

***Схема базы данных:***
> ![schema_db.png](EmployeesCompany/info/schema_db.PNG)
>
***Данные для тестирования в Postman:***
[tests](https://github.com/mikhailovPI/EmployeesCompany/blob/master/info/EmployeeCompany.postman_collection.json)








