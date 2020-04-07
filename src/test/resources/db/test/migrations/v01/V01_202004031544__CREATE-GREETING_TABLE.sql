-- Creation of greeting table at H2 in order to check behaviour
CREATE TABLE greeting (
    id          INTEGER         NOT NULL PRIMARY KEY,
    message     VARCHAR(255)    NOT NULL,
    PRIMARY KEY (id)
);

