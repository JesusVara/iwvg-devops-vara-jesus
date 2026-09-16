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

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Irene', 'Castro', 'irene.castro@example.com', 'ID-1009',
       'Calle Sol 12', 'Pamplona', 'Navarra', '31010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'irene.castro@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Javier', 'Ortega', 'javier.ortega@example.com', 'ID-1010',
       'Calle Luna 8', 'Barcelona', 'Barcelona', '08020'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'javier.ortega@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Laura', 'Vega', 'laura.vega@example.com', 'ID-1011',
       'Avenida Europa 15', 'Valencia', 'Valencia', '46010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'laura.vega@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Marcos', 'Iglesias', 'marcos.iglesias@example.com', 'ID-1012',
       'Ronda Norte 21', 'Sevilla', 'Sevilla', '41010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'marcos.iglesias@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Natalia', 'Ramos', 'natalia.ramos@example.com', 'ID-1013',
       'Calle Goya 33', 'Zaragoza', 'Zaragoza', '50010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'natalia.ramos@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Oscar', 'Serrano', 'oscar.serrano@example.com', 'ID-1014',
       'Avenida Libertad 4', 'Bilbao', 'Bizkaia', '48010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'oscar.serrano@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Paula', 'Mendez', 'paula.mendez@example.com', 'ID-1015',
       'Calle Marina 19', 'A Coruña', 'A Coruña', '15010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'paula.mendez@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Raul', 'Nieto', 'raul.nieto@example.com', 'ID-1016',
       'Calle Mayor 44', 'Valladolid', 'Valladolid', '47010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'raul.nieto@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Sara', 'Pascual', 'sara.pascual@example.com', 'ID-1017',
       'Calle Rio 6', 'Granada', 'Granada', '18010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'sara.pascual@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Tomas', 'Crespo', 'tomas.crespo@example.com', 'ID-1018',
       'Avenida Central 27', 'Alicante', 'Alicante', '03010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'tomas.crespo@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Ursula', 'Duran', 'ursula.duran@example.com', 'ID-1019',
       'Calle Estacion 11', 'Oviedo', 'Asturias', '33010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'ursula.duran@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Victor', 'Moya', 'victor.moya@example.com', 'ID-1020',
       'Calle Colón 9', 'Palma', 'Illes Balears', '07010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'victor.moya@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Wendy', 'Fuentes', 'wendy.fuentes@example.com', 'ID-1021',
       'Calle Norte 18', 'Santander', 'Cantabria', '39010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'wendy.fuentes@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Xavier', 'Pardo', 'xavier.pardo@example.com', 'ID-1022',
       'Avenida Galicia 13', 'Vigo', 'Pontevedra', '36210'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'xavier.pardo@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Yolanda', 'Rey', 'yolanda.rey@example.com', 'ID-1023',
       'Calle Real 22', 'Salamanca', 'Salamanca', '37010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'yolanda.rey@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Zacarias', 'Soto', 'zacarias.soto@example.com', 'ID-1024',
       'Calle Prado 17', 'Toledo', 'Toledo', '45010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'zacarias.soto@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Alba', 'Marin', 'alba.marin@example.com', 'ID-1025',
       'Avenida del Mar 3', 'Malaga', 'Malaga', '29010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'alba.marin@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Belen', 'Roca', 'belen.roca@example.com', 'ID-1026',
       'Calle Jardines 26', 'Cordoba', 'Cordoba', '14010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'belen.roca@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Cesar', 'Santos', 'cesar.santos@example.com', 'ID-1027',
       'Calle Iglesia 7', 'Leon', 'Leon', '24010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'cesar.santos@example.com'
);

INSERT INTO users (
    first_name, family_name, email, identity_value,
    address, city, province, postal_code
)
SELECT 'Diana', 'Vidal', 'diana.vidal@example.com', 'ID-1028',
       'Avenida Castilla 30', 'Burgos', 'Burgos', '09010'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'diana.vidal@example.com'
);
