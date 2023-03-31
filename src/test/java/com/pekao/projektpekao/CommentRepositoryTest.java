package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {

    private final CommentRepository commentRepository;

    public CommentRepositoryTest(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    void saveOwner() {
//        commentRepository.save(new Comment("LucySmith"));
//        assertThat(commentRepository.findCommentByContent("LucySmith").isPresent()).isTrue();
    }

    @Test
    void deleteOwners() {
//        commentRepository.save(new Comment("LisaMorrison"));
//        commentRepository.deleteAll();
//        assertThat(commentRepository.count()).isEqualTo(0);
    }
}
