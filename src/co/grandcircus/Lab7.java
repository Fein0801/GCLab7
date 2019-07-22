package co.grandcircus;

import java.util.Scanner;

public class Lab7 {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Scanner scan = new Scanner(System.in);
	System.out.println("Welcome to the regex validator!");

	boolean cont = true;
	boolean validExpression = false;

	String input = "";
	String expressionType = "";
	int choice;

	while (cont) {
	    System.out.println("Type of expression:");
	    System.out.println(
		    "\t1. Phone Number\n" + "\t2. Legal Name\n" + "\t3. Email Address\n" + "\t4. Date\n"
			    + "\t0. Exit");

	    if (scan.hasNextInt()) {
		choice = scan.nextInt();
	    } else {
		choice = -1;
	    }
	    scan.nextLine();

	    switch (choice) {
	    case 1:
		expressionType = "phone number";
		System.out.println("Please enter a phone number: ");
		input = scan.nextLine();
		validExpression = isValidPhoneNumber(input);
		break;
	    case 2:
		expressionType = "name";
		System.out.println("Please enter a name: ");
		input = scan.nextLine();
		validExpression = isLegalName(input);
		break;
	    case 3:
		expressionType = "email address";
		System.out.println("Please enter an email address: ");
		input = scan.nextLine();
		validExpression = isValidEmailAddress(input);
		break;
	    case 4:
		expressionType = "date";
		System.out.println("Please enter a date: ");
		input = scan.nextLine();
		validExpression = isValidDate(input);
		break;
	    case 0:
		cont = false;
		break;
	    default:
		System.out.println("Please choose one of the menu options. ");
		continue;
	    }

	    if (choice != 0) {
		if (validExpression) {
		    System.out.println("\"" + input + "\" is a valid " + expressionType + ".");
		} else {
		    System.out.println("\"" + input + "\" is not a valid " + expressionType + ".");
		}
	    }

	}

	System.out.println("Goodbye.");

	scan.close();
    }

    private static boolean isValidExpression(String regex, String input) {
	return input.matches(regex);
    }

    public static boolean isValidPhoneNumber(String input) {
	// Checks phone numbers in formats "xxx-xxx-xxxx", "(xxx) xxx-xxxx", and
	// "(xxx)xxx-xxxx"
	boolean valid = false;
	String[] phoneRegex = { "\\d{3}-\\d{3}-\\d{4}", "\\(\\d{3}\\)\\d{3}-\\d{4}", "\\(\\d{3}\\) \\d{3}-\\d{4}" };
	for (String regex : phoneRegex) {
	    valid = isValidExpression(regex, input);
	    if (valid = true) {
		break;
	    }
	}
	return valid;
    }

    public static boolean isValidEmailAddress(String input) {
	// Allow "-", ".", "_" in body
	String regex = "[\\w\\_\\.\\-]{5,30}@\\w{5,11}\\.\\w{2,3}";
	return isValidExpression(regex, input);
    }

    public static boolean isLegalName(String input) {
	// Allow letters and hyphens, but do not allow two in a row
	String regex = "[A-Z][\\w\\-]{1,29}";
	if (input.contains(" ")) {
	    String[] names = input.split(" ");
	    for (String s : names) {
		if (s.contains("--")) {
		    return false;
		}
		return true && isValidExpression(regex, s);
	    }
	}
	return isValidExpression(regex, input);
    }

    public static boolean isValidDate(String input) {
	String regex = "\\d{2}\\/\\d{2}\\/\\d{4}";
	return isValidExpression(regex, input);
    }

}
