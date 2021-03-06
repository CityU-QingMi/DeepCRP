                public String extractConstraintName(SQLException sqle) {
                        String constraintName = null;

                        int errorCode = JDBCExceptionHelper.extractErrorCode( sqle );

                        if ( errorCode == -8 ) {
                                constraintName = extractUsingTemplate(
                                                "Integrity constraint violation ", " table:", sqle.getMessage()
                                );
                        }
                        else if ( errorCode == -9 ) {
                                constraintName = extractUsingTemplate(
                                                "Violation of unique index: ", " in statement [", sqle.getMessage()
                                );
                        }
                        else if ( errorCode == -104 ) {
                                constraintName = extractUsingTemplate(
                                                "Unique constraint violation: ", " in statement [", sqle.getMessage()
                                );
                        }
                        else if ( errorCode == -177 ) {
                                constraintName = extractUsingTemplate(
                                                "Integrity constraint violation - no parent ", " table:",
                                                sqle.getMessage()
                                );
                        }
                        return constraintName;
                }
