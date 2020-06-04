insert into cart (user_id) values (1);

insert into vehicle_cart(owner_id) values (1);
insert into vehicle_cart(owner_id) values (1);
insert into vehicle_cart(owner_id) values (1);

insert into cart_vehicles(carts_id,vehicles_id) values (1,1);
insert into cart_vehicles(carts_id,vehicles_id) values (1,2);

insert into renting_request(user_id,status) values (1,0);


insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(3,1,'2020-1-1','2020-1-2',1000,100);