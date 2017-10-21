-- Insert roles
insert into role (name) values ('ROLE_USER');

-- Insert users
insert into user (username, password, enabled, role_id) values ('user', 'password', true, 1);
insert into user (username, password, enabled, role_id) values ('user2', 'password', true, 1);

-- Insert tasks
insert into task (complete, description) values (true, 'My initial task');
insert into task (complete, description) values (false, 'Buy groceries');
insert into task (complete, description) values (false, 'Learn Spring Framework');
insert into task (complete, description) values (true, 'Feed the dog');