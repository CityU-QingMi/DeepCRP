    @Test
    public void testTransaction() {
        doInHibernate( this::sessionFactory, s -> {
            Parent parent = s.load( Parent.class, parentID );
            assertThat( parent, notNullValue() );
            assertThat( parent, not( instanceOf( HibernateProxy.class ) ) );
            assertThat( parent, not( instanceOf( HibernateProxy.class ) ) );
            assertFalse( isPropertyInitialized( parent, "children" ) );
            checkDirtyTracking( parent );

            List children1 = parent.children;
            List children2 = parent.children;

            assertTrue( isPropertyInitialized( parent, "children" ) );
            checkDirtyTracking( parent );

            assertThat( children1, sameInstance( children2 ) );
            assertThat( children1.size(), equalTo( CHILDREN_SIZE ) );
        } );
    }
