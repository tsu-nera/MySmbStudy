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

	// private static final String REMOTE_PATH = "\\\\192.168.100.102\\共有\\";
	private static final String DEST_PATH = "C:\\app\\pleiades\\workspace\\smbstudy\\dest\\";
	private static final String DATA_PATH = "C:\\app\\pleiades\\workspace\\smbstudy\\data\\";
	String sourcePath;
	String targetPath;

	@Before
	public void setUp() {
		// source = REMOTE_PATH;
		sourcePath = DATA_PATH;
		targetPath = DEST_PATH;
	}

	@Test
	public void testDownloadOneFolder() throws IOException, InterruptedException {
		String dirName = "foo";
		String source = sourcePath.concat(dirName);
		String target = targetPath.concat(dirName);

		FileUtils.copy(source, target);

		Path targetP = Paths.get (target);
		assertTrue(Files.exists(targetP));

		FileUtils.delete(new File(target));
	}
}
