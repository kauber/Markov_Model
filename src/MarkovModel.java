import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int nChars;

    public MarkovModel(int N) {
        myRandom = new Random();
        nChars = N;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-nChars);
        String key = myText.substring(index, index+nChars);
        sb.append(key);
        for(int k=0; k < numChars -nChars; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key: " + key + follows);
            if (follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);

            sb.append(next);
            key=key.substring(1) + next;
            //key= next;
        }
        return sb.toString();
    }

    @Override
    public String toString(){
        return String.format("MarkovModel of order %d", nChars);
    }
}
