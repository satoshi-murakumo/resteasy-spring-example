select
  EMPLOYEE_ID,
  EMPLOYEE_NAME,
  HIREDATE,
  SALARY,
  VERSION_NO
from
  EMPLOYEE
where
  EMPLOYEE_ID = /* employeeId */1
  and
  VERSION_NO = /* versionNo */1
