/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class IndexFiles {
  
	private IndexFiles() {}

	public static void main(String[] args) throws IOException {
    
		String text = "Pengetahuan itu berasal dari kata tahu bukan tempe, apakah begitu?";
		System.out.println("ORIGINAL TEXT: ");
		System.out.println(text + System.getProperty("line.separator"));
     
		Analyzer standardTokenizer = new MyStandardTokenizer();
		Analyzer addLowerCaseFilter = new MyLowerCaseFilter();
		Analyzer addStopFilter = new MyIndonesianStopFilter();
		Analyzer addStemmer = new MyIndonesianStemFilter();
		Analyzer addStemmerWithStemDerivationalFalse = new MyIndonesianStemFilter(false);
		
//		CharArraySet a = new CharArraySet(100, true);
//		char[] texts = {'a','s','a','l'};
//		a.add(texts);
//		System.out.println(a);
//		Analyzer addStemmerWithKeywordExclusion = new MyIndonesianStemFilterWithKeywordExclusion(a);
		
		System.out.println("1. TOKENIZER (ADD STANDARD TOKENIZER) ");
		debugStream(standardTokenizer, text);
		
		System.out.println("2. TOKEN FILTERING");
		System.out.println("a. ADD LOWER CASE FILTER: ");
		debugStream(addLowerCaseFilter, text);
		
		System.out.println("b. ADD INDONESIAN STOP WORD FILTER: ");
		debugStream(addStopFilter, text);

		System.out.println("c. ADD INDONESIAN STEMMER");
		System.out.println("(stemmer default)");
		debugStream(addStemmer, text);
		System.out.println("(stemmer default with stem derivational = false)");
		debugStream(addStemmerWithStemDerivationalFalse, text);
		//System.out.println("(stemmer default with keyword exclusion)");
		//debugStream(addStemmerWithKeywordExclusion, text);
		
	}
	
	private static void debugStream(Analyzer analyzer, String text) throws IOException {
		TokenStream stream = analyzer.tokenStream("field", text);
		
		CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);
		//KeywordAttribute keywordAtt = stream.addAttribute(KeywordAttribute.class);
  
		try {
			stream.reset();
			while (stream.incrementToken()) {
				System.out.print(" - " + termAtt.toString());
			}
			
			stream.end();
		} finally {
			stream.close();
		}

		analyzer.close();
		System.out.println("" + System.getProperty("line.separator"));
	}
}
