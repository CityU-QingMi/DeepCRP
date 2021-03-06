    protected void assertLocalArtifactNotPresent( Artifact artifact )
        throws Exception
    {
        ArtifactRepository localRepo = localRepository();

        String path = localRepo.pathOf( artifact );

        File file = new File( localRepo.getBasedir(), path );

        if ( file.exists() )
        {
            fail( "Local artifact " + file + " should not be present." );
        }
    }
