package smbstudy;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DownloadEndToEndTest {

	// private static final String REMOTE_PATH = "\\\\192.168.100.102\\共有\\";
	private static final String DEST_PATH = "C:\\app\\pleiades\\workspace\\smbstudy\\dest\\";
	private static final String DATA_PATH = "C:\\app\\pleiades\\workspace\\smbstudy\\data\\";
	String source ,sourcePath;
	String target ,targetPath;
	String dirName;

	@Before
	public void setUp() {
		// source = REMOTE_PATH;
		sourcePath = DATA_PATH;
		targetPath = DEST_PATH;
	}

	@After
	public void tearDown() {
		FileUtils.delete(new File(target));
	}

	@Test
	public void testDownloadOneFolder() throws IOException, InterruptedException {
		dirName = "foo";
		source = sourcePath.concat(dirName);
		target = targetPath.concat(dirName);

		FileUtils.copy(source, target);

		Path sourceP = Paths.get (source);
		Path targetP = Paths.get (target);

		assertTrue(Files.exists(targetP));

		checkBasicFileAttribute(sourceP, targetP);
		checkDosFileAttribute(sourceP, targetP);
	}

	// http://docs.oracle.com/javase/7/docs/api/java/nio/file/attribute/BasicFileAttributeView.html
	private void checkBasicFileAttribute(Path sourceP, Path targetP) throws IOException {

		Map<String, Object> sourceAttr = Files.readAttributes(sourceP, "*");
		Map<String, Object> targetAttr = Files.readAttributes(targetP, "*");

		// [lastAccessTime, lastModifiedTime, size,
		// creationTime, isSymbolicLink, isRegularFile, fileKey, isOther, isDirectory]
		// System.out.println(sourceAttr.keySet().toString());

		for (String attribute: sourceAttr.keySet()) {
			if (attribute.equals("lastAccessTime")) continue;
			if (attribute.equals("lastModifiedTime")) continue;
			if (attribute.equals("creationTime")) continue;
			assertEquals(sourceAttr.get(attribute), targetAttr.get(attribute));
		}
	}

	// http://docs.oracle.com/javase/7/docs/api/java/nio/file/attribute/DosFileAttributeView.html
	private void checkDosFileAttribute(Path sourceP, Path targetP) throws IOException {

		Set<String> attrSet = new HashSet<String>(Arrays.asList(
				"dos:readonly",
				"dos:hidden",
				"dos:system"));
				//"dos:archive"));

		for (String attribute: attrSet) {
			boolean sourceAttr = (boolean) Files.getAttribute(sourceP, attribute);
			boolean targetAttr = (boolean) Files.getAttribute(targetP, attribute);

			assertEquals(sourceAttr, targetAttr);
		}
	}
}
