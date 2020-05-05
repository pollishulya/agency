insert into role (id, name) values (1, 'ROLE_USER');
insert into role (id, name) values (2, 'ROLE_ADMIN');
insert into role (id, name) values (3, 'ROLE_COMPANY');

insert into account(id,firstname,phone,email,password) values(1,'Полина','+37522346966','polli@gmail.com','1234');
insert into account(id,firstname,phone,email,password) values(55,'Компания','+37523655673','com@gmail.com','1234');
insert into account(id,firstname,phone,email,password) values(2,'Даша','+375336945673','dasha@gmail.com','1234');

insert into account_to_role (role_id, account_id) values (3, 55);
insert into account_to_role (role_id, account_id) values (1, 1);
insert into account_to_role (role_id, account_id) values (2, 2);

insert into company (id, name,address,phone,email) values (1, 'Праздник','Одинцова 1/1','+375669562323','com@gmal.com');

insert into food (id, name,cuisine,type,price,rating,food_company_id,image_url,description) values (1, 'Суши','ASIA','FIRST','5','1',55,'https://e1.edimdoma.ru/data/posts/0002/2457/22457-ed4_small.jpg?1541073008','Лучшие суши в Вашем городе');
insert into location (id, name,type,price,rating,location_company_id,capacity, address,image_url,description) values (1, 'Бар Мята','RESTAURANT','200','5',55,'20','Независимости 20','https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTXNK0qD0gRrbHQ8bdv3ZLd1C-O1Q02OP82UxI-2ETZS54vZmWz&usqp=CAU','Сеть ресторанов Мята всегда вам рада!');
insert into program (id, name,duration,type,price,rating,program_company_id,image_url,description) values (1, 'Клоун','1','LEADING','20','3',55,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTqI3b_hLMqWSdIUXiLBECZEiAgUMgOdwTQmXaVM9pxTUud5ufk&usqp=CAU','');