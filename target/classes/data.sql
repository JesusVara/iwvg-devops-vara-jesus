INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Ana', 'Lopez', 'ana.lopez@example.com', 'ID-1001',
       'Calle Mayor 10', 'Madrid', 'Madrid', '28001'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'ana.lopez@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Bruno', 'Garcia', 'bruno.garcia@example.com', 'ID-1002',
       'Carrer de Valencia 25', 'Barcelona', 'Barcelona', '08015'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'bruno.garcia@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Clara', 'Martin', 'clara.martin@example.com', 'ID-1003',
       'Rua do Príncipe 7', 'Vigo', 'Pontevedra', '36202'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'clara.martin@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Diego', 'Sanchez', 'diego.sanchez@example.com', 'ID-1004',
       'Avenida de la Paz 42', 'Sevilla', 'Sevilla', '41001'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'diego.sanchez@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Elena', 'Navarro', 'elena.navarro@example.com', 'ID-1005',
       'Calle Alfonso I 18', 'Zaragoza', 'Zaragoza', '50003'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'elena.navarro@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Fernando', 'Ruiz', 'fernando.ruiz@example.com', 'ID-1006',
       'Gran Via 31', 'Bilbao', 'Bizkaia', '48009'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'fernando.ruiz@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Gabriela', 'Torres', 'gabriela.torres@example.com', 'ID-1007',
       '', 'Valencia', 'Valencia', '46001'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'gabriela.torres@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Hugo', 'Molina', 'hugo.molina@example.com', 'ID-1008',
       'Calle Real 5', 'A Coruña', 'A Coruña', '15001'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'hugo.molina@example.com'
);
