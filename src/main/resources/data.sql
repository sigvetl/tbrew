
insert into users(userid, username, salt, password, firstname, lastname)
values(1, 's', 'Mk/9Dumyc5bWIWgSOZYLag==', 'GKNOOrWrmodB5A9/xrS97g==', 's', 's'),
(2, 'admin', 'Ijwttlok9ZNx8KcByvw5TA==', 'T6QfZMTOTQ3qoE0HfHv4hw==', 'admin', 'admin');

insert into beer(beerid, name, type, ibu, recipelink, userid, createdby, lastupdatedby) values
(1, 'lucky jack', 'pale ale', 60, 'http://www.vg.no', 1, 's s', 's s'),
(2, 'ringnes', 'pilsner', 40, 'http://www.dagbladet.no', 1, 's s', 's s');

insert into batch(batchid, volume, beerid, og, fg, brewdate, tapdate, userid, createdby, lastupdatedby, quality, comments)
values(1, 25, 2, 1.06, 1.012, DATE '2020-09-09', DATE '2020-09-25', 2, 'admin admin', 'admin admin', 'Good', 'example comment'),
(2, 23, 1, 1.04, 1.010, DATE '2020-10-09', DATE '2020-11-01', 1, 's s', 's s', 'Bad', 'too carbonated');



