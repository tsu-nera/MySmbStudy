package smbstudy;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperator {
  public static void main (String args[]) throws IOException {

      // file system
      FileSystem fileSystem = FileSystems.getDefault ();

      // ファイルを表す Path オブジェクトを生成
      // Path fooFile = fileSystem.getPath ("C:\\app\\pleiades\\workspace\\smbstudy\\foo.txt");
      // Path fooFile = fileSystem.getPath ("foo.txt");
      // Path fooFile = fileSystem.getPath ("\\\\192.168.100.102\\共有\\foo.txt");
      Path fooFile = fileSystem.getPath ("\\\\192.168.100.102\\共有\\foo");
      System.out.println (fooFile);
      Path barFile = Paths.get ("C:\\app\\pleiades\\workspace\\smbstudy\\foo");
      System.out.println (barFile);

      // ファイルが存在する場合は削除します
      // Files.deleteIfExists (barFile);

      // copy
      Files.copy (fooFile, barFile);

      // 存在チェック
      if (!Files.exists (barFile))
        System.out.println ("file not exist");

      // 容量チェック
      if (Files.size (barFile) != Files.size (barFile) )
        System.out.println ("file size is not equal");

  }
}