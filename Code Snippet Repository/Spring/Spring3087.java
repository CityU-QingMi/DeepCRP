	public void testEvict(CacheableService<?> service) throws Exception {
		Object o1 = new Object();

		Object r1 = service.cache(o1);
		Object r2 = service.cache(o1);

		assertSame(r1, r2);
		service.invalidate(o1);
		Object r3 = service.cache(o1);
		Object r4 = service.cache(o1);
		assertNotSame(r1, r3);
		assertSame(r3, r4);
	}
