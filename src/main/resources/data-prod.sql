-- Insert Questions
INSERT INTO question (label, text, type, is_required, is_active) VALUES
                                                                     ('What is your name?', 'Enter your full name.', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                                     ('Describe yourself.', 'Provide a brief description about yourself.', 'MULTILINE_TEXT', TRUE, TRUE),
                                                                     ('Choose your gender.', 'Select one of the options.', 'RADIO_BUTTON', TRUE, TRUE),
                                                                     ('Select your hobbies.', 'You can choose multiple options.', 'CHECKBOX', TRUE, TRUE),
                                                                     ('Choose your country.', 'Select from the list.', 'COMBOBOX', TRUE, TRUE),
                                                                     ('Select your birth date.', 'Pick a date.', 'DATE', TRUE, TRUE);

-- Insert Options for Questions Requiring Them
INSERT INTO option (index, text, question_id) VALUES
                                                      (1, 'Male', 3),
                                                      (2, 'Female', 3),
                                                      (1, 'Reading', 4),
                                                      (2, 'Traveling', 4),
                                                      (3, 'Sports', 4),
                                                      (1, 'USA', 5),
                                                      (2, 'Canada', 5),
                                                      (3, 'UK', 5);

-- Insert Submissions
INSERT INTO submission (submission_time) VALUES
                                             ('2024-06-18 10:00:00'),
                                             ('2024-06-18 11:00:00'),
                                             ('2024-06-18 12:00:00');

-- Insert Answers for Each Submission
-- Submission 1
INSERT INTO answer (text, submission_id, question_id) VALUES
                                                          ('John Doe', 1, 1),
                                                          ('I am a software developer.', 1, 2),
                                                          (NULL, 1, 3),
                                                          (NULL, 1, 4),
                                                          (NULL, 1, 5),
                                                          ('1990-01-01', 1, 6);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    ((SELECT id FROM answer WHERE submission_id = 1 AND question_id = 3), 1),
                                                                    ((SELECT id FROM answer WHERE submission_id = 1 AND question_id = 4), 1),
                                                                    ((SELECT id FROM answer WHERE submission_id = 1 AND question_id = 4), 2),
                                                                    ((SELECT id FROM answer WHERE submission_id = 1 AND question_id = 5), 1);

-- Submission 2
INSERT INTO answer (text, submission_id, question_id) VALUES
                                                          ('Jane Smith', 2, 1),
                                                          ('I am a graphic designer.', 2, 2),
                                                          (NULL, 2, 3),
                                                          (NULL, 2, 4),
                                                          (NULL, 2, 5),
                                                          ('1985-05-15', 2, 6);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    ((SELECT id FROM answer WHERE submission_id = 2 AND question_id = 3), 2),
                                                                    ((SELECT id FROM answer WHERE submission_id = 2 AND question_id = 4), 3),
                                                                    ((SELECT id FROM answer WHERE submission_id = 2 AND question_id = 5), 2);

-- Submission 3
INSERT INTO answer (text, submission_id, question_id) VALUES
                                                          ('Alice Johnson', 3, 1),
                                                          ('I am a project manager.', 3, 2),
                                                          (NULL, 3, 3),
                                                          (NULL, 3, 4),
                                                          (NULL, 3, 5),
                                                          ('1978-09-09', 3, 6);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    ((SELECT id FROM answer WHERE submission_id = 3 AND question_id = 3), 1),
                                                                    ((SELECT id FROM answer WHERE submission_id = 3 AND question_id = 4), 2),
                                                                    ((SELECT id FROM answer WHERE submission_id = 3 AND question_id = 4), 3),
                                                                    ((SELECT id FROM answer WHERE submission_id = 3 AND question_id = 5), 3);
