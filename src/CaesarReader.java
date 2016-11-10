import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Caesar-ohne-Modulo-entschlüsselnder Reader
 * Created by hmueller on 07.04.2016.
 */
public class CaesarReader extends FilterReader {

    private final int shift;

    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @param shift
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    protected CaesarReader(int shift, Reader in) {
        super(in);
        this.shift = shift;
    }

    @Override
    public int read() throws IOException {
        int read = super.read();
        if( read == -1 ){
            return -1;
        } else {
            return  read - this.shift;
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);

        if( read == -1 ){
            return -1;
        }

        for (int i = off; i < off+len; i++) {
            cbuf[i] = (char)(cbuf[i] - this.shift);
        }

        return read;
    }
}
