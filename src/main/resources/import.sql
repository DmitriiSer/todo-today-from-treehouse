-- Insert roles
insert into role (name) values ('ROLE_USER');

-- Insert users
insert into user (username, password, enabled, role_id) values ('user', 'password', true, 1);
insert into user (username, password, enabled, role_id) values ('user2', 'password', true, 1);

-- Insert tasks
insert into task (complete, description, user_id) values (true, 'My initial task', 1);
insert into task (complete, description, user_id) values (false, 'Buy groceries', 2);
insert into task (complete, description, user_id) values (false, 'Learn Spring Framework', 1);
insert into task (complete, description, user_id) values (true, 'Feed the dog', 2);