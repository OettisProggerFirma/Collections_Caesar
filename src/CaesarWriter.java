import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * Caesar-ohne-Modulo-verschlüsselnder Writer
 *
 * Created by hmueller on 07.04.2016.
 */
public class CaesarWriter extends FilterWriter {

    private final int shift;

    /**
     * Create a new filtered writer.
     *
     * @param shift the number of places to shift the alphabet
     * @param out a Writer object to provide the underlying stream.
     * @throws NullPointerException if <code>out</code> is <code>null</code>
     */
    protected CaesarWriter( int shift, Writer out) {
        super(out);
        this.shift = shift;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + this.shift);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] kopie = Arrays.copyOfRange(cbuf, off, len);
        for (int i = 0; i < kopie.length; i++) {
            kopie[i] = (char) (kopie[i] + this.shift);

        }
        super.write(kopie, 0, len);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        this.write( str.toCharArray(), off, len);
    }
}
