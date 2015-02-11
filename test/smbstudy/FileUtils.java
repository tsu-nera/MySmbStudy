package smbstudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

	// See:
	// http://www.k-tanaka.net/cmd/xcopy.php
	private static final String XCOPY_COMMAND = "xcopy";
	private static final String DIRECTORY_OPTION = "/i";
	private static final String RECURSIVE_OPTION = "/e";


	public static void copy(String source, String target) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder (XCOPY_COMMAND, RECURSIVE_OPTION, DIRECTORY_OPTION, source, target);

        // 標準出力と標準エラーをマージする(Defualt false)
		 pb.redirectErrorStream(true);

		// プロセス開始
		Process process = pb.start();

		// プロセス完了待ち合わせ
		process.waitFor();
	}

	public static void getLastMessage(Process ps) throws IOException {
		InputStream is = ps.getInputStream();
		printInputStream(is);
	}

	private static void printInputStream(InputStream is) throws IOException {
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

	/**
	 * フォルダ・ファイル再帰的に削除
	 * @param f
	 */
	public static void delete(File f){
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
