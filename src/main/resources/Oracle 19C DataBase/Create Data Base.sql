
CREATE TABLE country(
    country_id int GENERATED BY DEFAULT AS IDENTITY,
    country_name varchar2(50) NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (country_id )
);

CREATE TABLE province(
    province_id int GENERATED BY DEFAULT AS IDENTITY,
    province_name varchar2(50) NOT NULL,
    country_id int NOT NULL,
    CONSTRAINT province_pk PRIMARY KEY (province_id ),
    CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(country_id)
);

CREATE TABLE district(
    district_id int GENERATED BY DEFAULT AS IDENTITY,
    district_name varchar2(50) NOT NULL,
    province_id int NOT NULL,
    CONSTRAINT district_pk PRIMARY KEY (district_id),
    CONSTRAINT fk_province FOREIGN KEY (province_id) REFERENCES province(province_id)
);

CREATE TABLE canton(
    canton_id int GENERATED BY DEFAULT AS IDENTITY,
    canton_name varchar2(50) NOT NULL,
    district_id int NOT NULL,
    CONSTRAINT canton_pk PRIMARY KEY (canton_id),
    CONSTRAINT fk_district FOREIGN KEY (district_id) REFERENCES district(district_id)
);

CREATE TABLE user_auth(
    user_auth_id int GENERATED BY DEFAULT AS IDENTITY,
    user_name VARCHAR2(60) NOT NULL,
    password VARCHAR(250) NOT NULL,
    user_rol VARCHAR2(15) CHECK( user_rol IN ('Admin','Supervisor','dependent','customer') ),
    user_active CHAR(1),
    update_date TIMESTAMP,
    CONSTRAINT user_auth_pk PRIMARY KEY (user_auth_id)
);

CREATE TABLE user_web(
    user_id int GENERATED BY DEFAULT AS IDENTITY,
    user_name1 VARCHAR2(60) NOT NULL,
    user_name2 VARCHAR2(60),
    user_lastname1 VARCHAR2(60) NOT NULL,
    user_lastname2 VARCHAR2(60),
    user_id_card VARCHAR2(10) NOT NULL,
    user_email VARCHAR2(80) NOT NULL,
    user_auth_id int NOT NULL,
    country_id int NOT NULL,
    province_id int NOT NULL,
    district_id int NOT NULL,
    canton_id int NOT NULL,
    user_address VARCHAR2(200) NOT NULL,
    CONSTRAINT user_web_pk PRIMARY KEY (user_id),
    CONSTRAINT fk_user_auth FOREIGN KEY (user_auth_id) REFERENCES user_auth(user_auth_id),
    CONSTRAINT fk_country_user FOREIGN KEY (country_id) REFERENCES country(country_id),
    CONSTRAINT fk_province_user FOREIGN KEY (province_id) REFERENCES province(province_id),
    CONSTRAINT fk_district_user FOREIGN KEY (district_id) REFERENCES district(district_id),
    CONSTRAINT fk_canton_id_user FOREIGN KEY (canton_id ) REFERENCES canton(canton_id)
);


CREATE TABLE type_product(
    type_product_id int GENERATED BY DEFAULT AS IDENTITY,
    type_product_name VARCHAR2(60) NOT NULL,     
    CONSTRAINT type_produc_pk PRIMARY KEY (type_product_id)
);

CREATE TABLE deparment_product(
    deparment_product_id int GENERATED BY DEFAULT AS IDENTITY,
    deparment_product_name VARCHAR2(80) NOT NULL,     
    CONSTRAINT deparment_product_pk PRIMARY KEY (deparment_product_id)
);

CREATE TABLE brand_product(
    brand_product_id int GENERATED BY DEFAULT AS IDENTITY,
    brand_product_name VARCHAR2(80) NOT NULL,     
    CONSTRAINT brand_product_pk PRIMARY KEY (brand_product_id)
);

CREATE TABLE product(
    product_id int GENERATED BY DEFAULT AS IDENTITY,
    product_name VARCHAR2(60) NOT NULL,
    type_product_id int NOT NULL,
    deparment_product_id int NOT NULL,
    brand_product_id int NOT NULL,
    product_description LONG,
    product_quantities int NOT NULL,
    product_price NUMBER (8,2) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (product_id),
    CONSTRAINT fk_type_product FOREIGN KEY (type_product_id) REFERENCES type_product(type_product_id),
    CONSTRAINT fk_deparment_product FOREIGN KEY (deparment_product_id) REFERENCES deparment_product(deparment_product_id),
    CONSTRAINT fk_brand_product FOREIGN KEY (brand_product_id) REFERENCES brand_product(brand_product_id)
);

CREATE TABLE discount(
    discount_id int GENERATED BY DEFAULT AS IDENTITY,
    description VARCHAR2(60) NOT NULL,
    percentage int NOT NULL,
    CONSTRAINT discount_pk PRIMARY KEY (discount_id)
);

CREATE TABLE payment_type(
    payment_type_id int GENERATED BY DEFAULT AS IDENTITY,
    payment_name VARCHAR2(15) CHECK( payment_name IN ('Credit Card','Debit Card','Bitcoin','Paypal','Cash') ),
    CONSTRAINT payment_type_pk PRIMARY KEY (payment_type_id)
);

CREATE TABLE payment_state(
    payment_state_id int GENERATED BY DEFAULT AS IDENTITY,
    state_name VARCHAR2(15) CHECK( state_name IN ('Credit','Pending Payment','Paid out') ),
    CONSTRAINT payment_state_pk PRIMARY KEY (payment_state_id)
);

CREATE TABLE invoice(
    id_invoice int GENERATED BY DEFAULT AS IDENTITY,
    user_id int NOT NULL,
    date_sell TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sale_total NUMBER (8,2) NOT NULL,
    discount_id int,
    payment_type_id int NOT NULL,
    payment_state_id int NOT NULL,
    CONSTRAINT invoice_pk PRIMARY KEY (id_invoice),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_web(user_id),
    CONSTRAINT fk_discount FOREIGN KEY (discount_id) REFERENCES discount(discount_id),
    CONSTRAINT fk_payment_type FOREIGN KEY (payment_type_id) REFERENCES payment_type(payment_type_id),
    CONSTRAINT fk_payment_state FOREIGN KEY (payment_state_id) REFERENCES payment_state(payment_state_id)
);

CREATE TABLE invoice_detail(
    in_detail_id int GENERATED BY DEFAULT AS IDENTITY,
    id_invoice INT NOT NULL,
    product_id int NOT NULL,
    quantities int NOT NULL,
    product_price NUMBER (8,2) NOT NULL,
    CONSTRAINT invoice_detail_pk PRIMARY KEY (in_detail_id),
    CONSTRAINT fk_id_invoice FOREIGN KEY (id_invoice) REFERENCES invoice(id_invoice),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(product_id)
);
