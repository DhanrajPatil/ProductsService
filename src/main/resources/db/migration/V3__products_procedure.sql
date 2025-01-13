DROP PROCEDURE IF EXISTS get_all_product_with_category_details;

CREATE PROCEDURE get_all_product_with_category_details(IN pageSize INT, IN pageNo INT)
BEGIN
    DECLARE offsetValue INT;
    SET offsetValue = pageNo * pageSize;

    SELECT p.id as id, p.name as name,
           p.description as description, p.price as price, p.image_url as imageUrl,
           c.id as categoryId, c.name as categoryName
    FROM products p
    LEFT JOIN categories c on c.id = p.category_id
    LIMIT pageSize OFFSET offsetValue;
END;