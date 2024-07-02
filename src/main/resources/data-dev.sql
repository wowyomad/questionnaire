-- Insert Questions
INSERT INTO question (id, label, text, type, is_required, is_active) VALUES
                                                                         (1, 'What is your name?', 'Enter your full name.', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                                         (2, 'Describe yourself.', 'Provide a brief description about yourself.', 'MULTILINE_TEXT', TRUE, TRUE),
                                                                         (3, 'Choose your gender.', 'Select one of the options.', 'RADIO_BUTTON', TRUE, TRUE),
                                                                         (4, 'Select your hobbies.', 'You can choose multiple options.', 'CHECKBOX', TRUE, TRUE),
                                                                         (5, 'Choose your country.', 'Select from the list.', 'COMBOBOX', TRUE, TRUE),
                                                                         (6, 'Select your birth date.', 'Pick a date.', 'DATE', FALSE, TRUE),
                                                                         (7, 'What is your favorite color?', 'Select one of the options.', 'RADIO_BUTTON', FALSE, TRUE),
                                                                         (8, 'Select your preferred programming languages.', 'Choose as many as you like.', 'CHECKBOX', FALSE, FALSE),
                                                                         (9, 'Do you agree with the terms?', 'Check if you agree.', 'CHECKBOX', FALSE, TRUE);

-- Insert Options for Questions Requiring Them
INSERT INTO option (id, text, question_id, index) VALUES
                                                      (1, 'Male', 3, 0),
                                                      (2, 'Female', 3, 1),
                                                      (3, 'Reading', 4, 0),
                                                      (4, 'Traveling', 4, 1),
                                                      (5, 'Sports', 4, 2),
                                                      (6, 'USA', 5, 0),
                                                      (7, 'Canada', 5, 1),
                                                      (8, 'UK', 5, 2),
                                                      (9, 'Red', 7, 0),
                                                      (10, 'Blue', 7, 1),
                                                      (11, 'Green', 7, 2),
                                                      (12, 'Yellow', 7, 3),
                                                      (13, 'Black', 7, 4),
                                                      (14, 'Python', 8, 0),
                                                      (15, 'JavaScript', 8, 1),
                                                      (16, 'Java', 8, 2),
                                                      (17, 'C++', 8, 3),
                                                      (18, 'Ruby', 8, 4),
                                                      (19, 'I Agree', 9, 0);

-- Insert Submissions
INSERT INTO submission (id, submission_time) VALUES
                                                 (1, '2024-06-18 10:00:00'),
                                                 (2, '2024-06-18 11:00:00'),
                                                 (3, '2024-06-18 12:00:00'),
                                                 (4, '2024-06-18 13:00:00'),
                                                 (5, '2024-06-18 14:00:00');

-- Insert Answers for Each Submission
-- Submission 1
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (1, 'John Doe', 1, 1),
                                                              (2, 'I am a software developer.', 1, 2),
                                                              (3, NULL, 1, 3),
                                                              (4, NULL, 1, 4),
                                                              (5, NULL, 1, 5),
                                                              (6, '1990-01-01', 1, 6),
                                                              (7, NULL, 1, 7),
                                                              (8, NULL, 1, 8),
                                                              (9, NULL, 1, 9);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (3, 1), -- Male
                                                                    (4, 3), -- Reading
                                                                    (4, 4), -- Traveling
                                                                    (5, 6), -- USA
                                                                    (7, 9), -- Red
                                                                    (8, 14), -- Python
                                                                    (8, 15), -- JavaScript
                                                                    (9, 19); -- I Agree

-- Submission 2
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (10, 'Jane Smith', 2, 1),
                                                              (11, 'I am a graphic designer.', 2, 2),
                                                              (12, NULL, 2, 3),
                                                              (13, NULL, 2, 4),
                                                              (14, NULL, 2, 5),
                                                              (15, '1985-05-15', 2, 6),
                                                              (16, NULL, 2, 7),
                                                              (17, NULL, 2, 8),
                                                              (18, NULL, 2, 9);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (12, 2), -- Female
                                                                    (13, 5), -- Sports
                                                                    (14, 7), -- Canada
                                                                    (16, 10), -- Blue
                                                                    (17, 17), -- C++
                                                                    (18, 19); -- I Agree

-- Submission 3
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (19, 'Alice Johnson', 3, 1),
                                                              (20, 'I am a project manager.', 3, 2),
                                                              (21, NULL, 3, 3),
                                                              (22, NULL, 3, 4),
                                                              (23, NULL, 3, 5),
                                                              (24, '1978-09-09', 3, 6),
                                                              (25, NULL, 3, 7),
                                                              (26, NULL, 3, 8),
                                                              (27, NULL, 3, 9);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (21, 1), -- Male
                                                                    (22, 4), -- Traveling
                                                                    (22, 5), -- Sports
                                                                    (23, 8), -- UK
                                                                    (25, 13), -- Black
                                                                    (26, 14), -- Python
                                                                    (26, 16), -- Java
                                                                    (27, 19); -- I Agree

-- Submission 4
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (28, 'Bob Brown', 4, 1),
                                                              (29, 'I am an artist.', 4, 2),
                                                              (30, NULL, 4, 3),
                                                              (31, NULL, 4, 4),
                                                              (32, NULL, 4, 5),
                                                              (33, '1995-03-30', 4, 6),
                                                              (34, NULL, 4, 7),
                                                              (35, NULL, 4, 8);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (30, 2), -- Female
                                                                    (31, 3), -- Reading
                                                                    (31, 5), -- Sports
                                                                    (32, 7), -- Canada
                                                                    (34, 11), -- Green
                                                                    (35, 15); -- JavaScript

-- Submission 5
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (37, 'Charlie Davis', 5, 1),
                                                              (38, 'I am a data scientist.', 5, 2),
                                                              (39, NULL, 5, 3),
                                                              (40, NULL, 5, 4),
                                                              (41, NULL, 5, 5),
                                                              (42, '1992-07-22', 5, 6),
                                                              (43, NULL, 5, 7),
                                                              (44, NULL, 5, 8),
                                                              (45, NULL, 5, 9);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (39, 1), -- Male
                                                                    (40, 4), -- Traveling
                                                                    (40, 5), -- Sports
                                                                    (41, 6), -- USA
                                                                    (43, 12), -- Yellow
                                                                    (44, 14), -- Python
                                                                    (44, 17), -- C++
                                                                    (45, 19); -- I Agree

ALTER TABLE question ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM question);
ALTER TABLE option ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM option);
ALTER TABLE submission ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM submission);
ALTER TABLE answer ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM answer);
ALTER TABLE "user" ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM "user");
