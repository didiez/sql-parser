SELECT COUNT(NSS) AS 'nss', COUNT(WAGE IS NOT NULL) AS 'employments w/ wage', COUNT(DISTINCT WAGE) AS 'different wages'
FROM EMPLOYMENT
WHERE YEAR(DOB) > 1940