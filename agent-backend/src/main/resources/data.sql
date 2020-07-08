INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type, track, gps)
	VALUES (1, 'Yes', 10, 'Prvi', 1,100, 0, 'No', 'ben', 'Novi Sad', '123', 100, 'Manual', 'A', 1, '45.258075,19.813003');
INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (1, 'Yes', 10, 'Drugi', 1,1000, 10, 'Yes', 'ben', 'Beograd', '124', 150, 'Manual', 'B', 1, '45.253475,19.844703');

INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2019/08/bmw-x5-protection2.jpg', 1);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2019/10/20.jpg', 1);

insert into vehicle_cart(owner_id) values (1);
insert into vehicle_cart(owner_id) values (1);
insert into vehicle_cart(owner_id) values (2);
insert into vehicle_cart(owner_id) values (2);
insert into vehicle_cart(owner_id) values (3);
insert into vehicle_cart(owner_id) values (3);

insert into renting_request(user_id,status,created_at) values (1,1,'2020-1-11');
insert into renting_request(user_id,status,created_at) values (1,1,'2020-1-11');
insert into renting_request(user_id,status,created_at) values (1,2,'2020-1-11');


insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(1,1,'2020-1-11','2020-1-20',1000,100);
insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(1,2,'2020-1-1','2020-1-20',1000,100);
insert into renting_request_vehicle(vehicle_id,renting_request_id,start_date,end_date,price,distance)
values(2,3,'2020-1-5','2020-1-15',1000,100);

insert into comment(user_id, car_id, text, status) values(1,1,'asdsa','asdsadasd');
insert into comment(user_id, car_id, text, status) values(1,1,'asdsa','asdsadasd');
insert into comment(user_id, car_id, text, status) values(1,2,'asdsa','asdsadasd');

insert into car_grade(user_id, car_id, value) values(1,1,3);
insert into car_grade(user_id, car_id, value) values(1,2,4);
insert into car_grade(user_id, car_id, value) values(1,2,5);

insert into message(receiver_id,sender_id,content) values(1,1,'pozz');
insert into message(receiver_id,sender_id,content) values(1,1,'Kolega Stefane, pozdravljam!');
