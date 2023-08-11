
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/**
 * @Author：liukai
 * @Date：2023/8/10 10:41
 */
public class HdfsUploader {
    public static void main(String[] args) {
        String localFilePath = "E:\\work\\IdeaProjects\\TestJavaWeb\\TestHDFS\\src\\main\\java\\test.txt";
        String hdfsFilePath = "/tmp/tmp_lk";

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.18.13.18:8020"); // 设置HDFS的地址

        try {
            FileSystem fs = FileSystem.get(conf);
            Path localPath = new Path(localFilePath);
            Path hdfsPath = new Path(hdfsFilePath);

            fs.copyFromLocalFile(localPath, hdfsPath);
            System.out.println("文件上传成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
