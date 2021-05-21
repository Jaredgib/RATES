DROP TABLE accounts;
DROP TABLE topics;
DROP TABLE usertopics;
DROP TABLE review;

CREATE TABLE accounts (
username		CHAR(10) NOT NULL,
password		CHAR(10) NOT NULL,

primary key (username)
);

CREATE TABLE topics (
topic_id		CHAR(8) NOT NULL,
topic_name		CHAR(20) NOT NULL,

primary key (topic_id)
);

CREATE TABLE usertopics (
topic_id		CHAR(4) NOT NULL,
username		CHAR(10) NOT NULL,

foreign key (topic_id) references topics (topic_id),
foreign key (username) references accounts (username)
);

CREATE TABLE review (
review_id		CHAR(4) NOT NULL,
topic_id		CHAR(4) NOT NULL,
username		CHAR(4) NOT NULL,
review_text		CHAR(100) NOT NULL,
review_date		DATE NOT NULL,
anonymous		BOOLEAN,
picture			CHAR(200),
file_up			CHAR(200),

primary key (review_id),
foreign key (topic_id) references topics (topic_id),
foreign key (username) references accounts (username)
);