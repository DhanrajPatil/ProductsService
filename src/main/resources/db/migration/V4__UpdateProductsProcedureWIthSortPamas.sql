DROP PROCEDURE IF EXISTS get_all_product_with_category_details;

CREATE PROCEDURE get_all_product_with_category_details(IN pageSize INT, IN pageNo INT, IN sortColumn VARCHAR(50), IN sortDirection VARCHAR(50))
BEGIN
    DECLARE offsetValue INT;
    SET offsetValue = pageNo * pageSize;

    SELECT p.id as id, p.name as name,
           p.description as description, p.price as price, p.image_url as imageUrl,
           c.id as categoryId, c.name as categoryName
    FROM products p
             LEFT JOIN categories c on c.id = p.category_id
    ORDER BY CASE
                 WHEN sortDirection = 'ASC' THEN
                     CASE
                         WHEN sortColumn = 'name' THEN p.name
                         WHEN sortColumn = 'price' THEN p.price
                         WHEN sortColumn = 'categoryName' THEN c.name
                     END
                 ELSE
                     CASE
                         WHEN sortColumn = 'name' THEN p.name
                         WHEN sortColumn = 'price' THEN p.price
                         WHEN sortColumn = 'categoryName' THEN c.name
                     END
             END
    LIMIT pageSize OFFSET offsetValue;
END;