# Bibliotekas menegements
__Bibliotekas menegements - programma kas glaba un pievieno caur konsoli informaciju par gramatam kuri atrodas biblioteka un ari cilvekus kuri ir reģistreti šaja biblioteka.__  

__Merķis__:  

Bibliotēkas sistēmas izveide konsolei, kas ļauj pievienot, dzēst grāmatas, izsekot, kuras grāmatas paņēmuši klienti un reģistrēt jaunus klientus  

`Galvenas funkcijas:`
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
`Sākums:`   
Pašā sākumā tiek parādīts uzraksts __LIBARY MANAGER__, kas tika izveidots, pateicoties ASCII Animation.  
Pēc tam jūs varat redzēt 15 komandas, kuras var veikt šajā programma, un ir īss apraksts katrai komandai.   
__Īss apraksts un to paskaidrojums katrai komandai:__    
1. `ADD - Pievienot jaunu grāmatu `   
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
      
2. `REMOVE - Dzēst grāmatu`  
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
    __Loģika:__  
   - Tiek ielādētas visas grāmatas.  
   - Tiek filtrētas tās, kas neatbilst dzēšamajai grāmatai.  
   - Fails tiek pārrakstīts bez izdzēstās grāmatas.

3. `FIND – Atrast grāmatu`  
_Kā tas darbojas:_  
    - Pieprasa grāmatas nosaukumu.  
    - Meklē to sarakstā un, ja atrod, izvada informāciju.  
    __Ka ir realizets koda:__  
    ```  
    public static Book findBookByTitle(List<Book> books, String title) {
        return books.stream()
        .filter(book -> book.getTitle().equalsIgnoreCase(title))
        .findFirst()
        .orElse(null);
    }  
    ```  
    __Loģika:__  
    1. _Filtrēšana (filter)_  
        - __Metode:__ .`filter(book -> book.getTitle().equalsIgnoreCase(title))`  
        - __Darbība:__ Iziet cauri visām sarakstā esošajām grāmatām `(books)` un atstāj tikai tās, kuru nosaukums `(getTitle())` sakrīt ar meklēto `(title),` ignorējot lielos un mazos burtus `(equalsIgnoreCase).`  
    2. _Pirmā atrastā grāmata (findFirst)_  
       - __Metode:__ `.findFirst() ` 
       - __Darbība:__ Ja sarakstā ir vairākas grāmatas ar tādu pašu nosaukumu, __atgriež tikai pirmo atrasto.__  
    3. _Rezultāts (orElse(null))_  
       - __Metode:__ `.orElse(null) ` 
       - __Darbība:__ Ja sarakstā nav nevienas atbilstošas grāmatas, __atgriež__  null, lai norādītu, ka tāda grāmata nav atrasta.  

4. `LIST – Parādīt visas grāmatas`  
    _Kā tas darbojas:_  
    - Ielādē grāmatas no books.csv.  
    - Izvada tās konsolē.  
    __Ka ir realizets koda:__  
    ```  
    // Grāmatu lejupielāde no CSV
    public static List<Book> loadBooksFromCSV() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = Helper.getReader(BOOKS_FILE)) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(","); // Sadaliet virkni ar komatu
            if (data.length >= 4) { // Parbauda datus
                try {
                    // Pārvērtiet gadu par skaitli
                    int year = Integer.parseInt(data[2]);
                    // Pārvērtiet grāmatas statusu uz Būla vērtību
                    boolean isBorrowed = Boolean.parseBoolean(data[3]);

                    // Grāmatas izveide
                    Book book = new Book(data[0], data[1], year);
                    if (isBorrowed) {
                        book.borrowBook(); // Ja grāmata ir paņemta, tad atzīmējam kā paņemtu
                    }
                    books.add(book); // Pievienojiet grāmatu sarakstam
                } catch (NumberFormatException e) {
                    System.out.println("Data conversion error: " + line);
                }
            } else {
                System.out.println("Invalid string format: " + line);
            }
        }
        } catch (IOException e) {
           System.out.println("Error loading books from CSV.");
        }
        return books;
    }  
    ```  
    __Loģika:__  
     - __Nolasa CSV failu rindu pa rindai__ un sadala to pa komatiem.  
     - __Pārbauda, vai dati ir pilnīgi,__ un konvertē gadu (int) un aizņemšanas statusu (boolean).  
     - __Izveido Book objektu__ un, ja nepieciešams, atzīmē to kā aizņemtu.  
     - __Ja dati nederīgi__, izvada kļūdas ziņojumu, beigās atgriež sarakstu ar grāmatām.  

5. `LIST_CLIENT – Parādīt visus klientus`  
    _Ka tas darbojas_  
    - Izvada visus reģistrētos klientus tabula formata.  
    __Ka ir realizets koda:__  
    ```  
    public static void listClients(List<Client> clients) {
        TableFormatter.printClientsTable(clients);
    }  

    public static void printClientsTable(List<Client> clients) {
     // Inicializējiet klienta un grāmatu maksimālos garumus
     int maxClientNameLength = "Client Name".length();
     int maxBooksLength = "Borrowed Books".length();

     // Atrodiet klienta vārda maksimālo garumu un grāmatu sarakstu
     for (Client client : clients) {
        maxClientNameLength = Math.max(maxClientNameLength, client.getName().length());

        // Veidojot līniju ar grāmatu nosaukumiem
        String borrowedBooksList = client.getBorrowedBook().stream()
            .map(Book::getTitle)
            .collect(Collectors.joining(", "));

        maxBooksLength = Math.max(maxBooksLength, borrowedBooksList.length());
    }

    // Formāts tabulas attēlošanai
    String headerFormat = "| %-"+maxClientNameLength+"s | %-"+maxBooksLength+"s |";
    String separator = "-".repeat(maxClientNameLength + maxBooksLength + 7);

    // Tabulas drukāšana
    System.out.println(HEADER_COLOR + separator + RESET);
    System.out.printf(HEADER_COLOR + headerFormat + "%n", "Client Name", "Borrowed Books");
    System.out.println(HEADER_COLOR + separator + RESET);

    // Mēs šķirojam klientus un parādām viņu datus
    for (Client client : clients) {
        String borrowedBooksList = client.getBorrowedBook().stream()
            .map(Book::getTitle)
            .collect(Collectors.joining(", "));

        System.out.printf(ROW_COLOR + headerFormat + "%n" + RESET, client.getName(), borrowedBooksList.isEmpty() ? "None" : borrowedBooksList);
    }

     System.out.println(HEADER_COLOR + separator + RESET);
    }
    
    ```  
    __Loģika:__  
    1. __Tabulas formatēšana::__  
       - Inicializējam maksimālos garumus klienta vārdiem un aizņemto grāmatu sarakstiem: `maxClientNameLength` un `maxBooksLength`.  
    2. __Maksimālā garuma aprēķins:__  
       - Ar ciklu pārbaudām katra klienta vārdu garumu un atjaunojam `maxClientNameLength`.  
       - Pārbaudām katra klienta aizņemto grāmatu sarakstu un atjaunojam maxBooksLength, lai nodrošinātu, ka viss teksts labi ietilpst tabulā.  
    3. __Tabulas formāta izveide:__  
        - Izveidojam formātu galvenajai tabulai (headerFormat), ņemot vērā maksimālos garumus.  
        - Izveidojam atdalītāju (separator), kas veido līniju no - simboliem, kas atbilst tabulas platumam.  
    4. __Tabulas izdrukāšana:__  
        - Izdrukājam galveni, izmantojot aprēķināto formātu.  
        - Izdrukājam atdalītāju.  
    5. __Klientu datu izvadīšana:__  
        - Klientus šķirojam un izvadām katra klienta vārdu un aizņemto grāmatu sarakstu tabulā.   
        - Ja klientam nav aizņemtu grāmatu, tiek parādīts `"None"`.  
        - Tabulas beigās izdrukājam atdalītāju.  


6. `REGISTER – Reģistrēt jaunu klientu`  
    _Kā tas darbojas:_  
    - Pieprasa lietotājam ievadīt klienta vārdu
    - Reģistrē jaunu klientu CSV faila.  
    __Ka ir realizets koda:__  
    ```  
    private static void registerClient(Scanner scanner, List<Client> clients) {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        Client.registerClient(clients, name);
        CSVHandler.saveClientsToCSV(clients);
    }
    ```  
    __Loģika:__  
   1. __Klienta vārda ievade:__  
       - Programma pieprasa lietotājam ievadīt klienta vārdu ar `System.out.print("Enter client name: ")`.  
       - Vārds tiek saglabāts mainīgajā name ar scanner.nextLine().
   2. __Klienta reģistrēšana:__  
        - Izsauc `Client.registerClient(clients, name) metodi`, lai pievienotu jauno klientu sarakstam clients, izmantojot ievadīto vārdu.
   3. __Datu saglabāšana CSV failā:__  
        - Izsauc `CSVHandler.saveClientsToCSV(clients)`, lai saglabātu atjaunināto klientu sarakstu CSV failā.  
  
7. `REMOVE_CLIENT – Dzēst klientu`  
    _Kā tas darbojas:_  
    - Pieprasa lietotājam ievadīt klienta vārdu, kuru vēlas dzēst no bibliotēkas sistēmas.  
    - Pārbauda, vai šis klients eksistē un vai viņam nav `aizņemtu grāmatu.`  
    - Ja klients ir atrasts un visas grāmatas ir atgrieztas, klients tiek noņemts no saraksta un dati tiek atjaunināti CSV failā.  
    __Kā ir realizēts kodā__  
    ```  
    public static void removeClient(List<Client> clients, List<Book> books, Scanner scanner) {
        System.out.print("Enter client name to remove: ");
        String name = scanner.nextLine();
    
        Client client = Client.findClientByName(clients, name);
        if (client == null) {
            System.out.println(ColorScheme.ERROR_COLOR + "Client not found." + ColorScheme.ERROR_RESET);
            return;
        }
    
        // Parbaudam vai visi gramatas klients nododa biblioteka
        if (!client.getBorrowedBook().isEmpty()) {
            System.out.println(ColorScheme.ERROR_COLOR + "Client cannot be removed. They have borrowed books that must be returned first." + ColorScheme.ERROR_RESET);
            return;
        }
    
        clients.remove(client);
        CSVHandler.updateClientsCSV(clients); // Apdejtojam clients.csv
        System.out.println("Client removed successfully.");
    }  
    ```  
    __Loģika__  
    1. __Klienta meklēšana:__  
        - Pieprasa ievadīt klienta vārdu.  
        - Meklē klientu sarakstā, izmantojot `metodi Client.findClientByName.`  
        - Ja klients netiek atrasts, izvada ziņojumu `"Client not found."` un pārtrauc izpildi.  
    2. __Aizņemto grāmatu pārbaude:__  
        - Ja klientam ir `aizņemtas grāmatas,` viņu nevar dzēst.  
        - Izvada brīdinājumu: `"Client cannot be removed. They have borrowed books that must be returned first."`  
    3. __Klienta dzēšana:__  
        - Ja klients ir atrasts un visas grāmatas ir atgrieztas, viņš tiek izņemts no saraksta.  
        - Atjauno klientu datus `clients.csv failā` ar `CSVHandler.updateClientsCSV.`

8. `COUNT – Skaitīt grāmatas`  
    _Kā tas darbojas:_  
    - Izvada kopējo grāmatu skaitu sistēmā.  
    __Ka ir realizets koda__  
    ```  
    public static void countBorrowedBooks(List<Book> books) {
        long count = books.stream().filter(Book::isBorrowed).count();
        System.out.println(ColorScheme.TABLE_HEADER_COLOR + " Borrowed books: " + count + ColorScheme.TABLE_RESET);
    }
    ```  
    __Loģika__  
    1. __Filtrē grāmatas:__  
        - Programma izmanto `books.stream()` lai izveidotu straumi no grāmatām.  
        - Tiek izmantota `filter(Book::isBorrowed)`, lai atlasītu tikai tās grāmatas, kuru status ir "aizņemta" `(t.i., isBorrowed() atgriež true)`.
    2. __Skaitīšana:__  
        - Ar `count()` tiek saskaitītas grāmatas, kas atbilst filtrēšanas kritērijiem (tās, kas ir aizņemtas).  
    3. __Izvade:__  
        - Pēc tam tiek izdrukāts aizņemtās grāmatas skaits ar krāsu iestatījumiem no ColorScheme.TABLE_HEADER_COLOR un ColorScheme.TABLE_RESET.  

9.  `FILTER_YEAR – Filtrēt grāmatas pēc gada`  
    _Kā tas darbojas:_
    - Prasa no lietotāja gadu (piemēram, “2021”), pārbauda, vai tas ir ievadīts pareizi (četri cipari), un tad izvada tikai tās grāmatas, kas publicētas šajā gadā.  
    __Ka ir realizets koda__    
      
    ```  
    private static void filterBooksByYear(Scanner scanner, List<Book> books) {
    System.out.print("Enter the year to filter by: ");
    String input = scanner.nextLine();

    // Regulārā izteiksme pārbauda, vai lietotājs ievadījis 4 ciparus
    if (!input.matches("\\d{4}")) {
        System.out.println(ColorScheme.ERROR_COLOR + "Invalid year format. Please enter a 4-digit year." + ColorScheme.ERROR_RESET);
        return;
    }

    int year = Integer.parseInt(input); // konvertējam droši, jo pārbaudīts

    List<Book> filteredBooks = Book.filterBooksByYear(books, year);
    if (filteredBooks.isEmpty()) {
        System.out.println(ColorScheme.ERROR_COLOR + "No books found for the year " + year + "." + ColorScheme.ERROR_RESET);
    } else {
        TableFormatter.printBooksTable(filteredBooks);
    }
    }  
    ```  
    __Loģika__  
    1. __Ievades apstrāde:__  
        - Lietotājs ievada gadu kā tekstu `(scanner.nextLine())`.  
        - Ar regulāro izteiksmi `\\d{4}` pārbauda, vai tas satur tieši četri cipari (piemēram, "2023").  
    2. __Pārveidošana un filtrēšana:__  
        - Pēc pārbaudes gads tiek pārvērsts uz `int`.  
        - Metode `Book.filterBooksByYear(books, year)` atgriež sarakstu ar atbilstošajām grāmatām.  
    3. __Izvade:__  
        - Ja saraksts ir tukšs, lietotājs tiek informēts ar kļūdas paziņojumu.  
        - Ja ir rezultāti, tie tiek attēloti tabulas veidā ar `TableFormatter.printBooksTable(filteredBooks)`.  
          
10. `FILTER_BORROWED – Filtrēt grāmatas pēc aizņemtības statusa`  
    _Kā tas darbojas:_  
    - Prasa no lietotāja ievadīt statusu (true – ja grāmata ir aizņemta, false – ja pieejama), un pēc tam izvada sarakstu ar atbilstošajām grāmatām.  
    __Ka ir realizets koda__    
    ``` 
    private static void filterBooksByBorrowedStatus(Scanner scanner, List<Book> books) {
    System.out.print("Enter borrowed status (true for borrowed, false for available): ");
    
    boolean isBorrowed;
    try {
        isBorrowed = Boolean.parseBoolean(scanner.nextLine().trim().toLowerCase());
    } catch (Exception e) {
        System.out.println(ColorScheme.ERROR_COLOR + "Invalid input. Please enter 'true' or 'false'." + ColorScheme.ERROR_RESET);
        return;
    }

    List<Book> filteredBooks = Book.filterBooksByBorrowedStatus(books, isBorrowed);
    if (filteredBooks.isEmpty()) {
        System.out.println(ColorScheme.ERROR_COLOR + "No books found with borrowed status " + isBorrowed + "." + ColorScheme.ERROR_RESET);
    } else {
        TableFormatter.printBooksTable(filteredBooks);
    }
    }  
    ```  
    __Loģika__  
    1. __Ievades apstrāde:__  
        - Lietotājs ievada tekstu (`true` vai `false`).  
        - Ar `Boolean.parseBoolean`(...) pārveido tekstu uz boolean (`true` vai `false`).  
        - Tiek izmantots `.trim().toLowerCase()` lai padarītu ievadi elastīgāku (piemēram, "True", "TRUE", " true " u.c.).  
    2. Filtrēšana:  
        - Metode `Book.filterBooksByBorrowedStatus(books, isBorrowed)` atgriež tikai tās grāmatas, kuras atbilst izvēlētajam statusam.   
    3. __Izvade:__  
        - Ja neatrod nevienu grāmatu ar šādu statusu, parādās paziņojums ar kļūdas krāsu.  
        - Ja grāmatas atrastas – tās tiek izvadītas tabulas formā ar `TableFormatter.printBooksTable(filteredBooks)`.  


    

  

11. `SORT_ASC – Kārtot grāmatas pēc gada (pieaugošā secībā)`  
    _Kā tas darbojas:_  
    - Kārto grāmatas sarakstu pēc izdošanas gada pieaugošā secībā.  
    __Ka ir realizets koda__  
    ```  
    public static void sortBooksByYearAsc(List<Book> books) {
        books.sort(Book.sortByYearAsc);
        listBooks(books);
    }
    ```  
    __Loģika__  
    1. __Kārtot grāmatas:__  
        - Programma izmanto `books.sort(Book.sortByYearAsc)` metodi, kas kārtos grāmatas sarakstā pēc to izdošanas gada augošā secībā. 
        - `Book.sortByYearAsc` ir salīdzināšanas funkcija, kas salīdzina grāmatas pēc gada, nodrošinot, ka vecākās grāmatas būs saraksta sākumā.
    2. __Grāmatu parādīšana:__  
        - Pēc grāmatu kārtošanas tiek izsaukta `listBooks(books) metode`, lai izvadītu sarakstu ar kārtotajām grāmatām uz ekrāna.  

12. `SORT_DESC – Kārtot grāmatas pēc gada (dilstošā secībā)`  
    _Kā tas darbojas:_  
    - Kārto grāmatas sarakstu pēc izdošanas gada dilstošā secībā.  
    __Ka ir realizets koda__  
    ```  
    public static void sortBooksByYearDesc(List<Book> books) {
        books.sort(Book.sortByYearDesc);
        listBooks(books);
    }

    ```  
    __Loģika__  
    1. __Kārtot grāmatas:__  
        - Programma izmanto `books.sort(Book.sortByYearDesc) metodi`, kas kārtos grāmatas sarakstā pēc to izdošanas gada dilstošā secībā.
        - `Book.sortByYearDesc` ir salīdzināšanas funkcija, kas salīdzina grāmatas pēc gada, nodrošinot, ka jaunākās grāmatas būs saraksta sākumā.
    2. __Grāmatu parādīšana:__  
        - Pēc grāmatu kārtošanas, tiek izsaukta `listBooks(books) metode`, lai izvadītu sarakstu ar kārtotajām grāmatām uz ekrāna.  

13. `BORROW – Paņemt grāmatu no bibliotekas`   
    _Kā tas darbojas:_  
    - Pieprasa lietotājam ievadīt grāmatas nosaukumu, kuru viņš vēlas aizņemt.  
    - Atzīmē grāmatu kā aizņemtu.  
    __Kaz tas ir realizets koda__  
    ```  
    private static void borrowBook(Scanner scanner, List<Book> books,   List<Client> clients) {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = Client.findClientByName(clients, clientName);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter book title to borrow: ");
        String bookTitle = scanner.nextLine();
        Book book = Book.findBookByTitle(books, bookTitle);
        if (book != null) {
            client.borrowBook(book);
            CSVHandler.saveBorrowedBook(clientName, bookTitle);
        } else {
            System.out.println("Book not found.");
        }
    }
    ```  
    __Loģika__  
    1. __Klienta meklēšana:__  
        - Programma pieprasa lietotājam ievadīt klienta vārdu.  
        - Tiek meklēts klients ar šo vārdu sarakstā, izmantojot `Client.findClientByName metodi`. Ja klients nav atrasts, tiek izvadīts ziņojums "Client not found." un metode tiek pārtraukta.
    2. __Grāmatas meklēšana:__  
        - Pēc tam, kad klients ir atrasts, programma pieprasa ievadīt grāmatas nosaukumu.  
        - Grāmata tiek meklēta sarakstā, izmantojot `Book.findBookByTitle metodi.` Ja grāmata netiek atrasta, tiek izvadīts ziņojums "Book not found." un metode tiek pārtraukta.
    3. __Grāmatas aizņemšana:__  
        - Ja gan klients, gan grāmata ir atrasti, programma atzīmē grāmatu kā aizņemtu, izmantojot `klienta metodi borrowBook`, un veic atjauninājumus:  
        - Grāmatas aizņemšanas informācija tiek saglabāta ar `CSVHandler.saveBorrowedBook`.  

14. `RETURN – Atgriezt aizņemto grāmatu`  
    _Kā tas darbojas:_  
    - Pieprasa ievadit klientu vardu kurš aizņem gramatu un grīb atgriezt gramatu biblioteka  
    - Ja klients eksistē un viņam ir aizņemtas grāmatas, tiek pieprasīts ievadīt grāmatas nosaukumu.
    - Pieprasa lietotājam ievadīt grāmatas nosaukumu, kuru viņš vēlas atgriezt.  
    - Atzīmē grāmatu kā pieejamu.  
    __Ka ir realizets koda__  
    ```  
    private static void returnBook(Scanner scanner, List<Book> books, List<Client> clients) {
    System.out.print("Enter client name: ");
    String clientName = scanner.nextLine();
    Client client = Client.findClientByName(clients, clientName);
    if (client == null) {
        System.out.println(ColorScheme.ERROR_COLOR + "Client not found." + ColorScheme.ERROR_RESET);
        return;
    }

    if (client.getBorrowedBook().isEmpty()) {
        System.out.println(ColorScheme.ERROR_COLOR + "This client hasn't borrowed any books." + ColorScheme.ERROR_RESET);
        return;
    }

    System.out.print("Enter book title to return: ");
    String bookTitle = scanner.nextLine();
    Book book = Book.findBookByTitle(books, bookTitle);
    if (book != null) {
        client.returnBook(book);
        CSVHandler.saveClientsToCSV(clients);
        CSVHandler.removeBorrowedBook(clientName, bookTitle);
    } else {
        System.out.println(ColorScheme.ERROR_COLOR + "Book not found." + ColorScheme.ERROR_RESET);
    }
    }
    ```  
    __Loģika__  
    1. __Klienta meklēšana:__  
        - Pieprasa ievadīt klienta vārdu.  
        - Meklē klientu sarakstā, izmantojot `metodi Client.findClientByName.`  
        - Ja klients netiek atrasts, izvada ziņojumu "Client not found." un iznāk no metodes.  
    2. __Pārbaude: vai klients ir aizņēmies kādu grāmatu:__  
        - Ja klientam nav nevienas aizņemtas grāmatas `(client.getBorrowedBook().isEmpty()),` izvada kļūdas ziņojumu `This client hasn't borrowed any books.` un pārtrauc darbību.
    3. __Grāmatas meklēšana:__  
        - Pieprasa ievadīt grāmatas nosaukumu.  
        - Meklē grāmatu sarakstā, izmantojot `metodi Book.findBookByTitle.`  
        - Ja grāmata netiek atrasta, izvada ziņojumu "Book not found."  
    4. __Grāmatas atgriešana:__  
        - Ja gan klients, gan grāmata ir atrasti, tiek izsaukta `klienta metode returnBook`, kas veic grāmatas atgriešanu.  
        - Saglabā atjaunoto klientu datus CSV failā, izmantojot `CSVHandler.saveClientsToCSV`.  
        - Noņem aizņemto grāmatu no klienta saraksta, izmantojot `CSVHandler.removeBorrowedBook`.  

15. `EXIT – Iziet no programmas`  

    _Kā tas darbojas:_  
    - Iziet no programmas.  
    __Ka tas ir realizets koda__  
    ```  
    case "exit":
        System.out.println("Goodbye!");
        System.exit(0);
        break;  
    ```  
    __Loģika__  
    1. __Ziņojuma izvadīšana:__  
        - Pirms iziešanas no programmas tiek izvadīts ziņojums `"Goodbye!"` konsolē, lai lietotājs zinātu, ka programma beigs savu darbību.  
    2. __Programmas beigšana:__  
        - Izmanto `System.exit(0)` komandu, lai nekavējoties izbeigtu programmas izpildi. Parametrs `0` norāda, ka programma beidzas veiksmīgi (nav kļūdu).  

  
  

# Funkcijas apraksti  

> Izmantojot programmu, ir pieejamas šādas komandas. Ievadiet nepieciešamo komandu un nospiediet Enter. Komandas var ievadīt jebkurā reģistrā.  

` Pamatkomandas: `  

` ADD `  – grāmatas pievienošana    
   - Ievadiet nosaukumu, autoru un izdošanas gadu.  
   - Dati tiek saglabāti failā.  

`REMOVE` – grāmatas dzēšana  
   - Ievadiet grāmatas nosaukumu, kuru vēlaties izdzēst.  
   - Ja dzēšana veiksmīga, parādīsies ziņojums: _Book "Nosaukums" deleted._  

` FIND ` – grāmatas meklēšana  
   - Ievadiet grāmatas nosaukumu.  
   - Ja grāmata ir atrasta, tiks parādīts tās nosaukums, autors un izdošanas gads.  
   - Ja nē, parādīsies ziņojums _Book not found._  


`LIST – grāmatu saraksta attēlošana ||      
list_client – klientu saraksta attēlošana  
-> Dati tiek parādīti tabulas formātā.`  

`REGISTER – jauna klienta reģistrācija`  
- Ievadiet klienta vārdu.  
- Ja klients ar tādu vārdu neeksistē, tas tiek pievienots sarakstam.
- Parādās ziņojums: _Client registered successfully._  
- Ja klients jau eksistē, tiek parādīts ziņojums: _Client already exists._  

`REMOVE_CLIENT – klienta dzēšana`  
- Ievadiet klienta vārdu, kuru vēlaties izdzēst.  
- Ja klientam __nav aizņemtu grāmatu,__ viņš tiek dzēsts no saraksta.  
- Ja dzēšana veiksmīga, parādās ziņojums: _Client "Vārds" removed successfully._  
- Ja klientam ir grāmatas, parādās ziņojums: _Client cannot be removed. They have borrowed books that must be returned first._  
- Ja klients nav atrasts, tiek parādīts: _Client not found._

` COUNT ` – parāda kopējo grāmatu skaitu bibliotēkā.  

`FILTER_BORROWED ` - filtrēšana pēc aizņemšanas statusa  
   - Ievadiet `true`, lai redzētu aizņemtās grāmatas, vai `false`, lai redzētu pieejamās.  
   - Tiek parādītas visas grāmatas ar izvēlēto statusu.  
   - Ja grāmatu nav, tiek parādīts atbilstošs paziņojums.  

`FILTER_YEAR` – filtrēšana pēc izdošanas gada  
   - Ievadiet 4 ciparu gadu (piemēram, 2020).  
   - Tiek parādītas visas grāmatas, kas izdotas norādītajā gadā.  
   - Ja grāmatu nav, tiek parādīts atbilstošs paziņojums. 
  
`SORT_ASC / SORT_DESC` – grāmatu kārtošana pēc izdošanas gada  
   - __sort_asc__ – no vecākās līdz jaunākajai.  
   - __sort_desc__ – no jaunākās līdz vecākajai.  
   - Rezultāti tiek attēloti tabulā.  

` BORROW `– grāmatas aizņemšanās  
   - Ievadiet klienta vārdu un grāmatas nosaukumu.  
   - Informācija par aizņemtajām grāmatām tiek saglabāta failā.  
   > Klienta vārds un grāmata jābūt reģistrētiem sistēmā.  

` RETURN ` – grāmatas atgriešana  
   - Ievadiet klienta vārdu un atgriežamās grāmatas nosaukumu.  
   - Informācija par aizņemto grāmatu tiek dzēsta no faila.  

` EXIT ` – iziešana no programmas.   
 

  
        
    






 