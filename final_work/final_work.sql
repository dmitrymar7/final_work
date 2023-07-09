 -- 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
 
 DROP DATABASE IF EXISTS human_friends;
 
 CREATE DATABASE human_friends;
 
 -- 8. Создать таблицы с иерархией из диаграммы в БД
 USE human_friends;
 
 CREATE TABLE animals
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45)
 );
 
 CREATE TABLE home_animals
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_animal INT,
    CONSTRAINT fk_animals_id_home_animals 
    FOREIGN KEY (id_animal)  REFERENCES animals (id)
 );
 
 CREATE TABLE pack_animals
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_animal INT,
    CONSTRAINT fk_animals_id_pack_animals 
    FOREIGN KEY (id_animal)  REFERENCES animals (id)
 );
 
 CREATE TABLE dogs
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_home_animals INT,
    CONSTRAINT fk_home_animals_id_dogs 
    FOREIGN KEY (id_home_animals)  REFERENCES home_animals (id)
 );
 
 CREATE TABLE cats
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_home_animals INT,
    CONSTRAINT fk_home_animals_id_cats 
    FOREIGN KEY (id_home_animals)  REFERENCES home_animals (id)
 ); 
 
CREATE TABLE hamsters
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_home_animals INT,
    CONSTRAINT fk_home_animals_id_hamsters 
    FOREIGN KEY (id_home_animals)  REFERENCES home_animals (id)
 ); 
 
CREATE TABLE horses
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_pack_animals INT,
    CONSTRAINT fk_pack_animals_id_horses 
    FOREIGN KEY (id_pack_animals)  REFERENCES pack_animals (id)
 ); 
 
CREATE TABLE camels
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_pack_animals INT,
    CONSTRAINT fk_pack_animals_id_camels 
    FOREIGN KEY (id_pack_animals)  REFERENCES pack_animals (id)
 ); 
 
CREATE TABLE donkeys
 (
	id INT NOT NULL PRIMARY KEY,
    name VARCHAR(45),
    id_pack_animals INT,
    CONSTRAINT fk_pack_animals_id_donkeys 
    FOREIGN KEY (id_pack_animals)  REFERENCES pack_animals (id)
 ); 
 
-- 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения

INSERT INTO animals
VALUES
(1, 'Домашние животные'),
(2, 'Вьючные животные');

INSERT INTO home_animals
VALUES
(1, "Собаки", 1),
(2, "Кошки", 1),
(3, "Хомяки", 1);

INSERT INTO pack_animals
VALUES
(1, "Лошади", 2),
(2, "Жирафы", 2),
(3, "Ослы", 2);

ALTER TABLE dogs
ADD commands VARCHAR(50) NULL,
ADD birth_date DATETIME;

INSERT INTO dogs
VALUES
(1, "dog 1", 1, "comands 1, 2, 3", '2020-01-01'),
(2, "dog 2", 1, "comands 1, 2, 5", '2020-08-25');

ALTER TABLE cats
ADD commands VARCHAR(50) NULL,
ADD birth_date DATETIME;

INSERT INTO cats
VALUES
(1, "cat 1", 2, "comands 1, 1, 1", '2022-01-01'),
(2, "cat 2", 2, "comands 1, 2, 1", '2022-08-25');

ALTER TABLE hamsters
ADD commands VARCHAR(50) NULL,
ADD birth_date DATETIME;

INSERT INTO hamsters
VALUES
(1, "hamster 1", 3, "comands 1, 1, 1", '2022-05-01'),
(2, "hamster 2", 3, "comands 1, 2, 1", '2022-09-19');

ALTER TABLE horses
ADD commands VARCHAR(50) NULL,
ADD birth_date DATETIME;

INSERT INTO horses
VALUES
(1, "horse 1", 1, "comands 1, 1, 1", '2022-05-01'),
(2, "horse 2", 1, "comands 1, 2, 1", '2022-09-19');

ALTER TABLE camels
ADD commands VARCHAR(50) NULL,
ADD birth_date DATETIME;

INSERT INTO camels
VALUES
(1, "camel 1", 2, "comands 1, 1, 1", '2022-05-01'),
(2, "camel 2", 2, "comands 1, 2, 1", '2022-09-19');

ALTER TABLE donkeys
ADD commands VARCHAR(50) NULL,
ADD birth_date DATETIME;

INSERT INTO donkeys
VALUES
(1, "donkey 1", 3, "comands 1, 1, 1", '2022-05-01'),
(2, "donkey 2", 3, "comands 1, 2, 1", '2022-09-19'); 
 
-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

DROP TABLE camels;

SELECT *
FROM horses
UNION
SELECT *
FROM donkeys;


-- 11. Создать новую таблицу “молодые животные” в которую попадут все
-- животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
-- до месяца подсчитать возраст животных в новой таблице

 CREATE TABLE young_animals
 (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
	commands VARCHAR(50) NULL,
	birth_date DATETIME,
    age VARCHAR(50) NULL
 );

INSERT INTO young_animals(name, commands, birth_date, age)

SELECT 
	name, 
	commands, 
    birth_date,
    CONCAT(TIMESTAMPDIFF(YEAR, birth_date, NOW()), ' лет ', MOD(TIMESTAMPDIFF(MONTH, birth_date, NOW()), 12), " месяцев") AS age
FROM 
(
	SELECT name, commands, birth_date
	FROM dogs
	WHERE DATEDIFF(NOW(), birth_date) BETWEEN 365 AND 365 * 3
	UNION
	SELECT name, commands, birth_date
	FROM cats
	WHERE DATEDIFF(NOW(), birth_date) BETWEEN 365 AND 365 * 3
	UNION
	SELECT name, commands, birth_date
	FROM hamsters
	WHERE DATEDIFF(NOW(), birth_date) BETWEEN 365 AND 365 * 3
	UNION
	SELECT name, commands, birth_date
	FROM horses
	WHERE DATEDIFF(NOW(), birth_date) BETWEEN 365 AND 365 * 3
	UNION
	SELECT name, commands, birth_date
	FROM donkeys
	WHERE DATEDIFF(NOW(), birth_date) BETWEEN 365 AND 365 * 3
) as t_animals;

SELECT *
FROM young_animals;

-- 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

CREATE VIEW view_home_animals AS
	SELECT 
		ha.id as id,
		ha.name as name,
		a.name as name_animal
	FROM home_animals as ha
	LEFT JOIN animals as a
	ON ha.id_animal = a.id;
    
CREATE VIEW view_pack_animals AS
	SELECT 
		pa.id as id,
		pa.name as name,
		a.name as name_animal
	FROM pack_animals as pa
	LEFT JOIN animals as a
	ON pa.id_animal = a.id;

SELECT t_home_animals.*,
	view_home_animals.name as type_animal,
    view_home_animals.name_animal as kind_animal
FROM 
(    
	SELECT *
	FROM dogs
	UNION
	SELECT *
	FROM cats
	UNION
	SELECT *
	FROM hamsters
) as t_home_animals
INNER JOIN view_home_animals
ON t_home_animals.id_home_animals = view_home_animals.id
UNION
SELECT t_pack_animals.*,
	view_pack_animals.name as type_animal,
    view_pack_animals.name_animal as kind_animal
FROM 
(    
	SELECT *
	FROM horses
	UNION
	SELECT *
	FROM donkeys
) as t_pack_animals
INNER JOIN view_pack_animals
ON t_pack_animals.id_pack_animals = view_pack_animals.id;