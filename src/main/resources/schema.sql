CREATE TABLE IF NOT EXISTS diaries (
    diary_id SERIAL NOT NULL PRIMARY KEY,
    diary_title VARCHAR(50) NOT NULL,
    diary_content VARCHAR(100) NOT NULL,
    diary_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL NOT NULL PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    uesr_role VARCHAR(50) NOT NULL
);