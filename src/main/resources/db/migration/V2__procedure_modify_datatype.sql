ALTER TABLE products
    MODIFY image_url VARCHAR(150) NOT NULL;

create procedure get_all_categories_with_products()
BEGIN
select c.id as categoryId, c.name as categoryName,
       p.id as productId, p.name as productName,
       p.description as productDescription, p.price as price, p.image_url as imageUrl
from categories c
         left join products p on c.id = p.category_id;
END;