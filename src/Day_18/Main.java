package Day_18;

import java.io.*;

//BufferedReader br = new BufferedReader(fr);处理的是：字符 char / String
//图片、视频、压缩包等 → 字节流 ；.txt、.java 等文本 → 字符流通常更方便
//今天最重要的方法：readLine()，直接读取一整行文本。String line = br.readLine();
//字节流：bis.read()文件结束返回：-1；但是br.readLine()读取结束以后返回的是：null
//所以标准写法：
//String line;
//while ((line = br.readLine()) != null) {
//    System.out.println(line);
//}
//BufferedWriter这时候写字符串非常简单：bw.write("David");
//但是有一个坑：write() 不会自动换行
//所以需要：bw.write("David");bw.newLine();
//readLine() 有一个容易忽略的细节
//假设文件：
//David
//Tom
//Alice
//执行：String line = br.readLine();得到：David，但得到的 String 不包含最后的换行符。
//也就是说它不是："David\n"，而只是："David"，如果想把读取的内容复制到另一个文件：
//bw.write(line);还必须自己：bw.newLine();
//字节流更底层、更通用；字符流专门为了文本处理而设计。
//那么中文怎么办？为了让编码明确，以后实际项目中更推荐明确 UTF-8。可以先认识这种写法：
//InputStreamReader isr =
//        new InputStreamReader(
//                new FileInputStream("student.txt"),
//                StandardCharsets.UTF_8
//        );
//
//BufferedReader br =
//        new BufferedReader(isr);
//结构变成：FileInputStream—>InputStreamReader->BufferedReader
//这里：InputStreamReader干了一件很重要的事情：字节->按照 UTF-8 解码->字符,它实际上就是：字节流 → 字符流的桥梁。
public class Main {
    public static void main(String[] args){
        /*try{
            FileReader fr =new FileReader("src/Day_18/student.txt");
            BufferedReader br =new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        /*try{
            FileWriter fw=new FileWriter("src/Day_18/users.txt");

            BufferedWriter bw =new BufferedWriter(fw);
            bw.write("David");
            bw.newLine();
            bw.write("Jack");
            bw.newLine();
            bw.write("你好 Java");
            bw.newLine();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        try{
            FileReader fr =new FileReader("src/Day_18/student.txt");
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("src/Day_18/copy.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            String data;
            while((data=br.readLine())!=null){
                bw.write(data);
                bw.newLine();
            }
            bw.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
