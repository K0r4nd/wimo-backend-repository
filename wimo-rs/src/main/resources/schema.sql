CREATE TABLE IF NOT EXISTS users (id VARCHAR(36) PRIMARY KEY,password VARCHAR(255) NOT NULL,name VARCHAR(255),surname VARCHAR(255),address VARCHAR(255), gcm_token VARCHAR(255))

CREATE TABLE IF NOT EXISTS orders (id VARCHAR(36) PRIMARY KEY,user_Id VARCHAR(36),tracking_Id VARCHAR(255) NOT NULL,shipper_Name VARCHAR(255),destination_Address VARCHAR(255),shipping_Date BIGINT,delivery_Date BIGINT,status VARCHAR(255),status_Text VARCHAR(255),last_Status_Update BIGINT,sent_By_User BOOLEAN,FOREIGN KEY (user_Id) REFERENCES users(id))
