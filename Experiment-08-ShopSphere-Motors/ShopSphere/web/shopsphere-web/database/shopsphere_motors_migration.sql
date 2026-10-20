
USE shopsphere;

CREATE TABLE IF NOT EXISTS car_bookings (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    car_id INT NOT NULL,
    booking_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    total_amount DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_car_bookings_user
        FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_car_bookings_car
        FOREIGN KEY (car_id) REFERENCES products(id)
);

-- Add preferred_date only if missing
SET @column_exists = (
    SELECT COUNT(*)
    FROM information_schema.columns
    WHERE table_schema = DATABASE()
      AND table_name = 'car_bookings'
      AND column_name = 'preferred_date'
);

SET @sql = IF(
    @column_exists = 0,
    'ALTER TABLE car_bookings ADD COLUMN preferred_date DATE NULL',
    'SELECT 1'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Add message only if missing
SET @column_exists = (
    SELECT COUNT(*)
    FROM information_schema.columns
    WHERE table_schema = DATABASE()
      AND table_name = 'car_bookings'
      AND column_name = 'message'
);

SET @sql = IF(
    @column_exists = 0,
    'ALTER TABLE car_bookings ADD COLUMN message VARCHAR(500) NULL',
    'SELECT 1'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
