CREATE TABLE address
(
    address_id   INTEGER      NOT NULL,
    street       VARCHAR(255) NOT NULL,
    house_number INTEGER      NOT NULL,
    zip_code     FLOAT        NOT NULL,
    employee     INTEGER,
    CONSTRAINT pk_address PRIMARY KEY (address_id)
);
CREATE TABLE department
(
    department_id   INTEGER NOT NULL,
    department_name VARCHAR(255),
    CONSTRAINT pk_department PRIMARY KEY (department_id)
);

CREATE TABLE employee
(
    id                  INTEGER      NOT NULL,
    employee_identifier VARCHAR(255) NOT NULL,
    employee_name       VARCHAR(255),
    last_name           VARCHAR(255),
    email               VARCHAR(255),
    role                VARCHAR(255) NOT NULL,
    adress              INTEGER,
    birthdate           date,
    "departmenta-id"    INTEGER,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);



