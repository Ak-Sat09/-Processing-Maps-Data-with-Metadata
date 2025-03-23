CREATE TABLE locations (
    id VARCHAR(10) PRIMARY KEY,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);

CREATE TABLE metadata (
    id VARCHAR(10) PRIMARY KEY,
    type VARCHAR(50),
    rating DECIMAL(3,1),
    reviews INT,
    FOREIGN KEY (id) REFERENCES locations(id)
);
