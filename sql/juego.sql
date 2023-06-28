CREATE DATABASE IF NOT EXISTS juego

CREATE TABLE IF NOT EXISTS preguntas (id INT AUTO_INCREMENT,  question VARCHAR(255), answer1 VARCHAR(255), answer2 VARCHAR(255), answer3 VARCHAR(255), correct VARCHAR(255), PRIMARY KEY(id) )

INSERT INTO `preguntas`(`id`, `question`, `answer1`, `answer2`, `answer3`, `correct`) VALUES (1, ¿Capital de España? ,Madrid, Barcelona, Sevilla , 1)

INSERT INTO `preguntas`(`id`, `question`, `answer1`, `answer2`, `answer3`, `correct`) VALUES (2, ¿Capital de Brasil?, Sao Paulo, Brasilia, Rio de Janeiro, 2)

INSERT INTO `preguntas`(`id`, `question`, `answer1`, `answer2`, `answer3`, `correct`) VALUES (3, ¿Pico más alto de España?, Teide, Moncayo, Mulhacén, 1)

