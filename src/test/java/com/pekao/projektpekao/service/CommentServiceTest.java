package com.pekao.projektpekao.service;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@TestPropertySource("/application-test.properties") //alternative in case of test issues
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
    }

    @Test
    void findAllComments() {
        //given
        Comment comment1 = commentRepository.save(new Comment("Pierwszy komentarz"));
        Comment comment2 = commentRepository.save(new Comment("Drugi komentarz"));
        Comment comment3 = commentRepository.save(new Comment("Trzeci komentarz"));
        commentRepository.saveAll(List.of(comment1, comment2, comment3));
        //when
        List<Comment> commentList = commentService.findAllComments();
        //then
        assertThat(commentList, hasSize(3));
    }

    @Test
    void findCommentById() {
        //given
        Comment commentToSave = commentRepository.save(new Comment("Pierwszy komentarz"));
        Comment commentSaved = commentRepository.save(commentToSave);
        //when
        Comment commentFound = commentService.findCommentById(commentSaved.getId());
        //then
        assertEquals(commentSaved.getId(), commentFound.getId());
    }

    @Test
    void removeCommentById() {
        //given
        Comment commentSaved = commentRepository.save(new Comment("Pierwszy komentarz"));
        //when
        commentService.removeCommentById(commentSaved.getId());
        //then
        assertThrows(IllegalStateException.class, ()->commentService.findCommentById(commentSaved.getId()));
    }

    @Test
    void addComment() {
        //given
        Comment comment = new Comment("Add Comment Test");
        //when
        commentService.addComment(comment);
        Comment commentSaved = commentService.findCommentByContent("Add Comment Test");
        //then
        assertThat(commentSaved).extracting( "id","content")
                .doesNotContainNull()
                .containsExactly(commentSaved.getId(), "Add Comment Test");
    }

    @Test
    void updateComment() {
        //given
        Comment commentSaved = commentRepository.save(new Comment("Pierwszy komentarz"));
        Comment commentFound = commentService.findCommentByContent("Pierwszy komentarz");
        //when
        commentFound.setContent("Zmieniony komentarz");
        commentService.updateComment(commentSaved.getId(), commentFound);
        Comment changedComment = commentService.findCommentByContent(commentFound.getContent());
        //then
        assertEquals(changedComment.getContent(), "Zmieniony komentarz");

    }

    @Test
    void findCommentByContent() {
        //given
        Comment commentSaved = commentRepository.save(new Comment("Pierwszy komentarz"));
        //when
        Comment commentByContentFound = commentService.findCommentByContent("Pierwszy komentarz");
        //then
        assertEquals(commentByContentFound.getContent(), commentSaved.getContent());
    }
}