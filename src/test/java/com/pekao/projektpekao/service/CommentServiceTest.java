package com.pekao.projektpekao.service;

import com.pekao.projektpekao.CommentTestUtility;
import com.pekao.projektpekao.UserTestUtility;
import com.pekao.projektpekao.controller.Comments.CommentDto;
import com.pekao.projektpekao.controller.Comments.CommentDtoMapper;
import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.Comment.CommentParams;
import com.pekao.projektpekao.domain.Comment.CommentParamsMapper;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
    @Autowired
    private UserService userService;



    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
    }

    @Test
    void findAllComments() {
        //given
        commentRepository.saveAll(
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
        User userSaved = userService.addUser(UserTestUtility.createParamsUser("Lorrain","Garcia"));
        Comment commentWithUser = CommentTestUtility.createCommentWithUser(userSaved);
        CommentParams commentParamsMapped = CommentParamsMapper.fromEntityToCommentParams(commentWithUser);
        //when
        Comment commentSaved = commentService.addComment(commentParamsMapped);
        //then
        assertNotNull(commentSaved);
        assertThat(commentSaved).extracting("id", "content")
                .doesNotContainNull()
                .containsExactly(commentSaved.getId(), commentWithUser.getContent());
    }

    @Test
    void updateComment() {
        //given
        User userSaved = userService.addUser(UserTestUtility.createParamsUser("Lorrain","Garcia"));
        Comment commentWithUser = CommentTestUtility.createCommentWithUser(userSaved);
        CommentParams commentParamsMapped = CommentParamsMapper.fromEntityToCommentParams(commentWithUser);
        //when
        Comment commentFound = commentService.addComment(commentParamsMapped);
        CommentParams commentParams = CommentParamsMapper.fromEntityToCommentParams(commentFound);
        CommentParams commentChangedToSave = commentParams.toBuilder()
                .content("Zmieniony komentarz do testu")
                .build();
        Comment commentUpdated = commentService.updateComment(commentChangedToSave);
        //then
        assertEquals("Zmieniony komentarz do testu", commentUpdated.getContent());

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
