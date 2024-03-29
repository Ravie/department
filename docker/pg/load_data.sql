INSERT INTO department (id, name, author_id) VALUES (14, 'Finance', 2);
INSERT INTO department (id, name, author_id) VALUES (15, 'Human Resources', 2);
INSERT INTO department (id, name, author_id) VALUES (16, 'Production', 2);
INSERT INTO department (id, name, author_id) VALUES (3, 'Marketing', 2);
INSERT INTO department (id, name, author_id) VALUES (17, 'Development', 2);
INSERT INTO department (id, name, author_id) VALUES (18, 'Quality Management', 2);
INSERT INTO department (id, name, author_id) VALUES (19, 'Sales', 2);
INSERT INTO department (id, name, author_id) VALUES (20, 'Research', 2);
INSERT INTO department (id, name, author_id) VALUES (21, 'Customer Service', 2);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (4, 'Bezalel Simmel', 11280, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (53, 'Parto Bamford', 11280, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (54, 'Kyoichi Maliniak', 11280, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (55, 'Anneke Preusig', 11280.02, 2, 17);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (59, 'Tzvetan Zielinski', 11280.01, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (60, 'Saniya Kalloufi', 11280, 2, 17);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (61, 'Sumant Peac', 11280, 2, 14);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (62, 'Duangkaew Piveteau', 11280, 2, 16);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (63, 'Mary Sluis', 11280.01, 2, 15);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (64, 'Patricio Bridgland', 11280, 2, 14);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (65, 'Eberhardt Terkki', 11280.02, 2, 15);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (66, 'Berni Genin', 11280, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (68, 'Guoxiang Nooteboom', 11280, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (69, 'Kazuhito Cappelletti', 11280.01, 2, 16);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (72, 'Cristinel Bouloucos', 11280, 2, 14);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (74, 'Kazuhide Peha', 11280.02, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (75, 'Lillian Haddadi', 12412412, 2, 3);
INSERT INTO employee (id, name, salary, author_id, department_id) VALUES (76, 'Mayuko Warwick', 11280.01, 2, 18);
INSERT INTO flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (1, '1', 'InitDb', 'SQL', 'V1__InitDb.sql', -1098097612, 'postgres', '2019-07-16 22:33:49.425390', 31, true);
INSERT INTO logins (id, activation_code, active, email, password, username) VALUES (2, '51a03e64-5136-4be7-a8bf-7aa0fd4b4f87', true, 'ravijkee@yandex.ru', '$2a$08$/LM4OhreRYfgcBHmcbyo1eT8s2eAeT3Wc94HMrm941atTK7/.DGkG', 'Ravie');
INSERT INTO logins (id, activation_code, active, email, password, username) VALUES (1, 'be38efaf-c335-4d67-962d-155f2dd19d8f', true, 'ravijkee@gmail.com', '$2a$08$XSeNU9gu4Mk3ZHQPoyEm7.v5PorjFjWklMH73EU6rVhW5pAcJKT4i', 'A');
INSERT INTO logins (id, activation_code, active, email, password, username) VALUES (7, null, true, 'ravijkee@yandex.ru', '$2a$08$vnp0L6r.6yoTTpbIbjOxze8dAF30IOZRnAReQeZ2fI0dXS1f.ZkEe', '123123');
INSERT INTO logins (id, activation_code, active, email, password, username) VALUES (9, null, true, 'ravijkee@yandex.ru', '$2a$08$acAZanXhcTqolBolIDhcHe.YSj3lGrgUq7.hL8CyJtG49DcdXDwgi', 'RavieTest');
INSERT INTO user_role (user_id, access_level) VALUES (1, 'USER');
INSERT INTO user_role (user_id, access_level) VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, access_level) VALUES (2, 'USER');
INSERT INTO user_role (user_id, access_level) VALUES (2, 'ADMIN');
INSERT INTO user_role (user_id, access_level) VALUES (7, 'USER');
INSERT INTO user_role (user_id, access_level) VALUES (9, 'USER');