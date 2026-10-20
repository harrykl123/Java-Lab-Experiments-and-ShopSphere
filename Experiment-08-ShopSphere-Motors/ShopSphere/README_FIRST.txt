SHOPSPHERE MOTORS - START HERE

1. Open web/shopsphere-web in VS Code.
2. Read web/shopsphere-web/README_SHOPSPHERE_MOTORS.txt for exact setup.
3. Back up your database before making changes.
4. Run web/shopsphere-web/database/shopsphere_motors_migration.sql in MySQL Workbench
   after selecting the shopsphere database. Do not run DROP TABLE statements.
5. Configure DB_USER and DB_PASSWORD, then run mvn clean package.
6. Promote your own registered account to ADMIN using the SQL shown in the README.

The Java source was checked with javac using local servlet API stubs. A full Maven
build and live MySQL/Tomcat test could not be run in this environment because
Maven and a running database/Tomcat instance are not available here.
