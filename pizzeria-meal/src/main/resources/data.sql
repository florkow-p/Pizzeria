--PIZZA
INSERT INTO meal (id, meal_type, name, price) VALUES (1, 'PIZZA', 'margheritta', 20);
INSERT INTO pizza (id) VALUES (1);
INSERT INTO meal (id, meal_type, name, price) VALUES (2, 'PIZZA', 'vegetariana', 22);
INSERT INTO pizza (id) VALUES (2);
INSERT INTO meal (id, meal_type, name, price) VALUES (3, 'PIZZA', 'tosca', 25);
INSERT INTO pizza (id) VALUES (3);
INSERT INTO meal (id, meal_type, name, price) VALUES (4, 'PIZZA', 'venecia', 25);
INSERT INTO pizza (id) VALUES (4);
INSERT INTO meal (id, meal_type, name, price) VALUES (5, 'TOPPING', 'podwójny ser', 2);
INSERT INTO topping (id) VALUES (5);
INSERT INTO meal (id, meal_type, name, price) VALUES (6, 'TOPPING', 'salami', 2);
INSERT INTO topping (id) VALUES (6);
INSERT INTO meal (id, meal_type, name, price) VALUES (7, 'TOPPING', 'szynka', 2);
INSERT INTO topping (id) VALUES (7);
INSERT INTO meal (id, meal_type, name, price) VALUES (8, 'TOPPING', 'pieczarki', 2);
INSERT INTO topping (id) VALUES (8);

--DINNER
INSERT INTO meal (id, meal_type, name, price) VALUES (9, 'DINNER', 'schabowy', 30);
INSERT INTO dinner (id, allowed_base_ingredient) VALUES (9, true);
INSERT INTO meal (id, meal_type, name, price) VALUES (10, 'DINNER', 'ryba z frytkami', 28);
INSERT INTO dinner (id, allowed_base_ingredient) VALUES (10, false);
INSERT INTO meal (id, meal_type, name, price) VALUES (11, 'DINNER', 'placek po węgiersku', 27);
INSERT INTO dinner (id, allowed_base_ingredient) VALUES (11, false);

--BASE_INGREDIENT
INSERT INTO meal (id, meal_type, name, price) VALUES (12, 'BASE_INGREDIENT', 'frytki', 0);
INSERT INTO base_ingredient (id) VALUES (12);
INSERT INTO meal (id, meal_type, name, price) VALUES (13, 'BASE_INGREDIENT', 'ryż', 0);
INSERT INTO base_ingredient (id) VALUES (13);
INSERT INTO meal (id, meal_type, name, price) VALUES (14, 'BASE_INGREDIENT', 'ziemniaki', 0);
INSERT INTO base_ingredient (id) VALUES (14);

--EXTRAS
INSERT INTO meal (id, meal_type, name, price) VALUES (15, 'EXTRAS', 'bar sałatkowy', 5);
INSERT INTO extras (id) VALUES (15);
INSERT INTO meal (id, meal_type, name, price) VALUES (16, 'EXTRAS', 'zestaw sosów', 6);
INSERT INTO extras (id) VALUES (16);

--MEAL
INSERT INTO meal (id, meal_type, name, price) VALUES (17, 'SOUP', 'pomidorowa', 12);
INSERT INTO meal (id, meal_type, name, price) VALUES (18, 'SOUP', 'rosół', 10);
INSERT INTO meal (id, meal_type, name, price) VALUES (19, 'DRINKS', 'kawa', 5);
INSERT INTO meal (id, meal_type, name, price) VALUES (20, 'DRINKS', 'herbata', 5);
INSERT INTO meal (id, meal_type, name, price) VALUES (21, 'DRINKS', 'cola', 5);