-- Drop existing tables if they exist
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS submission;
DROP TABLE IF EXISTS question_option;
DROP TABLE IF EXISTS question;

-- Create Question Table
CREATE TABLE question (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          label VARCHAR(255) NOT NULL,
                          type VARCHAR(20) NOT NULL,
                          required BOOLEAN NOT NULL,
                          is_active BOOLEAN NOT NULL
);

-- Create Question Option Table
CREATE TABLE question_option (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 value VARCHAR(255) NOT NULL,
                                 question_id BIGINT,
                                 CONSTRAINT fk_question_option FOREIGN KEY (question_id)
                                     REFERENCES question(id)
                                     ON DELETE CASCADE
);

-- Create Submission Table
CREATE TABLE submission (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            submission_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Answer Table
CREATE TABLE answer (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        submission_id BIGINT,
                        question_id BIGINT,
                        response TEXT,
                        CONSTRAINT fk_submission_answer FOREIGN KEY (submission_id)
                            REFERENCES submission(id)
                            ON DELETE CASCADE,
                        CONSTRAINT fk_question_answer FOREIGN KEY (question_id)
                            REFERENCES question(id)
                            ON DELETE CASCADE
);

-- Insert initial data into Question Table
INSERT INTO question (label, type, required, is_active) VALUES
                                                            ('Your Name', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                            ('Your Age', 'SINGLE_LINE_TEXT', FALSE, TRUE),
                                                            ('Your Gender', 'RADIO_BUTTON', TRUE, TRUE),
                                                            ('Your Feedback', 'MULTILINE_TEXT', TRUE, TRUE),
                                                            ('Your Birthdate', 'DATE', FALSE, TRUE);

-- Insert initial data into Question Option Table
INSERT INTO question_option (value, question_id) VALUES
                                                     ('Male', 3),
                                                     ('Female', 3),
                                                     ('Other', 3);

-- Insert a sample submission into Submission Table
INSERT INTO submission (submission_time) VALUES (CURRENT_TIMESTAMP);

-- Insert answers for the first submission
INSERT INTO answer (submission_id, question_id, response) VALUES
                                                              (1, 1, 'John Doe'),         -- Answer for "Your Name"
                                                              (1, 2, '30'),               -- Answer for "Your Age"
                                                              (1, 3, 'Male'),             -- Answer for "Your Gender"
                                                              (1, 4, 'This is feedback'); -- Answer for "Your Feedback"
-- No answer for "Your Birthdate", it will be NULL

-- Insert another sample submission into Submission Table
INSERT INTO submission (submission_time) VALUES (CURRENT_TIMESTAMP);

-- Insert answers for the second submission
INSERT INTO answer (submission_id, question_id, response) VALUES
                                                              (2, 1, 'Jane Smith'),       -- Answer for "Your Name"
                                                              (2, 3, 'Female'),           -- Answer for "Your Gender"
                                                              (2, 4, 'Great service!'),   -- Answer for "Your Feedback"
                                                              (2, 5, '1990-01-01');       -- Answer for "Your Birthdate"
-- No answer for "Your Age", it will be NULL

-- Insert a new question after submissions
INSERT INTO question (label, type, required, is_active) VALUES
    ('Your Favorite Color', 'COMBOBOX', FALSE, TRUE);

-- Insert options for the new question
INSERT INTO question_option (value, question_id) VALUES
                                                     ('Red', 6),
                                                     ('Green', 6),
                                                     ('Blue', 6);

-- Insert another sample submission with the new question
INSERT INTO submission (submission_time) VALUES (CURRENT_TIMESTAMP);

-- Insert answers for the third submission
INSERT INTO answer (submission_id, question_id, response) VALUES
                                                              (3, 1, 'Alice Johnson'),    -- Answer for "Your Name"
                                                              (3, 2, '25'),               -- Answer for "Your Age"
                                                              (3, 3, 'Other'),            -- Answer for "Your Gender"
                                                              (3, 4, 'Keep up the good work!'), -- Answer for "Your Feedback"
                                                              (3, 5, '1995-05-15'),       -- Answer for "Your Birthdate"
                                                              (3, 6, 'Blue');             -- Answer for "Your Favorite Color"
