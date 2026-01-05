Pre-KDU-Assessment
SQL Assessment
select c2.content_id, c2.title, c1.category_name from category c1 right join content c2 on c1.category_id= c2.category_id;

-- REQUEST A select c2.content_id, c2.title, c1.category_name from category c1 right join content c2 on c1.category_id= c2.category_id where category_name = "Documentaries" AND release_year = 2024 AND rating>8;

-- REQUEST B select title, (rating + views_in_millions) as success_score from content c2 having success_score>100;
