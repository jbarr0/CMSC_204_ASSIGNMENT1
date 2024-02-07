
public class WeakPasswordException extends RuntimeException {
	public WeakPasswordException()
	{
		super("The password is OK but weak - it contains fewer than 10 characters.");
	}
}
