import java.io.File;
import java.io.IOException;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomTree;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.RemoveUseless;

/**
 * This class is used to explain the Linear Regression with Java.
 * 
 * @author Gowtham Girithar Srirangasamy
 *
 */
public class Weather_pridiction {

	/** file names are defined */
	public static final String TRAINING_DATA_SET_FILENAME = "/Users/hvanpariya/ML/car_data.arff";
	public static final String TRAINING_DATA_SET_FILENAME_NUM = "/Users/hvanpariya/ML/car_data_1.arff";


	/**
	 * This method is to load the data set.
	 * 
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
		}
		
		DataSource source_1 = new DataSource(TRAINING_DATA_SET_FILENAME_NUM);
		Instances training_data_1 = source_1.getDataSet();
		if (training_data_1.classIndex() == -1) // by default classIndex is -1
		{
			training_data_1.setClassIndex(training_data_1.numAttributes() - 1); // We need to setup classIndex to Number of
		}

		J48 j48 = new J48(); // new instance of tree
		j48.setUnpruned(false);
		j48.buildClassifier(training_data);
		String[] options = weka.core.Utils.splitOptions("-C 0.25 -M 2");
		j48.setOptions(options);
		j48.buildClassifier(training_data);
		
		
//		RemoveUseless removeUseless = new RemoveUseless();
//		removeUseless.setInputFormat(training_data_1);
//		String[] options_1 = weka.core.Utils.splitOptions("-M 99");
//		removeUseless.setOptions(options_1);
//		removeUseless.setInputFormat(training_data_1);
//		training_data_1 = Filter.useFilter(training_data_1, removeUseless);
		
		RandomTree randomTree = new RandomTree();
		randomTree.buildClassifier(training_data_1);
		String[] options_randomTree = weka.core.Utils.splitOptions("-K 0 -M 1.0 -V 0.001 -S 1");
		randomTree.setOptions(options_randomTree);
		randomTree.buildClassifier(training_data_1);

		// Test
		Instance instance = new DenseInstance(8);
		instance.setDataset(training_data);
		instance.setValue(training_data.attribute(0), 161075);
		instance.setValue(training_data.attribute(1), 1);
		instance.setValue(training_data.attribute(2), "petrol");
		instance.setValue(training_data.attribute(3), "Individual");
		instance.setValue(training_data.attribute(4), "Manual");
		instance.setValue(training_data.attribute(5), 2011);
		instance.setValue(training_data.attribute(6), "Bangalore");
		instance.setValue(training_data.attribute(7), "VXI");

		int result = (int) j48.classifyInstance(instance);
		String results = training_data.attribute(8).value(result);
		System.out.println(results);
		
		
		// Test
		Instance instance_1 = new DenseInstance(8);
		instance_1.setDataset(training_data_1);
		instance_1.setValue(training_data_1.attribute(0), 161075);
		instance_1.setValue(training_data_1.attribute(1), 1);
		instance_1.setValue(training_data_1.attribute(2), "petrol");
		instance_1.setValue(training_data_1.attribute(3), "Individual");
		instance_1.setValue(training_data_1.attribute(4), "Manual");
		instance_1.setValue(training_data_1.attribute(5), 2011);
		instance_1.setValue(training_data_1.attribute(6), "Bangalore");
		instance_1.setValue(training_data_1.attribute(7), "VXI");
	
		double result_1 = randomTree.classifyInstance(instance_1);
//		String results_1 = training_data_1.attribute(8).value(result_1);
		System.out.println(">>"+result_1);
		System.out.println(randomTree);
		
		
		
		
		
//		System.out.println("Test with: " + training_data + "  \n"
//				+ "Result: " + results);

	}

	public static void main(String[] args) throws Exception {
		Weather_pridiction linearRegressionDemo = new Weather_pridiction();
		linearRegressionDemo.process();
	}
}