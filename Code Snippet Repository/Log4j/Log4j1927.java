    @Test
    public void routingTest() {
        StructuredDataMessage msg = new StructuredDataMessage("Test", "This is a test", "Service");
        EventLogger.logEvent(msg);
        final List<LogEvent> list = loggerContextRule.getListAppender("List").getEvents();
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events. Expected 1, got " + list.size(), list.size() == 1);
        msg = new StructuredDataMessage("Test", "This is a test", "Unknown");
        EventLogger.logEvent(msg);
        final File file = new File(LOG_FILENAME);
        assertTrue("File was not created", file.exists());
    }
