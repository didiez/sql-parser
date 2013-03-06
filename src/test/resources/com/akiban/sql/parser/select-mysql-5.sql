select fechaNacimiento, max(dni) as 'max dni'
from persona
group by fechaNacimiento Desc