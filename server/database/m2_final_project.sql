-- database m2_final_project
BEGIN TRANSACTION;


-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users, project, artist_project, artist, manager, artist_link, site_type CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id serial,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	enabled boolean NOT NULL,
	user_type varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE project (
	project_id serial,
	project_name varchar(100) NOT NULL,
	release_date date NOT NULL,
	description varchar(100) NOT NULL,
	completed boolean NOT NULL,
	CONSTRAINT PK_project PRIMARY KEY (project_id)
);

CREATE TABLE manager (
	manager_id serial,
	manager_name varchar(200) NOT NULL,
	manager_email_address varchar(200) NOT NULL,
	user_id int NOT NULL,
	image_url TEXT NOT NULL,
	CONSTRAINT PK_manager PRIMARY KEY (manager_id),
	CONSTRAINT UQ_manager_email_address UNIQUE (manager_email_address),
	CONSTRAINT FK_manager_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE artist (
	artist_id serial,
	artist_name varchar(100) NOT NULL,
	manager_id int NOT NULL DEFAULT 0,
	artist_email_address varchar(200) NOT NULL,
	user_id int NOT NULL,
	image_url TEXT NOT NULL,
	pro varchar(100),
	CONSTRAINT PK_artist PRIMARY KEY (artist_id),
	CONSTRAINT FK_artist_manager FOREIGN KEY (manager_id) REFERENCES manager(manager_id),
	CONSTRAINT FK_artist_user FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT UQ_artist_email_address UNIQUE(artist_email_address),
	CONSTRAINT UQ_artist_name UNIQUE(artist_name)
);

CREATE TABLE site_type (
	site_type_id serial,
	site_name varchar(100) NOT NULL,
	CONSTRAINT PK_site_type_id PRIMARY KEY (site_type_id),
	CONSTRAINT UQ_site_name UNIQUE(site_name)
);

CREATE TABLE artist_link (
	link_id serial,
	artist_id int NOT NULL,
	url TEXT NOT NULL,
	site_type_id int NOT NULL,
	CONSTRAINT PK_link_id PRIMARY KEY (link_id),
	CONSTRAINT FK_artist_id FOREIGN KEY (artist_id) REFERENCES artist(artist_id),
	CONSTRAINT FK_site_type_id FOREIGN KEY (site_type_id) REFERENCES site_type(site_type_id)
	
);

CREATE TABLE artist_project (
	artist_id int NOT NULL,
	project_id int NOT NULL,
	CONSTRAINT PK_artist_project PRIMARY KEY(artist_id, project_id),
	CONSTRAINT FK_artist_project_artist FOREIGN KEY (artist_id) REFERENCES artist(artist_id),
	CONSTRAINT FK_artist_project_project FOREIGN KEY (project_id) REFERENCES project(project_id)
);


-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO users(user_id, username, password_hash, role, enabled, user_type) VALUES (0, 'notAuser', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER', false, 'N/A');

INSERT INTO
    users (username, password_hash, role, enabled, user_type)
VALUES
    ('bajose1029', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER', true, 'Artist'), --artist
    ('jimSwim11', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER', true, 'Artist'),  -- artist
    ('leviToCuz90', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER', false, 'Artist'), --artist
    ('mTwidale65', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER', true, 'Manager'), --manager
    ('OfeliaK0K', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER', true, 'Manager'),  --manager
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN', true, 'Admin'); --admin



insert into project (project_name, release_date, description, completed) values ('Ocean Blue', '2024-09-14', 'Album', true);
insert into project (project_name, release_date, description, completed) values ('Sapphire Sky', '2025-07-08', 'Album', false);
insert into project (project_name, release_date, description, completed) values ('Coral Reef', '2025-10-19', 'Album', false);
insert into project (project_name, release_date, description, completed) values ('Starlight', '2025-11-03', 'Album', false);
insert into project (project_name, release_date, description, completed) values ('Ambery Lite', '2025-10-13', 'Single', false);
insert into project (project_name, release_date, description, completed) values ('Thunderbird', '2024-07-23', 'Album', false);
insert into project (project_name, release_date, description, completed) values ('Gold Coast Canyon', '2025-11-20', 'Album', false);
insert into project (project_name, release_date, description, completed) values ('Mystic Mirage', '2025-05-30', 'Album', false);
insert into project (project_name, release_date, description, completed) values ('Moonshot Initiative', '2024-09-18', 'Single', false);
insert into project (project_name, release_date, description, completed) values ('Visions of You', '2025-10-21', 'Single', false);
insert into project (project_name, release_date, description, completed) values ('Copper Canyon', '2025-01-20', 'Album', false);


insert into manager (manager_id, manager_name, manager_email_address, user_id, image_url) values (0, 'No Management', '',0, '../src/assets/profile-pics/empty.png');
insert into manager (manager_name, manager_email_address, user_id, image_url) values ('Malissa Twidale', 'mtwidale0@mit.edu', 4, '../src/assets/profile-pics/Melissa.jpg');
insert into manager (manager_name, manager_email_address, user_id, image_url) values ('Ofelia Keeney', 'okeeney1@bandcamp.com', 5, '../src/assets/profile-pics/ofelia.jpg');


insert into artist (artist_name, manager_id, artist_email_address, user_id, image_url, pro) values ('Jim Swim', 1, 'jimSwim93@gmail.com', 2, '../src/assets/profile-pics/jimSwim.jpg', '177628902');
insert into artist (artist_name, manager_id, artist_email_address, user_id, image_url, pro) values ('ADE', 2, 'AD3music@hotmail.com', 1, '../src/assets/profile-pics/Ade.jpg', '1762803782');
insert into artist (artist_name, manager_id, artist_email_address, user_id, image_url, pro) values ('LevI', 2, 'LiveLevi2@yahoo.com', 3, '../src/assets/profile-pics/Levi.jpg', '8298710029');


insert into artist_project (artist_id, project_id) values (3, 1);
insert into artist_project (artist_id, project_id) values (1, 3);
insert into artist_project (artist_id, project_id) values (2, 5);
insert into artist_project (artist_id, project_id) values (2, 2);
insert into artist_project (artist_id, project_id) values (1, 10);
insert into artist_project (artist_id, project_id) values (2, 7);
insert into artist_project (artist_id, project_id) values (3, 4);
insert into artist_project (artist_id, project_id) values (3, 6);
insert into artist_project (artist_id, project_id) values (2, 11);
insert into artist_project (artist_id, project_id) values (1, 8);
insert into artist_project (artist_id, project_id) values (1, 9);


INSERT INTO 
	site_type (site_name)
VALUES
	('Facebook'),
	('Instagram'),
	('Tik Tok'),
	('Distributor - i.e. DistroKid, TuneCore, etc.'),
	('SoundCloud'),
	('Bandcamp'),
	('Twitter'),
	('YouTube');

INSERT INTO 
	artist_link (artist_id, url, site_type_id)
VALUES
	(1, 'https://www.instagram.com/jim_swim/', 2),
	(2, 'https://www.instagram.com/ademuzic/', 2),
	(2, 'https://www.facebook.com/bayo.ajose', 1),
	(2, 'https://www.tiktok.com/@ademuzic', 3),
	(3, 'https://www.facebook.com/levI', 1),
	(1, 'https://www.twitter.com/jimSwim', 7);



COMMIT TRANSACTION;
