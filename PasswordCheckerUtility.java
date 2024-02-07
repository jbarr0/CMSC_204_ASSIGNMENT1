import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

	public static void comparePasswords(String password, String passwordConfirm) {

		if (!(password.length() == passwordConfirm.length()))
			throw new UnmatchedException();
		for (int i = 0; i < password.length(); i++)
			if (!(password.charAt(i) == passwordConfirm.charAt(i)))
				throw new UnmatchedException();

	}

	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (!(password.length() == passwordConfirm.length()))
			return false;
		for (int i = 0; i < password.length(); i++)
			if (!(password.charAt(i) == passwordConfirm.charAt(i)))
				return false;

		return true;
	}

	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalidPasswords = new ArrayList<>();
		for (int i = 0; i < passwords.size(); i++) {
			try {
				isValidPassword(passwords.get(i));
			} catch (LengthException e) {
				invalidPasswords.add(passwords.get(i) + " " + "The password must be at least 6 characters long");
			} catch (NoUpperAlphaException e) {
				invalidPasswords.add(
						passwords.get(i) + " The password must contain at least one uppercase alphabetic character");

			} catch (NoLowerAlphaException e) {
				invalidPasswords.add(
						passwords.get(i) + " The password must contain at least one lowercase alphabetic character");

			} catch (NoDigitException e) {
				invalidPasswords.add(passwords.get(i) + " The password must contain at least one digit");
			} catch (NoSpecialCharacterException e) {
				invalidPasswords.add(passwords.get(i) + " The password must contain at least one special character");
			} catch (InvalidSequenceException e) {
				invalidPasswords.add(passwords.get(i)
						+ "  The password cannot contain more than two of the same character in sequence.");
			} catch (WeakPasswordException e) {
				invalidPasswords
						.add(passwords.get(i) + " The password is OK but weak - it contains fewer than 10 characters.");
			}

		}

		return invalidPasswords;
	}

	public static boolean hasBetweenSixAndNineChars(String password) {
		int len = password.length();
		if (len >= 6 && len <= 9)
			return true;
		else
			return false;
	}

	public static boolean hasDigit(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= '0' && password.charAt(i) <= '9')
				return true;
		}
		throw new NoDigitException();
	}

	public static boolean hasLowerAlpha(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
				return true;
		}
		throw new NoLowerAlphaException();
	}

	public static boolean hasUpperAlpha(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
				return true;
		}
		throw new NoUpperAlphaException();
	}

	public static boolean hasSpecialChar(String password) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		return (!matcher.matches());
//		for (int i =0; i < password.length(); i++)
//			if (!((password.charAt(i) >= 'A' && password.charAt(i)<='Z') && (password.charAt(i) >= 'a' && password.charAt(i)<='z')&&(password.charAt(i) >= '0' && password.charAt(i)<='9')))
//				return true;
//		
//		return false;
	}

	public static boolean isValidLength(String password) {
		if (password.length() < 6)
			throw new LengthException();
		else
			return true;
	}

	public static boolean isValidPassword(String password) {
		if (!(isValidLength(password))) {
			throw new LengthException();
		}
		if (!(hasUpperAlpha(password))) {
			throw new NoUpperAlphaException();
		}
		if (!(hasLowerAlpha(password))) {
			throw new NoLowerAlphaException();
		}
		if (!(hasDigit(password))) {
			throw new NoDigitException();
		}
		if (!(hasSpecialChar(password))) {
			throw new NoSpecialCharacterException();
		}
		if (!(NoSameCharInSequence(password)))
			throw new InvalidSequenceException();

		return true;
	}

	public static boolean isWeakPassword(String password) {
		int len = password.length();
		if ((PasswordCheckerUtility.isValidPassword(password) && hasBetweenSixAndNineChars(password)))
			throw new WeakPasswordException();
		else
			return false;
	}

	public static boolean NoSameCharInSequence(String password) {

		for (int currentCharIndex = 0; currentCharIndex < password.length() - 1; currentCharIndex++) {
			if (password.charAt(currentCharIndex) == password.charAt(currentCharIndex + 1))
				throw new InvalidSequenceException();

		}
		return true;
	}

}