package com.pekao.projektpekao.domain.book;

import com.pekao.projektpekao.domain.ElectronicJournal;

public class BookFactory {
	
	/* TODO:AR -> 5/4/2023
	    1 - Wywalamy buildera z book i podmieniamy wszedzie na factory
	    2 - 'createElectronicJournalEventType' ma być tylko w factory
	*/
	
	public Book buildNewEntity(CreateBookFactoryParams params) {
		final Book book = new Book();
		createElectronicJournalEventType();
		
		return book;
		/**
		 new Book
		 ustawiamy setterami/konstruktorem pola
		 dodajemy logikę z 'buildNewEntity'
		 i zwracamy book
		 */
		// tworzymy ksiazke z wszystkimi parametrami ale z logika 'buildNewEntity' z buildera
	}
	
	private static ElectronicJournal createElectronicJournalEventType(Book.Publisher publisher) {
		return switch (publisher) {
			case WYDAWNICTWO_LITERACKIE -> ElectronicJournal.builder()
					.eventType(ElectronicJournal.EventType.MANAGER)
					.name("Tu wydawcą jest Wydawnictwo Literackie")
					.buildNew();
			case PWN -> ElectronicJournal.builder()
					.eventType(ElectronicJournal.EventType.DONE)
					.name("Tu wydawcą jest PWN")
					.buildNew();
			case ZNAK -> ElectronicJournal.builder()
					.eventType(ElectronicJournal.EventType.TO_DO)
					.name("Tu wydawcą jest ZNAK")
					.buildNew();
			case AGORA -> ElectronicJournal.builder()
					.eventType(ElectronicJournal.EventType.WIP)
					.name("Tu wydawcą jest AGORA")
					.buildNew();
			default -> throw new IllegalArgumentException("Invalid example: " + publisher);
		};
	}
	
}
