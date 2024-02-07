
public class LengthException extends RuntimeException{
	
	LengthException()
	{
		super("The password must be at least 6 characters long");
	}
}
