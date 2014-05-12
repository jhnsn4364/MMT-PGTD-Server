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
    public int id, x, y, isIt, delayCount, immunityCount;
    public boolean delay, hasImmunity;
    
    public Player(int newId, int newX, int newY, int newIsIt)
    {
        id = newId;
        x = newX;
        y = newY;
        isIt = newIsIt;
        hasImmunity=false;
        delay=false;
        immunityCount=0;
        delayCount=0;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean getImmunityState()
    {
        return hasImmunity;
    }
    
    public void setImmunityState(boolean state)
    {
        hasImmunity=state;
        immunityCount=0;
    }
    
    public int getImmunityCount()
    {
        return immunityCount;
    }
    
    public void setImmunityCount(int count)
    {
        immunityCount+=count;
    }
    public boolean getDelay()
    {
        return delay;
    }
    
    public int getDelayCount()
    {
        return delayCount;
    }        
    
    public void setDelayCount(int i)
    {
        delayCount+=i;   
    }
    
    public void setDelayState(boolean count)
    {
        delay = count;
        delayCount=0;
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
