    @Test
    public void testWrapExecutionWithNoParameters() throws Exception {
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONTEXT_NAME))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.LOG4J_CONFIG_LOCATION))).willReturn(null);
        given(servletContext.getInitParameter(eq(Log4jWebSupport.IS_LOG4J_CONTEXT_SELECTOR_NAMED))).willReturn(null);
        given(servletContext.getServletContextName()).willReturn("helloWorld07");
        given(servletContext.getResourcePaths("/WEB-INF/")).willReturn(null);
        assertNull("The context should be null.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.start();

        then(servletContext).should().setAttribute(eq(Log4jWebSupport.CONTEXT_ATTRIBUTE), loggerContextCaptor.capture());
        assertNotNull("The context attribute should not be null.", loggerContextCaptor.getValue());
        final org.apache.logging.log4j.spi.LoggerContext loggerContext = loggerContextCaptor.getValue();

        assertNull("The context should still be null.", ContextAnchor.THREAD_CONTEXT.get());

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final LoggerContext context = ContextAnchor.THREAD_CONTEXT.get();
                assertNotNull("The context should not be null.", context);
                assertSame("The context is not correct.", loggerContext, context);
            }
        };

        this.initializerImpl.wrapExecution(runnable);

        assertNull("The context should be null again.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.stop();

        then(servletContext).should().removeAttribute(eq(Log4jWebSupport.CONTEXT_ATTRIBUTE));

        assertNull("The context should again still be null.", ContextAnchor.THREAD_CONTEXT.get());

        this.initializerImpl.setLoggerContext();

        assertNull("The context should finally still be null.", ContextAnchor.THREAD_CONTEXT.get());
    }
