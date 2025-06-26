CREATE TABLE questions (
    question_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    question TEXT NOT NULL,
    description TEXT
);

CREATE TABLE comments (
    comment_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    comment TEXT NOT NULL,
    question_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
);