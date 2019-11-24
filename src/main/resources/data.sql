INSERT INTO public.scooter_dock(id, available_place, dock_name) VALUES
(1, 10, 'zachodni'),
(2, 8, 'wschodni'),
(3, 5, 'północny'),
(4, 15, 'południowy');

INSERT INTO public.scooter(id, max_speed, model_name, rental_price, scooter_dock_id, user_account_id) VALUES
(5, 25, 'ERE-321', 19.99, 1, null),
(6, 20, 'RTT-43z', 15.50, 1, null),
(7, 35, 'V-SPEED-1', 29.99, 1, null),
(8, 40, 'V-SPEED-2', 39.99, 1, null),
(9, 35, 'V-SPEED-1', 29.99, 2, null),
(10, 35, 'V-SPEED-1', 29.99, 2, null),
(11, 25, 'ERE-321', 19.99, 2, null),
(12, 25, 'ERE-321', 19.99, 2, null),
(13, 40, 'V-SPEED-2', 39.99, 3, null),
(14, 35, 'V-SPEED-1', 29.99, 3, null),
(15, 20, 'RTT-43z', 15.50, 4, null);

insert into public.user_account(id, balance, create_date, owner_age, owner_email, owner_user_name) VALUES
(16, 22.22,'2019-10-27', 45, 'tom3@yahoo.com', 'tom_3'),
(17, 55.55,'2019-10-08', 78, 'jan.kowalski@gmail.com', 'janek222'),
(18, 0,'2019-07-08', 32, 'pati22@gazeta.pl.com', 'pati22');


SELECT setval('public.hibernate_sequence', 19, true);