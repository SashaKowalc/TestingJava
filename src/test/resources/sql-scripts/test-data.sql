CREATE TABLE IF NOT EXISTS movies(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    minutes INT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL
);

INSERT INTO movies (name, minutes, genre, director) VALUES
    ('Dark Knight', 152, 'ACTION', 'Christopher Nolan'),
    ('Memento', 113, 'THRILLER', 'Christopher Nolan'),
    ('Matrix', 136, 'ACTION', 'Lana Wachowski'),
    ('SuperMan', 140, 'ACTION', 'Richard Donner'),
    ('Super Mario Bros', 90, 'COMEDY', 'Aaron Horvath')