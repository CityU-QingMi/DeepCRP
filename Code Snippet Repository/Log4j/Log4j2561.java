    public void testUsageSubCommandStatus() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CommandLine.usage(new GitStatus(), new PrintStream(baos, true, "UTF8"));
            String result = baos.toString("UTF8");
            System.out.println(result);
            assert String.format(EXPECTED_USAGE_GITSTATUS).equals(result);
        } catch (UnsupportedEncodingException ex) {
            throw new InternalError(ex.toString());
        }
    }
