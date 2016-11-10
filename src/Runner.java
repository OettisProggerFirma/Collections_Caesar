import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by doetken on 27.04.2016.
 */
public class Runner {

    public static void main(String[] args) {


        HashMap<Character, Integer> buchstaben = new HashMap<>();
        try (BufferedReader puffer = new BufferedReader(new FileReader("ciphertext.txt"))) {
            int lese = 0;
            while ((lese = puffer.read()) != -1) {
                buchstaben.put((char) lese, buchstaben.getOrDefault((char) lese, 0) + 1);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int max = Collections.max(buchstaben.values());
        int key = 0;

        for (Character character : buchstaben.keySet()) {
            if (buchstaben.get(character) == max) {
                System.out.println("' ' wurde verschlüsselt als '" + character + "' ");
                key = character - ' ';
                System.out.println("Der Schlüssel ist " + key);
            }

            try (BufferedReader bfr = new BufferedReader(new CaesarReader(key, new FileReader("ciphertext.txt")))) {
                String read = "";
                while ((read = bfr.readLine()) != null) {
                    System.out.println(read);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

}
