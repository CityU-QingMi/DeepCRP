	@Test
	public void testIncompleteScrollFirstResult() {
		Session s = openSession();
		s.beginTransaction();
		ScrollableResults results = s.createQuery( QUERY + " order by p.name asc" ).scroll();
		results.next();
		Parent p = (Parent) results.get( 0 );
		assertResultFromOneUser( p );
		s.getTransaction().commit();
		s.close();
	}
