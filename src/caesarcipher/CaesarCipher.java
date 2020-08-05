//Adiyan A. Ahmed 
//This program allows the user to use the Caesar Cipher trick with any 
//desired message. The program allows the user to each one of four options
//in the initial menu, encode (e) decode (d) auto (a) and quit (q). The 
//encode option will take a user message, and a key by which 
//to shift the message, and move every letter forward in the string 
//according to the key. The decode option will do the same operation
//in reverse, to find the original message. The automatic option will 
//present every possible decoded message, and present the best one.
package caesarcipher;

import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        char option = ' ';
        System.out.println("WELCOME TO THE CAESAR CIPHER PROGRAM");

        while (option != 'q') {
            System.out.println("");//aesthetic   
            System.out.println("Pick an option: encode[e], decode[d], automated[a], quit[q]");
            option = keyboard.next().charAt(0);
            keyboard.nextLine(); //Clearing buffer

            //Encode option
            if (option == 'e') {
                String empty = "";
                String message = ""; //variable storing message
                int shift = 0; //variable storing shift/key
                boolean shiftContinue = false; //boolean to repeat user input until value between 20-25 is entered

                //User input                                 
                while (message.equals(empty)) { //User Verification, string can't be empty
                    System.out.println("Please enter a message to encode:");
                    message = keyboard.nextLine();
                }

                do { //User verification, shift needs to be between 0 and 25
                    System.out.println("Please enter a key (between 0 and 25) by which to shift the message: ");
                    shift = keyboard.nextInt();
                    if (shift >= 0 && shift <= 25) { //once value entered is between 0-25, stop looping
                        shiftContinue = true;
                    }
                } while (shiftContinue == false);

                //Printing out encoded message           
                System.out.println("The encoded message: " + changeIt(message, shift));
            }

            //Decode option
            if (option == 'd') {
                String empty = "";
                String message = "";
                int shift = 0;
                boolean shiftContinue = false;

                //User input 
                while (message.equals(empty)) { //User verification, string can't be empty
                    System.out.println("Please enter a message to decode:");
                    message = keyboard.nextLine();
                }

                do { //User verification, shift needs to be between 0 and -25
                    System.out.println("Please enter a NEGATIVE key (between 0 and -25) by which to shift the message: ");
                    shift = keyboard.nextInt();
                    if (shift <= 0 && shift >= -25) { //once value between 0 - -25 is entered, stop looping
                        shiftContinue = true;
                    }
                } while (shiftContinue == false);

                //Printing out decoded message           
                System.out.println("The decoded message: " + changeIt(message, shift));
            }

            //Automatic option (breakCode)
            if (option == 'a') {
                String empty = "";
                String message = "";    

                //User input, asking for already encoded message
                while (message.equals(empty)) {
                    System.out.println("Please enter an encoded message:");
                    message = keyboard.nextLine();
                }

                //Outputting every possible string
                System.out.println("Here are the possibilities:");
                String[] automated = breakCode(message);

                for (int i = 0; i < 26; i++) {
                    System.out.println("Key " + i + " - " + automated[i]); //printing out each key with string
                }

                //Outputting what is likely the correct message
                System.out.println(""); //aesthetic
                System.out.println("The correct message MIGHT be:");
                System.out.println(commonWords(automated));
            }
        }
    }

    //ONE METHOD FOR ENCODING AND DECODING 
    public static String changeIt(String message, int shift) {
        String output = "";
        int charCount = message.length() - 1;
        
        //Loop going through every character
        for (int counter = 0; counter <= charCount; counter++) {

            //Lowercase Letters (ASCII Values)
            if (message.charAt(counter) <= 122 && message.charAt(counter) >= 97) {

                //If the shifted character ends up being before a 
                if (message.charAt(counter) + shift <= 96) {
                    output = output + (char) (message.charAt(counter) + shift + 26);
                //If the shifted character ends up being after z
                } else if (message.charAt(counter) + shift >= 123){
                    output = output + (char) (message.charAt(counter) + shift - 26);                    
                } 
                
                //If the character remains between a and z after the shift
                if (message.charAt(counter) + shift <= 122 && message.charAt(counter) + shift >= 97){
                    output = output + (char) (message.charAt(counter) + shift);
                }

            }

            //Capital Letters (ASCII Values)
            if (message.charAt(counter) <= 90 && message.charAt(counter) >= 65) {

                //If the shifted character ends up being before A 
                if (message.charAt(counter) + shift <= 64) {
                    output = output + (char) (message.charAt(counter) + shift + 26);
                //If the shifted character ends up being after Z     
                } else if (message.charAt(counter) + shift >= 91) {
                    output = output + (char) (message.charAt(counter) + shift - 26);                    
                } 
                
                //If the character remains between A and Z after the shift
                if (message.charAt(counter) + shift <= 90 && message.charAt(counter) + shift >= 65){
                    output = output + (char) (message.charAt(counter) + shift);
                }

            }
            
            //Punctuation (! ? , . - SPACE)
            if (message.charAt(counter) == 46
                    || message.charAt(counter) == 44
                    || message.charAt(counter) == 33
                    || message.charAt(counter) == 45
                    || message.charAt(counter) == 32
                    || message.charAt(counter) == 63) {
                output = output + (char) (message.charAt(counter));
            }

        }
        return output;
    }

    //AUTOMATIC DECODING METHOD
    public static String[] breakCode(String message) {

        //Creating an array to store every possible result
        String[] results = new String[26];

        //Going through every possible key and storing the shifted message
        for (int counter = 0; counter < 26; counter++) {
            results[counter] = decode(message, counter);
        }

        return results;
    }

    //METHOD FOR ENCODING 
    public static String encode(String message, int shift) {
        String output = "";
        int charCount = message.length() - 1;

        for (int counter = 0; counter <= charCount; counter++) {

            //Lowercase Letters (ASCII Values)
            if (message.charAt(counter) >= 97 && message.charAt(counter) <= 122) {

                //If the shifted character ends up being after z (looping alphabet)
                if (message.charAt(counter) + shift >= 123) {
                    output = output + (char) (message.charAt(counter) + shift - 26);
                }
                //If character does not shift after z
                if (message.charAt(counter) + shift < 123) {
                    output = output + (char) (message.charAt(counter) + shift);
                }

            }

            //Capital Letters (ASCII Values)
            if (message.charAt(counter) >= 65 && message.charAt(counter) <= 90) {

                //If the shifted character ends up being after Z (looping alphabet)
                if (message.charAt(counter) + shift >= 91) {
                    output = output + (char) (message.charAt(counter) + shift - 26);
                }
                //If character does not shift after Z
                if (message.charAt(counter) + shift < 91) {
                    output = output + (char) (message.charAt(counter) + shift);
                }

            }

            //Punctuation (! ? , . - SPACE)
            if (message.charAt(counter) == 46
                    || message.charAt(counter) == 44
                    || message.charAt(counter) == 33
                    || message.charAt(counter) == 45
                    || message.charAt(counter) == 32
                    || message.charAt(counter) == 63) {
                output = output + (char) (message.charAt(counter)); //leaving character alone
            }

        }
        return (output); 
    }

    //METHOD FOR DECODING
    public static String decode(String message, int shift) {
        String output = "";
        int charCount = message.length() - 1;

        for (int counter = 0; counter <= charCount; counter++) {

            //Lowercase Letters (ASCII Values)
            if (message.charAt(counter) >= 97 && message.charAt(counter) <= 122) {

                //If the shifted character ends up being before a (looping alphabet)
                if (message.charAt(counter) - shift < 97) {
                    output = output + (char) (message.charAt(counter) - shift + 26);
                }
                //If character does not shift before a 
                if (message.charAt(counter) - shift >= 97) {
                    output = output + (char) (message.charAt(counter) - shift);
                }

            }

            //Capital Letters (ASCII Values)
            if (message.charAt(counter) >= 65 && message.charAt(counter) <= 90) {

                //If the shifted character ends up being before A (looping alphabet)
                if (message.charAt(counter) - shift < 65) {
                    output = output + (char) (message.charAt(counter) - shift + 26);
                }
                //If character does not shift before A
                if (message.charAt(counter) - shift >= 65) {
                    output = output + (char) (message.charAt(counter) - shift);
                }

            }

            //Punctuation (! ? , . - SPACE)
            if (message.charAt(counter) == 46
                    || message.charAt(counter) == 44
                    || message.charAt(counter) == 33
                    || message.charAt(counter) == 45
                    || message.charAt(counter) == 32
                    || message.charAt(counter) == 63) {
                output = output + (char) (message.charAt(counter)); //leaving character alone 
            }

        }
        return (output);
    }

    //DETERMINE BEST AUTO-DECODE METHOD
    public static String commonWords(String[] automated) {
        //List of common words in the english language to check for
        String[] words = new String[12];

        words[0] = " the ";
        words[1] = " you ";
        words[2] = " if ";
        words[3] = " that ";
        words[4] = " at ";
        words[5] = " and ";
        words[6] = " to ";
        words[7] = " is ";
        words[8] = " of ";
        words[9] = " it ";
        words[10] = " are ";
        words[11] = " am ";

        //Variables to count which key delivers best decode        
        int key = 0;
        int count = 0; //how many common words present
        int highestCount = 0; //highest amount of common words present

        //Going through every possibility and checking for common words
        for (int x = 0; x < 26; x++) { //running through every word in array
            count = 0;
            for (int i = 0; i < 12; i++) { //running through common words
                if (automated[x].indexOf(words[i]) != -1) {
                    count++; //counting how many common words are present in key                                             
                }
            }
            //Keeping track of which key had the most common words
            if (count > highestCount) {
                highestCount = count;
                key = x; //storing which key had the highest count 
            }
        }

        //Concatenating key and best decode string 
        String mostLikely = ("Key " + key + " - " + automated[key]);
        return mostLikely;

    }
}
