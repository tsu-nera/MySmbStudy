package smbstudy;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class DownloadEndToEndTest {

	// See:
	// http://www.k-tanaka.net/cmd/xcopy.php
	private static final String XCOPY_COMMAND = "xcopy";
	private static final String DIRECTORY_OPTION = "/i";
	private static final String RECURSIVE_OPTION = "/e";

	// private static final String REMOTE_PATH = "\\\\192.168.100.102\\共有\\";
	private static final String DEST_PATH = "C:\\app\\pleiades\\workspace\\smbstudy\\dest\\";
	private static final String DATA_PATH = "C:\\app\\pleiades\\workspace\\smbstudy\\data\\";
	String source, sourcePath;
	String target, targetPath;
	String dirName;

	@Before
	public void setUp() {
		// source = REMOTE_PATH;
		sourcePath = DATA_PATH;
		targetPath = DEST_PATH;
	}

	@Test
	public void testDownloadOneFolder() throws IOException, InterruptedException {
		dirName = "foo";
		source = sourcePath.concat(dirName);
		target = targetPath.concat(dirName);

		ProcessBuilder pb = new ProcessBuilder (XCOPY_COMMAND, RECURSIVE_OPTION, DIRECTORY_OPTION, source, target);

        // 標準出力と標準エラーをマージする(Defualt false)
		 pb.redirectErrorStream(true);

		// プロセス開始
		Process process = pb.start();

		// プロセス完了待ち合わせ
		process.waitFor();

		Path targetP = Paths.get (target);
		assertTrue(Files.exists(targetP));

		FileUtils.delete(new File(target));
	}
}
