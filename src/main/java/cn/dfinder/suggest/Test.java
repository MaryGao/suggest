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
	
	public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination {
		Suggestor sug = new Suggestor(FSDirectory.open(new File("index")));
		
 // 		sug.updateSuggestIndex(new BufferedReader(new InputStreamReader(new FileInputStream(new File("indicator.dic")))), new IndexWriterConfig(Version.LUCENE_48, null), true);
		
		System.out.println(Arrays.toString(sug.suggestSimilar("教", 10, 0.5f)));
		
		sug.close();
		
	}


}
