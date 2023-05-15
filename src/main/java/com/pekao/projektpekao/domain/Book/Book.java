package com.pekao.projektpekao.domain.Book;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Comment.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Setter
@Getter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Author author;
	@OneToMany(mappedBy = "book", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Comment> commentList;
	@Enumerated(EnumType.STRING)
	private Publisher publisher;
	
	public enum Publisher {
		WYDAWNICTWO_LITERACKIE,
		PWN,
		ZNAK,
		AGORA,
	}
	
	protected Book() {
	}
	
	Book(
            final Long id, final String title, final Author author,
            final List<Comment> commentList, final Publisher publisher
	) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.commentList = commentList;
		this.publisher = publisher;
	}
	
	// pattern Factory

	public static class Builder {
		private Long id;
		private String title;
		private Author author;
		private List<Comment> commentList;
		private Publisher publisher;

		private Builder() {
		}

		public Builder from(Book book) {
			this.id = book.id;
			this.title = book.title;
			this.author = book.author;
			this.commentList = book.commentList;
			this.publisher = book.publisher;
			return this;
		}

		public Builder fromExisting(Book book) {
			this.id = book.id;
			this.title = book.title;
			this.author = book.author;
			this.commentList = book.commentList;
			this.publisher = book.publisher;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder author(Author author) {
			this.author = author;
			return this;
		}

		public Builder commentList(List<Comment> commentList) {
			this.commentList = commentList;
			return this;
		}


		public Builder publisher(Publisher publisher) {
			this.publisher = publisher;
			return this;
		}

		public Book buildNewEntity() {
			if (id != null) {
				throw new IllegalStateException("Id must be null if you want create new Entity");
			}

			final Book book = new Book(null, title, author, commentList, publisher);

			Optional.ofNullable(book.commentList)
					.ifPresent(comments ->
							comments.forEach(comment -> comment.setBook(book))
					);

			return book;
		}

		// to jest ukryty mapper
		public Book build() {
			return new Book(id, title, author, commentList, publisher);
		}
	}

	public static Builder builder() {
		return new Book.Builder();
	}

}
