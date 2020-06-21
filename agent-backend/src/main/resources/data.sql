INSERT INTO public.vehicle(owner_id, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (1, 'Yes', 10, 'Prvi', 1,100, 0, 'No', 'ben', 'Novi Sad', '123', 100, 'Manual', 'A');
INSERT INTO public.vehicle(owner_id, cdwstatus, additional_price_per_km, brand, children_seats, distance, distance_per_rent, distance_per_rent_status, fuel_type, location, model, price, transmission, vehicle_type)
	VALUES (1, 'Yes', 10, 'Drugi', 1,100, 10, 'Yes', 'ben', 'Beograd', '124', 150, 'Manual', 'B');

INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2019/08/bmw-x5-protection2.jpg', 1);
INSERT INTO public.picture(url, vehicle_id)	VALUES ('https://autorepublika.com/wp-content/uploads/2019/10/20.jpg', 1);