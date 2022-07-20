SET check_function_bodies = false;
INSERT INTO public."user" (id, name, email, phone, created_at, updated_at) VALUES (1, 'Yonathan Morales', 'y@glofox.com', NULL, '2022-07-17 20:42:35.002294+00', '2022-07-17 20:42:35.002294+00');
INSERT INTO public."user" (id, name, email, phone, created_at, updated_at) VALUES (2, 'Emili Zambrano', 'e@uklok.life', NULL, '2022-07-17 20:42:51.80883+00', '2022-07-17 20:42:51.80883+00');
INSERT INTO public."user" (id, name, email, phone, created_at, updated_at) VALUES (3, 'Luna Morales', 'lm@uklok.life', NULL, '2022-07-17 20:43:02.307567+00', '2022-07-17 20:43:02.307567+00');
INSERT INTO public.business (id, name, address, owner_id, type, created_at, updated_at) VALUES (1, 'GLOFOX FIT', 'Somewhere Cool ', 1, 'gym', '2022-07-17 20:51:38.505744+00', '2022-07-17 20:51:38.505744+00');
INSERT INTO public.business (id, name, address, owner_id, type, created_at, updated_at) VALUES (2, 'GLOFOX DANCE', 'Somewhere Cool ', 2, 'studio', '2022-07-17 20:51:55.769455+00', '2022-07-17 20:51:55.769455+00');
INSERT INTO public.business (id, name, address, owner_id, type, created_at, updated_at) VALUES (3, 'GLOFOX BABIES', 'Somewhere Cool ', 3, 'boutique', '2022-07-17 20:52:08.856295+00', '2022-07-17 20:52:08.856295+00');
INSERT INTO public.event (id, business_id, name, description, type, created_at, updated_at) VALUES (1, 1, 'KARATE', 'Martial Arts & Self Defense', 'training', '2022-07-17 20:58:37.515307+00', '2022-07-17 20:58:37.515307+00');
INSERT INTO public.event (id, business_id, name, description, type, created_at, updated_at) VALUES (2, 1, 'YOGA', 'Self Care', 'training', '2022-07-17 20:59:17.938597+00', '2022-07-17 20:59:17.938597+00');
INSERT INTO public.event (id, business_id, name, description, type, created_at, updated_at) VALUES (3, 2, 'Belly Dance', 'Arabian dance', 'course', '2022-07-17 21:00:24.483415+00', '2022-07-17 21:00:24.483415+00');
INSERT INTO public.event (id, business_id, name, description, type, created_at, updated_at) VALUES (4, 3, 'Summer Collection', 'Awesome recent created baby collection', 'launch', '2022-07-17 21:01:00.289445+00', '2022-07-17 21:01:00.289445+00');
INSERT INTO public.activity (id, event_id, name, capacity, allow_overbooking, description, date_range, start_at, end_at, responsible_id, type, created_at, updated_at) VALUES (1, 1, 'novice', 15, true, 'This is a class for kids between 6-14 years', '[2023-01-01,2024-01-01)', '16:00:00+02', '18:00:00+02', NULL, 'class', '2022-07-17 21:08:37.618117+00', '2022-07-17 21:08:37.618117+00');
INSERT INTO public.activity (id, event_id, name, capacity, allow_overbooking, description, date_range, start_at, end_at, responsible_id, type, created_at, updated_at) VALUES (2, 1, 'adolescent', 15, true, 'This is a class for adolescent between 15-21 years', '[2023-01-01,2024-01-01)', '17:00:00+02', '19:00:00+02', NULL, 'class', '2022-07-17 21:10:22.259098+00', '2022-07-17 21:10:22.259098+00');
INSERT INTO public.activity (id, event_id, name, capacity, allow_overbooking, description, date_range, start_at, end_at, responsible_id, type, created_at, updated_at) VALUES (3, 1, 'adult', 10, false, 'This is a class for adults +21 years', '[2023-01-01,2024-01-01)', '18:00:00+02', '20:00:00+02', NULL, 'class', '2022-07-17 21:11:15.750047+00', '2022-07-17 21:11:15.750047+00');
INSERT INTO public.activity (id, event_id, name, capacity, allow_overbooking, description, date_range, start_at, end_at, responsible_id, type, created_at, updated_at) VALUES (4, 2, 'Flexibility', 10, false, 'This is a special session with a gymnastic world champion', '[2022-08-07,2022-08-11)', '20:00:00+02', '21:00:00+02', 2, 'session', '2022-07-17 21:16:55.914314+00', '2022-07-17 21:16:55.914314+00');
INSERT INTO public.activity (id, event_id, name, capacity, allow_overbooking, description, date_range, start_at, end_at, responsible_id, type, created_at, updated_at) VALUES (5, 3, 'Tribal', 4, true, 'Special rhythm from a special teacher', '[2022-10-01,2022-11-01)', '16:00:00+02', '17:00:00+02', NULL, 'class', '2022-07-17 21:22:20.656028+00', '2022-07-17 21:22:20.656028+00');
INSERT INTO public.activity (id, event_id, name, capacity, allow_overbooking, description, date_range, start_at, end_at, responsible_id, type, created_at, updated_at) VALUES (6, 4, 'Golden SUN', 200, false, 'Sweetness, cuteness, party, spectacle, and much more', '[2022-07-20,2022-07-21)', '12:00:00+02', '18:00:00+02', 3, 'show', '2022-07-17 21:28:39.511639+00', '2022-07-17 21:28:39.511639+00');
SELECT pg_catalog.setval('public.activity_id_seq', 6, true);
SELECT pg_catalog.setval('public.business_id_seq', 3, true);
SELECT pg_catalog.setval('public.event_id_seq', 4, true);
SELECT pg_catalog.setval('public.user_id_seq', 3, true);
