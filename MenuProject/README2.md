
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
- ITEM assumption- photo is just a url from s3 or file location in server and hence a string column. Although it is not added as part of entity.
--The dates and schedules are deliberately left as string since there are no major functionality associated with it right now.
-In case of front end - it can send the start and end date time stamp which can be converted and broken into respective date and time for respective column insertions.

#SWAGGER
http://localhost:8080/foodmenu/swagger-ui.htm

#Endpoints defined
- create menu
-create item
-get list of menu
-group by item price/rating
-get price of all item
-get active sub menu

#Testcase
- Test case of itemService has been written with Mockito.



#11. Validate if input data is correct for #3 and #4. Consider the fact that the input payload
#may grow in the future. What would you do to encapsulate the validations and avoid
#duplicated ones? How would you validate the input data in case you need to register
#through rest endpoints the menus and items (there is no need to create the endpoints
#to register, it is just a question)?

- One of the DTO is already encapsulated with status code and err message.
- For Validation, we can use SPRING Validator classes which will return errormap with err code if needed.
-All the business validation which are functional can be taken care there.
-All the not null checks and value checks basics can be taken care by javax validation annotation @NotNull, @Max etc.


 
#6. If there is a new logic, and given a menu, the application should print in the console
#all the submenu names. What change would you do?

-This can be achieved with the same logic where active submenu count is extracted, rather than sending the count send the list of names
- Can be encapsulated with a DTO - which will have count and List<String>


