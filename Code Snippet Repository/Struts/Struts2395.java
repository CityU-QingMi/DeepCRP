    public void testSwitchToHttpNonDefaultPort() {

        String expectedString = "http://www.mydomain.com:7001/mywebapp/MyAction.action?foo=bar&amp;hello=earth&amp;hello=mars";

        urlHelper.setHttpPort("7001");
        urlHelper.setHttpsPort("7002");

        Mock mockHttpServletRequest = new Mock(HttpServletRequest.class);
        mockHttpServletRequest.expectAndReturn("getServerName", "www.mydomain.com");
        mockHttpServletRequest.expectAndReturn("getScheme", "https");
        mockHttpServletRequest.expectAndReturn("getServerPort", 7002);
        mockHttpServletRequest.expectAndReturn("getContextPath", "/mywebapp");

        Mock mockHttpServletResponse = new Mock(HttpServletResponse.class);
        mockHttpServletResponse.expectAndReturn("encodeURL", expectedString, expectedString);

        String actionName = "/MyAction.action";
        TreeMap params = new TreeMap();
        params.put("hello", new String[]{"earth", "mars"});
        params.put("foo", "bar");

        String urlString = urlHelper.buildUrl(actionName, (HttpServletRequest) mockHttpServletRequest.proxy(), (HttpServletResponse) mockHttpServletResponse.proxy(), params, "http", true, true);
        assertEquals(expectedString, urlString);
    }
