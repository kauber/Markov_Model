import java.nio.file.*;
import java.io.*;

public class MarkovRunnerWithInterface {

    public static void main(String[] args) {
        runMarkov();
    }

    public static void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setSeed(seed);
        //System.out.println("running with " + markov);
        System.out.println(markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }

    public static void runMarkov() {

        Path p = Paths.get("D:/Java/MarkovModel/data/confucius.txt");
        StringBuilder contentBuilder = new StringBuilder();

        try(BufferedReader br = Files.newBufferedReader(p)) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String ourText = contentBuilder.toString();
        ourText = ourText.replace('\n', ' ');
        int size = 1000;
        int seed = 531;

        EfficientMarkovModel eMM = new EfficientMarkovModel(5);
        runModel(eMM,ourText,size,seed);

    /*MarkovZero mz = new MarkovZero();
    runModel(mz, st, size, 31);

    MarkovOne mOne = new MarkovOne();
    mOne.setRandom(11);
    runModel(mOne, st, size,55);

    MarkovModel mThree = new MarkovModel(3);
    runModel(mThree, st, size,88);

    MarkovFour mFour = new MarkovFour();
    runModel(mFour, st, size,88);*/
    }

    private static void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public void testHashMap(){
        EfficientMarkovModel efficientMarkov = new EfficientMarkovModel(2);
        //eMM.setRandom(42);
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        runModel(efficientMarkov, st, 50, 42);
        //markovModel =
        //efficientMarkovModel=
    }

//    public void compareMethods(){
//        FileResource fr = new FileResource();
//        String st = fr.asString();
//        st = st.replace('\n', ' ');
//        EfficientMarkovModel efficientMarkov = new EfficientMarkovModel(2);
//        MarkovModel mTwo = new MarkovModel(2);
//        long begin = System.nanoTime();
//        runModel(mTwo,st,1000,42);
//        long end = System.nanoTime();
//        System.out.println("time to run mTwo: " + (end-begin)/1000000000.0);
//        long begin2 = System.nanoTime();
//        runModel(efficientMarkov,st,1000,42);
//        long end2 = System.nanoTime();
//        System.out.println("time to run efficient markov: " + (end2-begin2)/1000000000.0);
//        //System.out.println("billy");
//    }
}
