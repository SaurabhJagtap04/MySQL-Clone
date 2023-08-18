import java.util.*;

class MySqlClone
{
    public static void main(String Arg[])
    {
        Database dobj = new Database();
        dobj.StartDatabase();    
    }
}

class Phone
{
    public int Id;
    public String Name;
    public int Price;
    public int Quantity;

    public static int IdGenerate;

    static
    {
        IdGenerate = 0;
    }

    public Phone( String str, int X, int Y)
    {
        this.Id = ++IdGenerate;
        this.Name = str;
        this.Price = X;
        this.Quantity = Y;
    }

    public void Display()
    {
        //System.out.println(this.Id + " " +this.Name+ " " +this.Price+ " " +this.Quantity);
        System.out.printf("| %-2s | %-10s | %-1s | %-1s |\n",this.Id,this.Name, this.Price, this.Quantity);
    }
}

class Database
{
    public LinkedList <Phone> lobj;

    public Database()
    {
        lobj = new LinkedList <Phone> ();
    }

    public void StartDatabase()
    {
        System.out.println();
        System.out.println("MySQLClone started successfully...");
        System.out.println("Table Schema created successfully...\n");

        //////////////////////////////////////////////////////////

        String Query;
        String Tokens[];

        Scanner sobj = new Scanner(System.in);
        int TokensCount = 0;

        while(true)
        {
            System.out.print("MySQL DBMS : > ");
            Query = sobj.nextLine();

            Tokens = Query.split(" ");

            TokensCount = Tokens.length;

            if(TokensCount == 1)
            {
                if("exit".equals(Tokens[0]))
                {
                    System.out.println("\n-----------------------------------------------------\n");

                    System.out.println("* * * * *  Thankyou for using MySQL DBMS  * * * * * \n");

                    System.out.println("-----------------------------------------------------\n");
                    break;
                }
            }
            else if(TokensCount == 2)
            {}
            else if(TokensCount == 3)
            {}
            else if(TokensCount == 4)
            {
                if("select".equals(Tokens[0]))
                {
                    SelectFrom();
                }
            }
            else if(TokensCount == 5)
            {
                if("select".equals(Tokens[0]))
                {
                    if("MAX".equals(Tokens[1]))
                    {
                        System.out.println("Maximum Price is : "+Find_Max());
                    }
                    else if("MIN".equals(Tokens[1]))
                    {
                        System.out.println("Minimum Price is : "+Find_Min());
                    }
                    else if("SUM".equals(Tokens[1]))
                    {
                        System.out.println("Summation of Prices is : "+Find_SUM());
                    }
                    else if("AVG".equals(Tokens[1]))
                    {
                        System.out.println("Average of Prices is : "+Find_AVG());
                    }
                }
            }
            else if(TokensCount == 6)
            {}
            else if(TokensCount == 7)
            {
                if("insert".equals(Tokens[0]))
                {
                    InsertIntoTable(Tokens[4], Integer.parseInt(Tokens[5]), Integer.parseInt(Tokens[6]));
                }
                else if("delete".equals(Tokens[0]))
                {
                    DeletetFrom(Integer.parseInt(Tokens[6]));
                }
            }
        }
        //////////////////////////////////////////////////////////
    }

    // Insert into table values( _____ , ______ , ______ )

    public void InsertIntoTable(String name, int Price, int qty)
    {
        Phone pobj = new Phone(name, Price, qty);
        lobj.add(pobj);
    }

    // select * from Phone
    
    public void SelectFrom()
    {
        System.out.println("\nRecords from phone table are : \n");

        System.out.println("---------------------------------");
        System.out.printf("| %-1s | %-10s | %-1s | %-1s |\n","ID","Phone Name", "Price", "Qty");
        System.out.println("---------------------------------");

        for(Phone pref : lobj)
        {
            pref.Display();
        }

        System.out.println("---------------------------------\n");
    }

    // select * from phone where Id = 5

    public void SelectFrom(int no)
    {
        System.out.println("---------------------------------");
        System.out.printf("| %-1s | %-10s | %-1s | %-1s |\n","ID","Phone Name", "Price", "Qty");
        System.out.println("---------------------------------");

        for(Phone pref : lobj)
        {
            if(pref.Id == no)
            {
                pref.Display();
                break;
            }
        }

        System.out.println("---------------------------------");
    }    

    // select * from phone where Name = OnePlus10

    public void SelectFrom(String str)
    {
         System.out.println("---------------------------------");
        System.out.printf("| %-1s | %-10s | %-1s | %-1s |\n","ID","Phone Name", "Price", "Qty");
        System.out.println("---------------------------------");

        for(Phone pref : lobj)
        {
            if(str.equals(pref.Name))
            {
                pref.Display();
                break;
            }
        }

        System.out.println("---------------------------------");
    }    

    // delete * from phone where Id = 5

    public void DeletetFrom(int no)
    {
        int i = 0;

        for(Phone pref : lobj)
        {
            if(pref.Id == no)
            {
                lobj.remove(i);
                break;
            }
            i++;
        }
    }    

    // select MAX(Price) from phone

    public int Find_Max()
    {
        int iMax = 0;

        for(Phone pref : lobj)
        {
            if(pref.Price > iMax)
            {
                iMax = pref.Price;
            }
        }
        return iMax;
    }    

    // select MIN(Price) from phone

    public int Find_Min()
    {
        Phone temp = lobj.get(0);

        int iMin = temp.Price;

        for(Phone pref : lobj)
        {
            if(pref.Price < iMin)
            {
                iMin = pref.Price;
            }
        }
        return iMin;
    }    

    // select SUM(Price) from phone

    public int Find_SUM()
    {
        int iSum = 0;

        for(Phone pref : lobj)
        {                       
            iSum = iSum + pref.Price;            
        }
        return iSum;
    }  

    // select AVG(Price) from phone

    public int Find_AVG()
    {
        int iSum = 0;

        for(Phone pref : lobj)
        {                  
            iSum = iSum + pref.Price;            
        }
        return (iSum / (lobj.size()));
    }    
}