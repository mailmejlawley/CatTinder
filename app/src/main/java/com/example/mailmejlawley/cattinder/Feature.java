package com.example.mailmejlawley.cattinder;

/**
 * Created by Ken on 11/29/2016.
 */


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
