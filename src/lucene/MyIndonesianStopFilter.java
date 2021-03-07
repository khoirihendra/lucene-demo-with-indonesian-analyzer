package lucene;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyIndonesianStopFilter extends MyAnalyzer{
	
	@Override
	protected TokenStreamComponents createComponents(String paramString) {
		final Tokenizer source = new StandardTokenizer();
		TokenStream result = new LowerCaseFilter(source);
		result = new StopFilter(result, stopwords);
	    return new TokenStreamComponents(source, result);
	}

}
