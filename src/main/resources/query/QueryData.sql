-- Tabla: Announcement -> Inserts

-- Tabla: Comment -> Inserts

-- Tabla: Course -> Inserts
INSERT INTO course (name, description, limit_students) VALUES
('Matemáticas Avanzadas', 'Curso de álgebra, cálculo y geometría para desarrollar el pensamiento lógico-matemático.', 5),
('Literatura Universal', 'Estudio de obras clásicas y modernas de la literatura mundial para fomentar el análisis crítico.', 5),
('Ciencias Naturales', 'Exploración de biología, química y física a través de experimentos y observación del entorno.', 5),
('Historia Mundial', 'Recorrido por los eventos históricos más relevantes que han dado forma a las sociedades modernas.', 5),
('Inglés Conversacional', 'Desarrollo de habilidades de comunicación oral y escrita en el idioma inglés.', 5),
('Arte y Creatividad', 'Expresión artística a través de diferentes técnicas como pintura, escultura y dibujo.', 5),
('Educación Física', 'Actividades deportivas y ejercicios para promover la salud y el trabajo en equipo.', 5),
('Introducción a la Informática', 'Aprendizaje de conceptos básicos de computación y programación para principiantes.', 5);

-- Tabla: Course_Student -> Inserts

-- Tabla: Course_Teacher -> Inserts

-- Tabla: Grade -> Inserts
INSERT INTO grade (name) VALUES
('Primero'),
('Segundo'),
('Tercero'),
('Cuarto'),
('Quinto'),
('Sexto'),
('Séptimo'),
('Octavo'),
('Noveno'),
('Décimo'),
('Undécimo');

-- Tabla: Homework -> Inserts

-- Tabla: Publication -> Inserts

-- Tabla: Student -> Inserts
INSERT INTO student (name, last_name, email, password, phone_number, grade_id) VALUES
('Rina Margarita', 'Aguilera Rodriguez', 'richuxd@gmail.com', 'rina051123', '3175027012', 11),
('David Nickolay', 'Gutierrez Barrero', 'gutibrosss@gmail.com', 'gutiproplayer', '3178335695', 11),
('Juan Felipe', 'Suarez Galvis', 'juanferprior@gmail.com', 'juanfe123', '3015756397', 11),
('Dilan Andres', 'Jimenez Noches', 'diilanmp3@gmail.com', 'diilanxd123', '3018635028', 11),
('Axel David', 'Acero Rodriguez', 'aaxelrosexd@gmail.com', 'axel123steam', '3105341137', 11);

-- Tabla: Teacher -> Inserts
INSERT INTO teacher (name, last_name, email, password, phone_number) VALUES
('Edgar Antonio', 'Gomescasseres Barbosa', 'gomescasseresb@gmail.com', 'Monovergas12_', '3043364925'),
('Yonares David', 'Lopez Iglesia', 'yonazwtf@gmail.com', 'yonazmegustaleonardo', '3106172994'),
('Alejandro', 'Benitez Rengifo', 'alejandrobenitez@gmail.com', 'alebeniren', '3226431064'),
('Alexander Jose', 'Garzon Maldonado', 'alexaomacha@gmail.com', '3017559989alexao', '3173955111'),
('Santiago Antonio', 'Fierro Paredes', 'santiagofierro@gmail.com', 'santifierropare', '3127271972');