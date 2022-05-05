create table postalcode_city (
                                 id integer primary key,
                                 zipcode varchar,
                                 city varchar
);
create sequence postalcode_city_seq start with 1 increment by 1;

create table customer (
                          id varchar primary key,
                          first_name varchar,
                          last_name varchar,
                          email_address varchar,
                          street_name varchar,
                          street_number varchar,
                          fk_postalcode_city integer,
                          phone_number varchar,
                          foreign key (fk_postalcode_city) references postalcode_city (id)
);

create table item (
                      id varchar primary key,
                      name varchar,
                      description varchar,
                      price numeric,
                      amount integer
);

create table order_overview (
                                id varchar primary key,
                                fk_customer_id varchar,
                                foreign key (fk_customer_id) references customer (id)
);

create table item_group (
                            id varchar primary key,
                            fk_item_id varchar,
                            amount integer,
                            shipping_date date,
                            fk_order_id varchar,
                            foreign key (fk_item_id) references item (id),
                            foreign key (fk_order_id) references order_overview (id)
);