    public com.opensymphony.sitemesh.Decorator selectDecorator(Content content, SiteMeshContext context) {
        SiteMeshWebAppContext webAppContext = (SiteMeshWebAppContext) context;
        HttpServletRequest request = webAppContext.getRequest();
        com.opensymphony.module.sitemesh.Decorator decorator =
                decoratorMapper.getDecorator(request, new Content2HTMLPage(content, request));
        if (decorator == null || decorator.getPage() == null) {
            return new NoDecorator();
        } else {
            return new OldDecorator2NewStrutsFreemarkerDecorator(decorator);
        }
    }
