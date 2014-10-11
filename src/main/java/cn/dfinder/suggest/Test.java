package cn.dfinder.suggest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Test {
	
	public static void main(String[] args) throws IOException,
			BadHanyuPinyinOutputFormatCombination {
		// 新建索引目录..../suggest/index
		Suggestor sug = new Suggestor(FSDirectory.open(new File("index")));
		// 新建索引文件
		//sug.updateSuggestIndex(new BufferedReader(new InputStreamReader(new FileInputStream(new File("indicator.dic")))), new IndexWriterConfig(Version.LUCENE_48, null), true);

		//提高精度可以提高正确率，长词（7个字以上）下0.5f左右出的结果比较合适
		// 中文测试
		System.out.println("****中文测试以及拼音首字母混合补全****");
		System.out.println(Arrays.toString(sug.suggestSimilar("教y", 10, 0.1f)));
		System.out.println(Arrays.toString(sug.suggestSimilar("教yu", 10, 0.1f)));

		// 首字母
		System.out.println("****拼音首字母补全****");
		System.out.println(Arrays.toString(sug.suggestSimilar("hl", 10, 0.1f)));
		// 拼音
		System.out.println("****拼音补全****");
		System.out.println(Arrays.toString(sug.suggestSimilar("senlin", 10, 0.1f)));

		sug.close();
	}


}
