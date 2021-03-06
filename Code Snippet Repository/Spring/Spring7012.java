	@Test
	public void testRegisteredErrorHandlerIsInvokedOnException() throws Exception {
		final SimpleMessageConsumer messageConsumer = new SimpleMessageConsumer();

		Session session = mock(Session.class);

		// Queue gets created in order to create MessageConsumer for that Destination...
		given(session.createQueue(DESTINATION_NAME)).willReturn(QUEUE_DESTINATION);
		// and then the MessageConsumer gets created...
		given(session.createConsumer(QUEUE_DESTINATION, null)).willReturn(messageConsumer); // no MessageSelector...
		// an exception is thrown, so the rollback logic is being applied here...
		given(session.getTransacted()).willReturn(false);

		Connection connection = mock(Connection.class);
		// session gets created in order to register MessageListener...
		given(connection.createSession(this.container.isSessionTransacted(),
				this.container.getSessionAcknowledgeMode())).willReturn(session);

		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		given(connectionFactory.createConnection()).willReturn(connection);

		final IllegalStateException theException = new IllegalStateException("intentional test failure");

		this.container.setConnectionFactory(connectionFactory);
		this.container.setDestinationName(DESTINATION_NAME);
		this.container.setMessageListener(new SessionAwareMessageListener<Message>() {
			@Override
			public void onMessage(Message message, @Nullable Session session) throws JMSException {
				throw theException;
			}
		});

		ErrorHandler errorHandler = mock(ErrorHandler.class);
		this.container.setErrorHandler(errorHandler);
		this.container.afterPropertiesSet();
		this.container.start();

		// manually trigger an Exception with the above bad MessageListener...
		Message message = mock(Message.class);

		// a Throwable from a MessageListener MUST simply be swallowed...
		messageConsumer.sendMessage(message);

		verify(connection).setExceptionListener(this.container);
		verify(connection).start();
		verify(errorHandler).handleError(theException);
	}
