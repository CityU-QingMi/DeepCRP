	@Test
	public void testManyToOneSelectFetch() {
		final AssociationType associationType = determineAssociationType( AnEntity.class, "otherEntitySelect" );
		final org.hibernate.FetchMode fetchMode = determineFetchMode( AnEntity.class, "otherEntitySelect" );
		assertSame( org.hibernate.FetchMode.SELECT, fetchMode );
		final FetchStyle fetchStyle = FetchStrategyHelper.determineFetchStyleByMetadata(
				fetchMode,
				associationType,
				sessionFactory()
		);
		assertSame( FetchStyle.SELECT, fetchStyle );
		final FetchTiming fetchTiming = FetchStrategyHelper.determineFetchTiming(
				fetchStyle,
				associationType,
				sessionFactory()
		);
		assertSame( FetchTiming.DELAYED, fetchTiming );
	}
