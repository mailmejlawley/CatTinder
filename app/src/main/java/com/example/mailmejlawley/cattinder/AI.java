package com.example.mailmejlawley.cattinder;

import java.util.ArrayList;
import java.util.Random;

class AI
{
    private  ArrayList<ArrayList<Feature>> featureArray;
    private  ArrayList<Integer> totalLikes;
    private  ArrayList<Feature> cat;
    private  Random rand;

    public AI(ArrayList<String> list)
    {
        featureArray = new ArrayList<>();
        totalLikes = new ArrayList<>();
        rand = new Random();
        String categories = "";

        for(String line : list)
        {
            Feature feature = new Feature();

            String elementString = line.substring(0, line.indexOf('\t'));
            int element = categories.indexOf(elementString);

            line = line.substring(line.indexOf('\t') + 1);

            feature.setX(Integer.parseInt(line.substring(0, line.indexOf('\t'))));
            line = line.substring(line.indexOf('\t') + 1);

            feature.setY(Integer.parseInt(line.substring(0, line.indexOf('\t'))));
            line = line.substring(line.indexOf('\t') + 1);

            feature.setLikes(Integer.parseInt(line.substring(0, line.indexOf('\t'))));
            line = line.substring(line.indexOf('\t') + 1);

            feature.setFileName(line);

            if(element == -1)
            {
                categories += elementString;
                ArrayList<Feature> newList = new ArrayList<>();
                newList.add(feature);
                featureArray.add(newList);
                totalLikes.add(feature.getLikes());
            }
            else
            {
                featureArray.get(element).add(feature);
                totalLikes.set(element, totalLikes.get(element) + feature.getLikes());
            }
        }
    }

    public void react(boolean like)
    {
        for(int i = 0; i < cat.size(); i++)
        {
            int element = featureArray.get(i).indexOf(cat.get(i));
            if(like)
            {
                featureArray.get(i).get(element).like();
                totalLikes.set(i, totalLikes.get(i) + 1);
            }
            else if(featureArray.get(i).get(element).dislike())
                totalLikes.set(i, totalLikes.get(i) - 1);
        }
    }

    public ArrayList<Feature> generateCat()
    {

        cat = new ArrayList<>();

        for(int i = 0; i < totalLikes.size(); i++)
        {
            int r = rand.nextInt(totalLikes.get(i));

            for(int j = 0; j < featureArray.get(i).size(); j++)
            {
                if(r < featureArray.get(i).get(j).getLikes())
                {
                    cat.add(featureArray.get(i).get(j));
                    break;
                }
                r -= featureArray.get(i).get(j).getLikes();
            }
        }

        return cat;
    }
}

