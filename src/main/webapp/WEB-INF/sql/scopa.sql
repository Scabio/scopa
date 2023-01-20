create extension pgcrypto;

drop table user;
create table users (
	name varchar(32) primary key,
	password text not null
);

select * from users;

INSERT INTO users VALUES(?, crypt(?, gen_salt('bf')) );
INSERT INTO users VALUES('Scabio', crypt('2606', gen_salt('bf')) );
INSERT INTO users VALUES('Fabio', crypt('1707', gen_salt('bf')) );

SELECT password=crypt(?, ( SELECT password FROM users WHERE name=? ) ) AS pswmatch FROM users WHERE name = ?;