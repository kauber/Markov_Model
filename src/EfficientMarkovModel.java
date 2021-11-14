import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int nChars;
    private HashMap<String,ArrayList<String>> mapFollows;
    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        nChars = N;
        mapFollows = new HashMap<String,ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-nChars);
        String key = myText.substring(index, index+nChars);
        sb.append(key);
        for(int k=0; k < numChars -nChars; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key: " + key + follows);
            if (follows==null){ //if there is not follow for a particular key the array is null
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

    public void buildMap(){
        //we got it wrong as to how the buildmap is built
        for (int i=0;i<myText.length()-(nChars-1);i++){

            String current = myText.substring(i,i+nChars); // this gives us the first char, hence the key
            //System.out.println(current);
            String follow = "";
            if (i+nChars<myText.length()){
                follow = myText.substring(i+nChars,i+nChars+1);   //
            }
            if (mapFollows.containsKey(current)){
                mapFollows.get(current).add(follow);
            }
            else {
                ArrayList<String>followers = new ArrayList<String>();
                followers.add(follow);
                mapFollows.put(current,followers);
            }
        }

    }

    @Override
    public ArrayList<String> getFollows(String key){ // this method will look into the hashmap and retrieve a following char
        return mapFollows.get(key);
    }

    public void printHashMapInfo(){
        //System.out.println(mapFollows);
        System.out.println("Number of keys in the map: " + mapFollows.size());
        int maxSize = 0;
        for (String key : mapFollows.keySet()) {
            maxSize = Math.max(maxSize, mapFollows.get(key).size());
        }
        System.out.println("The maximum number of keys following a key is " + maxSize);
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : mapFollows.keySet()) {
            if(mapFollows.get(key).size() == maxSize){
                keys.add(key);
            }
        }
        System.out.println("Keys that have the largest ArrayList are: " + keys);
    }

    @Override
    public String toString(){
        return String.format("EfficientMarkovModel of order %d", nChars);
    }


}
