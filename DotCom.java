import java.util.ArrayList;

public class DotCom {
     private ArrayList<String> locationCells;
     private ArrayList<String> boatLoc1;
     private ArrayList<String> boatLoc2;
    
    public void setLocationCells(ArrayList<String> loc)
    {
        locationCells = loc;
  
    }
    
    public ArrayList<String> getCells()
    {
        return locationCells;
  
    }   
    
    public void setOtherLoc(ArrayList<String> loc1, ArrayList<String> loc2)
    {
        boatLoc1 = loc1;
        boatLoc2 = loc2;
    }
    
    public String checkYourself(String userInput)
    {
        String result = "miss";
        
        //?��indexOf來檢?��?��?��??�中
        int index = locationCells.indexOf(userInput); 
        if (index >= 0) {
            System.out.println(sound);
            if(locationCells.size() >1){
                move();
            }
  
            locationCells.remove(index);
            
            index = boatLoc1.indexOf(userInput);
            if (index >= 0)
                boatLoc1.remove(index);
            
            index = boatLoc2.indexOf(userInput);
            if (index >= 0)
                boatLoc2.remove(index);
            
            if (locationCells.isEmpty()) {
                result = "kill";
            }
            else
            {
                result = "hit";
            }
        }
        return result;
    }

    //TODO:  all the following code was added and should have been included in the book
    private String name;
    private int size;
    private String sound;
    public String getName() {
        return name;
    }
    
    public String getSound() {
        return sound;
    }
    
   public int getSize() {
        return size;
    }
    
    public DotCom createDot(String name, int size) { 
                DotCom tmp = new DotCom();
                tmp.name = name;
                tmp.size = size;
                if(size==2)
                    tmp.sound = "HIT!";
                if(size==3)
                    tmp.sound = "WHACK!";
                if(size==4)
                    tmp.sound = "SMASH!";                   
                return tmp;
    }

    public int locToInt(String cell) {
        
     
        if(cell.charAt(0)=='a'){
            return (int)cell.charAt(1) - 48;
        }
        else if(cell.charAt(0)=='b'){
            return 7+(int)cell.charAt(1)- 48;
        }
        else if(cell.charAt(0)=='c'){
            return 14+(int)cell.charAt(1)- 48;
        }
        else if(cell.charAt(0)=='d'){
            return 21+(int)cell.charAt(1) - 48;
        }
        else if(cell.charAt(0)=='e'){
            return 28+(int)cell.charAt(1) - 48;
        }
        else if(cell.charAt(0)=='f'){
            return 35+(int)cell.charAt(1) - 48;
        }
        else {
            return 42+(int)cell.charAt(1) - 48;
        }
    }
         
         public String locToChar(int cell) {
             String column ;
            if(cell<7){
                column = "a";
            }
            else if(cell<14 || cell == 7){
                column = "b";
            }
            else if(cell<21|| cell==14){
                column = "c";
            }
            else if(cell<28 || cell==21){
                column = "d";
            }
            else if(cell<35|| cell==28){
                column = "e";
            }
            else if(cell<42 || cell==35){
                column = "f";
            }
            else {
                column = "g";
            }
            
            return column;
         }
    //移動
         public void move() {
         
          //設定移動方向和步數   
        int step =0;
        if(size==2){
            step = (int) (Math.random()*2 +1) * (int) (Math.random()>0.5?1:-1);
        }
        else{
             step = (int) (Math.random()>0.5?1:-1);
        }
        
         //船是直的橫的?
         
         boolean straight = false; //檢查船停放方式，false停橫的，true停直的
         
         if(locationCells.get(0).charAt(0) != locationCells.get(1).charAt(0) ){  //直的
            straight = true;
         }

         //檢查是否超出地圖?
         boolean out = false;
        
         if(straight){
             if(step == 1){
                 if(locationCells.get(0).charAt(0)=='a')
                     out = true;
             }
             else if(step == -1){
                 if(locationCells.get(locationCells.size()-1).charAt(0)=='g')
                     out = true;
             }
             else if(step == -2){
                 if(locationCells.get(locationCells.size()-1).charAt(0)=='g'|| locationCells.get(locationCells.size()-1).charAt(0)=='f')
                     out = true;
             }
             else if(step == 2){
                 if(locationCells.get(0).charAt(0)=='a'|| locationCells.get(0).charAt(0)=='b')
                     out = true;
             }
         }
         else{
             if(step == 1){
                 if(locationCells.get(0).charAt(1)=='0')
                     out = true;
             }
             else if(step == -1){
                 if(locationCells.get(locationCells.size()-1).charAt(1)=='6')
                     out = true;
             }
             else if(step == -2){
                 if(locationCells.get(locationCells.size()-1).charAt(1)=='5'|| locationCells.get(locationCells.size()-1).charAt(1)=='6')
                     out = true;
             }
             else if(step == 2){
                 if(locationCells.get(0).charAt(1)=='0'|| locationCells.get(0).charAt(1)=='1')
                     out = true;
             }
         }
         System.out.println("step: " + step);
         int loc = 0, empty1 = 0, empty2 = 0;
         //移動
         
         if(!out){
          
             if(straight){
                for(int i =0; i<locationCells.size(); i++){
                    loc = locToInt(locationCells.get(i)) ;
                    loc = loc-7*step;
                    String a = locToChar(loc)+ locationCells.get(i).charAt(1);
                    
                    empty1 = boatLoc1.indexOf(a);
                    empty2 = boatLoc2.indexOf(a);  
                    if(empty1==-1 && empty2 == -1)
                    {                    
                        locationCells.add(i, a);  
                        locationCells.remove(i+1);
                    }
                }
             }
             else{
                for(int i =0; i<locationCells.size(); i++){
                    loc = locToInt(locationCells.get(i)) ;
                    loc = loc-step;
                    int num = (int)locationCells.get(i).charAt(1) -48 - step;
                    String b = locationCells.get(i).charAt(0)+ Integer.toString(num);
                    
                    empty1 = boatLoc1.indexOf(b);
                    empty2 = boatLoc2.indexOf(b);
                    
                    if(empty1==-1 && empty2 == -1)
                    {
                        locationCells.add(i, b); 
                        locationCells.remove(i+1);                    
                    }

                }
             }
             
             
         }
         System.out.println("loc: " + locationCells);
         System.out.println("b1: "+ boatLoc1);
         System.out.println("b2: "+ boatLoc2);
     }
}
