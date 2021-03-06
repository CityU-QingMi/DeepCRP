	@Test
	public void testSQLErrorCodeTranslationWithSpecifiedDbName() throws Exception {
		final SQLException sqlException = new SQLException("I have a known problem", "99999", 1054);
		final String sql = "SELECT ID FROM CUSTOMER";

		given(this.resultSet.next()).willReturn(true);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);

		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(this.dataSource);
		template.setDatabaseProductName("MySQL");
		template.afterPropertiesSet();

		this.thrown.expect(BadSqlGrammarException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		try {
			template.query(sql, new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					throw sqlException;
				}
			});
		}
		finally {
			verify(this.resultSet).close();
			verify(this.preparedStatement).close();
			verify(this.connection).close();
		}
	}
