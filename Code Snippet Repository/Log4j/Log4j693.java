    protected SocketAppender(final String name, final Layout<? extends Serializable> layout, final Filter filter,
            final AbstractSocketManager manager, final boolean ignoreExceptions, final boolean immediateFlush,
            final Advertiser advertiser) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, manager);
        if (advertiser != null) {
            final Map<String, String> configuration = new HashMap<>(layout.getContentFormat());
            configuration.putAll(manager.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            this.advertisement = advertiser.advertise(configuration);
        } else {
            this.advertisement = null;
        }
        this.advertiser = advertiser;
    }
