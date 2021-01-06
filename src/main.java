public class main {
    public static void main(String args[]){
        LZW lzw=new LZW();
        lzw.Compress("C:\\Users\\RCSC\\Documents\\GitHub\\LZW\\test.txt", "compressed");
        lzw.Decompress("C:\\Users\\RCSC\\Documents\\GitHub\\LZW\\compressed.txt", "decompressed");
    }
}