package com.pekao.projektpekao.service;

import com.pekao.projektpekao.CommentTestUtility;
import com.pekao.projektpekao.domain.Comment;
import com.pekao.projektpekao.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

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
        final List<Comment> commentList = commentRepository.saveAll(
                List.of(CommentTestUtility.createComment(),
                        CommentTestUtility.createComment(),
                        CommentTestUtility.createComment()));

        //when
        List<Comment> commentListFound = commentService.findAllComments();
        //then
        assertThat(commentListFound, hasSize(3));
    }

    @Test
    void findCommentById() {
        //given
        Comment commentSaved = commentRepository.save(CommentTestUtility.createComment());
        //when
        Comment commentFound = commentService.findCommentById(commentSaved.getId());
        //then
        assertEquals(commentSaved.getId(), commentFound.getId());
    }

    @Test
    void removeCommentById() {
        //given
        Comment commentSaved = commentRepository.save(CommentTestUtility.createComment());

        //when
        commentService.removeCommentById(commentSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> commentService.findCommentById(commentSaved.getId()));
    }

    @Test
    void addComment() {
        //given
        Comment commentToSave = CommentTestUtility.createComment();
        //when
        Comment commentSaved = commentService.addComment(commentToSave);
        //then
        assertNotNull(commentSaved);
        assertThat(commentSaved).extracting("id", "content")
                .doesNotContainNull()
                .containsExactly(commentSaved.getId(), commentToSave.getContent());
    }

    @Test
    void updateComment() {
        //given
        Comment commentSaved = commentRepository.save(CommentTestUtility.createComment());

        //when
        Comment commentChangedToSave = Comment.builder()
                .from(commentSaved)
                .content("Zmieniony komentarz")
                .build();
        Comment commentUpdated = commentService.updateComment(commentChangedToSave);
        //then
        assertEquals(commentUpdated.getContent(), "Zmieniony komentarz");

    }

    @Test
    void findCommentByContent() {
        //given
        Comment commentSaved = commentRepository.save(CommentTestUtility.createComment());
        //when
        Comment commentByContentFound = commentService.findCommentByContent(commentSaved.getContent());
        //then
        assertEquals(commentByContentFound.getContent(), commentSaved.getContent());
    }
}
