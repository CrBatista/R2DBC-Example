-- Creation of greeting table
CREATE TABLE greeting (
    id          INTEGER         NOT NULL PRIMARY KEY,
    message     VARCHAR(255)    NOT NULL,
    PRIMARY KEY (id)
);


COMMENT ON TABLE greeting IS 'Greetings stored';

COMMENT ON COLUMN greeting.id IS 'Greeting ID. Primary key';
COMMENT ON COLUMN greeting.message IS 'Greeting message';
