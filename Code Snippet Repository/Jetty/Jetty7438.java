    @Test
    public void test14_16_PartialRange_MixedRanges() throws Exception
    {
        String alpha = ALPHA;

        // server should not return a 416 if at least one syntactically valid ranges
        // are is satisfiable
        //
        // should test for combinations of good and syntactically
        // invalid ranges here, but I am not certain what the right
        // behavior is anymore
        //
        // return data for valid ranges while ignoring unsatisfiable
        // ranges

        // a) Range: bytes=a-b,5-8

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /rfc2616-webapp/alpha.txt HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Range: bytes=a-b,5-8\n"); // Invalid range, then Valid range
        req1.append("Connection: close\n");
        req1.append("\n");

        http.setTimeoutMillis(60000);
        HttpTester.Response response = http.request(req1);

        String msg = "Partial Range (Mixed): 'bytes=a-b,5-8'";
        assertEquals(msg,HttpStatus.PARTIAL_CONTENT_206, response.getStatus());
        assertEquals(msg,"bytes 5-8/27", response.get("Content-Range"));
        assertTrue(msg,response.getContent().contains(alpha.substring(5,8 + 1)));
    }
