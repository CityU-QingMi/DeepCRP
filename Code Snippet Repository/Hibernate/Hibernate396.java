	@Test
	public void removeTest() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setId( 1L );
			person.setName( "John Doe" );

			Phone phone = new Phone();
			phone.setId( 1L );
			phone.setNumber( "123-456-7890" );

			person.addPhone( phone );
			entityManager.persist( person );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::pc-cascade-remove-example[]
			Person person = entityManager.find( Person.class, 1L );

			entityManager.remove( person );
			//end::pc-cascade-remove-example[]
		} );
	}
