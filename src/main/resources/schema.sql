CREATE TABLE Currency (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    ch_name VARCHAR(255),
    rate DOUBLE,
    update_time TIMESTAMP
);