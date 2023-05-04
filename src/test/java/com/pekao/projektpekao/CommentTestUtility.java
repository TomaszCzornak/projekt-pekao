package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Comment;

public class CommentTestUtility {

    public static Comment createComment() {
        return Comment.builder()
                .content("Komentarz do Książki")
                .user(UserTestUtility.createUser("Mary","Jane"))
                .buildNewEntity();

    }


}
