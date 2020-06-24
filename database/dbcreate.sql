CREATE TABLE user_role (
    id INTEGER AUTO_INCREMENT,
    role_name NVARCHAR(32) NOT NULL,
    check_deadlines BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT UNIQUE (role_name)
);

CREATE TABLE user (
    id INTEGER AUTO_INCREMENT,
    user_name NVARCHAR(32) NOT NULL,
#   надо подумать, какой тип данных здесь использовать
    password_hash NVARCHAR(64) NOT NULL,
    role_id INTEGER,

    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT UNIQUE (user_name),
    CONSTRAINT FOREIGN KEY (role_id) REFERENCES user_role(id)
);

CREATE TABLE university_group (
    id INTEGER AUTO_INCREMENT,
    name NVARCHAR(32) NOT NULL,

    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT UNIQUE (name)
);

CREATE TABLE user_university_group (
    user_id INTEGER NOT NULL,
    university_group_id INTEGER NOT NULL,

    CONSTRAINT PRIMARY KEY (user_id, university_group_id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT FOREIGN KEY (university_group_id) REFERENCES university_group(id)
);

CREATE TABLE tests (
    id INTEGER AUTO_INCREMENT,
    name TINYTEXT NOT NULL,

    CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE test_attempts (
    id INTEGER AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    test_id INTEGER NOT NULL,
    score INTEGER NOT NULL,
    datetime DATETIME NOT NULL DEFAULT NOW(),

    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT FOREIGN KEY (test_id) REFERENCES tests(id),
    CONSTRAINT attempts_chk_score CHECK ( score >= 0 )
);

CREATE TABLE test_deadlines (
    test_id INTEGER NOT NULL,
    university_group_id INTEGER NOT NULL,
    deadline DATETIME NOT NULL,

    CONSTRAINT PRIMARY KEY (test_id, university_group_id),
    CONSTRAINT FOREIGN KEY (test_id) REFERENCES tests(id),
    CONSTRAINT FOREIGN KEY (university_group_id) REFERENCES university_group(id)
);

CREATE TABLE test_questions (
    id INTEGER AUTO_INCREMENT,
    test_id INTEGER,
    question TEXT NOT NULL,
    score INTEGER NOT NULL,

    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (test_id) REFERENCES tests(id),
    CONSTRAINT questions_chk_score CHECK ( score >= 0 )
);

CREATE TABLE test_answers (
    id INTEGER AUTO_INCREMENT,
    answer TEXT,

    CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE test_question_answers (
    question_id INTEGER,
    answer_id INTEGER,
    is_correct BOOLEAN,

    CONSTRAINT PRIMARY KEY (question_id, answer_id),
    CONSTRAINT FOREIGN KEY (question_id) REFERENCES test_questions(id),
    CONSTRAINT FOREIGN KEY (answer_id) REFERENCES test_answers(id),
#   Надо подумать, стоит ли здесь делать UNIQUE
    CONSTRAINT UNIQUE (answer_id)
);
