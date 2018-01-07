import java.io.*;
class clp
{int n;
 int len[],brd[],ht[],pt[];
 int box[][][];
 int spx,spy,spz;
 clp()
 {//default constructor
 }
 clp(int n)
 {this.n=n;
  len=new int[n];
  brd=new int[n];
  ht=new int[n];
  pt=new int[n];
  spx=0;
  spy=0;
  spz=0;
  box=new int[n][8][3];
     for(int i=0;i<n;i++)
     {for(int j=0;j<8;j++)
         {for(int k=0;k<3;k++)
             box[i][j][k]=0;
            }
        }
 }
 public void accept()throws IOException
 {BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     int i=0;
  int volume=0;
  for(i=0;i<n;i++)
  {System.out.println("Enter length, breadth and height");
   int x=Integer.parseInt(br.readLine());
   int y=Integer.parseInt(br.readLine());
   int z=Integer.parseInt(br.readLine());
   int sum=x+y+z;
   x=(int)Math.max((int)Math.max(x,y),(int)Math.max(x,z));
   y=(int)Math.min((int)Math.min(x,y),(int)Math.min(x,z));
   z=sum-(x+y);
   if(x>5 || y>3)//INVALID INPUT
   {System.out.println("Invalid dimension");
    System.exit(0);
    }
   len[i]=x;
   brd[i]=y;
   ht[i]=z;
   System.out.println("Enter  priority");
   pt[i]=Integer.parseInt(br.readLine());
   volume+=(x*y*z);
   if(volume>75)//INVALID INPUT
   {System.out.println("Total volume exceeds the container volume.");
    System.exit(0);
   }
  }
 }//end accept()
 //ARRANGING CARGO
 public void sortpt()
 {for(int i=0;i<n-1;i++)
     {int in=i;
      for(int j=i+1;j<n;j++)
         {if(pt[j]<pt[in])
             in=j;
            }
      int t=pt[i];
      pt[i]=pt[in];
      pt[in]=t;
      t=len[i];
      len[i]=len[in];
      len[in]=t;
      t=brd[i];
      brd[i]=brd[in];
      brd[in]=t;
      t=ht[i];
      ht[i]=ht[in];
      ht[in]=t;
  }
 }
 //sorting according to brd
 public void sortbrd()
 {for(int i=0,x=0;i<n-1;i++)
   {if(pt[i]!=pt[i+1])
    { for(int j=x;j<=i;j++)
           { int in=j;
      for(int z=j+1;z<=i;z++)
      {if(brd[z]>brd[in])
        in=z;
       }
      int t=pt[j];
      pt[j]=pt[in];
      pt[in]=t;
      t=len[j];
      len[j]=len[in];
      len[in]=t;
      t=brd[j];
      brd[j]=brd[in];
      brd[in]=t;
      t=ht[j];
      ht[j]=ht[in];
      ht[in]=t;
     } 
     x=i+1;
    }
  }
 }
 public void actarr()//actually arranging
 {int j=0, depth=0;
  for(int i=0,x=0;i<n;i++)
  {depth=brd[i];
   int layvol=depth*5*3;
   int volfill=0;
   while(volfill<layvol&&j<n&&spx<3)
   {int ret=0;
       if(len[j]<depth)
       ret=place(1,j,i);
    else if(brd[i]<depth)
        ret=place(2,j,i);
    else 
       ret=place(3,j,i);
    if(ret==1)//box number j has been placed
    {volfill+=ht[j]*brd[j]*len[j];
     j+=1;
    }
    i=j-1;
   }
   if(spx>=3)
   {   spz=spz+box[j-1][4][2]-box[j-1][0][2];
       spx=0;
    }
    if(volfill>=layvol)
    {spz=0;
     spx=0;
     spy=spy+depth;
    }
  }
  }
  public int place(int a, int j,int i)//place box number j
 {if(a==1)//placing length wise
  { if((5-spz)>ht[j])
    {box[j][0][0]=spx;
     box[j][0][1]=spy;
     box[j][0][2]=spz;
     box[j][1][0]=spx+brd[j];
     box[j][1][1]=spy;
     box[j][1][2]=spz;
     box[j][2][0]=spx+brd[j];
     box[j][2][1]=spy+len[j];
     box[j][2][2]=spz;
     box[j][3][0]=spx;
     box[j][3][1]=spy+len[j];
     box[j][3][2]=spz;
     box[j][4][0]=spx;
     box[j][4][1]=spy;
     box[j][4][2]=spz+ht[j];
     box[j][5][0]=spx+brd[j];
     box[j][5][1]=spy;
     box[j][5][2]=spz+ht[j];
     box[j][6][0]=spx+brd[j];
     box[j][6][1]=spy+len[j];
     box[j][6][2]=spz+ht[j];
     box[j][7][0]=spx;
     box[j][7][1]=spy+len[j];
     box[j][7][2]=spz+ht[j];
     spx=spx+brd[j];
     return 1;
    }
    else if((5-spz)>brd[j])
    {box[j][0][0]=spx;
     box[j][0][1]=spy;
     box[j][0][2]=spz;
     box[j][1][0]=spx+ht[j];
     box[j][1][1]=spy;
     box[j][1][2]=spz;
     box[j][2][0]=spx+ht[j];
     box[j][2][1]=spy+len[j];
     box[j][2][2]=spz;
     box[j][3][0]=spx;
     box[j][3][1]=spy+len[j];
     box[j][3][2]=spz;
     box[j][4][0]=spx;
     box[j][4][1]=spy;
     box[j][4][2]=spz+brd[j];
     box[j][5][0]=spx+ht[j];
     box[j][5][1]=spy;
     box[j][5][2]=spz+brd[j];
     box[j][6][0]=spx+ht[j];
     box[j][6][1]=spy+len[j];
     box[j][6][2]=spz+brd[j];
     box[j][7][0]=spx;
     box[j][7][1]=spy+len[j];
     box[j][7][2]=spz+brd[j];
     spx=spx+ht[j];
     return 1;
    }
    else
    {int t=j;
       while(t<n-1)
       {if(pt[t]!=pt[t+1])
           break;
           t++;
        }
        int t1=pt[j];
        int t2=ht[j];
        int t3=brd[j];
        int t4=len[j];
        int x=j;
        for(x=j;x<t;x++)
        {pt[x+1]=pt[x];
         ht[x+1]=ht[x];
         brd[x+1]=brd[x];
         len[x+1]=len[x];
        }
        pt[x]=t1;
        ht[x]=t2;
        brd[x]=t3;
        len[x]=t4;
        return 0;
   }
  }//end a==1
  if(a==2)
  { if((5-spz)>ht[j])
    {box[j][0][0]=spx;
     box[j][0][1]=spy;
     box[j][0][2]=spz;
     box[j][1][0]=spx+len[j];
     box[j][1][1]=spy;
     box[j][1][2]=spz;
     box[j][2][0]=spx+len[j];
     box[j][2][1]=spy+brd[j];
     box[j][2][2]=spz;
     box[j][3][0]=spx;
     box[j][3][1]=spy+brd[j];
     box[j][3][2]=spz;
     box[j][4][0]=spx;
     box[j][4][1]=spy;
     box[j][4][2]=spz+ht[j];
     box[j][5][0]=spx+len[j];
     box[j][5][1]=spy;
     box[j][5][2]=spz+ht[j];
     box[j][6][0]=spx+len[j];
     box[j][6][1]=spy+brd[j];
     box[j][6][2]=spz+ht[j];
     box[j][7][0]=spx;
     box[j][7][1]=spy+brd[j];
     box[j][7][2]=spz+ht[j];
     spx=spx+len[j];
     return 1;
    }
    else if((5-spz)>len[j])
    {box[j][0][0]=spx;
     box[j][0][1]=spy;
     box[j][0][2]=spz;
     box[j][1][0]=spx+ht[j];
     box[j][1][1]=spy;
     box[j][1][2]=spz;
     box[j][2][0]=spx+ht[j];
     box[j][2][1]=spy+brd[j];
     box[j][2][2]=spz;
     box[j][3][0]=spx;
     box[j][3][1]=spy+brd[j];
     box[j][3][2]=spz;
     box[j][4][0]=spx;
     box[j][4][1]=spy;
     box[j][4][2]=spz+len[j];
     box[j][5][0]=spx+ht[j];
     box[j][5][1]=spy;
     box[j][5][2]=spz+len[j];
     box[j][6][0]=spx+ht[j];
     box[j][6][1]=spy+brd[j];
     box[j][6][2]=spz+len[j];
     box[j][7][0]=spx;
     box[j][7][1]=spy+brd[j];
     box[j][7][2]=spz+len[j];
     spx=spx+ht[j];
     return 1;
    }
    else//push to end of same priority
   {int t=j;
       while(t<n-1)
       {if(pt[t]!=pt[t+1])
           break;
           t++;
        }
        int t1=pt[j];
        int t2=ht[j];
        int t3=brd[j];
        int t4=len[j];
        int x;
        for(x=j;x<t;x++)
        {pt[x+1]=pt[x];
         ht[x+1]=ht[x];
         brd[x+1]=brd[x];
         len[x+1]=len[x];
        }
        pt[x]=t1;
        ht[x]=t2;
        brd[x]=t3;
        len[x]=t4;
       }
  }//end a==2
  if(a==3)//a==3
  { if((5-spz)>brd[j])
    {box[j][0][0]=spx;
     box[j][0][1]=spy;
     box[j][0][2]=spz;
     box[j][1][0]=spx+len[j];
     box[j][1][1]=spy;
     box[j][1][2]=spz;
     box[j][2][0]=spx+len[j];
     box[j][2][1]=spy+ht[j];
     box[j][2][2]=spz;
     box[j][3][0]=spx;
     box[j][3][1]=spy+ht[j];
     box[j][3][2]=spz;
     box[j][4][0]=spx;
     box[j][4][1]=spy;
     box[j][4][2]=spz+brd[j];
     box[j][5][0]=spx+len[j];
     box[j][5][1]=spy;
     box[j][5][2]=spz+brd[j];
     box[j][6][0]=spx+len[j];
     box[j][6][1]=spy+ht[j];
     box[j][6][2]=spz+brd[j];
     box[j][7][0]=spx;
     box[j][7][1]=spy+ht[j];
     box[j][7][2]=spz+brd[j];
     spx=spx+len[j];
     return 1;
    }
    else if((5-spz)>len[j])
    {box[j][0][0]=spx;
     box[j][0][1]=spy;
     box[j][0][2]=spz;
     box[j][1][0]=spx+brd[j];
     box[j][1][1]=spy;
     box[j][1][2]=spz;
     box[j][2][0]=spx+brd[j];
     box[j][2][1]=spy+ht[j];
     box[j][2][2]=spz;
     box[j][3][0]=spx;
     box[j][3][1]=spy+ht[j];
     box[j][3][2]=spz;
     box[j][4][0]=spx;
     box[j][4][1]=spy;
     box[j][4][2]=spz+len[j];
     box[j][5][0]=spx+brd[j];
     box[j][5][1]=spy;
     box[j][5][2]=spz+len[j];
     box[j][6][0]=spx+brd[j];
     box[j][6][1]=spy+ht[j];
     box[j][6][2]=spz+len[j];
     box[j][7][0]=spx;
     box[j][7][1]=spy+ht[j];
     box[j][7][2]=spz+len[j];
     spx=spx+brd[j];
     return 1;
    }
    else//push to end of same priority
   {int t=j;
       while(t<n-1)
       {if(pt[t]!=pt[t+1])
           break;
           t++;
        }
        int t1=pt[j];
        int t2=ht[j];
        int t3=brd[j];
        int t4=len[j];
        int x;
        for(x=j;x<t;x++)
        {pt[x+1]=pt[x];
         ht[x+1]=ht[x];
         brd[x+1]=brd[x];
         len[x+1]=len[x];
        }
        pt[x]=t1;
        ht[x]=t2;
        brd[x]=t3;
        len[x]=t4;
       }
   }//end a==3
   return 0;
  }
  public void print()
  {for(int i=0;i<n;i++)
      {System.out.println("Box number "+(i+1));
          for(int j=0;j<8;j++)
          {for(int k=0;k<3;k++)
              {System.out.print(box[i][j][k]+" ");
                }
           System.out.println();
        }
    }
  }
  public static void main()throws IOException
  {BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   System.out.println("Enter the number of cargo boxes");
   int n=Integer.parseInt(br.readLine());
      clp obj=new clp(n);
      obj.accept();
      obj.sortpt();
      obj.sortbrd();
      obj.actarr();
      obj.print();
    }
}
