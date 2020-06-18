INSERT INTO public.vehicle(owner_id, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (1, 'Yes', 10, 'NAUDI', 1,100, 0, 'No', 'ben', 'Novi Sad', '123', 100, 'Manual', 'A');
INSERT INTO public.vehicle(owner_id, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (1, 'Yes', 10, 'abc', 1,100, 10, 'Yes', 'ben', 'Beograd', '124', 150, 'Manual', 'B');
INSERT INTO public.vehicle(owner_id, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (1, 'No', 10, '', 1,100, 20, 'Yes', 'ben', 'Novi Sad', '125', 130, 'Manual', 'C');

INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2019/08/bmw-x5-protection2.jpg', 1);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2019/10/20.jpg', 1);

INSERT INTO public.fuel_type(fuel_type, status) VALUES ('Diesel', 0);
INSERT INTO public.fuel_type(fuel_type, status) VALUES ('Petrol', 0);

INSERT INTO public.vehicle_class(vehicle_class, status) VALUES ('Limousine', 0);

INSERT INTO public.vehicle_model(brand, model, status) VALUES ('AUDI', 'A3', 0);
INSERT INTO public.vehicle_model(brand, model, status) VALUES ('AUDI', 'A4', 0);