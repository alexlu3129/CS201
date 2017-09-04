    public class Totality {
     public int sum(int[] a, String stype) {
         int sum=0;
         switch(stype)
         {
             case ("odd") :
             for (int k=1; k<a.length; k+=2)
             {
                 sum+=a[k];
             }
             break;
             case ("even") :
             for (int k=0; k<a.length; k+=2)
             {
                 sum+=a[k];
             }
             break;
             case("all") :
             for (int k=0; k<a.length; k++)
             {
                 sum+=a[k];
             }
             break;
         }
         return sum;
         
     }
 }
