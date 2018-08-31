import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.pmml.jaxbbindings.DecisionTree;

/**
 * This class is used to explain the Linear Regression with Java.
 * 
 * @author Gowtham Girithar Srirangasamy
 *
 */
public class LinearRegressionDemo {
	
	/** file names are defined*/
	public static final String TRAINING_DATA_SET_FILENAME="/Users/hvanpariya/ML/weka-3-8-2/data/weather.nominal.arff";
	public static final String TESTING_DATA_SET_FILENAME="/Users/hvanpariya/ML/weka-3-8-2/data/weather.nominal.arff";
	public static final String PREDICTION_DATA_SET_FILENAME="/Users/hvanpariya/ML/weka-3-8-2/data/weather.nominal.arff";

	/**
	 * This method is to load the data set.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Instances getDataSet(String fileName) throws IOException {
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		int classIdx = 1;
		/** the arffloader to load the arff file */
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File(fileName));
		/** load the traing data */
//		loader.setSource(LinearRegressionDemo.class.getResourceAsStream("/" + fileName));
		/**
		 * we can also set the file like loader3.setFile(new
		 * File("test-confused.arff"));
		 */
		Instances dataSet = loader.getDataSet();
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		return dataSet;
	}

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
	public static void process() throws Exception {

		DataSource source = new DataSource(TRAINING_DATA_SET_FILENAME);
		Instances training_data = source.getDataSet();
		if (training_data.classIndex() == -1) // by default classIndex is -1
		{
			training_data.setClassIndex(training_data.numAttributes() - 1); // We need to setup classIndex to Number of
																			// target attribute
		} // end of (training_data.classIndex()==-1)
		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		
		BufferedReader reader = new BufferedReader(
				 new FileReader(TESTING_DATA_SET_FILENAME));
				Instances data = new Instances(reader);
				reader.close();
				// setting class attribute
				
		DecisionTree decisionTree = new DecisionTree();
		J48 j48 = new J48(); // new instance of tree
		j48.setUnpruned(false);
		j48.buildClassifier(training_data); 
		String[] options = weka.core.Utils.splitOptions("-C 0.25 -M 2"); 
		j48.setOptions(options);
		j48.buildClassifier(training_data); 
		
		Evaluation eval = new Evaluation(training_data); 
		eval.crossValidateModel(j48, training_data, 10, new Random(1));
		
		ThresholdCurve tc = new ThresholdCurve();
		Instances curve = tc.getCurve(eval.predictions());
		
		System.out.println(j48);
//		curve.
//		Instances result = tc.getCurve(eval.predictions(), X);
//		J48 j48 = new  J48();
//		j48.classifyInstance(trainingDataSet.get(0));
		/** Classifier here is Linear Regression */
//		Classifier classifier = new J48();
//		/** */
//		classifier.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
//		Evaluation eval = new Evaluation(trainingDataSet);
//		eval.evaluateModel(classifier, testingDataSet);
//		/** Print the algorithm summary */
//		System.out.println("** Linear Regression Evaluation with Datasets **");
//		System.out.println(eval.toSummaryString());
//		System.out.println("Correlation coefficient" +eval.correlationCoefficient());
//		System.out.print(" the expression for the input data as per alogorithm is ");
//		System.out.println(classifier);
//
//		Instance predicationDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME).lastInstance();
//		double value = classifier.classifyInstance(predicationDataSet);
//		/** Prediction Output */
//		System.out.println(value);
	}
	
	 public static void main(String[] args) throws Exception {
		 LinearRegressionDemo linearRegressionDemo = new LinearRegressionDemo();
		 linearRegressionDemo.process();
	}
}