    @Test
    public void testDoEndTagStringFactoryVarApplicationScope() throws Exception {
        this.tag.setLogger("testDoEndTagStringFactoryVarApplicationScope");

        final MessageFactory factory = new StringFormatterMessageFactory();

        this.tag.setFactory(factory);
        this.tag.setVar("goodbyeCruelWorld");
        this.tag.setScope("application");

        assertNull("The default logger should be null.", TagUtils.getDefaultLogger(this.context));
        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        assertNull("The default logger should still be null.", TagUtils.getDefaultLogger(this.context));

        final Object attribute = this.context.getAttribute("goodbyeCruelWorld", PageContext.APPLICATION_SCOPE);
        assertNotNull("The attribute should not be null.", attribute);
        assertTrue("The attribute should be a Log4jTaglibLogger.", attribute instanceof Log4jTaglibLogger);

        final Log4jTaglibLogger logger = (Log4jTaglibLogger)attribute;
        assertEquals("The logger name is not correct.", "testDoEndTagStringFactoryVarApplicationScope",
                logger.getName());
        checkMessageFactory("The message factory is not correct.", factory, logger);
    }
