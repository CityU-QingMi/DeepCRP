    public static String relativePath(String base,
            String pathSpec,
            String path )
    {
        String info=pathInfo(pathSpec,path);
        if (info==null)
            info=path;

        if( info.startsWith( "./"))
            info = info.substring( 2);
        if( base.endsWith( URIUtil.SLASH))
            if( info.startsWith( URIUtil.SLASH))
                path = base + info.substring(1);
            else
                path = base + info;
        else
            if( info.startsWith( URIUtil.SLASH))
                path = base + info;
            else
                path = base + URIUtil.SLASH + info;
        return path;
    }
