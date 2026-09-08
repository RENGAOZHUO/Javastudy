package Day_13;
//file.delete();删除文件
//file.getAbsolutePath();获取文件路径
//FileWriter与File不同。File需要使用creatNewFile来创建文件，但是FileWriter会自己创建文件。
//FileWriter fw = new FileWriter("test.txt");之后使用fw.write("填入的内容");来填入内容。
//在使用文件结束后需要使用fw.close()来关闭文件，因为文件会占用内存
//不能直接用write方法来直接向文件添加内容，那样做会覆盖掉原文件内容
//可以以FileWriter fw = new FileWriter("test.txt", true);这种传递第二个参数为true的方法来追加输入；
//FileWriter也需要try-catch方法来使用。故要在try块中创建FileWrite。
//读方法：FileReader fr = new FileReader("test.txt");
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;

public class Main {
    public static void main(){
        /*File file=new File("test.txt");
        try{file.createNewFile();
            System.out.println("文件创建成功");
            System.out.println("文件存在："+file.exists());
        }
        catch (IOException e){
            System.out.println("创建文件失败");
        }*/
        try{
            FileWriter student=new FileWriter("src/Day_13/student.txt",true);
            student.write("姓名：张三\n");
            student.write("年龄:20\n");
            student.write("成绩：90\n");
            student.close();

        }catch(IOException e){
            System.out.println("创建写入文件失败！");
        }
        try{
            FileReader st=new FileReader("src/Day_13/student.txt");
            int ch;
            while((ch=st.read())!=-1){
                System.out.print((char)ch);
            }
            st.close();
        }catch(IOException e){
            System.out.println("读取失败！");
        }


    }
}
