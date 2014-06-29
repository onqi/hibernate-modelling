SET @cid = 5;
SET @uid = NULL;
SET @rid = NULL;
SET @did = NULL;

SELECT c.id as company_id, u.id as unit_id, r.id as user_id, d.id as device_id 
FROM Company c 
left outer join Unit u on (u.company_id = c.id)
LEFT OUTER JOIN User r ON (r.unit_id = u.id)
LEFT OUTER JOIN Device d ON (d.user_id = r.id)
WHERE (@cid IS NULL OR c.id = @cid)
AND (@uid IS NULL OR u.id = @uid)
AND (@rid IS NULL OR r.id = @rid)
AND (@did IS NULL OR d.id = @did)


