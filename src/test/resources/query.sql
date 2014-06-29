select * 
from company company0_ 
left outer join company_unit units1_ on company0_.id=units1_.company_id 
left outer join unit unit2_ on units1_.units_id=unit2_.id 
left outer join unit_user users3_ on unit2_.id=users3_.unit_id 
left outer join user user4_ on users3_.users_id=user4_.id 
left outer join user_device devices5_ on user4_.id=devices5_.user_id 
left outer join device device6_ on devices5_.devices_id=device6_.id 
where 1=1 
and company0_.id=5
and unit2_.id IS NULL