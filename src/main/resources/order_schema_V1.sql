CREATE TABLE POSTALCODE_CITY (
                                 ID INTEGER PRIMARY KEY,
                                 ZIPCODE VARCHAR,
                                 CITY VARCHAR
);
CREATE SEQUENCE POSTALCODE_CITY_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE CUSTOMER (
                          ID VARCHAR PRIMARY KEY,
                          FIRST_NAME VARCHAR,
                          LAST_NAME VARCHAR,
                          EMAIL_ADDRESS VARCHAR,
                          STREET_NAME VARCHAR,
                          STREET_NUMBER VARCHAR,
                          FK_POSTALCODE_CITY INTEGER,
                          PHONE_NUMBER VARCHAR,
                          FOREIGN KEY (FK_POSTALCODE_CITY) REFERENCES POSTALCODE_CITY (ID)
);

CREATE TABLE ITEM (
                      ID VARCHAR PRIMARY KEY,
                      NAME VARCHAR,
                      DESCRIPTION VARCHAR,
                      PRICE NUMERIC,
                      AMOUNT INTEGER
);

CREATE TABLE EURDER (
                        ID VARCHAR PRIMARY KEY,
                        CUSTOMER_ID VARCHAR,
                        FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (ID)
);

CREATE TABLE ITEM_GROUP (
                            ID VARCHAR PRIMARY KEY,
                            FK_ITEM_ID VARCHAR,
                            AMOUNT INTEGER,
                            SHIPPING_DATE DATE,
                            FK_EURDER_ID VARCHAR,
                            FOREIGN KEY (FK_ITEM_ID) REFERENCES ITEM (ID),
                            FOREIGN KEY (FK_EURDER_ID) REFERENCES EURDER (ID)
);