import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setSeed(int seed){
        myRandom = new Random(seed);
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows (String key){
        ArrayList<String> follows = new ArrayList<String>();
        int index = 0;
        int pos = 0;
        while (true){ //(index!=-1)
            index = myText.indexOf(key, pos);
            if (index + key.length()==myText.length() | index==-1){
                break;
            }
            //String follower = Character.toString(myText.charAt(index + key.length()));
            String follower = myText.substring(index + key.length(),index+key.length()+1);
            follows.add(follower);
            pos = index + 1;
        }
        return follows;
    }

}
