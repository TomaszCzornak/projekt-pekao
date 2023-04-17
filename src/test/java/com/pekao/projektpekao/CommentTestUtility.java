package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Comment;

public class CommentTestUtility {

    public static Comment createComment() {
        return Comment.builder()
                .createContent("Komentarz do Książki")
//                .createUser(UserTestUtility.createUser("Mary","Jane"))
                .buildNewEntity();

    }


}
