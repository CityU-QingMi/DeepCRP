	@Test
	public void startDeferredResultProcessingErrorAndResumeThroughInterceptor() throws Exception {

		DeferredResult<Integer> deferredResult = new DeferredResult<>();

		DeferredResultProcessingInterceptor interceptor = new DeferredResultProcessingInterceptor() {
			@Override
			public <T> boolean handleError(NativeWebRequest request, DeferredResult<T> result, Throwable t)
					throws Exception {
				result.setErrorResult(t);
				return true;
			}
		};

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		Exception e = new Exception();
		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(e, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
