-- INSERT INTO Author(ID, FIRST_NAME, LAST_NAME) VALUES (1,'Craig','Walls');
-- INSERT INTO Author(ID, FIRST_NAME, LAST_NAME) VALUES (2,'Pierre-Yves','Saumont');
-- INSERT INTO BOOK(ID,TITLE, AUTHOR_ID) VALUES (1,'Spring w Akcji', 1);
-- INSERT INTO BOOK(ID,TITLE, AUTHOR_ID) VALUES (2,'Programowanie funkcyjne', 2);
-- INSERT INTO UserS(ID, FIRST_NAME, LAST_NAME, EMAIL, CREATED_AT) VALUES (2,'Brajan', 'Brajanusz', 'brajanusz@gmail.com', NOW());
-- INSERT INTO UserS(ID, FIRST_NAME, LAST_NAME, EMAIL, CREATED_AT) VALUES (1,'Marek', 'Nowakowski', 'marek@gmail.com', NOW());
-- INSERT INTO Comment (ID,CONTENT, BOOK_ID, USER_ID) VALUES (2,'To jest fajna książka', 2, 2);
-- INSERT INTO Comment (ID,CONTENT, BOOK_ID, USER_ID) VALUES (1,'Dupa Jasiu', 1, 2);
--
--
-- INSERT INTO USERS_COMMENT_LIST(USER_ID, COMMENT_LIST_ID) VALUES (1, 1);
-- INSERT INTO BOOK_COMMENT_LIST(BOOK_ID, COMMENT_LIST_ID) VALUES (1, 1);
-- INSERT INTO USERS_COMMENT_LIST(USER_ID, COMMENT_LIST_ID) VALUES (1, 2);
-- INSERT INTO BOOK_COMMENT_LIST(BOOK_ID, COMMENT_LIST_ID) VALUES (2,2);
