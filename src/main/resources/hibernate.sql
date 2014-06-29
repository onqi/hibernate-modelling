EXPLAIN EXTENDED SELECT company3_.id as col_0_0_, unit2_.id as col_1_0_, user1_.id as col_2_0_, device0_.id as col_3_0_ 
from device device0_ right outer join user user1_ on device0_.user_id=user1_.id right outer join unit unit2_ on user1_.unit_id=unit2_.id right outer join company company3_ on unit2_.company_id=company3_.id 
where 1=1 
and company3_.id=4 and user1_.id=4;