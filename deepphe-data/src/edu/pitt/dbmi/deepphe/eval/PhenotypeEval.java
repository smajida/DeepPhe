package edu.pitt.dbmi.deepphe.eval;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class PhenotypeEval {
	public static boolean STRICT_VALUE_CALCULATION = false;
	public static boolean PRINT_RECORD_LEVEL_STATS = false;
	public static void main(String[] args) throws IOException {
		// compare two files
		if(args.length >= 2){
			File gold = null, candidate = null;
			for(String s: args){
				if("-strict".equals(s)){
					STRICT_VALUE_CALCULATION = true;
				}else if("-print".equals(s)){
					PRINT_RECORD_LEVEL_STATS = true;
				}else if(gold  == null){
					gold = new File(s);
				}else if(candidate == null){
					candidate = new File(s);
				}
			}
			PhenotypeEval pe = new PhenotypeEval();
			pe.evaluate(gold,candidate);
		}else{
			System.err.println("Usage: java PhenotypeEval [-print|-strict] <gold .bsv file> <candidate .bsv file>");
		}
	}
	public static enum ConfusionLabel {
		TP,FP,FN,TN
	}
	public static class ConfusionMatrix {
		public double TP,FP,FN,TN;
		public void append(ConfusionMatrix c){
			TP += c.TP;
			FP += c.FP;
			FN += c.FN;
			TN += c.TN;
		}
		
		public double getPrecision(){
			return TP / (TP+ FP);
		}
		public double getRecall(){
			return  TP / (TP+ FN);
		}
		public double getFscore(){
			double precision = getPrecision();
			double recall = getRecall();
			return (2*precision*recall)/(precision + recall);
		}
		public double getAccuracy(){
			return (TP+TN) / (TP+TN+FP+FN);
		}
		
		public static void printHeader(PrintStream out){
			out.println(String.format("%1$-"+Record.MAX_ATTRIBUTE_SIZE+"s","Label")+"\tTP\tFP\tFN\tTN\tPrecis\tRecall\tAccur\tF1-Score");
		}
		public void print(PrintStream out,String label){
			out.println(String.format("%1$-"+Record.MAX_ATTRIBUTE_SIZE+"s",label)+"\t"+
					String.format("%.4f",TP)+"\t"+String.format("%.4f",FP)+"\t"+
					String.format("%.4f",FN)+"\t"+String.format("%.4f",TN)+"\t"+
					String.format("%.4f", getPrecision())+"\t"+
					String.format("%.4f", getRecall())+"\t"+
					String.format("%.4f",getAccuracy())+"\t"+
					String.format("%.4f",getFscore()));
		}
	}
	
	/**
	 * record data 
	 * @author tseytlin
	 *
	 */
	
	private static class Record {
		public static int MAX_ATTRIBUTE_SIZE = 10;
		private static final String I = "\\|";
		private static final String FS = ";";
		private static List<String> headers,valueHeaders,ignoredHeaders;
		private Map<String,String> content;
		private String id;
		private ConfusionLabel confusionLabel;
		private Record pairedRecord;
		
		public static Record loadRecord(String l) {
			Record r = new Record();
			int i = 0;
			for(String s: l.split(I)){
				String key = headers.get(i++);
				String val = s.trim();
				if(key.length() > MAX_ATTRIBUTE_SIZE)
					MAX_ATTRIBUTE_SIZE = key.length();
				r.addField(key,val);
			}
			return r;
		}

		private void addField(String key, String val) {
			if(content == null)
				content = new LinkedHashMap<String, String>();
			content.put(key,val);
			
		}

		public String getId() {
			if(id == null){
				id = createId();
			}
			return id;
		}
		public static boolean hasHeaders(){
			return headers != null;
		}

		public static void loadHeaders(String l) {
			headers = new ArrayList<String>();
			for(String s: l.split(I)){
				headers.add(s.trim());
			}
		}

		public String createId() {
			StringBuffer b = new StringBuffer();
			for(String hd: headers){
				if(isIdField(hd)){
					for(String v: getValues(hd)){
						b.append(v+" ");
					}
				}
			}
			return b.toString().trim();
		}

		public static boolean isIdField(String hd) {
			return hd.startsWith("*");
		}
		public static boolean isIgnoreField(String hd) {
			return hd.startsWith("-");
		}

		public ConfusionLabel getConfusion() {
			return confusionLabel;
		}

		public void setConfusion(ConfusionLabel confusionLabel) {
			this.confusionLabel = confusionLabel;
		}

		public Record getPairedRecord() {
			return pairedRecord;
		}

		public void setPairedRecord(Record pairedRecord) {
			this.pairedRecord = pairedRecord;
		}

		public void print(PrintStream out) {
			out.println(getConfusion()+": "+getId());
			for(String hd: getValueHeaders()){
				List<String> gold = getValues(hd);
				List<String> pred = Collections.EMPTY_LIST;
				switch (getConfusion()) {
				case TP:
					pred = getPairedRecord().getValues(hd);
					break;
				case FP:
					gold = Collections.EMPTY_LIST;
					pred = getValues(hd);
					break;
				default:
					break;
				}
				out.println("\t"+String.format("%1$-"+Record.MAX_ATTRIBUTE_SIZE+"s",hd)+"\tscore: "+
						String.format("%.4f",compare(gold,pred))+"\t gold: "+PhenotypeEval.toString(gold)+"\t pred: "+PhenotypeEval.toString(pred));
			}
			out.println("\n\t"+String.format("%1$-"+Record.MAX_ATTRIBUTE_SIZE+"s","Weighted Score:")+"\t"+String.format("%.4f",getWeightedScore())+"\n");
			out.println("\t-----------");
			for(String hd: getIgnoredHeaders()){
				List<String> gold = getValues(hd);
				List<String> pred = Collections.EMPTY_LIST;
				if(!gold.isEmpty()){
					if(getPairedRecord() != null )
						pred = getPairedRecord().getValues(hd);
					out.println("\t"+String.format("%1$-"+Record.MAX_ATTRIBUTE_SIZE+"s",hd)+"\t gold: "+PhenotypeEval.toString(gold)+"\t pred: "+PhenotypeEval.toString(pred));
				}
			}
			out.println("\t-----------\n");
			
		}

		public double getWeightedScore() {
			if(pairedRecord != null){
				double score = 0;
				int total =  getValueHeaders().size();
				for(String hd: getValueHeaders()){
					score += compare(getValues(hd),pairedRecord.getValues(hd));
				}
				return score/ total;
			}
			return 1.0;
		}
		
		
		public Map<String,ConfusionMatrix> getAttributeConfusionMatricies() {
			Map<String,ConfusionMatrix> map = new LinkedHashMap<String, PhenotypeEval.ConfusionMatrix>();
			if(pairedRecord != null){
				for(String hd: getValueHeaders()){
					ConfusionMatrix c = new ConfusionMatrix();
					List<String> val1 = getValues(hd);
					List<String> val2 = pairedRecord.getValues(hd);
					for(String v: val1){
						if(val2.contains(v)){
							c.TP++;
						}else{
							c.FN++;
						}
					}
					for(String v: val2){
						if(!val1.contains(v)){
							c.FP++;
						}
					}
					map.put(hd,c);
				}
			}
			return map;
		}
		
		
		private double compare(List<String> val1, List<String> val2) {
			// if strict calculation, do 1 or 0 match
			if(STRICT_VALUE_CALCULATION)
				return val1.size() == val2.size() && val1.containsAll(val2)?1:0;
			
			double score = 0;
			for(String v: val1){
				if(val2.contains(v)){
					score ++;
				}
			}
			double t = val1.size() + (val2.size() - score);
			// if all values are empty, then we have a match :)
			if(t == 0)
				return 1.0;
			return score / t;
		}

		public List<String> getValues(String hd) {
			List<String> vals = new ArrayList<String>();
			String str = content.get(hd);
			if(str != null && str.trim().length() > 0){
				if(str.contains(FS)){
					for(String s: str.split(FS)){
						vals.add(s.trim());
					}
					Collections.sort(vals);
				}else{
					vals.add(str);
				}
			}
			return vals;
		}

		public List<String> getValueHeaders(){
			if(valueHeaders == null){
				valueHeaders = new ArrayList<String>();
				for(String h : headers){
					if(!isIgnoreField(h) && !isIdField(h)){
						valueHeaders.add(h);
					}
				}
			}
			return valueHeaders;
		}
		public List<String> getIgnoredHeaders(){
			if(ignoredHeaders == null){
				ignoredHeaders = new ArrayList<String>();
				for(String h : headers){
					if(isIgnoreField(h)){
						ignoredHeaders.add(h);
					}
				}
			}
			return ignoredHeaders;
		}
		
	}


	private Map<String, Record> loadAnnotations(File file) throws IOException {
		Map<String,Record> map = new LinkedHashMap<String, PhenotypeEval.Record>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		boolean headerLine = true;
		for(String l=reader.readLine();l != null; l = reader.readLine()){
			if(headerLine){
				Record.loadHeaders(l);
				headerLine = false;
			}else{
				Record record = Record.loadRecord(l);
				map.put(record.getId(),record);
			}
		}
		reader.close();
		return map;
	}

	
	private void append(Map<String,ConfusionMatrix> first, Map<String,ConfusionMatrix> second){
		for(String hd: second.keySet()){
			ConfusionMatrix c = first.get(hd);
			if(c == null){
				c = new ConfusionMatrix();
				first.put(hd,c);
			}
			c.append(second.get(hd));
		}
		
	}
	
	private static String toString(Collection c){
		if(c == null)
			return "";
		String s = c.toString();
		if(s.startsWith("[") && s.endsWith("]"))
			return s.substring(1,s.length()-1);
		return s;
	}
	
	
	/**
	 * evaluate phenotype of two BSV files
	 * @param file1
	 * @param file2
	 * @throws IOException 
	 */
	
	private void evaluate(File file1, File file2) throws IOException {
		Map<String,Record> goldAnnotations = loadAnnotations(file1);
		Map<String,Record> candidateAnnotations = loadAnnotations(file2);
		System.out.println("Gold File:\t"+file1.getAbsolutePath());
		System.out.println("Candidate File:\t"+file2.getAbsolutePath());
		System.out.println("");
		ConfusionMatrix totalConfusion = new ConfusionMatrix();
		Map<String,ConfusionMatrix> attributeConfusions = new LinkedHashMap<String, PhenotypeEval.ConfusionMatrix>();
		
		// iterate over gold standard
		List<Record> records = new ArrayList<PhenotypeEval.Record>();
		for(String id: goldAnnotations.keySet()){
			Record gold = goldAnnotations.get(id);
			Record candidate = candidateAnnotations.get(id);
			// if we have a candidate with the same (like the same span), we have a TP
			if(candidate != null){
				gold.setConfusion(ConfusionLabel.TP);
				gold.setPairedRecord(candidate);
				totalConfusion.TP += gold.getWeightedScore();
				
				// calculate stats per attribute
				append(attributeConfusions,gold.getAttributeConfusionMatricies());
			
			// if we don't have a candidate, then we have a FN	
			}else{
				gold.setConfusion(ConfusionLabel.FN);
				totalConfusion.FN ++;
			}
			records.add(gold);	
		}
		// now lets look at FPs
		for(String id: candidateAnnotations.keySet()){
			if(!goldAnnotations.containsKey(id)){
				Record r = candidateAnnotations.get(id);
				r.setConfusion(ConfusionLabel.FP);
				records.add(r);
				
				totalConfusion.FP ++;
			}
		}
		
		// display individual counts
		if(PRINT_RECORD_LEVEL_STATS){
			for(Record r: records){
				r.print(System.out);
			}
		}
			
		
		System.out.println("\n\n-----------------------------------------------------------------------------------------");
		String label = "Container";
		if(file1.getName().toLowerCase().contains("cancer"))
			label = "Cancer";
		else if(file1.getName().toLowerCase().contains("tumor"))
			label = "Tumor";
		 
		
		ConfusionMatrix.printHeader(System.out);
		totalConfusion.print(System.out,label);
		for(String attribute: attributeConfusions.keySet()){
			attributeConfusions.get(attribute).print(System.out,attribute);
	
		}
	
	}

}
