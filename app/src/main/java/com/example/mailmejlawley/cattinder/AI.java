package com.example.mailmejlawley.cattinder;

//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.awt.image.BufferedImageOp;
//import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.content.res.AssetManager;
import android.graphics.*;
import android.widget.ImageView;

/**
 * Created by Ken on 11/29/2016.
 */


class AI
{
    private  ArrayList<ArrayList<Feature>> featureArray;
    private  ArrayList<Integer> totalLikes;
    private  ArrayList<Feature> cat;
    private  Random rand;

    public AI()
    {
        featureArray = new ArrayList<>();
        totalLikes = new ArrayList<>();
        rand = new Random();

        try
        {

            //AssetManager am = context.getAssets();
            //InputStream is = am.open("test.txt");
            //BufferedReader f = getResources().openRawResource(R.raw.List);
            BufferedReader f = new BufferedReader(new FileReader("list.txt"));


            String line;
            String categories = "";

            while((line = f.readLine()) != null)
            {
                Feature feature = new Feature();
                System.out.println(line);

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

            f.close();
            generateCat();
        }
        catch(Exception e)
        {
            System.out.println("Failed to read file");
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

        System.out.println(cat);
        return cat;
/*
        try
        {

            //Bitmap bitmap = null;//ImageIO.read(new java.io.File("Images\\" + cat.get(0).getFileName() + cat.get(1).getFileName() + ".png"));
            //BufferedImage canvas = ImageIO.read(new java.io.File("Images\\" + cat.get(0).getFileName() + cat.get(1).getFileName() + ".png"));
            //Graphics2D g2 = canvas.createGraphics();

            for(int i = 2; i < totalLikes.size(); i++)
            {
                if (!cat.get(i).getFileName().equals("-"))
                {
                    //BufferedImage bI = ImageIO.read(new java.io.File("Images\\" + cat.get(i).getFileName() + ".png"));
                   // g2.drawImage(bI, null, cat.get(i).getX(), cat.get(i).getY());
                }
            }

            //g2.dispose();
            //return bitmap;
        }
        catch (Exception e)
        {
            System.out.println("Could not load image");
        }
        //return null;
        */
    }

    public void close()
    {
        try {
            java.io.PrintWriter w = new java.io.PrintWriter("list.txt", "UTF-8");

            for(int i = 0; i < featureArray.size(); i++)
            {
                for(Feature f : featureArray.get(i))
                {
                    w.print(i);
                    w.print('\t');
                    w.print(f.getX());
                    w.print('\t');
                    w.print(f.getY());
                    w.print('\t');
                    w.print(f.getLikes());
                    w.print('\t');
                    w.println(f.getFileName());
                }
            }

            w.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to write file");
        }
    }
}

