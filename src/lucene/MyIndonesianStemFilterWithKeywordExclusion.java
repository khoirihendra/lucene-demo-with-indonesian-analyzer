package lucene;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.id.IndonesianStemFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyIndonesianStemFilterWithKeywordExclusion extends MyAnalyzer {
	private CharArraySet stemExclusionSet;
	public MyIndonesianStemFilterWithKeywordExclusion() {}
	
	public MyIndonesianStemFilterWithKeywordExclusion(CharArraySet stemExclusionSet) {
		super(stemExclusionSet);
		this.stemExclusionSet = stemExclusionSet;
	}
	
	@Override
	protected TokenStreamComponents createComponents(String paramString) {
		final Tokenizer source = new StandardTokenizer();
		TokenStream result = new LowerCaseFilter(source);
		result = new StopFilter(result, stopwords);
		result = new SetKeywordMarkerFilter(result, stemExclusionSet);
		result = new IndonesianStemFilter(result);
		return new TokenStreamComponents(source, result);
	}
}
