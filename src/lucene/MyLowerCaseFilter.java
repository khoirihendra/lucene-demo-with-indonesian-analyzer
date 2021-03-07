package lucene;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyLowerCaseFilter extends MyAnalyzer{
	
	@Override
	protected TokenStreamComponents createComponents(String paramString) {
		final Tokenizer source = new StandardTokenizer();
		TokenStream result = new LowerCaseFilter(source);
	    return new TokenStreamComponents(source, result);
	}
}
