package lucene;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.id.IndonesianStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyIndonesianStemFilter extends MyIndonesianStopFilter{
	
	private boolean stemDerivational = true;

	public MyIndonesianStemFilter() {}
	
	public MyIndonesianStemFilter(boolean stemDerivational) {
		this.stemDerivational  = stemDerivational;
	}
	
	@Override
	protected TokenStreamComponents createComponents(String paramString) {
		final Tokenizer source = new StandardTokenizer();
		TokenStream result = new LowerCaseFilter(source);
		result = new StopFilter(result, stopwords);
		result = new IndonesianStemFilter(result, stemDerivational);
		return new TokenStreamComponents(source, result);
	}
}
