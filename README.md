# Тестовое задание
REST-сервис для управления опросами.<br/>
Используемые технологии:
* Java 8-ой версии
* Spring Framework
* Hibernate
* PostgreSQL
* Liquibase
# Краткая инструкция для запуска приложения
Для запуска необходима среда разработки Intellij IDEA Ultimate-версии, JDK 8-ой версии.<br/>
В файле **application.properties** указаны названия параметров для подключения к БД, которые нужно проинициализировать в "**Edit Configurations...**".<br/>
*Примечание:* в верхнем меню выбрать Run -> Edit Configurations... -> Environment variables -> В Name указать имя параметра, а в Value значение.

# API Спецификация

| Метод | Путь | Действие | JSON-запрос |
| ------------- | ------------- | ------------- | ------------- |
| GET | /poll | Отобразить список всех опросов | Можно добавить параметры в url-адрес:<br/>filter - фильтрация списка со значениями: active, notActive;<br/>sort - сортировка списка со значениями: startDate, startDateReverse, name, nameReverse.
| GET | /poll/{id} | Отобразить конкретный опрос по id |
| POST | /poll | Создать опрос | <pre>{<br>  "name": "Теоретические знания",<br>  "startDate": "12-04-2019 10:45",<br>  "finishDate": "05-11-2022 17:15"<br>}
| PUT | /poll/{id} | Изменить опрос | <pre>{<br>  "name": "Практические знания",<br>  "startDate": "",<br>  "finishDate": ""<br>}
| DELETE | /poll/{id} | Удалить опрос (и все связанные вопросы) |
| GET | /question/poll/{id} | Отобразить все вопросы определенного опроса (id опроса) |
| GET | /question/{id} | Отобразить вопрос по id вопроса |
| POST | /question/{id} | Создать вопрос в опросе (id опроса) | <pre>{<br>  "text": "Какая сила притяжения на Земле?"<br>}
| PUT | /question/{id} | Ответить на вопрос (id вопроса) | <pre>{<br>  "response": "Сила притяжения равна 9.8 м/с2"<br>}
| DELETE | /question/{id} | Удалить вопрос по его id |

		
