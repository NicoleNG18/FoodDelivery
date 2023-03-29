# FoodDelivery
This is my final project for SoftUni Java Web module.

Overview:
-----------------

Food Delivery is web application for making food orders from restaurants.
Before registration, you only can view the menu and maybe choose dish.
After registration and login you can add some products to your cart, add contact number and address for delivery and make order.


Functionalities:
-----------------------------------------------------

Before registration people can view the menu and submit something in the contact form.

There are three types of users: ADMIN, WORKER, USER.
After registration, everyone automatically gets the "USER" role.
After that, only the admin can change roles.


ADMIN functionalities 
  - The admin of the application is one person, who is manually set up
  - View all users along with their details and change their roles (can add or remove user role "WORKER")
  - View all orders history and finish orders which are in progress they are already delivered 
  - Add, edit or delete product from the menu
  - View its personal information, but cannot edit anything

WORKER functionalities:
  - Manually set up workers are two - with usernames "workerOne" and "workerTwo"
  - Only view the menu
  - View all orders history and finish orders which are in progress they are already delivered 
  - View its personal information and edit username

USER functionalities:
  - Manually set up workers are two - with usernames "workerOne" and "workerTwo"
  - Add products to the cart
  - Remove products from the cart
  - Make orders
  - View its own orders history and orders' details
  - View its own cart
  - View its own personal information and edit username
  - View contact form and submit some message