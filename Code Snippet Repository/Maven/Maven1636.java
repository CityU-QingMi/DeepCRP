    protected void mergeExtension_ArtifactId( Extension target, Extension source, boolean sourceDominant,
                                              Map<Object, Object> context )
    {
        String src = source.getArtifactId();
        if ( src != null )
        {
            if ( sourceDominant || target.getArtifactId() == null )
            {
                target.setArtifactId( src );
                target.setLocation( "artifactId", source.getLocation( "artifactId" ) );
            }
        }
    }
