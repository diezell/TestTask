# Тестовое задание
REST-сервис для управления опросами

# Краткая инструкция для запуска приложения
Для запуска необходима среда разработки Intellij IDEA Ultimate-версии, JDK 8-ой версии

# API Спецификация
| Метод | Путь | Действие | JSON-запрос |
| ------------- | ------------- | ------------- | ------------- |
| GET | /poll????????????????добавить | Отобразить список всех опросов |
| GET | /poll/{id} | Отобразить конкретный опрос по id |
| POST | /poll | Создать опрос | <pre>{<br>  "name": "Теоретические знания",<br>  "startDate": "12-04-2019 10:45",<br>  "finishDate": "05-11-2022 17:15"<br>}
| PUT | /poll/{id} | Изменить опрос | <pre>{<br>  "name": "Практические знания",<br>  "startDate": "",<br>  "finishDate": ""<br>}
| DELETE | /poll/{id} | Удалить опрос (и все связанные вопросы) |
| GET | /question/poll/{id} | Отобразить все вопросы определенного опроса (id опроса) |
| GET | /question/{id} | Отобразить вопрос по id вопроса |
| POST | /question/{id} | Создать вопрос в опросе (id опроса) | <pre>{<br>  "text": "Какая сила притяжения на Земле?"<br>}
| PUT | /question/{id} | Ответить на вопрос (id вопроса) | <pre>{<br>  "response": "Сила притяжения равна 9.8 м/с2"<br>}
| DELETE | /question/{id} | Удалить вопрос по его id |

		
