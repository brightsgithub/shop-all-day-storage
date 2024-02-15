drop table if exists "customer_shipping_address";

drop table if exists "customer";

drop sequence if exists customer_seq;
create sequence customer_seq increment by 1 start with 10;

create table "customer"
(
    customer_id  integer default nextval('customer_seq') PRIMARY KEY,
    first_name   text NOT NULL,
    last_name    text NOT NULL,
    address1     text NOT NULL,
    address2     text,
    city         text,
    post_code    text NOT NULL,
    username     text NOT NULL,
    password     text NOT NULL,
    enabled      text,
    email        text NOT NULL,
    phone_number text NOT NULL,
    CONSTRAINT username_unique UNIQUE (username),
    CONSTRAINT email_unique UNIQUE (email)
);


commit;

drop sequence if exists customer_shipping_add_seq;
create sequence customer_shipping_add_seq increment by 1 start with 123;

create table CUSTOMER_SHIPPING_ADDRESS
(
    shipping_address_id integer default nextval('customer_shipping_add_seq') PRIMARY KEY,
    customer_id         integer references CUSTOMER (customer_id) NOT NULL,
    address1            text NOT NULL,
    address2            text,
    city                text,
    post_code           text NOT NULL
);

commit;

/*
 Any new category should be entered here, since all product types will belong
 to a category
 */
drop table if exists "category" CASCADE;
drop sequence if exists category_seq;

create sequence category_seq increment by 1 start with 1;

create table category
(
    category_id   integer default nextval('category_seq') PRIMARY KEY,
    category_name text NOT NULL,
    CONSTRAINT CAT_NAME_UNIQUE UNIQUE (category_name)
);

commit;

/*
 Product type describes the type of product, such as under the Category tab 'Electronics', we may have product types
 such as TV, Mobile, Desktop PC, Laptops, Speakers
 */

drop table if exists "product_type" CASCADE;
drop sequence if exists product_type_seq;

create sequence product_type_seq increment by 1 start with 99;

create table product_type
(
    product_type_id   integer default nextval('product_type_seq') PRIMARY KEY,
    category_id       integer references category (category_id) NOT NULL,
    product_type_name text NOT NULL
);

commit;

-- Brand - So we can identify our brands defined in a single place, more efficient to change brand name without updating
-- repeated rows if brand name was kept in products table, which would not be normalised. Also prevents someone updating a
-- brand (if it existed in the products table) by mistake and corrupting the data. it should be unique and in one place

drop table if exists brands CASCADE;
drop sequence if exists brand_seq;

create sequence brand_seq increment by 1 start with 600;

create table brands
(
    brand_id integer default nextval('brand_seq') PRIMARY KEY,
    brand_name text NOT NULL,
    constraint brands_name_unique UNIQUE(brand_name)
);


/*
 Contains detail about a particular product
 */

drop table if exists "products" CASCADE;
drop sequence if exists product_seq;

create sequence product_seq increment by 1 start with 17;

create table products
(
    product_id           integer default nextval('product_seq') PRIMARY KEY,
    product_type_id      integer references product_type(product_type_id) NOT NULL,
    brand_id             integer references brands(brand_id) NOT NULL,
    short_title          text  NOT NULL,
    long_title           text,
    short_description    text  NOT NULL,
    long_description text ,
    CONSTRAINT PRODUCTS_SHORT_TITLE_UNIQUE UNIQUE(short_title)
);

commit;


/*
 Product stock represents for each given product, what is the size of this product, it's color, how many is
 left product and it's price.
 */
drop table if exists "product_stock" CASCADE;
drop sequence if exists product_stock_seq;

create sequence product_stock_seq increment by 1 start with 300;

create table product_stock
(
    product_stock_id integer default nextval('product_stock_seq') PRIMARY KEY,
    product_id integer references products(product_id) NOT NULL,
    quantity integer NOT NULL,
    size text,
    color text,
    price float NOT NULL
);
