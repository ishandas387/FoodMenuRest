
#Notes
#A Spring BOOT application with H2 in memory database
MENU -> SUB MENU -> ITEMS
#ER Design->
-Menu table is responsible for storing menu and submenus
-Menu ID is Auto generated 
-Each menu has isActive flag.
-Each menu will also have isSubMenuOf column to state the parent menu ID.
-ITEM table is responsible to store items
-Each item has menu ID
-ONE TO MANY relationship from MENU to ITEM
-MANY TO ONE relationship from ITEM to MENU
-owning side MENU
- ITEM assumption- photo is just a url from s3 or file location in server and hence a string column.

#SWAGGER
http://localhost:8080/foodmenu/swagger-ui.htm


 
1. A menu has a list of items (at least 1), and a list of submenus (where each list
element is a menu, that might be empty). Each menu additionally has a description
and a flag that states if the menu is active or not.
2. A menu item has a name, description, a price with its respective currency, a photo,
available days, validity of start date and end date, start and end schedule for which it
applies and a numeric ranking from 1 to 5.
Example: the menu item “Ham and cheese pizza” is valid Fridays to Sundays from
20:00hrs to 23:00hrs, since 1st March to 1st April and has a 5 ranking.
3. A restaurant can offer different types of menus, for example: “Daily menu” or
“Summer menu”.
Description
This section has the backend requirements for the system which objective is to manage the
restaurant menus. For this purpose, the candidate should define the appropriate
architecture.
1. The information will be consumed through REST web services.
2. Rest Endpoint. The application should list the menus to be consumed by the front
end.
3. Rest Endpoint: Given a menu, the application should list the items grouped by price.
Take into consideration that in the future, it can be grouped by ranking.
4. Method (*): given a menu, the application should return the sum of prices of all its
items (including submenus).
5. Method (*): given a menu, the application should return the quantity of active
submenus.
6. If there is a new logic, and given a menu, the application should print in the console
all the submenu names. What change would you do?
7. The application should log all the execution time for every method or a group of them.
8. The menu data that you hand in, should be a mock. There is no need to obtain that
kind of information from a database. Put the mocks in the appropriate place.
9. Create the necessary entities.
10. Assume that there is no infinite loop in each menu.
11. Validate if input data is correct for #3 and #4. Consider the fact that the input payload
may grow in the future. What would you do to encapsulate the validations and avoid
duplicated ones? How would you validate the input data in case you need to register
through rest endpoints the menus and items (there is no need to create the endpoints
to register, it is just a question)?
12. Unit testing. Add some examples to showcase the usage of unit tests in the project.