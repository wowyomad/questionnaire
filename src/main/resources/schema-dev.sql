-- DROP TABLE IF EXISTS answer_selected_option_assoc;
-- DROP TABLE IF EXISTS answer;
-- DROP TABLE IF EXISTS submission;
-- DROP TABLE IF EXISTS option;
-- DROP TABLE IF EXISTS question;

CREATE TABLE IF NOT EXISTS question
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    label       VARCHAR(255) NOT NULL,
    text        VARCHAR(255),
    type        VARCHAR(20) NOT NULL,
    is_required BOOLEAN     NOT NULL,
    is_active   BOOLEAN     NOT NULL
);

CREATE TABLE IF NOT EXISTS "option"
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    "index"     INT,
    text        VARCHAR(255) NOT NULL,
    question_id INT,
    CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES question (id)
);

CREATE TABLE IF NOT EXISTS submission
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    submission_time TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS answer
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    text          VARCHAR(255),
    submission_id INT REFERENCES submission (id) ON DELETE CASCADE,
    question_id   INT REFERENCES question (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS answer_selected_option_assoc
(
    answer_id INT REFERENCES answer (id),
    option_id INT REFERENCES "option" (id),
    CONSTRAINT pk_answer_option PRIMARY KEY (answer_id, option_id)
);
