    private void send(byte[] bytes, int offset, int length) throws IOException
    {
        synchronized (this)
        {
            if (closed)
                throw new IOException("Stream is closed");

            while (length > 0)
            {
                // There may be no space available, we want
                // to handle correctly when space == 0.
                int space = buffer.remaining();
                int size = Math.min(space, length);
                buffer.put(bytes, offset, size);
                offset += size;
                length -= size;
                if (length > 0)
                {
                    // If we could not write everything, it means
                    // that the buffer was full, so flush it.
                    flush(false);
                }
            }
        }
    }
