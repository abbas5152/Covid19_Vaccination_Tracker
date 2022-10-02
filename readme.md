
# REST API for an Covid-19 Application

* We have developed this REST API for an Covid-19 Application. This API performs all the fundamental CRUD operations of any Covid-19 Application platform with user validation at every step.
* This project is developed by team of 6 Back-end Developers during project week in Masai School. 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger
* Lombook

## Modules

* Login, Logout Module
* User Module
* Admin Module

## Features

* User and Admin authentication & validation with session uuid having.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid session token can add/update/delete driver or customer from main database
    * Admin can access the details of different Appointment, Member ,Vaccine Center ,Vaccine Inventory and Vaccine Ragistration.
* User Features:
    * A user can register himself or herself on the platform.
    * He/She can check the vaccine centres and vaccine availabilty.
    * If vaccine is available, can book an appointment slot.
    * After booking an appointment, he will get appointment details for the vaccine dose.    


## Contributors

* [@Abbas Ghaniwala](https://github.com/abbas5152)
* [@Ankit Sah](https://github.com/Ankit-Sah-Developer)
* [@Gagan Patil](https://github.com/GaganPatil1995)
* [@Shubham Agnihotry](https://github.com/Theagni0070)
* [@Akash Kumar Singh](https://github.com/akashsinghdto55)




## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/nvFARHAN/cowin.gov.in/blob/master/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8889

    spring.datasource.url=jdbc:mysql://localhost:3306/sb201db;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=mysqlroot

```


## API Root Endpoint

`https://localhost:8889/`

`http://localhost:8889/swagger-ui/`


## API Module Endpoints

### Login Module

* `POST //api/adminLogin` : Admin can login with mobile number and password provided at the time of registation
* `POST //api/customerLogin` : customer can login with mobile number and password provided at the time of registation
* `DELETE //api/adminLogut` : Admin can logout using key
* `DELETE //api/customerLogut` : customer can logout using key
<!--
### User Module


* `GET /customer/AllRegistration` : user can see all the registration
* `POST /customer/Id` : User can add using Id class id
* `PUT /customers/Member` : user can update member 
* `DELETE/customers/Member` : user can delete member 
........
And Many more Methods We Implemented in our controller


### Admin Module

* `POST /admin/addInventory` : only admin can add the inventory details using unique key
* `GET /admin/GetAllInventory` :only admin can access inventory details using unique key
* `DELETE /admin/DeleteInventory` : only admin can delete the inventory details using unique key

........
And Many more Methods We Implemented in our controller


### Sample API Response for Admin Login

`POST   localhost:8889/login/adminlogin`

* Request Body

```
    {
        "mobileNo": "7056319981",
        "password": "Clickme@007"
    }
```

* Response

```
   CurrentAdminSession(id=11, adminId=10, uuid=ZaVLaK, localDateTime=2022-10-02T11:13:42.772910500)
   
```

## Video Explainer of flow control
 <a href="https://drive.google.com/drive/folders/1h6Mo5Gty6InD-nOOmJhgOeK96G7_RD5V?usp=sharing">**Video Drive Link** </a>
 
 
### E-R Diagram Of Covid-19 Application
---
<img src="https://github.com/abbas5152/wasteful-cast-8657/blob/main/CovidERDiagram.JPG" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Swagger UI


---

### Login Controller

---

<img src="https://github.com/abbas5152/wasteful-cast-8657/blob/main/Covid_Vaccination_Tracker/swagger-ui%20Images/logincontroller.JPG" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Admin Controller

---

<img src="https://github.com/abbas5152/wasteful-cast-8657/blob/main/Covid_Vaccination_Tracker/swagger-ui%20Images/admincontroller.JPG" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### User Controller

---

<img src="https://github.com/abbas5152/wasteful-cast-8657/blob/main/Covid_Vaccination_Tracker/swagger-ui%20Images/customercontroller.JPG" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---


### Error Controller

---


<img src="https://github.com/abbas5152/wasteful-cast-8657/blob/main/Covid_Vaccination_Tracker/swagger-ui%20Images/errorcontroller.JPG" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">







