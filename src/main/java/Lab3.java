import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Lab3 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
          System.out.println(urlToString("https://www.bls.gov/tus/charts/chart9.txt"));
           /* Scanner in = new Scanner(System.in);
            System.out.println("Enter the url");
            String story = in.next();
            System.out.print(wordCount(story)); */
      System.out.println(wordCount("http://erdani.com/tdpl/hamlet.txt"));

    }


/**
 * Retrieve contents from a URL and return them as a string.
 *
 * @param url url to retrieve contents from
 * @return the contents from the url as a string, or an empty string on error
 */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static long wordCount (String words) {
        long count = 0L;
        boolean isWord = false;
        boolean isSpecial = false; //look for \r\n
        words = urlToString(words);
        char[] characters = words.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (Character.isLetterOrDigit(characters[i]) && i != words.length() - 1 && isSpecial == false) {
                isWord = true;
            }
            else if (!Character.isLetterOrDigit(characters[i]) && isWord && isSpecial == false) {

                if (characters[i] == '\\') {
                    isSpecial = true;
                }
                count++;
                isWord = false;
            }

            else if (Character.isLetterOrDigit(characters[i]) && i == words.length() - 1 && isSpecial == true) {
                count++;
            }
            else {
                isSpecial = false;
            }
        }
        return count;
    }

}