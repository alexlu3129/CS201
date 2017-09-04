public class CirclesCountry {
    public int leastBorders(int[] x, int[] y, int[] r, 
                            int x1, int y1, int x2, int y2) {
        int sum=0;
        for (int k=0; k<x.length; k++)
        {
           if (Math.sqrt(Math.pow(x[k]-x1,2) + Math.pow(y[k]-y1, 2))< r[k] 
           && !(Math.sqrt(Math.pow(x[k]-x2,2) + Math.pow(y[k]-y2, 2)) < r[k]))
           {
               sum++;
           }
           else if (Math.sqrt(Math.pow(x[k]-x2,2) + Math.pow(y[k]-y2, 2)) < r[k]
           && !(Math.sqrt(Math.pow(x[k]-x1,2) + Math.pow(y[k]-y1, 2))< r[k]))
           {
                sum++;
           }
        }
        return sum;
    }
 }