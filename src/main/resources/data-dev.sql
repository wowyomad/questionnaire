-- Insert Questions
INSERT INTO question (id, label, text, type, is_required, is_active) VALUES
                                                                         (1, 'What is your name?', 'Enter your full name.', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                                         (2, 'Describe yourself.', 'Provide a brief description about yourself.', 'MULTILINE_TEXT', TRUE, TRUE),
                                                                         (3, 'Choose your gender.', 'Select one of the options.', 'RADIO_BUTTON', TRUE, TRUE),
                                                                         (4, 'Select your hobbies.', 'You can choose multiple options.', 'CHECKBOX', TRUE, TRUE),
                                                                         (5, 'Choose your country.', 'Select from the list.', 'COMBOBOX', TRUE, TRUE),
                                                                         (6, 'Select your birth date.', 'Pick a date.', 'DATE', FALSE, TRUE),
                                                                         (7, 'What is your favorite color?', 'Select one of the options.', 'RADIO_BUTTON', FALSE, TRUE),
                                                                         (8, 'Select your preferred programming languages.', 'Choose as many as you like.', 'CHECKBOX', FALSE, TRUE),
                                                                         (9, 'Do you agree with the terms?', 'Check if you agree.', 'CHECKBOX', FALSE, TRUE),
                                                                         (10, 'Provide your feedback.', 'Share your feedback about our service.', 'MULTILINE_TEXT', FALSE, TRUE),
                                                                         (11, 'What is your age?', 'Enter your age.', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                                         (12, 'Choose your marital status.', 'Select one of the options.', 'RADIO_BUTTON', TRUE, TRUE),
                                                                         (13, 'Enter your phone number.', 'Provide your contact number.', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                                         (14, 'Select your favorite cuisine.', 'You can choose multiple options.', 'CHECKBOX', TRUE, TRUE),
                                                                         (15, 'What is your occupation?', 'Enter your current occupation.', 'SINGLE_LINE_TEXT', TRUE, TRUE),
                                                                         (16, 'Provide your LinkedIn profile.', 'Share the URL of your LinkedIn profile.', 'SINGLE_LINE_TEXT', FALSE, FALSE),
                                                                         (17, 'What is your Twitter handle?', 'Enter your Twitter handle.', 'SINGLE_LINE_TEXT', FALSE, FALSE),
                                                                         (18, 'Share your GitHub profile.', 'Provide the URL of your GitHub profile.', 'SINGLE_LINE_TEXT', FALSE, FALSE),
                                                                         (19, 'What is your Instagram username?', 'Enter your Instagram username.', 'SINGLE_LINE_TEXT', FALSE, FALSE),
                                                                         (20, 'Describe your skills.', 'Provide a detailed description of your skills.', 'MULTILINE_TEXT', FALSE, FALSE);


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
                                                      (19, 'I Agree', 9, 0),
                                                      (20, 'Single', 12, 0),
                                                      (21, 'Married', 12, 1),
                                                      (22, 'Divorced', 12, 2),
                                                      (23, 'Widowed', 12, 3),
                                                      (24, 'Italian', 14, 0),
                                                      (25, 'Chinese', 14, 1),
                                                      (26, 'Indian', 14, 2),
                                                      (27, 'Mexican', 14, 3),
                                                      (28, 'Japanese', 14, 4);


INSERT INTO submission (id, submission_time) VALUES
                                                 (1, '2024-06-18 10:00:00'),
                                                 (2, '2024-06-18 11:00:00'),
                                                 (3, '2024-06-18 12:00:00'),
                                                 (4, '2024-06-18 13:00:00'),
                                                 (5, '2024-06-18 14:00:00'),
                                                 (6, '2024-06-19 10:00:00'),
                                                 (7, '2024-06-19 11:00:00'),
                                                 (8, '2024-06-19 12:00:00'),
                                                 (9, '2024-06-19 13:00:00'),
                                                 (10, '2024-06-19 14:00:00');

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
                                                              (9, NULL, 1, 9),
                                                              (10, 'Great service.', 1, 10),
                                                              (11, '30', 1, 11),
                                                              (12, NULL, 1, 12),
                                                              (13, '123-456-7890', 1, 13),
                                                              (14, NULL, 1, 14),
                                                              (15, 'Engineer', 1, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (3, 1), -- Male
                                                                    (4, 3), -- Reading
                                                                    (4, 4), -- Traveling
                                                                    (5, 6), -- USA
                                                                    (7, 9), -- Red
                                                                    (8, 14), -- Python
                                                                    (8, 15), -- JavaScript
                                                                    (9, 19), -- I Agree
                                                                    (12, 20), -- Single
                                                                    (14, 24); -- Italian

-- Submission 2
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (16, 'Jane Smith', 2, 1),
                                                              (17, 'I am a graphic designer.', 2, 2),
                                                              (18, NULL, 2, 3),
                                                              (19, NULL, 2, 4),
                                                              (20, NULL, 2, 5),
                                                              (21, '1985-05-15', 2, 6),
                                                              (22, NULL, 2, 7),
                                                              (23, NULL, 2, 8),
                                                              (24, NULL, 2, 9),
                                                              (25, 'Good experience.', 2, 10),
                                                              (26, '35', 2, 11),
                                                              (27, NULL, 2, 12),
                                                              (28, '987-654-3210', 2, 13),
                                                              (29, NULL, 2, 14),
                                                              (30, 'Designer', 2, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (18, 2), -- Female
                                                                    (19, 5), -- Sports
                                                                    (20, 7), -- Canada
                                                                    (22, 10), -- Blue
                                                                    (23, 16), -- Java
                                                                    (23, 17), -- C++
                                                                    (24, 19), -- I Agree
                                                                    (27, 21), -- Married
                                                                    (29, 25); -- Chinese

-- Submission 3
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (31, 'Alice Johnson', 3, 1),
                                                              (32, 'I am an educator.', 3, 2),
                                                              (33, NULL, 3, 3),
                                                              (34, NULL, 3, 4),
                                                              (35, NULL, 3, 5),
                                                              (36, '1978-03-22', 3, 6),
                                                              (37, NULL, 3, 7),
                                                              (38, NULL, 3, 8),
                                                              (39, NULL, 3, 9),
                                                              (40, 'Satisfied.', 3, 10),
                                                              (41, '46', 3, 11),
                                                              (42, NULL, 3, 12),
                                                              (43, '555-555-5555', 3, 13),
                                                              (44, NULL, 3, 14),
                                                              (45, 'Teacher', 3, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (33, 1), -- Male
                                                                    (34, 3), -- Reading
                                                                    (34, 5), -- Sports
                                                                    (35, 8), -- UK
                                                                    (37, 12), -- Yellow
                                                                    (38, 18), -- Ruby
                                                                    (39, 19), -- I Agree
                                                                    (42, 22), -- Divorced
                                                                    (44, 26); -- Indian

-- Submission 4
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (46, 'Bob Brown', 4, 1),
                                                              (47, 'I am a musician.', 4, 2),
                                                              (48, NULL, 4, 3),
                                                              (49, NULL, 4, 4),
                                                              (50, NULL, 4, 5),
                                                              (51, '1992-07-08', 4, 6),
                                                              (52, NULL, 4, 7),
                                                              (53, NULL, 4, 8),
                                                              (54, NULL, 4, 9),
                                                              (55, 'Loved it.', 4, 10),
                                                              (56, '32', 4, 11),
                                                              (57, NULL, 4, 12),
                                                              (58, '444-444-4444', 4, 13),
                                                              (59, NULL, 4, 14),
                                                              (60, 'Musician', 4, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (48, 1), -- Male
                                                                    (49, 4), -- Traveling
                                                                    (49, 5), -- Sports
                                                                    (50, 6), -- USA
                                                                    (52, 11), -- Green
                                                                    (53, 14), -- Python
                                                                    (54, 19), -- I Agree
                                                                    (57, 23), -- Widowed
                                                                    (59, 27); -- Mexican

-- Submission 5
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (61, 'Charlie Black', 5, 1),
                                                              (62, 'I am a writer.', 5, 2),
                                                              (63, NULL, 5, 3),
                                                              (64, NULL, 5, 4),
                                                              (65, NULL, 5, 5),
                                                              (66, '1980-11-11', 5, 6),
                                                              (67, NULL, 5, 7),
                                                              (68, NULL, 5, 8),
                                                              (69, NULL, 5, 9),
                                                              (70, 'Very happy.', 5, 10),
                                                              (71, '43', 5, 11),
                                                              (72, NULL, 5, 12),
                                                              (73, '333-333-3333', 5, 13),
                                                              (74, NULL, 5, 14),
                                                              (75, 'Author', 5, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (63, 2), -- Female
                                                                    (64, 3), -- Reading
                                                                    (64, 4), -- Traveling
                                                                    (65, 7), -- Canada
                                                                    (67, 13), -- Black
                                                                    (68, 17), -- C++
                                                                    (68, 18), -- Ruby
                                                                    (69, 19), -- I Agree
                                                                    (72, 20), -- Single
                                                                    (74, 28); -- Japanese


-- Submission 6
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (76, 'David White', 6, 1),
                                                              (77, 'I am a photographer.', 6, 2),
                                                              (78, NULL, 6, 3),
                                                              (79, NULL, 6, 4),
                                                              (80, NULL, 6, 5),
                                                              (81, '1995-12-12', 6, 6),
                                                              (82, NULL, 6, 7),
                                                              (83, NULL, 6, 8),
                                                              (84, NULL, 6, 9),
                                                              (85, 'Amazing service.', 6, 10),
                                                              (86, '28', 6, 11),
                                                              (87, NULL, 6, 12),
                                                              (88, '222-222-2222', 6, 13),
                                                              (89, NULL, 6, 14),
                                                              (90, 'Photographer', 6, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (78, 1), -- Male
                                                                    (79, 4), -- Traveling
                                                                    (79, 5), -- Sports
                                                                    (80, 6), -- USA
                                                                    (82, 9), -- Red
                                                                    (83, 14), -- Python
                                                                    (84, 19), -- I Agree
                                                                    (87, 21), -- Married
                                                                    (89, 25); -- Chinese

-- Submission 7
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (91, 'Emma Green', 7, 1),
                                                              (92, 'I am a chef.', 7, 2),
                                                              (93, NULL, 7, 3),
                                                              (94, NULL, 7, 4),
                                                              (95, NULL, 7, 5),
                                                              (96, '1988-08-08', 7, 6),
                                                              (97, NULL, 7, 7),
                                                              (98, NULL, 7, 8),
                                                              (99, NULL, 7, 9),
                                                              (100, 'Enjoyed a lot.', 7, 10),
                                                              (101, '36', 7, 11),
                                                              (102, NULL, 7, 12),
                                                              (103, '111-111-1111', 7, 13),
                                                              (104, NULL, 7, 14),
                                                              (105, 'Chef', 7, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (93, 2), -- Female
                                                                    (94, 3), -- Reading
                                                                    (94, 5), -- Sports
                                                                    (95, 8), -- UK
                                                                    (97, 11), -- Green
                                                                    (98, 18), -- Ruby
                                                                    (99, 19), -- I Agree
                                                                    (102, 22), -- Divorced
                                                                    (104, 24); -- Italian

-- Submission 8
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (106, 'Frank Blue', 8, 1),
                                                              (107, 'I am an artist.', 8, 2),
                                                              (108, NULL, 8, 3),
                                                              (109, NULL, 8, 4),
                                                              (110, NULL, 8, 5),
                                                              (111, '1984-04-04', 8, 6),
                                                              (112, NULL, 8, 7),
                                                              (113, NULL, 8, 8),
                                                              (114, NULL, 8, 9),
                                                              (115, 'Satisfied.', 8, 10),
                                                              (116, '40', 8, 11),
                                                              (117, NULL, 8, 12),
                                                              (118, '999-999-9999', 8, 13),
                                                              (119, NULL, 8, 14),
                                                              (120, 'Artist', 8, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (108, 1), -- Male
                                                                    (109, 3), -- Reading
                                                                    (109, 4), -- Traveling
                                                                    (110, 7), -- Canada
                                                                    (112, 10), -- Blue
                                                                    (113, 17), -- C++
                                                                    (114, 19), -- I Agree
                                                                    (117, 20), -- Single
                                                                    (119, 27); -- Mexican

-- Submission 9
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (121, 'Grace Yellow', 9, 1),
                                                              (122, 'I am a scientist.', 9, 2),
                                                              (123, NULL, 9, 3),
                                                              (124, NULL, 9, 4),
                                                              (125, NULL, 9, 5),
                                                              (126, NULL, 9, 6),
                                                              (127, NULL, 9, 7),
                                                              (128, NULL, 9, 8),
                                                              (129, NULL, 9, 9),
                                                              (130, 'Very good.', 9, 10),
                                                              (131, '29', 9, 11),
                                                              (132, NULL, 9, 12),
                                                              (133, '666-666-6666', 9, 13),
                                                              (134, NULL, 9, 14),
                                                              (135, 'Scientist', 9, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (123, 2), -- Female
                                                                    (124, 4), -- Traveling
                                                                    (124, 5), -- Sports
                                                                    (125, 6), -- USA
                                                                    (132, 21); -- Married

-- Submission 10
INSERT INTO answer (id, text, submission_id, question_id) VALUES
                                                              (136, 'Hank Pink', 10, 1),
                                                              (137, 'I am an engineer.', 10, 2),
                                                              (138, NULL, 10, 3),
                                                              (139, NULL, 10, 4),
                                                              (140, NULL, 10, 5),
                                                              (141, NULL, 10, 6),
                                                              (142, NULL, 10, 7),
                                                              (143, NULL, 10, 8),
                                                              (144, NULL, 10, 9),
                                                              (145, 'Excellent.', 10, 10),
                                                              (146, '33', 10, 11),
                                                              (147, NULL, 10, 12),
                                                              (148, '777-777-7777', 10, 13),
                                                              (149, NULL, 10, 14),
                                                              (150, 'Engineer', 10, 15);

INSERT INTO answer_selected_option_assoc (answer_id, option_id) VALUES
                                                                    (138, 1), -- Male
                                                                    (139, 4), -- Traveling
                                                                    (139, 5), -- Sports
                                                                    (140, 8), -- UK
                                                                    (147, 20); -- Single


ALTER TABLE question ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM question);
ALTER TABLE option ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM option);
ALTER TABLE submission ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM submission);
ALTER TABLE answer ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM answer);
ALTER TABLE "user" ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM "user");
