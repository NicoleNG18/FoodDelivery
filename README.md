# FoodDelivery

This is my final project for SoftUni Java Web module.

Overview:
-----------------

Food Delivery is a web application for making food orders from restaurants.
After registration and login you can add some products to your cart and make an order. 
If you have discount code, you can use it for 10% discount.(valid codes at the moment: "FOOD23", "PIZZA4", "EASTER")

Functionalities:
-----------------------------------------------------

Before registration people can view the menu and submit something in the contact form.

There are three types of users: ADMIN, WORKER, USER.
After registration, everyone automatically gets the "USER" role.
After that, only the admin is able to change roles.

ADMIN functionalities

- The admin of the application is one person, who is manually set up
- View all users along with their details and change their roles (add or remove user role "WORKER")
- View all orders' history and finish or cancel orders
- Add, edit or delete product from the menu
- View its personal information, but cannot edit anything

WORKER functionalities:

- Manually set up workers are two - with usernames "workerOne" and "workerTwo"
- Only view the menu
- View all orders' history and finish or cancel orders
- View its personal information and edit names

USER functionalities:

- Manually set up plain users are two - with usernames "nikol" and "ivan"
- Add products to the cart
- Remove products from the cart
- Make orders
- View its own orders' history and orders' details
- View its own cart
- View its own personal information and edit names
- View contact form and submit some message


Pages:
--------------------------------------------

- Contact us page:

![img_30.png](img_30.png)

- Menu page:

![img_31.png](img_31.png)

- Menu - category page with anonymous user or worker:

![img_32.png](img_32.png)

- Register page:

![img_33.png](img_33.png)

- Login page:

![img_34.png](img_34.png)

- Home page with anonymous user:

![img_35.png](img_35.png)

- Home page with admin or worker:

![img_36.png](img_36.png)

- Home page with user:

![img_37.png](img_37.png)

- Menu - category page with admin user:

![img_38.png](img_38.png)

- Product edit page:

![img_39.png](img_39.png)

  - All orders history page with admin or worker logged:

![img_40.png](img_40.png)

  - Order's details page:

![img_41.png](img_41.png)

  - All users page:

![img_42.png](img_42.png)

  - Add product page:

![img_44.png](img_44.png)

  - Personal information with admin logged:

![img_45.png](img_45.png)

  - Personal information page with worker or user logged:

![img_46.png](img_46.png)

  - Edit user page:

![img_47.png](img_47.png)

  - Menu - category page with user:

![img_48.png](img_48.png)

  - Cart page:

![img_49.png](img_49.png)

  - Finalize order page:

![img_50.png](img_50.png)

  - Orders history for logged user:

![img_51.png](img_51.png)

  - Page for closed restaurant:

![img_52.png](img_52.png)

Test accounts:
---------------------------------------------

Password for all: "secret1@"

Role admin:

- username: admin

Role worker:

- username: workerOne
- username: workerTwo

Role user:

- username: nikol
- username: ivan

SoftUni additional requirements:
--------------------------------------

- Interceptor: the working time of the restaurant is 11AM-10PM. Outside the working hours when a logged user tries to
  make order, it will be redirected to page which will inform user with the working time.
- Fetch: displaying order's details
- Error handling: global controller advice for not found object, specific controller exception for wrong menu category.
  Whitelabel error page replaced with custom error.html page.
- Scheduled jobs - Every day, there are promotions. Depending on the day of week, some products have lower prices.
- Test coverage:
  ![img_29.png](img_29.png)

Food Delivery Project - Nikol Georgieva - 2023 &copy; All rights reserved.