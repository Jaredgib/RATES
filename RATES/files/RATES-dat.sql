insert into accounts values ('admin', 'admin');
insert into accounts values ('jared', 'gibson');

insert into topics values ('COMP3751', 'Interactive Comp Systems');
insert into topics values ('COMP3712', 'Computer Programming 3');

insert into usertopics values ('COMP3751', 'admin');
insert into usertopics values ('COMP3712', 'admin');
insert into usertopics values ('COMP3751', 'jared');
insert into usertopics values ('COMP3712', 'jared');

insert into review values ('0001', 'COMP3751', 'admin', 'Test review temp', '1990-04-04', 'true', null, null);
insert into review values ('0002', 'COMP3712', 'jared', 'Test 2 review temp', '2001-06-06', 'false', null, null);


