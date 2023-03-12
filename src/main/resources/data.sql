USE `food_delivery`;

# pizzas
INSERT INTO `foods` (name, price, description) VALUES ('Margherita',7.99,'Tomato sauce, mozzarella, spices');
INSERT INTO `foods` (name, price, description) VALUES ('Caprese',8.99,'Tomato sauce, mozzarella, baby mozzarella, cherry tomatoes, basil pesto');
INSERT INTO `foods` (name, price, description) VALUES ('Carbonara',9.49,'Cream, mozzarella, bacon, yolk, mushrooms, parmesan');
INSERT INTO `foods` (name, price, description) VALUES ('Quattro Formaggi',10.99,'Philadelphia sauce, smoked cheese, blue cheese, scamorza, cheddar, pesto, mozzarella, cherry tomatoes');
INSERT INTO `foods` (name, price, description) VALUES ('Quattro Staggioni',10.99,'Tomato sauce, mozzarella, ham, bacon, mushrooms, pickles');
INSERT INTO `foods` (name, price, description) VALUES ('Bonifacio',9.99,'Tomato sauce, mozzarella, chicken, pickled cucumbers, smoked cheese, corn, spices');
INSERT INTO `foods` (name, price, description) VALUES ('Di Montagna',9.99,'Tomato sauce, mozzarella, ham, bacon, sausage, mushrooms, spices');
INSERT INTO `foods` (name, price, description) VALUES ('Pepperoni',10.99,'Tomato sauce, mozzarella, hot Italian sausage');
INSERT INTO `foods` (name, price, description) VALUES ('Pizza with prosciutto',11.99,'Tomato sauce, mozzarella, baby spinach, arugula, cherry tomatoes, prosciutto, parmesan');
INSERT INTO `foods` (name, price, description) VALUES ('Capricciosa',9.99,'Tomato sauce, cheese, ham, mushrooms, olive oil, oregano');
INSERT INTO `foods` (name, price, description) VALUES ('Pizza with mushrooms',8.99,'Tomato sauce, mushrooms, mozzarella, thyme, olive oil');

# tortillas
INSERT INTO `foods` (name, price, description) VALUES ('Porkesa',11.99,'Pulled pork, caramel onion, barbecue sauce, cheese, mixed salads, tomatoes, mushroom mayonnaise, french fries');
INSERT INTO `foods` (name, price, description) VALUES ('Chicken',9.99,'Crispy chicken with sesame, cheese, mixed salads, tomatoes, garlic sauce, ketchup, french fries');

# burgers
INSERT INTO `foods` (name, price, description) VALUES ('With pulled pork',10.99,'Pulled pork, caramelized onions, mushroom mayonnaise, barbecue sauce, mixed salads, tomatoes, cheddar slice, fries');
INSERT INTO `foods` (name, price, description) VALUES ('Chicken Burger',10.99,'Crispy chicken, cheddar slice, tomatoes, mixed salads, garlic sauce, fries');
INSERT INTO `foods` (name, price, description) VALUES ('Double beef',13.99,'two beef burgers, mixed salads, crispy onions, cheddar, ketchup. Cucumbers, tartar sauce, burger sauce, jalapeño peppers, fries');
INSERT INTO `foods` (name, price, description) VALUES ('Beef',10.99,'beef burger, cheddar slice, bacon chips, crispy onion, sour cream. cucumbers, barbecue sauce, tartar sauce, fries');
INSERT INTO `foods` (name, price, description) VALUES ('Black Angus',10.99,'Beef burger, crispy quinoa, avocado with tomato sauce, Irish cheddar, tomato, chili mayo, french fries, brioche bun and ketchup');
INSERT INTO `foods` (name, price, description) VALUES ('My Crazy',11.99,'Burger made with chopped Spanish Black Angus beef, Cheddar cheese, soy-garlic mayonnaise, sauteed onions, pickles, brioche bun, fries, ketchup');
INSERT INTO `foods` (name, price, description) VALUES ('Mr. Cheesy',12.99,'Black Angus beef burger, extra cheddar sauce, Irish cheddar, bacon, caramelized onions, soy-garlic mayo, brioche bun, fries and ketchup');
INSERT INTO `foods` (name, price, description) VALUES ('Satoshi',14.99,'Overnight baked brioche bun, 100% fresh ground free range limousine beef, crispy bacon, melted cheddar, fresh iceberg, house pickled red onions, pickles, homemade sauce');
INSERT INTO `foods` (name, price, description) VALUES ('Eat the spinach',8.99,'Green bread with spinach, hemp seed meatballs, spinach and mushrooms, tomato and tartar with vegan mayonnaise');
INSERT INTO `foods` (name, price, description) VALUES ('Vegan Burger',12.99,'Burger made from peas, beets, onions and spices, with vegan cheddar cheese, vegan mayo, iceberg, tomato, avocado, red onion and vegan bun without butter and eggs, fries and ketchup');

# fries
INSERT INTO `foods` (name, price, description) VALUES ('Plain french fries',3.99,'French fries, sea salt');
INSERT INTO `foods` (name, price, description) VALUES ('Garlic fries',4.99,'French fries, garlic sauce');
INSERT INTO `foods` (name, price, description) VALUES ('Ranch fries',5.99,'French fries, ranch sauce');
INSERT INTO `foods` (name, price, description) VALUES ('Chili con carne fries',8.99,'French fries, chili con carne');
INSERT INTO `foods` (name, price, description) VALUES ('Sweet fries',6.99,'French fries, garlic sauce');

# sauces
INSERT INTO `foods` (name, price, description) VALUES ('Ketchup',1.20,'Heinz');
INSERT INTO `foods` (name, price, description) VALUES ('Mayonnaise',1.20,'Heinz');
INSERT INTO `foods` (name, price, description) VALUES ('BBQ',1.20,'Heinz');
INSERT INTO `foods` (name, price, description) VALUES ('Ranch',1.20,'Heinz');
INSERT INTO `foods` (name, price, description) VALUES ('Mustard',1.20,'Heinz');
INSERT INTO `foods` (name, price, description) VALUES ('Burger sauce',1.20,'Homemade burger sauce with secret ingredients');
INSERT INTO `foods` (name, price, description) VALUES ('Caesar',1.20,'Heinz');
INSERT INTO `foods` (name, price, description) VALUES ('Sweet chili',1.20,'Heinz');

# desserts
INSERT INTO `foods` (name, price, description) VALUES ('Brownie',4.59,'With ice cream and fruit');
INSERT INTO `foods` (name, price, description) VALUES ('Cookies',0.99,'With chocolate chips');
INSERT INTO `foods` (name, price, description) VALUES ('Muffins',2.59,'Filled with blueberry cream');
INSERT INTO `foods` (name, price, description) VALUES ('Doughnuts',1.79,'Filled with vanilla cream');
INSERT INTO `foods` (name, price, description) VALUES ('White chocolate mousse',3.99,'With Lindt chocolate');
INSERT INTO `foods` (name, price, description) VALUES ('Ice cream',2.50,'Salted caramel');

# drinks
INSERT INTO `drinks` (name, price, quantity) VALUES ('Coca cola',1.99,0.5);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Fanta',1.99,0.5);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Sprite',1.99,0.5);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Coca cola zero',1.99,0.5);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy orange',1.99,0.33);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy red orange',1.99,0.33);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy green apple',1.99,0.33);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy pineaple',1.99,0.33);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy peach',1.99,0.33);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Water',1.00,0.5);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Water',2.50,1.5);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy lemonade',2.99,0.4);
INSERT INTO `drinks` (name, price, quantity) VALUES ('Cappy lemonade - lime',2.99,0.4);

