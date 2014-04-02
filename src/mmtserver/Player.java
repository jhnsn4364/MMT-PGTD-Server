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
}
