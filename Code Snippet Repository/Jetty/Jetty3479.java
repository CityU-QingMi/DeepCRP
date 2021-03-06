    @Test
    public void testAsyncWriteSimpleKnown() throws Exception
    {
        final Resource big = Resource.newClassPathResource("simple/simple.txt");
        
        _handler._async=true;
        _handler._writeLengthIfKnown=true;
        _handler._content=BufferUtil.toBuffer(big,false);
        _handler._arrayBuffer=new byte[4000];
        
        String response=_connector.getResponse("GET / HTTP/1.0\nHost: localhost:80\n\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("Content-Length: 11"));
        assertThat(response,containsString("simple text"));
    }
