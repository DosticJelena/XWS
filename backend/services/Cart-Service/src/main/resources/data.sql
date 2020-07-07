-- da li admini imaju korpe?
insert into cart (user_id) values (1);
insert into cart (user_id) values (2);
-- korisnici
insert into cart (user_id) values (3);
insert into cart (user_id) values (4);
insert into cart (user_id) values (5);
-- kompanija: insert into cart(user_id) values (6);

insert into vehicle_cart(owner_id) values (3);
insert into vehicle_cart(owner_id) values (3);
insert into vehicle_cart(owner_id) values (4);
insert into vehicle_cart(owner_id) values (4);
insert into vehicle_cart(owner_id) values (5);
insert into vehicle_cart(owner_id) values (5);

insert into cart_vehicles(carts_id,vehicles_id) values (5,1);
insert into cart_vehicles(carts_id,vehicles_id) values (5,2);
insert into cart_vehicles(carts_id,vehicles_id) values (5,3);

insert into renting_request(user_id,status,created_at) values (4,1,'2020-1-11');
insert into renting_request(user_id,status,created_at) values (4,1,'2020-1-11');
insert into renting_request(user_id,status,created_at) values (4,2,'2020-1-11');


insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(3,1,'2020-1-11','2020-1-20',1000,100);
insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(3,2,'2020-1-1','2020-1-20',1000,100);
insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(3,3,'2020-1-5','2020-1-15',1000,100);