    @Test
    public void testPartialTextMessage() throws Exception {
        final TestEndpoint echoer = new TestEndpoint(new PartialStringCaptureHandler());
        Assert.assertThat(echoer, instanceOf(javax.websocket.Endpoint.class));
        // Issue connect using instance of class that extends Endpoint
        final Session session = container.connectToServer(echoer, serverUri);
        if (LOG.isDebugEnabled())
            LOG.debug("Client Connected: {}", session);
        session.getBasicRemote().sendText("");
        session.getBasicRemote().sendText("Echo");
        if (LOG.isDebugEnabled())
            LOG.debug("Client Message Sent");
        echoer.handler.getMessageQueue().awaitMessages(2, 1000, TimeUnit.MILLISECONDS);
    }
