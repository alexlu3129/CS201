public class DNAMaxNucleotide {
      public String max(String[] strands, String nuc) {
            int max=0;
            int indexWithMax=0;
            for (int k=0; k<strands.length; k++)
            {
                int tot=0;
                for (int j=0; j<strands[k].length(); j++)
                {
                    if(strands[k].substring(j,j+1).equals(nuc))
                    {
                        tot++;
                    }
                }
                if (tot>max)
                {
                    max=tot;
                    indexWithMax=k;
                }
                else if (tot==max && strands[k].length() > strands[indexWithMax].length())
                {
                        indexWithMax=k;
                }
                
            }
            if (max==0)
            {
                return "";
            }
            else 
            {
                return strands[indexWithMax];
            }
      }
   }