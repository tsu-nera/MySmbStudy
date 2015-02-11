package smbstudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

	/**
	 * 結果の出力
	 * @param ps
	 * @throws IOException
	 */
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
