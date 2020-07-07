-- admini --
insert into application_user(dtype, status, role, first_name, last_name, username, password) values ('A', 0, 0, 'Admin', 'Prvi', 'admin1', '123');
insert into application_user(dtype, status, role, first_name, last_name, username, password) values ('A', 0, 0, 'Admin', 'Drugi', 'admin2', '123');

-- korisnici --
-- blokiran
insert into application_user(dtype, status, role, first_name, last_name, username, password) values ('P', 2, 1, 'Korisnik', 'Prvi', 'korisnik1', '123');
-- aktivan
insert into application_user(dtype, status, role, first_name, last_name, username, password) values ('P', 0, 1, 'Korisnik', 'Drugi', 'korisnik2', '123');
insert into application_user(dtype, status, role, first_name, last_name, username, password) values ('P', 0, 1, 'Korisnik', 'Treci', 'korisnik3', '123');

-- kompanije? --
insert into application_user(dtype, status, role, company_name, username, password) values ('C', 0, 2, 'Rent NS', 'Rent1', '123');