CREATE TABLE IF NOT EXISTS customer (
                id INT PRIMARY KEY AUTO_INCREMENT,
                first_name NVARCHAR(60) NOT NULL,
                last_name NVARCHAR(60) NOT NULL,
                username NVARCHAR(60) NOT NULL,
                password NVARCHAR(60) NOT NULL,
                email NVARCHAR(120) NOT NULL
);

create table if not exists product_category(
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) not null,
    description varchar(50) not null
);

create table if not exists supplier(
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) not null
);

create table if not exists product(
  id int primary key auto_increment,
  name varchar(50) not null ,
  description varchar(50) not null ,
  price numeric not null ,
  weight double not null,
  category_id int not null references product_category(id),
  supplier_id int not null references supplier(id),
  image_url varchar(50) not null
);

create table if not exists location(
  id int primary key auto_increment,
  name varchar(50) not null ,
  address_country varchar(50) not null ,
  address_city varchar(50) not null ,
  address_county varchar(50) not null ,
  address_streetAddress varchar(50) not null
);

create table if not exists stock(
  product_id int not null references product(id),
  location_id int not null references location(id),
  quantity int not null,
  constraint pk_stock primary key (product_id,location_id)
);

create table if not exists product_order(
  id int primary key auto_increment,
  shippedFrom_id int not null references location(id),
  customer_id int not null references customer(id),
  created_at date not null ,
  address_country varchar(50) not null ,
  address_city varchar(50) not null ,
  address_county varchar(50) not null ,
  address_street_address varchar(50) not null
);

create table if not exists product_order_detail(
  product_order_id int not null references product_order(id),
  product_id int not null references product(id),
  quantity int not null,
  constraint pk_product_order_detail primary key (product_id,product_order_id)
);

create table if not exists revenue(
    id int not null ,
    location_id int not null references location(id),
    datarevenue date not null ,
    suma numeric not null
);