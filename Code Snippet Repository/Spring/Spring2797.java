	@Test
	public void testCannotAddAdvisorWhenFrozenUsingCast() throws Throwable {
		TestBean target = new TestBean();
		target.setAge(21);
		ProxyFactory pc = new ProxyFactory(target);
		assertFalse(pc.isFrozen());
		pc.addAdvice(new NopInterceptor());
		ITestBean proxied = (ITestBean) createProxy(pc);
		pc.setFrozen(true);
		Advised advised = (Advised) proxied;

		assertTrue(pc.isFrozen());
		try {
			advised.addAdvisor(new DefaultPointcutAdvisor(new NopInterceptor()));
			fail("Shouldn't be able to add Advisor when frozen");
		}
		catch (AopConfigException ex) {
			assertTrue(ex.getMessage().contains("frozen"));
		}
		// Check it still works: proxy factory state shouldn't have been corrupted
		assertEquals(target.getAge(), proxied.getAge());
		assertEquals(1, advised.getAdvisors().length);
	}
