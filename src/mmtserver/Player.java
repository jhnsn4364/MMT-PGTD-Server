/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmtserver;

/**
 *
 * @author tareck.haykal and george.barrow
 */
public class Player 
{
    public int id, x, y, isIt;
    
    public Player(int newId, int newX, int newY, int newIsIt)
    {
        id = newId;
        x = newX;
        y = newY;
        isIt = newIsIt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public void setIsIt(int isIt) {
        this.isIt = isIt;
    }
    
    public String toString()
    {
        return ""+id+"\t"+x+"\t"+y+"\t"+isIt;
    }
    
    public boolean getIsIt()
    {
     if(this.isIt==0)
         return false;
     else
         return true;
    }
    
    public double distanceTo(int x,int y)
    {
        double xForDist = Math.pow(this.getX()-x, 2);
        double yForDist = Math.pow(this.getY()-y, 2);
        return Math.sqrt(yForDist+xForDist);
        
    }
}
