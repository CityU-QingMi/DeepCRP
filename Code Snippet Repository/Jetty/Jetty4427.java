    @Test
    public void testParameterMap() throws Exception
    {
        tester.addServlet(TestServletParameterMap.class,"/test2");

        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        // test GET
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/dump");

        String boundary="XyXyXy";
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);


        String content = "--" + boundary + "\r\n"+
        "Content-Disposition: form-data; name=\"fileup\"; filename=\"Diplomsko Delo Lektorirano KON&#268;NA.doc\"\r\n"+
        "Content-Type: application/octet-stream\r\n\r\n"+
        "How now brown cow."+
        "\r\n--" + boundary + "\r\n"+
        "Content-Disposition: form-data; name=\"strup\""+
        "Content-Type: application/octet-stream\r\n\r\n"+
        "How now brown cow."+
        "\r\n--" + boundary + "--\r\n\r\n";

        request.setContent(content);

        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK,response.getStatus());
        assertTrue(response.getContent().indexOf("brown cow")>=0);
    }
