
public class NoLowerAlphaException extends RuntimeException {

	public NoLowerAlphaException()
	{
		super("The password must contain at least one lowercase alphabetic character");
	}
}
