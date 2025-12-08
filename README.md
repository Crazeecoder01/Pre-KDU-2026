# Database Assignment

Query 1: 
select c2.content_id, c2.title, c1.category_name 
from category c1 right join
content c2 on c1.category_id= c2.category_id;

![alt text](image.png)

Query2:
select title, rating, views_in_millions as views from content order by  views_in_millions desc;

![alt text](image-4.png)

Query3:
select t.category_name, avg(t.rating) from
(select c2.content_id, c2.title, c1.category_name, c2.rating
from category c1 right join
content c2 on c1.category_id= c2.category_id) as t group by t.category_name;

![alt text](image-1.png)

Alternative (to get all the content rows)

select
    c2.content_id,
    c2.title,
    c1.category_name,
    c2.rating,
    avg(c2.rating) over (partition by c1.category_name) as avg_rating
from content c2
right join category c1 
    ON c1.category_id = c2.category_id;

![alt text](image-2.png)


Query 4:
SELECT c2.title, c2.rating, c2.views_in_millions AS views, c1.category_name
FROM content c2
LEFT JOIN category c1 ON c1.category_id = c2.category_id
WHERE c2.rating > 8.5 AND c2.views_in_millions > 100;

![alt text](image-3.png)


EXPLAIN ANALYZE:
**Before indexing**:
-> Nested loop left join  (cost=3.85 rows=8) (actual time=0.554..0.628 rows=8 loops=1)
     -> Table scan on c2  (cost=1.05 rows=8) (actual time=0.0932..0.113 rows=8 loops=1)
     -> Single-row index lookup on c1 using PRIMARY (category_id=c2.category_id) ...

**After indexing**:
-> Nested loop left join  (cost=3.85 rows=8) (actual time=0.105..0.171 rows=8 loops=1)
     -> Table scan on c2  (cost=1.05 rows=8) (actual time=0.068..0.0901 rows=8 loops=1)
     -> Single-row index lookup on c1 using PRIMARY (category_id=c2.category_id) ...

Reason for performance Improvement:
    Before the index, MySQL had to scan through all the rows one by one to find matches for category_id,  which took extra time. After creating the index, it could directly look up the needed rows using the index.


# Answering the 3-WHY's

Why #1: Why do we use Foreign Keys?

We use foreign keys to make sure the data stays valid and consistent.
If someone tries to insert a content record with category_id = 999 (but category 999 doesn’t exist), the foreign key will block the insert.
This prevents “orphan” records. Without foreign keys, your database can easily end up with broken or meaningless data.

Why #2: Why is ACID important for this database?

When 1000 users watch “Stranger Adventures” at the same time, the system must increase the view count correctly.

Without ACID:

→ Two users might update views at the same time and overwrite each other
→ Database might crash in the middle of updating
→ Data could become inconsistent

With ACID:

Atomicity: Each update happens completely or not at all
Consistency: Data is same across all states, it never becomes irrelevant or obslete
Isolation: Multiple updates don’t interfere with each other
Durability: Once updated, the view count stays updated even after a crash

Why #3: Why would we create an index on category_id?

On the StreamFlix homepage, hundreds of queries filter content by category,
Without an index, MySQL scans the entire content table every time to find matching rows — which is slow.
With an index on category_id, MySQL can jump directly to the rows it needs, instead of checking every row.
