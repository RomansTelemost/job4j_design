--Только для постгри
CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username text NOT NULL
);

--Добавление 1000 строк
INSERT INTO users (username)
SELECT 'person' || n
FROM generate_series(1, 1000) AS n;