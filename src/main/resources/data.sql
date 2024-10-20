CREATE TABLE Currency (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    chName VARCHAR(255),
    rate DOUBLE,
    updateTime TIMESTAMP
);

INSERT INTO Currency (name, chName, rate, updateTime) VALUES ('USD', '美元', 68180.0701, CURRENT_TIMESTAMP);
INSERT INTO Currency (name, chName, rate, updateTime) VALUES ('GBP', '英鎊', 52241.2742, CURRENT_TIMESTAMP);
INSERT INTO Currency (name, chName, rate, updateTime) VALUES ('EUR', '歐元', 62708.6195, CURRENT_TIMESTAMP);