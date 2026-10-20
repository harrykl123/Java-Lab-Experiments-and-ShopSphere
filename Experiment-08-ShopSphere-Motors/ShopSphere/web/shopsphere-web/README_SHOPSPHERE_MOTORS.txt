ShopSphere Motors - Java 21 / JSP / Servlet / MySQL
===================================================

This package extends the supplied ShopSphere web project. It reuses the existing
users, categories, products and wishlist tables. Car inventory is identified by
categories named SUV, Sedan, Hatchback and Luxury. Fashion categories/products
are not shown on the car listing page.

FEATURES INCLUDED
- Existing registration/login/logout, with BCrypt password verification.
- Car inventory list with keyword/category/max-price filters.
- Car details page.
- Add/remove wishlist items.
- Car visit/test-drive booking requests and user booking history.
- Admin dashboard to add/delete car listings and update booking status.
- Responsive styling.
- Safe migration SQL for phone column (if missing), car categories/sample rows,
  and the separate car_bookings table.

IMPORTANT DATABASE SAFETY
- Do NOT run SQL containing DROP TABLE against your current database. It can
  permanently delete users, products, orders, and other data.
- Use database/shopsphere_motors_migration.sql against the existing shopsphere
  database. Back up the database first.
- The old database/shopsphere.sql file is the original schema snapshot, not a
  migration to run over an existing database.
- Existing tables must have the columns used by the original project. The
  users.phone column is added by the migration only if it is missing.

DATABASE PASSWORD
DBConnection reads:
  DB_USER (defaults to root)
  DB_PASSWORD (defaults to empty)
  SHOPSPHERE_DB_URL (optional override)

For example, in the same PowerShell window from which you launch Tomcat:
  $env:DB_USER = "root"
  $env:DB_PASSWORD = "YOUR_MYSQL_PASSWORD"
  $env:SHOPSPHERE_DB_URL = "jdbc:mysql://localhost:3306/shopsphere?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kolkata"

Do not commit or share your real database password. Since a password was present
in the submitted source archive, change/rotate that MySQL credential if it is
still active.

BUILD AND DEPLOY
1. Open PowerShell in the shopsphere-web folder (the folder containing pom.xml).
2. Set the DB environment variables above in the same shell used to start Tomcat.
3. Run:
     mvn clean package
4. Copy target\shopsphere.war to Tomcat's webapps folder (stop Tomcat first if
   the app is already deployed), remove the old exploded webapps\shopsphere
   directory, then start Tomcat again.
5. Open:
     http://localhost:8080/shopsphere/
   The default landing page is index.jsp.
6. Open:
     http://localhost:8080/shopsphere/cars

ADMIN ACCESS
Register a normal account first, then promote that account using MySQL Workbench:
  UPDATE users SET role='ADMIN' WHERE email='your-account-email@example.com';
Use your actual registered email in the query. Do not expose admin registration
to the public. Log out and back in after changing the role.

BOOKINGS
Booking requests are not online payments. They are saved as PENDING until an
admin updates them to CONFIRMED, CANCELLED or COMPLETED.
