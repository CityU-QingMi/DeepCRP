    protected BinaryData readUUID() {

        readFieldPrefix();

        if (scanner.scanNull()) {
            return null;
        }

        scanner.scanUUIDStringWithQuote();

        if (scanner.getTokenType() == Tokens.X_MALFORMED_BINARY_STRING) {
            throw Error.error(ErrorCode.X_42587);
        }

        value = scanner.getValue();

        return (BinaryData) value;
    }
