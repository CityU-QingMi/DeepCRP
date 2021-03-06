    public void testDoExecute_event_locationIsAction() {

        Mock mockRequest = mock(ActionRequest.class);
        Mock mockResponse = mock(ActionResponse.class);

        Constraint[] params = new Constraint[]{eq(ACTION_PARAM), eq("testView")};
        mockResponse.expects(once()).method("setRenderParameter").with(params);
        params = new Constraint[]{eq(MODE_PARAM), eq(PortletMode.VIEW.toString())};
        mockResponse.expects(once()).method("setRenderParameter").with(params);
        params = new Constraint[]{eq(PortletConstants.RENDER_DIRECT_NAMESPACE), eq("/test")};
        mockResponse.expects(once()).method("setRenderParameter").with(params);
        
        mockRequest.stubs().method("getPortletMode").will(returnValue(PortletMode.VIEW));
        mockCtx.expects(atLeastOnce()).method("getMajorVersion").will(returnValue(1));
        ActionContext ctx = ActionContext.getContext();

        ctx.put(REQUEST, mockRequest.proxy());
        ctx.put(RESPONSE, mockResponse.proxy());
        ctx.put(PHASE, PortletPhase.ACTION_PHASE);

        PortletResult result = new PortletResult();
        try {
            result.doExecute("testView.action", invocation);
        }
        catch(Exception e) {
            e.printStackTrace();
            fail("Error occured!");
        }

    }
