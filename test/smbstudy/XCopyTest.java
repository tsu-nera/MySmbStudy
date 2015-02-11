package smbstudy;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class XCopyTest {

	// http://www.k-tanaka.net/cmd/xcopy.php
	static final String XCOPY_Command = "xcopy";

	@Before
	public void setUp() {
	}

	@Test
	public void testDownloadOneFolder() throws IOException, InterruptedException {
		String source = "\\\\192.168.100.102\\共有\\foo";
		String target = "C:\\app\\pleiades\\workspace\\smbstudy\\data\\foo";

		// Process Builder生成
		// xcopy /e /i source target ディレクトリを再帰的にコピー
		ProcessBuilder pb = new ProcessBuilder (XCOPY_Command, "/e", "/i",source, target);

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
