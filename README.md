# Bibliotekas menegements
__Bibliotekas menegements - programma kas glaba un pievieno caur konsoli informaciju par gramatam kuri atrodas biblioteka un ari cilvekus kuri ir reģistreti šaja biblioteka.__  

__Merķis__:  

Bibliotēkas sistēmas izveide konsolei, kas ļauj pievienot, dzēst grāmatas, izsekot, kuras grāmatas paņēmuši klienti un reģistrēt jaunus klientus  

__Galvenas funkcijas:__
1. __Grāmatu pārvaldība__
* Pievienot gramatu
* Dzest gramatu
* Meklēt grāmatas pēc nosaukuma
* Grāmatu saraksta parādīšana, kas sakārtota pēc izdošanas gada tabulas formātā

2. __Bibliotēkas analīze__
* Grāmatu kopskaita skaitīšana
* Aizņemto grāmatu skaita skaitīšana

3. __Klientu vadība__
* Klientu reģistrācija sistēmā
* Klientu saraksta parādīšana tabulas formātā

4. __Mijiedarbība, izmantojot konsoli__

# Tirišanas funkcija:  
// Command to clear all caches:  
// In VSCode press "F1", then type a command: Clean Java Language Server Workspace

# Lietotaja interfejsa apraksts   
__Sākums:__   
Pašā sākumā tiek parādīts uzraksts __LIBARY MANAGER__, kas tika izveidots, pateicoties ASCII Animation.  
Pēc tam jūs varat redzēt 8 komandas, kuras var veikt šajā programma, un ir īss apraksts katrai komandai.   
__Īss apraksts un to paskaidrojums katrai komandai:__    
1. __add__ - Pievienot jaunu grāmatu  
      _Kā tas darbojas:_  
      - Pieprasa lietotājam grāmatas nosaukumu, autoru un izdošanas gadu.  
      - Izveido objektu __Book.__  
      - Pievieno to grāmatu sarakstam un saglabā __books.csv__ failā.  
      __Ka ir realizets koda:__  
    ```
    public static void saveBooksToCSV(List<Book> books) {
        try (BufferedWriter writer = Helper.getWriter(BOOKS_FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getYear() + "," + book.isBorrowed());
                writer.newLine();  // Rindas pārtraukums pēc katra grāmatas ieraksta
            }
        } catch (IOException e) {
            e.printStackTrace();  // Drukājiet kļūdu, ja tāda rodas
        }
    }
    ```  
    __Loģika:__  
    - Tiek atvērts fails __books.csv__ rakstišanai.  
    - Katra grāmata tiek ierakstīta formātā:  
    __Nosaukums__, __Autors__, __Gads__, __Vai aizņemta__.  
    - Ja fails neeksistē, tas tiek izveidots.  
      
2. __remove__ - Dzēst grāmatu  
    _Kā tas darbojas:_  
    - Pieorasa lietotājam grāmatas nosaukumu.  
    - Meklē to __book.csv__ failā.  
    - Ja atrod, izdzēš, atjauno failu.  
    __Ka ir realizets koda:__  
    ```  
    public static void updateBooksCSV(List<Book> books) {
        try (BufferedWriter writer = Helper.getWriter(BOOKS_FILE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
           for (Book book : books) {
             writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getYear() + "," + book.isBorrowed());
             writer.newLine();
            }
        } catch (IOException e) {
          System.out.println("Error updating books.csv: " + e.getMessage());
        }
    }  
    ```  
    






 