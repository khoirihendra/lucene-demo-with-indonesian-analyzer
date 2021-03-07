package lucene;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyStandardTokenizer extends MyAnalyzer{
	
	@Override
	protected TokenStreamComponents createComponents(String paramString) {
		final Tokenizer source = new StandardTokenizer();
	    return new TokenStreamComponents(source);
	}

}
