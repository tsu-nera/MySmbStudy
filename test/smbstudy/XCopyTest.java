package smbstudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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



		// 結果の出力
		// InputStream is = process.getInputStream();
		// printInputStream(is);

		delete(new File(target));
	}

	private void printInputStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			for (;;) {
				String line = br.readLine();
				if (line == null) break;
				System.out.println(line);
			}
		} finally {
			br.close();
		}
	}

	private void delete(File f){
		if( f.exists()==false ){
			return ;
		}

		if(f.isFile()){
			f.delete();
		}

		if(f.isDirectory()){
			File[] files=f.listFiles();
			for(int i=0; i<files.length; i++){
				delete( files[i] );
			}
			f.delete();
		}
	}
}
