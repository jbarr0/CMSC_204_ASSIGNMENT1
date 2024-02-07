
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Jason Barrios Ortega
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	String password1,password2,password3,password4,p5,p6,p7;
	ArrayList<String> passwordsArray;
	@Before
	public void setUp() throws Exception {
		
		password1= "Av4%#Ck"; //  passes
		password2= "c!11&8F"; // fails sequence
		password3= "thend@2"; // fails upper alpha
		password4= "THEND^6"; // fails lower alpha
		p5 = "NOdigits!"; // fails digit
		p6 = "2Smal";// fails length 
		p7 = "hD3^32nj5w!fdc";
		String[] p = {password1,password2,password3,password4,p5,p6,p7,};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));	
		}

	@After
	public void tearDown() throws Exception {
		password1=password2=password3=password4=p5=p6=p7 = null;
		passwordsArray =  null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		assertTrue(PasswordCheckerUtility.isValidLength(password1));
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(p6);
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		assertTrue(PasswordCheckerUtility.hasUpperAlpha(password1));
		Throwable exception = Assertions.assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha(password3);
			}			
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		assertTrue(PasswordCheckerUtility.hasLowerAlpha(password1));
		Throwable exception = Assertions.assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha(password4);
			}			
		});
		assertEquals("The password must contain at least one lowercase alphabetic character", exception.getMessage());
		
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertFalse(PasswordCheckerUtility.isWeakPassword(p7));
		Throwable exception = Assertions.assertThrows(WeakPasswordException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isWeakPassword(password1);
			}			
		});
		assertEquals("The password is OK but weak - it contains fewer than 10 characters.", exception.getMessage());

	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		assertTrue(PasswordCheckerUtility.NoSameCharInSequence(p7));
		Throwable exception = Assertions.assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.NoSameCharInSequence(password2);
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence.", exception.getMessage());

		
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		assertTrue(PasswordCheckerUtility.hasDigit(p7));
		Throwable exception = Assertions.assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit(p5);
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
		
		
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(PasswordCheckerUtility.isValidPassword(p7));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		System.out.println(results.size());
		assertEquals(results.size(),5);
	    assertEquals(results.get(0), "c!11&8F  The password cannot contain more than two of the same character in sequence.");
	}
	
}
