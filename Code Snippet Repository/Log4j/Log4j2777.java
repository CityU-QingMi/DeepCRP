    @Test
    public void testClassLogger() throws Exception {
        final ListAppender app = ctx.getListAppender("Class").clear();
        final Logger logger = Logger.getLogger("ClassLogger");
        logger.info("Ignored message contents.");
        logger.warning("Verifying the caller class is still correct.");
        logger.severe("Hopefully nobody breaks me!");
        final List<String> messages = app.getMessages();
        assertEquals("Incorrect number of messages.", 3, messages.size());
        for (final String message : messages) {
            assertEquals("Incorrect caller class name.", this.getClass().getName(), message);
        }
    }
