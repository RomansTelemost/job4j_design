select employees.name, position.name from employees
left join position on employees.position_id = position.id;