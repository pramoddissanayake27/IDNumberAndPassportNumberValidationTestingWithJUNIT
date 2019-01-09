/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationprocessing;

import static com.epic.cms.system.util.validate.UserInputValidator.isAlphaNumeric;
import static com.epic.cms.system.util.validate.UserInputValidator.isNumeric;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pramod_d
 */
public class IDAndPassportNumberValidator {

    private static String inputNICNumber;
    private static boolean isValidNICNumber;
    private static String inputPassportNumber;
    private static boolean isValidPassportNumber;

    @BeforeClass
    public static void initVariables() {
        try {

            inputNICNumber = "199427901593";
            isValidNICNumber = true;

            inputPassportNumber = "a2345123";
            isValidPassportNumber = true;

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Successfully execute method: initVariables()");
        } catch (Exception ex) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Failed to execute method: initVariables()");
        }
    }

    @Test
    public void methodExecutor() {
        try {
            //validate NIC number
            boolean NICResult = isValidNICNumber(inputNICNumber);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Is valid NIC Number: " + NICResult);
            assertEquals(isValidNICNumber, NICResult);

            //validate Passport number
            boolean SriLankanPassportResult = isValidSriLankanPassportNumber(inputPassportNumber);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Is valid Sri lankan Passport Number: " + SriLankanPassportResult);
            assertEquals(isValidPassportNumber, SriLankanPassportResult);
            
            boolean GlobalPassportResult = isValidGlobalPassportNumber(inputPassportNumber);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Is valid Global Passport Number: " + GlobalPassportResult);
            assertEquals(isValidPassportNumber, GlobalPassportResult);

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Successfully execute method: methodExecutor()");
        } catch (Exception ex) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>Failed to execute method: methodExecutor()");
        }
    }

    /**
     * correct only any lowercase letter or any uppercase letter in exactly 1 or
     * 2 times with any digit in exactly 7 times for example Az0989876 or
     * a2345123
     *
     * @param value
     * @return
     */
    public static boolean isValidSriLankanPassportNumber(String value) {
        boolean valied = false;

        try {
            if (value.matches("[a-zA-Z]{2}[0-9]{7}") || value.matches("[a-zA-Z]{1}[0-9]{7}")) {
                valied = true;
            }

        } catch (Exception ex) {
            valied = false;
        }
        return valied;
    }
    
    
    /**
     * correct only any lowercase letter or any uppercase letter at the begin with maximum 11 digits
     * @param value
     * @return 
     */
    public static boolean isValidGlobalPassportNumber(String value) {
        boolean valied = true;

        try {
            if (value.length() > 11) {
                valied = false;
            }
            if (!isAlphaNumeric(value)) {
                valied = false;
            }
        } catch (Exception ex) {
            valied = false;
        }
        return valied;
    }

    /**
     *
     * @param nic
     * @return
     */
    public static boolean isValidNICNumber(String nic) {
        boolean status = true;

        try {
            switch (nic.length()) {
                case 10:
                    String nicFirst_9_Digit = nic.substring(0, 9);
                    String nicLastCharacter = nic.substring(9, 10);
                    if (!isNumeric(nicFirst_9_Digit)) {
                        status = false;

                    }
                    if (!nicLastCharacter.equalsIgnoreCase("v") && !nicLastCharacter.equalsIgnoreCase("x")) {
                        status = false;

                    }
                    break;
                case 12:
                    if (!isNumeric(nic)) {
                        status = false;
                    }
                    break;
                default:
                    status = false;
                    break;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

}
