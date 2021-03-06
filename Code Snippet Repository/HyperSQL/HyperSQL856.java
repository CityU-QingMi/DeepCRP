    public synchronized int executeUpdate(String sql,
            int autoGeneratedKeys) throws SQLException {

        if (autoGeneratedKeys != Statement.RETURN_GENERATED_KEYS
                && autoGeneratedKeys != Statement.NO_GENERATED_KEYS) {
            throw JDBCUtil.invalidArgument("autoGeneratedKeys");
        }
        fetchResult(sql, StatementTypes.RETURN_COUNT, autoGeneratedKeys, null,
                    null);

        if (resultIn.isError()) {
            throw JDBCUtil.sqlException(resultIn);
        }

        return resultIn.getUpdateCount();
    }
