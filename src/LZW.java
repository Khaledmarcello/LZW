import java.io.*;
import java.util.Scanner;
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
    public void Compress(String path , String name) {
        String text = new String();
        String compressed = new String ("") ;
        Vector<String> d = new Vector<String>() ;

        try {
            text = ReadFromFile(path);
        } catch (IOException e) {}

        int len = text.length();
        String end = "";

        for (int i = 0; i<len; i++)
        {
            String curr =new String("");
            curr += (text.toCharArray()[i]) ;
            int ptr = 0 ;
            while (Exist(curr, d)!=-1)
            {
                i++;
                ptr = Exist(curr , d) ;

                if (i==len)
                    break ;
                curr += text.toCharArray()[i];

            }
            d.addElement(curr);
            end += curr ;

            compressed+=ptr+" ";
            i--;
        }

        WriteToFile(compressed , name);

    }
    public void Decompress(String path , String name) {
        String text = new String();
        String decompressed = new String ("") ;
        Vector<String> d = new Vector<String>() ;
        try {
            text = ReadFromFile(path);
        } catch (IOException e) {}

        int len = text.length();
        Scanner stream = new Scanner (text) ;
        String temp = "" ;
        while (stream.hasNextInt())
        {	String Decomp = "" ;
            int curr = stream.nextInt();
            boolean exist = false , unknown = true ;
            if (curr<=127)
            {
                exist = true ;
                String str = "" ;
                str += (char)curr ;
                Decomp=str ;
            }
            else
            {
                if (curr-128 < d.size())
                    Decomp=d.get(curr-128) ;

                else
                {
                    Decomp = (temp + temp.toCharArray()[0]);
                }
            }

            decompressed+=Decomp ;

            if ((temp+Decomp.toCharArray()[0]).length() > 1)
                d.add(temp+Decomp.toCharArray()[0]);

            temp = Decomp;

        }
        WriteToFile(decompressed , name);
    }

}
