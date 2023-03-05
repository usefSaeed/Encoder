import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HexWriter {
    private boolean isEmpty;
    private int content;
    OutputStream os;

    public HexWriter(String path) throws FileNotFoundException {
        this.isEmpty = true;
        this.os = new FileOutputStream(path);
    }

    private void write() throws IOException {
        os.write((byte) content);
        clear();
    }

    private void clear(){
        content = 0x0;
        isEmpty = true;
    }

    public void appendHexDigit(int hex) throws IOException {
        if (isEmpty){
            content = hex;
            isEmpty = false;
        }else{
            content += hex << 4;
            write();
        }
    }

    public void appendHexByte(int hex) throws Exception {
        if (isEmpty){
            content = hex;
            write();
        }else{
            throw new Exception("Cannot append byte. Awaiting missing hex digit");
        }
    }

    public void appendChar(char c) throws Exception {
        if (isEmpty){
            content = c;
            System.out.println(content);
            write();
        }else{
            throw new Exception("Cannot append byte. Awaiting missing hex digit");
        }
    }

    public void appendWord(String s) throws Exception {
        if (isEmpty){
            for (int i=0;i<s.length();i++){
                appendChar(s.charAt(i));
            }
        }else{
            throw new Exception("Cannot append byte. Awaiting missing hex digit");
        }
    }

    public void close() throws IOException {
        os.close();
    }

    public static void main(String[] arg) throws Exception {
        String path = "src\\files\\lol-mp";
        HexWriter hw = new HexWriter(path);
        hw.appendWord("userName");
    }
}
