    public void testInterceptSelectedCookiesNameAndValue() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(
                new Cookie("cookie1", "cookie1value"),
                new Cookie("cookie2", "cookie2value"),
                new Cookie("cookie3", "cookie3value")
        );
        ServletActionContext.setRequest(request);

        MockActionWithCookieAware action = new MockActionWithCookieAware();

        ActionContext.getContext().getValueStack().push(action);

        ActionInvocation invocation = (ActionInvocation) createMock(ActionInvocation.class);
        expect(invocation.getAction()).andReturn(action);
        expect(invocation.invoke()).andReturn(Action.SUCCESS);

        replay(invocation);

        CookieInterceptor interceptor = new CookieInterceptor();
        interceptor.setExcludedPatternsChecker(new DefaultExcludedPatternsChecker());
        interceptor.setAcceptedPatternsChecker(new DefaultAcceptedPatternsChecker());
        interceptor.setCookiesName("cookie1, cookie3");
        interceptor.setCookiesValue("cookie1value");
        interceptor.intercept(invocation);

        assertFalse(action.getCookiesMap().isEmpty());
        assertEquals(action.getCookiesMap().size(), 1);
        assertEquals(action.getCookiesMap().get("cookie1"), "cookie1value");
        assertEquals(action.getCookiesMap().get("cookie2"), null);
        assertEquals(action.getCookiesMap().get("cookie3"), null);
        assertEquals(action.getCookie1(), "cookie1value");
        assertEquals(action.getCookie2(), null);
        assertEquals(action.getCookie3(), null);
        assertEquals(ActionContext.getContext().getValueStack().findValue("cookie1"), "cookie1value");
        assertEquals(ActionContext.getContext().getValueStack().findValue("cookie2"), null);
        assertEquals(ActionContext.getContext().getValueStack().findValue("cookie3"), null);
        
        verify(invocation);
    }
