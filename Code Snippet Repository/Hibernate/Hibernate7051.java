	@Test
	public void testMultipleJoinColumns() throws Exception {
		reader = getReader( Entity1.class, "field1", "many-to-one.orm3.xml" );
		assertAnnotationPresent( ManyToOne.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinTable.class );
		JoinColumns joinColumnsAnno = reader.getAnnotation( JoinColumns.class );
		JoinColumn[] joinColumns = joinColumnsAnno.value();
		assertEquals( 2, joinColumns.length );
		assertEquals( "", joinColumns[0].name() );
		assertEquals( "", joinColumns[0].referencedColumnName() );
		assertEquals( "", joinColumns[0].table() );
		assertEquals( "", joinColumns[0].columnDefinition() );
		assertTrue( joinColumns[0].insertable() );
		assertTrue( joinColumns[0].updatable() );
		assertTrue( joinColumns[0].nullable() );
		assertFalse( joinColumns[0].unique() );
		assertEquals( "col1", joinColumns[1].name() );
		assertEquals( "col2", joinColumns[1].referencedColumnName() );
		assertEquals( "table1", joinColumns[1].table() );
		assertEquals( "int", joinColumns[1].columnDefinition() );
		assertFalse( joinColumns[1].insertable() );
		assertFalse( joinColumns[1].updatable() );
		assertFalse( joinColumns[1].nullable() );
		assertTrue( joinColumns[1].unique() );
	}
