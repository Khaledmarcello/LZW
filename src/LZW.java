import java.io.*;
import java.util.Vector;

public class LZW {
    public String ReadFromFile (String Path) throws IOException {
        String content = null;
        File file = new File(Path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
    public void WriteToFile(String content,String name){
        BufferedWriter writer = null;
        String fileName = name+".txt" ;
        File logFile = new File(fileName);
        try{
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}

        }
    }
    public int Exist(String chars, Vector<String> d){
        int len = d.size() , num = chars.length();
        if (num == 1)
        {
            char c = chars.toCharArray()[0];
            return ((int)c) ;
        }

        for (int i = 0; i < len ; i++)
        {
            if (chars.equals(d.get(i)))
                return i+128;
        }
        return -1;
    }


}
