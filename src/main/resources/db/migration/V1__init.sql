CREATE TABLE categories
(
    id           BINARY(16)  NOT NULL,
    created_date BIGINT NULL,
    updated_date BIGINT NULL,
    is_deleted   BIT(1) NULL,
    name         VARCHAR(50) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BINARY(16)   NOT NULL,
    created_date  BIGINT NULL,
    updated_date  BIGINT NULL,
    is_deleted    BIT(1) NULL,
    name          VARCHAR(150) NOT NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    image_url     VARCHAR(255) NOT NULL,
    category_id   BINARY(16)   NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_name UNIQUE (name);

CREATE INDEX category_name_index ON categories (name);

CREATE INDEX product_name_index ON products (name);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

CREATE INDEX product_category_index ON products (category_id);