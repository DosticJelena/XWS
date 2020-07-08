insert into discount(amount) values (10);
insert into discount(amount) values (20);
insert into discount(amount) values (30);

INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type,discount_id)
	VALUES (3, 'Yes', 10, 'AUDI', 1,100, 0, 'No', 'Diesel', 'Novi Sad', 'A3', 100, 'Manual', 'A',1);
INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type,discount_id)
	VALUES (3, 'Yes', 10, 'AUDI', 1,100, 10, 'Yes', 'Diesel', 'Beograd', 'A4', 150, 'Manual', 'B');
INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_typeINSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
)
	VALUES (3, 'No', 10, 'Kia', 1,100, 20, 'Yes', 'Diesel', 'Novi Sad', 'Ceed', 130, 'Manual', 'C');
INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (4, 'Yes', 15, 'Mazda', 2,150, 50, 'Yes', 'Diesel', 'Beograd', 'CX-5', 160, 'Automatic', 'C');
INSERT INTO public.vehicle(owner, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (5, 'Yes', 12, 'Fiat', 0,130, 40, 'Yes', 'Diesel', 'Beograd', '500', 160, 'Automatic', 'C');

INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2020/03/2020-audi-a3-sportback.jpg', 1);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2020/03/2020-audi-a3-sportback-8-780x470.jpg', 1);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://editorial.pxcrush.net/carsales/general/editorial/audi-a4-45-quattro-17.jpg', 2);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://www.topspeed.rs/gallery/thumb/foto-kia-1/page/1/marker/15956/photo/138458.jpeg', 3);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://www.b92.net/news/pics/2018/09/15/2184352575b9cae33c0b2e027465535_orig.jpg', 4);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://www.fiatsrbija.rs/novi/500/images/Fiat-500-Star-Pink-CityCar-Desktop-671x532.jpg', 5);

INSERT INTO public.fuel_type(fuel_type, status) VALUES ('Diesel', 0);
INSERT INTO public.fuel_type(fuel_type, status) VALUES ('Petrol', 0);

INSERT INTO public.vehicle_class(vehicle_class, status) VALUES ('Limousine', 0);

INSERT INTO public.vehicle_model(brand, model, status) VALUES ('AUDI', 'A3', 0);
INSERT INTO public.vehicle_model(brand, model, status) VALUES ('AUDI', 'A4', 0);
INSERT INTO public.vehicle_model(brand, model, status) VALUES ('Kia', 'Ceed', 0);