public class IdentifierGenerator
{
    int counter = 0;
    
    public int createNewIdentifier()
    {
        counter++;
        return counter;
    }
}