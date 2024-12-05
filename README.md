# 🪑 Furnii - Furniture E-Commerce Web Application

**Furnii** is a furniture-focused e-commerce web application designed for both buyers and sellers. It combines a pre-designed frontend template with a custom-built backend to provide a seamless user experience for furniture shopping and management. This project utilizes **Java**, **Hibernate**, **MySQL**, **JavaScript**, and a free **Molla eCommerce HTML template** for the frontend.

---
# 🌐 Demo

Watch the demo on YouTube: https://youtu.be/RbrO3GQXhpQ

---
## 🎯 **Features**

### **User Roles:**
1. **Buyer:**
   - Sign in and Sign up.
   - Browse and search furniture using advanced search.
   - Add items to a session-based or database-based cart.
   - Complete purchases with **PayHere** payment gateway integration.
   - Receive purchase invoices directly via email.

2. **Seller:**
   - Manage product listings.
   - View listed products.
   - Purchase items as a buyer.

### **Additional Features:**
- **Product Listing:** Furniture catalog with detailed product descriptions.
- **Secure Transactions:** Payment gateway integration for seamless transactions.
- **Email Notifications:** Automated invoice emails upon successful purchases.
- **User-Friendly Interface:** Leveraging the **Molla eCommerce HTML Template** for a modern and responsive design.

---

## 🔧 **Technologies Used**
- **Frontend Template:** [Molla eCommerce HTML Template](https://www.templateshub.net/template/molla-eCommerce-html-template)
- **Backend:** Java (Servlets), Hibernate
- **Database:** MySQL
- **Frontend Scripting:** JavaScript
- **Payment Gateway:** PayHere
- **Email Gateway:** JavaMail API for sending invoices

---

## 🏗️ **Getting Started**

- **Clone the Repository**
- git clone [Project](https://github.com/SandeepaLakruwan/Java_Hibernate_E_Commerce_Website.git)
- cd furnii

- Set Up the Database
- Import the database:
- Locate the furnii.sql file in the root of the repository.
- Import it into your MySQL server using tools like phpMyAdmin or the MySQL CLI.

- Update the database configuration in the backend (Furnii/src/java/hibernate.cfg.xml): Add password
  
- Configure Email Gateway
- Update the email gateway settings in Furnii/src/java/model/Mail.java: Set email and password

- Configure the PayHere Payment Gateway
- Update payment gateway settings in Furnii/src/java/controller/VerifyPayment.java & checkout.java: Set Merchant Id & Merchanr Secret to it.

- Run the Application
- Open the project in your IDE (e.g., NetBeans or IntelliJ IDEA).
- Build and deploy the application to your local Glassfish server.
- Access the application at: http://localhost:8080/Furnii

## 🤝 Contributions
- Contributions are welcome! Feel free to fork the repository.

## 📧 Contact
- If you have any questions, feel free to reach out via sandeepalakruwan@gmail.com

## 📜 License
- This project is licensed under the MIT License.

## Enjoy buying and selling furniture on Furnii! 🪑✨
