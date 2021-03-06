    public void testMonthRangeIntervalAfterSlash() throws Exception {
        // Test case 1
        try {
            new CronExpression("0 0 0 ? /120 2-6");
            fail("Cron did not validate bad range interval in '_blank/xxx' form");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Increment > 12 : 120");
        }

        // Test case 2
        try {
            new CronExpression("0 0 0 ? 0/120 2-6");
            fail("Cron did not validate bad range interval in in '0/xxx' form");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Increment > 12 : 120");
        }

        // Test case 3
        try {
            new CronExpression("0 0 0 ? / 2-6");
            fail("Cron did not validate bad range interval in '_blank/_blank'");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "'/' must be followed by an integer.");
        }

        // Test case 4
        try {
            new CronExpression("0 0 0 ? 0/ 2-6");
            fail("Cron did not validate bad range interval in '0/_blank'");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "'/' must be followed by an integer.");
        }
    }
