package smbstudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class XCopyOperator {
	public static void main (String args[]) throws IOException, InterruptedException {
//		String source = "foo";
//		String target = "bar";

		String source = "\\\\192.168.100.102\\共有\\foo";
		String target = "C:\\app\\pleiades\\workspace\\smbstudy\\foo";

		// Process Builder生成
		// xcopy /e /i source target ディレクトリを再帰的にコピー
		ProcessBuilder pb = new ProcessBuilder ("xcopy", "/e", "/i", source, target);

        // 標準出力と標準エラーをマージする(Defualt false)
		pb.redirectErrorStream(true);

		// プロセス開始
		Process process = pb.start();

		// プロセス完了待ち合わせ
		process.waitFor();

		// 結果の出力
		InputStream is = process.getInputStream();
		printInputStream(is);
    }

	public static void printInputStream(InputStream is) throws IOException {
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
}
