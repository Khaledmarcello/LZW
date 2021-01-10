public class main {
    public static void main(String args[]) {
        LZW lzw = new LZW();
        //Take Care of the Path of the project
        lzw.Compress("D:\\Java Repos\\LZW\\test.txt", "compressed");
        lzw.Decompress("D:\\Java Repos\\LZW\\compressed.txt", "decompressed");
    }
}