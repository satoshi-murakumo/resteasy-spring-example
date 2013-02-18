select
  a.EMPLOYEE_ID,
  a.EMPLOYEE_NAME,
  a.HIREDATE,
  a.SALARY,
  a.VERSION_NO
from
  EMPLOYEE a
order by
  a.EMPLOYEE_ID;