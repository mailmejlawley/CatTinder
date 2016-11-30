package com.cattinder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class UI
{
	public static void main(String[] args)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		try
		{
			BufferedReader f = new BufferedReader(new FileReader("List.txt"));
			
			String line;
			while((line = f.readLine()) != null)
			{
				list.add(line);
			}
			
			f.close();
		}
		catch(Exception e)
		{
			System.out.println("Failed to read file");
		}
		
		AI ai = new AI(list);
		JDialog jF = new JDialog();

        BufferedImage i = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
        {
            Graphics2D g = i.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(ai.generateCat(), 0, 0, 700, 700, null);
            g.dispose();
        }

        jF.add(new JLabel(new ImageIcon(i)));

        jF.getContentPane().setBackground(new Color(200, 200, 200));
		jF.setSize(700, 700);
		jF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jF.setVisible(true);

        ai.close();
	}
}

class Feature
{
	private int likes, offsetX, offsetY;
	String fileName;

	public void setLikes(int l)
	{
		likes = l;
	}

	public void setX(int x)
	{
		offsetX = x;
	}

	public void setY(int y)
	{
		offsetY = y;
	}

	public void setFileName(String fN)
	{
		fileName = fN;
	}

	public int getLikes()
	{
		return likes;
	}

    public int getX()
    {
        return offsetX;
    }

    public int getY()
    {
        return offsetY;
    }

    public String getFileName()
    {
        return fileName;
    }

	public void like()
	{
		likes++;
	}

	public boolean dislike()
	{
		if(likes > 1)
		{
			likes--;
			return true;
		}
		else
			return false;
	}

	public String toString() //Debug
	{
		return fileName;
	}
}

class AI
{
	private ArrayList<ArrayList<Feature>> featureArray;
	private ArrayList<Integer> totalLikes;
	private ArrayList<Feature> cat;
	private Random rand;

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

	public BufferedImage generateCat()
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

        try
        {
            BufferedImage canvas = ImageIO.read(new java.io.File("Images\\" + cat.get(0).getFileName() + cat.get(1).getFileName() + ".png"));
            Graphics2D g2 = canvas.createGraphics();

            for(int i = 2; i < totalLikes.size(); i++)
            {
                if (!cat.get(i).getFileName().equals("-"))
                {
                    BufferedImage bI = ImageIO.read(new java.io.File("Images\\" + cat.get(i).getFileName() + ".png"));
                    g2.drawImage(bI, null, cat.get(i).getX(), cat.get(i).getY());
                }
            }

            g2.dispose();
            return canvas;
        }
        catch (Exception e)
        {
            System.out.println("Could not load image");
        }
		return null;
	}

    public void close()
    {
        try {
            java.io.PrintWriter w = new java.io.PrintWriter("List.txt", "UTF-8");

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
