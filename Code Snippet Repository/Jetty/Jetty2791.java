        @Override
        public Object getAttribute(String key)
        {
            if (Dispatcher.this._named==null)
            {
                if (key.equals(FORWARD_PATH_INFO))
                    return _pathInfo;
                if (key.equals(FORWARD_REQUEST_URI))
                    return _requestURI;
                if (key.equals(FORWARD_SERVLET_PATH))
                    return _servletPath;
                if (key.equals(FORWARD_CONTEXT_PATH))
                    return _contextPath;
                if (key.equals(FORWARD_QUERY_STRING))
                    return _query;
            }

            if (key.startsWith(__INCLUDE_PREFIX))
                return null;

            return _attr.getAttribute(key);
        }
