    @Override
    public boolean willDecode(String s)
    {
        if (s == null)
        {
            return false;
        }
        try
        {
            Short.parseShort(s);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
