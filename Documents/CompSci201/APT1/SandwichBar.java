import java.util.ArrayList;
public class SandwichBar
{
    
    public int whichOrder(String[] available, String[] orders){
        for (int k=0; k<orders.length; k++)
        {
            ArrayList<String> ingredients = new ArrayList<>();
            int index=0;
            int last=index;
            while (index<orders[k].length())
            {
                if (orders[k].substring(index,index+1).equals(" "))
                {
                    if (!ingredients.contains(orders[k].substring(last,index)))
                    {
                        ingredients.add(orders[k].substring(last, index));
                    }
                    last=index+1;
                }
                else if (index==orders[k].length()-1)
                {
                    ingredients.add(orders[k].substring(last, index+1));
                }
                index++;
            }
            test:
            for (int j=0; j<ingredients.size(); j++)
            {
                boolean hasIt=false;
                for (int i=0; i<available.length; i++)
                {
                    if (ingredients.get(j).equals(available[i]))
                    {
                        hasIt=true;
                        break;
                    }
                }
                if(!hasIt)
                {
                    break;
                }
                if (hasIt && j==ingredients.size()-1)
                {
                    return k;
                }
            }
            
        }
        return -1;
    }
}