	@Test
	@TestForIssue( jiraKey = "")
	public void testRemoveThenCloseWithAnActiveTransaction() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager em = getOrCreateEntityManager();
		try {
			Box box = new Box();
			box.setColor( "red-and-white" );
			em.persist( box );
			Muffin muffin = new Muffin();
			muffin.setKind( "blueberry" );
			box.addMuffin( muffin );
			em.close();
			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();

			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
			em = getOrCreateEntityManager();
			box = em.find( Box.class, box.getId() );
			em.remove( box );

			em.close();
			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
		}
		catch (Exception e) {
			final TransactionManager transactionManager = TestingJtaPlatformImpl.INSTANCE.getTransactionManager();
			if ( transactionManager.getTransaction() != null && transactionManager.getTransaction()
					.getStatus() == Status.STATUS_ACTIVE ) {
				TestingJtaPlatformImpl.INSTANCE.getTransactionManager().rollback();
			}
			throw e;
		}
		finally {
			if ( em.isOpen() ) {
				em.close();
			}
		}
		em = getOrCreateEntityManager();
		try {
			final List<Box> boxes = em.createQuery( "from Box" ).getResultList();
			assertThat( boxes.size(), is( 0 ) );
		}
		finally {
			em.close();
		}
	}
